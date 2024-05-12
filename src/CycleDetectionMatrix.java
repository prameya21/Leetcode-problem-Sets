public class CycleDetectionMatrix
{
    /*
        Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
        A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
        Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
        Return true if any cycle of the same value exists in grid, otherwise, return false.

        Example 1:
        Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
        Output: true
        Explanation: There are two valid cycles shown in different colors in the image below:

        Example 2:
        Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
        Output: true
        Explanation: There is only one valid cycle highlighted in the image below:

        Example 3:
        Input: grid = [["a","b","b"],
                       ["b","z","b"],
                       ["b","b","a"]]
        Output: false
     */
    public boolean containsCycle(char[][] grid)
    {
        if(grid==null || grid.length==0)
            return false;
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
                if(!visited[i][j] && helper(grid,visited,grid[i][j],i,j,-1,-1))
                    return true;

        return false;
    }

    public boolean helper(char[][] grid, boolean[][] visited,char ch, int i, int j,int parentI, int parentJ)
    {
        if(visited[i][j])
            return true;
        visited[i][j]=true;
        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        boolean res=false;
        for(int[] d:dirs)
        {
            int r=i+d[0];
            int c=j+d[1];
            if(r<0 || c<0 || r>=grid.length || c>=grid[0].length || grid[r][c]!=ch)
                continue;
            if(r==parentI && c==parentJ)
                continue;
            res|=helper(grid,visited,ch,r,c,i,j);
        }
        return res;
    }

    public static void main(String[] args)
    {
        CycleDetectionMatrix obj=new CycleDetectionMatrix();
        System.out.println(obj.containsCycle(new char[][]{{'a','b','b'},{'b','z','b'},{'b','b','a'}}));
    }

}
