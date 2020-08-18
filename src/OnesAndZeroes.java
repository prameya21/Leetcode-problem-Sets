import java.util.HashMap;
import java.util.Map;

public class OnesAndZeroes
{
    /*
    Given an array, strs, with strings consisting of only 0s and 1s. Also two integers m and n.

    Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.



    Example 1:

    Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
    Output: 4
    Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are "10","0001","1","0".
    Example 2:

    Input: strs = ["10","0","1"], m = 1, n = 1
    Output: 2
    Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".


    Constraints:

    1 <= strs.length <= 600
    1 <= strs[i].length <= 100
    strs[i] consists only of digits '0' and '1'.
    1 <= m, n <= 100
     */

    public int findMaxForm(String[] strs, int m, int n)
    {
        if(strs==null || strs.length==0)
            return 0;

        return helper(strs,m,n,0,new HashMap<>());
    }

    public int helper(String[] strs, int m, int n, int idx, Map<String,Integer> memo)
    {
        if(idx==strs.length)
            return 0;
        String key=idx+","+m+","+n;
        if(memo.containsKey(key))
            return memo.get(key);
        int taken=-1;
        int[] ret=compute(strs[idx]);
        if(m-ret[0]>=0 && n-ret[1]>=0)
            taken=helper(strs,m-ret[0],n-ret[1],idx+1,memo)+1;
        int not_taken=helper(strs,m,n,idx+1,memo);

        memo.put(key,Math.max(taken,not_taken));
        return Math.max(taken,not_taken);
    }

    public int[] compute(String s)
    {
        int zeroes=0,ones=0;
        for(char c:s.toCharArray())
        {
            if(c=='0')
                zeroes++;
            else
                ones++;
        }
        return new int[]{zeroes,ones};
    }


    public static void main(String[] args)
    {
        OnesAndZeroes obj=new OnesAndZeroes();
        //System.out.println(obj.findMaxForm(new String[]{"10","0001","111001","1","0"},5,3));

        System.out.println(obj.findMaxForm(new String[]{"11111","100","1101","1101","11000"},5,7));

    }
}
