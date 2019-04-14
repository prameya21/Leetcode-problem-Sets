public class MirrorTree
{
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(4);
        root.right=new TreeNode(5);
        mirror(root);
        System.out.println(root);
    }

    public static void mirror(TreeNode root)
    {
        if(root==null)
            return;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        mirror((root.left));
        mirror(root.right);
    }
}
