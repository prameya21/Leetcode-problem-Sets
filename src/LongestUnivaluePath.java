public class LongestUnivaluePath
{
    /*
    Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

    The length of path between two nodes is represented by the number of edges between them.



    Example 1:

    Input:

                  5
                 / \
                4   5
               / \   \
              1   1   5
    Output: 2



    Example 2:

    Input:

                  1
                 / \
                4   5
               / \   \
              4   4   5
    Output: 2
     */
    static int ans;
    public static int longestUnivaluePath(TreeNode root)
    {
        ans=0;
        if(root==null)
            return 0;
        compute(root);
        return ans;
    }
    public static int compute(TreeNode root)
    {
        if(root==null)
            return 0;
        int left=compute(root.left);
        int right=compute(root.right);
        int lval=0,rval=0;
        if(root.left!=null && root.left.val==root.val)
            lval=left+1;
        if(root.right!=null && root.right.val==root.val)
            rval=right+1;
        ans=Math.max(ans,rval+lval);
        return Math.max(lval,rval);
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(4);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(4);
        root.right=new TreeNode(5);
        root.right.right=new TreeNode(5);
        System.out.println(longestUnivaluePath(root));
    }
}
