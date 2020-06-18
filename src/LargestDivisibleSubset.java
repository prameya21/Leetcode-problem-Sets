import java.util.*;

public class LargestDivisibleSubset
{
    /*
    Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

    Si % Sj = 0 or Sj % Si = 0.

    If there are multiple solutions, return any subset is fine.

    Example 1:

    Input: [1,2,3]
    Output: [1,2] (of course, [1,3] will also be ok)
    Example 2:

    Input: [1,2,4,8]
    Output: [1,2,4,8]
     */

    public List<Integer> largestDivisibleSubset(int[] nums)
    {
        if(nums==null || nums.length==0)
            return new ArrayList<>();

        Arrays.sort(nums);
        int[] count=new int[nums.length];
        Arrays.fill(count,1);

        for(int i=1;i<nums.length;i++)
            for(int j=i-1;j>=0;j--)
                if(nums[i]%nums[j]==0)
                    count[i]=Math.max(count[i],count[j]+1);

        int maxIndex=0;
        for(int i=0;i<count.length;i++)
            maxIndex=count[i]>count[maxIndex]?i:maxIndex;

        int totalCount=count[maxIndex];
        int temp=nums[maxIndex];
        List<Integer> result=new ArrayList<>();
        for(int i=maxIndex;i>=0;i--)
        {
            if(temp%nums[i]==0 && count[i]==totalCount)
            {
                result.add(nums[i]);
                temp=nums[i];
                totalCount--;
            }
        }
        return result;
    }

    public List<Integer> largestDivisibleSubset2(int[] nums)
    {
        Arrays.sort(nums);

        return helper(nums,0,new HashMap<>());
    }
    public List<Integer> helper(int[] nums, int idx, Map<Integer,List<Integer>> memo)
    {
        if(memo.containsKey(idx))
            return memo.get(idx);
        List<Integer> temp=new ArrayList<>();
        int div=idx==0?1:nums[idx-1];
        for(int k=idx;k<nums.length;k++)
        {
            if(nums[k]%div==0)
            {
                List<Integer> subList=new ArrayList<>(helper(nums,k+1,memo));
                subList.add(nums[k]);
                if(subList.size()>temp.size())
                    temp=subList;
            }
        }
        memo.put(idx,temp);
        return temp;
    }


    public static void main(String[] args)
    {
        LargestDivisibleSubset obj=new LargestDivisibleSubset();
        System.out.println(obj.largestDivisibleSubset2(new int[]{1,4,5,12,8,9}));
    }
}
