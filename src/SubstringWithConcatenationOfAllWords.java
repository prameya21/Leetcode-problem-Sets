import java.util.*;
public class SubstringWithConcatenationOfAllWords
{
    /*
    You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

    Example 1:

    Input:
      s = "barfoothefoobarman",
      words = ["foo","bar"]
    Output: [0,9]
    Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
    The output order does not matter, returning [9,0] is fine too.
    Example 2:

    Input:
      s = "wordgoodgoodgoodbestword",
      words = ["word","good","best","word"]
    Output: []
     */
    public static List<Integer> findSubstring(String s, String[] words)
    {
        Map<String,Integer> freq=new HashMap<>();
        List<Integer> res=new ArrayList<>();
        for(String word:words)
            freq.put(word,freq.getOrDefault(word,0)+1);
        int totalLength=words[0].length()*words.length;
        int wordLen=words[0].length();
        for(int start=0;start<s.length()-totalLength;start++)
        {
            Map<String,Integer> seen=new HashMap<>();
            int j=0;
            String con=s.substring(start,start+totalLength);
            for(int i=0;i<=con.length()-wordLen;i+=wordLen)
            {
                String temp=con.substring(i,i+wordLen);
                if(freq.containsKey(temp))
                {
                    seen.put(temp,seen.getOrDefault(temp,0)+1);
                    if(seen.get(temp)>freq.get(temp))
                        break;
                }
                else
                    break;
                j++;
            }
            if(j==words.length)
                res.add(start);
        }
        return res;
    }

    public static void main(String[] args)
    {
        String[] words={"foo","bar"};
        System.out.println(findSubstring("barfoothefoobarman",words));
    }
}
