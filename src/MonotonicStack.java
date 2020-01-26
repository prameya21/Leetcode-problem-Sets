import java.util.Stack;

public class MonotonicStack
{
    public void increasing(int[] nums)
    {
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<nums.length;i++)
        {
            while(!st.isEmpty() && nums[st.peek()]>=nums[i])
                st.pop();
            st.push(i);
        }
        while(!st.isEmpty())
            System.out.print(nums[st.pop()]+",");
        System.out.println("\n\n\n");
    }

    public void decreasing(int[] nums)
    {
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<nums.length;i++)
        {
            while(!st.isEmpty() && nums[st.peek()]<=nums[i])
                st.pop();
            st.push(i);
        }
        while(!st.isEmpty())
            System.out.print(nums[st.pop()]+",");
        System.out.println("\n\n\n");
    }

    public static void main(String[] args)
    {
        MonotonicStack obj=new MonotonicStack();
        obj.increasing(new int[]{5,3,1,2,4});
        obj.decreasing(new int[]{5,3,1,2,4});
    }
}
