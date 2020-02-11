import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KWeakestRow
{
    /*
    Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.

    A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j.
    Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.
    Example 1:

    Input: mat =
    [[1,1,0,0,0],
     [1,1,1,1,0],
     [1,0,0,0,0],
     [1,1,0,0,0],
     [1,1,1,1,1]],
    k = 3
    Output: [2,0,3]
    Explanation:
    The number of soldiers for each row is:
    row 0 -> 2
    row 1 -> 4
    row 2 -> 1
    row 3 -> 2
    row 4 -> 5
    Rows ordered from the weakest to the strongest are [2,0,3,1,4]

    Example 2:
    Input: mat =
    [[1,0,0,0],
     [1,1,1,1],
     [1,0,0,0],
     [1,0,0,0]],
    k = 2
    Output: [0,2]
    Explanation:
    The number of soldiers for each row is:
    row 0 -> 1
    row 1 -> 4
    row 2 -> 1
    row 3 -> 1
    Rows ordered from the weakest to the strongest are [0,2,3,1]

    Constraints:
    m == mat.length
    n == mat[i].length
    2 <= n, m <= 100
    1 <= k <= m
    matrix[i][j] is either 0 or 1.
     */

    public int[] kWeakestRows(int[][] mat, int k)
    {
        PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b)
            {
                int cmp=Integer.compare(a[1],b[1]);
                if(cmp==0)
                    return Integer.compare(a[0],b[0]);
                return cmp;
            }
        });
        for(int i=0;i<mat.length;i++)
        {
            int count=0;
            for(int j=0;j<mat[0].length;j++)
            {
                if(mat[i][j]==1)
                    count++;
            }
            pq.offer(new int[]{i,count});
        }
        /*
        System.out.println("For loop");
        for(int[] i:pq)
            System.out.println(i[0]+","+i[1]);

        System.out.println();
        System.out.println("While loop");
        while(!pq.isEmpty())
        {
            int[] i=pq.poll();
            System.out.println(i[0]+","+i[1]);
        }
        */
        int[] res=new int[k];
        for(int i=0;i<k;i++)
            res[i]=pq.poll()[0];

        return res;
    }


    public static void main(String[] args)
    {
        KWeakestRow obj=new KWeakestRow();
        int[][] mat={{1,1,1,0,0},{1,1,1,1,0},{0,0,0,0,0},{1,1,1,0,0},{1,1,1,1,1}};
        int[] map=obj.kWeakestRows(mat,4);
        for(int i:map)
            System.out.println(i);

    }
}
