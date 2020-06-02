import java.util.*;

public class ConcatenatedWords
{
    /*
    Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
    A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

    Example:
    Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

    Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

    Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
     "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
    "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
    Note:
    The number of elements of the given array will not exceed 10,000
    The length sum of elements in the given array will not exceed 600,000.
    All the input string will only include lower case letters.
    The returned elements order does not matter.
     */

    public List<String> findAllConcatenatedWordsInADict(String[] words)
    {
        if(words==null || words.length==0)
            return new ArrayList<>();
        Set<String> dict=new HashSet<>(Arrays.asList(words));
        List<String> result=new ArrayList<>();
        for(String s:words)
            if(helper(s,dict))
                result.add(s);

        return result;
    }

    public boolean helper(String s, Set<String> dict)
    {
        for(int i=1;i<s.length();i++)
        {
            if(dict.contains(s.substring(0,i)) && (dict.contains(s.substring(i)) || helper(s.substring(i), dict)))
                return true;
        }
        return false;
    }



    public List<String> findAllConcatenatedWordsInADict2(String[] words)
    {
        Set<String> wordDict=new HashSet<>(Arrays.asList(words));
        List<String> res=new ArrayList<>();
        if(words.length==0 || words==null)
            return res;
        for(String word:words)
        {
            wordDict.remove(word);
            if(canForm(word,wordDict))
                res.add(word);
            wordDict.add(word);
        }
        return res;

    }
    public boolean canForm(String s, Set<String> dict)
    {
        if(s.length()==0 || s==null)
            return false;
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        boolean[] visited=new boolean[s.length()];
        while(!q.isEmpty())
        {
            int val=q.poll();
            if(!visited[val])
            {
                for(int i=val+1;i<=s.length();i++)
                {
                    if(dict.contains(s.substring(val,i)))
                    {
                        q.offer(i);
                        if(i==s.length())
                            return true;
                    }
                }
            }
            visited[val]=true;
        }
        return false;
    }
}

