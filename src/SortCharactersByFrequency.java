import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency
{
    /*
    Given a string, sort it in decreasing order based on the frequency of characters.

    Example 1:

    Input:
    "tree"

    Output:
    "eert"

    Explanation:
    'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
    Example 2:

    Input:
    "cccaaa"

    Output:
    "cccaaa"

    Explanation:
    Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
    Note that "cacaca" is incorrect, as the same characters must be together.
    Example 3:

    Input:
    "Aabb"

    Output:
    "bbAa"

    Explanation:
    "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.
     */
    public String frequencySort(String s)
    {
        Map<Character,Integer> map=new HashMap<>();
        for(char c:s.toCharArray())
            map.put(c,map.getOrDefault(c,0)+1);
        PriorityQueue<Character> pq=new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2)
            {
                return map.get(c2).compareTo(map.get(c1));
            }
        });
        for(char c:map.keySet())
            pq.offer(c);
        StringBuilder sb=new StringBuilder();
        while(!pq.isEmpty())
        {
            char c=pq.poll();
            int ct=map.get(c);
            for(int i=0;i<ct;i++)
                sb.append(c);
        }
        return sb.toString();
    }
    public static void main(String[] args)
    {
        SortCharactersByFrequency obj=new SortCharactersByFrequency();
        System.out.println(obj.frequencySort("cccaaa"));
    }
}
