import java.util.*;

public class ArticulationPointTrajan
{
    /*
        Get the articulation point/strongly connected vertices for a given graph.


        You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which,
        when removed along with associated edges, makes the graph disconnected (or more precisely,
        increases the number of connected components in the graph).

        The task is to find all articulation points in the given graph.

        Input:
        The input to the function/method consists of three arguments:

        numNodes, an integer representing the number of nodes in the graph.
        numEdges, an integer representing the number of edges in the graph.
        edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
        Output:
        Return a list of integers representing the critical nodes.
     */

    int time=0;
    public List<Character> getArticulationPoints(List<String> adjList)
    {
        Map<Character,List<Character>> graph=createGraph(adjList);
        List<Character> result=new ArrayList<>();
        Map<Character,Integer> timeMap=new HashMap<>();
        trajan('D','#',timeMap,result,graph);
        return result;
    }

    public int trajan(char src, char p, Map<Character,Integer> timerMap, List<Character> result, Map<Character,List<Character>> graph)
    {
        if(timerMap.containsKey(src))
            return timerMap.get(src);
        timerMap.put(src,time++);
        if(!graph.containsKey(src))
            return timerMap.get(src);
        int min=Integer.MAX_VALUE;
        for(char nxt:graph.get(src))
        {
            if(nxt==p)
                continue;
            min=Math.min(min,trajan(nxt,src,timerMap,result,graph));
        }


        int max=Integer.MIN_VALUE;
        for(char c:graph.get(src))
        {
            max=Math.max(max,timerMap.get(c));
        }
        if(timerMap.get(src)<=max)
            result.add(src);

        timerMap.put(src,Math.min(min,timerMap.get(src)));
        return Math.min(min,timerMap.get(src));
    }

    public Map<Character,List<Character>> createGraph(List<String> adjList)
    {
        Map<Character,List<Character>> map=new HashMap<>();
        for(String str:adjList)
        {
            char u=str.charAt(0);
            char v=str.charAt(2);
            map.putIfAbsent(u,new ArrayList<Character>());
            map.putIfAbsent(v,new ArrayList<Character>());
            map.get(u).add(v);
            map.get(v).add(u);
        }
        return map;
    }

    public static void main(String[] args)
    {
        List<String> graph=new ArrayList<>(Arrays.asList("A,B","A,C","B,C","C,D","D,E","E,G","E,F","G,F","F,H"));
        List<String> graph2=new ArrayList<>(Arrays.asList("A,B","A,C","B,D","C,D","D,E","C,F","F,G"));
        ArticulationPointTrajan obj=new ArticulationPointTrajan();
        System.out.println(obj.getArticulationPoints(graph2));
    }
}
