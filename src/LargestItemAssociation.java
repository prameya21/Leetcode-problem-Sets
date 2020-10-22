import java.util.*;

public class LargestItemAssociation
{
    /*
        Given a list of item association relationships, write an algorithm that outputs the larger item association group. If two groups have the same number of items then select the group which contains the item that appears first in Lexicographic order.
     */

    public class PairString
    {
        String first;
        String second;

        public PairString(String first, String second)
        {
            this.first = first;
            this.second = second;
        }
    }
    public List<String> largestItemAssociation(List<PairString> itemAssociation)
    {
        if(itemAssociation==null || itemAssociation.size()==0)
            return new ArrayList<>();
        Map<String,Set<String>> map=new HashMap<>();
        for(PairString ps:itemAssociation)
        {
            map.putIfAbsent(ps.first,new HashSet<>());
            map.putIfAbsent(ps.second,new HashSet<>());
            map.get(ps.first).add(ps.second);
            map.get(ps.second).add(ps.first);
        }

        List<String> result=new ArrayList<>();
        Set<String> visited=new HashSet<>();
        for(PairString ps:itemAssociation)
        {
            if(!visited.contains(ps.first))
            {
                List<String> temp=new ArrayList<>();
                dfs(ps.first,map,visited,temp);
                Collections.sort(temp);
                if(temp.size()==result.size() && temp.toString().compareTo(result.toString())>0)
                {
                    result=new ArrayList<>(temp);
                }
                else if(temp.size()>result.size())
                    result=new ArrayList<>(temp);



            }
        }
        return result;
    }

    public void dfs(String s, Map<String,Set<String>> map, Set<String> visited, List<String> temp)
    {
        if(visited.contains(s))
            return;
        temp.add(s);
        visited.add(s);
        for(String u:map.get(s))
            dfs(u,map,visited,temp);
    }

    public static void main(String[] args)
    {
        LargestItemAssociation s=new LargestItemAssociation();
        List<PairString> input = Arrays.asList(
                new PairString[]{
                        s.new PairString("item1", "item2"),
                        s.new PairString("item3", "item4"),
                        s.new PairString("item4", "item5")
                }
        );

        List<PairString> input2 =  Arrays.asList(
                new PairString[] {
                        s.new PairString("item1","item2"),
                        s.new PairString("item2","item3"),
                        s.new PairString("item4","item5"),
                        s.new PairString("item6","item7"),
                        s.new PairString("item5","item6"),
                        s.new PairString("item3","item7")
                }
        );

        List<PairString> input3 =  Arrays.asList(
                new PairString[] {
                        s.new PairString("item1","item2"),
                        s.new PairString("item1","item3"),
                        s.new PairString("item2","item7"),
                        s.new PairString("item3","item7"),
                        s.new PairString("item5","item6"),
                        s.new PairString("item3","item7")
                }
        );

        System.out.println(s.largestItemAssociation(input));
        System.out.println(s.largestItemAssociation(input2));
        System.out.println(s.largestItemAssociation(input3));
    }
}
