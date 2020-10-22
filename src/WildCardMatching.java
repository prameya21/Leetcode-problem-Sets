public class WildCardMatching
{
    /*
    Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
    The matching should cover the entire input string (not partial).

    Note:

    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like ? or *.
    Example 1:

    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    Example 2:

    Input:
    s = "aa"
    p = "*"
    Output: true
    Explanation: '*' matches any sequence.
    Example 3:

    Input:
    s = "cb"
    p = "?a"
    Output: false
    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
    Example 4:

    Input:
    s = "adceb"
    p = "*a*b"
    Output: true
    Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
    Example 5:

    Input:
    s = "acdcb"
    p = "a*c?b"
    Output: false
     */

    public boolean isMatch(String s, String p)
    {
        char[] sc=s.toCharArray();
        char[] pc=p.toCharArray();
        boolean[][] dp=new boolean[sc.length+1][pc.length+1];
        dp[0][0]=true;
        for(int i=1;i<dp[0].length;i++)
        {
            if(pc[i-1]=='*')
                dp[0][i]=true;
            else
                break;
        }

        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                if(sc[i-1]==pc[j-1] || pc[j-1]=='?')
                    dp[i][j]=dp[i-1][j-1];
                else if(pc[j-1]=='*')
                    dp[i][j]=dp[i-1][j] | dp[i][j-1];
                else
                    dp[i][j]=false;
            }
        }
        return dp[sc.length][pc.length];
    }


    public static void main(String[] args)
    {
        WildCardMatching obj=new WildCardMatching();
        System.out.println(obj.isMatch("aa","*"));
    }

}
