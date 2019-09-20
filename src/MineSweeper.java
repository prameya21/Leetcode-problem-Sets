import java.util.LinkedList;
import java.util.Queue;

public class MineSweeper
{
    /*
    Let's play the minesweeper game (Wikipedia, online game)!

    You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

    Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

    If a mine ('M') is revealed, then the game is over - change it to 'X'.
    If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
    If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
    Return the board when no more squares will be revealed.


    Example 1:

    Input:

    [['E', 'E', 'E', 'E', 'E'],
     ['E', 'E', 'M', 'E', 'E'],
     ['E', 'E', 'E', 'E', 'E'],
     ['E', 'E', 'E', 'E', 'E']]

    Click : [3,0]

    Output:

    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'M', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]

    Explanation:

    Example 2:

    Input:

    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'M', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]

    Click : [1,2]

    Output:

    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'X', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]

    Explanation:



    Note:

    The range of the input matrix's height and width is [1,50].
    The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
    The input board won't be a stage when game is over (some mines have been revealed).
    For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
     */


    public char[][] updateBoard(char[][] board, int[] click)
    {
        int r=click[0];
        int c=click[1];
        if(board[r][c]=='M')
        {
            board[r][c]='X';
        }
        else
        {
            int mineCount=mine(board,r,c);
            if(mineCount>0)
            {
                board[r][c]=(char)(mineCount+'0');
            }
            else
            {
                board[r][c]='B';
                for(int i=r-1;i<=r+1;i++)
                {
                    for(int j=c-1;j<=c+1;j++)
                    {
                        if(i<0 || i>=board.length || j<0 || j>=board[0].length)
                            continue;
                        if(i==r && j==c)
                            continue;
                        if(board[i][j]=='E')
                            updateBoard(board,new int[]{i,j});
                    }
                }
            }
        }
        return board;
    }


    public char[][] updateBoardDFS(char[][] board,int[] click)
    {
        int r=click[0];
        int c=click[1];
        if(board[r][c]=='M')
            board[r][c]='X';
        else
        {
            dfs(board,r,c);
        }
        return board;
    }
    public void dfs(char[][] board,int i, int j)
    {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!='E')
            return;
        int count=mine(board,i,j);
        if(count>0)
        {
            board[i][j]=(char)(count+'0');
            return;
        }
        else
        {
            board[i][j]='B';
            dfs(board,i+1,j);
            dfs(board,i-1,j);
            dfs(board,i,j+1);
            dfs(board,i,j-1);
            dfs(board,i+1,j+1);
            dfs(board,i+1,j-1);
            dfs(board,i-1,j-1);
            dfs(board,i-1,j+1);
        }
    }

    //BFS is buggy. Refer to DFS
    public char[][] updateBoardBFS(char[][] board, int[] click)
    {
        int r=click[0];
        int c=click[1];
        if(board[r][c]=='M')
            board[r][c]='X';
        else
        {
            Queue<int[]> q=new LinkedList<>();
            q.offer(click);
            int[][] dir={{1,0},{0,1},{-1,0},{0,-1}};
            while(!q.isEmpty())
            {
                int size=q.size();
                for(int i=0;i<size;i++)
                {
                    int[] curr=q.poll();
                    int rc=curr[0];
                    int cc=curr[1];
                    int count=mine(board,rc,cc);
                    if(count>0)
                        board[rc][cc]=(char)(count+'0');
                    else
                    {
                        board[rc][cc]='B';
                        for(int[]d:dir)
                        {
                            int nr=rc+d[0];
                            int nc=cc+d[1];
                            if(nr<0 || nr>=board.length || nc<0 || nc>=board[0].length || board[nr][nc]!='E')
                                continue;
                            q.offer(new int[]{nr,nc});
                        }
                    }
                }
            }
        }
        return board;
    }



    public int mine(char[][] board, int r, int c)
    {
        int count=0;
        for(int i=r-1;i<=r+1;i++)
        {
            for(int j=c-1;j<=c+1;j++)
            {
                if(i<0 || i>=board.length || j<0 || j>=board[0].length)
                    continue;
                if(board[i][j]=='M')
                    count++;
            }
        }
        return count;
    }




    public static void main(String[] args)
    {
        MineSweeper obj=new MineSweeper();
        char[][] board={{'E', 'E', 'E', 'E', 'E'},{'E', 'E', 'M', 'E', 'E'},{'E', 'E', 'E', 'E', 'E'},{'E', 'E', 'E', 'E', 'E'}};
        char[][] res=obj.updateBoardBFS(board,new int[]{3,0});
        for(char[] ch:board)
        {
            for(char c:ch)
                System.out.print(c+",");
            System.out.println();
        }
    }




}
