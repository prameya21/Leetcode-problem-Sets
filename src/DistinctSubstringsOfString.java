public class DistinctSubstringsOfString
{
    /*
    Given a string of length n of lowercase alphabet characters, we need to count total number of distinct substrings of this string.
    Examples:

    Input  : str = “ababa”
    Output : 10
    Total number of distinct substring are 10, which are,
    "", "a", "b", "ab", "ba", "aba", "bab", "abab", "baba"
    and "ababa"
     */
    class TrieNode
    {
        TrieNode[] next=new TrieNode[26];
    }
    public void insert(TrieNode root, String str)
    {
        for(char c:str.toCharArray())
        {
            if(root.next[c-'a']==null)
                root.next[c-'a']=new TrieNode();
            root=root.next[c-'a'];
        }
    }
    public int dfs(TrieNode root)
    {
        int ctr=0;
        for(int i=0;i<26;i++)
        {
            if(root.next[i]!=null)
                ctr+=dfs(root.next[i]);
        }
        return ctr+1;
    }
    public int distinctCount(String s)
    {
        if(s==null || s.length()==0)
            return 1;
        TrieNode root=new TrieNode();
        for(int i=0;i<s.length();i++)
            insert(root,s.substring(i));

        return dfs(root);
    }

    public static void main(String[] args)
    {
        DistinctSubstringsOfString obj=new DistinctSubstringsOfString();
        System.out.println(obj.distinctCount("ababa"));
    }
}
