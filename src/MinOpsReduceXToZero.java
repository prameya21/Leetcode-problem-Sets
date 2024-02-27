public class MinOpsReduceXToZero
{
    /*
        You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
        Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

        Example 1:
        Input: nums = [1,1,4,2,3], x = 5
        Output: 2
        Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

        Example 2:
        Input: nums = [5,6,7,8,9], x = 4
        Output: -1

        Example 3:
        Input: nums = [3,2,20,1,1,3], x = 10
        Output: 5
        Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.

        Constraints:

        1 <= nums.length <= 105
        1 <= nums[i] <= 104
        1 <= x <= 109
     */

    public int minOperations(int[] nums, int x)
    {
        if(nums==null || nums.length==0)
            return -1;
        int sum=0;
        for(int i:nums)
            sum+=i;
        sum-=x;
        int res=Integer.MIN_VALUE, l=0, r=0, curr=0;
        while(r<nums.length)
        {
            curr+=nums[r];
            while(curr>sum && l<nums.length)
                curr-=nums[l++];

            if(curr==sum)
                res=Math.max(res,r-l+1);
            r++;
        }
        return res==Integer.MIN_VALUE?-1:nums.length-res;
    }

    public static void main(String[] args)
    {
        MinOpsReduceXToZero obj=new MinOpsReduceXToZero();
        System.out.println(obj.minOperations(new int[]{1,1,4,2,3}, 5));
        System.out.println(obj.minOperations(new int[]{5,6,7,8,9}, 4));
        System.out.println(obj.minOperations(new int[]{3,2,20,1,1,3}, 10));
    }
}
