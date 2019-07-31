public class LongestIncreasingPath
{
    /*
    Given an integer matrix, find the length of the longest increasing path.

    From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

    Example 1:

    Input: nums =
    [
      [9,9,4],
      [6,6,8],
      [2,1,1]
    ]
    Output: 4
    Explanation: The longest increasing path is [1, 2, 6, 9].
    Example 2:

    Input: nums =
    [
      [3,4,5],
      [3,2,6],
      [2,2,1]
    ]
    Output: 4
    Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
     */
    public int longestIncreasingPath(int[][] matrix)
    {
        int[][] memo=new int[matrix.length][matrix[0].length];
        int res=0;
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                int len=dfs2(matrix,memo,i,j,Integer.MIN_VALUE);
                res=Math.max(res,len);
            }
        }
        return res;
    }
    public int dfs2(int[][] matrix,int[][] memo, int i,int j,int last)
    {
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[0].length || matrix[i][j]<=last)
            return 0;
        if(memo[i][j]!=0)
            return memo[i][j];
        int len=0;
        len=Math.max(len,dfs2(matrix,memo,i+1,j,matrix[i][j]));
        len=Math.max(len,dfs2(matrix,memo,i-1,j,matrix[i][j]));
        len=Math.max(len,dfs2(matrix,memo,i,j+1,matrix[i][j]));
        len=Math.max(len,dfs2(matrix,memo,i,j-1,matrix[i][j]));
        memo[i][j]=len+1;
        return len+1;
    }

    public int dfs(int[][] matrix,int[][] memo, int i,int j)
    {
        if(memo[i][j]!=0)
            return memo[i][j];
        int len=1;
        if(i-1>=0 && matrix[i-1][j]>matrix[i][j])
            len=Math.max(len,1+dfs(matrix,memo,i-1,j));
        if(i+1<matrix.length && matrix[i+1][j]>matrix[i][j])
            len=Math.max(len,1+dfs(matrix,memo,i+1,j));
        if(j-1>=0 && matrix[i][j-1]>matrix[i][j])
            len=Math.max(len,1+dfs(matrix,memo,i,j-1));
        if(j+1<matrix[0].length && matrix[i][j+1]>matrix[i][j])
            len=Math.max(len,1+dfs(matrix,memo,i,j+1));
        memo[i][j]=len;
        return len;
    }
    public static void main(String[] args)
    {
        LongestIncreasingPath obj=new LongestIncreasingPath();
        System.out.println(obj.longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{3,2,1}}));
    }
}
