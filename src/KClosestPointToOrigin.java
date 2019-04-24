import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointToOrigin
{
    /*
    We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

    (Here, the distance between two points on a plane is the Euclidean distance.)

    You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



    Example 1:

    Input: points = [[1,3],[-2,2]], K = 1
    Output: [[-2,2]]
    Explanation:
    The distance between (1, 3) and the origin is sqrt(10).
    The distance between (-2, 2) and the origin is sqrt(8).
    Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
    We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
    Example 2:

    Input: points = [[3,3],[5,-1],[-2,4]], K = 2
    Output: [[3,3],[-2,4]]
    (The answer [[-2,4],[3,3]] would also be accepted.)


    Note:

    1 <= K <= points.length <= 10000
    -10000 < points[i][0] < 10000
    -10000 < points[i][1] < 10000
     */
    public static int[][] kClosest(int[][] points, int K)
    {
        Arrays.sort(points,(p1,p2)->p1[0]*p1[0]+p1[1]*p1[1]-p2[0]*p2[0]-p2[1]*p2[1]);
        return Arrays.copyOfRange(points,0,K);
    }
    public static int[][] kClosest1(int[][] points,int K)
    {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                int val1=o1[0]*o1[0]+o1[1]*o1[1];
                int val2=o2[0]*o2[0]+o2[1]*o2[1];
                if(val1>val2)
                    return 1;
                else if(val1<val2)
                    return -1;
                else
                    return 0;
            }
        });
        return Arrays.copyOfRange(points,0,K);
    }
    public static int[][] kClosest2(int[][] points,int K)
    {
        PriorityQueue<int[]> heap=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                int val1=o1[0]*o1[0]+o1[1]*o1[1];
                int val2=o2[0]*o2[0]+o2[1]*o2[1];
                if(val1>val2)
                    return -1;
                else if(val1<val2)
                    return 1;
                else return 0;
            }
        });
        for(int[] i:points)
        {
            heap.offer(i);
            if(heap.size()>K)
                heap.poll();
        }
        int res[][]=new int[K][2];
        for(int i=0;i<K;i++)
        {
            res[i]=heap.poll();
        }
        return res;
    }
    public static void main(String[] args)
    {
        int[][] points=new int[][]{{3,3},{5,-1},{-2,4}};
        int[][] res=kClosest2(points,2);
        System.out.println(res);
    }
}
