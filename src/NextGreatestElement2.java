import java.util.Stack;

public class NextGreatestElement2
{
    /*
    Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

    Example 1:
    Input: [1,2,1]
    Output: [2,-1,2]
    Explanation: The first 1's next greater number is 2;
    The number 2 can't find next greater number;
    The second 1's next greater number needs to search circularly, which is also 2.
     */
    public static int[] nextGreatElements(int[] nums)
    {
        Stack<Integer> st=new Stack<>();
        int[] res=new int[nums.length];
        for(int i=2*nums.length-1;i>=0;i--)
        {
            while(!st.isEmpty() && nums[st.peek()]<=nums[i%nums.length])
                st.pop();
            res[i%nums.length]=st.isEmpty()?-1:nums[st.peek()];
            st.push(i%nums.length);
        }
        return res;
    }
    public static void main(String[] args)
    {
        int[] ans=nextGreatElements(new int[]{3,8,4,1,2});
        for(int i:ans)
            System.out.println(i);
    }
}
