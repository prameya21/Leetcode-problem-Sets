public class MaxConsequtiveOnes1
{
    /*
    Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

    Example 1:
    Input: [1,0,1,1,0]
    Output: 4
    Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
        After flipping, the maximum number of consecutive 1s is 4.
    Note:

    The input array will only contain 0 and 1.
    The length of input array is a positive integer and will not exceed 10,000
     */

    public static int findMaxConsecutiveOnes(int[] nums)
    {
        int h=0,l=0,k=1,tot=0,zero=0;
        while(h<nums.length)
        {
            if(nums[h]==0)
                zero++;
            while(zero>k)
            {
                if(nums[l]==0)
                    zero--;
                l++;
            }
            tot=Math.max(tot,h-l+1);
            h++;
        }
        return tot;
    }
    public static void main(String[] args)
    {
        int[] arr={1,0,1,1,0};
        System.out.println(findMaxConsecutiveOnes(arr));
    }
}
