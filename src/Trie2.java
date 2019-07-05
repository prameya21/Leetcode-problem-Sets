class TrieNode
{
    TrieNode[] next=new TrieNode[26];
    boolean isWord=false;
}
public class Trie2
{
    /*
    Implement a trie with insert, search, and startsWith methods.

    Example:

    Trie trie = new Trie();

    trie.insert("apple");
    trie.search("apple");   // returns true
    trie.search("app");     // returns false
    trie.startsWith("app"); // returns true
    trie.insert("app");
    trie.search("app");     // returns true
    Note:

    You may assume that all inputs are consist of lowercase letters a-z.
    All inputs are guaranteed to be non-empty strings.
     */
    TrieNode node;
    public Trie2()
    {
        node=new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word)
    {
        TrieNode root=node;
        for(char c: word.toCharArray())
        {
            if(root.next[c-'a']==null)
                root.next[c-'a']=new TrieNode();
            root=root.next[c-'a'];
        }
        root.isWord=true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word)
    {
        TrieNode root=node;
        for(char c: word.toCharArray())
        {
            if(root.next[c-'a']==null)
                return false;
            root=root.next[c-'a'];
        }
        return root.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix)
    {
        TrieNode root=node;
        for(char c:prefix.toCharArray())
        {
            if(root.next[c-'a']==null)
                return false;
            root=root.next[c-'a'];
        }
        return true;
    }
    public static void main(String[] args)
    {
        Trie2 node=new Trie2();
        node.insert("apple");
        System.out.println(node.search("apple"));
        System.out.println(node.startsWith("app"));
    }
}
