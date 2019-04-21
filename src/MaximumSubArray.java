public class MaximumSubArray
{
    /*
    Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

    Example:

    Input: [-2,1,-3,4,-1,2,1,-5,4],
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.
    Follow up:

    If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
     */
    public static int maxSubArray(int[] nums)
    {
        int maxSoFar=nums[0],maxFinal=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            maxSoFar+=nums[i];
            if(maxSoFar<0)
                maxSoFar=0;
            maxFinal=Math.max(maxFinal,maxSoFar);
        }
        return maxFinal;
    }
    public static void main(String[] args)
    {
        int[] arr={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }
}
