import java.util.*;
public class MinimumCostToRepairEdges
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

    public int minCostToRepairEdges(int n, int[][] edges, int[][] edgesToRepair)
    {
        Set<String> seen=new HashSet<>();
        Map<Integer,List<int[]>> graph=new HashMap<>();
        for(int[] i:edgesToRepair)
        {
            String key1=i[0]+","+i[1];
            String key2=i[1]+","+i[0];
            seen.add(key1);
            seen.add(key2);
            graph.putIfAbsent(i[0],new ArrayList<>());
            graph.putIfAbsent(i[1],new ArrayList<>());
            graph.get(i[0]).add(new int[]{i[1],i[2]});
            graph.get(i[1]).add(new int[]{i[0],i[2]});
        }
        for(int[] i:edges)
        {
            String key=i[0]+","+i[1];
            if(seen.contains(key))
                continue;
            graph.putIfAbsent(i[0],new ArrayList<>());
            graph.putIfAbsent(i[1],new ArrayList<>());
            graph.get(i[0]).add(new int[]{i[1],0});
            graph.get(i[1]).add(new int[]{i[0],0});
        }
        PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                return o1[1]-o2[1];
            }
        });
        pq.offer(new int[]{1,0});
        Set<Integer> visited=new HashSet<>();

        int cost=0;
        while(!pq.isEmpty())
        {
            int[] curr=pq.poll();
            if(visited.contains(curr[0]))
                continue;
            cost+=curr[1];
            visited.add(curr[0]);
            for(int[] i:graph.get(curr[0]))
            {
                if(visited.contains(i[0]))
                    continue;
                pq.offer(i);
            }
        }
        return cost;
    }

    public static void main(String[] args)
    {
        MinimumCostToRepairEdges obj=new MinimumCostToRepairEdges();
        System.out.println(obj.minCostToRepairEdges(5,new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}, new int[][]{{1, 2, 12}, {3, 4, 30}, {1, 5, 8}}));
    }
}
