import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoSumBST
{
    /*
    Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.


    Example 1:



    Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
    Output: true
    Explanation: 2 and 3 sum up to 5.
    Example 2:



    Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
    Output: false


    Constraints:

    Each tree has at most 5000 nodes.
    -10^9 <= target, node.val <= 10^9
     */

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target)
    {
        Set<Integer> set=new HashSet<>();
        helper(root1,set, target);
        return check(set,root2);
    }

    public boolean check(Set<Integer> set, TreeNode root)
    {
        if(root==null)
            return false;
        if(set.contains(root.val))
            return true;
        return check(set,root.left) || check(set,root.right);
    }

    public void helper(TreeNode root, Set<Integer> l1, int target)
    {
        if(root==null)
            return;
        helper(root.left,l1, target);
        l1.add(target-root.val);
        helper(root.right,l1, target);
    }

    public static void main(String[] args)
    {
        TwoSumBST obj=new TwoSumBST();
        TreeNode root1=new TreeNode(2);
        root1.left=new TreeNode(1);
        root1.right=new TreeNode(4);

        TreeNode root2=new TreeNode(1);
        root2.left=new TreeNode(0);
        root2.right=new TreeNode(3);

        System.out.println(obj.twoSumBSTs(root1,root2,5));

    }


}
