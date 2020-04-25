import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceFromAllBuilding
{
    /*
    You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

    Each 0 marks an empty land which you can pass by freely.
    Each 1 marks a building which you cannot pass through.
    Each 2 marks an obstacle which you cannot pass through.
    Example:

    Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

    1 - 0 - 2 - 0 - 1
    |   |   |   |   |
    0 - 0 - 0 - 0 - 0
    |   |   |   |   |
    0 - 0 - 1 - 0 - 0

    Output: 7

    Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
                 the point (1,2) is an ideal empty land to build a house, as the total
                 travel distance of 3+3+1=7 is minimal. So return 7.
     */

    public int shortestDistance(int[][] grid)
    {
        if(grid==null || grid.length==0)
            return 0;
        int[][] reach=new int[grid.length][grid[0].length];
        int[][] dist=new int[grid.length][grid[0].length];
        int numBuildings=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                    numBuildings++;
            }
        }
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                {
                    if(!bfs(grid,i,j,reach,dist,numBuildings))
                        return -1;
                }
            }
        }
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<dist.length;i++)
        {
            for(int j=0;j<dist[0].length;j++)
            {
                if(dist[i][j]<ans && reach[i][j]==numBuildings)
                    ans=dist[i][j];
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    public boolean bfs(int[][] grid, int r, int c, int[][] reach, int[][] dist,int numBuildings)
    {
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        Queue<int[]> q=new LinkedList<>();
        visited[r][c]=true;
        q.offer(new int[]{r,c});
        int level=1;
        int[][] dirs=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int ctr=1;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                for(int[] d:dirs)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || visited[nr][nc])
                        continue;
                    if(grid[nr][nc]==1)
                    {
                        ctr++;
                        visited[nr][nc]=true;
                    }
                    else if(grid[nr][nc]==0)
                    {
                        dist[nr][nc]+=level;
                        reach[nr][nc]++;
                        q.offer(new int[]{nr,nc});
                        visited[nr][nc]=true;
                    }
                }
            }
            level++;
        }
        return ctr==numBuildings;
    }

    public static void main(String[] args)
    {
        ShortestDistanceFromAllBuilding obj=new ShortestDistanceFromAllBuilding();
        System.out.println(obj.shortestDistance(new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}}));
    }

}
