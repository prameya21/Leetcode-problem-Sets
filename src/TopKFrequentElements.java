import java.util.*;

public class TopKFrequentElements
{
    /*
    Given a non-empty array of integers, return the k most frequent elements.

    Example 1:

    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
    Example 2:

    Input: nums = [1], k = 1
    Output: [1]
     */
    public static List<Integer> topKFrequent(int[] nums, int k)
    {
        Map<Integer,Integer> count=new HashMap<>();
        for(int c:nums)
            count.put(c,count.getOrDefault(c,0)+1);
        PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer n1,Integer n2)
            {
                return count.get(n1)-count.get(n2);
            }
        });
        for(int n:count.keySet())
        {
            pq.offer(n);
            if(pq.size()>k)
                pq.poll();
        }
        List<Integer> result=new ArrayList<>(pq);
        Collections.reverse(result);
        return result;
    }
    public static void main(String[] args)
    {
        System.out.println(topKFrequent(new int[]{1,1,1,2,2,3},2));
    }
}
