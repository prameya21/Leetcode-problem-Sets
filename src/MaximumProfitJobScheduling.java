import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MaximumProfitJobScheduling
{
    /*
    We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

    You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.

    If you choose a job that ends at time X you will be able to start another job that starts at time X.



    Example 1:



    Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
    Output: 120
    Explanation: The subset chosen is the first and fourth job.
    Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
    Example 2:




    Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
    Output: 150
    Explanation: The subset chosen is the first, fourth and fifth job.
    Profit obtained 150 = 20 + 70 + 60.
    Example 3:



    Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
    Output: 6


    Constraints:

    1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
    1 <= startTime[i] < endTime[i] <= 10^9
    1 <= profit[i] <= 10^4
     */

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit)
    {
        if(startTime==null || startTime.length==0)
            return 0;
        int[][] arr=new int[startTime.length][3];
        for(int i=0;i<profit.length;i++)
        {
            arr[i][0]=startTime[i];
            arr[i][1]=endTime[i];
            arr[i][2]=profit[i];
        }
        Arrays.sort(arr,new Comparator<int[]>(){
            public int compare(int[] i, int[] j)
            {
                return i[0]-j[0];
            }
        });
        return dfs(0,arr,new HashMap<>());
    }

    public int dfs(int idx, int[][] arr, Map<Integer,Integer> memo)
    {
        if(idx>=arr.length)
            return 0;
        if(memo.containsKey(idx))
            return memo.get(idx);
        int nxt=findNext(idx,arr);
        int inc=arr[idx][2]+(nxt==-1?0:dfs(nxt,arr,memo));
        int exc=dfs(idx+1,arr,memo);
        int p=Math.max(inc,exc);
        memo.put(idx,p);
        return p;
    }

    public int findNext(int i, int[][] arr)
    {
        for(int j=i+1;j<arr.length;j++)
        {
            if(arr[j][0]>=arr[i][1])
                return j;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        MaximumProfitJobScheduling obj=new MaximumProfitJobScheduling();
        System.out.println(obj.jobScheduling(new int[]{1,2,3,4,6},new int[]{3,5,10,6,9},new int[]{20,20,100,70,60}));
    }
}
