public class LargestBST
{
    /*
    Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

    Note:
    A subtree must include all of its descendants.

    Example:

    Input: [10,5,15,1,8,null,7]

       10
       / \
      5  15
     / \   \
    1   8   7

    Output: 3
    Explanation: The Largest BST Subtree in this case is the highlighted one.
                 The return value is the subtree's size, which is 3.

     */
    public int largestBSTSubtree(TreeNode root)
    {
        int[] ret=helper(root);
        return ret[2];
    }
    public int[] helper(TreeNode root)
    {
        if(root==null)
            return new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,0};
        int[] left=helper(root.left);
        int[] right=helper(root.right);
        if(root.val>left[1] && root.val<right[0])
            return new int[]{Math.min(left[0],root.val),Math.max(root.val,right[1]),left[2]+right[2]+1};
        else
            return new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,Math.max(left[2],right[2])};
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(10);
        root.left=new TreeNode(5);
        root.left.left=new TreeNode(1);
        root.left.right=new TreeNode(8);
        root.right=new TreeNode(15);
        root.right.right=new TreeNode(7);
        LargestBST obj=new LargestBST();
        System.out.println(obj.largestBSTSubtree(root));
    }
}
