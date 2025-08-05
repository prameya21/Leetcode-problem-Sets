import java.util.*;
public class AnagramsInString
{
    /*
    438
    Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

    Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

    The order of output does not matter.

    Example 1:

    Input:
    s: "cbaebabacd" p: "abc"

    Output:
    [0, 6]

    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".
    Example 2:

    Input:
    s: "abab" p: "ab"

    Output:
    [0, 1, 2]

    Explanation:
    The substring with start index = 0 is "ab", which is an anagram of "ab".
    The substring with start index = 1 is "ba", which is an anagram of "ab".
    The substring with start index = 2 is "ab", which is an anagram of "ab".
     */
    public static List<Integer> findAnagram(String s,String p)
    {
        List<Integer> res=new ArrayList<>();
        int[] map=new int[26];
        for(char c:p.toCharArray())
            map[c-'a']++;
        int cnt=p.length();
        int left=0;
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(map[c-'a']-->0)
                cnt--;
            if(cnt==0 && i-left+1==p.length())
                res.add(left);
            if (i -left + 1 >= p.length())
            {
                if (++map[s.charAt(left) - 'a'] > 0)
                    cnt++;
                left++;
            }
        }
        return res;
    }
    public static void main(String[] args)
    {
        System.out.println(findAnagram("cbaebabacd","abc"));
    }
}
