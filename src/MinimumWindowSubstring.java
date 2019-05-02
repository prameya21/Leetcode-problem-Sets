import java.util.*;

public class MinimumWindowSubstring
{
    /*
    Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

    Example:

    Input: S = "ADOBECODEBANC", T = "ABC"
    Output: "BANC"
    Note:

    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
     */
    public static String minWindow(String s, String t)
    {
        Map<Character,Integer> map_t=new HashMap<>();
        int ctr=0;
        int l=0,r=0,len=Integer.MAX_VALUE;
        String res="";
        for(char c:t.toCharArray())
        {
            map_t.put(c,map_t.getOrDefault(c,0)+1);
        }
        Map<Character,Integer> curMap=new HashMap<>();
        while(r<s.length())
        {
            char c=s.charAt(r);
            curMap.put(c,curMap.getOrDefault(c,0)+1);
            if(map_t.containsKey(c) && map_t.get(c).intValue()==curMap.get(c).intValue())
                ctr++;
            while(ctr==map_t.size() && l<=r)
            {
                String str=s.substring(l,r+1);
                if(str.length()<len)
                {
                    res=str;
                    len=str.length();
                }
                char ch=s.charAt(l);
                curMap.put(ch,curMap.get(ch)-1);
                if(map_t.containsKey(ch) && map_t.get(ch).intValue()>curMap.get(ch).intValue())
                    ctr--;
                l++;
            }
            r++;
        }
        return res;
    }

    public static void main(String[] args)
    {
        System.out.println(minWindow("ADOBECODEBANC","ABC"));
    }
}
