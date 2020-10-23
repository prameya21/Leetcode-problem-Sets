import java.util.*;

public class EmployeeFreeTime
{
    /*
        We are given a list schedule of employees, which represents the working time for each employee.

        Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

        Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

        (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
        For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.



        Example 1:

        Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
        Output: [[3,4]]
        Explanation: There are a total of three employees, and all common
        free time intervals would be [-inf, 1], [3, 4], [10, inf].
        We discard any intervals that contain inf as they aren't finite.
        Example 2:

        Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
        Output: [[5,6],[7,9]]
     */

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule)
    {
        if(schedule==null || schedule.size()==0)
            return new ArrayList<>();
        PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b)
            {
                return a.start-b.start;
            }
        });
        for(List<Interval> temp:schedule)
            pq.addAll(temp);

        List<Interval> res=new ArrayList<>();
        Interval temp=pq.poll();
        while(!pq.isEmpty())
        {
            if(temp.end<pq.peek().start)
            {
                res.add(new Interval(temp.end,pq.peek().start));
                temp=pq.poll();
            }
            else
            {
                if(temp.end<pq.peek().end)
                    temp=pq.peek();
                pq.poll();
            }
        }
        return res;
    }

    public static  void main(String[] args)
    {
        List<List<Interval>> schedule=new ArrayList<>();
        List<Interval> t1=new ArrayList<>();
        t1.add(new Interval(1,2));
        t1.add(new Interval(5,6));
        schedule.add(t1);
        List<Interval> t2=new ArrayList<>();
        t1.add(new Interval(1,3));
        schedule.add(t2);
        List<Interval> t3=new ArrayList<>();
        t1.add(new Interval(4,10));
        schedule.add(t3);
        EmployeeFreeTime obj=new EmployeeFreeTime();
        System.out.println(obj.employeeFreeTime(schedule));
    }
}
