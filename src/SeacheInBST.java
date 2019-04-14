public class SeacheInBST
{
    /*
    Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.

    For example,

    Given the tree:
            4
           / \
          2   7
         / \
        1   3

    And the value to search: 2
    You should return this subtree:

          2
         / \
        1   3
    In the example above, if we want to search the value 5, since there is no node with value 5, we should return NULL.

    Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree format) as [], not null.
     */
    public static TreeNode searchBST(TreeNode root, int val)
    {
        if(root==null)
            return null;
        else if(root.val==val)
            return root;
        else
            return root.val>val?searchBST(root.left,val):searchBST(root.right,val);
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(4);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(1);
        root.left.right=new TreeNode(3);
        root.right=new TreeNode(7);
        TreeNode t=searchBST(root,2);
        System.out.println(t.val);
    }
}
