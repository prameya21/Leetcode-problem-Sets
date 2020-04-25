import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PalindromePartitioning3
{
    /*
        You are given a string s containing lowercase letters and an integer k. You need to :

        First, change some characters of s to other lowercase English letters.
        Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
        Return the minimal number of characters that you need to change to divide the string.



        Example 1:

        Input: s = "abc", k = 2
        Output: 1
        Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
        Example 2:

        Input: s = "aabbc", k = 3
        Output: 0
        Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
        Example 3:

        Input: s = "leetcode", k = 8
        Output: 0
     */
    public int palindromePartition(String s, int k)
    {
        if(k==s.length())
            return 0;
        //return dfs(0,s,k);
        //return dfs2(0,s,k,new HashMap<>());
        int len=s.length();
        int[][] dp=new int[k][len];
        int[][] cache=new int[len][len];
        char[] ch=s.toCharArray();

        for(int[] i:cache)
            Arrays.fill(i,-1);

        for(int i=0;i<k;i++)
        {
            for(int j=i;j<len;j++)
            {
                if(i==0)
                    dp[i][j]=cost(ch,i,j,cache);
                else
                {
                    int min=Integer.MAX_VALUE;
                    for(int p=i;p<=j;p++)
                        min=Math.min(min,dp[i-1][p-1]+cost(ch,p,j,cache));
                    dp[i][j]=min;
                }
            }
        }
        return dp[k-1][len-1];
    }

    public int cost(char[] ch, int left, int right, int[][] cache)
    {
        if(cache[left][right]!=-1)
            return cache[left][right];
        int l=left,r=right,diff=0;
        while(l<=r)
            if(ch[l++]!=ch[r--])
                diff++;

        cache[left][right]=diff;
        return diff;
    }


    public int dfs2(int start, String s, int k, Map<String,Integer> memo)
    {
        String key=start+"#"+k;
        if(memo.containsKey(key))
            return memo.get(key);
        if(s.length()-start==k)
            return 0;
        if(k==1)
            return cost(s,start,s.length()-1);
        int min=Integer.MAX_VALUE;
        for(int i=start+1;i<s.length()-k+2;i++)
            min=Math.min(min,dfs2(i,s,k-1,memo)+cost(s,start,i-1));
        memo.put(key,min);
        return min;
    }


    public int cost(String str,int l, int r)
    {
        int ret=0;
        while(l<=r)
        {
            if(str.charAt(l)!=str.charAt(r))
                ret++;
            l++;
            r--;
        }
        return ret;
    }

    public int dfs(int start, String s, int k)
    {
        if(s.length()-start==k)
            return 0;
        if(k==1)
            return cost(s,start,s.length()-1);
        int min=Integer.MAX_VALUE;
        for(int i=start+1;i<s.length()-k+2;i++)
            min=Math.min(min,dfs(i,s,k-1)+cost(s,start,i-1));
        return min;
    }
    public static void main(String[] args)
    {
        PalindromePartitioning3 obj=new PalindromePartitioning3();
        System.out.println(obj.palindromePartition("aabbc",3));
    }
}
