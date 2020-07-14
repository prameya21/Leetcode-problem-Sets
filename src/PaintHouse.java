import java.util.HashMap;
import java.util.Map;

public class PaintHouse
{
    /*
    There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

    The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
    Find the minimum cost to paint all houses.

    Note:
    All costs are positive integers.

    Example:

    Input: [[17,2,17],[16,16,5],[14,3,19]]
    Output: 10
    Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
                 Minimum cost: 2 + 5 + 3 = 10.
     */

    public int minCost(int[][] costs)
    {
        if(costs==null || costs.length==0)
            return 0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<costs[0].length;i++)
        {
            min=Math.min(min,helper1(costs,costs[0][i],1,i,new HashMap<>()));
            //min=Math.min(min,helper(costs,0,i,new HashMap<>()));
        }
        return min;
    }

    public int helper1(int[][] costs, int total, int r, int ex, Map<String,Integer> map)
    {
        if(r==costs.length)
            return total;
        String key=r+":"+ex;
        if(map.containsKey(key))
            return map.get(key);
        int min=Integer.MAX_VALUE;
        for(int col=0;col<costs[0].length;col++)
        {
            if(col==ex)
                continue;
            min=Math.min(min,helper1(costs,total+costs[r][col],r+1,col,map));
        }
        map.put(key,min);
        return min;
    }

    public int helper(int[][] costs, int r, int c, Map<String,Integer> memo)
    {
        if(r==costs.length)
            return 0;
        String key=String.valueOf(r)+":"+String.valueOf(c);
        int cost=costs[r][c];
        if(memo.containsKey(key))
            return memo.get(key);
        int min=Integer.MAX_VALUE;
        for(int col=0;col<costs[0].length;col++)
        {
            if(col==c)
                continue;
            min=Math.min(min,helper(costs,r+1,col,memo));
        }
        cost+=min;
        memo.put(key,cost);
        return cost;
    }

    public static void main(String[] args)
    {
        PaintHouse obj=new PaintHouse();
        System.out.println(obj.minCost(new int[][]{{3,5,3},{6,17,6},{7,13,18},{9,10,18}}));
        System.out.println(obj.minCost(new int[][]{{17,2,17},{16,16,5},{14,3,19}}));
    }
}
