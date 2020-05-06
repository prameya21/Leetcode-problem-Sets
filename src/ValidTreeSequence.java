public class ValidTreeSequence
{
    public boolean isValidSequence(TreeNode root, int[] arr)
    {
        if(arr.length==0 && root==null)
            return true;
        if(arr==null || arr.length==0)
            return true;
        return dfs(root,arr,0);
    }
    public boolean dfs(TreeNode root, int[] arr, int i)
    {
        if(root==null && i<arr.length)
            return false;
        if(root.val!=arr[i])
            return false;
        if(root.val==arr[i] && (root.left==null && root.right==null))
            return false;
        if(i==arr.length-1 && root.val==arr[i])
            return true;
        return dfs(root.left,arr,i+1) || dfs(root.right,arr,i+1);
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(0);
        root.left=new TreeNode(1);
        root.left.left=new TreeNode(0);
        root.left.left.right=new TreeNode(1);
        root.left.right=new TreeNode(1);
        root.left.right.left=new TreeNode(0);
        root.left.right.right=new TreeNode(0);
        root.right=new TreeNode(0);
        root.right.left=new TreeNode(0);
        ValidTreeSequence obj=new ValidTreeSequence();
        System.out.println(obj.isValidSequence(root,new int[]{0,1,0,1}));
    }
}
