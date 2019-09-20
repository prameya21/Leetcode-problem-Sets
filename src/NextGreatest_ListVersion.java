import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class NextGreatest_ListVersion
{
    public static int[] nextGreat(List<Integer> list)
    {
        int[] res=new int[list.size()];
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<list.size();i++)
        {
            while(!st.isEmpty() && list.get(st.peek())<=list.get(i))
                res[st.pop()]=list.get(i);
            st.push(i);
        }
        return res;
    }
    public static void main(String[] args)
    {
        List<Integer> list=new ArrayList<>(Arrays.asList(1,7,5,1,9,2,5,1));
        for(int i:nextGreat(list))
            System.out.println(i);
    }
}
