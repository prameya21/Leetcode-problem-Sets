import java.util.*;

public class PerfectSquares
{
    /*
    Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

    Example 1:

    Input: n = 12
    Output: 3
    Explanation: 12 = 4 + 4 + 4.
    Example 2:

    Input: n = 13
    Output: 2
    Explanation: 13 = 4 + 9.
     */

    public int numSquares(int n)
    {
        int[] dp=new int[n+1];
        for(int i=0;i<=n;i++)
        {
            dp[i]=i;
            for(int j=1;j*j<=i;j++)
            {
                dp[i]=Math.min(dp[i-j*j]+1,dp[i]);
            }
        }
        return dp[n];
    }


    public int numSquares2(int n)
    {
        if(n<=0)
            return 0;
        List<Integer> squares=new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            if(i*i<=n)
                squares.add(i*i);
        }
        Queue<Integer> q=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();
        q.offer(n);

        int depth=0;
        while(!q.isEmpty())
        {

            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int curr=q.poll();
                visited.add(curr);
                if(curr==0)
                    return depth;
                for(int s:squares)
                {
                    if(!visited.contains(curr-s))
                        q.offer(curr-s);
                }
            }
            depth++;
        }

        return 0;
    }


    public int numSquares3(int n)
    {
        if (n <= 0) return 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        int depth = 0;
        while (!q.isEmpty())
        {
            depth++;
            int size = q.size(); // nodes at current level
            for (int i = 0; i < size; i++)
            {
                int cur = q.poll();
                for (int j = 1; j*j <= cur; j++)
                {
                    int rest = cur - j*j;
                    if (rest == 0)  // found
                        return depth;
                    else
                        q.offer(rest);

                }
            }
        }
        return depth; // it won't reach here usually, it will return in the while loop
    }







    public static void main(String[] args)
    {
        PerfectSquares obj=new PerfectSquares();
        System.out.print(obj.numSquares3(7168));
    }
}
