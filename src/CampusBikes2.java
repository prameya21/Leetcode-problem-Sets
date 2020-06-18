import java.util.HashMap;
import java.util.Map;

public class CampusBikes2
{
    /*
    On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

    We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

    The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

    Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.



    Example 1:



    Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
    Output: 6
    Explanation:
    We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
    Example 2:



    Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
    Output: 4
    Explanation:
    We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.


    Note:

    0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
    All worker and bike locations are distinct.
    1 <= workers.length <= bikes.length <= 10
     */

    public int assignBikes(int[][] workers, int[][] bikes)
    {
        if(workers==null || bikes==null)
            return 0;
        boolean[] usedB=new boolean[bikes.length];
        //return dfs(workers,0,bikes,usedB);
        return dfs2(workers,0,bikes,usedB,new HashMap<Integer,Integer>(),0);
    }

    public int dfs2(int[][] workers, int idx, int[][] bikes, boolean[] visited, Map<Integer,Integer> memo, int key)
    {
        if(idx==workers.length)
            return 0;
        if(memo.containsKey(key))
            return memo.get(key);
        int min=Integer.MAX_VALUE;
        for(int i=0;i<bikes.length;i++)
        {
            if(visited[i])
                continue;
            visited[i]=true;
            int d=dist(workers[idx],bikes[i]);
            d+=dfs2(workers,idx+1,bikes,visited,memo,key^(1<<i));
            min=Math.min(d,min);

            memo.put(key,Math.min(memo.getOrDefault(key,Integer.MAX_VALUE),min));
            visited[i]=false;
        }
        memo.put(key,min);
        return min;
    }

    public int dfs(int[][] workers, int idx, int[][] bikes, boolean[] visited)
    {
        if(idx==workers.length)
            return 0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<bikes.length;i++)
        {
            if(visited[i])
                continue;
            visited[i]=true;
            int d=dist(workers[idx],bikes[i]);
            d+=dfs(workers,idx+1,bikes,visited);
            min=Math.min(d,min);
            visited[i]=false;
        }
        return min;
    }

    public int dist(int[] w, int[] b)
    {
        return Math.abs(w[0]-b[0])+Math.abs(w[1]-b[1]);
    }

    public static void main(String[] args)
    {
        CampusBikes2 obj=new CampusBikes2();
        System.out.println(obj.assignBikes(new int[][]{{0,0},{1,1},{2,0}}, new int[][]{{1,0},{2,2},{2,1}}));
    }
}
