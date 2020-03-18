import java.util.*;

public class ConnectingCitiesWithMinimumCost
{
    /*
    There are N cities numbered from 1 to N.

    You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
    (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

    Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.
    The cost is the sum of the connection costs used. If the task is impossible, return -1.



    Example 1:



    Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
    Output: 6
    Explanation:
    Choosing any 2 edges will connect all cities so we choose the minimum 2.
    Example 2:



    Input: N = 4, connections = [[1,2,3],[3,4,4]]
    Output: -1
    Explanation:
    There is no way to connect all cities even if all edges are used.


    Note:

    1 <= N <= 10000
    1 <= connections.length <= 10000
    1 <= connections[i][0], connections[i][1] <= N
    0 <= connections[i][2] <= 10^5
    connections[i][0] != connections[i][1]
     */

    public int minimumCost(int N, int[][] connections)
    {
        if(N==0 || N==1)
            return 1;
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] i:connections)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.putIfAbsent(i[1],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
            map.get(i[1]).add(new int[]{i[0],i[2]});
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b)
            {
                return Integer.compare(a[1],b[1]);
            }
        });
        pq.offer(new int[]{1,0});
        Set<Integer> visited=new HashSet<>();
        int totalCost=0;
        while(!pq.isEmpty())
        {
            int[] curr=pq.poll();
            if(visited.contains(curr[0]))
                continue;
            visited.add(curr[0]);

            totalCost+=curr[1];
            if(map.containsKey(curr[0]))
            {
                for(int[] conn:map.get(curr[0]))
                {
                    if(visited.contains(conn[0]))
                        continue;
                    pq.offer(conn);

                }
            }
        }
        return visited.size()==N?totalCost:-1;
    }

    public static void main(String[] args)
    {
        ConnectingCitiesWithMinimumCost obj=new ConnectingCitiesWithMinimumCost();
        System.out.println(obj.minimumCost(3,new int[][]{{1,2,5},{1,3,6},{2,3,1}}));

        System.out.println(obj.minimumCost(4,new int[][]{{1,2,3},{3,4,4}}));
    }
}
