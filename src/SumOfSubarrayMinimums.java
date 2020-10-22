import java.util.Arrays;
import java.util.Stack;

public class SumOfSubarrayMinimums
{
    /*
    Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

    Since the answer may be large, return the answer modulo 10^9 + 7.
    Example 1:

    Input: [3,1,2,4]
    Output: 17
    Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
    Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.


    Note:

    1 <= A.length <= 30000
    1 <= A[i] <= 30000
     */

    public int sumSubarrayMins(int[] A)
    {
        if(A==null || A.length==0)
            return 0;
        int MOD = 1_000_000_007;
        int[] left=new int[A.length];
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<A.length;i++)
        {
            while(!st.isEmpty() && A[st.peek()]>=A[i])
                st.pop();
            left[i]=st.isEmpty()?-1:st.peek();
            st.push(i);
        }

        st.clear();
        int[] right=new int[A.length];
        for(int i=A.length-1;i>=0;i--)
        {
            while(!st.isEmpty() && A[st.peek()]>A[i])
                st.pop();
            right[i]=st.isEmpty()?A.length:st.peek();
            st.push(i);
        }


        long res=0;
        for(int i=0;i<A.length;i++)
        {
            res+=(i-left[i])*(right[i]-i) % MOD * A[i]%MOD;
            res%=MOD;
        }
        return (int)res;
    }

    public static void main(String[] args)
    {
        SumOfSubarrayMinimums obj=new SumOfSubarrayMinimums();
        System.out.println(obj.sumSubarrayMins(new int[]{2,9,7,8,3,4,6,1}));
    }
}
