public class InOrderSuccessorBST
{
    /*
        Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
        The successor of a node p is the node with the smallest key greater than p.val.
        Example 1:
        Input: root = [2,1,3], p = 1
        Output: 2
        Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
        Example 2:
        Input: root = [5,3,6,2,4,null,null,1], p = 6
        Output: null
        Explanation: There is no in-order successor of the current node, so the answer is null.
        Note:

        If the given node has no in-order successor in the tree, return null.
        It's guaranteed that the values of the tree are unique.
     */

    public void inorderTraversal(TreeNode root)
    {
        if(root==null)
            return;
        inorderTraversal(root.left);
        System.out.print(root.val+", ");
        inorderTraversal(root.right);
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p)
    {
        if(root==null)
            return null;
        if(root.val<=p.val)
            return inorderSuccessor(root.right,p);
        else
        {
            TreeNode left=inorderSuccessor(root.left,p);
            return left==null?root:left;
        }
    }

    public TreeNode inOrderSuccessorIter(TreeNode root, TreeNode p)
    {
        if(root==null)
            return null;
        TreeNode temp=null;
        while(root!=null)
        {
            if(root.val<=p.val)
                root=root.right;
            else
            {
                temp=root;
                root=root.left;
            }
        }
        return temp;
    }



    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(3);
        root.left.left=new TreeNode(2);
        root.left.left.left=new TreeNode(1);
        root.left.right=new TreeNode(4);
        root.right=new TreeNode(6);


        TreeNode node=new TreeNode(20);
        node.left=new TreeNode(8);
        node.left.left=new TreeNode(4);
        node.left.right=new TreeNode(12);
        node.left.right.left=new TreeNode(10);
        node.left.right.right=new TreeNode(14);
        node.right=new TreeNode(22);

        InOrderSuccessorBST obj=new InOrderSuccessorBST();
        obj.inorderTraversal(node);
        System.out.print("\n");;
        System.out.println(obj.inOrderSuccessorIter(node,node.left.right.right).val);
    }

}


