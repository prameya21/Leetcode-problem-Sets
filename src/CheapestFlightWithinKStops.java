import java.util.*;
public class CheapestFlightWithinKStops
{
    /*
    There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

    Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

    Example 1:
    Input:
    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    src = 0, dst = 2, k = 1
    Output: 200
    Explanation:
    The graph looks like this:

    The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
    Example 2:
    Input:
    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    src = 0, dst = 2, k = 0
    Output: 500
    Explanation:
    The graph looks like this:


    The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
    Note:

    The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
    The size of flights will be in range [0, n * (n - 1) / 2].
    The format of each flight will be (src, dst, price).
    The price of each flight will be in the range [1, 10000].
    k is in the range of [0, n - 1].
    There will not be any duplicated flights or self cycles.
     */
    int ans_dfs;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K)
    {
        ans_dfs=Integer.MAX_VALUE;
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] i:flights)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
        }
        dfs(map,src,dst,K+1,0);
        return ans_dfs==Integer.MAX_VALUE?-1:ans_dfs;
    }
    public void dfs(Map<Integer,List<int[]>> map, int src, int dst, int k, int cost)
    {
        if(k<0)
            return;
        if(src==dst)
        {
            ans_dfs=Math.min(ans_dfs,cost);
            return;
        }
        if(!map.containsKey(src))
            return;
        for(int[] i:map.get(src))
        {
            if(cost+i[1]>ans_dfs)
                continue;
            dfs(map,i[0],dst,k-1,cost+i[1]);
        }
    }
    public int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int K)
    {
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] i:flights)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
        }
        int step=0;
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{src,0});
        int ans=Integer.MAX_VALUE;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                if(curr[0]==dst)
                    ans=Math.min(ans,curr[1]);
                if(!map.containsKey(curr[0]))
                    continue;
                for(int[] f:map.get(curr[0]))
                {
                    if(curr[1]+f[1]>ans)
                        continue;
                    q.offer(new int[]{f[0],curr[1]+f[1]});
                }
            }
            if(step++>K)
                break;
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    public int findCheapestPriceBF(int n, int[][] flights, int src, int dst, int K)
    {
        int[] cost=new int[n];
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[src]=0;
        for(int i=0;i<=K;i++)
        {
            int[] temp= Arrays.copyOf(cost,n);
            for(int[] f: flights)
            {
                int curr=f[0],next=f[1],price=f[2];
                if(cost[curr]==Integer.MAX_VALUE)
                    continue;
                temp[next]=Math.min(temp[next],cost[curr]+price);
            }
            cost=temp;
        }
        return cost[dst]==Integer.MAX_VALUE?-1:cost[dst];
    }
    public int findCheapestPricedijkstra(int n, int[][] flights, int src, int dst, int K)
    {
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] f:flights)
        {
            map.putIfAbsent(f[0],new ArrayList<>());
            map.get(f[0]).add(new int[]{f[1],f[2]});
        }
        PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        q.offer(new int[]{0,src,K+1});
        while(!q.isEmpty())
        {
            int[] c=q.poll();
            int cost=c[0];
            int curr=c[1];
            int stop=c[2];
            if(curr==dst)
                return cost;
            if(stop>0)
            {
                for(int[] next:map.get(curr))
                {
                    q.add(new int[]{cost+next[1],next[0],stop-1});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        CheapestFlightWithinKStops obj=new CheapestFlightWithinKStops();
        //System.out.print(obj.findCheapestPrice(5,new int[][]{{4,1,1},{1,2,3},{0,3,2},{0,4,10},{3,1,1},{1,4,3}},2,1,1));
        System.out.print(obj.findCheapestPricedijkstra(3,new int[][]{{0,1,100},{1,2,100},{0,2,500}},0,2,1));
    }
}
