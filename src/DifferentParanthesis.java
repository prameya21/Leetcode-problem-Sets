import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentParanthesis
{
    /*
    Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

    Example 1:

    Input: "2-1-1"
    Output: [0, 2]
    Explanation:
    ((2-1)-1) = 0
    (2-(1-1)) = 2
    Example 2:

    Input: "2*3-4*5"
    Output: [-34, -14, -10, -10, 10]
    Explanation:
    (2*(3-(4*5))) = -34
    ((2*3)-(4*5)) = -14
    ((2*(3-4))*5) = -10
    (2*((3-4)*5)) = -10
    (((2*3)-4)*5) = 10
     */

    static Map<String,List<Integer>> memo=new HashMap<>();
    public static List<Integer> diffWaysToCompute(String input)
    {
        if(memo.containsKey(input))
            return memo.get(input);
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<input.length();i++)
        {
            char c=input.charAt(i);
            if(c=='+' || c=='-' || c=='*')
            {
                List<Integer> part1=diffWaysToCompute(input.substring(0,i));
                List<Integer> part2=diffWaysToCompute(input.substring(i+1));
                for(int p1: part1)
                {
                    for(int p2:part2)
                    {
                        if(c=='+')
                            result.add(p1+p2);
                        else if(c=='-')
                            result.add(p1-p2);
                        else
                            result.add(p1*p2);
                    }
                }
            }
        }
        if(result.size()==0)
            result.add(Integer.parseInt(input));
        memo.put(input,result);
        return result;
    }
    public static void main(String[] args)
    {
        System.out.println(diffWaysToCompute("2*3-4*5"));
    }
}
