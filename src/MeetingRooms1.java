import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms1
{
    /*
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

    Example 1:

    Input: [[0,30],[5,10],[15,20]]
    Output: false
    Example 2:

    Input: [[7,10],[2,4]]
    Output: true

     */
    public boolean canAttendMeetings(int[][] intervals)
    {
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] a,int[] b)
            {
                return a[0]-b[0];
            }
        });
        for(int i=0;i<intervals.length-1;i++)
        {
            if(intervals[i][1]>intervals[i+1][0])
                return false;
        }
        return true;
    }
    public boolean canAttendMeetings2(int[][] intervals)
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
        int count=0,j=0;
        for(int i=1;i<start.length;i++)
        {
            if(start[i]<end[j])
                count++;
            else
                    j++;
        }
        return count==0;
    }
    public static void main(String[] args)
    {
        System.out.println(new MeetingRooms1().canAttendMeetings2(new int[][]{{7,10},{2,4}}));
    }
}
