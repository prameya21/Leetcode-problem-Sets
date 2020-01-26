import java.util.Stack;

public class ShortestContinuousUnsortedSubarray
{
    /*
    Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

    You need to find the shortest such subarray and output its length.

    Example 1:
    Input: [2, 6, 4, 8, 10, 9, 15]
    Output: 5
    Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
    Note:
    Then length of the input array is in range [1, 10,000].
    The input array may contain duplicates, so ascending order here means <=.
     */

    public int findUnsortedSubarray(int[] nums)
    {
        Stack<Integer> st=new Stack<Integer>();
        int l=nums.length,r=0;
        for(int i=0;i<nums.length;i++)
        {
            while(!st.isEmpty() && nums[st.peek()]>nums[i])
                l=Math.min(l,st.pop());
            st.push(i);
        }
        st.clear();
        for(int i=nums.length-1;i>=0;i--)
        {
            while(!st.isEmpty() && nums[st.peek()]<nums[i])
                r=Math.max(r,st.pop());
            st.push(i);
        }
        return r-l>0?r-l+1:0;
    }
    public static void main(String[] args)
    {
        ShortestContinuousUnsortedSubarray obj=new ShortestContinuousUnsortedSubarray();
        System.out.println(obj.findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
    }
}
