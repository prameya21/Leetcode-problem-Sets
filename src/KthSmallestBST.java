public class KthSmallestBST
{
    /*
    Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

    Note:
    You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

    Example 1:

    Input: root = [3,1,4,null,2], k = 1
       3
      / \
     1   4
      \
       2
    Output: 1
    Example 2:

    Input: root = [5,3,6,2,4,null,null,1], k = 3
           5
          / \
         3   6
        / \
       2   4
      /
     1
    Output: 3
     */
    int count,ans=0;
    public int kthSmallest(TreeNode root, int k)
    {
        count=k;
        helper(root);
        return ans;
    }
    public void helper(TreeNode root)
    {
        if(root==null)
            return;
        helper(root.left);
        count--;
        if(count==0)
        {
            ans=root.val;
            return;
        }
        helper(root.right);
    }
    public static void main(String[] args)
    {
        KthSmallestBST obj=new KthSmallestBST();
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(3);
        root.left.left=new TreeNode(2);
        root.left.left.left=new TreeNode(1);
        root.left.right=new TreeNode(4);
        root.right=new TreeNode(6);
        System.out.println(obj.kthSmallest(root,3));
    }
}
