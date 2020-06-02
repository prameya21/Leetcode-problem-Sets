import java.util.*;

public class MaximumVowelsInGivenSubString
{
    /*
    Given a string s and an integer k.

    Return the maximum number of vowel letters in any substring of s with length k.

    Vowel letters in English are (a, e, i, o, u).
    Example 1:

    Input: s = "abciiidef", k = 3
    Output: 3
    Explanation: The substring "iii" contains 3 vowel letters.
    Example 2:

    Input: s = "aeiou", k = 2
    Output: 2
    Explanation: Any substring of length 2 contains 2 vowels.
    Example 3:

    Input: s = "leetcode", k = 3
    Output: 2
    Explanation: "lee", "eet" and "ode" contain 2 vowels.
    Example 4:

    Input: s = "rhythms", k = 4
    Output: 0
    Explanation: We can see that s doesn't have any vowel letters.
    Example 5:

    Input: s = "tryhard", k = 4
    Output: 1


    Constraints:

    1 <= s.length <= 10^5
    s consists of lowercase English letters.
    1 <= k <= s.length
     */

    public int maxVowels(String s, int k)
    {
        if(s==null || s.length()==0)
            return 0;

        Set<Character> set=new HashSet<>(Arrays.asList('a','e','i','o','u'));
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<s.length();i++)
        {
            if(set.contains(s.charAt(i)))
                list.add(i);
        }
        if(list.size()==0)
            return 0;

        int max=helper(list,k);
        return max;

    }
    public int helper(List<Integer> list, int k)
    {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<list.size();i++)
        {
            int s=list.get(i);
            int e=s+k-1;
            int cnt=1;
            for(int j=i+1;j<=i+k && j<list.size();j++)
            {
                if(list.get(j)<=e)
                    cnt++;
            }
            max=Math.max(max,cnt);
        }
        return max;
    }
    public static void main(String[] args)
    {
        MaximumVowelsInGivenSubString obj=new MaximumVowelsInGivenSubString();
        System.out.println(obj.maxVowels("aeiou",2));
    }
}
