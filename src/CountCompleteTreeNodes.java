public class CountCompleteTreeNodes
{
    /*
    Given a complete binary tree, count the number of nodes.

    Note:

    Definition of a complete binary tree from Wikipedia:
    In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

    Example:

    Input:
        1
       / \
      2   3
     / \  /
    4  5 6

    Output: 6
     */
    public int height(TreeNode root)
    {
        return root==null?-1:1+height(root.left);
    }
    public int countNodes(TreeNode root)
    {
        int h=height(root),nodes=0;
        while(root!=null)
        {
            if(height(root.right)==h-1)
            {
                nodes+=1<<h;
                root=root.right;
            }
            else
            {
                nodes+=1<<h-1;
                root=root.left;
            }
            h--;
        }
        return nodes;
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right=new TreeNode(3);
        root.right.left=new TreeNode(6);
        CountCompleteTreeNodes obj=new CountCompleteTreeNodes();
        System.out.println(obj.countNodes(root));
    }

}
