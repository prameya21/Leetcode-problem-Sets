import java.util.*;

public class MaxPointsOnLine
{
    /*
    Input: [[1,1],[2,2],[3,3]]
    Output: 3
    Explanation:
    ^
    |
    |        o
    |     o
    |  o
    +------------->
    0  1  2  3  4
    Example 2:

    Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    Output: 4
    Explanation:
    ^
    |
    |  o
    |     o        o
    |        o
    |  o        o
    +------------------->
    0  1  2  3  4  5  6
    NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
     */
    public int maxPoints(int[][] points)
    {
        if(points.length==0)
            return 0;
        if(points.length<=2)
            return points.length;
        Map<String,Integer> slopeMap;
        int maxPoints=0;
        int overlap,curr_max,vertical;
        for(int i=0;i<points.length;i++)
        {
            curr_max=overlap=0;
            vertical=1;
            slopeMap=new HashMap<>();
            for(int j=i+1;j<points.length;j++)
            {
                if(points[i][0]==points[j][0] && points[i][1]==points[j][1])
                    overlap++;
                else if(points[i][0]==points[j][0])
                    vertical++;
                else
                {
                    int y=points[i][1]-points[j][1];
                    int x=points[i][0]-points[j][0];
                    int g=gcd(x,y);
                    int g_x=x/g;
                    int g_y=y/g;
                    String s=g_x+","+g_y;
                    slopeMap.put(s,slopeMap.getOrDefault(s,1)+1);
                    curr_max=Math.max(curr_max,slopeMap.get(s));
                }
            }
            curr_max=Math.max(curr_max,vertical);
            maxPoints=Math.max(maxPoints,curr_max+overlap);
        }
        return maxPoints;
    }
    public int gcd(int x,int y)
    {
        if(x==0)
            return y;
        return gcd(y%x,x);
    }
    public static void main(String[] args)
    {
        System.out.println(new MaxPointsOnLine().maxPoints(new int[][]{{2,3},{3,3},{-5,3}}));
    }
}
