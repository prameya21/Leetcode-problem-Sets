import java.util.ArrayList;
import java.util.List;

public class RecoverBST
{
    /*
    Two elements of a binary search tree (BST) are swapped by mistake.

    Recover the tree without changing its structure.

    Example 1:

    Input: [1,3,null,null,2]

       1
      /
     3
      \
       2

    Output: [3,1,null,null,2]

       3
      /
     1
      \
       2
    Example 2:

    Input: [3,1,4,null,null,2]

      3
     / \
    1   4
       /
      2

    Output: [2,1,4,null,null,3]

      2
     / \
    1   4
       /
      3
    Follow up:

    A solution using O(n) space is pretty straight forward.
    Could you devise a constant space solution?
     */
    public void inorder(TreeNode root)
    {
        if(root==null)
            return;
        inorder(root.left);
        System.out.print(root.val);
        inorder(root.right);
    }

    //Approach 1
    public void inorder(TreeNode root, List<Integer> num)
    {
        if(root==null)
            return;
        inorder(root.left,num);
        num.add(root.val);
        inorder(root.right,num);
    }

    public int[] findSwapped(List<Integer> num)
    {
        int x=-1,y=-1;
        for(int i=0;i<num.size()-1;i++)
        {
            if(num.get(i+1)<num.get(i))
            {
                y=num.get(i+1);
                if(x==-1)
                    x=num.get(i);
                else
                    break;
            }

        }
        return new int[]{x,y};
    }

    public void swap(TreeNode root, int cnt, int[] num)
    {
        if(root==null)
            return;
        swap(root.left,cnt,num);
        if(root.val==num[0] || root.val==num[1])
        {
            root.val=root.val==num[0]?num[1]:num[0];
            cnt--;
        }
        if(cnt==0)
            return;
        swap(root.right,cnt,num);
    }

    public void recoverTree1(TreeNode root)
    {
        List<Integer> list=new ArrayList<>();
        inorder(root,list);
        int[] s=findSwapped(list);
        swap(root,2,s);
    }


    //Approach 2
    TreeNode pre=null,first=null,second=null;
    public void recoverTree2(TreeNode root)
    {
        traverse(root);
        int temp=first.val;
        first.val=second.val;
        second.val=temp;
    }
    public void traverse(TreeNode root)
    {
        if(root==null)
            return;
        traverse(root.left);
        if(pre!=null && root.val<pre.val)
        {
            first=root;
            if(second==null)
                second=pre;
            else
                return;
        }

        pre=root;
        traverse(root.right);
    }

    //Approach3
    public void recoverTree(TreeNode root)
    {
        TreeNode prev=null,x=null,y=null;
        TreeNode current=root;
        while(current!=null)
        {
            if(current.left==null)
            {
                if(prev!=null && current.val<prev.val)
                {
                    x=current;
                    if(y==null)
                        y=prev;
                    else
                        break;
                }
                prev=current;
                current=current.right;
            }
            else
            {
                TreeNode pred=current.left;
                while(pred.right!=null && pred.right!=current)
                    pred=pred.right;
                if(pred.right==null)
                {
                    pred.right=current;
                    current=current.left;
                }
                else
                {
                    if(prev!=null && current.val<prev.val)
                    {
                        x=current;
                        if(y==null)
                            y=prev;
                        else
                            break;
                    }
                    prev=current;
                    pred.right=null;
                    current=current.right;
                }
            }
        }

        int temp=x.val;
        x.val=y.val;
        y.val=x.val;

    }

    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(1);
        root.right=new TreeNode(4);
        root.right.left=new TreeNode(2);

        RecoverBST obj=new RecoverBST();
        obj.inorder(root);
        obj.recoverTree(root);
        System.out.println();
        obj.inorder(root);
    }
}
