import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class QueueReconstruction
{
    /*
        Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
        where h is the height of the person and k is the number of people in front of this person who have a height greater than
        or equal to h. Write an algorithm to reconstruct the queue.

        Note:
        The number of people is less than 1,100.


        Example

        Input:
        [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

        Output:
        [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */

    public int[][] reconstructQueue(int[][] people)
    {
        if(people==null || people.length==0)
            return new int[0][];

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b)
            {
                int cmp= Integer.compare(b[0],a[0]);
                if(cmp==0)
                    return Integer.compare(a[1],b[1]);
                return cmp;
            }
        });

        System.out.println(Arrays.deepToString(people));

        ArrayList<int[]> res=new ArrayList<>();
        for(int[] i:people)
            res.add(i[1],i);


        return res.toArray(new int[res.size()][]);
    }


    public static void main(String[] args)
    {
        QueueReconstruction obj=new QueueReconstruction();
        System.out.println(obj.reconstructQueue(new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}}));
    }
}
