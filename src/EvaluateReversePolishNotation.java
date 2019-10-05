import java.util.Stack;

public class EvaluateReversePolishNotation
{
    /*
    Evaluate the value of an arithmetic expression in Reverse Polish Notation.

    Valid operators are +, -, *, /. Each operand may be an integer or another expression.

    Note:

    Division between two integers should truncate toward zero.
    The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
    Example 1:

    Input: ["2", "1", "+", "3", "*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9
    Example 2:

    Input: ["4", "13", "5", "/", "+"]
    Output: 6
    Explanation: (4 + (13 / 5)) = 6
    Example 3:

    Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
    Output: 22
    Explanation:
      ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
    = ((10 * (6 / (12 * -11))) + 17) + 5
    = ((10 * (6 / -132)) + 17) + 5
    = ((10 * 0) + 17) + 5
    = (0 + 17) + 5
    = 17 + 5
    = 22
     */
    public int evalRPN(String[] tokens)
    {
        Stack<Integer> st=new Stack<>();
        for(String s:tokens)
        {
            if(s.equals("+"))
                st.push(st.pop()+st.pop());
            else if(s.equals("*"))
                st.push(st.pop()*st.pop());
            else if(s.equals("-"))
            {
                int val1=st.pop();
                int val2=st.pop();
                st.push(val2-val1);
            }
            else if(s.equals("/"))
            {
                int val1=st.pop();
                int val2=st.pop();
                st.push(val2/val1);
            }
            else
                st.push(Integer.parseInt(s));
        }
        return st.pop();
    }
    public static void main(String[] args)
    {
        EvaluateReversePolishNotation obj=new EvaluateReversePolishNotation();
        System.out.println(obj.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        System.out.println(obj.evalRPN(new String[]{"2","1","+","3","*"}));
    }
}
