import java.util.HashMap;
import java.util.Map;

public class DesignFileSystem
{
    //1166
    /*
        You are asked to design a file system that allows you to create new paths and associate them with different values.

        The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.

        Implement the FileSystem class:

        bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
        int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.


        Example 1:

        Input:
        ["FileSystem","createPath","get"]
        [[],["/a",1],["/a"]]
        Output:
        [null,true,1]
        Explanation:
        FileSystem fileSystem = new FileSystem();

        fileSystem.createPath("/a", 1); // return true
        fileSystem.get("/a"); // return 1
        Example 2:

        Input:
        ["FileSystem","createPath","createPath","get","createPath","get"]
        [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
        Output:
        [null,true,true,2,false,-1]
        Explanation:
        FileSystem fileSystem = new FileSystem();

        fileSystem.createPath("/leet", 1); // return true
        fileSystem.createPath("/leet/code", 2); // return true
        fileSystem.get("/leet/code"); // return 2
        fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
        fileSystem.get("/c"); // return -1 because this path doesn't exist.


        Constraints:

        2 <= path.length <= 100
        1 <= value <= 109
        Each path is valid and consists of lowercase English letters and '/'.
        At most 104 calls in total will be made to createPath and get.

     */
    class FileSystem {

        class TrieNode
        {
            int val=-1;
            String name;
            Map<String, TrieNode> map=new HashMap<>();
        }

        TrieNode root;
        public FileSystem()
        {
            this.root=new TrieNode();
        }

        public boolean createPath(String path, int value)
        {
            if(path.length()<=1 || path.equals("/"))
                return false;
            TrieNode curr=root;
            String[] p=path.split("/");
            for(int i=1;i<p.length;i++)
            {
                if(!curr.map.containsKey(p[i]))
                {
                    if(i==p.length-1)
                    {
                        curr.map.put(p[i],new TrieNode());
                        curr.map.get(p[i]).name=p[i];
                    }
                    else
                        return false;
                }
                curr=curr.map.get(p[i]);
            }
            if(curr.val!=-1)
                return false;
            curr.val=value;
            return true;

        }

        public int get(String path)
        {
            String[] p=path.split("/");
            TrieNode cur=root;
            for(int i=1;i<p.length;i++)
            {
                if(!cur.map.containsKey(p[i]))
                    return -1;
                cur=cur.map.get(p[i]);
            }
            return cur.val;
        }
    }

    public void test()
    {
        FileSystem obj= new FileSystem();
        System.out.println(obj.createPath("/a", 1));
        System.out.println(obj.get("/a"));
        obj=new FileSystem();
        System.out.println(obj.createPath("/leet", 1));
        System.out.println(obj.createPath("/leet/code", 2));
        System.out.println(obj.get("/leet/code"));
        System.out.println(obj.createPath("/c/d", 2));
        System.out.println(obj.get("/c"));


    }
    public static void main(String[] args)

    {
        DesignFileSystem obj =new DesignFileSystem();
        obj.test();

    }
}
