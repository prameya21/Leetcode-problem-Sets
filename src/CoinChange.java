public class CoinChange
{
    /*
        You are given coins of different denominations and a total amount of money amount.
        Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

        Example 1:

        Input: coins = [1, 2, 5], amount = 11
        Output: 3
        Explanation: 11 = 5 + 5 + 1
        Example 2:

        Input: coins = [2], amount = 3
        Output: -1
        Note:
        You may assume that you have an infinite number of each kind of coin.
     */


    public int coinChange(int[] coins, int amount)
    {
        if(coins==null || coins.length==0)
            return 0;
        int[] dp=new int[amount+1];
        for(int i=1;i<=amount;i++)
        {
            dp[i]=Integer.MAX_VALUE;
            for(int x:coins)
            {
                if(i-x>=0 && dp[i-x]!=Integer.MAX_VALUE)
                    dp[i]=Math.min(dp[i],dp[i-x]+1);
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }



    public static void main(String[] args)
    {
        CoinChange obj=new CoinChange();
        System.out.println(obj.coinChange(new int[]{1,2,5},11));
    }
}
