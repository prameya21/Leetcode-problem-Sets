public class TheMaze2
{
    /*
    There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

    Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

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

    Output: true

    Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

    Example 2:

    Input 1: a maze represented by a 2D array

    0 0 1 0 0
    0 0 0 0 0
    0 0 0 1 0
    1 1 0 1 1
    0 0 0 0 0

    Input 2: start coordinate (rowStart, colStart) = (0, 4)
    Input 3: destination coordinate (rowDest, colDest) = (3, 2)

    Output: false

    Explanation: There is no way for the ball to stop at the destination.
     */


    public boolean hasPath(int[][] maze, int[] start, int[] destination)
    {
        if(maze==null || maze.length==0)
            return false;
        int i=start[0], j=start[1];
        int r=destination[0], c=destination[1];
        return dfs(maze,i,j,r,c);
    }

    public boolean dfs(int[][] maze, int i, int j, int r, int c)
    {
        if(i==r && j==c)
            return true;
        if(maze[i][j]==2)
            return false;
        maze[i][j]=2;

        int k=j;
        while(k-1>=0 && maze[i][k-1]!=1)
            k--;
        if(dfs(maze,i,k,r,c))
            return true;

        k=j;
        while(k+1<maze[0].length && maze[i][k+1]!=1)
            k++;
        if(dfs(maze,i,k,r,c))
            return true;

        k=i;
        while(k-1>=0 && maze[k-1][j]!=1)
            k--;
        if(dfs(maze,k,j,r,c))
            return true;

        k=i;
        while(k+1<maze.length && maze[k+1][j]!=1)
            k++;
        if(dfs(maze,k,j,r,c))
            return true;
        return false;
    }

    public static void main(String[] args)
    {
        TheMaze2 obj=new TheMaze2();
        int[][] maze={{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int[] start={0,4};
        int[] dest={3,2};
        System.out.println(obj.hasPath(maze,start,dest));
    }
}
