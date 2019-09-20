import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge
{
    /*
    In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

    Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

    Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)



    Example 1:

    Input: [[0,1],[1,0]]
    Output: 1
    Example 2:

    Input: [[0,1,0],[0,0,0],[0,0,1]]
    Output: 2
    Example 3:

    Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
    Output: 1


    Note:

    1 <= A.length = A[0].length <= 100
    A[i][j] == 0 or A[i][j] == 1
     */

    public int shortestBridge(int[][] A)
    {
        if(A==null || A.length==0)
            return 0;
        boolean found=false;
        for(int i=0;i<A.length;i++)
        {
            if(found)
                break;
            for(int j=0;j<A[0].length;j++)
            {
                if(A[i][j]==1)
                {
                    dfs(A,i,j);
                    found=true;
                    break;
                }

            }
        }
        Queue<int[]> q=new LinkedList<>();
        boolean[][] visited=new boolean[A.length][A[0].length];
        for(int i=0;i<A.length;i++)
        {
            for(int j=0;j<A[0].length;j++)
            {
                if(A[i][j]==2)
                {
                    q.offer(new int[]{i,j});
                    visited[i][j]=true;
                }
            }
        }

        int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
        int count=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                int r=curr[0];
                int c=curr[1];
                if(A[r][c]==1)
                    return count-1;
                for(int[] d:dir)
                {
                    int nr=r+d[0];
                    int nc=c+d[1];
                    if(nr<0 || nr>=A.length || nc<0 || nc>=A[0].length || visited[nr][nc])
                        continue;
                    else
                    {
                        q.offer(new int[]{nr,nc});
                        visited[nr][nc]=true;
                    }
                }
            }
            count++;
        }
        return 0;
    }
    public void dfs(int[][] A, int i, int j)
    {
        if(i<0 || i>=A.length || j<0 || j>=A[0].length || A[i][j]!=1)
            return;
        A[i][j]=2;
        dfs(A,i+1,j);
        dfs(A,i-1,j);
        dfs(A,i,j+1);
        dfs(A,i,j-1);
    }
    public static void main(String[] args)
    {
        ShortestBridge obj=new ShortestBridge();
        System.out.println(obj.shortestBridge(new int[][]{{0,1},{1,0}}));
        System.out.println(obj.shortestBridge(new int[][]{{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}}));
    }
}
