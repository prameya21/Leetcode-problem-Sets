import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern2
{
    /*
    Given a pattern and a string str, find if str follows the same pattern.

    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

    Example 1:

    Input: pattern = "abab", str = "redblueredblue"
    Output: true
    Example 2:

    Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
    Output: true
    Example 3:

    Input: pattern = "aabb", str = "xyzabcxzyabc"
    Output: false
    Notes:
    You may assume both pattern and str contains only lowercase letters.
     */
    public boolean wordPatternMatch(String pattern, String str)
    {
        Map<Character,String> map=new HashMap<>();
        return helper(pattern,str,map,new HashSet<>());
    }
    public boolean helper(String pattern, String str, Map<Character,String> map, Set<String> set)
    {
        if(pattern.length()==0)
            return str.length()==0;
        if(map.containsKey(pattern.charAt(0)))
        {
            String value=map.get(pattern.charAt(0));
            if(str.length()<value.length() || !str.substring(0, value.length()).equals(value))
                return false;
            if(helper(pattern.substring(1),str.substring(value.length()),map,set))
                return true;
        }
        else
        {
            for(int i=1;i<=str.length();i++)
            {
                String value=str.substring(0,i);
                if(set.contains(value))
                    continue;
                map.put(pattern.charAt(0),value);
                set.add(value);
                if(helper(pattern.substring(1),str.substring(i),map,set))
                    return true;
                set.remove(value);
                map.remove(pattern.charAt(0));
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        WordPattern2 obj=new WordPattern2();
        System.out.println(obj.wordPatternMatch("abab","redblueredblue"));
    }
}
