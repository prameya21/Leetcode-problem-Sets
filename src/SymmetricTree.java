public class SymmetricTree
{
    /*
    Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

    For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

        1
       / \
      2   2
     / \ / \
    3  4 4  3
    But the following [1,2,2,null,3,null,3] is not:
        1
       / \
      2   2
       \   \
       3    3
     */
    public static boolean isSymmetric(TreeNode root)
    {
        return isSymmetric(root,root);
    }
    public static boolean isSymmetric(TreeNode root1, TreeNode root2)
    {
        if(root1==null && root2==null)
            return true;
        else if(root1.val==root2.val)
            return isSymmetric(root1.left,root2.right) && isSymmetric(root1.right, root2.left);
        else
            return false;
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(4);
        root.right=new TreeNode(2);
        root.right.left=new TreeNode(4);
        root.right.right=new TreeNode(3);
        System.out.println(isSymmetric(root));
    }
}
