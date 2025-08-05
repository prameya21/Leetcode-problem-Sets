import java.util.*;

public class MinObstaclesRemovalToReachBottom
{
    /*
        2290
        You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
        0 represents an empty cell,
        1 represents an obstacle that may be removed.
        You can move up, down, left, or right from and to an empty cell.
        Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).

        Example 1:
        Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
        Output: 2
        Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
        It can be shown that we need to remove at least 2 obstacles, so we return 2.
        Note that there may be other ways to remove 2 obstacles to create a path.

        Example 2:
        Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
        Output: 0
        Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.

        Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 105
        2 <= m * n <= 105
        grid[i][j] is either 0 or 1.
        grid[0][0] == grid[m - 1][n - 1] == 0
     */

    public int minimumObstacles(int[][] grid)
    {
        if(grid==null || grid.length==0)
            return 0;
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });
        pq.offer(new int[]{0,0,0});
        int[][] min=new int[grid.length][grid[0].length];
        for(int[] i:min)
            Arrays.fill(i,Integer.MAX_VALUE);
        min[0][0]=grid[0][0];
        int[][] dirs= {{0,1},{0,-1},{1,0},{-1,0}};
        while(!pq.isEmpty())
        {
            int[] curr=pq.poll();
            int cost=curr[0];
            for(int[] d:dirs)
            {
                int nr=d[0]+curr[1], nc=d[1]+curr[2];
                if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || cost+grid[nr][nc]>=min[nr][nc])
                    continue;
                min[nr][nc]=cost+grid[nr][nc];
                pq.offer(new int[]{min[nr][nc],nr,nc});
            }
        }
        return min[min.length-1][min[0].length-1];
    }

    //0-1 BFS. Since the weight is 1 or zero, we can skip djikstra's sort cost by utilizing a deque and adding 0 cost values in the front and 1 cost values in the end.
    //This ensures the q remains sorted, and djikstra's algorithm still functions correctly

    public int minimumObstacles01(int[][] grid)
    {
        if(grid==null || grid.length==0)
            return 0;

        Deque<int[]> pq=new ArrayDeque<>();
        pq.offer(new int[]{0,0,0});
        int[][] min=new int[grid.length][grid[0].length];
        for(int[] i:min)
            Arrays.fill(i,Integer.MAX_VALUE);
        min[0][0]=grid[0][0];
        int[][] dirs= {{0,1},{0,-1},{1,0},{-1,0}};
        while(!pq.isEmpty())
        {
            int[] curr=pq.poll();
            int cost=curr[0];
            for(int[] d:dirs)
            {
                int nr=d[0]+curr[1], nc=d[1]+curr[2];
                if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || cost+grid[nr][nc]>=min[nr][nc])
                    continue;
                min[nr][nc]=cost+grid[nr][nc];
                if(grid[nr][nc]==1)
                    pq.offerLast(new int[]{min[nr][nc],nr,nc});
                else
                    pq.offerFirst(new int[]{min[nr][nc],nr,nc});
            }
        }
        return min[min.length-1][min[0].length-1];
    }

    public static void main(String[] args)
    {
        MinObstaclesRemovalToReachBottom obj=new MinObstaclesRemovalToReachBottom();
        System.out.println(obj.minimumObstacles01(new int[][]{{0,1,1},{1,1,0},{1,1,0}}));
    }
}
