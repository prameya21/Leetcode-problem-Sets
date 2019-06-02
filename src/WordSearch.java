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
                if(word.charAt(0)==grid[i][j] && canForm(grid,i,j,word,0,new boolean[grid.length][grid[0].length]))
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
    public static void main(String[] args)
    {
        char[][] board={{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        //char[][] board={{'a'}};
        System.out.println(exist(board,"ABCESEEEFS"));
    }
}
