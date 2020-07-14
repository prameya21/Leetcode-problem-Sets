import java.util.*;

public class MinimumHeightTrees
{
    /*
    For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
    Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
    Given such a graph, write a function to find all the MHTs and return a list of their root labels.

    Format
    The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
    You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

    Example 1 :

    Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

            0
            |
            1
           / \
          2   3

    Output: [1]
    Example 2 :

    Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

         0  1  2
          \ | /
            3
            |
            4
            |
            5

    Output: [3, 4]
    Note:

    According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
     */

    public List<Integer> findMinHeightTrees(int n, int[][] edges)
    {
        List<Integer> result=new ArrayList<>();
        if(n==0)
            return result;
        if(n==1)
        {
            result.add(0);
            return  result;
        }
        List<Integer>[] adjList=new List[n];
        for(int i=0;i<n;i++)
            adjList[i]=new ArrayList<>();
        int[] inDegree=new int[n];
        for(int[] i:edges)
        {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
            inDegree[i[0]]++;
            inDegree[i[1]]++;
        }

        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<n;i++)
            if(adjList[i].size()==1)
                q.offer(i);



        if(n<=2)
            return new ArrayList<>(q);

        while(n>2)
        {
            int size=q.size();
            n-=size;
            for(int i=0;i<size;i++)
            {
                int curr=q.poll();
                for(int nxt:adjList[curr])
                {
                    inDegree[nxt]--;
                    if(inDegree[nxt]==1)
                        q.offer(nxt);
                }
            }
        }
        return new ArrayList<>(q);
    }




    public static void main(String[] args)
    {
        MinimumHeightTrees obj=new MinimumHeightTrees();
        System.out.println(obj.findMinHeightTrees(6, new int[][]{{0,3},{1,3},{2,3},{4,3},{5,4}}));

        System.out.println(obj.findMinHeightTrees(7,new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}}));
    }

}
