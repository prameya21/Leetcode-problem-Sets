import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistance
{
    /*
    Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

    Example:
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

    Input: word1 = “coding”, word2 = “practice”
    Output: 3
    Input: word1 = "makes", word2 = "coding"
    Output: 1
    Note:
    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     */
    public int shortestDistance(String[] words, String word1, String word2)
    {
        int p1=-1,p2=-1,res=Integer.MAX_VALUE;
        for(int i=0;i<words.length;i++)
        {
            if(word1.equals(words[i]))
            {
                p1=i;
                if(p2>=0)
                    res=Math.min(res,Math.abs(p2-p1));
            }
            if(word2.equals(words[i]))
            {
                p2=i;
                if(p1>=0)
                    res=Math.min(res,Math.abs(p2-p1));
            }
        }
        return res;
    }

    /*
    Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

    word1 and word2 may be the same and they represent two individual words in the list.

    Example:
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

    Input: word1 = “makes”, word2 = “coding”
    Output: 1
    Input: word1 = "makes", word2 = "makes"
    Output: 3
    Note:
    You may assume word1 and word2 are both in the list.
     */
    public int shortestWordDistance(String[] words, String word1, String word2)
    {
        int p1=-1,p2=-1,res=Integer.MAX_VALUE;
        for(int i=0;i<words.length;i++)
        {
            if(words[i].equals(word1))
            {
                p1=i;
                if(p2>=0 && p2!=p1)
                    res=Math.min(res,Math.abs(p1-p2));
            }
            if(words[i].equals(word2))
            {
                p2=i;
                if(p1>=0 && p1!=p2)
                    res=Math.min(res,Math.abs(p1-p2));
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        ShortestWordDistance obj=new ShortestWordDistance();
        WordDistance obj1=new WordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        System.out.println(obj1.shortest("coding","practice"));
        System.out.println(obj.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"},"coding","practice"));
    }
}
/*
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
class WordDistance
{
    Map<String, List<Integer>> map;
    public WordDistance(String[] words)
    {
        map=new HashMap<>();
        for(int i=0;i<words.length;i++)
        {
            map.putIfAbsent(words[i],new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }
    public int shortest(String word1, String word2)
    {
        List<Integer> w1=map.get(word1);
        List<Integer> w2=map.get(word2);
        int min=Integer.MAX_VALUE;
        for(int i:w1)
            for(int j:w2)
                min=Math.min(min,Math.abs(i-j));
        return min;
    }
}
