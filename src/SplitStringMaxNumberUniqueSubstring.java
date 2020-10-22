import java.util.HashSet;
import java.util.Set;

public class SplitStringMaxNumberUniqueSubstring
{
    /*
    Given a string s, return the maximum number of unique substrings that the given string can be split into.

    You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.

    A substring is a contiguous sequence of characters within a string.



    Example 1:

    Input: s = "ababccc"
    Output: 5
    Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
    Example 2:

    Input: s = "aba"
    Output: 2
    Explanation: One way to split maximally is ['a', 'ba'].
    Example 3:

    Input: s = "aa"
    Output: 1
    Explanation: It is impossible to split the string any further.
     */

    int res;
    public int maxUniqueSplit(String s)
    {
        if(s==null || s.length()==0)
            return 0;
        res=0;
        helper(s,0,new HashSet<>());
        return res;
    }

    public void helper(String s, int idx, Set<String> set)
    {
        if(idx>s.length())
            return;
        if(idx==s.length())
        {
            res=Math.max(res,set.size());
            return;
        }
        for(int i=idx+1;i<=s.length();i++)
        {
            String temp=s.substring(idx,i);
            if(!set.contains(temp))
            {
                set.add(temp);
                helper(s,i,set);
                set.remove(temp);
            }
        }
    }

    public static void main(String[] args)
    {
        SplitStringMaxNumberUniqueSubstring obj=new SplitStringMaxNumberUniqueSubstring();
        System.out.println(obj.maxUniqueSplit("aba"));
    }
}
