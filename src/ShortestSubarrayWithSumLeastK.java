import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarrayWithSumLeastK
{
    /*
    Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

    If there is no non-empty subarray with sum at least K, return -1.



    Example 1:

    Input: A = [1], K = 1
    Output: 1
    Example 2:

    Input: A = [1,2], K = 4
    Output: -1
    Example 3:

    Input: A = [2,-1,2], K = 3
    Output: 3
     */

    public int shortestSubarray(int[] A, int K)
    {
        if(A==null || A.length==0)
            return 0;
        int res=A.length+1;
        int[] B=new int[A.length+1];
        for(int i=0;i<A.length;i++)
            B[i+1]=B[i]+A[i];
        Deque<Integer> d=new ArrayDeque<>();
        for(int i=0;i<B.length;i++)
        {
            while(!d.isEmpty() && B[i]-B[d.getFirst()]>=K)
                res=Math.min(res,i-d.pollFirst());
            while(!d.isEmpty() && B[i]<=B[d.getLast()])
                d.pollLast();
            d.offerLast(i);
        }
        return res<=A.length?res:-1;
    }

    public static void main(String[] args)
    {
        ShortestSubarrayWithSumLeastK obj=new ShortestSubarrayWithSumLeastK();
        System.out.println(obj.shortestSubarray(new int[]{1,2},4));
    }

}
