import java.util.*;
public class MinimumCostToConnectServers
{
    /*
    Your team at amazon is overseeing the design of a new high-efficiency data center at HQ2. A power grid need to be generated for supplying power to N servers.
    All servers in the grid have to be connected such that they have access to power. The cost of connections between different servers varies.

    Assume that there are no ties, names of servers are unique, connections are directionless, there is at most one connection between a pair of servers, all costs are greater than zero, and a server does not connect to itself.

    Write an algorithm to minimize the cost of connecting all servers in the power grid.

    Input
    two arguments - num, an Integer representing number of connections.
    connectons, representing a list of connections where each element of the list consists of two servers and the cost of connection between the servers.

    Note
    The cost of connection between the servers is always greater than 0.

    Input
    num = 5

    connection =
         [[A,B,1],
         [B,C,4],
         [B,D,6],
         [D,E,5],
         [C,E,1]]

    Output
    [[A,B,1],
    [B,C,4],
    [C,E,1],
    [D,E,5]]

     */
    public int minimumConnectionCost(int n, List<String> connections)
    {
        Collections.sort(connections,new Comparator<String>(){
            public int compare(String i, String j)
            {
                return Integer.parseInt(i.split(",")[2])-Integer.parseInt(j.split(",")[2]);
            }
        });

        int[] roots=new int[n];
        for(int i=0;i< roots.length;i++)
            roots[i]=i;
        int cost=0;
        List<String> result=new ArrayList<>();
        for(String conn:connections)
        {
            String[] data=conn.split(",");
            int i=data[0].charAt(0)-'A';
            int j=data[1].charAt(0)-'A';
            int val=Integer.parseInt(data[2]);
            int irep=find(roots,i);
            int jrep=find(roots,j);
            if(irep!=jrep)
            {
                cost+=val;
                n--;
                roots[irep]=jrep;
                result.add(conn);
                if(n==1)
                {
                    System.out.println(result);
                    return cost;
                }
            }
        }
        return -1;
    }

    public int find(int[] roots, int i)
    {
        if(roots[i]!=i)
            roots[i]=find(roots,roots[i]);
        return roots[i];
    }

    public static void main(String[] args)
    {
        MinimumCostToConnectServers obj=new MinimumCostToConnectServers();
        System.out.println(obj.minimumConnectionCost(5,new ArrayList<>(Arrays.asList("A,B,1","B,C,4","B,D,6","D,E,5","C,E,1"))));
    }
}
