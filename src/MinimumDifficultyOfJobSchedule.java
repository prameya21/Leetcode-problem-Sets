import java.util.Arrays;

public class MinimumDifficultyOfJobSchedule
{
    /*
    You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).

    You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.

    Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].

    Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.



    Example 1:


    Input: jobDifficulty = [6,5,4,3,2,1], d = 2
    Output: 7
    Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
    Second day you can finish the last job, total difficulty = 1.
    The difficulty of the schedule = 6 + 1 = 7
    Example 2:

    Input: jobDifficulty = [9,9,9], d = 4
    Output: -1
    Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
    Example 3:

    Input: jobDifficulty = [1,1,1], d = 3
    Output: 3
    Explanation: The schedule is one job per day. total difficulty will be 3.
    Example 4:

    Input: jobDifficulty = [7,1,7,1,7,1], d = 3
    Output: 15
    Example 5:

    Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
    Output: 843
     */

    public int minDifficulty(int[] jobDifficulty, int d)
    {
        if(jobDifficulty==null || jobDifficulty.length<d)
            return -1;
        int[][] dp=new int[jobDifficulty.length][d+1];
        for(int[] i:dp)
            Arrays.fill(i,-1);
        return helper(jobDifficulty,d,0,dp);
    }

    public int helper(int[] nums, int d, int idx, int[][] dp)
    {
        if(d==0 && idx==nums.length)
            return 0;
        if(d==0 || idx==nums.length)
            return Integer.MAX_VALUE;
        if(dp[idx][d]!=-1)
            return dp[idx][d];
        int currMax=nums[idx];
        int res=Integer.MAX_VALUE;
        for(int i=idx;i<nums.length;i++)
        {
            currMax=Math.max(currMax,nums[i]);
            int val=helper(nums,d-1,i+1,dp);
            if(val==Integer.MAX_VALUE)
                continue;
            res=Math.min(res,currMax+val);
        }
        dp[idx][d]=res;
        return res;
    }




    public static void main(String[] args)
    {
        MinimumDifficultyOfJobSchedule obj=new MinimumDifficultyOfJobSchedule();
        System.out.println(obj.minDifficulty(new int[]{11,111,22,222,33,333,44,444},6));
    }

}
