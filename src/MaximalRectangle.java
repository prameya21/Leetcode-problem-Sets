import java.util.Stack;

public class MaximalRectangle
{
    /*
        Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

        Example:

        Input:
        [
          ["1","0","1","0","0"],
          ["1","0","1","1","1"],
          ["1","1","1","1","1"],
          ["1","0","0","1","0"]
        ]
        Output: 6
     */

    public int maximalRectangle(char[][] matrix)
    {
        if(matrix==null || matrix.length==0)
            return 0;
        int maxarea=0;
        int[] dp=new int[matrix[0].length];
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
                dp[j]=matrix[i][j]=='1'?dp[j]+1:0;

            maxarea=Math.max(maxarea,largetRect(dp));
        }
        return maxarea;
    }

    public int largetRect(int[] hist)
    {
        int i=0,max_area=0;
        Stack<Integer> st=new Stack<>();
        while(i<hist.length)
        {
            if(st.isEmpty() || hist[st.peek()]<=hist[i])
                st.push(i++);
            else
            {
                int tmp=st.pop();
                int area=hist[tmp]*(st.isEmpty()?i:i-st.peek()-1);
                max_area=Math.max(area,max_area);
            }
        }
        while(!st.isEmpty())
        {
            int tmp=st.pop();
            int area=hist[tmp]*(st.isEmpty()?i:i-st.peek()-1);
            max_area=Math.max(area,max_area);
        }
        return max_area;
    }

    public static void main(String[] args)
    {
        MaximalRectangle obj=new MaximalRectangle();
        System.out.println(obj.maximalRectangle(new char[][] {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
    }
}
