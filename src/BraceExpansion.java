import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BraceExpansion
{
    /*
        You are given a string s representing a list of words. Each letter in the word has one or more options.
        If there is one option, the letter is represented as is.
        If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"].
        For example, if s = "a{b,c}", the first character is always 'a', but the second character can be 'b' or 'c'. The original list is ["ab", "ac"].

        Return all words that can be formed in this manner, sorted in lexicographical order.

        Example 1:
        Input: s = "{a,b}c{d,e}f"
        Output: ["acdf","acef","bcdf","bcef"]

        Example 2:
        Input: s = "abcd"
        Output: ["abcd"]


        Constraints:

        1 <= s.length <= 50
        s consists of curly brackets '{}', commas ',', and lowercase English letters.
        s is guaranteed to be a valid input.
        There are no nested curly brackets.
        All characters inside a pair of consecutive opening and ending curly brackets are different.
     */

    public String[] expand(String s)
    {
        if(s==null)
            return new String[0];
        List<List<Character>> tmp=new ArrayList<>();
        parse(s,tmp);
        List<String> res=new ArrayList<>();
        dfs(0,tmp,new StringBuilder(), res);
        return res.toArray(new String[res.size()]);
    }

    public void dfs(int idx, List<List<Character>> tmp, StringBuilder sb, List<String> res)
    {
        if(idx==tmp.size())
            res.add(sb.toString());
        else
        {
            for(int i=0;i<tmp.get(idx).size();i++)
            {
                sb.append(tmp.get(idx).get(i));
                dfs(idx+1, tmp,sb,res);
                sb.setLength(sb.length()-1);
            }
        }
    }

    public void parse(String s, List<List<Character>> tmp)
    {
        for(int i=0;i<s.length();i++)
        {
            List<Character> lst=new ArrayList<>();
            if(s.charAt(i)=='{')
            {
                while(s.charAt(i)!='}')
                {
                    if(Character.isAlphabetic(s.charAt(i)))
                        lst.add(s.charAt(i));
                    i++;
                }
                Collections.sort(lst);
            }
            else
            {
                lst.add(s.charAt(i));
            }
            tmp.add(lst);
        }

    }

    public static void main(String[] args)
    {
        BraceExpansion obj=new BraceExpansion();
        String[] res=obj.expand("{a,b,c}ghj{n,m}yt{op}");
        for(String s:res)
            System.out.println(s);
    }
}
