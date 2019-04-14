public class BinaryTreeMaximumPathSum
{
    /*
    Given a non-empty binary tree, find the maximum path sum.

    For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

    Example 1:

    Input: [1,2,3]

           1
          / \
         2   3

    Output: 6
    Example 2:

    Input: [-10,9,20,null,null,15,7]

       -10
       / \
      9  20
        /  \
       15   7

    Output: 42
     */
    static int max_gain;
    public static int maxPathSum(TreeNode root)
    {
        if(root==null)
            return 0;
        compute(root);
        return max_gain;
    }
    public static int compute(TreeNode root)
    {
        if(root==null)
            return 0;
        int left=Math.max(compute(root.left),0);
        int right=Math.max(compute(root.right),0);
        max_gain=Math.max(max_gain,left+right+root.val);
        return Math.max(left,right) + root.val;
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(-10);
        root.left=new TreeNode(9);
        root.right=new TreeNode(20);
        root.right.left=new TreeNode(15);
        root.right.right=new TreeNode(7);
        System.out.println(maxPathSum(root));
    }
}
