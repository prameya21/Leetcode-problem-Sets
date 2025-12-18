import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MaxFreqAfterPerformingOperations2
{
    //3347
    /*
        You are given an integer array nums and two integers k and numOperations.
        You must perform an operation numOperations times on nums, where in each operation you:
        Select an index i that was not selected in any previous operations.
        Add an integer in the range [-k, k] to nums[i].
        Return the maximum possible frequency of any element in nums after performing the operations.

        Example 1:
        Input: nums = [1,4,5], k = 1, numOperations = 2
        Output: 2
        Explanation:
        We can achieve a maximum frequency of two by:
        Adding 0 to nums[1], after which nums becomes [1, 4, 5].
        Adding -1 to nums[2], after which nums becomes [1, 4, 4].

        Example 2:
        Input: nums = [5,11,20,20], k = 5, numOperations = 1
        Output: 2
        Explanation:
        We can achieve a maximum frequency of two by:
        Adding 0 to nums[1].
     */


    public int maxFrequency(int[] nums, int k, int numOperations)
    {
        Map<Integer,Integer> freq=new HashMap<>();
        Map<Integer,Integer> range=new HashMap<>();
        TreeSet<Integer> points=new TreeSet<>();

        for(int i:nums)
        {
            freq.put(i,freq.getOrDefault(i,0)+1);
            range.put(i-k,range.getOrDefault(i-k,0)+1);
            range.put(i+k+1, range.getOrDefault(i+k+1,0)-1);
            points.add(i);
            points.add(i-k);
            points.add(i+k+1);
        }
        int res=0, sum=0;
        for(int p:points)
        {
            sum+=range.getOrDefault(p,0);
            res=Math.max(res,freq.getOrDefault(p,0)+Math.min(sum-freq.getOrDefault(p,0),numOperations));
        }
        return res;
    }

    public static void main(String[] args)
    {
        MaxFreqAfterPerformingOperations2 obj=new MaxFreqAfterPerformingOperations2();
        System.out.println(obj.maxFrequency(new int[]{1,4,5}, 1,2));
    }
}
