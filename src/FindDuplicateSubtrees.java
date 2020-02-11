import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees
{
    /*
        Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

        Two trees are duplicate if they have the same structure with same node values.

        Example 1:

                1
               / \
              2   3
             /   / \
            4   2   4
               /
              4
        The following are two duplicate subtrees:

              2
             /
            4
        and

            4
        Therefore, you need to return above trees' root in the form of a list.
     */

    public List<TreeNode> findDuplicateSubtrees(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        List<TreeNode> res=new ArrayList<>();
        serialize(root,res,new HashMap<>());
        return res;
    }

    public String serialize(TreeNode root,List<TreeNode> res, Map<String,Integer> map)
    {
        if(root==null)
            return "#";
        String s=root.val+","+serialize(root.left,res,map)+","+serialize(root.right,res,map);
        map.put(s,map.getOrDefault(s,0)+1);
        if(map.get(s)==2)
            res.add(root);
        return s;
    }



}
