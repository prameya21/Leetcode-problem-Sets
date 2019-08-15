import java.util.Arrays;

public class RegionCutBySlashes
{
    /*
    In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.

    (Note that backslash characters are escaped, so a \ is represented as "\\".)

    Return the number of regions.



    Example 1:

    Input:
    [
      " /",
      "/ "
    ]
    Output: 2
    Explanation: The 2x2 grid is as follows:

    Example 2:

    Input:
    [
      " /",
      "  "
    ]
    Output: 1
    Explanation: The 2x2 grid is as follows:

    Example 3:

    Input:
    [
      "\\/",
      "/\\"
    ]
    Output: 4
    Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
    The 2x2 grid is as follows:

    Example 4:

    Input:
    [
      "/\\",
      "\\/"
    ]
    Output: 5
    Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
    The 2x2 grid is as follows:

    Example 5:

    Input:
    [
      "//",
      "/ "
    ]
    Output: 3
    Explanation: The 2x2 grid is as follows:

     */

    public int regionsBySlashes(String[] grid)
    {
        int[][] map=new int[grid.length*3][grid.length*3];
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[i].length();j++)
            {
                if(grid[i].charAt(j)=='/')
                    map[3*i+2][3*j]=map[3*i+1][3*j+1]=map[3*i][3*j+2]=1;
                if(grid[i].charAt(j)=='\\')
                    map[3*i][3*j]=map[3*i+1][3*j+1]=map[3*i+2][3*j+2]=1;

            }
        }
        int ans=0;
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(map[i][j]==0)
                {
                    ans++;
                    dfs(map,i,j);
                }
            }
        }
        return ans;
    }
    public void dfs(int[][] map, int i, int j)
    {
        if(i<0 || i>=map.length || j<0 || j>=map[0].length || map[i][j]==1)
            return;
        map[i][j]=1;
        dfs(map,i+1,j);
        dfs(map,i-1,j);
        dfs(map,i,j+1);
        dfs(map,i,j-1);

    }
    public static void main(String[] args)
    {
        RegionCutBySlashes obj=new RegionCutBySlashes();
        System.out.println(obj.regionsBySlashes(new String[]{"\\/","/\\"}));
    }
}
