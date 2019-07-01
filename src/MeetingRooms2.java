import java.util.Arrays;

public class MeetingRooms2
{
    /*
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

    Example 1:

    Input: [[0, 30],[5, 10],[15, 20]]
    Output: 2
    Example 2:

    Input: [[7,10],[2,4]]
    Output: 1
    */
    public int minMeetingRooms(int[][] intervals)
    {
        int[] start=new int[intervals.length];
        int[] end=new int[intervals.length];
        for(int i=0;i<intervals.length;i++)
        {
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int count=0,j=0,i=0;
        while(i<start.length)
        {
            if(start[i]>=end[j])
            {
                count--;
                j++;
            }
            i++;
            count++;
        }
        return count;
    }
    public static void main(String[] args)
    {
        System.out.println(new MeetingRooms2().minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(new MeetingRooms2().minMeetingRooms(new int[][]{{7,10},{2,4}}));
    }
}
