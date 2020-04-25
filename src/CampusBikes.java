import java.util.*;

public class CampusBikes
{
    /*
    On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

    Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker.
    (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index;
    if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

    The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

    Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.

    Example 1:

    Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
    Output: [1,0]
    Explanation:
    Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
    Example 2:

    Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
    Output: [0,2,1]
    Explanation:
    Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].


    Note:

    0 <= workers[i][j], bikes[i][j] < 1000
    All worker and bike locations are distinct.
    1 <= workers.length <= bikes.length <= 1000
     */
    public int[] assignBikes(int[][] workers, int[][] bikes)
    {
        if(workers.length==0 || bikes.length==0)
            return new int[0];

        int[] res=new int[workers.length];
        boolean[] usedW=new boolean[workers.length];
        boolean[] usedB=new boolean[bikes.length];

        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b)
            {
                int cmp1=Integer.compare(a[0],b[0]);
                if(cmp1==0)
                {
                    int cmp2=Integer.compare(a[1],b[1]);
                    if(cmp2==0)
                        return Integer.compare(a[2],b[2]);
                    return cmp2;
                }
                return cmp1;
            }
        });

        for(int i=0;i<workers.length;i++)
        {
            for(int j=0;j<bikes.length;j++)
            {
                int d=Math.abs(workers[i][0]-bikes[j][0])+Math.abs(workers[i][1]-bikes[j][1]);
                pq.offer(new int[]{d,i,j});
            }
        }
        while(!pq.isEmpty())
        {
            int[] t=pq.poll();
            if(usedW[t[1]] || usedB[t[2]])
                continue;
            res[t[1]]=t[2];
            usedW[t[1]]=true;
            usedB[t[2]]=true;
        }
        return res;
    }

    public int[] assignBikes2(int[][] workers, int[][] bikes)
    {
        if(workers.length==0 || bikes.length==0)
            return new int[0];
        int[] ret=new int[workers.length];
        List<int[]>[] dist=new List[2000];

        for(int i=0;i<workers.length;i++)
        {
            for(int j=0;j<bikes.length;j++)
            {
                int d=Math.abs(workers[i][0]-bikes[j][0])+Math.abs(workers[i][1]-bikes[j][1]);
                if(dist[d]==null)
                    dist[d]=new ArrayList<int[]>();
                dist[d].add(new int[]{i,j});
            }
        }
        boolean[] usedW=new boolean[workers.length];
        boolean[] usedB=new boolean[bikes.length];

        for(int i=0,ctr=0;i<dist.length && ctr<workers.length;i++)
        {
            if(dist[i]==null)
                continue;
            for(int[] t:dist[i])
            {
                if(usedW[t[0]] || usedB[t[1]])
                    continue;
                ret[t[0]]=t[1];
                ctr++;
                usedW[t[0]]=true;
                usedB[t[1]]=true;
            }
        }
        return ret;
    }

    public static void main(String[] args)
    {
        CampusBikes obj=new CampusBikes();
        //int[] ret=obj.assignBikes2(new int[][]{{0,0},{1,1},{2,0}},new int[][]{{1,0},{2,2},{2,1}});
        int[] ret=obj.assignBikes(new int[][]{{0,0},{2,1}},new int[][]{{1,2},{3,3}});
        for(int i:ret)
            System.out.println(i);
    }
}
