import java.util.PriorityQueue;

public class MinOpToExceedThresholdValue2
{
    /*
        //3066
        You are given a 0-indexed integer array nums, and an integer k.
        You are allowed to perform some operations on nums, where in a single operation, you can:
        Select the two smallest integers x and y from nums.
        Remove x and y from nums.
        Insert (min(x, y) * 2 + max(x, y)) at any position in the array.
        Note that you can only apply the described operation if nums contains at least two elements.
        Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.

        Example 1:
        Input: nums = [2,11,10,1,3], k = 10
        Output: 2

        Explanation:
        In the first operation, we remove elements 1 and 2, then add 1 * 2 + 2 to nums. nums becomes equal to [4, 11, 10, 3].
        In the second operation, we remove elements 3 and 4, then add 3 * 2 + 4 to nums. nums becomes equal to [10, 11, 10].
        At this stage, all the elements of nums are greater than or equal to 10 so we can stop.
        It can be shown that 2 is the minimum number of operations needed so that all elements of the array are greater than or equal to 10.


        Example 2:
        Input: nums = [1,1,2,4,9], k = 20
        Output: 4

        Explanation:
        After one operation, nums becomes equal to [2, 4, 9, 3].
        After two operations, nums becomes equal to [7, 4, 9].
        After three operations, nums becomes equal to [15, 9].
        After four operations, nums becomes equal to [33].
        At this stage, all the elements of nums are greater than 20 so we can stop.
        It can be shown that 4 is the minimum number of operations needed so that all elements of the array are greater than or equal to 20.
     */

    public int minOperations(int[] nums, int k)
    {
        if(nums==null || nums.length==0)
            return 0;
        PriorityQueue<Long> pq=new PriorityQueue<Long>();
        for(int i:nums)
            pq.offer((long)i);
        int res=0;
        while(pq.size()>1)
        {
            if(pq.peek()>=k)
                break;
            long i=pq.poll();
            long j=pq.poll();
            long val=Math.min(i,j) * 2;
            val+=Math.max(i,j);
            System.out.println(i+" "+j+" "+val);
            pq.offer(val);
            res++;
        }
        return res;
    }

    public static void main(String[] args)
    {
        MinOpToExceedThresholdValue2 obj=new MinOpToExceedThresholdValue2();
        //System.out.println(obj.minOperations(new int[]{2,11,10,1,3}, 10));
        //System.out.println(obj.minOperations(new int[]{1,1,2,4,9}, 20));
        System.out.println(obj.minOperations(new int[]{1000000000,999999999,1000000000,999999999,1000000000,999999999},1000000000));
    }
}
