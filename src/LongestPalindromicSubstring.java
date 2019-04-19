public class LongestPalindromicSubstring
{
    /*
    Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

    Example 1:

    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
    Example 2:

    Input: "cbbd"
    Output: "bb"
     */
    public static String longestPalindrome(String str)
    {
        int start=0,end=0;
        for(int i=0;i<str.length();i++)
        {
            int len1=expand(str,i,i);
            int len2=expand(str,i,i+1);
            int len=Math.max(len1,len2);
            if(len>(end-start))
            {
                start=i-((len-1)/2);
                end=i+(len/2);
            }
        }
        return str.substring(start,end+1);
    }
    public static int expand(String str, int l,int r)
    {
        while(l>=0 && r<str.length() && str.charAt(l)==str.charAt(r))
        {
            l--;
            r++;
        }
        return r-l-1;
    }
    public static void main(String[] args)
    {
        System.out.println(longestPalindrome("babad"));
    }
}
