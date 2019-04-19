import java.util.Stack;

public class ValidParenthesis
{
    /*
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Note that an empty string is also considered valid.

    Example 1:

    Input: "()"
    Output: true
    Example 2:

    Input: "()[]{}"
    Output: true
    Example 3:

    Input: "(]"
    Output: false
    Example 4:

    Input: "([)]"
    Output: false
    Example 5:

    Input: "{[]}"
    Output: true
     */
    public static boolean isValid(String str)
    {
        Stack<Character> st=new Stack<>();
        for(char c: str.toCharArray())
        {
            if(c=='{')
                st.push('}');
            else if(c=='(')
                st.push(')');
            else if(c=='[')
                st.push(']');
            else if(st.isEmpty() || st.pop()!=c)
                return false;
        }
        return st.isEmpty();
    }
    public static void main(String[] args)
    {
        System.out.println(isValid("{}()[]"));
    }
}
