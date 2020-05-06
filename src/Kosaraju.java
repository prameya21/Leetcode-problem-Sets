import java.util.*;

public class Kosaraju
{
    /*
        Implementation of Kosaraju's algorithm to find strongly connected components.


        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        graph.addEdge(4, 5);
        graph.addEdge(7, 6);
     */

    public List<Set<Integer>> stronglyConnectedComponents(int[][] graph)
    {
        Map<Integer,List<Integer>> adjList=new HashMap<>();
        for(int[] i:graph)
        {
            adjList.putIfAbsent(i[0],new ArrayList<>());
            adjList.get(i[0]).add(i[1]);
        }
        Set<Integer> visited=new HashSet<>();
        Deque<Integer> st=new ArrayDeque<>();
        for(int i:adjList.keySet())
        {
            if(visited.contains(i))
                continue;
            dfsUtil(adjList,visited,st,i);
        }
        System.out.println(st);
        Map<Integer,List<Integer>> rev=reverse(adjList);

        visited.clear();
        List<Set<Integer>> res=new ArrayList<>();
        while(!st.isEmpty())
        {
            int i=st.poll();
            if(visited.contains(i))
                continue;
            Set<Integer> set=new HashSet<>();
            dfsUtilReverse(i,rev,visited,set);
            res.add(set);
        }
        return res;
    }



    public void dfsUtilReverse(int i, Map<Integer,List<Integer>> map, Set<Integer> visited, Set<Integer> set)
    {
        if(visited.contains(i))
            return;
        visited.add(i);
        set.add(i);
        if(map.containsKey(i))
        {
            for(int j:map.get(i))
            {
                dfsUtilReverse(j,map,visited,set);
            }
        }
    }
    public Map<Integer,List<Integer>> reverse(Map<Integer,List<Integer>> adjList)
    {
        Map<Integer,List<Integer>> map=new HashMap<>();
        for(int i:adjList.keySet())
        {
            for(int j:adjList.get(i))
            {
                map.putIfAbsent(j,new ArrayList<>());
                map.get(j).add(i);
            }
        }
        return map;
    }

    public void dfsUtil(Map<Integer,List<Integer>> adjList, Set<Integer> visited, Deque<Integer> st, int i)
    {
        if(visited.contains(i) || !adjList.containsKey(i))
            return;
        visited.add(i);
        List<Integer> temp=adjList.get(i);
        for(int j:temp)
            dfsUtil(adjList,visited,st,j);
        st.offerFirst(i);
    }


    public static void main(String[] args)
    {
        Kosaraju obj=new Kosaraju();
        int[][] graph={{1,2},{2,3},{3,1},{3,5},{5,6},{6,4},{4,5},{7,6}};
        System.out.println(obj.stronglyConnectedComponents(graph));
    }
}
