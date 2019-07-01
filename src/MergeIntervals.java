import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeIntervals
{
    /*
    Given a collection of intervals, merge all overlapping intervals.

    Given a collection of intervals, merge all overlapping intervals.

    Example 1:

    Input: [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
    Example 2:

    Input: [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     */
    public List<int[]> merge(int[][] intervals)
    {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });
        LinkedList<int[]> result=new LinkedList<>();
        for(int[] i:intervals)
        {
            if(result.isEmpty() || result.getLast()[1]<i[0])
                result.add(i);
            else
                result.getLast()[1]=Math.max(result.getLast()[1],i[1]);

        }
        return result;
    }
    public int[][] merge1(int[][] intervals)
    {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        LinkedList<int[]> result=new LinkedList<>();
        for(int[] i:intervals)
        {
            if(result.isEmpty() || result.getLast()[1]<i[0])
                result.add(i);
            else
                result.getLast()[1]=Math.max(result.getLast()[1],i[1]);
        }
        int [][] res=new int[result.size()][2];
        return result.toArray(res);
    }
    public static void main(String[] args)
    {
        List<int[]> res1=new MergeIntervals().merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        int[][] res=new MergeIntervals().merge1(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        for(int[] i:res)
            System.out.println(i[0]+"  "+i[1]);
    }
}
