
public class WordDictionary
{
    /*
    Design a data structure that supports the following two operations:

    void addWord(word)
    bool search(word)
    search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

    Example:

    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true
     */
    TrieNode node;
    public WordDictionary()
    {
        node=new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word)
    {
        TrieNode root=node;
        for(char c:word.toCharArray())
        {
            if(root.next[c-'a']==null)
                root.next[c-'a']=new TrieNode();
            root=root.next[c-'a'];
        }
        root.isWord=true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word)
    {

        return util(word.toCharArray(),0,node);
    }
    public boolean util(char[] c,int k,TrieNode root)
    {
        if(k==c.length)
            return root.isWord;
        if(c[k]=='.')
        {
            for(int i=0;i<root.next.length;i++)
            {
                if(root.next[i]!=null && util(c,k+1,root.next[i]))
                    return true;
            }
        }
        else
            return root.next[c[k]-'a']!=null && util(c,k+1,root.next[c[k]-'a']);
        return false;
    }




    public static void main(String args[])
    {
        WordDictionary wd=new WordDictionary();
        wd.addWord("a");
        wd.addWord("a");

        System.out.println(wd.search("."));
        System.out.println(wd.search("a"));
        System.out.println(wd.search("aa"));
        System.out.println(wd.search(".a"));
        System.out.println(wd.search("a."));
    }
}
