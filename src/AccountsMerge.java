import java.util.*;

public class AccountsMerge
{
    /*
    Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

    Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

    After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

    Example 1:
    Input:
    accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
    Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    Explanation:
    The first and third John's are the same person as they have the common email "johnsmith@mail.com".
    The second John and Mary are different people as none of their email addresses are used by other accounts.
    We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
    ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
    Note:

    The length of accounts will be in the range [1, 1000].
    The length of accounts[i] will be in the range [1, 10].
    The length of accounts[i][j] will be in the range [1, 30].
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts)
    {
        Map<String, Set<String>> graph=new HashMap<>();
        Map<String,String> name=new HashMap<>();
        for(List<String> li:accounts)
        {
            String userName=li.get(0);
            for(int i=1;i<li.size();i++)
            {
                if(!graph.containsKey(li.get(i)))
                    graph.put(li.get(i),new HashSet<>());
                name.put(li.get(i),userName);
                if(i==1)
                    continue;
                graph.get(li.get(i)).add(li.get(i-1));
                graph.get(li.get(i-1)).add(li.get(i));
            }
        }
        Set<String> visited=new HashSet<>();
        List<List<String>> res=new ArrayList<>();
        for(String s:name.keySet())
        {
            List<String> temp=new ArrayList<>();
            if(!visited.contains(s))
            {
                dfs(temp,graph,visited,s);
                Collections.sort(temp);
                temp.add(0,name.get(s));
                res.add(temp);
            }
        }
        return res;
    }
    public void dfs(List<String> temp,Map<String,Set<String>> graph, Set<String> visited, String s)
    {
        if(visited.contains(s))
            return;
        temp.add(s);
        visited.add(s);
        for(String str:graph.get(s))
        {
            dfs(temp,graph,visited,str);
        }
    }



    public List<List<String>> accountsMerge2(List<List<String>> accounts)
    {
        Map<String,String> nameMap=new HashMap<>();
        Map<String,Set<String>> map=new HashMap<>();

        for(List<String> elem:accounts)
        {
            String name=elem.get(0);
            for(int i=1;i<elem.size();i++)
            {
                nameMap.put(elem.get(i),name);
                if(i==elem.size()-1)
                    continue;
                map.putIfAbsent(elem.get(i),new HashSet<>());
                map.putIfAbsent(elem.get(i+1),new HashSet<>());
                map.get(elem.get(i)).add(elem.get(i+1));
                map.get(elem.get(i+1)).add(elem.get(i));
            }
        }

        List<List<String>> res=new ArrayList<>();
        Set<String> visited=new HashSet<>();
        for(String s:nameMap.keySet())
        {
            if(!visited.contains(s))
            {
                List<String> temp=new ArrayList<>();
                bfs(visited,s,map,temp);
                Collections.sort(temp);
                temp.add(0,nameMap.get(s));
                res.add(new ArrayList<>(temp));
            }
        }
        return res;
    }

    public void bfs(Set<String> visited, String src, Map<String,Set<String>> map, List<String> temp)
    {
        Queue<String> q=new LinkedList<>();
        q.offer(src);
        visited.add(src);
        temp.add(src);
        while(!q.isEmpty())
        {
            String curr=q.poll();
            if(!map.containsKey(curr) || map.get(curr)==null)
                continue;
            for(String nxt:map.get(curr))
            {
                if(visited.contains(nxt))
                    continue;
                q.offer(nxt);
                visited.add(nxt);
                temp.add(nxt);
            }
        }
    }
    public static void main(String[] args)
    {
        List<List<String>> accounts=new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        AccountsMerge obj=new AccountsMerge();
        System.out.println(obj.accountsMerge2(accounts));
    }
}
