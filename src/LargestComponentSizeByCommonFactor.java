import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LargestComponentSizeByCommonFactor
{
    /*
    Given a non-empty array of unique positive integers A, consider the following graph:

    There are A.length nodes, labelled A[0] to A[A.length - 1];
    There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
    Return the size of the largest connected component in the graph.



    Example 1:

    Input: [4,6,15,35]
    Output: 4

    Example 2:

    Input: [20,50,9,63]
    Output: 2

    Example 3:

    Input: [2,3,6,7,4,12,21,39]
    Output: 8

    Note:

    1 <= A.length <= 20000
    1 <= A[i] <= 100000
     */

    public int largestComponentSize2(int[] A)
    {
        Map<Integer, Set<Integer>> map=new HashMap<>();
        for(int a:A)
        {
            map.putIfAbsent(a,new HashSet<>());
            map.get(a).add(a);
            for(int j=2;j*j<=a;j++)
            {
                if(a%j==0)
                {
                    map.putIfAbsent(j,new HashSet<>());
                    map.putIfAbsent(a/j,new HashSet<>());
                    map.get(j).add(a);
                    map.get(a/j).add(a);
                    map.get(a).add(j);
                    map.get(a).add(a/j);
                }
            }

        }
        int max=0;
        Set<Integer> visited=new HashSet<>();
        for(int i:map.keySet())
        {
            if(visited.contains(i))
                continue;
            max=Math.max(max,dfs(i,map,visited));
        }
        return max;
    }
    public int dfs(int i, Map<Integer,Set<Integer>> map, Set<Integer> visited)
    {
        if(visited.contains(i) || !map.containsKey(i))
            return 0;
        visited.add(i);
        int ans=1;
        for(int j:map.get(i))
            ans+=dfs(j,map,visited);
        return ans;
    }
    public int largestComponentSize(int[] A)
    {
        if(A==null || A.length==0)
            return 0;

        int[] roots=new int[A.length];
        int[] size =new int[A.length];

        for(int i=0;i<A.length;i++)
        {
            roots[i]=i;
            size[i]=1;
        }
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<A.length;i++)
        {
            int a=A[i];
            for(int j=2;j*j<=a;j++)
            {
                if(a%j==0)
                {
                    if(!map.containsKey(j))
                        map.put(j,i);
                    else
                        union(i,map.get(j),roots,size);
                    if(!map.containsKey(a/j))
                        map.put(a/j,i);
                    else
                        union(i,map.get(a/j),roots,size);
                }
            }
            if(!map.containsKey(a))
                map.put(a,i);
            else
                union(i,map.get(a),roots,size);;
        }
        int max=Integer.MIN_VALUE;
        for(int i:size)
            max=Math.max(max,i);
        return max;
    }

    public int find(int[] roots, int i)
    {
        if(roots[i]!=i)
            roots[i]=find(roots,roots[i]);
        return roots[i];
    }
    public void union(int i, int j, int[] roots, int[] size)
    {
        int irep=find(roots,i);
        int jrep=find(roots,j);
        if(irep!=jrep)
        {
            roots[irep]=jrep;
            size[jrep]+=size[irep];
        }
    }

    public static void main(String[] args)
    {
        LargestComponentSizeByCommonFactor obj=new LargestComponentSizeByCommonFactor();
        System.out.println(obj.largestComponentSize2(new int[]{2,3,6,7,4,12,21,39}));

    }
}
