public class TwoKeysKeyboard
{
    /*
    Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

    Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
    Paste: You can paste the characters which are copied last time.


    Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

    Example 1:

    Input: 3
    Output: 3
    Explanation:
    Intitally, we have one character 'A'.
    In step 1, we use Copy All operation.
    In step 2, we use Paste operation to get 'AA'.
    In step 3, we use Paste operation to get 'AAA'.


    Note:

    The n will be in the range [1, 1000].
     */


    public int minSteps(int n)
    {
        Integer[][] memo=new Integer[n+1][n+1];
        return helper(n,0,1, memo);
    }
    public int helper(int n, int clip, int m, Integer[][] memo)
    {
        if(m==n)
            return 0;
        if(m>n || clip>n)
            return Integer.MAX_VALUE;
        if(memo[m][clip]!=null)
            return memo[m][clip];
        int min=Integer.MAX_VALUE;
        if(m!=clip)
            min=Math.min(min,helper(n,m,m,memo));
        if(clip>0)
            min=Math.min(min,helper(n,clip,m+clip,memo));
        memo[m][clip]=min==Integer.MAX_VALUE?min:min+1;
        return memo[m][clip];
    }


    public int minSteps2(int n)
    {
        if (n <= 1) return 0;
        if (n == 2) return 2;
        for (int i = 2; i <= n / 2; i++)
        {
            if (n % i == 0)
            {
                return i + minSteps(n / i);
            }
        }
        return n;
    }

    public static void main(String[] args)
    {
        TwoKeysKeyboard obj=new TwoKeysKeyboard();
        System.out.println(obj.minSteps(3));
    }
}
