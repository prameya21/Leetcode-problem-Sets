import java.util.*;
public class SequentialDigits
{
    /*
    An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

    Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
    Example 1:

    Input: low = 100, high = 300
    Output: [123,234]
    Example 2:

    Input: low = 1000, high = 13000
    Output: [1234,2345,3456,4567,5678,6789,12345]
     */


    public List<Integer> sequentialDigits(int low, int high)
    {
        List<Integer> result=new ArrayList<>();
        for(int i=1;i<=9;i++)
            helper(result,low,high,0,i);
        Collections.sort(result);
        return result;
    }

    public void helper(List<Integer> result, int low, int high, int sum, int curr)
    {
        if(sum>high)
            return;
        if(sum>=low)
            result.add(sum);
        if(curr<10)
            helper(result,low,high,sum*10+curr,curr+1);

    }


    public static void main(String[] args)
    {
        SequentialDigits obj=new SequentialDigits();
        System.out.println(obj.sequentialDigits(1000,13000));
        System.out.println(obj.sequentialDigits(100,300));
    }
}
