import java.util.*;
public class MutableList
{
    public int[] mutableList(int[][] arr)
    {
        if(arr==null || arr.length==0)
            return new int[0];
        Map<Integer,List<Integer>> map=new HashMap<>();
        Map<Integer,Integer> deg=new HashMap<>();
        for(int[] i:arr)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.putIfAbsent(i[1],new ArrayList<>());
            map.get(i[0]).add(i[1]);
            map.get(i[1]).add(i[0]);
            deg.put(i[0],deg.getOrDefault(i[0],0)+1);
            deg.put(i[1],deg.getOrDefault(i[1],0)+1);
        }
        int src=-1;
        for(int i: deg.keySet())
        {
            if(deg.get(i)==1)
            {
                src=i;
                break;
            }
        }

        List<Integer> res=new ArrayList<>();
        dfs(map,src,res,new HashSet<>());
        int[] ret=new int[res.size()];
        System.out.println(res);
        for(int i=0;i<ret.length;i++)
            ret[i]=res.get(i);
        return ret;
    }

    public void dfs(Map<Integer,List<Integer>> map, int src, List<Integer> res, Set<Integer> visited)
    {
        if(visited.contains(src))
            return;
        res.add(src);
        visited.add(src);
        for(int nxt:map.get(src))
            dfs(map,nxt,res,visited);

    }

    public static void main(String[] args)
    {
        MutableList obj=new MutableList();
        System.out.println(obj.mutableList(new int[][]{{3,5},{1,4},{2,4},{1,5}}));
    }
}
