import java.util.Arrays;

public class NumberOfLIS
{
    /*
    Given an integer array nums, return the number of longest increasing subsequences.

    Example 1:
    Input: nums = [1,3,5,4,7]
    Output: 2
    Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

    Example 2:
    Input: nums = [2,2,2,2,2]
    Output: 5
    Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
     */

    public int findNumberOfLIS(int[] nums)
    {
        //todo
        return 0;
    }

    public static void main(String[] args)
    {
        NumberOfLIS obj=new NumberOfLIS();
        System.out.println(obj.findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(obj.findNumberOfLIS(new int[]{2,2,2,2,2}));
    }
}
