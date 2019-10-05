import java.util.LinkedList;
import java.util.Queue;

public class Matrix01
{
    /*
    Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

    The distance between two adjacent cells is 1.
    Example 1:

    Input:
    [[0,0,0],
     [0,1,0],
     [0,0,0]]

    Output:
    [[0,0,0],
     [0,1,0],
     [0,0,0]]
    Example 2:
    Input:
    [[0,0,0],
     [0,1,0],
     [1,1,1]]

    Output:
    [[0,0,0],
     [0,1,0],
     [1,2,1]]


    Note:

    The number of elements of the given matrix will not exceed 10,000.
    There are at least one 0 in the given matrix.
    The cells are adjacent in only four directions: up, down, left and right.
     */
    public int[][] updateMatrix(int[][] mat)
    {
        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++)
            {
                if(mat[i][j]==0)
                    q.offer(new int[]{i,j});
                else
                    mat[i][j]=Integer.MAX_VALUE;
            }
        }
        int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
        while(!q.isEmpty())
        {
            int[] curr=q.poll();
            int r=curr[0];
            int c=curr[1];
            for(int[] d:dir)
            {
                int nr=r+d[0];
                int nc=c+d[1];
                if(nr<0 || nr>=mat.length || nc<0 || nc>=mat[0].length || mat[nr][nc]<=mat[r][c]+1)
                    continue;
                mat[nr][nc]=Math.min(mat[r][c]+1,mat[nr][nc]);
                q.offer(new int[]{nr,nc});

            }

        }
        return mat;
    }
    public static void main(String[] args)
    {
        Matrix01 obj=new Matrix01();
        int[][] ret=obj.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}});
        for(int[] row:ret)
        {
            for(int i:row)
            {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}
