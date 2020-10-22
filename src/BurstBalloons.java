public class BurstBalloons
{
    /*
    Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons.
    If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i.
    After the burst, the left and right then becomes adjacent.

    Find the maximum coins you can collect by bursting the balloons wisely.

    Note:

    You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
    0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
    Example:

    Input: [3,1,5,8]
    Output: 167
    Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
                 coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     */

    public int maxCoins(int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;
        int[][] dp=new int[nums.length+1][nums.length+1];
        return helper(nums,0,nums.length-1,dp);
    }

    public int helper(int[] nums, int l, int r, int[][] dp)
    {
        if(l>r)
            return 0;
        if(dp[l][r]!=0)
            return dp[l][r];
        int max=0;
        for(int i=l;i<=r;i++)
        {
            int val=helper(nums,l,i-1,dp)+(get(l-1,nums)*get(i,nums)*get(r+1,nums))+helper(nums,i+1,r,dp);
            max=Math.max(max,val);
        }
        dp[l][r]=max;
        return max;
    }


    public int get(int i, int[] nums)
    {
        return (i<0 || i>=nums.length)?1:nums[i];
    }

    public static void main(String[] args)
    {
        BurstBalloons obj=new BurstBalloons();
        System.out.println(obj.maxCoins(new int[]{3,1,5,8}));
    }
}
