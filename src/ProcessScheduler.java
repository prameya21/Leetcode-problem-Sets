import java.util.*;

public class ProcessScheduler
{
    /*Given a Map of process and their dependencies, resolve the said dependencies and return the sequence
    For e.g:
    1:3,4
    2:
    3:4
    4:2

    return: 2,4,3,1

     */
    public static List<Integer> scheduler(Map<Integer,List<Integer>> input)
    {
        Set<Integer> visited=new HashSet<>();
        List<Integer> result= new ArrayList<>();
        for(int i:input.keySet())
        {
            if(!visited.contains(i))
                order(i,input,result,visited);
        }
        return result;

    }
    public static void order(int key, Map<Integer,List<Integer>> map,List<Integer> result,Set<Integer> visited)
    {
        if(visited.contains(key))
            return;
        if(map.get(key).size()==0)
        {
            result.add(key);
            visited.add(key);
            return;
        }
        for(int i:map.get(key))
        {
            order(i,map,result,visited);
        }
        visited.add(key);
        result.add(key);
    }
    public static void main(String[] args)
    {
        Map<Integer,List<Integer>> map=new HashMap<>();
        map.put(1,new ArrayList<>());
        map.put(2,new ArrayList<>());
        map.put(3,new ArrayList<>());
        map.put(4,new ArrayList<>());
        map.get(1).add(3);
        map.get(1).add(4);
        map.get(3).add(4);
        map.get(4).add(2);
        System.out.println(scheduler(map));

    }
}
