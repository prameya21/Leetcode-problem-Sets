public class MaximumSumThreeNonOverlappingSubArrays
{
    /*
    In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

    Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

    Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

    Example:

    Input: [1,2,1,2,6,7,5,1], 2
    Output: [0, 3, 5]
    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
    We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.


    Note:

    nums.length will be between 1 and 20000.
    nums[i] will be between 1 and 65535.
    k will be between 1 and floor(nums.length / 3).
     */

    public int[] maxSumOfThreeSubarrays1(int[] nums, int k)
    {
        int n=nums.length,maxSum=0;
        int[] sum=new int[n+1], posLeft=new int[n], posRight=new int[n],ans=new int[3];
        for(int i=0;i<n;i++)
            sum[i+1]=sum[i]+nums[i];
        for(int i=k, tot=sum[k]-sum[0];i<n;i++)
        {
            if(sum[i+1]-sum[i+1-k]>tot)
            {
                posLeft[i]=i+1-k;
                tot=sum[i+1]-sum[i+1-k];
            }
            else
                posLeft[i]=posLeft[i-1];
        }

        posRight[n-k]=n-k;
        for(int i=n-k-1, tot=sum[n]-sum[n-k];i>=0;i--)
        {
            if(sum[i+k]-sum[i]>=tot)
            {
                posRight[i]=i;
                tot=sum[i+k]-sum[i];
            }
            else
                posRight[i]=posRight[i+1];
        }

        for(int i=k;i<=n-2*k;i++)
        {
            int l=posLeft[i-1],r=posRight[i+k];
            int tot=(sum[i+k]-sum[i])+(sum[l+k]-sum[l])+(sum[r+k]-sum[r]);
            if(tot>maxSum)
            {
                maxSum=tot;
                ans[0]=l;
                ans[1]=i;
                ans[2]=r;
            }
        }
        return ans;
    }


    public int[] maxSumOfThreeSubarrays(int[] nums, int k)
    {
        if(nums==null || nums.length==0)
            return null;
        int[] kSum=getKSum(nums,k);
        int[] maxFromLeft=getMaxFromLeft(kSum);
        int[] maxFromRight=getMaxFromRight(kSum);
        int[] res=new int[3];

        int max=0;
        for(int i=k;i<kSum.length-k;i++)
        {
            int l=maxFromLeft[i-k], r=maxFromRight[i+k];
            if(kSum[i]+kSum[l]+kSum[r]>max)
            {
                max=kSum[i]+kSum[l]+kSum[r];
                res[0]=l;
                res[1]=i;
                res[2]=r;
            }
        }
        return res;
    }

    public int[] getMaxFromRight(int[] nums)
    {
        int maxVal=-1;
        int maxIndex=-1;
        int[] res=new int[nums.length];
        for(int i=nums.length-1;i>=0;i--)
        {
            if(nums[i]>maxVal)
            {
                maxVal=nums[i];
                maxIndex=i;
                res[i]=i;
            }
            else
                res[i]=maxIndex;
        }
        return res;
    }

    public int[] getMaxFromLeft(int[] nums)
    {
        int maxVal=-1;
        int maxIndex=-1;
        int[] res=new int[nums.length];
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>maxVal)
            {
                maxVal=nums[i];
                maxIndex=i;
                res[i]=i;
            }
            else
                res[i]=maxIndex;
        }
        return res;
    }
    public int[] getKSum(int[] nums, int k)
    {
        int[] res=new int[nums.length-k+1];
        int kSum=0;
        for(int i=0;i<nums.length;i++)
        {
            if(i<k)
                kSum+=nums[i];
            else
            {
                kSum+=nums[i];
                kSum-=nums[i-k];
            }
            if(i>=k-1)
                res[i-k+1]=kSum;
        }
        return res;
    }


    public static void main(String[] args)
    {
        MaximumSumThreeNonOverlappingSubArrays obj=new MaximumSumThreeNonOverlappingSubArrays();
        int[] ret=obj.maxSumOfThreeSubarrays(new int[]{1,2,1,2,6,7,5,1},2);
        for(int i:ret)
            System.out.println(i);
    }
}
