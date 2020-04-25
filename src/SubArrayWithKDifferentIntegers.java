import java.util.HashMap;
import java.util.Map;

public class SubArrayWithKDifferentIntegers
{
    /*
    Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

    (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

    Return the number of good subarrays of A.



    Example 1:

    Input: A = [1,2,1,2,3], K = 2
    Output: 7
    Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
    Example 2:

    Input: A = [1,2,1,3,4], K = 3
    Output: 3
    Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


    Note:

    1 <= A.length <= 20000
    1 <= A[i] <= A.length
    1 <= K <= A.length
     */
    public int subarraysWithKDistinct(int[] A, int K)
    {
        int val1=helper(A,K)-helper(A,K-1);
        return val1;
    }
    public int helper(int[] nums, int k)
    {
        Map<Integer,Integer> map=new HashMap<>();
        int i=0,j=0,res=0,count=0;
        while(j<nums.length)
        {
            map.put(nums[j],map.getOrDefault(nums[j],0)+1);
            if(map.get(nums[j])==1)
                count++;
            while(count>k)
            {
                map.put(nums[i],map.get(nums[i])-1);
                if(map.get(nums[i])==0)
                    count--;
                i++;
            }
            res+=j-i+1;
            j++;
        }
        return res;
    }

    public static void main(String[] args)
    {
        SubArrayWithKDifferentIntegers obj=new SubArrayWithKDifferentIntegers();
        System.out.println(obj.subarraysWithKDistinct(new int[]{1,2,1,2,3},2));
    }
}
