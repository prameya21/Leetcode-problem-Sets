import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ValidSquare
{
    /*
        Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
        The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
        A valid square has four equal sides with positive length and four equal angles (90-degree angles).

        Example 1:
        Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
        Output: true

        Example 2:
        Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
        Output: false

        Example 3:
        Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
        Output: true


        Constraints:

        p1.length == p2.length == p3.length == p4.length == 2
        -104 <= xi, yi <= 104
     */

    public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4)
    {
        Set<Integer> set=new HashSet<>();
        set.add(distance(p1,p2));
        set.add(distance(p1,p3));
        set.add(distance(p1,p4));
        set.add(distance(p2,p3));
        set.add(distance(p2,p4));
        set.add(distance(p3,p4));
        return !set.contains(0) && set.size()==2;
    }
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4)
    {
        int[][] p={p4,p1,p2,p3};

        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] i, int[] j) {
                int cmp=i[0]-j[0];
                if(cmp==0)
                    return i[1]-j[1];
                return cmp;
            }
        });
        return dist(p[0],p[1])!=0 && dist(p[0],p[1])==dist(p[1],p[3]) && dist(p[1],p[3])==dist(p[3],p[2]) && dist(p[3],p[2])==dist(p[2],p[0]) && dist(p[0],p[3])==dist(p[1],p[2]);

    }

    public int distance(int[] p1, int[] p2)
    {
        // distance = (x1 - x2) ^ 2 + (y1 - y2) ^ 2;
        return ((p1[0] - p2[0]) * (p1[0] - p2[0])) + ((p1[1] - p2[1]) * (p1[1] - p2[1]));
    }
    public double dist(int[] a, int[] b)
    {
        return (a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1]);
    }
}
