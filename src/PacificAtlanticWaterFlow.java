import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow
{
    /*
    Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

    Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

    Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

    Note:
    The order of returned grid coordinates does not matter.
    Both m and n are less than 150.
    Example:

    Given the following 5x5 matrix:

      Pacific ~   ~   ~   ~   ~
           ~  1   2   2   3  (5) *
           ~  3   2   3  (4) (4) *
           ~  2   4  (5)  3   1  *
           ~ (6) (7)  1   4   5  *
           ~ (5)  1   1   2   4  *
              *   *   *   *   * Atlantic

    Return:

    [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
     */

    public static List<int[]> pacificAtlanticDFS(int[][] matrix)
    {
        List<int[]> res=new ArrayList<>();
        boolean[][] pac=new boolean[matrix.length][matrix[0].length];
        boolean[][] atl=new boolean[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++)
        {
            dfs(matrix,pac,Integer.MIN_VALUE,i,0);
            dfs(matrix,atl,Integer.MIN_VALUE,i,matrix[0].length-1);
        }
        for(int i=0;i<matrix[0].length;i++)
        {
            dfs(matrix,pac,Integer.MIN_VALUE,0,i);
            dfs(matrix,atl,Integer.MIN_VALUE,matrix.length-1,i);
        }
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++)
                if(pac[i][j] && atl[i][j])
                    res.add(new int[]{i,j});
        return res;
    }
    public static void dfs(int[][] matrix,boolean[][] visited,int val,int i,int j)
    {
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[0].length || visited[i][j] || matrix[i][j]<val)
            return;
        visited[i][j]=true;
        dfs(matrix,visited,matrix[i][j],i+1,j);
        dfs(matrix,visited,matrix[i][j],i-1,j);
        dfs(matrix,visited,matrix[i][j],i,j+1);
        dfs(matrix,visited,matrix[i][j],i,j-1);
    }
    public static List<int[]> pacificAtlantic(int[][] matrix)
    {
        List<int[]> res=new ArrayList<>();

        boolean[][] pac=new boolean[matrix.length][matrix[0].length];
        boolean[][] atl=new boolean[matrix.length][matrix[0].length];
        Queue<int[]> pqueue=new LinkedList<>();
        Queue<int[]> aqueue=new LinkedList<>();
        for(int i=0;i<matrix.length;i++)
        {
            pac[i][0]=true;
            atl[i][matrix[0].length-1]=true;
            pqueue.offer(new int[]{i,0});
            aqueue.offer(new int[]{i,matrix[0].length-1});
        }
        for(int i=0;i<matrix[0].length;i++)
        {
            pac[0][i]=true;
            atl[matrix.length-1][i]=true;
            pqueue.offer(new int[]{0,i});
            aqueue.offer(new int[]{matrix.length-1,i});
        }
        bfs(matrix,atl,aqueue);
        bfs(matrix,pac,pqueue);
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++)
                if(atl[i][j] && pac[i][j])
                    res.add(new int[]{i,j});
        return res;

    }

    public static void bfs(int[][] matrix,boolean[][] visited,Queue<int[]> q)
    {
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] cur=q.poll();
                for(int[] d:dir)
                {
                    int nr=cur[0]+d[0];
                    int nc=cur[1]+d[1];
                    if(nr>=0 && nr<matrix.length && nc>=0 && nc<matrix[0].length && !visited[nr][nc] && matrix[nr][nc]>=matrix[cur[0]][cur[1]])
                    {
                        visited[nr][nc]=true;
                        q.offer(new int[]{nr,nc});
                    }
                }
            }
        }
    }
    public static void main(String[] args)
    {
        int[][] matrix={{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        List<int[]> res=pacificAtlanticDFS(matrix);
        for(int[] l:res)
            System.out.println(l[0]+","+l[1]);
    }
}
