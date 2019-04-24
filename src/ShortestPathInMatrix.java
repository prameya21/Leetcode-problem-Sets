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
    public static void main(String[] args)
    {
        List<List<Integer>> lot=new ArrayList<>();
        lot.add(Arrays.asList(1,1,1,1));
        lot.add(Arrays.asList(1,0,0,1));
        lot.add(Arrays.asList(1,0,0,1));
        lot.add(Arrays.asList(1,1,9,1));
        System.out.println(shortestPath(4,4,lot));
    }
}
