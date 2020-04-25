public class ValidTicTacToeState
{
    /*
        A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

        The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

        Here are the rules of Tic-Tac-Toe:

        Players take turns placing characters into empty squares (" ").
        The first player always places "X" characters, while the second player always places "O" characters.
        "X" and "O" characters are always placed into empty squares, never filled ones.
        The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
        The game also ends if all squares are non-empty.
        No more moves can be played if the game is over.
        Example 1:
        Input: board = ["O  ", "   ", "   "]
        Output: false
        Explanation: The first player always plays "X".

        Example 2:
        Input: board = ["XOX", " X ", "   "]
        Output: false
        Explanation: Players take turns making moves.

        Example 3:
        Input: board = ["XXX", "   ", "OOO"]
        Output: false

        Example 4:
        Input: board = ["XOX", "O O", "XOX"]
        Output: true
        Note:

        board is a length-3 array of strings, where each string board[i] has length 3.
        Each board[i][j] is a character in the set {" ", "X", "O"}.
     */

    public boolean validTicTacToe(String[] board)
{
    if(board==null || board.length==0)
        return false;
    int[] count=count(board);
    int countCross=count[0];
    int countZero=count[1];

    if(countZero>countCross)
        return false;
    if(countCross-countZero>1)
        return false;
    if(win(board,'X') && countZero!=countCross-1)
        return false;
    if(win(board,'O') && countZero!=countCross)
        return false;
    return true;
}
    public boolean win(String[] board, char c)
    {
        for(int i=0;i<3;i++)
        {
            if(board[i].charAt(0)==c && board[i].charAt(1)==c && board[i].charAt(2)==c)
                return true;
            if(board[0].charAt(i)==c && board[1].charAt(i)==c && board[2].charAt(i)==c)
                return true;
        }
        if(board[0].charAt(0)==c && board[1].charAt(1)==c && board[2].charAt(2)==c)
            return true;
        if(board[2].charAt(0)==c && board[1].charAt(1)==c && board[0].charAt(2)==c)
            return true;
        return false;
    }
    public int[] count(String[] board)
    {
        int x=0,z=0;
        for(String s:board)
        {
            for(char c:s.toCharArray())
            {
                if(c=='O')
                    z++;
                else if(c=='X')
                    x++;
            }
        }
        return new int[]{x,z};
    }
    public static void main(String[] args)
    {
        ValidTicTacToeState obj=new ValidTicTacToeState();
        System.out.println(obj.validTicTacToe(new String[]{"XXX","OOX","OOX"}));
    }
}
