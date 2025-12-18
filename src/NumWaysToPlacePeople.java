import java.util.Arrays;
import java.util.Comparator;

public class NumWaysToPlacePeople
{
    //3025
    /*
        You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].
        Count the number of pairs of points (A, B), where
        A is on the upper left side of B, and
        there are no other points in the rectangle (or line) they make (including the border).
        Return the count.

        Example 1:
        Input: points = [[1,1],[2,2],[3,3]]
        Output: 0
        Explanation: There is no way to choose A and B such that A is on the upper left side of B.

        Example 2:
        Input: points = [[6,2],[4,4],[2,6]]
        Output: 2
        Explanation: The left one is the pair (points[1], points[0]), where points[1] is on the upper left side of points[0] and the rectangle is empty.
        The middle one is the pair (points[2], points[1]), same as the left one it is a valid pair.
        The right one is the pair (points[2], points[0]), where points[2] is on the upper left side of points[0], but points[1] is inside the rectangle so it's not a valid pair.

        Example 3:
        Input: points = [[3,1],[1,3],[1,1]]
        Output: 2
        Explanation: The left one is the pair (points[2], points[0]), where points[2] is on the upper left side of points[0] and there are no other points on the line they form. Note that it is a valid state when the two points form a line.
        The middle one is the pair (points[1], points[2]), it is a valid pair same as the left one.
        The right one is the pair (points[1], points[0]), it is not a valid pair as points[2] is on the border of the rectangle.

        Constraints:
        2 <= n <= 50
        points[i].length == 2
        0 <= points[i][0], points[i][1] <= 50
        All points[i] are distinct.
     */

    public int numberOfPairs(int[][] points)
    {
        if(points==null || points.length==0)
            return 0;
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] i, int[] j)
            {
                int res=i[0]-j[0];
                return res==0?j[1]-i[1]:res;
            }
        });
        int res=0;
        for(int i=0;i<points.length;i++)
        {
            int upperY=points[i][1];
            int lowerLimitY=Integer.MIN_VALUE;
            for(int j=i+1;j<points.length;j++)
            {
                int currY=points[j][1];
                if(currY<=upperY && currY>lowerLimitY)
                {
                    res++;
                    lowerLimitY=currY;
                    if(currY==upperY)
                        break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        NumWaysToPlacePeople obj=new NumWaysToPlacePeople();
        System.out.println(obj.numberOfPairs(new int[][]{{1,1},{2,2},{3,3}}));
        System.out.println(obj.numberOfPairs(new int[][]{{6,2},{4,4},{2,6}}));
    }
}
