public class MinimumFallingPathSum
{
    /*
    Given a square array of integers A, we want the minimum sum of a falling path through A.

    A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.



    Example 1:

    Input: [[1,2,3],[4,5,6],[7,8,9]]
    Output: 12
    Explanation:
    The possible falling paths are:
    [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
    [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
    [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
    The falling path with the smallest sum is [1,4,7], so the answer is 12.



    Constraints:

    1 <= A.length == A[0].length <= 100
    -100 <= A[i][j] <= 100
     */

    public int minFallingPathSum(int[][] A)
    {
        if(A==null || A.length==0)
            return 0;
        Integer[][] memo=new Integer[A.length][A[0].length];
        int min=Integer.MAX_VALUE;
        for(int i=0;i<A[0].length;i++)
            min=Math.min(min,helper(A,i,A.length-1,memo));
        return min;
    }

    public int helper(int[][] A, int j,int i, Integer[][] memo)
    {
        if(i==0)
            return A[i][j];
        if(memo[i][j]!=null)
            return memo[i][j];
        int min=Integer.MAX_VALUE;
        for(int col=j-1;col<=j+1;col++)
        {
            if(col<0 || col>=A[0].length)
                continue;
            min=Math.min(min,helper(A,col,i-1,memo));
        }
        memo[i][j]=min+A[i][j];
        return memo[i][j];
    }


    public static void main(String[] args)
    {
        MinimumFallingPathSum obj=new MinimumFallingPathSum();
        System.out.println(obj.minFallingPathSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }
}
