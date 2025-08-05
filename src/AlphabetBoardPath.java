public class AlphabetBoardPath
{
    /*
    1138
        On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
        Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
        We may make the following moves:

        'U' moves our position up one row, if the position exists on the board;
        'D' moves our position down one row, if the position exists on the board;
        'L' moves our position left one column, if the position exists on the board;
        'R' moves our position right one column, if the position exists on the board;
        '!' adds the character board[r][c] at our current position (r, c) to the answer.
        (Here, the only positions that exist on the board are positions with letters on them.)

        Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.



        Example 1:

        Input: target = "leet"
        Output: "DDR!UURRR!!DDD!"
        Example 2:

        Input: target = "code"
        Output: "RR!DDRR!UUL!R!"
     */

    public String alphabetBoardPath(String target)
    {
        if(target==null)
            return "";
        int[][] board={{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{3,4},{4,0},{4,1},{4,2},{4,3},{4,4},{5,0}};
        StringBuilder sb=new StringBuilder();
        int r=0, c=0;
        for(char ch:target.toCharArray())
        {
            int[] pos=board[ch-'a'];
            int nr=pos[0], nc=pos[1];
            while(r<nr)
            {
                sb.append("D");
                r++;
            }
            while(c<nc)
            {
                sb.append("R");
                c++;
            }
            while(c>nc)
            {
                sb.append("L");
                c--;
            }
            while(r>nr)
            {
                sb.append("U");
                r--;
            }
            sb.append("!");
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        AlphabetBoardPath obj=new AlphabetBoardPath();
        System.out.println(obj.alphabetBoardPath("leet"));
        System.out.println(obj.alphabetBoardPath("code"));
    }
}
