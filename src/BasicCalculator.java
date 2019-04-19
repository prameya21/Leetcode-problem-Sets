import java.util.Stack;

public class BasicCalculator
{
    /*
    Implement a basic calculator to evaluate a simple expression string.

    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

    Example 1:

    Input: "1 + 1"
    Output: 2
    Example 2:

    Input: " 2-1 + 2 "
    Output: 3
    Example 3:

    Input: "(1+(4+5+2)-3)+(6+8)"
    Output: 23
     */
    public static int calculate(String s)
    {
        Stack<Integer> st=new Stack<>();
        int result=0,sign=1;
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(c>='0' && c<='9')
            {
                int j=i;
                while(i+1<s.length() && (s.charAt(i+1)>='0' && s.charAt(i+1)<=9))
                    i++;
                int val=Integer.parseInt(s.substring(j,i+1));
                result+=sign*val;
            }
            else if(c=='+')
                sign=1;
            else if(c=='-')
                sign=-1;
            else if(c=='(')
            {
                st.push(result);
                st.push(sign);
                result=0;
                sign=1;
            }
            else
            {
                result=result*st.pop()+st.pop();
            }
        }
        return result;
    }
    public static void main(String[] args)
    {
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
