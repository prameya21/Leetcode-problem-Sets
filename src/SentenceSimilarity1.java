import java.util.*;

public class SentenceSimilarity1
{
    /*
    Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

    For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

    Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

    However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

    Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

    Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

    Note:

    The length of words1 and words2 will not exceed 1000.
    The length of pairs will not exceed 2000.
    The length of each pairs[i] will be 2.
    The length of each words[i] and pairs[i][j] will be in the range [1, 20].
     */
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs)
    {
        if(words1.length!=words2.length)
            return false;
        Set<String> set=new HashSet<>();
        for(List p:pairs)
            set.add(p.get(0)+"#"+p.get(1));
        for(int i=0;i<words1.length;i++)
        {
            if(!set.contains(words1[i]+"#"+words2[i]) || !set.contains(words2[i]+"#"+words1[i]))
                return false;
        }
        return true;
    }
    public boolean areSentencesSimilar2(String[] w1, String[] w2, List<List<String>> pairs)
    {
        if(w1.length!=w2.length)
            return false;
        Map<String,Set<String>> map=new HashMap<>();
        for(List<String> p: pairs)
        {
            map.putIfAbsent(p.get(0),new HashSet<>());
            map.putIfAbsent(p.get(1),new HashSet<>());
            map.get(p.get(0)).add(p.get(1));
            map.get(p.get(1)).add(p.get(0));
        }
        for(int i=0;i<w1.length;i++)
        {
            if(w1[i].equals(w2[i]))
                continue;
            if(map.containsKey(w1[i]) && map.get(w1[i]).contains(w2[i]))
                continue;
            if(map.containsKey(w2[i]) && map.get(w2[i]).contains(w1[i]))
                continue;
            return false;
        }
        return true;
    }
    public static void main(String[] args)
    {
        SentenceSimilarity1 obj=new SentenceSimilarity1();
        String[] word1={"great","acting","skills"};
        String[] word2={"fine","painting","talent"};
        List<List<String>> pairs=new ArrayList<>();
        pairs.add(Arrays.asList("great","fine"));
        pairs.add(Arrays.asList("drama","acting"));
        pairs.add(Arrays.asList("skills","talent"));
        System.out.println(obj.areSentencesSimilar(word1,word2,pairs));
    }
}

