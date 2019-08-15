import java.util.*;

public class ReconstructItenary
{
    /*
    Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

    Note:

    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.
    Example 1:

    Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
    Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
    Example 2:

    Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                 But it is larger in lexical order.
     */
    public List<String> findItinerary(List<List<String>> tickets)
    {
        Map<String, PriorityQueue<String>> map=new HashMap<>();
        for(List<String> s:tickets)
        {
            String from=s.get(0);
            String to=s.get(1);
            map.putIfAbsent(from,new PriorityQueue<>());
            map.get(from).add(to);
        }
        Stack<String> st=new Stack<>();
        dfs(st,map,"JFK");
        Collections.reverse(st);
        return new ArrayList<>(st);
    }
    public void dfs(Stack<String> st, Map<String,PriorityQueue<String>> map, String s)
    {
        while(map.containsKey(s) && !map.get(s).isEmpty())
            dfs(st,map,map.get(s).poll());
        st.push(s);
    }
    public static void main(String[] args)
    {
        List<List<String>> tickets=new ArrayList<>();
        tickets.add(new ArrayList<>(Arrays.asList("MUC","LHR")));
        tickets.add(new ArrayList<>(Arrays.asList("JFK","MUC")));
        tickets.add(new ArrayList<>(Arrays.asList("SFO","SJC")));
        tickets.add(new ArrayList<>(Arrays.asList("LHR","SFO")));
        ReconstructItenary obj=new ReconstructItenary();
        System.out.println(obj.findItinerary(tickets));


    }
}
