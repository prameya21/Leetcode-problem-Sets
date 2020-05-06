public class MinimumRemoveToMakeValidParanthesis
{
    /*
    Given a string s of '(' , ')' and lowercase English characters.

    Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

    Formally, a parentheses string is valid if and only if:

    It is the empty string, contains only lowercase characters, or
    It can be written as AB (A concatenated with B), where A and B are valid strings, or
    It can be written as (A), where A is a valid string.


    Example 1:

    Input: s = "lee(t(c)o)de)"
    Output: "lee(t(c)o)de"
    Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
    Example 2:

    Input: s = "a)b(c)d"
    Output: "ab(c)d"
    Example 3:

    Input: s = "))(("
    Output: ""
    Explanation: An empty string is also valid.
    Example 4:

    Input: s = "(a(b(c)d)"
    Output: "a(b(c)d)"
     */

    public String minRemoveToMakeValid(String s)
    {
        if(s==null || s.isEmpty())
            return "";
        int left=0,right=0;
        for(char c:s.toCharArray())
        {
            if(c=='(')
                left++;
            else if(c==')')
            {
                if(left>0)
                    left--;
                else
                    right++;
            }
        }
        StringBuilder sb=new StringBuilder(s);
        int i=0;
        while(i<sb.length())
        {
            if(sb.charAt(i)==')' && right>0)
            {
                sb.deleteCharAt(i);
                right--;
            }
            else
                i++;
        }
        i=sb.length()-1;
        while(i>=0)
        {
            if(sb.charAt(i)=='(' && left>0)
            {
                sb.deleteCharAt(i);
                left--;
            }
            i--;
        }
        return sb.toString();
    }


    public static void main(String[] args)
    {
        MinimumRemoveToMakeValidParanthesis obj=new MinimumRemoveToMakeValidParanthesis();
        System.out.println(obj.minRemoveToMakeValid("())()((("));
    }
}
