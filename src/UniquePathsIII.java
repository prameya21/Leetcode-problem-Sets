import java.util.ArrayList;
import java.util.List;

public class UniquePathsIII
{
    /*
    On a 2-dimensional grid, there are 4 types of squares:

    1 represents the starting square.  There is exactly one starting square.
    2 represents the ending square.  There is exactly one ending square.
    0 represents empty squares we can walk over.
    -1 represents obstacles that we cannot walk over.
    Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.



    Example 1:

    Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
    Output: 2
    Explanation: We have the following two paths:
    1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
    2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
    Example 2:

    Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
    Output: 4
    Explanation: We have the following four paths:
    1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
    2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
    3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
    4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
    Example 3:

    Input: [[0,1],[2,0]]
    Output: 0
    Explanation:
    There is no path that walks over every empty square exactly once.
    Note that the starting and ending square can be anywhere in the grid.
     */

    int max;
    List<String> temp=new ArrayList<>();
    public int uniquePathsIII(int[][] grid)
    {
        if(grid==null || grid.length==0)
            return -1;
        int[] src=new int[2];
        int[] dst=new int[2];
        int cnt=0;
        max=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==0)
                    cnt++;
                else if(grid[i][j]==1)
                    src=new int[]{i,j};
                else if(grid[i][j]==2)
                    dst=new int[]{i,j};
            }
        }
        dfs(grid,src[0],src[1],dst,cnt+1,new boolean[grid.length][grid[0].length]);
        return max==0?-1:max;
    }

    public void dfs(int[][] grid, int i, int j, int[] dst, int cnt, boolean[][] visited)
    {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==-1 || visited[i][j])
            return;
        if(i==dst[0] && j==dst[1] && cnt==0)
        {
            max++;

            return;
        }
        visited[i][j]=true;

        dfs(grid,i+1,j,dst,cnt-1,visited);
        dfs(grid,i-1,j,dst,cnt-1,visited);
        dfs(grid,i,j+1,dst,cnt-1,visited);
        dfs(grid,i,j-1,dst,cnt-1,visited);

        visited[i][j]=false;
    }


    public static void main(String[] args)
    {
        UniquePathsIII obj=new UniquePathsIII();
        System.out.println(obj.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}}));
        System.out.println(obj.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,0,2}}));
        System.out.println(obj.uniquePathsIII(new int[][]{{0,1},{2,0}}));
    }
}

