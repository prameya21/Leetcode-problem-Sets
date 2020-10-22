public class SlidingWindowMaximum
{
    /*
    You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

    Return the max sliding window.
    Example 1:
    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [3,3,5,5,6,7]
    Explanation:
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7

    Example 2:
    Input: nums = [1], k = 1
    Output: [1]

    Example 3:
    Input: nums = [1,-1], k = 1
    Output: [1,-1]

    Example 4:
    Input: nums = [9,11], k = 2
    Output: [11]

    Example 5:
    Input: nums = [4,-2], k = 2
    Output: [4]
     */

    public int[] maxSlidingWindow(int[] nums, int k)
    {
        if(nums.length*k==0)
            return new int[0];
        int[] left=new int[nums.length];
        left[0]=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            if(i%k==0)
                left[i]=nums[i];
            else
                left[i]=Math.max(left[i-1],nums[i]);
        }
        int[] right=new int[nums.length];
        right[nums.length-1]=nums[nums.length-1];
        for(int i=nums.length-2;i>=0;i--)
        {
            if(i%k==0)
                right[i]=nums[i];
            else
                right[i]=Math.max(nums[i],right[i+1]);
        }
        int[] ret=new int[nums.length-k+1];
        for(int i=0;i<ret.length;i++)
            ret[i]=Math.max(left[i+k-1],right[i]);
        return ret;
    }
}
