public class MaximumSumBSTInBinaryTree
{
    /*
    Given a binary tree root, the task is to return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

    Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.


    Example 1:



    Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
    Output: 20
    Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
    Example 2:



    Input: root = [4,3,null,1,2]
    Output: 2
    Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
    Example 3:

    Input: root = [-4,-2,-5]
    Output: 0
    Explanation: All values are negatives. Return an empty BST.
    Example 4:

    Input: root = [2,1,3]
    Output: 6
    Example 5:

    Input: root = [5,4,8,3,null,6,3]
    Output: 7


    Constraints:

    The given binary tree will have between 1 and 40000 nodes.
    Each node's value is between [-4 * 10^4 , 4 * 10^4].
     */

    int max;
    public int maxSumBST(TreeNode root)
    {
        max=Integer.MIN_VALUE;
        helper(root);
        return max;
    }

    public int[] helper(TreeNode root)
    {
        if(root==null)
            return new int[]{1,Integer.MIN_VALUE,Integer.MAX_VALUE,0};
        int[] left=helper(root.left);
        int[] right=helper(root.right);
        boolean isBst=left[0]==1 && right[0]==1 && left[1]<root.val && root.val<right[2];
        int sum=root.val+left[3]+right[3];
        if(isBst)
            max=Math.max(max,sum);
        return new int[]{isBst?1:0,Math.max(right[1],root.val),Math.min(left[2],root.val),sum};
    }

}
