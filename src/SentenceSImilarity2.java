import java.util.*;

public class SentenceSImilarity2
{
    /*
    Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

    For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

    Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

    Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

    Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

    Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

    Note:

    The length of words1 and words2 will not exceed 1000.
    The length of pairs will not exceed 2000.
    The length of each pairs[i] will be 2.
    The length of each words[i] and pairs[i][j] will be in the range [1, 20].
     */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs)
    {
        if(words1.length!=words2.length)
            return false;
        Map<String, Set<String>> map=new HashMap<>();
        for(List<String> p:pairs)
        {
            String w1=p.get(0);
            String w2=p.get(1);
            map.putIfAbsent(w1,new HashSet<>());
            map.putIfAbsent(w2,new HashSet<>());
            map.get(w1).add(w2);
            map.get(w2).add(w1);
        }
        for(int i=0;i<words1.length;i++)
        {
            if(words1[i].equals(words2[i]))
                continue;
            if(!dfs(words1[i],words2[i],map,new HashSet<>()))
                return false;

        }
        return true;
    }
    public boolean dfs(String w1, String w2, Map<String, Set<String>> map, Set<String> visited)
    {
        if(visited.contains(w1))
            return false;
        if(map.get(w1).contains(w2))
            return true;
        visited.add(w1);
        for(String n:map.get(w1))
            if(dfs(n,w2,map,visited))
                return true;

        return false;
    }

    public static void main(String[] args)
    {
        SentenceSImilarity2 obj=new SentenceSImilarity2();
        String[] word1={"great","acting","skills"};
        String[] word2={"fine","painting","talent"};
        List<List<String>> pairs=new ArrayList<>();
        pairs.add(Arrays.asList("great","fine"));
        pairs.add(Arrays.asList("drama","acting"));
        pairs.add(Arrays.asList("skills","talent"));
        System.out.println(obj.areSentencesSimilarTwo(word1,word2,pairs));
    }
}
