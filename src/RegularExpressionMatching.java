public class RegularExpressionMatching
{
    /*
    Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.
    The matching should cover the entire input string (not partial).

    Note:

    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.
    Example 1:

    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    Example 2:

    Input:
    s = "aa"
    p = "a*"
    Output: true
    Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
    Example 3:

    Input:
    s = "ab"
    p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".
    Example 4:

    Input:
    s = "aab"
    p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
    Example 5:

    Input:
    s = "mississippi"
    p = "mis*is*p*."
    Output: false
     */

    public static boolean isMatch(String s, String p)
    {
        char[] sc=s.toCharArray();
        char[] pc=p.toCharArray();
        boolean[][] dp=new boolean[sc.length+1][pc.length+1];
        dp[0][0]=true;
        for(int i=1;i<dp[0].length;i++)
        {
            if(pc[i-1]=='*')
                dp[0][i]=dp[0][i-2];
        }
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                if(sc[i-1]==pc[j-1] || pc[j-1]=='.')
                    dp[i][j]=dp[i-1][j-1];
                else if(pc[j-1]=='*')
                {
                    dp[i][j]=dp[i][j-2];
                    if(sc[i-1]==pc[j-2] || pc[j-2]=='.')
                        dp[i][j]=dp[i][j]|dp[i-1][j];
                }
            }
        }
        return dp[sc.length][pc.length];
    }
    public static void main(String[] args)
    {
        System.out.println(isMatch("ab",".*"));
    }
}
