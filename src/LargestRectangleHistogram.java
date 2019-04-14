import java.util.Stack;

public class LargestRectangleHistogram
{
    /*
    Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


    Example:

    Input: [2,1,5,6,2,3]
    Output: 10
     */
    public static void main(String[] args)
    {
        int[] hist={2,1,5,6,2,3};
        System.out.println(largestRectangleArea(hist));

    }
    public static int largestRectangleArea(int[] hist)
    {
        Stack<Integer> st=new Stack<>();
        int i=0;
        int max_area=0;
        while(i<hist.length)
        {
            if(st.isEmpty() || hist[st.peek()]<=hist[i])
                st.push(i++);
            else
            {
                int tp=st.peek();
                st.pop();
                int area_with_top=hist[tp]*(st.isEmpty()?i:i-st.peek()-1);
                max_area=Math.max(area_with_top,max_area);
            }
        }
        while(!st.isEmpty())
        {
            int area=hist[st.pop()] * (st.isEmpty()?i:i-st.peek()-1);
            max_area=Math.max(area,max_area);
        }
        return max_area;
    }

}
