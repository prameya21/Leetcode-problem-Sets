import java.util.Arrays;

public class RotateBox
{
    /*
        1861
        You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
        A stone '#'
        A stationary obstacle '*'
        Empty '.'
        The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity.
        Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box.
        Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
        It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
        Return an n x m matrix representing the box after the rotation described above.

        Example 1:
        Input: box = [["#",".","#"]]
        Output: [["."],
                 ["#"],
                 ["#"]]

        Example 2:
        Input: box = [["#",".","*","."],
                      ["#","#","*","."]]
        Output: [["#","."],
                 ["#","#"],
                 ["*","*"],
                 [".","."]]

        Example 3:
        Input: box = [["#","#","*",".","*","."],
                      ["#","#","#","*",".","."],
                      ["#","#","#",".","#","."]]
        Output: [[".","#","#"],
                 [".","#","#"],
                 ["#","#","*"],
                 ["#","*","."],
                 ["#",".","*"],
                 ["#",".","."]]

        Constraints:

        m == box.length
        n == box[i].length
        1 <= m, n <= 500
        box[i][j] is either '#', '*', or '.'.
     */

    public char[][] rotateTheBox(char[][] box)
    {
        if(box==null || box.length==0)
            return box;
        for(char[] ch:box)
            helper(ch);
        char[][] res=new char[box[0].length][box.length];
        for(int i=0;i<box.length;i++)
            for(int j=0;j<box[0].length;j++)
                res[j][box.length-1-i]=box[i][j];

        return res;
    }

    public void helper(char[] ch)
    {
        int i=ch.length-1, j=ch.length-1;
        while(j>=0)
        {
            if(ch[j]=='*')
            {
                i=j-1;
            }
            else if(ch[j]=='#')
            {
                char c=ch[i];
                ch[i]=ch[j];
                ch[j]=c;
                i--;
            }
            j--;
        }
    }

    public static void main(String[] args)
    {
        RotateBox obj=new RotateBox();
        char[][] res=obj.rotateTheBox(new char[][]{{'#','.','#'}});
        System.out.println(Arrays.toString(res[0]));
    }
}
