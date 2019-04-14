import java.util.*;

public class WordLadder3
{
    /*
    Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

    Only one letter can be changed at a time
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
    Note:

    Return an empty list if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.
    Example 1:

    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    Output:
    [
      ["hit","hot","dot","dog","cog"],
      ["hit","hot","lot","log","cog"]
    ]
    Example 2:

    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    Output: []

    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.*/
    public static void main(String[] args)
    {
        String[] wordList={"hot","dot","dog","lot","log","cog"};

        System.out.println(findLadders("hit","cog",Arrays.asList(wordList)));

    }
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        Map<String, ArrayList<String>> adj=new HashMap<>();
        Map<String,Integer> distance=new HashMap<>();
        Set<String> dict=new HashSet<>(wordList);
        dict.add(beginWord);
        bfs(beginWord,endWord,adj,distance,dict);
        List<List<String>> sol=new ArrayList<>();
        dfs(sol,new ArrayList<>(),beginWord,endWord,distance,adj);
        return sol;
    }
    public static void bfs(String start, String end,Map<String,ArrayList<String>> adj, Map<String,Integer> distance,Set<String> dict)
    {
        for(String s:dict)
            adj.put(s,new ArrayList<>());
        distance.put(start,0);
        Queue<String> q=new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty())
        {
            boolean found=false;
            int count=q.size();
            for(int i=0;i<count;i++)
            {
                String word=q.poll();
                int currDistance=distance.get(word);
                ArrayList<String> n=canUse(word,dict);
                for(String _n:n)
                {
                    adj.get(word).add(_n);
                    if(!distance.containsKey(_n))
                    {
                        distance.put(_n,currDistance+1);
                        if(_n.equals(end))
                            found=true;
                        else
                            q.offer(_n);
                    }
                }
            }
            if(found)
                break;
        }
    }
    public static void dfs(List<List<String>> sol,List<String> temp,String start,String end,Map<String,Integer> distance,Map<String,ArrayList<String>> adj)
    {
        temp.add(start);
        if(start.equals(end))
        {
            sol.add(new ArrayList<>(temp));
        }
        else
        {
            for(String s: adj.get(start))
            {
                if(distance.get(s)==distance.get(start)+1)
                    dfs(sol,temp,s,end,distance,adj);
            }
        }
        temp.remove(temp.size()-1);
    }
    public static ArrayList<String> canUse(String word,Set<String> dict)
    {
        ArrayList<String> result=new ArrayList<>();
        for(String s: dict)
        {
            int ctr=0;
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)!=word.charAt(i))
                    ctr++;
            }
            if(ctr==1)
                result.add(s);
        }
        return result;
    }
}
