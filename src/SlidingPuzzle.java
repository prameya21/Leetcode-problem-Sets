import java.util.*;

public class SlidingPuzzle
{
    /*
        On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

        A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

        The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

        Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

        Examples:

        Input: board = [[1,2,3],[4,0,5]]
        Output: 1
        Explanation: Swap the 0 and the 5 in one move.
        Input: board = [[1,2,3],[5,4,0]]
        Output: -1
        Explanation: No number of moves will make the board solved.
        Input: board = [[4,1,2],[5,0,3]]
        Output: 5
        Explanation: 5 is the smallest number of moves that solves the board.
        An example path:
        After move 0: [[4,1,2],[5,0,3]]
        After move 1: [[4,1,2],[0,5,3]]
        After move 2: [[0,1,2],[4,5,3]]
        After move 3: [[1,0,2],[4,5,3]]
        After move 4: [[1,2,0],[4,5,3]]
        After move 5: [[1,2,3],[4,5,0]]
        Input: board = [[3,2,4],[1,5,0]]
        Output: 14
        Note:

        board will be a 2 x 3 array as described above.
        board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
     */


    public int slidingPuzzle(int[][] board)
    {
        if(board==null)
            return -1;
        if(board.length<2 && board[0].length<3)
            return -1;
        int x=-1,y=1;
        for(int i =0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]==0)
                {
                    x=i;
                    y=j;
                }
            }
        }
        if(x==-1 && y==-1)
            return -1;
        int[][] target={{1,2,3},{4,5,0}};
        Queue<int[][]> q=new LinkedList<>();
        q.offer(board);
        Set<String> visited=new HashSet<>();
        visited.add(Arrays.deepToString(board));
        int ctr=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[][] curr=q.poll();
                if(boardCompare(curr,target))
                    return ctr;
                List<int[][]> combinations=getCombinations(curr);
                for(int[][] nextBoard:combinations)
                {
                    if(visited.contains(Arrays.deepToString(nextBoard)))
                        continue;
                    q.offer(nextBoard);
                    visited.add(Arrays.deepToString(nextBoard));
                }
            }
            ctr++;
        }
        return -1;
    }

    public List<int[][]> getCombinations(int[][] board)
    {
        int r=-1,c=-1;
        for(int i =0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]==0)
                {
                    r=i;
                    c=j;
                }
            }
        }
        int[][] dirs={{0,1},{1,0},{-1,0},{0,-1}};
        List<int[][]> comb=new ArrayList<>();
        for(int[] d:dirs)
        {
            int nr=r+d[0];
            int nc=c+d[1];
            if(nr<0 || nr>=board.length || nc<0 || nc>=board[0].length)
                continue;
            int[][] temp=new int[board.length][board[0].length];
            for(int i=0;i<board.length;i++)
                temp[i]=Arrays.copyOf(board[i],board[i].length);
            temp[r][c]=temp[nr][nc];
            temp[nr][nc]=0;
            comb.add(temp);
        }
        return comb;
    }

    public boolean boardCompare(int[][] board, int[][] target)
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]!=target[i][j])
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args)
    {
        SlidingPuzzle obj=new SlidingPuzzle();
        System.out.print(obj.slidingPuzzle(new int[][]{{4,1,2},{5,0,3}}));
    }
}
