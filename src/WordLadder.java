import java.util.*;

public class WordLadder
{
    /*
        Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

        Only one letter can be changed at a time.
        Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
        Note:

        Return 0 if there is no such transformation sequence.
        All words have the same length.
        All words contain only lowercase alphabetic characters.
        You may assume no duplicates in the word list.
        You may assume beginWord and endWord are non-empty and are not the same.
        Example 1:

        Input:
        beginWord = "hit",
        endWord = "cog",
        wordList = ["hot","dot","dog","lot","log","cog"]

        Output: 5

        Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
        return its length 5.
        Example 2:

        Input:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log"]

        Output: 0

        Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     */


    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        Set<String> dict=new HashSet<>(wordList);
        if(!dict.contains(endWord))
            return 0;

        Queue<String> q=new LinkedList<>();
        q.offer(beginWord);
        int count=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String curr=q.poll();
                if(curr.equals(endWord))
                    return count+1;
                for(String word:wordList)
                {
                    if(canForm(curr,word) && dict.contains(word))
                    {
                        q.offer(word);
                        dict.remove(word);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public boolean canForm(String word1, String word2)
    {
        int count=0;
        for(int i=0;i<word1.length();i++)
        {
            if(word1.charAt(i)!=word2.charAt(i))
                count++;
        }
        return count==1;
    }
    public static void main(String[] args)
    {
        WordLadder obj=new WordLadder();
        System.out.println(obj.ladderLength("hit","cog",new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"))));
    }
}
