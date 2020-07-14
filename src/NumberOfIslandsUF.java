public class NumberOfIslandsUF
{
    /*
    UF:
    Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    Input:
    11110
    11010
    11000
    00000

    Output: 1
    Example 2:

    Input:
    11000
    11000
    00100
    00011

    Output: 3
     */

    public int find(int[] parent, int i)
    {
        if(parent[i]!=i)
            parent[i]=find(parent,parent[i]);
        return parent[i];
    }


    public int union(int[] parent, int[] rank, int i, int j, int count)
    {
        int irep=find(parent,i);
        int jrep=find(parent,j);

        int iRank=rank[irep];
        int jRank=rank[jrep];

        if(irep!=jrep)
        {
            if(iRank<jRank)
                parent[irep]=jrep;
            else if(iRank>jRank)
                parent[jrep]=irep;
            else
            {
                parent[irep]=jrep;
                rank[jrep]=1;
            }
            return count-1;
        }
        return count;
    }
    public int numIslands(char[][] grid)
    {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        int n=grid.length, m=grid[0].length;

        int count=0;

        int[] parent=new int[n*m];
        int[] rank=new int[n*m];

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j]=='1')
                {
                    parent[i*m+j]=i*m+j;
                    count++;
                }
                rank[i*m+j]=0;
            }
        }


        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j]=='1')
                {
                    grid[i][j]='0';
                    if(i-1>=0 && grid[i-1][j]=='1')
                        count=union(parent,rank,i*m+j,(i-1)*m+j,count);
                    if(i+1<n && grid[i+1][j]=='1')
                        count=union(parent,rank,i*m+j,(i+1)*m+j,count);
                    if(j-1>=0 && grid[i][j-1]=='1')
                        count=union(parent,rank,i*m+j,i*m+j-1,count);
                    if(j+1<m && grid[i][j+1]=='1')
                        count=union(parent,rank,i*m+j,i*m+j+1,count);

                }
            }
        }
        return count;
    }



    public static void main(String[] args)
    {
        NumberOfIslandsUF obj=new NumberOfIslandsUF();
        System.out.println(obj.numIslands(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}}));

        System.out.println(obj.numIslands(new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}}));

        System.out.println(obj.numIslands(new char[][]{{'1'},{'1'}}));

        System.out.println(obj.numIslands(new char[][]{{'1','1','0'},{'1','0','1'},{'0','1','1'}}));
    }
}
