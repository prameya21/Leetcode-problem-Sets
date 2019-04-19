import java.util.HashMap;
import java.util.Map;

public class TwoSum
{
    /*
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:

    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
     */
    public static int[] twoSum(int[] arr,int target)
    {
        Map<Integer,Integer> freq=new HashMap<>();
        for(int i=0;i<arr.length;i++)
        {
            int complement=target-arr[i];
            if(freq.containsKey(complement))
                return new int[]{freq.get(complement),i};
            else
                freq.put(arr[i],i);
        }
        return new int[]{-1,-1};
    }
    public static void main(String[] args)
    {
        int[] arr={2,7,11,15};
        int[] res=twoSum(arr,9);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
