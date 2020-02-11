import java.util.*;

public class NryTreeSerialization
{
    /*
    Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize an N-ary tree.
    An N-ary tree is a rooted tree in which each node has no more than N children.
    There is no restriction on how your serialization/deserialization algorithm should work.
    You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

    For example, you may serialize the following 3-ary tree



    as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.

    Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.



    For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].

    You do not necessarily need to follow the above suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.



    Constraints:

    The height of the n-ary tree is less than or equal to 1000
    The total number of nodes is between [0, 10^4]
    Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
     */
    class Node
    {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val)
        {
            val = _val;
            children=new ArrayList<>();
        }

        public Node(int _val, List<Node> _children)
        {
            val = _val;
            children = _children;
        }
    }

    String NN="X",splitter=",";


    public String serialize(Node root)
    {
        StringBuilder sb=new StringBuilder();
        buildString(root,sb);
        return sb.toString();
    }
    public void buildString(Node root, StringBuilder sb)
    {
        if(root==null)
        {
            sb.append(NN);
            sb.append(splitter);
        }
        else
        {
            sb.append(root.val);
            sb.append(splitter);
            sb.append(root.children.size());
            sb.append(splitter);
            for(Node node:root.children)
                buildString(node,sb);
        }
    }

    public Node deserialize(String data)
    {
        String[] d=data.split(",");
        Queue<String> q=new LinkedList<>(Arrays.asList(d));
        return buildTree(q);
    }
    public Node buildTree(Queue<String> q)
    {
        String s=q.poll();
        if(s.equals(NN))
            return null;
        Node root=new Node(Integer.valueOf(s));
        int childrenSize=Integer.parseInt(q.poll());
        root.children=new ArrayList<>();
        for(int i=0;i<childrenSize;i++)
            root.children.add(buildTree(q));
        return root;
    }


    public void populate()
    {
        Node root=new Node(1);
        for(int i=2;i<=5;i++)
            root.children.add(new Node(i));
        root.children.get(1).children.add(new Node(6));
        root.children.get(1).children.add(new Node(7));
        root.children.get(1).children.get(1).children.add(new Node(7));
        root.children.get(1).children.get(1).children.get(0).children.add(new Node(11));
        root.children.get(1).children.get(1).children.get(0).children.get(0).children.add(new Node(14));
        root.children.get(2).children.add(new Node(8));
        root.children.get(2).children.get(0).children.add(new Node(12));

    }

    public static void main(String[] args)
    {
        NryTreeSerialization obj=new NryTreeSerialization();
        obj.populate();
        System.out.println(1);
    }
}
