public class
SudokuSolver
{
    /*
    Write a program to solve a Sudoku puzzle by filling the empty cells.

    A sudoku solution must satisfy all of the following rules:

    Each of the digits 1-9 must occur exactly once in each row.
    Each of the digits 1-9 must occur exactly once in each column.
    Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
    Empty cells are indicated by the character '.'.
     */
    public void solve(char[][] board)
    {
        if(board==null || board.length==0)
            return;
        helper(board);
    }
    public boolean helper(char[][] board)
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]=='.')
                {
                    for(char c='1';c<='9';c++)
                    {
                        if(canUse(board,i,j,c))
                        {
                            board[i][j]=c;
                            if(helper(board))
                                return true;
                            else
                                board[i][j]='.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public boolean canUse(char[][] board, int i, int j, char c)
    {
        for(int k=0;k<board.length;k++)
        {
            if(board[k][j]==c)
                return false;
            if(board[i][k]==c)
                return false;
            if(board[3*(i/3)+k/3][3*(j/3)+k%3]==c)
                return false;
        }
        return true;
    }
    public static void main(String[] args)
    {
        char board[][]={{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        SudokuSolver obj=new SudokuSolver();
        obj.solve(board);
        for(char[] row:board)
        {
            for(char d:row)
                System.out.print(d+",");
            System.out.println();
        }
    }
}

