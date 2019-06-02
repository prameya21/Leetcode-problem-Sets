import java.util.HashMap;
import java.util.Map;

public class HouseRobberTree
{
    /*
    The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

    Determine the maximum amount of money the thief can rob tonight without alerting the police.

    Example 1:

    Input: [3,2,3,null,3,null,1]

         3
        / \
       2   3
        \   \
         3   1

    Output: 7
    Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
    Example 2:

    Input: [3,4,5,1,3,null,1]

         3
        / \
       4   5
      / \   \
     1   3   1

    Output: 9
    Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
     */
    public static int rob(TreeNode root)
    {
        if(root==null)
            return 0;
        int val=0;
        if(root.left!=null)
            val+=rob(root.left.left)+rob(root.left.right);
        if(root.right!=null)
            val+=rob(root.right.left)+rob(root.right.right);
        return Math.max(val+root.val,rob(root.left)+rob(root.right));
    }
    public static int rob2(TreeNode root)
    {
        return rob2Helper(root,new HashMap<>());
    }
    public static int rob2Helper(TreeNode root, Map<TreeNode,Integer> map)
    {
        if(root==null)
            return 0;
        if(map.containsKey(root))
            return map.get(root);
        int val=0;
        if(root.left!=null)
            val+=rob2Helper(root.left.left,map)+rob2Helper(root.left.right,map);
        if(root.right!=null)
            val+=rob2Helper(root.right.left,map)+rob2Helper(root.right.right,map);
        val=Math.max(val+root.val,rob2Helper(root.left,map)+rob2Helper(root.right,map));
        map.put(root,val);
        return val;
    }
    public static int rob3(TreeNode root)
    {
        int[] res=rob3Helper(root);
        return Math.max(res[0],res[1]);
    }
    public static int[] rob3Helper(TreeNode root)
    {
        if(root==null)
            return new int[2];
        int[] left=rob3Helper(root.left);
        int[] right=rob3Helper(root.right);
        int[] val=new int[2];
        val[0]=Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        val[1]=root.val+left[0]+right[0];
        return val;
    }
    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(4);
        root.left.left=new TreeNode(1);
        root.left.right=new TreeNode(3);
        root.right=new TreeNode(5);
        root.right.right=new TreeNode(1);
        System.out.println(rob3(root));
    }
}
