import java.util.HashMap;
import java.util.Map;

public class MinimumStepsToMakeTwoStringsAnagram
{
    /*
        Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.

        Return the minimum number of steps to make t an anagram of s.

        An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.



        Example 1:

        Input: s = "bab", t = "aba"
        Output: 1
        Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
        Example 2:

        Input: s = "leetcode", t = "practice"
        Output: 5
        Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
        Example 3:

        Input: s = "anagram", t = "mangaar"
        Output: 0
        Explanation: "anagram" and "mangaar" are anagrams.
        Example 4:

        Input: s = "xxyyzz", t = "xxyyzz"
        Output: 0
        Example 5:

        Input: s = "friend", t = "family"
        Output: 4


        Constraints:

        1 <= s.length <= 50000
        s.length == t.length
        s and t contain lower-case English letters only.
     */

    public int minSteps(String s, String t)
    {
        if(s.length()==0 && t.length()==0)
            return 0;
        Map<Character,Integer> map=new HashMap<>();
        for(char c:s.toCharArray())
            map.put(c,map.getOrDefault(c,0)+1);

        for(char c:t.toCharArray())
            map.put(c,map.getOrDefault(c,0)-1);

        int count=0;
        for(char c:map.keySet())
        {
            if(map.get(c)>0)
                count+=map.get(c);
        }
        return count;
    }


    public static void main(String[] args)
    {
        MinimumStepsToMakeTwoStringsAnagram obj= new MinimumStepsToMakeTwoStringsAnagram();
        System.out.println(obj.minSteps("bab","aba"));
        System.out.println(obj.minSteps("leetcode","practice"));
        System.out.println(obj.minSteps("anagram","mangaar"));
        System.out.println(obj.minSteps("xxyyzz","xxyyzz"));
        System.out.println(obj.minSteps("friend","family"));
    }
}
