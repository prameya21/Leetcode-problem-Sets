import java.util.ArrayList;
import java.util.List;

public class CollectLeavesBT
{
    /*
    Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.


    Example:

    Input: [1,2,3,4,5]

              1
             / \
            2   3
           / \
          4   5

    Output: [[4,5,3],[2],[1]]


    Explanation:

    1. Removing the leaves [4,5,3] would result in this tree:

              1
             /
            2


    2. Now removing the leaf [2] would result in this tree:

              1


    3. Now removing the leaf [1] would result in the empty tree:

              []
     */
    public static List<List<Integer>> findLeaves(TreeNode root)
    {
        List<List<Integer>> res=new ArrayList<>();
        if(root==null)
            return res;
        while(root!=null)
        {
            List<Integer> leaves = new ArrayList<>();
            root = removeLeaves(root, leaves);
            res.add(leaves);
        }
        return res;
    }
    public static TreeNode removeLeaves(TreeNode root,List<Integer> leaves)
    {
        if(root==null)
            return null;
        if(root.left==null && root.right==null)
        {
            leaves.add(root.val);
            return null;
        }
        root.left=removeLeaves(root.left,leaves);
        root.right=removeLeaves(root.right,leaves);
        return root;
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right=new TreeNode(3);
        System.out.println(findLeaves(root));
    }
}
