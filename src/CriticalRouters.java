import java.util.*;

public class CriticalRouters
{
    /*
    You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which, when removed along with associated edges,
    makes the graph disconnected (or more precisely, increases the number of connected components in the graph). The task is to find all articulation points in the given graph.

    Input:
    The input to the function/method consists of three arguments:

    numNodes, an integer representing the number of nodes in the graph.
    numEdges, an integer representing the number of edges in the graph.
    edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
    Output:
    Return a list of integers representing the critical nodes.
     */

    public List<Integer> getCriticalRouters(int numNodes, int numEdges, int[][] edges)
    {
        Map<Integer, Set<Integer>> graph=new HashMap<>();
        for(int[] i:edges)
        {
            graph.putIfAbsent(i[0],new HashSet<>());
            graph.get(i[0]).add(i[1]);
            graph.putIfAbsent(i[1],new HashSet<>());
            graph.get(i[1]).add(i[0]);
        }

        List<Integer> result=new ArrayList<>();
        for(int i=0;i<numNodes;i++)
        {
            Set<Integer> removedNodes=graph.get(i);
            graph.remove(i);
            Set<Integer> visited=new HashSet<>();
            dfs(graph,visited,i==0?1:0);
            if(visited.size()!=numNodes-1)
                result.add(i);
            graph.put(i,removedNodes);
        }
        return result;
    }

    public void dfs(Map<Integer,Set<Integer>> graph,Set<Integer> visited, int src)
    {
        if(visited.contains(src))
            return;
        visited.add(src);
        for(int node:graph.get(src))
            if(graph.containsKey(node))
                dfs(graph,visited,node);
    }



    public static void main(String[] args)
    {
        CriticalRouters obj=new CriticalRouters();
        System.out.println(obj.getCriticalRouters(7,7,new int[][]{{0,1},{0,2},{1,3},{2,3},{2,5},{5,6},{3,4}}));
        System.out.println(obj.getCriticalRouters(4,4,new int[][]{{0,1},{0,2},{1,3},{2,1}}));
    }
}
