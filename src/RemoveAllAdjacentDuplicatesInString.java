import java.util.*;

public class RemoveAllAdjacentDuplicatesInString
{
    /*
    Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

    We repeatedly make duplicate removals on S until we no longer can.

    Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.



    Example 1:

    Input: "abbaca"
    Output: "ca"
    Explanation:
    For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
     */


    public String removeDuplicates(String S)
    {
        if(S==null || S.length()==0)
            return "";
        Stack<Character> st=new Stack<>();
        int i=0;
        while(i<S.length())
        {
            if(!st.isEmpty() && st.peek()==S.charAt(i))
                st.pop();
            else
                st.push(S.charAt(i));
            i++;
        }
        StringBuilder sb=new StringBuilder();
        for(Character c:st)
            sb.append(c);
        return sb.toString();
    }

    public static void main(String[] args)
    {
        RemoveAllAdjacentDuplicatesInString obj=new RemoveAllAdjacentDuplicatesInString();
        System.out.println(obj.removeDuplicates("abbaca"));
    }
}
