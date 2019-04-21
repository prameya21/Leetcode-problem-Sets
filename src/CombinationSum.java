import java.util.ArrayList;
import java.util.List;

public class CombinationSum
{
    /*
    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

    The same repeated number may be chosen from candidates unlimited number of times.

    Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.
    Example 1:

    Input: candidates = [2,3,6,7], target = 7,
    A solution set is:
    [
      [7],
      [2,2,3]
    ]
    Example 2:

    Input: candidates = [2,3,5], target = 8,
    A solution set is:
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target)
    {
        List<List<Integer>> result=new ArrayList<>();
        if(candidates.length==0)
            return result;
        combinationSum(candidates,target,0,new ArrayList<>(),result);
        return result;
    }
    public static void combinationSum(int[] nums,int target,int start,List<Integer> temp,List<List<Integer>> result)
    {
        if(target<0)
            return;
        if(0==target)
            result.add(new ArrayList<>(temp));
        for(int i=start;i<nums.length;i++)
        {
            temp.add(nums[i]);
            combinationSum(nums,target-nums[i],i,temp,result);
            temp.remove(temp.size()-1);
        }
    }
    public static void main(String[] args)
    {
        int [] nums={2,3,6,7};
        System.out.println(combinationSum(nums,9));
    }
}
