import java.util.*;
public class MaximumNumberOfNonOverlappingSubstrings
{
    /*
    Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:

    The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
    A substring that contains a certain character c must also contain all occurrences of c.
    Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.

    Notice that you can return the substrings in any order.



    Example 1:

    Input: s = "adefaddaccc"
    Output: ["e","f","ccc"]
    Explanation: The following are all the possible substrings that meet the conditions:
    [
      "adefaddaccc"
      "adefadda",
      "ef",
      "e",
      "f",
      "ccc",
    ]
    If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings.
    Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
    Example 2:

    Input: s = "abbaccd"
    Output: ["d","bb","cc"]
    Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.


    Constraints:

    1 <= s.length <= 10^5
    s contains only lowercase English letters.
     */

    public List<String> maxNumOfSubstrings(String s)
    {
        if(s==null || s.length()==0)
            return new ArrayList<>();
        Stack<String> st=new Stack<>();
        int[] l=new int[26];
        int[] r=new int[26];
        Arrays.fill(l,Integer.MAX_VALUE);
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            l[c-'a']=Math.min(l[c-'a'],i);
            r[c-'a']=i;
        }

        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(l[c-'a']==i)
                helper(s,i,l,r,st);
        }
        if(st.isEmpty())
            st.push(s);
        return new ArrayList<>(st);
    }

    public void helper(String s, int idx, int[] l, int[] r, Stack<String> st)
    {
        int start=idx, end=r[s.charAt(idx)-'a'];
        for(int i=start;i<=end;i++)
            end=Math.max(end,r[s.charAt(i)-'a']);
        Set<Character> visited=new HashSet<>();
        for(int i=start;i<=end;i++)
        {
            if(visited.contains(s.charAt(i)))
                continue;
            if(r[s.charAt(i)-'a']>end || l[s.charAt(i)-'a']<start)
                return;
            visited.add(s.charAt(i));
        }
        String temp=s.substring(start,end+1);
        if(!st.isEmpty() && st.peek().length()>temp.length())
        {
            while(!st.isEmpty() && r[st.peek().charAt(st.peek().length()-1)-'a']>l[temp.charAt(0)-'a'])
                st.pop();
            st.push(temp);
        }
        else
            st.push(temp);
    }

    public static void main(String[] args)
    {
        MaximumNumberOfNonOverlappingSubstrings obj=new MaximumNumberOfNonOverlappingSubstrings();
        System.out.println(obj.maxNumOfSubstrings("abaabbcaaabbbccd"));
    }
}
