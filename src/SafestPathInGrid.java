import java.util.*;
public class SafestPathInGrid
{
    /*
        You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
        A cell containing a thief if grid[r][c] = 1
        An empty cell if grid[r][c] = 0
        You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.
        The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.
        Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
        An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.
        The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.

        Example 1:
        Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
        Output: 0
        Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).

        Example 2:
        Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
        Output: 2
        Explanation: The path depicted in the picture above has a safeness factor of 2 since:
        - The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
        It can be shown that there are no other paths with a higher safeness factor.

        Example 3:
        Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
        Output: 2
        Explanation: The path depicted in the picture above has a safeness factor of 2 since:
        - The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
        - The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
        It can be shown that there are no other paths with a higher safeness factor.


        Constraints:

        1 <= grid.length == n <= 400
        grid[i].length == n
        grid[i][j] is either 0 or 1.
        There is at least one thief in the grid.
     */

    public int maximumSafenessFactor(List<List<Integer>> grid)
    {
        if(grid==null || grid.size()==0)
            return 0;
        int n=grid.size();
        int[][] mat=new int[n][n];
        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid.get(i).get(j)==1)
                {
                    q.offer(new int[]{i,j});
                    mat[i][j]=0;
                }
                else
                    mat[i][j]=-1;
            }
        }
        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                int val=mat[curr[0]][curr[1]];
                for(int[] d:dirs)
                {
                    int r=curr[0]+d[0];
                    int c=curr[1]+d[1];
                    if(r<0 || r>=n || c<0 || c>=n || mat[r][c]!=-1)
                        continue;
                    mat[r][c]=val+1;
                    q.offer(new int[]{r,c});
                }
            }
        }

        for(int[] i:mat)
            System.out.println(Arrays.toString(i));

        int lo=0, hi=0, res=-1;
        for(int[] i:mat)
            for(int j:i)
                hi=Math.max(j,hi);

        while(lo<=hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (helper(mat, mid))
            {
                res = mid;
                lo = mid + 1;
            }
            else
            {
                hi = mid - 1;
            }
        }
        return res;
    }

    public boolean helper(int[][] grid, int val)
    {
        int n = grid.length;
        if(grid[0][0] < val || grid[n - 1][n - 1] < val)
            return false;

        Queue<int[]> traversalQueue = new LinkedList<>();
        traversalQueue.add(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while(!traversalQueue.isEmpty())
        {
            int[] curr = traversalQueue.poll();
            if(curr[0] == n - 1 && curr[1] == n - 1)
                return true;
            int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
            for(int[] d : dirs)
            {
                int r = curr[0] + d[0];
                int c = curr[1] + d[1];

                if(r<0 || r>=n || c<0 || c>=n || grid[r][c]<val || visited[r][c])
                    continue;
                visited[r][c] = true;
                traversalQueue.add(new int[]{r, c});
            }
        }
        return false;
    }





    public static void main(String[] args)
    {
        SafestPathInGrid obj=new SafestPathInGrid();
        List<List<Integer>> grid=new ArrayList<>();
        grid.add(Arrays.asList(0,0,0,1));
        grid.add(Arrays.asList(0,0,0,0));
        grid.add(Arrays.asList(0,0,0,0));
        grid.add(Arrays.asList(1,0,0,0));

        List<List<Integer>> grid2=new ArrayList<>();
        grid2.add(Arrays.asList(0,1,1));
        grid2.add(Arrays.asList(0,1,1));
        grid2.add(Arrays.asList(1,1,0));

        System.out.println(obj.maximumSafenessFactor(grid2));
    }
}
