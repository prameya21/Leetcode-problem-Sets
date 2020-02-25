public class MinimumPathSum
{
    /*
        Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

        Note: You can only move either down or right at any point in time.

        Example:

        Input:
        [
          [1,3,1],
          [1,5,1],
          [4,2,1]
        ]
        Output: 7
        Explanation: Because the path 1→3→1→1→1 minimizes the sum.
     */
    public int minPathSum(int[][] grid)
    {
        if(grid==null || grid.length==0)
            return 0;
        return helper2(grid);
    }
    //Recursion
    public int helper1(int[][] grid,int m, int n)
    {
        if(m<0 || n<0)
            return Integer.MAX_VALUE;
        else if(m==0 && n==0)
            return grid[m][n];
        return Math.min(helper1(grid,m-1,n),helper1(grid,m,n-1))+grid[m][n];
    }


    //DP
    public int helper2(int[][] grid)
    {
        int[][] dp=new int[grid.length][grid[0].length];
        dp[0][0]=grid[0][0];
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(i==0 && j==0)
                    continue;
                else if(i==0)
                {
                    dp[i][j]=grid[i][j]+dp[i][j-1];
                }
                else if(j==0)
                    dp[i][j]=grid[i][j]+dp[i-1][j];
                else
                    dp[i][j]=grid[i][j]+Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public static void main(String[] args)
    {
        MinimumPathSum obj=new MinimumPathSum();
        System.out.println(obj.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }
}
