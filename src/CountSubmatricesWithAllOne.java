import java.util.Arrays;

public class CountSubmatricesWithAllOne
{
    //1504
    /*
        Given an m x n binary matrix mat, return the number of submatrices that have all ones.

        Example 1:
        Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
        Output: 13
        Explanation:
        There are 6 rectangles of side 1x1.
        There are 2 rectangles of side 1x2.
        There are 3 rectangles of side 2x1.
        There is 1 rectangle of side 2x2.
        There is 1 rectangle of side 3x1.
        Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.

        Example 2:
        Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
        Output: 24
        Explanation:
        There are 8 rectangles of side 1x1.
        There are 5 rectangles of side 1x2.
        There are 2 rectangles of side 1x3.
        There are 4 rectangles of side 2x1.
        There are 2 rectangles of side 2x2.
        There are 2 rectangles of side 3x1.
        There is 1 rectangle of side 3x2.
        Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.


        Constraints:

        1 <= m, n <= 150
        mat[i][j] is either 0 or 1.
     */

    public int numSubmat(int[][] mat)
    {
        if(mat==null || mat.length==0)
            return 0;
        int[][] dp=new int[mat.length][mat[0].length];
        int res=0;
        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++)
            {
                if(j==0)
                    dp[i][j] = mat[i][j];
                else
                    dp[i][j]=mat[i][j]==0?0:dp[i][j-1]+1;
                int cur=dp[i][j];
                for(int k=i;k>=0;k--)
                {
                    cur=Math.min(cur,dp[k][j]);
                    if(cur==0)
                        break;
                    res+=cur;
                }
            }
        }
        for(int[] i: dp)
            System.out.println(Arrays.toString(i));
        return res;
    }

    //todo Monotonic stack

    public static void main(String[] args)
    {
        CountSubmatricesWithAllOne obj=new CountSubmatricesWithAllOne();
        System.out.println(obj.numSubmat(new int[][]{{0,1,1,0},{0,1,1,1},{1,1,1,0}}));
        System.out.println(obj.numSubmat(new int[][]{{1,0,1},{1,1,0},{1,1,0}}));
    }
}
