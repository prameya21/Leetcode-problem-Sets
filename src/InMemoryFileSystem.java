import java.util.*;
public class InMemoryFileSystem
{
    /*
    Design an in-memory file system to simulate the following functions:

    ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

    mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

    addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

    readContentFromFile: Given a file path, return its content in string format.



    Example:

    Input:
    ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
    [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]

    Output:
    [null,[],null,null,["a"],"hello"]

    Explanation:
    filesystem


    Note:

    You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
    You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
    You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
     */


    class TrieNode
    {
        TrieNode[] next;
        int type;
        StringBuilder sb;
        public TrieNode()
        {
            next=new TrieNode[27];
            type=0;
            sb=new StringBuilder();
        }
    }

    TrieNode root;
    public InMemoryFileSystem()
    {
        root=new TrieNode();
    }

    public int toInt(char c)
    {
        return c=='/'?26:c-'a';
    }



    public TrieNode insert(TrieNode root, String path, int type)
    {
        for(int i=0;i<path.length();i++)
        {
            int val=toInt(path.charAt(i));
            if(root.next[val]==null)
                root.next[val]=new TrieNode();
            root=root.next[val];
            if(i+1<path.length() && path.charAt(i+1)=='/')
                root.type=1;
        }
        if(root.type==0)
            root.type=type;
        return root;
    }


    public void dfs(TrieNode node, StringBuilder sb,List<String> res)
    {
        if(node==null)
            return;
        if(node.type>0)
            res.add(sb.toString());
        for(int i=0;i<26;i++)
        {
            if(node.next[i]!=null)
            {
                sb.append((char)(i+'a'));
                dfs(node.next[i],sb,res);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    public List<String> ls(String path)
    {
        List<String> res=new ArrayList<>();
        if(path.equals("/"))
        {
            dfs(root.next[26],new StringBuilder(), res);
            return res;
        }

        TrieNode node=insert(root,path,1);
        if(node.type==2)
        {
            res.add(path.substring(path.lastIndexOf("/")+1));
            return res;
        }
        else
        {
            if(node.next[26]==null)
                return res;
            else
                dfs(node.next[26],new StringBuilder(), res);
            return res;
        }
    }

    public void mkdir(String path)
    {
        insert(root, path, 1);
    }

    public void addContentToFile(String filePath, String content)
    {
        TrieNode node=insert(root,filePath,2);
        node.sb.append(content);
    }

    public String readContentFromFile(String filePath)
    {
        TrieNode node=insert(root,filePath,2);
        return node.sb.length()==0?"":node.sb.toString();
    }

    public static void main(String[] args)
    {
        InMemoryFileSystem fs=new InMemoryFileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        //fs.addContentToFile("/a/b/c/d","Hello");
        //System.out.println(fs.readContentFromFile("/a/b/c/d"));
        System.out.println(fs.ls("/a/b"));
    }
}
