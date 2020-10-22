public class SumOfEvenValuedGrandParentNodes
{
    /*
    Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

    If there are no nodes with an even-valued grandparent, return 0.
    Example 1:



    Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
    Output: 18
    Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
     */


    public int sumEvenGrandparent(TreeNode root)
    {
        if(root==null)
            return 0;

        return helper(root,null,null);

    }

    public int helper(TreeNode root, TreeNode parent,TreeNode grandParent)
    {
        if(root==null)
            return 0;
        int res=0;
        if(grandParent!=null && grandParent.val%2==0)
            res+=root.val;
        res+=helper(root.left,root,parent);
        res+=helper(root.right,root,parent);
        return res;
    }
}
