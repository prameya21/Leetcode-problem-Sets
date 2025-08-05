import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathWithObstacleElimination
{
    /*
        You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.
        Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

        Example 1:
        Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
        Output: 6
        Explanation:
        The shortest path without eliminating any obstacle is 10.
        The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

        Example 2:
        Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
        Output: -1
        Explanation: We need to eliminate at least two obstacles to find such a walk.

        Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 40
        1 <= k <= m * n
        grid[i][j] is either 0 or 1.
        grid[0][0] == grid[m - 1][n - 1] == 0
     */

    public int shortestPath(int[][] grid, int k)
    {
        if(grid==null || grid.length==0)
            return 0;
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{0,0,k});
        boolean[][][] visited=new boolean[grid.length][grid[0].length][k+1];
        int res=0;
        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                int obs=curr[2];
                if(curr[0]==grid.length-1 && curr[1]==grid[0].length-1)
                    return res;
                for(int[] d:dirs)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || visited[nr][nc][obs])
                        continue;
                    if(grid[nr][nc]==1 && obs==0)
                        continue;
                    visited[nr][nc][obs]=true;
                    q.offer(new int[]{nr,nc,grid[nr][nc]==1?obs-1:obs});
                }
            }
            res++;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        ShortestPathWithObstacleElimination obj=new ShortestPathWithObstacleElimination();
        System.out.println(obj.shortestPath(new int[][]{{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}},1));
        System.out.println(obj.shortestPath(new int[][]{{0,1,1},{1,1,1},{1,0,0}},1));
    }
}
