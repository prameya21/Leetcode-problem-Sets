import java.util.*;
public class KSImilarString
{
    /*
    Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.

    Given two anagrams A and B, return the smallest K for which A and B are K-similar.

    Example 1:

    Input: A = "ab", B = "ba"
    Output: 1
    Example 2:

    Input: A = "abc", B = "bca"
    Output: 2
    Example 3:

    Input: A = "abac", B = "baca"
    Output: 2
    Example 4:

    Input: A = "aabc", B = "abca"
    Output: 2
     */

    public int kSimilarity(String A, String B)
    {
        if(A==null && B==null)
            return 0;
        Queue<String> q=new LinkedList<>();
        Set<String> visited=new HashSet<>();
        visited.add(A);
        q.offer(A);
        int ctr=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String curr=q.poll();
                if(curr.equals(B))
                    return ctr;
                for(String next:getNeighbors(curr,B))
                {
                    if(visited.contains(next))
                        continue;
                    visited.add(next);
                    q.offer(next);
                }
            }
            ctr++;
        }
        return 0;
    }

    public List<String> getNeighbors(String A, String B)
    {
        int i=0;
        char[] ca=A.toCharArray();
        char[] cb=B.toCharArray();
        List<String> temp=new ArrayList<>();
        while(ca[i]==cb[i])
            i++;
        for(int j=i+1;j<ca.length;j++)
        {
            if(cb[i]==ca[j])
            {
                swap(ca,i,j);
                temp.add(new String(ca));
                swap(ca,i,j);
            }
        }
        return temp;
    }
    public void swap(char[] ca, int i, int j)
    {
        char temp=ca[i];
        ca[i]=ca[j];
        ca[j]=temp;

    }

    public static void main(String[] args)
    {
        KSImilarString obj=new KSImilarString();
        System.out.println(obj.kSimilarity("aabc","abca"));
    }
}
