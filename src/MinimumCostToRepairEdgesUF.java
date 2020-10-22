import java.util.*;
public class MinimumCostToRepairEdgesUF
{
    //Min Cost to Repair Edges
    {
        /*
        There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been broken disconnecting the graph. Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.

        Input:

        n, an int representing the total number of nodes.
        edges, a list of integer pair representing the nodes connected by an edge.
        edgesToRepair, a list where each element is a triplet representing the pair of nodes between which an edge is currently broken and the cost of repearing that edge, respectively (e.g. [1, 2, 12] means to repear an edge between nodes 1 and 2, the cost would be 12).
        Example 1:

        Input: n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
        Output: 20
        Explanation:
        There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
        We can connect these components into a single component by repearing the edges between nodes 1 and 2, and nodes 1 and 5 at a minimum cost 12 + 8 = 20.
        Example 2:

        Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]], edgesToRepair = [[1, 6, 410], [2, 4, 800]]
        Output: 410
        Example 3:

        Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]], edgesToRepair = [[1, 5, 110], [2, 4, 84], [3, 4, 79]]
        Output: 79
         */
    }

    public int minCostToRepairEdgesUF(int n, int[][] edges, int[][] edgesToRepair)
    {
        Set<String> seen=new HashSet<>();
        for(int[] i:edgesToRepair)
        {
            String key1=i[0]+","+i[1];
            String key2=i[1]+","+i[0];
            seen.add(key1);
            seen.add(key2);
        }
        int[] roots=new int[n+1];
        for(int i=0;i<roots.length;i++)
            roots[i]=i;

        for(int[] i:edges)
        {
            String key=i[0]+","+i[1];
            if(seen.contains(key))
                continue;
            int urep=find(roots,i[0]);
            int vrep=find(roots,i[1]);
            roots[urep]=vrep;
            n--;
        }
        Arrays.sort(edgesToRepair, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });
        int cost=0;
        for(int[]i : edgesToRepair)
        {
            int urep=find(roots,i[0]);
            int vrep=find(roots,i[1]);
            if(urep!=vrep)
            {
                cost+=i[2];
                roots[urep]=vrep;
                n--;
            }
        }
        return n==1?cost:-1;

    }

    public int find(int[] roots, int i)
    {
        if(roots[i]!=i)
            roots[i]=find(roots,roots[i]);
        return roots[i];
    }

    public static void main(String[] args)
    {
        MinimumCostToRepairEdgesUF obj=new MinimumCostToRepairEdgesUF();
        System.out.println(obj.minCostToRepairEdgesUF(5,new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}, new int[][]{{1, 2, 12}, {3, 4, 30}, {1, 5, 8}}));
    }
}
