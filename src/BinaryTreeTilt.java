public class BinaryTreeTilt
{
    int res;
    public int findTilt(TreeNode root)
    {
        res=0;
        if(root==null)
            return 0;
        helper(root);
        return res;
    }

    public int helper(TreeNode root)
    {
        if(root==null)
            return 0;
        int l=helper(root.left);
        int r=helper(root.right);
        res+=Math.abs(l-r);
        return l+r+root.val;
    }
}
