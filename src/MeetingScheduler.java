import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MeetingScheduler
{
    /*
        Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

        If there is no common time slot that satisfies the requirements, return an empty array.

        The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

        It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.



        Example 1:

        Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
        Output: [60,68]
        Example 2:

        Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
        Output: []
    */

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration)
    {
        List<Integer> res=new ArrayList<>();
        if(slots1==null || slots2==null)
            return res;
        Arrays.sort(slots1, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });

        Arrays.sort(slots2, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });

        int i=0,j=0;
        while(i<slots1.length && j<slots2.length)
        {
            int s=Math.max(slots1[i][0],slots2[j][0]);
            int e=Math.min(slots1[i][1],slots2[j][1]);
            if(s+duration<=e)
            {
                res.add(s);
                res.add(s+duration);
                return res;
            }
            if(slots1[i][0]<e)
                i++;
            else j++;
        }
        return res;
    }
    public static void main(String[] args)
    {
        MeetingScheduler obj=new MeetingScheduler();
        System.out.println(obj.minAvailableDuration(new int[][]{{10,50},{60,120},{140,210}},new int[][]{{0,15},{60,70}},8));
    }
}
