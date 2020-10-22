import java.util.HashMap;
import java.util.Map;

public class NonOverlappingSUbarraysWithTargetSum
{
    /*
    Given an array of integers arr and an integer target.
    You have to find two non-overlapping sub-arrays of arr each with sum equal target. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
    Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.

    Example 1:
    Input: arr = [3,2,2,4,3], target = 3
    Output: 2
    Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.

    Example 2:
    Input: arr = [7,3,4,7], target = 7
    Output: 2
    Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose the first and third sub-arrays as the sum of their lengths is 2.

    Example 3:
    Input: arr = [4,3,2,6,2,3,4], target = 6
    Output: -1
    Explanation: We have only one sub-array of sum = 6.

    Example 4:
    Input: arr = [5,5,4,4,5], target = 3
    Output: -1
    Explanation: We cannot find a sub-array of sum = 3.

    Example 5:
    Input: arr = [3,1,1,1,5,1,2,1], target = 3
    Output: 3
    Explanation: Note that sub-arrays [1,2] and [2,1] cannot be an answer because they overlap.
     */

    public int minSumOfLengths(int[] arr, int target)
    {
        if(arr==null || arr.length==0)
            return -1;
        int min1=Integer.MAX_VALUE,min2=Integer.MAX_VALUE;
        int[] dp=new int[arr.length];

        int l=0,r=0,sum=0,bestSoFar=Integer.MAX_VALUE,ans=Integer.MAX_VALUE;
        while(r<arr.length)
        {
            sum+=arr[r];
            while(sum>target)
                sum-=arr[l++];
            if(sum==target)
            {
                if(l>0 && dp[l-1]!=Integer.MAX_VALUE)
                    ans=Math.min(ans,dp[l-1]+r-l+1);
                bestSoFar=Math.min(bestSoFar,r-l+1);
            }
            dp[r]=bestSoFar;
            r++;
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    public int minSumOfLengths2(int[] arr, int target)
    {
        Map<Integer,Integer> map=new HashMap<>();
        if(arr==null || arr.length==0)
            return -1;
        int res=Integer.MAX_VALUE,sum=0,lmin=Integer.MAX_VALUE;
        map.put(0,-1);
        for(int i=0;i<arr.length;i++)
        {
            sum+=arr[i];
            map.put(sum,i);
        }
        sum=0;
        for(int i=0;i<arr.length;i++)
        {
            sum+=arr[i];
            if(map.containsKey(sum-target))
                lmin=Math.min(lmin,i-map.get(sum-target));
            if(map.containsKey(sum+target) && lmin!=Integer.MAX_VALUE)
                res=Math.min(res,map.get(sum+target)-i+lmin);
        }
        return res==Integer.MAX_VALUE?-1:res;
    }

    public static void main(String[] args)
    {
        NonOverlappingSUbarraysWithTargetSum obj=new NonOverlappingSUbarraysWithTargetSum();
        System.out.println(obj.minSumOfLengths2(new int[]{3,1,1,1,5,1,2,1},3));
        System.out.println(obj.minSumOfLengths2(new int[]{3,2,2,4,3},3));
        System.out.println(obj.minSumOfLengths2(new int[]{7,3,4,7},7));
        System.out.println(obj.minSumOfLengths2(new int[]{4,3,2,6,2,3,4},6));
        System.out.println(obj.minSumOfLengths2(new int[]{1,6,1},7));
    }
}



