import java.util.HashMap;
import java.util.Map;

public class KnightProbabilityInChessBoard
{
    /*
    On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

    A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

    Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

    The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.



    Example:

    Input: 3, 2, 0, 0
    Output: 0.0625
    Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
    From each of those positions, there are also two moves that will keep the knight on the board.
    The total probability the knight stays on the board is 0.0625.


    Note:

    N will be between 1 and 25.
    K will be between 0 and 100.
    The knight always initially starts on the board.
     */

    int[][] dirs={{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    public double knightProbability(int N, int K, int r, int c)
    {
        if(N==0 || K==0)
            return 0;
        return dfs(N,K,r,c,new HashMap<>());
    }

    public double dfs(int N, int K, int i, int j, Map<String,Double> memo)
    {
        if(i<0 || i>=N || j<0 || j>=N || K<0)
            return 0;
        if(K==0)
            return 1;
        String key=i+","+j+","+K;
        if(memo.containsKey(key))
            return memo.get(key);
        double val=0;
        for(int[] d:dirs)
        {
            int nr=i+d[0];
            int nc=j+d[1];
            val+=dfs(N,K-1,nr,nc,memo);
        }
        memo.put(key,val*0.125);
        return memo.get(key);
    }

    public static void main(String[] args)
    {
        KnightProbabilityInChessBoard obj=new KnightProbabilityInChessBoard();
        System.out.println(obj.knightProbability(3,2,0,0));
    }
}
