import java.util.Arrays;

public class MaximumSumOfTwoNonOverLappingSubArray
{
    /*
    Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)

    Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:

    0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
    0 <= j < j + M - 1 < i < i + L - 1 < A.length.


    Example 1:

    Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
    Output: 20
    Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
    Example 2:

    Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
    Output: 29
    Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
    Example 3:

    Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
    Output: 31
    Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.


    Note:

    L >= 1
    M >= 1
    L + M <= A.length <= 1000
    0 <= A[i] <= 1000
     */

    public int maxSumTwoNoOverlap(int[] A, int L, int M)
    {
        if(A==null || A.length==0)
            return 0;
        int[] preSum=new int[A.length];
        preSum[0]=A[0];
        for(int i=1;i<A.length;i++)
            preSum[i]=preSum[i-1]+A[i];


        int res=preSum[L+M-1], maxL=A[L-1], maxM=A[M-1];
        for(int i=L+M;i<preSum.length;i++)
        {
            maxL=Math.max(maxL,preSum[i-M]-preSum[i-L-M]);
            maxM=Math.max(maxM,preSum[i-L]-preSum[i-M-L]);
            res=Math.max(res,Math.max(maxL+preSum[i]-preSum[i-M],maxM+preSum[i]-preSum[i-L]));
        }
        return res;
    }

    public int maxSumTwoNoOverlap2(int[] A, int L, int M)
    {
        if(A==null || A.length==0)
            return 0;
        int res=0,maxL=0,lSum=0,maxM=0,mSum=0;
        for(int i=0;i<A.length;i++)
        {
            mSum+=A[i];
            if(i-M>=0)
            {
                mSum-=A[i-M];
                lSum+=A[i-M];
            }
            if(i-M-L>=0)
                lSum-=A[i-L-M];
            maxL=Math.max(maxL,lSum);
            res=Math.max(res,maxL+mSum);
        }
        maxL=maxM=mSum=lSum=0;
        for(int i=0;i<A.length;i++)
        {
            lSum+=A[i];
            if(i-L>=0)
            {
                lSum-=A[i-L];
                mSum+=A[i-L];
            }
            if(i-L-M>=0)
                mSum-=A[i-L-M];
            maxM=Math.max(maxM,mSum);
            res=Math.max(res,lSum+maxM);
        }
        return res;
    }

    public static void main(String[] args)
    {
        MaximumSumOfTwoNonOverLappingSubArray obj=new MaximumSumOfTwoNonOverLappingSubArray();
        System.out.println(obj.maxSumTwoNoOverlap2(new int[]{0,6,5,2,2,5,1,9,4},1,2));
    }
}
