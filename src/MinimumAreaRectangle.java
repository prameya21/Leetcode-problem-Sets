import java.util.*;

public class MinimumAreaRectangle
{
    /*
    Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

    If there isn't any rectangle, return 0.



    Example 1:

    Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
    Output: 4
    Example 2:

    Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
    Output: 2
     */
    public int minAreaRect(int[][] points)
    {
        if(points==null || points.length==0)
            return 0;
        Map<Integer, Set<Integer>> map=new HashMap<>();
        for(int[] i:points)
        {
            map.putIfAbsent(i[0],new HashSet<>());
            map.get(i[0]).add(i[1]);
        }
        int res=Integer.MAX_VALUE;
        for(int[] p1:points)
        {
            for(int[] p2:points)
            {
                if(p1[0]==p2[0] || p1[1]==p2[1])
                    continue;
                if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1]))
                    res=Math.min(res,Math.abs(p1[0]-p2[0])*Math.abs(p1[1]-p2[1]));
            }
        }
        return res==Integer.MAX_VALUE?0:res;
    }

    public static void main(String[] args)
    {
        MinimumAreaRectangle obj=new MinimumAreaRectangle();
        System.out.println(obj.minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{2,2}}));
    }
}
