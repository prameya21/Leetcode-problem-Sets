import java.util.*;

public class AlienDictionary
{
    /*
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
        Map<Character, Set<Character>> adjList=new HashMap<>();
        for(String word:words)
        {
            for(char c: word.toCharArray())
            {
                if(!degree.containsKey(c))
                    degree.put(c,0);
            }
        }
        for(int i=0;i<words.length-1;i++)
        {
            String word1=words[i];
            String word2=words[i+1];
            int len=Math.min(word1.length(),word2.length());
            for(int j=0;j<len;j++)
            {
                char c1=word1.charAt(j);
                char c2=word2.charAt(j);
                if(c1!=c2)
                {
                    Set<Character> set;
                    if(adjList.containsKey(c1))
                        set=adjList.get(c1);
                    else
                        set=new HashSet<>();
                    set.add(c2);
                    adjList.put(c1,set);
                    degree.put(c2,degree.getOrDefault(c2,0)+1);
                    break;
                }
            }
        }
        Queue<Character> q=new LinkedList<>();
        for(Character c:degree.keySet())
        {
            if(degree.get(c)==0)
                q.add(c);
        }
        String result="";
        while(!q.isEmpty())
        {
            char c=q.poll();
            result+=c;
            if(adjList.containsKey(c))
            {
                for(char ch:adjList.get(c))
                {
                    degree.put(ch,degree.get(ch)-1);
                    if(degree.get(ch)==0)
                        q.offer(ch);

                }
            }
        }
        return result.length()==degree.size()?result:"";
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
