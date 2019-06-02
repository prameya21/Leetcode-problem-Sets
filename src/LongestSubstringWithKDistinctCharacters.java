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
    public static int longestSubstringLength(String str,int k)
    {
        Map<Character,Integer> map=new HashMap<>();
        int j=0,res=0;
        for(int i=0;i<str.length();i++)
        {
            map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0)+1);
            while(map.size()>k)
            {
                map.put(str.charAt(j),map.get(str.charAt(j))-1);
                if(map.get(str.charAt(j))==0)
                    map.remove(str.charAt(j));
                j++;
            }
            res=Math.max(res,i-j+1);
        }
        return res;
    }
    public static void main(String[] args)
    {
        System.out.println(longestSubstringLength("eceba",2));
    }
}
