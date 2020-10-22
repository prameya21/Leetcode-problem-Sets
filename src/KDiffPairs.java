import java.util.HashMap;
import java.util.Map;

public class KDiffPairs
{
    /*
    Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

    Example 1:

    Input: nums = [3,1,4,1,5], k = 2
    Output: 2
    Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
    Although we have two 1s in the input, we should only return the number of unique pairs.
    Example 2:

    Input: nums = [1,2,3,4,5], k = 1
    Output: 4
    Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
    Example 3:

    Input: nums = [1,3,1,5,4], k = 0
    Output: 1
    Explanation: There is one 0-diff pair in the array, (1, 1).
    Example 4:

    Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3
    Output: 2
    Example 5:

    Input: nums = [-1,-2,-3], k = 1
    Output: 2
     */

    public int findPairs(int[] nums, int k)
    {
        if(nums==null || nums.length==0)
            return 0;
        int result=0;
        Map<Integer,Integer> freqMap=new HashMap<>();
        for(int i:nums)
            freqMap.put(i,freqMap.getOrDefault(i,0)+1);

        for(int i:freqMap.keySet())
        {
            if(freqMap.containsKey(i-k))
                result++;
            if(k==0 && freqMap.get(i)>1)
                result++;
        }
        return result;
    }

    public static void main(String[] args)
    {
        KDiffPairs obj=new KDiffPairs();
        System.out.println(obj.findPairs(new int[]{1,2,4,4,3,3,0,9,2,3},3));
    }
}
