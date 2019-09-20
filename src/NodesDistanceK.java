import java.util.*;

public class NodesDistanceK
{
    /*
    We are given a binary tree (with root node root), a target node, and an integer value K.

    Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.



    Example 1:

    Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

    Output: [7,4,1]

    Explanation:
    The nodes that are a distance 2 from the target node (with value 5)
    have values 7, 4, and 1.



    Note that the inputs "root" and "target" are actually TreeNodes.
    The descriptions of the inputs above are just serializations of these objects.
     */

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K)
    {
        Map<TreeNode,TreeNode> parentMap=new HashMap<>();
        linkParents(root,null,parentMap);
        Queue<TreeNode> q=new LinkedList<>();
        Set<TreeNode> visited=new HashSet<>();
        q.offer(null);
        q.offer(target);
        visited.add(null);
        visited.add(target);
        int dist=0;
        List<Integer> res=new ArrayList<>();
        while(!q.isEmpty())
        {
            TreeNode curr=q.poll();
            if(curr==null)
            {
                if(dist==K)
                {
                    for(TreeNode t:q)
                        res.add(t.val);
                    return res;
                }
                dist++;
                q.offer(null);
            }
            else
            {
                if(!visited.contains(curr.left))
                {
                    q.offer(curr.left);
                    visited.add(curr.left);
                }
                if(!visited.contains(curr.right))
                {
                    q.offer(curr.right);
                    visited.add(curr.right);
                }
                TreeNode parent=parentMap.get(curr);
                if(!visited.contains(parent))
                {
                    q.offer(parent);
                    visited.add(parent);
                }
            }
        }
        return new ArrayList<>();
    }
    public void linkParents(TreeNode root, TreeNode parent, Map<TreeNode,TreeNode> map)
    {
        if(root!=null)
        {
            map.put(root,parent);
            linkParents(root.left,root,map);
            linkParents(root.right,root,map);
        }
    }
    public static void main(String[] args)
    {
        NodesDistanceK obj=new NodesDistanceK();
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(5);
        root.right=new TreeNode(1);
        root.left.left=new TreeNode(6);
        root.left.right=new TreeNode(2);
        root.left.right.left=new TreeNode(7);
        root.left.right.right=new TreeNode(4);
        root.right.left=new TreeNode(0);
        root.right.right=new TreeNode(8);
        System.out.println(obj.distanceK(root,root.left,2));
    }
}
