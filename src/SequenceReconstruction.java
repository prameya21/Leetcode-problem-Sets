import java.util.*;
public class SequenceReconstruction
{
    /*
    Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104.
    Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
    Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

    Example 1:
    Input: org = [1,2,3], seqs = [[1,2],[1,3]]
    Output: false
    Explanation: [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

    Example 2:
    Input: org = [1,2,3], seqs = [[1,2]]
    Output: false
    Explanation: The reconstructed sequence can only be [1,2].

    Example 3:
    Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
    Output: true
    Explanation: The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

    Example 4:
    Input: org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
    Output: true


    Constraints:

    1 <= n <= 10^4
    org is a permutation of {1,2,...,n}.
    1 <= segs[i].length <= 10^5
    seqs[i][j] fits in a 32-bit signed integer.
     */

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs)
    {
        Map<Integer,Integer> inDegree=new HashMap<>();
        Map<Integer,Set<Integer>> graph=new HashMap<>();

        for(List<Integer> s:seqs)
        {
            if(s.size()==1)
            {
                inDegree.putIfAbsent(s.get(0),0);
                graph.putIfAbsent(s.get(0),new HashSet<>());
                continue;
            }
            for(int i=0;i<s.size()-1;i++)
            {
                inDegree.putIfAbsent(s.get(i),0);
                inDegree.putIfAbsent(s.get(i+1),0);
                graph.putIfAbsent(s.get(i),new HashSet<>());
                graph.putIfAbsent(s.get(i+1),new HashSet<>());
                if(graph.get(s.get(i)).add(s.get(i+1)))
                    inDegree.put(s.get(i+1),inDegree.get(s.get(i+1))+1);
            }
        }

        Queue<Integer> q=new LinkedList<>();
        for(int i:inDegree.keySet())
            if(inDegree.get(i)==0)
                q.offer(i);

        int idx=0;
        while(!q.isEmpty())
        {
            if(q.size()!=1)
                return false;
            int curr=q.poll();

            if(idx==org.length || org[idx++]!=curr)
                return false;
            for(int nxt:graph.get(curr))
            {
                inDegree.put(nxt,inDegree.get(nxt)-1);
                if(inDegree.get(nxt)==0)
                    q.offer(nxt);
            }
        }
        return idx==org.length && idx==inDegree.size();
    }

    public static void main(String[] args)
    {
        SequenceReconstruction obj=new SequenceReconstruction();
        List<List<Integer>> seq1=new ArrayList<>();
        seq1.add(Arrays.asList(new Integer[]{4,1,5,2}));
        seq1.add(Arrays.asList(new Integer[]{5,2,5,3}));
        List<List<Integer>> seq2=new ArrayList<>();
        seq2.add(Arrays.asList(new Integer[]{1,2}));
        seq2.add(Arrays.asList(new Integer[]{1,3}));

        List<List<Integer>> seq3=new ArrayList<>();
        seq3.add(Arrays.asList(new Integer[]{1}));
        seq3.add(Arrays.asList(new Integer[]{1}));
        seq3.add(Arrays.asList(new Integer[]{1}));
        System.out.println(obj.sequenceReconstruction(new int[]{1},seq3));
    }
}


