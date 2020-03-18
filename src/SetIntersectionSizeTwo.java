import java.util.Arrays;
import java.util.Comparator;

public class SetIntersectionSizeTwo
{
    /*
    An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.

    Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has size at least 2.

    Example 1:
    Input: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
    Output: 3
    Explanation:
    Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
    Also, there isn't a smaller size set that fulfills the above condition.
    Thus, we output the size of this set, which is 3.
    Example 2:
    Input: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
    Output: 5
    Explanation:
    An example of a minimum sized set is {1, 2, 3, 4, 5}.
    Note:

    intervals will have length in range [1, 3000].
    intervals[i] will have length 2, representing some integer interval.
    intervals[i][j] will be an integer in [0, 10^8].
     */

    public int intersectionSizeTwo(int[][] intervals)
    {
        if(intervals==null || intervals.length==0)
            return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b)
            {
                int cmp=Integer.compare(a[1],b[1]);
                if(cmp==0)
                    return Integer.compare(b[0],a[0]);
                return cmp;
            }
        });

        System.out.println(Arrays.deepToString(intervals));

        int right=intervals[0][1];
        int left=right-1;
        int res=2;
        for(int i=1;i<intervals.length;i++)
        {
            int[] curr=intervals[i];
            if(left<curr[0] && right>=curr[0])
            {
                res++;
                left=right;
                right=curr[1];
            }
            else if(right<curr[0])
            {
                res+=2;
                right=curr[1];
                left=right-1;
            }
        }
        return res;
    }


    public static void main(String[] args)
    {
        SetIntersectionSizeTwo obj=new SetIntersectionSizeTwo();
        System.out.println(obj.intersectionSizeTwo(new int[][]{{1,3},{1,4},{2,5},{3,5}}));
    }
}
