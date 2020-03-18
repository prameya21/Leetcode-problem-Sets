import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithKRepeatingCaharcters
{
    /*
    Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

    Example 1:

    Input:
    s = "aaabb", k = 3

    Output:
    3

    The longest substring is "aaa", as 'a' is repeated 3 times.
    Example 2:

    Input:
    s = "ababbc", k = 2

    Output:
    5

    The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
     */

    public int longestSubstring(String s, int k)
    {
        if(s==null || s.length()==0)
            return 0;
        return helper(s.toCharArray(),0,s.length(),k);
    }
    public int helper(char[] ch, int l, int r, int k)
    {
        if(l>=r)
            return 0;
        int[] map=new int[26];
        for(int i=l;i<r;i++)
            map[ch[i]-'a']++;
        boolean flag=true;
        for(int i=0;i<26;i++)
            if(map[i]>0 && map[i]<k)
                flag=false;


        if(flag)
            return r-l;

        int start=l;
        int result=0;
        for(int i=l;i<r;i++)
        {
            if(map[ch[i]-'a']<k)
            {
                result=Math.max(result,helper(ch,start,i,k));
                start=i+1;
            }
        }
        result=Math.max(result,helper(ch,start,r,k));
        return result;

    }
    public static void main(String[] args)
    {
        LongestSubstringWithKRepeatingCaharcters obj=new LongestSubstringWithKRepeatingCaharcters();
        System.out.println(obj.longestSubstring("ababbc",3));
    }
}
