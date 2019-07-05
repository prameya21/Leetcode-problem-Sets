import java.util.*;

class Trie
{
    Trie[] next=new Trie[26];
    String word=null;
}
public class WordSearch2
{
    /*
    Given a 2D board and a list of words from the dictionary, find all words in the board.

    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



    Example:

    Input:
    board = [
      ['o','a','a','n'],
      ['e','t','a','e'],
      ['i','h','k','r'],
      ['i','f','l','v']
    ]
    words = ["oath","pea","eat","rain"]

    Output: ["eat","oath"]
     */
    public List<String> findWords(char[][] board, String[] words)
    {
        Trie root=buildTrie(words);
        if(board==null || board.length==0)
            return new ArrayList<>();
        List<String> res=new ArrayList<>();
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                dfs(board,i,j,root,res);
            }
        }
        return res;
    }
    public void dfs(char[][] board,int i,int j, Trie root, List<String> res)
    {
        if(i<0 || i>=board.length || j< 0 || j>=board[0].length || board[i][j]=='#')
            return;
        char c=board[i][j];
        if(root.next[c-'a']==null)
            return;
        root=root.next[c-'a'];
        if(root.word!=null)
        {
            res.add(root.word);
            root.word=null;
        }
        board[i][j]='#';
        dfs(board,i+1,j,root,res);
        dfs(board,i,j+1,root,res);
        dfs(board,i-1,j,root,res);
        dfs(board,i,j-1,root,res);
        board[i][j]=c;
    }
    public Trie buildTrie(String[] words)
    {
        Trie root=new Trie();
        for(String word:words)
        {
            Trie p=root;
            for(char c:word.toCharArray())
            {
                if(p.next[c-'a']==null)
                    p.next[c-'a']=new Trie();
                p=p.next[c-'a'];
            }
            p.word=word;
        }
        return root;
    }
    public static void main(String[] args)
    {
        WordSearch2 ws=new WordSearch2();
        System.out.println(ws.findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},new String[]{"oath","pea","eat","rain"}));
    }
}
