import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKDistinctCharacters
{
    /*
    Given a string, find the length of the longest substring T that contains at most k distinct characters.

    Example 1:

    Input: s = "eceba", k = 2
    Output: 3
    Explanation: T is "ece" which its length is 3.
    Example 2:

    Input: s = "aa", k = 1
    Output: 2
    Explanation: T is "aa" which its length is 2.
     */
    public int lengthOfLongestSubstringKDistinct(String str, int k)
    {
        Map<Character,Integer> map=new HashMap<>();
        int j=0,res=0;
        for(int i=0;i<str.length();i++)
        {
            char c=str.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
            while(map.size()>k)
            {
                char ch=str.charAt(j);
                map.put(ch,map.get(ch)-1);
                if(map.get(ch)==0)
                    map.remove(ch);
                j++;
            }
            res=Math.max(res,i-j+1);
        }
        return res;
    }
    public static void main(String[] args)
    {
        LongestSubstringWithKDistinctCharacters obj=new LongestSubstringWithKDistinctCharacters();
        System.out.println(obj.lengthOfLongestSubstringKDistinct("eceba",2));
    }
}