import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslands2
{
    /*
    A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example:

    Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
    Output: [1,1,2,3]
    Explanation:

    Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

    0 0 0
    0 0 0
    0 0 0
    Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

    1 0 0
    0 0 0   Number of islands = 1
    0 0 0
    Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

    1 1 0
    0 0 0   Number of islands = 1
    0 0 0
    Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

    1 1 0
    0 0 1   Number of islands = 2
    0 0 0
    Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

    1 1 0
    0 0 1   Number of islands = 3
    0 1 0
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions)
    {
        List<Integer> res=new ArrayList<>();
        if(m<0 || n<0)
            return res;
        int[] roots=new int[m*n];
        Arrays.fill(roots,-1);
        int count=0;
        int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
        for(int[] p:positions)
        {
            int root=n*p[0]+p[1];
            if(root<0 || root>=roots.length)
                continue;
            if(roots[root]!=-1)
            {
                res.add(count);
                continue;
            }
            roots[root]=root;
            count++;
            for(int[] d:dir)
            {
                int x=p[0]+d[0];
                int y=p[1]+d[1];
                int newRoot=n*x+y;
                if(x<0 || x>=m || y<0 || y>=n || roots[newRoot]==-1)
                    continue;
                int parent=findIsland(roots,newRoot);
                if(root!=parent)
                {
                    roots[root]=parent;
                    root=parent;
                    count--;
                }

            }
            res.add(count);
        }
        return res;
    }

    public int findIsland(int[] roots, int newRoot)
    {
        while(roots[newRoot]!=newRoot)
        {
            roots[newRoot]=roots[roots[newRoot]];
            newRoot=roots[newRoot];
        }
        return newRoot;
    }

    public static void main(String[] args)
    {
        NumberOfIslands2 obj=new NumberOfIslands2();
        System.out.println(obj.numIslands2(3,3,new int[][]{{0,0},{0,1},{1,2},{2,1}}));
        System.out.println(obj.numIslands2(1,2,new int[][]{{0,1},{0,0}}));
    }


}
