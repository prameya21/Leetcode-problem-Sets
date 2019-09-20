import java.util.HashSet;
import java.util.Set;

public class MinimumCostForTickets
{
    /*
    In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

    Train tickets are sold in 3 different ways:

    a 1-day pass is sold for costs[0] dollars;
    a 7-day pass is sold for costs[1] dollars;
    a 30-day pass is sold for costs[2] dollars.
    The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

    Return the minimum number of dollars you need to travel every day in the given list of days.



    Example 1:

    Input: days = [1,4,6,7,8,20], costs = [2,7,15]
    Output: 11
    Explanation:
    For example, here is one way to buy passes that lets you travel your travel plan:
    On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
    On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
    On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
    In total you spent $11 and covered all the days of your travel.
    Example 2:

    Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
    Output: 17
    Explanation:
    For example, here is one way to buy passes that lets you travel your travel plan:
    On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
    On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
    In total you spent $17 and covered all the days of your travel.


    Note:

    1 <= days.length <= 365
    1 <= days[i] <= 365
    days is in strictly increasing order.
    costs.length == 3
    1 <= costs[i] <= 1000

     */
    public int mincostTickets(int[] days, int[] costs)
    {
        int[] minCost=new int[366];
        minCost[0]=0;
        Set<Integer> travelDays=new HashSet<>();
        for(int d:days)
            travelDays.add(d);
        for(int i=1;i<=365;i++)
        {
            if(travelDays.contains(i))
            {
                minCost[i]=Math.min(minCost[i-1]+costs[0],minCost[Math.max(0,i-7)]+costs[1]);
                minCost[i]=Math.min(minCost[i],minCost[Math.max(0,-30)]+costs[2]);
            }
            else
                minCost[i]=minCost[i-1];
        }
        return minCost[366];
    }
    public int mincostTickets2(int[] days,int[] costs)
    {
        Integer[] memo=new Integer[days.length];
        return dp(0,days,costs,memo,new int[]{1,7,30});
    }
    public int dp(int i,int[] days,int[] costs,Integer[] memo,int[] duration)
    {
        if(i>=days.length)
            return 0;
        if(memo[i]!=null)
            return memo[i];
        int ans=Integer.MAX_VALUE;
        int j=i;
        for(int k=0;k<duration.length;k++)
        {
            while(j<days.length && days[j]<days[i]+duration[k])
                j++;
            ans=Math.min(ans,dp(j,days,costs,memo,duration)+costs[k]);
        }
        memo[i]=ans;
        return ans;
    }
    public static void main(String[] args)
    {
        MinimumCostForTickets obj=new MinimumCostForTickets();
        System.out.println(obj.mincostTickets2(new int[]{1,4,6,7,8,20},new int[]{2,7,15}));
    }
}
