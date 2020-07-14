import java.util.Arrays;

public class HouseRobber
{
    /*
    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
    the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

    Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.



    Example 1:

    Input: nums = [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
                 Total amount you can rob = 1 + 3 = 4.
    Example 2:

    Input: nums = [2,7,9,3,1]
    Output: 12
    Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
                 Total amount you can rob = 2 + 9 + 1 = 12.


    Constraints:

    0 <= nums.length <= 100
    0 <= nums[i] <= 400
     */


    public int rob(int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;
        int[] memo=new int[nums.length+1];
        Arrays.fill(memo,-1);
        return Math.max(helper(nums,0,memo),helper(nums,1,memo));
    }


    public int helper(int[] nums, int i,int[] memo)
    {
        if(i>=nums.length)
            return 0;
        if(memo[i]>-1)
            return memo[i];
        int cost=nums[i];
        memo[i]=Math.max(helper(nums,i+2,memo)+nums[i],helper(nums,i+1,memo));
        return memo[i];

    }
    public int helper2(int[] nums, int i, int cost)
    {
        if(i>=nums.length)
            return cost;
        return Math.max(helper2(nums,i+1,cost),helper2(nums,i+2,cost+nums[i]));
    }

    public static void main(String[] args)
    {
        HouseRobber obj=new HouseRobber();
        //System.out.println(obj.rob(new int[]{1,2,3,1}));
        System.out.println(obj.rob(new int[]{2,7,9,3,1}));
    }
}
