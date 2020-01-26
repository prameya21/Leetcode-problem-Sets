import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence
{
    /*
    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

    Your algorithm should run in O(n) complexity.

    Example:

    Input: [100, 4, 200, 1, 3, 2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     */

    public int longestConsecutive(int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;
        Arrays.sort(nums);
        int curr_max=1,max=1;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]!=nums[i-1])
            {
                if(nums[i]-nums[i-1]==1)
                    curr_max++;
                else
                {
                    max=Math.max(max,curr_max);
                    curr_max=1;
                }
            }
        }
        return Math.max(max,curr_max);
    }

    public int longestConsecutive2(int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;
        Set<Integer> set=new HashSet<>();
        for(int n:nums)
            set.add(n);
        int longest=0;
        for(int n:nums)
        {
            if(!set.contains(n-1))
            {
                int curr=n;
                int streak=1;
                while(set.contains(curr+1))
                {
                    curr++;
                    streak++;
                }
                longest=Math.max(streak,longest);
            }
        }
        return longest;
    }
    public static void main(String[] args)
    {
        LongestConsecutiveSequence obj=new LongestConsecutiveSequence();
        System.out.println(obj.longestConsecutive(new int[]{100,4,200,1,3,2}));
    }
}
