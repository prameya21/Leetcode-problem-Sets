import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EscapeFire
{
    /*
    Given a room represented like a grid, you have to escape from the fire. The grid looks like the following where 0 represents empty place you can move through, 1 represents you (a person) and 2 represents fire.
    You can consider yourself escaped if you reach any of the sides that's not on fire (ie., grid[i][j]!=2 && i = 0 || j == 0 || i == m-1 || j == n-1 where 0<=i<m , 0 <=j<n and m,n being length and height of the grid).

    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0
    0 0 0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0
    0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0
    0 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0
    Goal is to find the shortest distance from 1 to any of the sides. I was able to solve this with plain BFS.

    Followup:

    Now for every step you take, the fire grows a step in all four directions. For the example above, for the next step you make, the room would look like this.

    0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0
    2 2 2 0 0 0 0 0 0 0 2 0 0 0 2 2 2 0
    0 2 0 0 0 0 0 0 0 2 2 2 0 0 0 2 0 0
    0 0 0 0 0 0 0 1 0 0 2 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0
    0 0 2 0 0 0 0 0 0 0 0 0 2 2 2 0 0 0
    0 2 2 2 0 0 0 0 2 0 0 0 0 2 0 0 0 0
    0 0 2 0 0 0 0 2 2 2 0 0 0 0 0 0 0 0
    Again the goal is to escape fire.
     */

    public int escapeFire1(int[][] grid, int[] src)
    {
        if(grid==null || grid.length==0)
            return -1;
        Queue<int[]> q=new LinkedList<>();
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        q.offer(src);
        visited[src[0]][src[1]]=true;
        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        int ctr=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                int r=curr[0];
                int c=curr[1];
                if(r==0 || r==grid.length-1 || c==0 || c==grid[0].length-1)
                    return ctr;
                for(int[] d:dirs)
                {
                    int nr=r+d[0];
                    int nc=c+d[1];
                    if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || visited[nr][nc] || grid[nr][nc]==2)
                        continue;
                    visited[nr][nc]=true;
                    q.offer(new int[]{nr,nc});
                }
            }
            ctr++;
        }
        return -1;
    }
    public int escapeFire2(int[][] grid, int[] src)
    {
        if(grid==null || grid.length==0)
            return -1;
        Queue<int[]> q=new LinkedList<>();
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        q.offer(src);
        visited[src[0]][src[1]]=true;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
                if(grid[i][j]==2)
                    q.offer(new int[]{i,j});
        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        int ctr=0;

        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                int r=curr[0],c=curr[1];
                if(r==0 || r==grid.length-1 || c==0 || c==grid[0].length-1)
                {
                    if(grid[r][c]!=2)
                        return ctr;
                }
                if(grid[r][c]==2)
                {
                    for(int[] d: dirs)
                    {
                        int nr=r+d[0],nc=c+d[1];
                        if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || grid[nr][nc]==2)
                            continue;
                        grid[nr][nc]=2;
                        q.offer(new int[]{nr,nc});
                    }
                }
                else
                {
                    for(int[] d:dirs)
                    {
                        int nr=r+d[0], nc=c+d[1];
                        if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || grid[nr][nc]==2 || visited[nr][nc])
                            continue;
                        visited[nr][nc]=true;
                        q.offer(new int[]{nr,nc});
                    }
                }
            }

            ctr++;
        }
        return -1;
    }
    public static void main(String[] args)
    {
        EscapeFire obj=new EscapeFire();
        int[][] grid={{0,0,2,2,0},{2,0,0,0,2},{0,2,1,0,0},{2,0,0,0,0},{0,0,0,0,0}};
        System.out.println(obj.escapeFire2(grid,new int[]{2,2}));
    }

}
