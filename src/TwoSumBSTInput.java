import java.util.HashSet;
import java.util.Set;

public class TwoSumBSTInput
{
    /*
    Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

    Example 1:

    Input:
        5
       / \
      3   6
     / \   \
    2   4   7

    Target = 9

    Output: True


    Example 2:

    Input:
        5
       / \
      3   6
     / \   \
    2   4   7

    Target = 28

    Output: False
     */

    public static boolean findTarget(TreeNode root, int k)
    {
        if(root==null)
            return false;
        Set<Integer> set=new HashSet<>();
        return findTarget(root,k,set);
    }
    public static boolean findTarget(TreeNode root,int target,Set<Integer> set)
    {
        if(root==null)
            return false;
        if(set.contains(target-root.val))
            return true;
        set.add(root.val);
        return findTarget(root.left,target,set) || findTarget(root.right,target,set);
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(3);
        root.left.left=new TreeNode(2);
        root.left.right=new TreeNode(4);
        root.right=new TreeNode(6);
        root.right.right=new TreeNode(7);
        System.out.println(findTarget(root,9));
    }
}
