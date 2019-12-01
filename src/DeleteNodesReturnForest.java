import java.util.*;

public class DeleteNodesReturnForest
{
    /*
    Given the root of a binary tree, each node in the tree has a distinct value.

    After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

    Return the roots of the trees in the remaining forest.  You may return the result in any order.



    Example 1:



    Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
    Output: [[1,2,null,4],[6],[7]]


    Constraints:

    The number of nodes in the given tree is at most 1000.
    Each node has a distinct value between 1 and 1000.
    to_delete.length <= 1000
    to_delete contains distinct values between 1 and 1000.
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete)
    {
        Set<Integer> del=new HashSet<>();
        for(int i:to_delete)
            del.add(i);
        List<TreeNode> res=new ArrayList<>();
        helper(root,res,del,true);
        return res;
    }
    public TreeNode helper(TreeNode root,List<TreeNode> res, Set<Integer> del, boolean isRoot)
    {
        if(root==null)
            return null;
        boolean delete=del.contains(root.val);
        if(!delete && isRoot)
            res.add(root);
        root.left=helper(root.left,res,del,delete);
        root.right=helper(root.right,res,del,delete);
        return delete?null:root;
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
        DeleteNodesReturnForest obj=new DeleteNodesReturnForest();
        System.out.println(obj.delNodes(root,new int[]{3,5}));
    }
}