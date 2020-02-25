public class KnapSack
{
    /*
        Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
        In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively.
        Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W.
        You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
     */

    public int knapSack(int[] val, int [] wt, int W)
    {
        if(wt==null || wt.length==0 || W==0)
            return 0;
        //return recursiveHelper(wt,val,W,val.length-1);
        return recursiveHelperdp(val,wt,W);
    }
    public int recursiveHelperdp(int[] val, int[] wt, int W)
    {
        int[][] dp=new int[val.length+1][W+1];
        for(int i=0;i<=val.length;i++)
        {
            for(int j=0;j<=W;j++)
            {
                if(i==0 || j==0)
                {
                    dp[i][j]=0;
                    continue;
                }
                else
                {
                    if(j-wt[i-1]>=0)
                        dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-wt[i-1]]+val[i-1]);
                    else
                        dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[val.length][W];
    }

    public int recursiveHelper(int[] wt, int[] val, int W, int n)
    {
        if(n==0 || W==0)
            return 0;
        if(wt[n]>W)
            return recursiveHelper(wt,val,W,n-1);
        else
            return Math.max(val[n]+recursiveHelper(wt,val,W-wt[n],n-1),recursiveHelper(wt,val,W,n-1));
    }


    public static void main(String[] args)
    {
        KnapSack obj=new KnapSack();
        System.out.println(obj.knapSack(new int[]{1,4,5,7},new int[]{1,3,4,5},7));
    }
}
