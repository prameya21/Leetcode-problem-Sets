import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubArrayK
{
    /*
    Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

    Note:
    The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

    Example 1:

    Input: nums = [1, -1, 5, -2, 3], k = 3
    Output: 4
    Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
    Example 2:

    Input: nums = [-2, -1, 2, 1], k = 1
    Output: 2
    Explanation: The subarray [-1, 2] sums to 1 and is the longest.
    Follow Up:
    Can you do it in O(n) time?
     */
    public static int maxSubArrayLen(int[] nums, int k)
    {
        int sum,len=0;
        for(int i=0;i<nums.length;i++)
        {
            sum=nums[i];
            for(int j=i+1;j<nums.length;j++)
            {
                sum+=nums[j];
                if(sum==k)
                {
                    int val=j-i+1;
                    len=Math.max(len,val);
                }
            }
        }
        return len;
    }
    public static int maxSubArrayLen1(int[] nums,int k)
    {
        int sum,max=0;
        for(int i=1;i<nums.length;i++)
            nums[i]+=nums[i-1];
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<nums.length;i++)
        {
            if(map.containsKey(nums[i]-k))
                max=Math.max(max,i-map.get(nums[i]-k));
            else
                map.put(nums[i],i);
        }
        return max;
    }
    public static void main(String[] args)
    {
        int[] arr={-2, -1, 2, 1};
        System.out.println(maxSubArrayLen1(arr,1));
    }
}
