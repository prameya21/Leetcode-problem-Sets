import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DP
{
    public static int countWays(int n)
    {
        if(n<0)
            return 0;
        if(n==1|| n==0)
            return 1;
        return countWays(n-3)+countWays(n-2)+countWays(n-1);
    }
    public static int countWaysMemo(int n, int[] arr)
    {
        if(n<0)
            return 0;
        if(n==0)
            return 1;
        if(arr[n]!=-1)
            return arr[n];
        arr[n]=countWaysMemo(n-1,arr)+countWaysMemo(n-2,arr)+countWaysMemo(n-3,arr);
        return arr[n];
    }
    public static void findPath(int map[][], ArrayList<Point> path, int[][] visited,int row,int col)
    {
       if(row>=map.length || col>=map[0].length)
           return;
       if(map[row][col]==1 || visited[row][col]==1)
           return;
       Point p=new Point(row,col);
       boolean isATBottom=row==map.length-1&& col==map[0].length-1;
       path.add(p);
       visited[row][col]=1;
       if(isATBottom)
       {
           System.out.println("Bottom found");
           System.out.println(path);
           return;
       }
       findPath(map,path,visited,row+1,col);
       findPath(map,path,visited,row,col+1);
       // path.remove(path.size()-1);
    }
    public static int magicIndex(int arr[],int start,int end)
    {
        if(end<start)
            return -1;
        int midIndex=start+end;
        midIndex=midIndex/2;
        if(arr[midIndex]==midIndex)
            return midIndex;
        if(arr[midIndex]>midIndex)
            return magicIndex(arr,start,midIndex-1);
        else if(arr[midIndex]<midIndex)
            return(magicIndex(arr,midIndex+1,end));
        return -1;
    }
    public static int magicIndexDup(int arr[],int start,int end)
    {
        if(end<start)
            return -1;
        int mid=start+end;
        mid=mid/2;
        if(arr[mid]==mid)
            return mid;
        int leftIndex=Math.min(arr[mid],mid-1);
        int left=magicIndexDup(arr,start,leftIndex);
        if(left>0)
            return left;
        int rightIndex=Math.max(arr[mid],mid+1);
        int right=magicIndexDup(arr,rightIndex,end);
        return right;

    }
    public static int countIslands(int[][] map, int[][] visited)
    {
        if(map==null)
            return 0;
        int count=0;
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(map[i][j]==1 && visited[i][j]==0)
                {
                    dfsUtil(map,visited,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    public static void dfsUtil(int[][] map,int[][] visited,int row,int col)
    {
        if(row<0 || col<0 || row>=map.length || col>=map[0].length)
            return;
        if(visited[row][col]!=0)
            return;
        if(map[row][col]==0)
            return;
        visited[row][col]=1;
        dfsUtil(map,visited,row-1,col);
        dfsUtil(map,visited,row+1,col);
        dfsUtil(map,visited,row,col-1);
        dfsUtil(map,visited,row,col+1);
    }
    public static void main(String args[])
    {
        System.out.println(countWays(10));
        int n=10;
        int[] arr=new int[n+1];
        Arrays.fill(arr,-1);
        System.out.println("Memoized: "+countWaysMemo(n,arr));
        int ROW=5;
        int COL=5;
        int maze[][]={{0,0,0,0,1},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{1,0,0,0,0}};
        int visited[][]=new int[ROW][COL];
        Arrays.fill(visited[0],0);
        Arrays.fill(visited[1],0);
        Arrays.fill(visited[2],0);
        Arrays.fill(visited[3],0);
        Arrays.fill(visited[4],0);
        ArrayList<Point> path=new ArrayList<>();
        findPath(maze,path,visited,0,0);
        //System.out.println(path);
        int arr1[]={-10,-9,-8,-7,4,8,9,10};
        System.out.println("Magic Index= "+magicIndex(arr1,0,arr1.length-1));
        int[][] island={{1,1,0,0},{1,0,0,0},{0,0,0,0},{1,1,0,1}};
        int[][] v={{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        System.out.println("The island count is"+countIslands(island,v));
        int arr3[]={-10,-5,2,2,2,3,4,7,9,12,13};
        System.out.println("Magic Index= "+magicIndexDup(arr3,0,arr3.length-1));





    }
}
