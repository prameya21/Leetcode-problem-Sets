public class ExpressIntAsSumOfPowers
{
    /*
        //2787
        Given two positive integers n and x.
        Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.
        Since the result can be very large, return it modulo 109 + 7.
        For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.

        Example 1:
        Input: n = 10, x = 2
        Output: 1
        Explanation: We can express n as the following: n = 32 + 12 = 10.
        It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.

        Example 2:
        Input: n = 4, x = 1
        Output: 2
        Explanation: We can express n in the following ways:
        - n = 41 = 4.
        - n = 31 + 11 = 4.


        Constraints:

        1 <= n <= 300
        1 <= x <= 5
     */
    int MOD=1_000_000_007;
    public int numberOfWays(int n, int x)
    {
        if(n==0)
            return 0;
        Integer[][] dp=new Integer[n+1][n+1];
        return helper(n,x,1,n, dp);
    }

    public int helper(int n, int x, int curr, int max, Integer[][] dp)
    {
        if(n==0)
            return 1;
        if(n<0 || curr>max)
            return 0;
        if(dp[n][curr]!=null)
            return dp[n][curr];
        return dp[n][curr]=(helper(n,x,curr+1,n,dp)+helper((int)(n-Math.pow(curr,x)),x,curr+1,max,dp)) %MOD;
    }


    public static void main(String[] args)
    {
        ExpressIntAsSumOfPowers obj=new ExpressIntAsSumOfPowers();
        System.out.println(obj.numberOfWays(213,1));
    }
}
