import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators
{
    /*
    Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

    Example 1:

    Input: num = "123", target = 6
    Output: ["1+2+3", "1*2*3"]
    Example 2:

    Input: num = "232", target = 8
    Output: ["2*3+2", "2+3*2"]
    Example 3:

    Input: num = "105", target = 5
    Output: ["1*0+5","10-5"]
    Example 4:

    Input: num = "00", target = 0
    Output: ["0+0", "0-0", "0*0"]
    Example 5:

    Input: num = "3456237490", target = 9191
    Output: []
     */

    public List<String> addOperators(String num, int target)
    {
        if(num==null || num.length()==0)
            return new ArrayList<>();
        List<String> result=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        helper(num,target,0,0,0, sb, result);
        return result;
    }

    public void helper(String num, int target, int idx, long total, long mult, StringBuilder sb, List<String> result)
    {
        if(idx==num.length())
        {
            if(target==total)
                result.add(sb.toString());
            return;
        }
        for(int i=idx;i<num.length();i++)
        {
            if(num.charAt(i)=='0' && i!=idx)
                break;
            long curr=Long.parseLong(num.substring(idx,i+1));
            int len=sb.length();
            if(idx==0)
            {
                helper(num,target,i+1,curr,curr,sb.append(curr),result);
                sb.setLength(len);
            }
            else
            {
                helper(num,target,i+1,total+curr,curr,sb.append("+").append(curr),result);
                sb.setLength(len);
                helper(num,target,i+1,total-curr,-curr,sb.append("-").append(curr),result);
                sb.setLength(len);
                helper(num,target,i+1,total-mult+mult*curr,mult*curr,sb.append("*").append(curr),result);
                sb.setLength(len);
            }
        }
    }


    public static void main(String[] args)
    {
        ExpressionAddOperators obj=new ExpressionAddOperators();
        System.out.println(obj.addOperators("105",5));
    }
}
