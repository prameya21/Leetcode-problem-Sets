public class ReverseInParanthesis
{
    /*
        Write a function that reverses characters in (possibly nested) parentheses in the input string.
        Input strings will always be well-formed with matching ()s.

        Example

        For inputString = "(bar)", the output should be
        reverseInParentheses(inputString) = "rab";
        For inputString = "foo(bar)baz", the output should be
        reverseInParentheses(inputString) = "foorabbaz";
        For inputString = "foo(bar)baz(blim)", the output should be
        reverseInParentheses(inputString) = "foorabbazmilb";
        For inputString = "foo(bar(baz))blim", the output should be
        reverseInParentheses(inputString) = "foobazrabblim".
        Because "foo(bar(baz))blim" becomes "foo(barzab)blim" and then "foobazrabblim".
        Input/Output

        [execution time limit] 3 seconds (java)

        [input] string inputString

        A string consisting of lowercase English letters and the characters ( and ). It is guaranteed that all parentheses in inputString form a regular bracket sequence.

        Guaranteed constraints:
        0 ≤ inputString.length ≤ 50.

        [output] string

        Return inputString, with all the characters that were in parentheses reversed.
     */

    public String reverseInParentheses(String s)
    {
        if(s==null || s.length()==0)
            return s;
        int start=0;
        while(start>=0)
        {
            start=-1;
            int end=-1;
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)=='(')
                    start=i;
                if(s.charAt(i)==')')
                {
                    end=i;
                    StringBuilder sb=new StringBuilder(s.substring(start+1,end));
                    System.out.println(start);
                    System.out.println(end);
                    s=s.substring(0,start)+sb.reverse().toString()+s.substring(end+1);
                    System.out.println(s);
                    break;
                }
            }
        }
        return s;
    }
    public static void main(String[] args)
    {
        ReverseInParanthesis obj=new ReverseInParanthesis();
        System.out.println(obj.reverseInParentheses("(abc)d(efg)"));
    }
}
