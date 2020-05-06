import java.util.*;

public class MostVistiedPattern
{
    /*
    We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

    A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)

    Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.



    Example 1:

    Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
    Output: ["home","about","career"]
    Explanation:
    The tuples in this example are:
    ["joe", 1, "home"]
    ["joe", 2, "about"]
    ["joe", 3, "career"]
    ["james", 4, "home"]
    ["james", 5, "cart"]
    ["james", 6, "maps"]
    ["james", 7, "home"]
    ["mary", 8, "home"]
    ["mary", 9, "about"]
    ["mary", 10, "career"]
    The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
    The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
    The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
    The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
    The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
     */

    class Web
    {
        String name;
        int time;
        public Web(String name, int time)
        {
            this.name=name;
            this.time=time;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website)
    {
        Map<String,List<Web>> map=new HashMap<>();
        for(int i=0;i<username.length;i++)
        {
            map.putIfAbsent(username[i],new ArrayList<Web>());
            map.get(username[i]).add(new Web(website[i],timestamp[i]));
        }


        Map<String,Integer> count=new HashMap<>();
        String res="";
        for(String s:map.keySet())
        {
            Collections.sort(map.get(s), new Comparator<Web>() {
                @Override
                public int compare(Web w1, Web w2) {
                    return Integer.compare(w1.time,w2.time);
                }
            });
            Set<String> set=new HashSet<>();
            List<Web> temp=map.get(s);
            for(int i=0;i<temp.size();i++)
            {
                for(int j=i+1;j<temp.size();j++)
                {
                    for(int k=j+1;k<temp.size();k++)
                    {
                        String str=temp.get(i).name+","+temp.get(j).name+","+temp.get(k).name;
                        if(!set.contains(str))
                        {
                            count.put(str,count.getOrDefault(str,0)+1);
                            set.add(str);
                        }
                        if(res.equals("") || count.get(res) < count.get(str) || (count.get(res) == count.get(str) && res.compareTo(str) > 0))
                            res=str;
                    }
                }
            }
        }


        String[] temp=res.split(",");
        return new ArrayList<>(Arrays.asList(temp));
    }

    public static void main(String[] args)
    {
        MostVistiedPattern obj=new MostVistiedPattern();
        System.out.println(obj.mostVisitedPattern(new String[]{"joe","joe","joe","james","james","james","james","mary","mary","mary"},new int[]{1,2,3,4,5,6,7,8,9,10},new String[]{"home","about","career","home","cart","maps","home","home","about","career"}));
    }
}
