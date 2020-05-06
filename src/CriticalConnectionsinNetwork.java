import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnectionsinNetwork
{
    /*
    There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

    A critical connection is a connection that, if removed, will make some server unable to reach some other server.

    Return all critical connections in the network in any order.



    Example 1:



    Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
    Output: [[1,3]]
    Explanation: [[3,1]] is also accepted.
     */

    int T=1;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections)
    {
        List<Integer>[] graph=new List[n];
        for(int i=0;i<n;i++)
            graph[i]=new ArrayList<>();
        for(List<Integer> l:connections)
        {
            graph[l.get(0)].add(l.get(1));
            graph[l.get(1)].add(l.get(0));
        }
        int[] timestamp=new int[n];
        List<List<Integer>> res=new ArrayList<>();
        dfs(0,-1,graph,timestamp,res);
        return res;
    }

    public int dfs(int i, int parent,List<Integer>[] graph,int[] timestamp, List<List<Integer>> res)
    {
        if(timestamp[i]!=0)
            return timestamp[i];
        timestamp[i]=T++;

        int minTimeStamp=Integer.MAX_VALUE;
        List<Integer> list=graph[i];
        for(int n:list)
        {
            if(n==parent)
                continue;
            int nTimeStamp=dfs(n,i,graph,timestamp,res);
            minTimeStamp=Math.min(minTimeStamp,nTimeStamp);
        }
        if(minTimeStamp>=timestamp[i])
        {
            if(parent>=0)
                res.add(Arrays.asList(i,parent));
        }
        return Math.min(minTimeStamp,timestamp[i]);
    }


    public static void main(String[] args)
    {
        CriticalConnectionsinNetwork obj=new CriticalConnectionsinNetwork();
        /*
        List<List<Integer>> conn=new ArrayList<>();
        conn.add(Arrays.asList(0,1));
        conn.add(Arrays.asList(1,2));
        conn.add(Arrays.asList(2,0));
        conn.add(Arrays.asList(1,3));


        List<List<Integer>> conn=new ArrayList<>();
        conn.add(Arrays.asList(1,0));
        conn.add(Arrays.asList(2,0));
        conn.add(Arrays.asList(3,2));
        conn.add(Arrays.asList(4,2));
        conn.add(Arrays.asList(4,3));
        conn.add(Arrays.asList(3,0));
        conn.add(Arrays.asList(4,0));
        */

        List<List<Integer>> conn=new ArrayList<>();
        conn.add(Arrays.asList(0,1));
        conn.add(Arrays.asList(1,2));
        conn.add(Arrays.asList(2,0));
        conn.add(Arrays.asList(1,3));
        conn.add(Arrays.asList(3,4));
        conn.add(Arrays.asList(4,5));
        conn.add(Arrays.asList(3,5));
        conn.add(Arrays.asList(5,6));

        System.out.println(obj.criticalConnections(7,conn));
    }
}

