public class ValidateBinarySearchTree
{
    /*
    Given a binary tree, determine if it is a valid binary search tree (BST).

    Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.
    Example 1:

    Input:
        2
       / \
      1   3
    Output: true
    Example 2:

        5
       / \
      1   4
         / \
        3   6
    Output: false
    Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
                 is 5 but its right child's value is 4.
     */
    public static boolean isValidBST(TreeNode root)
    {
        return helper(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public static boolean helper(TreeNode root,int min,int max)
    {
        if(root==null)
            return true;
        if(root.val<=min ||root.val>=max)
            return false;
        else
            return helper(root.left,min,root.val) && helper(root.right,root.val+1,max);
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(1);
        root.right=new TreeNode(4);
        root.right.left=new TreeNode(3);
        root.right.right=new TreeNode(6);
        System.out.println(isValidBST(root));
    }
}
