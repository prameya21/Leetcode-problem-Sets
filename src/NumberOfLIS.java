import java.util.Arrays;

public class NumberOfLIS
{
    /*
    Given an integer array nums, return the number of longest increasing subsequences.

    Example 1:
    Input: nums = [1,3,5,4,7]
    Output: 2
    Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

    Example 2:
    Input: nums = [2,2,2,2,2]
    Output: 5
    Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
     */

    public int findNumberOfLIS(int[] nums)
    {
        int[] dp=new int[nums.length];
        int[] count=new int[nums.length];
        int longest=1;
        count[0]=1;
        for(int i=0;i<nums.length;i++)
        {
            int max=1, c=1;
            for(int j=0;j<=i;j++)
            {
                if(nums[j]<nums[i])
                {
                    if(dp[j]>max)
                    {
                        max=dp[j];
                        c=count[j];
                    }
                    else if(dp[j]==max)
                        c+=count[j];
                }
                dp[i]=max+1;
                count[i]=c;
                longest=Math.max(dp[i],longest);
            }
        }
        int res=0;
        for(int i=0;i<nums.length;i++)
        {
            if(longest==dp[i])
                res+=count[i];
        }
        return res;
    }

    public static void main(String[] args)
    {
        NumberOfLIS obj=new NumberOfLIS();
        System.out.println(obj.findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(obj.findNumberOfLIS(new int[]{2,2,2,2,2}));
    }
}
