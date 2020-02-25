import java.util.*;

public class WordBreak
{
    /*
        Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

        Note:

        The same word in the dictionary may be reused multiple times in the segmentation.
        You may assume the dictionary does not contain duplicate words.
        Example 1:

        Input: s = "leetcode", wordDict = ["leet", "code"]
        Output: true
        Explanation: Return true because "leetcode" can be segmented as "leet code".
        Example 2:

        Input: s = "applepenapple", wordDict = ["apple", "pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
                     Note that you are allowed to reuse a dictionary word.
        Example 3:

        Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        Output: false
     */

    public boolean wordBreakRecursive(String s, List<String> wordDict)
    {
        if(s==null || s.length()==0)
            return false;
        Set<String> dict=new HashSet<>(wordDict);
        //return recursiveHelper2(s,dict,0);
        //return recursiveHelper(s,dict);
        //return recursiveHelper2Memo(s,dict,0,new boolean[s.length()]);
        return wordBreakBFS(s,dict);

    }
    public boolean recursiveHelper2Memo(String s, Set<String> dict, int idx, boolean[] visited)
    {
        if(idx==s.length())
            return true;
        if(visited[idx])
            return true;
        for(int i=idx+1;i<=s.length();i++)
        {
            if(dict.contains(s.substring(idx,i)) && recursiveHelper2Memo(s,dict,i,visited))
            {
                visited[idx]=true;
                return true;
            }
        }
        visited[idx]=false;
        return false;
    }

    public boolean wordBreakBFS(String s, Set<String> dict)
    {
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        boolean[] visited=new boolean[s.length()+1];
        visited[0]=true;
        while(!q.isEmpty())
        {
            int curr=q.poll();
            for(int i=curr+1;i<=s.length();i++)
            {
                if(dict.contains(s.substring(curr,i)) && !visited[i])
                {
                    q.offer(i);
                    visited[i]=true;
                    if(i==s.length())
                        return true;
                }
            }
        }
        return false;
    }


    public boolean recursiveHelper2(String s, Set<String> dict, int idx)
    {
        if(idx==s.length())
            return true;
        for(int i=idx+1;i<=s.length();i++)
        {
            if(dict.contains(s.substring(idx,i)) && recursiveHelper2(s,dict,i))
                return true;
        }
        return false;
    }



    public boolean recursiveHelper(String s, Set<String> dict)
    {
        if(s.isEmpty() || s.length()==0)
            return true;
        for(int i=1;i<=s.length();i++)
        {
            String str1=s.substring(0,i);
            String str2=s.substring((i));
            if(dict.contains(s.substring(0,i)) && recursiveHelper(s.substring(i),dict))
                return true;
        }
        return false;
    }


    public static void main(String[] args)
    {
        WordBreak obj=new WordBreak();
        System.out.println(obj.wordBreakRecursive("leetcode", Arrays.asList("leet","code")));
        System.out.println(obj.wordBreakRecursive("applepenapple", Arrays.asList("apple","pen")));
        System.out.println("leetcode".substring(0,1));
    }
}
