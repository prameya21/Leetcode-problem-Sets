public class MaxMatrixSum
{
    /*
        1975
        You are given an n x n integer matrix. You can do the following operation any number of times:
        Choose any two adjacent elements of matrix and multiply each of them by -1.
        Two elements are considered adjacent if and only if they share a border.
        Your goal is to maximize the summation of the matrix's elements.
        Return the maximum sum of the matrix's elements using the operation mentioned above.

        Example 1:
        Input: matrix = [[1,-1],[-1,1]]
        Output: 4
        Explanation: We can follow the following steps to reach sum equals 4:
        - Multiply the 2 elements in the first row by -1.
        - Multiply the 2 elements in the first column by -1.

        Example 2:
        Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
        Output: 16
        Explanation: We can follow the following step to reach sum equals 16:
        - Multiply the 2 last elements in the second row by -1.


        Constraints:

        n == matrix.length == matrix[i].length
        2 <= n <= 250
        -105 <= matrix[i][j] <= 105
     */

    public long maxMatrixSum(int[][] mat)
    {
        long sum=0;
        int minValue=Integer.MAX_VALUE, nc=0;
        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++)
            {
                sum+=Math.abs(mat[i][j]);
                minValue=Math.min(Math.abs(mat[i][j]),minValue);
                nc+=mat[i][j]<0?1:0;
            }
        }

        return nc%2==0?sum:sum-(2*minValue);
    }

    public static void main(String[] args)
    {
        MaxMatrixSum obj=new MaxMatrixSum();
        System.out.println(obj.maxMatrixSum(new int[][]{{1,2,3},{-1,-2,-3},{1,2,3}}));
    }
}
