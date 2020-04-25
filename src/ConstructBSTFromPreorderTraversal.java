import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConstructBSTFromPreorderTraversal
{
    /*
    Return the root node of a binary search tree that matches the given preorder traversal.

    (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.
    Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

    Example 1:

    Input: [8,5,1,7,10,12]
    //inorder 1,5,7,8,10,12
    Output: [8,5,10,1,7,null,12]

    Note:

    1 <= preorder.length <= 100
    The values of preorder are distinct.
     */
    int pre_idx=0;
    public TreeNode bstFromPreorder(int[] preorder)
    {
        int[] inorder= Arrays.copyOf(preorder,preorder.length);
        Arrays.sort(inorder);
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++)
            map.put(inorder[i],i);

        return helper(preorder,map,0,preorder.length);
    }

    public TreeNode helper(int[] preorder, Map<Integer,Integer> map,int left, int right)
    {
        if(left==right)
            return null;
        TreeNode root=new TreeNode(preorder[pre_idx]);
        int index=map.get(root.val);
        pre_idx++;
        root.left=helper(preorder,map,left,index);
        root.right=helper(preorder,map,index+1,right);
        return root;
    }

    public TreeNode bstFromPreorder2(int[] preorder)
    {
        return helper(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public TreeNode helper(int[] preorder,int left, int right)
    {
        if(pre_idx==preorder.length)
            return null;
        int val=preorder[pre_idx];
        if(val<left || val>right)
            return null;
        TreeNode root=new TreeNode(val);
        pre_idx++;
        root.left=helper(preorder,left,val);
        root.right=helper(preorder,val,right);
        return root;
    }

    public static void main(String[] args)
    {
        ConstructBSTFromPreorderTraversal obj=new ConstructBSTFromPreorderTraversal();
        System.out.println(obj.bstFromPreorder(new int[]{8,5,1,7,10,12}));
    }
}
