import java.util.*;


public class NonOverLappingInterval
{
    /*
    Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



    Example 1:

    Input: [[1,2],[2,3],[3,4],[1,3]]
    Output: 1
    Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
    Example 2:

    Input: [[1,2],[1,2],[1,2]]
    Output: 2
    Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
    Example 3:

    Input: [[1,2],[2,3]]
    Output: 0
    Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


    Note:

    You may assume the interval's end point is always bigger than its start point.
    Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
     */

    public int eraseOverlapIntervals(int[][] intervals)
    {
        if(intervals==null || intervals.length==0)
            return 0;
        LinkedList<int[]> temp=new LinkedList<>();
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] i, int[] j)
            {
                return i[1]-j[1];
            }
        });
        System.out.println(Arrays.deepToString(intervals));

        int ctr=0;
        for(int[] i:intervals)
        {
            if(temp.isEmpty() || temp.getLast()[1]<=i[0])
                temp.add(i);
            else
                ctr++;
        }

        return ctr;
    }

    public static void main(String[] args)
    {
        NonOverLappingInterval obj=new NonOverLappingInterval();
        System.out.println(obj.eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
    }
}
