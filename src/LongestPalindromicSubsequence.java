public class LongestPalindromicSubsequence
{

    /*
    Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

    Example 1:
    Input:

    "bbbab"
    Output:
    4
    One possible longest palindromic subsequence is "bbbb".
    Example 2:
    Input:

    "cbbd"
    Output:
    2
    One possible longest palindromic subsequence is "bb".
     */


    public int longestPalindromeSubseq(String s)
    {
        int[][] dp=new int[s.length()][s.length()];
        for(int i=0;i<s.length();i++)
            dp[i][i]=1;
        for(int l=2;l<=s.length();l++)
        {
            for(int i=0;i<s.length()-l+1;i++)
            {
                int j=i+l-1;
                if(l==2 && s.charAt(i)==s.charAt(j))
                    dp[i][j]=2;
                else if(s.charAt(i)==s.charAt(j))
                    dp[i][j]=2+dp[i+1][j-1];
                else
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][s.length()-1];
    }




    public static void main(String[] args)
    {
        LongestPalindromicSubsequence obj=new LongestPalindromicSubsequence();
        System.out.println(obj.longestPalindromeSubseq("bbbab"));
    }
}
