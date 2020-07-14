import java.util.*;

public class IsUglyII
{
    /*
    Write a program to find the n-th ugly number.

    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

    Example:

    Input: n = 10
    Output: 12
    Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
    Note:

    1 is typically treated as an ugly number.
    n does not exceed 1690.
     */

    int[] nums=new int[1690];
    int[] primes={2,3,5};
    public int nthUglyNumber(int n)
    {
        PriorityQueue<Long> pq=new PriorityQueue<>();
        Set<Long> seen=new HashSet<>();
        pq.offer(1L);
        seen.add(1L);

        for(int i=0;i<nums.length;i++)
        {
            Long curr=pq.poll();
            nums[i]=curr.intValue();
            for(int j:primes)
            {
                Long nxt=curr*j;
                if(!seen.contains(nxt))
                {
                    seen.add(nxt);
                    pq.offer(nxt);
                }
            }
        }
        return nums[n-1];
    }
    public static void main(String[] args)
    {
        IsUglyII obj=new IsUglyII();
        System.out.println(obj.nthUglyNumber(1352));
    }
}
