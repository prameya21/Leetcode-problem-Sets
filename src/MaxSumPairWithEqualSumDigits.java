import java.util.*;

public class MaxSumPairWithEqualSumDigits
{
    /*
        2342
        You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].
        Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the conditions. If no such pair of indices exists, return -1.

        Example 1:
        Input: nums = [18,43,36,13,7]
        Output: 54
        Explanation: The pairs (i, j) that satisfy the conditions are:
        - (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
        - (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
        So the maximum sum that we can obtain is 54.

        Example 2:
        Input: nums = [10,12,19,14]
        Output: -1
        Explanation: There are no two numbers that satisfy the conditions, so we return -1.

        Constraints:
        1 <= nums.length <= 105
        1 <= nums[i] <= 109
     */

    public int maximumSum(int[] nums)
    {
        if(nums==null || nums.length==0)
            return -1;
        Map<Integer, PriorityQueue<Integer>> map=new HashMap<>();
        for(int i:nums)
        {
            int k=i;
            int key=0;
            while(k!=0)
            {
                key+=k%10;
                k/=10;
            }
            //System.out.println(key);
            map.putIfAbsent(key,new PriorityQueue<>(Collections.reverseOrder()));
            map.get(key).offer(i);
        }
        int res=-1;
        for(int i:map.keySet())
        {
            if(map.get(i).size()>1)
            {
                int val=map.get(i).poll()+map.get(i).poll();
                res=Math.max(res,val);
            }
        }
        return res;
    }


    public static void main(String[] args)
    {
        MaxSumPairWithEqualSumDigits obj=new MaxSumPairWithEqualSumDigits();
        System.out.println(obj.maximumSum(new int[]{18,43,36,13,7}));
        System.out.println(obj.maximumSum(new int[]{10,12,19,14}));
    }
}
