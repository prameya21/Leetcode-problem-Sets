import java.lang.reflect.Array;
import java.util.*;
public class WordBreak2
{
    /*
    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

    Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.
    Example 1:

    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
    [
      "cats and dog",
      "cat sand dog"
    ]
    Example 2:

    Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    Output:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    Explanation: Note that you are allowed to reuse a dictionary word.
    Example 3:

    Input:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output:
    []
    */
    public static void main(String[] ars)
    {
        String[] wordDict={"cat","cats","and","sand","dog"};
        System.out.println(wordBreak("catsanddog",new ArrayList<>(Arrays.asList(wordDict))));
    }
    public static List<String> wordBreak(String s, List<String> wordDict)
    {
        return _DFS(s,new HashSet<>(wordDict),new HashMap<String,List<String>>());
    }
    public static List<String> DFS(String s,Set<String> dict,Map<String,List<String>> memo)
    {
        if(memo.containsKey(s))
            return memo.get(s);
        List<String> result=new ArrayList<>();
        if(s.length()==0)
            result.add("");
        for(String word:dict)
        {
            if(s.startsWith(word))
            {
                List<String> subList=DFS(s.substring(word.length()),dict,memo);
                for(String sub:subList)
                {
                    result.add(word+(sub.isEmpty()?"":" ")+sub);
                }
            }
        }
        memo.put(s,result);
        return result;
    }
    public static List<String> _DFS(String s,Set<String> dict,Map<String,List<String>> memo)
    {
        if(memo.containsKey(s))
            return memo.get(s);
        List<String> res=new ArrayList<>();
        if(s.length()==0)
            res.add("");
        for(int i=1;i<=s.length();i++)
        {
            if(dict.contains(s.substring(0,i)))
            {
                String word=s.substring(0,i);
                List<String> subList=_DFS(s.substring(i),dict,memo);
                for(String sub:subList)
                {
                    res.add(word+(sub.isEmpty()?"":" ")+sub);
                }
            }
        }
        memo.put(s,res);
        return res;
    }
}