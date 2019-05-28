import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPathInMatrix
{
    public static int shortestPath(int numRows,int numCols,List<List<Integer>> lot)
    {
        List<Integer> total=new ArrayList<>();
        boolean[][] visited=new boolean[numRows][numCols];
        dfs(numRows,numCols,visited,lot,total,0,0,0);
        int min=Integer.MAX_VALUE;
        System.out.println(total);
        for(int i:total)
            min=Math.min(min,i);
        return min;
    }
    public static void dfs(int numRows,int numCols,boolean[][] visited,List<List<Integer>> lot,List<Integer> total,int x,int y,int path)
    {
        if(x<0 || y<0 || x>=numRows || y>=numCols)
            return;
        if(visited[x][y] || lot.get(x).get(y)==0)
            return;
        visited[x][y]=true;
        if(lot.get(x).get(y)==9)
        {
            visited[x][y]=false;
            total.add(path+1);
            return;
        }
        dfs(numRows,numCols,visited,lot,total,x+1,y,path+1);
        dfs(numRows,numCols,visited,lot,total,x-1,y,path+1);
        dfs(numRows,numCols,visited,lot,total,x,y+1,path+1);
        dfs(numRows,numCols,visited,lot,total,x,y-1,path+1);
    }
    public static int sp(int numRows,int numCols,List<List<Integer>> lot)
    {
        int[][] dist=new int[numRows][numCols];
        for(int[] row :dist)
            Arrays.fill(row,Integer.MAX_VALUE);
        dist[0][0]=0;
        spDfs(lot,dist,numRows,numCols,0,0);
        int[] d=new int[2];
        for(int i=0;i<lot.size();i++)
        {
            for(int j=0;j<lot.get(i).size();j++)
            {
                if(lot.get(i).get(j)==9)
                {
                    d[0]=i;
                    d[1]=j;
                }
            }
        }
        return dist[d[0]][d[1]];
    }
    public static void spDfs(List<List<Integer>> lot, int[][] dist,int n, int m,int i,int j)
    {
        if(i<0 || i>=n || j<0 || j>=m || lot.get(i).get(j)==0)
            return;
        if(j+1<n && dist[i][j]+1<dist[i][j+1])
        {
            dist[i][j+1]=dist[i][j]+1;
            spDfs(lot,dist,n,m,i,j+1);
        }
        if(j-1>0 && dist[i][j]+1<dist[i][j-1])
        {
            dist[i][j-1]=dist[i][j]+1;
            spDfs(lot,dist,n,m,i,j-1);
        }
        if(i+1<m && dist[i][j]+1<dist[i+1][j])
        {
            dist[i+1][j]=dist[i][j]+1;
            spDfs(lot,dist,n,m,i+1,j);
        }
        if(i-1>0 && dist[i][j]+1<dist[i-1][j])
        {
            dist[i-1][j]=dist[i][j]+1;
            spDfs(lot,dist,n,m,i-1,j);
        }

    }
    public static void main(String[] args)
    {
        List<List<Integer>> lot=new ArrayList<>();
        lot.add(Arrays.asList(1,1,1,1));
        lot.add(Arrays.asList(1,0,0,1));
        lot.add(Arrays.asList(1,0,0,1));
        lot.add(Arrays.asList(1,1,9,1));
        System.out.println(sp(4,4,lot));
    }
}
