public class MinimumWindowSubsequence
{
    /*
    Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

    If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

    Example 1:

    Input:
    S = "abcdebdde", T = "bde"
    Output: "bcde"
    Explanation:
    "bcde" is the answer because it occurs before "bdde" which has the same length.
    "deb" is not a smaller window because the elements of T in the window must occur in order.


    Note:

    All the strings in the input will only contain lowercase letters.
    The length of S will be in the range [1, 20000].
    The length of T will be in the range [1, 100].
     */

    public String minWindow(String S, String T)
    {
        int start=0;
        String res="";
        int st=-1;
        int len=Integer.MAX_VALUE;
        char[] s=S.toCharArray();
        char[] t=T.toCharArray();

        while(start<s.length)
        {
            if(s[start]==t[(t.length-1)])
            {
                int i=start;
                int j=T.length()-1;
                while(i>=0 && j>=0)
                {
                    if(s[i]==t[j])
                        j--;
                    i--;
                }
                if(j==-1)
                {
                    int l=start-i;
                    if(l<len)
                    {
                        len=l;
                        st=i+1;
                    }
                }
            }
            start++;
        }
        return st==-1?"":S.substring(st,st+len);
    }

    public String minWindow1(String S, String T)
    {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sindex = 0, tindex = 0, slen = s.length, tlen = t.length, start = -1, len = slen;
        while(sindex < slen)
        {
            if(s[sindex] == t[tindex])
            {
                if(++tindex == tlen)
                {
                    //check feasibility from left to right of T
                    int end = sindex+1;
                    //check optimization from right to left of T
                    while(--tindex >= 0)
                    {
                        while(s[sindex--] != t[tindex]);
                    }
                    ++sindex;
                    ++tindex;
                    //record the current smallest candidate
                    if(end - sindex < len)
                    {
                        len = end - sindex;
                        start = sindex;
                    }
                }
            }
            ++sindex;
        }
        return start == -1? "" : S.substring(start, start + len);
    }
    public static void main(String[] args)
    {
        MinimumWindowSubsequence obj=new MinimumWindowSubsequence();
        System.out.println(obj.minWindow("abcdebdde","bde"));
    }
}
