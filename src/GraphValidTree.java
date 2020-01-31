import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphValidTree
{
    /*
    Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

    Example 1:

    Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
    Output: true
    Example 2:

    Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
    Output: false
    Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
     */

    public boolean validTree(int n, int[][] edges)
    {
        List<Integer>[] adjList=new ArrayList[n];
        for(int i=0;i<n;i++)
            if(adjList[i]==null)
                adjList[i]=new ArrayList<>();


        for(int[] i:edges)
        {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
        }
        boolean[] visited=new boolean[n];

        if(!dfs(adjList,visited,0,-1))
            return false;

        for(int i=0;i<n;i++)
            if(!visited[i])
                return false;

        return true;
    }

    public boolean dfs(List<Integer>[] adjList,boolean[] visited,int root,int parent)
    {
        visited[root]=true;
        for(int i: adjList[root])
        {
            if(i==parent)
                continue;
            if(visited[i] || !dfs(adjList,visited,i,root))
                return false;
        }
        return true;
    }

    public boolean validTreeBFS(int n, int[][] edges)
    {
        List<Integer>[] adjList=new ArrayList[n];
        for(int i=0;i<n;i++)
            adjList[i]=new ArrayList<Integer>();

        for(int[] i:edges)
        {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
        }

        boolean[] visited=new boolean[n];

        if(!bfs(adjList,0,visited))
            return false;

        for(boolean b:visited)
            if(!b)
                return false;

        return true;
    }

    public boolean bfs(List<Integer>[] adjList, int node,  boolean[] visited)
    {
        Queue<Integer> q=new LinkedList<>();
        q.offer(node);
        visited[node]=true;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int curr=q.poll();
                for(int n:adjList[curr])
                {
                    if(visited[n])
                        return false;
                    q.offer(n);
                    visited[n]=true;
                    adjList[n].remove((Integer) curr);
                }
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        GraphValidTree obj=new GraphValidTree();
        System.out.println(obj.validTreeBFS(5,new int[][]{{0,1},{0,2},{0,3},{1,4}}));
        System.out.println(obj.validTreeBFS(5,new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}));
    }

}
