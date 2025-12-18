import java.util.*;

public class PowerGridMaintenance
{
    //3607
    /*
        You are given an integer c representing c power stations, each with a unique identifier id from 1 to c (1‑based indexing).
        These stations are interconnected via n bidirectional cables, represented by a 2D array connections, where each element connections[i] = [ui, vi] indicates a connection between station ui and station vi. Stations that are directly or indirectly connected form a power grid.
        Initially, all stations are online (operational).
        You are also given a 2D array queries, where each query is one of the following two types:
        [1, x]: A maintenance check is requested for station x. If station x is online, it resolves the check by itself. If station x is offline, the check is resolved by the operational station with the smallest id in the same power grid as x. If no operational station exists in that grid, return -1.
        [2, x]: Station x goes offline (i.e., it becomes non-operational).
        Return an array of integers representing the results of each query of type [1, x] in the order they appear.
        Note: The power grid preserves its structure; an offline (non‑operational) node remains part of its grid and taking it offline does not alter connectivity.

        Example 1:
        Input: c = 5, connections = [[1,2],[2,3],[3,4],[4,5]], queries = [[1,3],[2,1],[1,1],[2,2],[1,2]]
        Output: [3,2,3]
        Explanation:
        Initially, all stations {1, 2, 3, 4, 5} are online and form a single power grid.
        Query [1,3]: Station 3 is online, so the maintenance check is resolved by station 3.
        Query [2,1]: Station 1 goes offline. The remaining online stations are {2, 3, 4, 5}.
        Query [1,1]: Station 1 is offline, so the check is resolved by the operational station with the smallest id among {2, 3, 4, 5}, which is station 2.
        Query [2,2]: Station 2 goes offline. The remaining online stations are {3, 4, 5}.
        Query [1,2]: Station 2 is offline, so the check is resolved by the operational station with the smallest id among {3, 4, 5}, which is station 3.

        Example 2:
        Input: c = 3, connections = [], queries = [[1,1],[2,1],[1,1]]
        Output: [1,-1]
        Explanation:
        There are no connections, so each station is its own isolated grid.
        Query [1,1]: Station 1 is online in its isolated grid, so the maintenance check is resolved by station 1.
        Query [2,1]: Station 1 goes offline.
        Query [1,1]: Station 1 is offline and there are no other stations in its grid, so the result is -1.

        Constraints:

        1 <= c <= 105
        0 <= n == connections.length <= min(105, c * (c - 1) / 2)
        connections[i].length == 2
        1 <= ui, vi <= c
        ui != vi
        1 <= queries.length <= 2 * 105
        queries[i].length == 2
        queries[i][0] is either 1 or 2.
        1 <= queries[i][1] <= c
     */

    public int find(int[] root, int i)
    {
        if(root[i]==i)
            return i;
        root[i]=find(root,root[i]);
        return root[i];
    }

    public void union(int[] root, int i, int j)
    {
        int iRank=find(root,i);
        int jRank=find(root,j);
        root[jRank]=iRank;

    }


    public int[] processQueries(int c, int[][] connections, int[][] queries)
    {
        int[] roots=new int[c+1];
        for(int i=0;i<roots.length;i++)
            roots[i]=i;

        boolean[] offLine=new boolean[c+1];
        for(int[] i:connections)
            union(roots,i[0],i[1]);
        Map<Integer, PriorityQueue> map=new HashMap<>();
        for(int i=1;i<=c;i++)
        {
            int root=find(roots,i);
            map.putIfAbsent(root,new PriorityQueue());
            map.get(root).offer(i);
        }
        List<Integer> tmp=new ArrayList<>();
        for(int[] i:queries)
        {
            if(i[0]==2)
                offLine[i[1]]=true;
            else
            {
                if(!offLine[i[1]])
                    tmp.add(i[1]);
                else
                {
                    int root = find(roots, i[1]);
                    PriorityQueue<Integer> pq=map.get(root);
                    while(pq!=null && !pq.isEmpty() && offLine[pq.peek()])
                        pq.poll();
                    tmp.add(pq==null || pq.isEmpty()?-1:pq.peek());
                }
            }
        }
        int[] res=new int[tmp.size()];
        for(int i=0;i<res.length;i++)
            res[i]=tmp.get(i);
        return res;
    }


    public static void main(String[] args)
    {
        PowerGridMaintenance obj=new PowerGridMaintenance();
        System.out.println(Arrays.toString(obj.processQueries(5,new int[][]{{1,2},{2,3},{3,4},{4,5}}, new int[][]{{1,3},{2,1},{1,1},{2,2},{1,2}})));
    }
}
