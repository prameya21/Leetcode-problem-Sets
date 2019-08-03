import java.util.LinkedList;
import java.util.Queue;

public class LargestConnectedGroupArray
{
    /*
    Given an array of N*M dimensions, find the number with the maximum connected components
    For Example
    Array={1,2,3
           1,1,4
           1,4,4}
    Answer=1
     */
    public int countConnectedComponents(int[][] arr)
    {
        int max=Integer.MIN_VALUE,ans=0;
        boolean[][] visited=new boolean[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                if(!visited[i][j])
                {
                    //int val=dfs(arr,visited,i,j,arr[i][j]);
                    int val=bfs(arr,visited,i,j);
                    if(val>max)
                    {
                        max=val;
                        ans=arr[i][j];
                    }
                }
            }
        }
        return ans;
    }
    public int bfs(int[][] arr,boolean[][] visited,int i, int j)
    {
        Queue<int[]> q=new LinkedList<>();
        int[][] dir={{0,1},{1,0},{0,-1},{-1,0}};
        q.offer(new int[]{i,j});
        visited[i][j]=true;
        int cnt=1;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int k=0;k<size;k++)
            {
                int[] curr=q.poll();
                for(int[] d:dir)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    if(nr<0 || nr>=arr.length || nc<0 || nc>=arr[0].length || visited[nr][nc] || arr[nr][nc]!=arr[curr[0]][curr[1]])
                        continue;
                    visited[nr][nc]=true;
                    q.offer(new int[]{nr,nc});
                }
            }
            cnt++;
        }
        return cnt;
    }
    public int dfs(int[][] arr,boolean[][] visited,int i,int j,int val)
    {
        if(i<0 || i>=arr.length || j<0 || j>=arr[0].length || visited[i][j] || arr[i][j]!=val)
            return 0;
        int max=1;
        visited[i][j]=true;
        max=Math.max(max,dfs(arr,visited,i+1,j,arr[i][j]));
        max=Math.max(max,dfs(arr,visited,i-1,j,arr[i][j]));
        max=Math.max(max,dfs(arr,visited,i,j+1,arr[i][j]));
        max=Math.max(max,dfs(arr,visited,i,j-1,arr[i][j]));
        return max+1;
    }

    public static void main(String[] args)
    {
        LargestConnectedGroupArray obj=new LargestConnectedGroupArray();
        System.out.println(obj.countConnectedComponents(new int[][]{{1,2,3},{1,1,4},{1,4,4}}));
    }
}
