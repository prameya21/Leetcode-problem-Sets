import java.util.Arrays;
import java.util.Comparator;

public class NumberOfVisibleMountains
{
    //2345
    /*
        You are given a 0-indexed 2D integer array peaks where peaks[i] = [xi, yi] states that mountain i has a peak at coordinates (xi, yi).
        A mountain can be described as a right-angled isosceles triangle, with its base along the x-axis and a right angle at its peak. More formally,
        the gradients of ascending and descending the mountain are 1 and -1 respectively.
        A mountain is considered visible if its peak does not lie within another mountain (including the border of other mountains).

        Return the number of visible mountains.
        Example 1:
        Input: peaks = [[2,2],[6,3],[5,4]]
        Output: 2
        Explanation: The diagram above shows the mountains.
        - Mountain 0 is visible since its peak does not lie within another mountain or its sides.
        - Mountain 1 is not visible since its peak lies within the side of mountain 2.
        - Mountain 2 is visible since its peak does not lie within another mountain or its sides.
        There are 2 mountains that are visible.

        Example 2:
        Input: peaks = [[1,3],[1,3]]
        Output: 0
        Explanation: The diagram above shows the mountains (they completely overlap).
        Both mountains are not visible since their peaks lie within each other.

        Constraints:
        1 <= peaks.length <= 105
        peaks[i].length == 2
        1 <= xi, yi <= 105
     */
    public int visibleMountains(int[][] peaks)
    {
        int[][] mat=new int[peaks.length][2];
        for(int i=0;i<peaks.length;i++)
        {
            mat[i][0]=peaks[i][0]-peaks[i][1];
            mat[i][1]=peaks[i][0]+peaks[i][1];
        }
        Arrays.sort(mat, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                int cmp=o1[0]-o2[0];
                return cmp==0?o2[1]-o1[1]:cmp;
            }
        });
        int res=0;
        int i=0;
        while(i<mat.length)
        {
            int j=i+1;
            if(j==mat.length || mat[i][0]!=mat[j][0] || mat[i][1]!=mat[j][1])
                res++;
            while(j<mat.length && (mat[i][0]<=mat[j][0] && mat[i][1]>=mat[j][1]))
                j++;
            i=j;
        }
        return res;
    }

    public static void main(String[] args)
    {
        NumberOfVisibleMountains obj=new NumberOfVisibleMountains();
        System.out.println(obj.visibleMountains(new int[][]{{2,2},{6,3},{5,4}}));
    }
}
