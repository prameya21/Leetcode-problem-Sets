public class LastSubstringLexicographicalOrder
{
    /*
    Given a string s, return the last substring of s in lexicographical order.



    Example 1:

    Input: "abab"
    Output: "bab"
    Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
    Example 2:

    Input: "leetcode"
    Output: "tcode"
     */

    public String lastSubstring(String s)
    {
        if(s==null || s.length()==0)
            return "";
        int i=0,j=1,offset=0;
        while(i+offset<s.length() && j+offset<s.length())
        {
            char ci=s.charAt(i+offset);
            char cj=s.charAt(j+offset);
            if(ci==cj)
                offset++;
            else
            {
                if(ci<cj)
                    i+=offset+1;
                else
                    j+=offset+1;
                if(i==j)
                    j++;
                offset=0;
            }
        }
        return s.substring(i);
    }
    public static void main(String[] args)
    {
        LastSubstringLexicographicalOrder obj=new LastSubstringLexicographicalOrder();
        System.out.println(obj.lastSubstring("leetcode"));
    }
}
