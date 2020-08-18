import java.util.HashSet;
import java.util.Set;

public class SimilarStringGroups
{
    /*
    Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

    For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

    Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.
    Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

    We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?



    Example 1:

    Input: A = ["tars","rats","arts","star"]
    Output: 2


    Constraints:

    1 <= A.length <= 2000
    1 <= A[i].length <= 1000
    A.length * A[i].length <= 20000
    All words in A consist of lowercase letters only.
    All words in A have the same length and are anagrams of each other.
    The judging time limit has been increased for this question.
     */

    public int numSimilarGroups(String[] A)
    {
        if(A==null || A.length==0)
            return 0;
        Set<String> visited=new HashSet<>();
        int ctr=0;
        for(String s:A)
        {
            if(visited.contains(s))
                continue;
            dfs(A,visited,s);
            ctr++;
        }
        return ctr;
    }

    public void dfs(String[] A, Set<String> visited, String s)
    {
        if(visited.contains(s))
            return;
        visited.add(s);
        for(String nxt:A)
            if(helper(s,nxt))
                dfs(A,visited,nxt);

    }

    public boolean helper(String s1, String s2)
    {
        int ctr=0;
        for(int i=0;i<s1.length();i++)
            if(s1.charAt(i)!=s2.charAt(i))
                ctr++;
        return ctr==2;
    }

    public int numSimilarGroups2(String[] A)
    {
        if(A==null || A.length==0)
            return 0;
        int[] roots=new int[A.length];
        for(int i=0;i<roots.length;i++)
            roots[i]=i;
        for(int i=0;i<A.length;i++)
        {
            for(int j=0;j<A.length;j++)
            {
                if(helper(A[i],A[j]))
                    union(i,j,roots);
            }
        }
        int res=0;
        for(int i=0;i<roots.length;i++)
            if(roots[i]==i)
                res++;
            return res;
    }
    public int find(int[] roots, int parent)
    {
        if(roots[parent]!=parent)
            roots[parent]=find(roots,roots[parent]);
        return roots[parent];
    }

    public void union(int i, int j, int[] roots)
    {
        int irep=find(roots,i);
        int jrep=find(roots,j);
        if(irep!=jrep)
            roots[jrep]=irep;
    }
    public static void main(String[] args)
    {
        SimilarStringGroups obj=new SimilarStringGroups();
        System.out.println(obj.numSimilarGroups2(new String[]{"tars","rats","arts","star"}));
    }
}
