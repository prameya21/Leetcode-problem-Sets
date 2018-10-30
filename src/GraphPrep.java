import java.util.*;

class Graph
{
    int data;
    ArrayList<Graph> nodes;
    public Graph(int val)
    {
        data=val;
        nodes=new ArrayList<Graph>();
    }
    public void addNode(Graph G)
    {
        nodes.add(G);
    }
}


public class GraphPrep
{
    public static  void DFS(Graph start, Graph end)
    {
        ArrayList<Graph> visited=new ArrayList<Graph>();
        ArrayList<Integer> path=new ArrayList<Integer>();
        dfsUtil(start,end,visited,path);
    }
    public static void dfsUtil(Graph node,Graph end, ArrayList<Graph> visited,ArrayList<Integer> path)
    {
        if(node==null)
            return;
        if(node==end) {
            path.add(node.data);
            System.out.println(path);
            return;
        }
        path.add(node.data);
        visited.add(node);
        for(Graph temp: node.nodes)
        {
            if(!visited.contains(temp))
                dfsUtil(temp,end,visited,path);
        }
    }
    public static void DFSprint(Graph root, ArrayList<Graph> visited)
    {
        if(root==null)
            return;
        System.out.print(root.data+" ");
        visited.add(root);
        for(Graph temp:root.nodes)
        {
            if(!visited.contains(temp))
                DFSprint(temp,visited);
        }

    }
    public static void BFSsearch(Graph start, Graph end, ArrayList<Integer> path)
    {
        if(start==end)
            return;
        Queue<Graph> bfsq=new LinkedList<Graph>();
        ArrayList<Graph> visited=new ArrayList<Graph>();
        bfsq.add(start);
        visited.add(start);
        path.add(start.data);
        while(!bfsq.isEmpty())
        {
            Graph temp=bfsq.remove();
            if(temp!=null) {
                for (Graph g:temp.nodes)
                {
                    if (!visited.contains(g))
                    {
                        visited.add(g);
                        path.add(g.data);
                        if (g == end)
                        {
                            System.out.println(path);
                            return;
                        }
                        bfsq.add(g);
                    } else
                        continue;
                }
            }
        }

    }
    public static LinkedList<String> transform(String start, String stop, HashSet<String> dictionary)
    {
        String startword=start.toUpperCase();
        String stopword= stop.toUpperCase();
        Queue<String> q=new LinkedList<String>();
        LinkedList<String> res=new LinkedList<String>();
        HashSet<String> visited = new HashSet<>();
        q.add(start);
        visited.add(start);
        res.add(start);
        while(!q.isEmpty())
        {
            String w=q.remove();
            for(String v:onewordaway(w))
            {
                if(v.equals(stop))
                {
                    res.add(v);
                    return res;
                }
                else
                {
                    if(dictionary.contains(v))
                    {
                        if(!visited.contains(v))
                        {
                            q.add(v);
                            res.add(v);
                            visited.add(v);
                        }
                    }
                }
            }

        }
        return null;
    }
    public static Set<String> onewordaway(String word)
    {
        Set<String> result=new HashSet<>();
        for(int i=0;i<word.length();i++)
        {
            char[] wordarray=word.toCharArray();
            for(char c='A';c<'Z';c++)
            {
                if(word.charAt(i)!=c)
                {
                    wordarray[i]=c;
                    result.add(new String(wordarray));
                }
            }
        }
        return result;
    }
    public static void printParanthesis(int size)
    {
        int open=0;
        int close=0;
        String str="";
        printParanthesisUtil(str,open,close,size);
    }
    public static void printParanthesisUtil(String str,int open,int close,int size)
    {
        if(close==size && open==size)
            System.out.println(str);
        if(close>open)
            return;
        if(open<size)
            printParanthesisUtil(str+"(",open+1,close,size);
        if(close<size)
            printParanthesisUtil(str+")",open,close+1,size);
    }

    public static void main(String[] args)
    {
        System.out.println("Hello World");
        Graph node1=new Graph(0);
        Graph node2=new Graph(1);
        Graph node3=new Graph(2);
        Graph node4=new Graph(3);
        node1.addNode(node2);
        node1.addNode(node3);
        node2.addNode(node4);
        node3.addNode(node1);
        DFS(node1,node4);
        ArrayList<Graph> visited = new ArrayList<Graph>();
        DFSprint(node1,visited);
        System.out.println();
        BFSsearch(node1,node4,new ArrayList<Integer>());
        HashSet<String> dictionary=new HashSet<>();
        dictionary.add("DAMP");
        dictionary.add("LAMP");
        dictionary.add("LIMP");
        dictionary.add("LIME");
        dictionary.add("LIKE");
        LinkedList<String> res=transform("DAMP","LIKE",dictionary);
        System.out.println(res);
        printParanthesis(3);

    }
}
