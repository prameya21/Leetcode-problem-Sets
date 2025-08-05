import java.util.*;

public class AlienDictionary
{
    /*
    269
    There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

    Example 1:

    Input:
    [
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"
    ]

    Output: "wertf"
    Example 2:

    Input:
    [
      "z",
      "x"
    ]

    Output: "zx"
    Example 3:

    Input:
    [
      "z",
      "x",
      "z"
    ]

    Output: ""

    Explanation: The order is invalid, so return "".
     */
    public static String alienOrderBFSTopologicalSort(String[] words)
    {
        Map<Character,Integer> degree=new HashMap<>();
        Map<Character,Set<Character>> graph=new HashMap<>();
        for(String w:words)
        {
            for(char c:w.toCharArray())
            {
                degree.put(c,0);
                if(!graph.containsKey(c))
                    graph.put(c,new HashSet<>());

            }
        }
        for(int i=0;i<words.length-1;i++)
        {
            String word1=words[i];
            String word2=words[i+1];
            int len=Math.min(word1.length(),word2.length());
            for(int k=0;k<len;k++)
            {
                char c1=word1.charAt(k);
                char c2=word2.charAt(k);
                if(c1!=c2)
                {
                    Set s=graph.get(c1);
                    if(!s.contains(c2))
                    {
                        s.add(c2);
                        graph.put(c1,s);
                        degree.put(c2,degree.get(c2)+1);
                    }
                    break;
                }
            }
        }
        Set<Character> visited=new HashSet<>();
        Queue<Character> q=new LinkedList<>();
        StringBuilder sb=new StringBuilder();
        for(char c: degree.keySet())
            if(degree.get(c)==0)
            {
                q.offer(c);
                visited.add(c);
            }

        while(!q.isEmpty())
        {
            char curr=q.poll();
            sb.append(curr);
            for(char ch: graph.get(curr))
            {
                if(!visited.contains(ch))
                {
                    degree.put(ch,degree.get(ch)-1);
                    if(degree.get(ch)==0)
                    {
                        visited.add(ch);
                        q.offer(ch);
                    }
                }
            }
        }
        return visited.size()==degree.size()?sb.toString():"";
    }
    public static String alienOrderDFSTopologicalSort(String[] words)
    {
        boolean[][] adj=new boolean[26][26];
        int[] visited=new int[26];
        Stack<Character> st=new Stack<>();
        Arrays.fill(visited,-1);
        for(int i=0;i<words.length;i++)
        {
            for(char c: words[i].toCharArray())
                visited[c-'a']=0;
        }
        for(int i=0;i<words.length-1;i++)
        {
            int len=Math.min(words[i].length(),words[i+1].length());
            for(int j=0;j<len;j++)
            {
                char c1=words[i].charAt(j);
                char c2=words[i+1].charAt(j);
                if(c1!=c2)
                {
                    adj[c1-'a'][c2-'a']=true;
                    break;
                }
            }
        }
        for(int i=0;i<26;i++)
        {
            if(visited[i]==0)
            {
                if(!dfs(adj,visited,st,i))
                    return "";
            }
        }
        StringBuilder sb=new StringBuilder();
        while(!st.isEmpty())
            sb.append(st.pop());
        return sb.toString();
    }
    public static boolean dfs(boolean[][] adj,int[] visited,Stack<Character> st,int i)
    {
        visited[i]=1;
        for(int j=0;j<26;j++)
        {
            if(adj[i][j])
            {
                if(visited[j]==1)
                    return false;
                if(visited[j]==0 && !dfs(adj,visited,st,j))
                    return false;

            }
        }
        visited[i]=2;
        char c=(char)(i+'a');
        st.push(c);
        return true;
    }

    public static void main(String args[])
    {
        System.out.println(alienOrderBFSTopologicalSort(new String[]{"wrt","wrf","er","ett","rftt"}));
        System.out.println(alienOrderDFSTopologicalSort(new String[]{"caa","aaa","aab"}));
    }
}
