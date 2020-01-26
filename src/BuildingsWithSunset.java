import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuildingsWithSunset
{
    /*
        You are given with a series of buildings that have windows facing west. The buildings are in a straight line, and any building which is to the east of a building of equal or greater height cannot view the sunset.
        Design an algorithm that processes buildings in east-to-west order and returns the set of buildings which view the sunset. Each building is specified by its height.
     */

    public List<Integer> solution(int[] nums)
    {
        Stack<Integer> st=new Stack<>();
        for(int i=nums.length-1;i>=0;i--)
        {
            while(!st.isEmpty() && nums[st.peek()]<=nums[i])
                st.pop();
            st.push(i);
        }
        return new ArrayList<Integer>(st);
    }
    public static void main(String[] args)
    {
        BuildingsWithSunset obj=new BuildingsWithSunset();
        System.out.println(obj.solution(new int[]{3,4,6,4,3,4,5,6}));
    }
}
