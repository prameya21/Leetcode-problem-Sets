import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths
{
    /*
    Given a binary tree, return all root-to-leaf paths.

    Note: A leaf is a node with no children.

    Example:

    Input:

       1
     /   \
    2     3
     \
      5

    Output: ["1->2->5", "1->3"]

    Explanation: All root-to-leaf paths are: 1->2->5, 1->3
     */
    public List<String> binaryTreePaths(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        List<String> res=new ArrayList<>();
        helper(root,res,"");
        return res;
    }
    public void helper(TreeNode root,List<String> res,String path)
    {
        if(root==null)
            return;
        if(root.left==null && root.right==null)
        {
            path+=root.val;
            res.add(path);
            return;
        }
        path+=root.val+"->";
        helper(root.left,res,path);
        helper(root.right,res,path);
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right=new TreeNode(3);
        BinaryTreePaths obj=new BinaryTreePaths();
        System.out.println(obj.binaryTreePaths(root));
    }
}
