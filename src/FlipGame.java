import java.util.ArrayList;
import java.util.List;

public class FlipGame
{
    /*
        You are playing a Flip Game with your friend.

        You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other person will be the winner.

        Return all possible states of the string currentState after one valid move. You may return the answer in any order. If there is no valid move, return an empty list [].



        Example 1:

        Input: currentState = "++++"
        Output: ["--++","+--+","++--"]
        Example 2:

        Input: currentState = "+"
        Output: []
     */
    public List<String> generatePossibleNextMoves(String currentState)
    {
        if(currentState==null)
            return null;
        char[] c=currentState.toCharArray();
        List<String> res=new ArrayList<>();
        for(int i=0;i<c.length;i++)
        {
            if(i+1<c.length && c[i]==c[i+1] && c[i]=='+')
            {
                c[i]='-';
                c[i+1]=c[i];
                res.add(new String(c));
                c[i]='+';
                c[i+1]=c[i];
            }
        }
        return res;
    }
}
