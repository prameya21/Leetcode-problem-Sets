import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands
{
    /*
    Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    Input:
    11110
    11010
    11000
    00000

    Output: 1
    Example 2:

    Input:
    11000
    11000
    00100
    00011

    Output: 3
     */
    public static int numIslands(char[][] grid)
    {
        if(grid.length==0 || grid==null)
            return 0;
        int cnt=0;
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='1' && !visited[i][j])
                {
                    bfs(i,j,visited,grid);
                    cnt++;
                }
            }
        }
        return cnt;

    }
    public static void dfs(int i,int j,boolean[][] visited,char[][] grid)
    {
        if(i<0 || j<0 || i>grid.length || j>grid[0].length || visited[i][j] || grid[i][j]!='1')
            return;
        visited[i][j]=true;
        dfs(i+1,j,visited,grid);
        dfs(i,j+1,visited,grid);
        dfs(i-1,j,visited,grid);
        dfs(i,j-1,visited,grid);
    }
    public static void bfs(int i,int j,boolean[][] visited,char[][] grid)
    {
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{i,j});
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int k=0;k<size;k++)
            {
                int[] curr=q.poll();
                visited[curr[0]][curr[1]]=true;
                int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
                for(int[] d:dir)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    if(nr<0 || nc<0 || nr>=grid.length || nc>=grid[0].length || visited[nr][nc] || grid[nr][nc]!='1')
                        continue;
                    q.offer(new int[]{nr,nc});
                }
            }
        }
    }
    public static void main(String[] args)
    {
        char[][] grid={{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(numIslands(grid));
    }
}
