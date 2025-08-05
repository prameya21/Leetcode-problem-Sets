import java.util.Arrays;
import java.util.LinkedList;

public class PowerOfKSizeSubArrays1
{
    /*
        3254
        You are given an array of integers nums of length n and a positive integer k.
        The power of an array is defined as:
        Its maximum element if all of its elements are consecutive and sorted in ascending order.
        -1 otherwise.
        You need to find the power of all
        subarrays
         of nums of size k.
        Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].

        Example 1:
        Input: nums = [1,2,3,4,3,2,5], k = 3
        Output: [3,4,-1,-1,-1]
        Explanation:
        There are 5 subarrays of nums of size 3:
        [1, 2, 3] with the maximum element 3.
        [2, 3, 4] with the maximum element 4.
        [3, 4, 3] whose elements are not consecutive.
        [4, 3, 2] whose elements are not sorted.
        [3, 2, 5] whose elements are not consecutive.

        Example 2:
        Input: nums = [2,2,2,2,2], k = 4
        Output: [-1,-1]
        Example 3:
        Input: nums = [3,2,3,2,3,2], k = 2
        Output: [-1,3,-1,3,-1]
        Constraints:

        1 <= n == nums.length <= 500
        1 <= nums[i] <= 105
        1 <= k <= n
     */

    public int[] resultsArray(int[] nums, int k)
    {
        int[] res=new int[nums.length-k+1];
        LinkedList<Integer> q=new LinkedList<>();
        for(int i=0;i<nums.length;i++)
        {
            if(!q.isEmpty() && q.peekFirst()<i-k+1)
                q.pollFirst();
            if(!q.isEmpty() && nums[i]!=nums[i-1]+1)
                q.clear();
            q.offerLast(i);
            if(i>=k-1)
            {
                if(q.size()==k)
                    res[i-k+1]=nums[q.peekLast()];
                else
                    res[i-k+1]=-1;
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        PowerOfKSizeSubArrays1 obj=new PowerOfKSizeSubArrays1();
        System.out.println(Arrays.toString(obj.resultsArray(new int[]{1,2,3,4,3,2,5}, 3)));
    }
}
