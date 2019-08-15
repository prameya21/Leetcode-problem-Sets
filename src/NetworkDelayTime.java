import java.util.*;

public class NetworkDelayTime
{
    /*
    Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

    Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

    Example 1:
    Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
    Output: 2
    Note:

    N will be in the range [1, 100].
    K will be in the range [1, N].
    The length of times will be in the range [1, 6000].
    All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
     */
    public int networkDelayTime(int[][] times, int N, int K)
    {
        Map<Integer,List<int[]>> graph=new HashMap<>();
        for(int[] i:times)
        {
            graph.putIfAbsent(i[0],new ArrayList<>());
            graph.get(i[0]).add(new int[]{i[1],i[2]});
        }
        int[] dist=new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[K]=0;
        Queue<Integer> q=new LinkedList<>();
        q.offer(K);
        while(!q.isEmpty())
        {
            int curr=q.poll();
            int t=dist[curr];
            if(!graph.containsKey(curr))
                continue;
            for(int[] i:graph.get(curr))
            {
                if(dist[i[0]]>t+i[1])
                {
                    dist[i[0]]=t+i[1];
                    q.offer(i[0]);
                }
            }
        }
        int res=0;
        for(int i=1;i<dist.length;i++)
        {
            if(dist[i]==Integer.MAX_VALUE)
                return -1;
            res=Math.max(res,dist[i]);
        }
        return res;

    }
    public int networkDelayTime2(int[][] times, int N, int K) {
        int r = times.length, max = Integer.MAX_VALUE;

        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<r;i++){
            int[] nums = times[i];
            int u = nums[0];
            int v = nums[1];
            List<Integer> list = map.getOrDefault(u,new ArrayList<>());

            list.add(i);

            map.put(u,list);
        }
        if(map.get(K) == null){
            return -1;// no immediate neighbor of node K, so return -1
        }
        int[] dist = new int[N+1];//dist[i] is the time taken to reach node i from node k
        Arrays.fill(dist,max);

        dist[K] = 0;
        Queue<Integer> queue = new LinkedList<>();

        queue.add(K);

        while(!queue.isEmpty()){
            int u = queue.poll();
            int t = dist[u];
            List<Integer> list = map.get(u);// get the indices of all the neighbors of node u
            if(list == null)
                continue;

            for(int n:list){
                int v = times[n][1];
                int time = times[n][2];// time taken to reach from u to v
                if(dist[v] > t + time){// if time taken to reach v from k is greater than time taken to reach from k to u + time taken to reach from u to v, then update dist[v]
                    dist[v] = t + time;
                    queue.add(v);// as we have found shorter distance to node v, explore all neighbors of v
                }
            }
        }

        int res = -1;

        for(int i=1;i<=N;i++){
            int d = dist[i];
            if(d == max){// if d is max, it means node i can not be reached from K, so return -1
                return -1;
            }
            res = d > res ? d : res;
        }

        return res;
    }
    public static void main(String[] args)
    {
        NetworkDelayTime obj=new NetworkDelayTime();
        System.out.println(obj.networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}},4,2));
    }
}
