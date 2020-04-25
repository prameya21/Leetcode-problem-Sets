public class WordSearch
{
    /*
    Given a 2D board and a word, find if the word exists in the grid.

    The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

    Example:

    board =
    [
      ['A','B','C','E'],
      ['S','F','C','S'],
      ['A','D','E','E']
    ]

    Given word = "ABCCED", return true.
    Given word = "SEE", return true.
    Given word = "ABCB", return false.
     */
    public static boolean exist(char[][] grid,String word)
    {
        //boolean[][] visited=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(word.charAt(0)==grid[i][j] && exist(grid,word.toCharArray(),i,j,0,new boolean[grid.length][grid[0].length]))
                    return true;
            }
        }
        return false;
    }
    public static boolean canForm(char[][] grid, int i,int j,String word,int len,boolean[][] visited)
    {
        if(len==word.length())
            return true;
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || visited[i][j] || grid[i][j]!=word.charAt(len))
            return false;
        if(grid[i][j]==word.charAt(len))
        {
            visited[i][j]=true;
            if(canForm(grid,i-1,j,word,len+1,visited) || canForm(grid,i+1,j,word,len+1,visited) || canForm(grid,i,j-1,word,len+1,visited) || canForm(grid,i,j+1,word,len+1,visited))
                return true;
            visited[i][j]=false;
        }
        return false;
    }
    public static boolean exist(char[][] board, char[] ch, int i, int j, int start, boolean[][] visited)
    {
        if(i<0 || i>=board.length || j< 0 || j>=board[0].length || start>ch.length || board[i][j]!=ch[start] || visited[i][j])
            return false;
        if(start==ch.length-1 && board[i][j]==ch[start])
            return true;
        visited[i][j]=true;
        if(exist(board,ch,i+1,j,start+1,visited) || exist(board,ch,i-1,j,start+1,visited) || exist(board,ch,i,j+1,start+1,visited) || exist(board,ch,i,j-1,start+1,visited))
            return true;
        visited[i][j]=false;
        return false;
    }

    public static void main(String[] args)
    {
        char[][] board={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        //char[][] board={{'a'}};
        System.out.println(exist(board,"ABCB"));
    }
}
