import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TheMaze
{
    /*
    There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

    Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

    The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



    Example 1:

    Input 1: a maze represented by a 2D array

    0 0 1 0 0
    0 0 0 0 0
    0 0 0 1 0
    1 1 0 1 1
    0 0 0 0 0

    Input 2: start coordinate (rowStart, colStart) = (0, 4)
    Input 3: destination coordinate (rowDest, colDest) = (4, 4)

    Output: 12

    Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
                 The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

    Example 2:

    Input 1: a maze represented by a 2D array

    0 0 1 0 0
    0 0 0 0 0
    0 0 0 1 0
    1 1 0 1 1
    0 0 0 0 0

    Input 2: start coordinate (rowStart, colStart) = (0, 4)
    Input 3: destination coordinate (rowDest, colDest) = (3, 2)

    Output: -1

    Explanation: There is no way for the ball to stop at the destination.



    Note:

    There is only one ball and one destination in the maze.
    Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
     */

    public static int shortestDistance(int[][] maze,int[] start, int[] dest)
    {
        int[][] dist=new int[maze.length][maze[0].length];
        for(int[] row:dist)
            Arrays.fill(row,Integer.MAX_VALUE);
        dist[start[0]][start[1]]=0;
        bfs(maze,dist,start);
        return dist[dest[0]][dest[1]]==Integer.MAX_VALUE?-1:dist[dest[0]][dest[1]];
    }
    public static void dfs(int[][] maze,int[][] dist,int[] start)
    {
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] d:dir)
        {
            int nr = start[0] + d[0];
            int nc = start[1] + d[1];
            int count = 0;
            while (nr >= 0 && nr < maze.length && nc >= 0 && nc < maze[0].length && maze[nr][nc] != 1)
            {
                nr += d[0];
                nc += d[1];
                count++;
            }
            if (dist[start[0]][start[1]] + count < dist[nr - d[0]][nc - d[1]])
            {
                dist[nr - d[0]][nc - d[1]] = dist[start[0]][start[1]] + count;
                dfs(maze, dist, new int[]{nr - d[0], nc - d[1]});
            }
        }
    }
    public static void bfs(int[][] maze,int[][] dist,int[] start)
    {
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int[]> q=new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int curr[]=q.poll();
                for(int[] d:dir)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    int count=0;
                    while(nr>=0 && nr<maze.length && nc>=0 && nc<maze[0].length && maze[nr][nc]!=1)
                    {
                        nr+=d[0];
                        nc+=d[1];
                        count++;
                    }
                    if(dist[curr[0]][curr[1]]+count<dist[nr-d[0]][nc-d[1]])
                    {
                        dist[nr-d[0]][nc-d[1]]=dist[curr[0]][curr[1]]+count;
                        q.offer(new int[]{nr-d[0],nc-d[1]});
                    }
                }
            }
        }
    }
    public static void main(String[] args)
    {
        int[][] maze={{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int[] start={0,4};
        int[] dest={4,4};
        System.out.println(shortestDistance(maze,start,dest));
        System.out.println("3 5 + 2".trim().replaceAll(" ",""));
    }
}

