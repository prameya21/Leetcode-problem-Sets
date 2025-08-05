import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberOfBeautifulSubsets
{
    /*
        You are given an array nums of positive integers and a positive integer k.
        A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
        Return the number of non-empty beautiful subsets of the array nums.
        A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

        Example 1:
        Input: nums = [2,4,6], k = 2
        Output: 4
        Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
        It can be proved that there are only 4 beautiful subsets in the array [2,4,6].

        Example 2:
        Input: nums = [1], k = 1
        Output: 1
        Explanation: The beautiful subset of the array nums is [1].
        It can be proved that there is only 1 beautiful subset in the array [1].

        Constraints:
        1 <= nums.length <= 20
        1 <= nums[i], k <= 1000
     */

    public int beautifulSubsets(int[] nums, int k)
    {
        if(nums==null || nums.length==0)
            return 0;
        Arrays.sort(nums);
        return helper(nums, k, 0,new HashMap<>())-1;
    }

    public int helper(int[] nums, int k, int idx, Map<Integer,Integer> map)
    {
        if(idx==nums.length)
            return 1;
        int res=0;
        res+=helper(nums,k,idx+1,map);

        if(!map.containsKey(nums[idx]-k))
        {
            map.put(nums[idx],map.getOrDefault(nums[idx],0)+1);
            res+=helper(nums,k,idx+1,map);
            map.put(nums[idx],map.get(nums[idx])-1);
            if(map.get(nums[idx])==0)
                map.remove(nums[idx]);
        }
        return res;
    }


    public static void main(String[] args)
    {
        NumberOfBeautifulSubsets obj= new NumberOfBeautifulSubsets();
        System.out.println(obj.beautifulSubsets(new int[]{2,4,6}, 2));
    }

}
