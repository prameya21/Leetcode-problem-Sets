import java.util.ArrayList;
import java.util.List;

public class MyCalendar
{
    /*
    Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

    Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

    A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

    For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

    Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
    Example 1:

    MyCalendar();
    MyCalendar.book(10, 20); // returns true
    MyCalendar.book(15, 25); // returns false
    MyCalendar.book(20, 30); // returns true
    Explanation:
    The first event can be booked.  The second can't because time 15 is already booked by another event.
    The third event can be booked, as the first event takes every time less than 20, but not including 20.
     */

    class MyCalendar1
    {
        List<int[]> booking;
        public MyCalendar1()
        {
            booking=new ArrayList<>();
        }

        public boolean book(int start, int end)
        {
            for(int[] i:booking)
            {
                if(Math.max(i[0],start)<Math.min(i[1],end))
                    return false;
            }
            booking.add(new int[]{start,end});
            return true;
        }
    }

    class TreeNode
    {
        int start,end;
        TreeNode left,right;
        TreeNode(int s, int e)
        {
            left=null;
            right=null;
            start=s;
            end=e;
        }
    }

    class MyCalendar2
    {
        TreeNode root;
        boolean inserted;
        public MyCalendar2()
        {
            inserted=false;
            root=null;
        }
        public TreeNode insert(TreeNode node, int start, int end)
        {
            if(node==null)
            {
                inserted=true;
                return new TreeNode(start,end);
            }
            if(node.start>=end)
                node.left=insert(node.left,start,end);
            else if(node.end<=start)
                node.right=insert(node.right,start,end);
            return node;
        }
        public boolean book(int start, int end)
        {
            if(end<start || start<0)
                return false;
            root=insert(root,start,end);
            if(inserted)
            {
                inserted=false;
                return true;
            }
            else
                return false;
        }
    }

    public void test(int[][] data)
    {
        MyCalendar2 obj=new MyCalendar2();
        for(int[] i: data)
            obj.book(i[0],i[1]);
    }

    public static void main(String[] args)
    {
        MyCalendar obj=new MyCalendar();
        obj.test(new int[][]{{10,20},{15,25},{20,30}});
    }
}
