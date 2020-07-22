import java.util.LinkedList;
import java.util.Queue;

public class JumpGameIII
{
    /*
    Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

    Notice that you can not jump outside of the array at any time.



    Example 1:

    Input: arr = [4,2,3,0,3,1,2], start = 5
    Output: true
    Explanation:
    All possible ways to reach at index 3 with value 0 are:
    index 5 -> index 4 -> index 1 -> index 3
    index 5 -> index 6 -> index 4 -> index 1 -> index 3
    Example 2:

    Input: arr = [4,2,3,0,3,1,2], start = 0
    Output: true
    Explanation:
    One possible way to reach at index 3 with value 0 is:
    index 0 -> index 4 -> index 1 -> index 3
    Example 3:

    Input: arr = [3,0,2,1,2], start = 2
    Output: false
    Explanation: There is no way to reach at index 1 with value 0.
     */

    public boolean canReach(int[] arr, int start)
    {
        if(arr==null || arr.length==0 || start>=arr.length)
            return false;
        if(arr[start]==0)
            return true;
        boolean[] visited=new boolean[arr.length];
        Queue<Integer> q=new LinkedList<>();
        q.offer(start);
        visited[start]=true;
        while(!q.isEmpty())
        {
            int curr=q.poll();
            if(arr[curr]==0)
                return true;
            int idx1=curr-arr[curr];
            int idx2=curr+arr[curr];
            if(idx1>=0 && !visited[idx1])
            {
                q.offer(idx1);
                visited[idx1]=true;
            }
            if(idx2<arr.length && !visited[idx2])
            {
                q.offer(idx2);
                visited[idx2]=true;
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        JumpGameIII obj=new JumpGameIII();
        System.out.println(obj.canReach(new int[]{4,2,3,0,3,1,2},5));
    }
}
