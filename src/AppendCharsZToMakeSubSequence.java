public class AppendCharsZToMakeSubSequence
{
    /*
        2486
        You are given two strings s and t consisting of only lowercase English letters.
        Return the minimum number of characters that need to be appended to the end of s so that t becomes a subsequence of s.
        A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

        Example 1:
        Input: s = "coaching", t = "coding"
        Output: 4
        Explanation: Append the characters "ding" to the end of s so that s = "coachingding".
        Now, t is a subsequence of s ("coachingding").
        It can be shown that appending any 3 characters to the end of s will never make t a subsequence.

        Example 2:
        Input: s = "abcde", t = "a"
        Output: 0
        Explanation: t is already a subsequence of s ("abcde").

        Example 3:
        Input: s = "z", t = "abcde"
        Output: 5
        Explanation: Append the characters "abcde" to the end of s so that s = "zabcde".
        Now, t is a subsequence of s ("zabcde").
        It can be shown that appending any 4 characters to the end of s will never make t a subsequence.

        Constraints:
        1 <= s.length, t.length <= 105
        s and t consist only of lowercase English letters.
     */

    public int appendCharacters(String s, String t)
    {
        if(t==null || t.length()==0)
            return 0;
        int i=0, j=0;
        while(i<s.length())
        {
            if(j<t.length() &&s.charAt(i)==t.charAt(j))
                j++;
            i++;
        }
        return t.length()-j;
    }

    public static void main(String[] args)
    {
        AppendCharsZToMakeSubSequence obj=new AppendCharsZToMakeSubSequence();
        System.out.println(obj.appendCharacters("coaching","coding"));
        System.out.println(obj.appendCharacters("abcde","a"));
        System.out.println(obj.appendCharacters("z","abcde"));
    }
}
