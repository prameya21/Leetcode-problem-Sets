import java.util.LinkedList;
import java.util.Queue;

public class JumpGameII
{
    /*
    Given an array of non-negative integers, you are initially positioned at the first index of the array.

    Each element in the array represents your maximum jump length at that position.

    Your goal is to reach the last index in the minimum number of jumps.

    Example:

    Input: [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2.
        Jump 1 step from index 0 to 1, then 3 steps to the last index.
     */
    public int jump(int[] nums)
    {
        int jumps=0,curr_start=0,curr_farthest=0,curr_end=0;
        for(int i=0;i<nums.length-1;i++)
        {
            curr_farthest=Math.max(curr_farthest,i+nums[i]);
            if(i==curr_end)
            {
                jumps++;
                curr_end=curr_farthest;
            }
        }
        return jumps;
    }

    public int jump2(int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;
        boolean[] visited=new boolean[nums.length];
        visited[0]=true;
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        int ctr=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int curr=q.poll();
                if(curr==nums.length-1)
                    return ctr;
                for(int idx=1;idx<=nums[curr];idx++)
                {
                    if(curr+idx>=nums.length || visited[curr+idx])
                        continue;
                    q.offer(curr+idx);
                    visited[curr+idx]=true;
                }
            }
            ctr++;
        }
        return -1;
    }
    
    public static void main(String[] args)
    {
        JumpGameII obj=new JumpGameII();
        System.out.println(obj.jump(new int[]{2,3,1,1,4}));
        System.out.println(obj.jump2(new int[]{2,3,1,1,4}));
    }
}
