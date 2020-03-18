public class CherryPickup
{
    /*
        In a N x N grid representing a field of cherries, each cell is one of three possible integers.

        0 means the cell is empty, so you can pass through;
        1 means the cell contains a cherry, that you can pick up and pass through;
        -1 means the cell contains a thorn that blocks your way.

        Your task is to collect maximum number of cherries possible by following the rules below:
        Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
        After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
        When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
        If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.

        Example 1:

        Input: grid =
        [[0, 1, -1],
         [1, 0, -1],
         [1, 1,  1]]
        Output: 5
        Explanation:
        The player started at (0, 0) and went down, down, right right to reach (2, 2).
        4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
        Then, the player went left, up, up, left to return home, picking up one more cherry.
        The total number of cherries picked up is 5, and this is the maximum possible.
        Note:

        grid is an N by N 2D array, with 1 <= N <= 50.
        Each grid[i][j] is an integer in the set {-1, 0, 1}.
        It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
     */

    public int cherryPickup(int[][] grid)
    {
        return Math.max(0,cherryPickup(grid,grid.length,0,0,grid.length-1,grid.length-1));
        //int N=grid.length;
        //return Math.max(0,cherryPickup2(grid,grid.length,0,0,0,0,new Integer[N][N][N]));

    }
    public int cherryPickup(int[][] grid, int n, int r1, int c1, int r2, int c2)
    {
        if(r1>=n || r2<0 || c1>=n || c2<0 || grid[r1][c1]==-1 || grid[r2][c2]==-1)
            return Integer.MIN_VALUE;
        if(r1==n-1 && c1==n-1)
            return grid[r1][c1];
        if(r2==0 && c2==0)
            return grid[r2][c2];
        int cherries=0;

        if(r1==r2 && c1==c2)
            cherries=grid[r1][c1];
        else
            cherries=grid[r1][c1]+grid[r2][c2];

        int val1=grid[r1][c1],val2=grid[r2][c2];
        grid[r1][c1]=0;
        grid[r2][c2]=0;
        cherries+=Math.max(Math.max(cherryPickup(grid,n,r1+1,c1,r2-1,c2),cherryPickup(grid,n,r1,c1+1,r2,c2-1)),Math.max(cherryPickup(grid,n,r1+1,c1,r2,c2-1),cherryPickup(grid,n,r1,c1+1,r2-1,c2)));
        grid[r1][c1]=val1;
        grid[r2][c2]=val2;
        return cherries;
    }

    public int cherryPickup(int[][] grid, int n, int r1, int c1, int r2, int c2,Integer[][][][] memo)
    {
        if(r1>=n || r2>=n || c1>=n || c2>=n || grid[r1][c1]==-1 || grid[r2][c2]==-1)
            return Integer.MIN_VALUE;
        if(memo[r1][c1][r2][c2]!=null)
            return memo[r1][c1][r2][c2];
        if(r1==n-1 && c1==n-1)
            return grid[r1][c1];
        if(r2==n-1 && c2==n-1)
            return grid[r2][c2];

        int cherries=0;
        if(r1==r2 && c1==c2)
            cherries=grid[r2][c2];
        else
            cherries=grid[r1][c1]+grid[r2][c2];
        cherries+=Math.max(Math.max(cherryPickup(grid,n,r1+1,c1,r2+1,c2,memo),cherryPickup(grid,n,r1,c1+1,r2,c2+1,memo)),Math.max(cherryPickup(grid,n,r1+1,c1,r2,c2+1,memo),cherryPickup(grid,n,r1,c1+1,r2+1,c2,memo)));
        memo[r1][c1][r2][c2]=cherries;
        return cherries;
    }
    public int cherryPickup2(int[][] grid, int n, int r1, int c1, int r2, int c2,Integer[][][] memo)
    {
        if(r1>=n || r2>=n || c1>=n || c2>=n || grid[r1][c1]==-1 || grid[r2][c2]==-1)
            return Integer.MIN_VALUE;
        if(memo[r1][c1][r2]!=null)
            return memo[r1][c1][r2];
        if(r1==n-1 && c1==n-1)
            return grid[r1][c1];
        if(r2==n-1 && c2==n-1)
            return grid[r2][c2];

        int cherries=0;
        if(r1==r2 && c1==c2)
            cherries=grid[r2][c2];
        else
            cherries=grid[r1][c1]+grid[r2][c2];
        cherries+=Math.max(Math.max(cherryPickup2(grid,n,r1+1,c1,r2+1,c2,memo),cherryPickup2(grid,n,r1,c1+1,r2,c2+1,memo)),Math.max(cherryPickup2(grid,n,r1+1,c1,r2,c2+1,memo),cherryPickup2(grid,n,r1,c1+1,r2+1,c2,memo)));
        memo[r1][c1][r2]=cherries;
        return cherries;
    }


    public static void main(String[] args)
    {
        CherryPickup obj=new CherryPickup();
        System.out.println(obj.cherryPickup(new int[][]{{0,1,-1},{1,0,-1},{1,1,1}}));
    }
}
