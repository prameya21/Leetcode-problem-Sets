public class SplitArrayLargestSum
{
    /*
        Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

        Note:
        If n is the length of array, assume the following constraints are satisfied:

        1 ≤ n ≤ 1000
        1 ≤ m ≤ min(50, n)
        Examples:

        Input:
        nums = [7,2,5,10,8]
        m = 2

        Output:
        18

        Explanation:
        There are four ways to split nums into two subarrays.
        The best way is to split it into [7,2,5] and [10,8],
        where the largest sum among the two subarrays is only 18.
     */
    public int splitArray(int[] nums, int m)
    {
        int minVal=Integer.MIN_VALUE,maxVal=0;
        for(int n:nums)
        {
            maxVal+=n;
            minVal=Math.max(minVal,n);
        }
        while(minVal<maxVal)
        {
            int mid=minVal+(maxVal-minVal)/2;
            if(valid(mid,nums,m))
                maxVal=mid;
            else
                minVal=mid+1;
        }
        return minVal;
    }
    public boolean valid(int subarraySum, int[] nums, int m)
    {
        int subArrayCnt=1, currSubArraySum=0;
        for(int n:nums)
        {
            currSubArraySum+=n;
            if(currSubArraySum>subarraySum)
            {
                currSubArraySum=n;
                subArrayCnt++;
                if(subArrayCnt>m)
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args)
    {
        SplitArrayLargestSum obj=new SplitArrayLargestSum();
        System.out.println(obj.splitArray(new int[]{7,2,5,10,8},2));
    }
}
