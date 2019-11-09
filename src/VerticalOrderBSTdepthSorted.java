import java.util.*;

public class VerticalOrderBSTdepthSorted
{
    /*
    Given a binary tree, return the vertical order traversal of its nodes values.

    For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

    Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

    If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

    Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

    Example 1:
    Input: [3,9,20,null,null,15,7]
    Output: [[9],[3,15],[20],[7]]
    Explanation:
    Without loss of generality, we can assume the root node is at position (0, 0):
    Then, the node with value 9 occurs at position (-1, -1);
    The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
    The node with value 20 occurs at position (1, -1);
    The node with value 7 occurs at position (2, -2).
    Example 2:
    Input: [1,2,3,4,5,6,7]
    Output: [[4],[2],[1,5,6],[3],[7]]
    Explanation:
    The node with value 5 and the node with value 6 have the same position according to the given scheme.
    However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.


    Note:

    The tree will have between 1 and 1000 nodes.
    Each node's value will be between 0 and 1000.
     */
    class Node
    {
        int x,y;
        TreeNode node;
        public Node(TreeNode node,int x, int y)
        {
            this.node=node;
            this.x=x;
            this.y=y;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        Queue<Node> q=new LinkedList<>();
        Queue<Integer> cols=new LinkedList<>();
        Map<Integer,List<Node>> map=new HashMap<>();
        int min=0,max=0;
        cols.offer(0);
        q.offer(new Node(root,0,0));
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                Node n=q.poll();
                int c=cols.poll();
                map.putIfAbsent(c,new ArrayList<>());
                map.get(c).add(n);
                if(n.node.left!=null)
                {
                    q.offer(new Node(n.node.left,c-1,n.y+1));
                    cols.offer(c-1);
                    min=Math.min(min,n.x-1);
                }
                if(n.node.right!=null)
                {
                    q.offer(new Node(n.node.right,c+1,n.y+1));
                    cols.offer(c+1);
                    max=Math.max(max,n.x+1);
                }
            }

        }
        List<List<Integer>> res=new ArrayList<>();
        for(int i=min;i<=max;i++)
        {
            Collections.sort(map.get(i), new Comparator<Node>() {
                @Override
                public int compare(Node a, Node b) {
                    if(a.y==b.y)
                        return a.node.val-b.node.val;
                    return 0;
                }
            });
            List<Integer> temp=new ArrayList<>();
            for(Node n:map.get(i))
                temp.add(n.node.val);
            res.add(new ArrayList<>(temp));
        }
        return res;
    }
}
