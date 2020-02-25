import java.util.*;

public class BusRoutes
{
    /*
    We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

    We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

    Example:
    Input:
    routes = [[1, 2, 7], [3, 6, 7]]
    S = 1
    T = 6
    Output: 2
    Explanation:
    The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
    Note:

    1 <= routes.length <= 500.
    1 <= routes[i].length <= 500.
    0 <= routes[i][j] < 10 ^ 6.
     */

    public int numBusesToDestination(int[][] routes, int S, int T)
    {
        if(S==T)
            return 0;
        Map<Integer,List<Integer>> map=new HashMap<>();
        for(int i=0;i<routes.length;i++)
        {
            for(int j=0;j<routes[i].length;j++)
            {
                map.putIfAbsent(routes[i][j],new ArrayList<>());
                map.get(routes[i][j]).add(i);
            }
        }
        Queue<Integer> q=new LinkedList<>();
        q.offer(S);
        Set<Integer> visited=new HashSet<>();
        int res=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int curr=q.poll();
                for(int route:map.get(curr))
                {
                    if(visited.contains(route))
                        continue;
                    visited.add(route);
                    for(int bus:routes[route])
                    {
                        if(bus==T)
                            return res+1;
                        q.offer(bus);
                    }
                }
            }
            res++;
        }
        return -1;
    }



/*
[[7,12],[4,5,15],[6],[15,19],[9,12,13]]
15
12
 */



    public static void main(String[] args)
    {
        BusRoutes obj=new BusRoutes();
        System.out.println(obj.numBusesToDestination(new int[][]{{7,12},{4,5,15},{6},{15,19},{9,12,13}},15,12));
    }
}
