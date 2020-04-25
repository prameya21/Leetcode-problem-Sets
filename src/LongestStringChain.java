import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LongestStringChain
{
    /*
        Given a list of words, each word consists of English lowercase letters.

        Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

        A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

        Return the longest possible length of a word chain with words chosen from the given list of words.



        Example 1:

        Input: ["a","b","ba","bca","bda","bdca"]
        Output: 4
        Explanation: one of the longest word chain is "a","ba","bda","bdca".


        Note:

        1 <= words.length <= 1000
        1 <= words[i].length <= 16
        words[i] only consists of English lowercase letters.
     */

    public int longestStrChain(String[] words)
    {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        int res=Integer.MIN_VALUE;
        Map<String,Integer> map=new HashMap<>();
        for(String s:words)
        {
            if(map.containsKey(s))
                continue;
            map.put(s,1);

            for(int i=0;i<s.length();i++)
            {
                StringBuilder sb=new StringBuilder(s);
                String next=sb.deleteCharAt(i).toString();
                if(map.containsKey(next) && map.get(next)+1>map.get(s))
                    map.put(s,map.get(next)+1);
            }
            res=Math.max(res,map.get(s));
        }
        return res;
    }

    public static void main(String[] args)
    {
        LongestStringChain obj=new LongestStringChain();
        System.out.println(obj.longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
    }
}
