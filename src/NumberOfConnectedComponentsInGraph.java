import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfConnectedComponentsInGraph
{
    /*
    Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

    Example 1:

    Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

         0          3
         |          |
         1 --- 2    4

    Output: 2
    Example 2:

    Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

         0           4
         |           |
         1 --- 2 --- 3

    Output:  1
    Note:
    You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     */
    public static int countComponents(int n, int[][] edge)
    {
        List<List<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<n;i++)
            adjList.add(new ArrayList<Integer>());
        for(int[] e:edge)
        {
            adjList.get(e[0]).add(e[1]);
            adjList.get(e[1]).add(e[0]);
        }
        int count=0;
        boolean[] visited=new boolean[n];
        for(int i=0;i<n;i++)
        {
            if(!visited[i])
            {
                bfs(visited,i,adjList);
                count++;
            }
        }
        return count;
    }
    public static void dfs(boolean[] visited,int index,List<List<Integer>> adjList)
    {
        if(visited[index])
            return;
        visited[index]=true;
        for(int i:adjList.get(index))
            dfs(visited,i,adjList);
    }
    public static void bfs(boolean[] visited,int index,List<List<Integer>> adjList)
    {
        Queue<Integer> q=new LinkedList<>();
        q.offer(index);
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int k=0;k<size;k++)
            {
                int curr=q.poll();
                visited[curr]=true;
                for(int i:adjList.get(curr))
                {
                    if(!visited[i])
                        q.offer(i);
                }
            }
        }
    }
    public static void main(String[] args)
    {
        int[][] edges=new int[][]{{0,1},{1,2},{3,4}};
        System.out.println(countComponents(5,edges));
    }
}
