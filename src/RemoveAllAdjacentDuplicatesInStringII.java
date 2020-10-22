import java.util.*;
public class RemoveAllAdjacentDuplicatesInStringII
{
    /*
    Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

    We repeatedly make k duplicate removals on s until we no longer can.

    Return the final string after all such duplicate removals have been made.

    It is guaranteed that the answer is unique.



    Example 1:

    Input: s = "abcd", k = 2
    Output: "abcd"
    Explanation: There's nothing to delete.
    Example 2:

    Input: s = "deeedbbcccbdaa", k = 3
    Output: "aa"
    Explanation:
    First delete "eee" and "ccc", get "ddbbbdaa"
    Then delete "bbb", get "dddaa"
    Finally delete "ddd", get "aa"
    Example 3:

    Input: s = "pbbcggttciiippooaais", k = 2
    Output: "ps"
     */

    public String removeDuplicates(String s, int k)
    {
        if(s==null || s.length()==0)
            return "";
        Stack<Integer> st=new Stack<>();
        Stack<Character> temp=new Stack<>();
        for(char c:s.toCharArray())
        {
            if(!temp.isEmpty() && temp.peek()==c)
            {
                st.push(st.pop()+1);
                temp.push(c);
                if(st.peek()==k)
                {
                    int ctr=st.pop();
                    for(int i=0;i<ctr;i++)
                        temp.pop();
                }
            }
            else
            {
                temp.push(c);
                st.push(1);
            }
        }
        StringBuilder sb=new StringBuilder();
        for(char c:temp)
            sb.append(c);
        return sb.toString();
    }

    public static void main(String[] args)
    {
        RemoveAllAdjacentDuplicatesInStringII obj=new RemoveAllAdjacentDuplicatesInStringII();
        System.out.println(obj.removeDuplicates("abcd",2));
    }
}
