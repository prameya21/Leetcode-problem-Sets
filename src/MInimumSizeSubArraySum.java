public class MinimumSizeSubArraySum
{
    /*
    Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

    Example:

    Input: s = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: the subarray [4,3] has the minimal length under the problem constraint.
    Follow up:
    If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
     */

    public int minSubArrayLen(int s, int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;
        int i=0,j=0,sum=0;
        int ans=Integer.MAX_VALUE;
        while(j<nums.length)
        {
            sum+=nums[j];
            while(sum>=s)
            {
                ans=Math.min(ans,j-i+1);
                sum-=nums[i++];
            }
            j++;
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }

    public static void main(String[] args)
    {
        MinimumSizeSubArraySum obj=new MinimumSizeSubArraySum();
        System.out.println(obj.minSubArrayLen(7,new int[]{2,3,1,2,4,3}));
    }
}
