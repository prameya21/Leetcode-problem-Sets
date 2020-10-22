public class SubArrayProductLessThanK
{
    /*
    Your are given an array of positive integers nums.

    Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

    Example 1:
    Input: nums = [10, 5, 2, 6], k = 100
    Output: 8
    Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
    Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
     */

    public int numSubarrayProductLessThanK(int[] nums, int k)
    {
        int ans=0,prod=1,l=0,r=0;
        while(r<nums.length)
        {
            prod*=nums[r];
            while(l<nums.length && prod>=k)
                prod/=nums[l++];
            ans+=r-l+1;
            r++;
        }
        return ans;
    }

    public static void main(String[] args)
    {
        SubArrayProductLessThanK obj=new SubArrayProductLessThanK();
        System.out.println(obj.numSubarrayProductLessThanK(new int[]{10,5,2,6},100));
    }
}
