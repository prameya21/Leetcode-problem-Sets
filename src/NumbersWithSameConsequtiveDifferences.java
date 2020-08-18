import java.util.ArrayList;
import java.util.List;

public class NumbersWithSameConsequtiveDifferences
{
    /*
    Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

    Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

    You may return the answer in any order.



    Example 1:

    Input: N = 3, K = 7
    Output: [181,292,707,818,929]
    Explanation: Note that 070 is not a valid number, because it has leading zeroes.
    Example 2:

    Input: N = 2, K = 1
    Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
     */

    public int[] numsSameConsecDiff(int N, int K)
    {
        if(N==0)
            return new int[0];
        List<Integer> result=new ArrayList<>();
        for(int i=1;i<=9;i++)
            helper(i,i,1,N,K,result);
        if(N==1)
            result.add(0);
        int[] ret=new int[result.size()];
        int idx=0;
        for(int i:result)
            ret[idx++]=i;

        return ret;

    }

    public void helper(int val, int last, int len, int n, int k,List<Integer> result)
    {
        if(len==n)
        {
            result.add(val);
            return;
        }
        if(k==0)
        {
            helper(val*10+last,last,len+1,n,k,result);
            return;
        }
        if(last+k<10)
        {
            int nxt=last+k;
            helper(val*10+nxt,nxt,len+1,n,k,result);
        }
        if(last-k>=0)
        {
            int nxt=last-k;
            helper(val*10+nxt,nxt,len+1,n,k,result);
        }
    }

    public static void main(String[] args)
    {
        NumbersWithSameConsequtiveDifferences obj=new NumbersWithSameConsequtiveDifferences();
        System.out.println(obj.numsSameConsecDiff(2,1));
    }
}
