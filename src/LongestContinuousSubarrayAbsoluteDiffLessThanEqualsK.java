import java.util.*;
public class LongestContinuousSubarrayAbsoluteDiffLessThanEqualsK
{
    /*
    Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.



    Example 1:

    Input: nums = [8,2,4,7], limit = 4
    Output: 2
    Explanation: All subarrays are:
    [8] with maximum absolute diff |8-8| = 0 <= 4.
    [8,2] with maximum absolute diff |8-2| = 6 > 4.
    [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
    [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
    [2] with maximum absolute diff |2-2| = 0 <= 4.
    [2,4] with maximum absolute diff |2-4| = 2 <= 4.
    [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
    [4] with maximum absolute diff |4-4| = 0 <= 4.
    [4,7] with maximum absolute diff |4-7| = 3 <= 4.
    [7] with maximum absolute diff |7-7| = 0 <= 4.
    Therefore, the size of the longest subarray is 2.
    Example 2:

    Input: nums = [10,1,2,4,7,2], limit = 5
    Output: 4
    Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
    Example 3:

    Input: nums = [4,2,2,2,4,4,2,2], limit = 0
    Output: 3


    Constraints:

    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^9
    0 <= limit <= 10^9
     */

    public int longestSubarray(int[] nums, int limit)
    {
        if(nums==null || nums.length==0)
            return 0;
        Deque<Integer> max=new ArrayDeque<>();
        Deque<Integer> min=new ArrayDeque<>();
        int l=0,r=0,res=0;
        while(r<nums.length && l<nums.length)
        {
            while(!max.isEmpty() && max.peekLast()<nums[r])
                max.pollLast();
            max.offerLast(nums[r]);


            while(!min.isEmpty() && min.peekLast()>nums[r])
                min.pollLast();
            min.offerLast(nums[r]);

            while(max.peekFirst()-min.peekFirst()>limit)
            {
                if(max.peekFirst()==nums[l])
                    max.pollFirst();
                if(min.peekFirst()==nums[l])
                    min.pollFirst();
                l++;
            }
            res=Math.max(res,r-l+1);
            r++;
        }
        return res;


    }

    public static void main(String[] args)
    {
        LongestContinuousSubarrayAbsoluteDiffLessThanEqualsK obj=new LongestContinuousSubarrayAbsoluteDiffLessThanEqualsK();
        System.out.println(obj.longestSubarray(new int[]{1,5,6,7,8,10,6,5,6},4));
    }
}
