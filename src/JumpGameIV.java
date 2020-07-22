import java.util.*;

public class JumpGameIV
{
    /*
    Given an array of integers arr, you are initially positioned at the first index of the array.

    In one step you can jump from index i to index:

    i + 1 where: i + 1 < arr.length.
    i - 1 where: i - 1 >= 0.
    j where: arr[i] == arr[j] and i != j.
    Return the minimum number of steps to reach the last index of the array.

    Notice that you can not jump outside of the array at any time.



    Example 1:

    Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
    Output: 3
    Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
    Example 2:

    Input: arr = [7]
    Output: 0
    Explanation: Start index is the last index. You don't need to jump.
    Example 3:

    Input: arr = [7,6,9,6,9,6,9,7]
    Output: 1
    Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
    Example 4:

    Input: arr = [6,1,9]
    Output: 2
    Example 5:

    Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
    Output: 3


    Constraints:

    1 <= arr.length <= 5 * 10^4
    -10^8 <= arr[i] <= 10^8
     */


    public int minJumps(int[] arr)
    {
        if(arr==null || arr.length==0)
            return -1;
        Map<Integer, List<Integer>> map=new HashMap<>();
        for(int i=0;i<arr.length;i++)
        {
            map.putIfAbsent(arr[i],new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        boolean[] visited=new boolean[arr.length];
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        visited[0]=true;
        int ctr=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int curr=q.poll();
                if(curr==arr.length-1)
                    return ctr;
                List<Integer> temp=map.get(arr[curr]);
                temp.add(curr+1);
                temp.add(curr-1);
                for(int idx:temp)
                {
                    if(idx<0 || idx>=arr.length || idx==curr || visited[idx])
                        continue;
                    q.offer(idx);
                    visited[idx]=true;
                }
                temp.clear();
            }
            ctr++;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        JumpGameIV obj=new JumpGameIV();
        System.out.println(obj.minJumps(new int[]{11,22,7,7,7,7,7,7,7,22,13}));
        System.out.println(obj.minJumps(new int[]{6,1,9}));
    }
}
