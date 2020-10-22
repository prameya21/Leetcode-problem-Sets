import java.util.*;

public class RemoveCoveredInterval
{
    /*
    Given a list of intervals, remove all intervals that are covered by another interval in the list.

    Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

    After doing so, return the number of remaining intervals.



    Example 1:

    Input: intervals = [[1,4],[3,6],[2,8]]
    Output: 2
    Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
    Example 2:

    Input: intervals = [[1,4],[2,3]]
    Output: 1
    Example 3:

    Input: intervals = [[0,10],[5,12]]
    Output: 2
    Example 4:

    Input: intervals = [[3,10],[4,10],[5,11]]
    Output: 2
    Example 5:

    Input: intervals = [[1,2],[1,4],[3,4]]
    Output: 1
     */

    public int removeCoveredIntervals(int[][] intervals)
    {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i, int[] j)
            {
                return i[0]==j[0]?j[1]-i[1]:i[0]-j[0];
            }
        });
        int r=0,res=0;
        for(int[] i:intervals)
        {
            if(r<i[1])
            {
                r=i[1];
                res++;
            }
        }
        return res;
    }
}
