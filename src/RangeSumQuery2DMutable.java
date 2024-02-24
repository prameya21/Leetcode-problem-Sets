public class RangeSumQuery2DMutable
{
    /*
        Given a 2D matrix matrix, handle multiple queries of the following types:

        Update the value of a cell in matrix.
        Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
        Implement the NumMatrix class:

        NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
        void update(int row, int col, int val) Updates the value of matrix[row][col] to be val.
        int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).


        Example 1:


        Input
        ["NumMatrix", "sumRegion", "update", "sumRegion"]
        [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
        Output
        [null, 8, null, 10]

        Explanation
        NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
        numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e. sum of the left red rectangle)
        numMatrix.update(3, 2, 2);       // matrix changes from left image to right image
        numMatrix.sumRegion(2, 1, 4, 3); // return 10 (i.e. sum of the right red rectangle)
     */
    class NumMatrix
    {
        int[][] pSum, mat;
        public NumMatrix(int[][] matrix)
        {
            this.mat=matrix;
            pSum=new int[mat.length+1][mat[0].length+1];
            for(int i=0;i<pSum.length;i++)
                for(int j=0;j<mat[0].length;j++)
                    pSum[i][j+1]=pSum[i][j]+mat[i][j];

        }

        public void update(int row, int col, int val)
        {
            int temp=mat[row][col];
            for(int j=col+1;j<pSum[0].length;j++)
                pSum[row][j]=pSum[row][j]-temp+val;
            mat[row][col]=val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2)
        {
            int sum=0;
            for(int i=row1;i<=row2;i++)
                sum+=(pSum[i][col2+1]-pSum[i][col1]);
            return sum;
        }
    }
}
