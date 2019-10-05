public class MorrisTraversal
{
    /*
    Morris Traversal for inorder traversal of a binary tree
     */

    public void inorder(TreeNode root)
    {
        if(root==null)
            return;
        inorder(root.left);
        System.out.print(root.val+",");
        inorder(root.right);
    }
    public void morrisInorder(TreeNode root)
    {
        TreeNode current=root;
        while(current!=null)
        {
            if(current.left==null)
            {
                System.out.print(current.val+",");
                current=current.right;
            }
            else
            {
                TreeNode pre=current.left;
                while(pre.right!=null && pre.right!=current)
                    pre=pre.right;
                if(pre.right==null)
                {
                    pre.right=current;
                    current=current.left;
                }
                else
                {
                    pre.right=null;
                    System.out.print(current.val+",");
                    current=current.right;
                }
            }
        }
    }

    public void preorder(TreeNode root)
    {
        if(root==null)
            return;
        System.out.print(root.val+",");
        preorder(root.left);
        preorder(root.right);
    }

    public void morrisPreorder(TreeNode root)
    {
        TreeNode current=root;
        while(current!=null)
        {
            if(current.left==null)
            {
                System.out.print(current.val+",");
                current=current.right;
            }
            else
            {
                TreeNode pre=current.left;
                while(pre.right!=null && pre.right!=current)
                    pre=pre.right;
                if(pre.right==null)
                {
                    pre.right=current;
                    System.out.print(current.val+",");
                    current=current.left;
                }
                else
                {
                    pre.right=null;
                    current=current.right;
                }
            }
        }
    }


    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(10);
        root.left=new TreeNode(5);
        root.left.left=new TreeNode(-2);
        root.left.left.right=new TreeNode(2);
        root.left.left.right.left=new TreeNode(-1);
        root.left.right=new TreeNode(6);
        root.left.right.right=new TreeNode(8);
        root.right=new TreeNode(30);
        root.right.right=new TreeNode(40);

        MorrisTraversal obj=new MorrisTraversal();
        obj.inorder(root);
        System.out.println();
        obj.morrisInorder(root);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        obj.preorder(root);;
        System.out.println();
        obj.morrisPreorder(root);



    }
}
