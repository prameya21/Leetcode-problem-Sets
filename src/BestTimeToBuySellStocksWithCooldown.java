public class BestTimeToBuySellStocksWithCooldown
{
    /*
        You are given an array prices where prices[i] is the price of a given stock on the ith day.

        Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

        After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
        Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



        Example 1:

        Input: prices = [1,2,3,0,2]
        Output: 3
        Explanation: transactions = [buy, sell, cooldown, buy, sell]
        Example 2:

        Input: prices = [1]
        Output: 0

     */

    public int maxProfit(int[] prices)
    {
        if(prices==null || prices.length==0)
            return 0;
        Integer[][] memo=new Integer[prices.length][3];
        return helper(prices,memo,0,0);
    }
    public int helper(int[] prices, Integer[][] memo, int idx, int status)
    {
        if(idx>=prices.length)
            return 0;
        if(memo[idx][status]!=null)
            return memo[idx][status];

        int doNothing=helper(prices,memo,idx+1,status);
        int doSomething;
        if(status==0)
            doSomething=helper(prices,memo,idx+1,1)-prices[idx];
        else if(status==1)
            doSomething=helper(prices,memo,idx+1,2)+prices[idx];
        else
            doSomething=helper(prices,memo,idx+1,0);
        memo[idx][status]=Math.max(doSomething,doNothing);

        return memo[idx][status];
    }

    public static void main(String[] args)
    {
        BestTimeToBuySellStocksWithCooldown obj=new BestTimeToBuySellStocksWithCooldown();
        System.out.println(obj.maxProfit(new int[]{1,2,3,0,2}));
    }
}
