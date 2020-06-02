import java.util.*;

public class PossibleBipartitions
{
    /*
        Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

        Each person may dislike some other people, and they should not go into the same group.

        Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

        Return true if and only if it is possible to split everyone into two groups in this way.



        Example 1:

        Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
        Output: true
        Explanation: group1 [1,4], group2 [2,3]
        Example 2:

        Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
        Output: false
        Example 3:

        Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
        Output: false


        Note:

        1 <= N <= 2000
        0 <= dislikes.length <= 10000
        1 <= dislikes[i][j] <= N
        dislikes[i][0] < dislikes[i][1]
        There does not exist i != j for which dislikes[i] == dislikes[j].
    */


    public boolean possibleBipartition(int N, int[][] dislikes)
    {
        if(dislikes==null || dislikes.length==0)
            return true;

        List<Integer>[] graph=new List[N+1];
        for(int i=1;i<=N;i++)
            graph[i]=new ArrayList<Integer>();

        for(int[] i:dislikes)
        {
            graph[i[0]].add(i[1]);
            graph[i[1]].add(i[0]);
        }

        Map<Integer,Integer> map=new HashMap<>();
        for(int i=1;i<=N;i++)
        {
            if(!map.containsKey(i) && !dfs(map,graph,i,1))
                return false;
        }
        return true;
    }

    public boolean dfs(Map<Integer,Integer> map, List<Integer>[] graph, int node, int val)
    {
        if(map.containsKey(node))
            return map.get(node)==val;
        map.put(node,val);
        for(int n:graph[node])
        {
            if(!dfs(map,graph,n,val*-1))
                return false;
        }
        return true;
    }


    public static void main(String[] args)
    {
        PossibleBipartitions obj=new PossibleBipartitions();
        System.out.println(obj.possibleBipartition(4,new int[][]{{1,2},{1,3},{2,4}}));
    }
}
