public class LongestIncreasingSubsequence
{
    /*
    Given an unsorted array of integers, find the length of longest increasing subsequence.

    Example:

    Input: [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
    Note:

    There may be more than one LIS combination, it is only necessary for you to return the length.
    Your algorithm should run in O(n2) complexity.
     */
    public int lengthOfLIS(int[] nums)
    {
        if(nums.length==0)
            return 0;
        int[] dp=new int[nums.length];
        dp[0]=1;
        int maxLen=1;
        for(int i=1;i<nums.length;i++)
        {
            int max=0;
            for(int j=0;j<i;j++)
            {
                if(nums[i]>nums[j])
                {
                    max=Math.max(dp[j],max);
                }
            }
            dp[i]=max+1;
            maxLen=Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }
    public static void main(String[] args)
    {
        LongestIncreasingSubsequence obj=new LongestIncreasingSubsequence();
        System.out.println(obj.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        //System.out.println(obj.lengthOfLIS(new int[]{3,4,-1,0,6,2,3}));
    }
}
