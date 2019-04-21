public class MaximumBinaryTree
{
    /*
    Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

    The root is the maximum number in the array.
    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
    Construct the maximum tree by the given array and output the root node of this tree.

    Example 1:
    Input: [3,2,1,6,0,5]
    Output: return the tree root node representing the following tree:

          6
        /   \
       3     5
        \    /
         2  0
           \
            1
    Note:
    The size of the given array will be in the range [1,1000].
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums)
    {
        return construct(nums,0,nums.length);
    }
    public static TreeNode construct(int[] arr,int s,int e)
    {
        if(s>=e)
            return null;
        int max=max(arr,s,e);
        TreeNode root=new TreeNode(arr[max]);
        root.left=construct(arr,s,max);
        root.right=construct(arr,max+1,e);
        return root;
    }
    public static int max(int[] arr,int s,int e)
    {
        int max=s;
        for(int i=s+1;i<e;i++)
        {
            if(arr[i]>arr[max])
                max=i;
        }
        return max;
    }
    public static void main(String[] args)
    {
        int[] arr={3,2,1,6,0,5};
        TreeNode root=constructMaximumBinaryTree(arr);
        System.out.println(root);
    }
}
