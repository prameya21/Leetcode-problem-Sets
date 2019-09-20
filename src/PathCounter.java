import java.util.LinkedList;
import java.util.Queue;


public class PathCounter
{
    /*
    You are at position [0, 0] in maze NxN and you can only move in one of the four cardinal directions (i.e. North, East, South, West).
    Return the minimal number of steps to exit position [N-1, N-1] if it is possible to reach the exit from the starting position.
    Otherwise, return false in JavaScript/Python and -1 in C++/C#/Java.

    Empty positions are marked .. Walls are marked W. Start and exit positions are guaranteed to be empty in all test cases.
     */

    public int pathFinder(String maze)
    {

        System.out.println(maze);
        String[] board=maze.split("\n");
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{0,0});
        int count=0;
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        boolean[][] visited=new boolean[board.length][board[0].length()];
        visited[0][0]=true;

        while(!q.isEmpty())
        {

            int size=q.size();
            for(int i=0;i<size;i++)
            {

                int[] curr=q.poll();
                int r=curr[0];
                int c=curr[1];
                if(r==board.length-1 && c==board[0].length()-1)
                    return count;
                for(int[] d:dir)
                {
                    int nr=r+d[0];
                    int nc=c+d[1];
                    if(nr<0 || nr>=board.length || nc<0 || nc>=board[0].length() || board[nr].charAt(nc)=='W' || visited[nr][nc])
                        continue;
                    q.offer(new int[]{nr,nc});
                    visited[nr][nc]=true;
                }
            }
            count++;
        }
        return -1;

    }
    public int pathFinderDFS(String maze)
    {
        String[] board=maze.split("\n");
        return dfs(board,0,0,new boolean[board.length][board[0].length()],0);
    }
    public int dfs(String[] board, int i, int j, boolean[][] visited,int cost)
    {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length() || visited[i][j] || board[i].charAt(j)=='W')
            return Integer.MAX_VALUE;
        if(i==board.length-1 && j==board[0].length()-1)
            return cost;
        int min=Integer.MAX_VALUE;
        visited[i][j]=true;
        min=Math.min(min,dfs(board,i+1,j,visited,cost+1));
        min=Math.min(min,dfs(board,i-1,j,visited,cost+1));
        min=Math.min(min,dfs(board,i,j+1,visited,cost+1));
        min=Math.min(min,dfs(board,i,j-1,visited,cost+1));
        return min;
    }
    public static void main(String[] args)
    {
        PathCounter obj=new PathCounter();
        String maze=".W.\n" +
                    ".W.\n" +
                    "...\n" +
                    ".W.\n" +
                    ".W.\n" +
                    "W..";
        System.out.println(obj.pathFinderDFS(maze));
    }
}
