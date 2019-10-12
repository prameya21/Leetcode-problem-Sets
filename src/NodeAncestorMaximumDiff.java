public class NodeAncestorMaximumDiff
{
    /*
    Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

    (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
    Example 1:
    Input: [8,3,10,1,6,null,14,null,null,4,7,13]
    Output: 7
    Explanation:
    We have various ancestor-node differences, some of which are given below :
    |8 - 3| = 5
    |3 - 7| = 4
    |8 - 1| = 7
    |10 - 13| = 3
    Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

    Note:

    The number of nodes in the tree is between 2 and 5000.
    Each node will have value between 0 and 100000.
     */
    int diff=Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root)
    {
        helper(root,root.val,root.val);
        return diff;
    }
    public void helper(TreeNode root, int min, int max)
    {
        if(root==null)
            return;
        min=Math.min(min,root.val);
        max=Math.max(max,root.val);
        diff=Math.max(diff,max-min);
        helper(root.left,min,max);
        helper(root.right,min,max);
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(8);
        root.left=new TreeNode(3);
        root.left.left=new TreeNode(1);
        root.left.right=new TreeNode(6);
        root.left.right.left=new TreeNode(4);
        root.left.right.right=new TreeNode(7);
        root.right=new TreeNode(10);
        root.right.right=new TreeNode(14);
        root.right.right.left=new TreeNode(13);
        NodeAncestorMaximumDiff obj=new NodeAncestorMaximumDiff();
        System.out.println(obj.maxAncestorDiff(root));

    }
}
