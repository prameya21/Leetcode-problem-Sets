import java.util.Comparator;
import java.util.PriorityQueue;

public class ExamRoom
{
    /*
        In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

        When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.
        If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)
        Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.
        It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.



        Example 1:

        Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
        Output: [null,0,9,4,2,null,5]
        Explanation:
        ExamRoom(10) -> null
        seat() -> 0, no one is in the room, then the student sits at seat number 0.
        seat() -> 9, the student sits at the last seat number 9.
        seat() -> 4, the student sits at the last seat number 4.
        seat() -> 2, the student sits at the last seat number 2.
        leave(4) -> null
        seat() -> 5, the student sits at the last seat number 5.
        ​​​​​​​

        Note:

        1 <= N <= 10^9
        ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
        Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
    */

    class Intervals
    {
        int start,end,length;
        public Intervals(int s, int e, int l)
        {
            start=s;
            end=e;
            length=l;
        }
    }

    PriorityQueue<Intervals> pq;
    int count;

    public ExamRoom(int N)
    {
        pq=new PriorityQueue<Intervals>(new Comparator<Intervals>(){
            public int compare(Intervals a, Intervals b)
            {
                int cmp=Integer.compare(b.length,a.length);
                if(cmp==0)
                    return Integer.compare(a.start,b.start);
                return cmp;
            }
        });
        count=N;
        pq.offer(new Intervals(-1,N,N));
    }
    public int seat()
    {
        if(pq.isEmpty())
            return -1;
        Intervals curr=pq.poll();
        if(curr.start==-1)
        {
            pq.offer(new Intervals(0,curr.end,curr.length-1));
            return 0;
        }
        else if(curr.end==count)
        {
            pq.offer(new Intervals(curr.start,count-1,curr.length-1));
            return count-1;
        }
        else
        {
            int seat=curr.start+(curr.end-curr.start)/2;
            pq.offer(new Intervals(curr.start,seat,(seat-curr.start)/2));
            pq.offer(new Intervals(seat,curr.end,(curr.end-seat)/2));
            return seat;
        }
    }

    public void leave(int p)
    {
        Intervals prev=null;
        Intervals curr=null;
        for(Intervals i:pq)
        {
            if(i.end==p)
                prev=i;
            if(i.start==p)
                curr=i;
        }
        if (prev == null && curr != null)
        {
            pq.remove(curr);
            pq.offer(new Intervals(-1, curr.end, curr.end - curr.start));
        }
        else if (prev != null && curr == null)
        {
            pq.remove(prev);
            pq.offer(new Intervals(prev.start, count, prev.end - prev.start));
        }
        else
        {
            pq.remove(curr);
            pq.remove(prev);
            if (prev.start == -1 || curr.end == count)
            {
                pq.offer(new Intervals(prev.start, curr.end, curr.end - prev.start));
            }
            else
            {
                pq.offer(new Intervals(prev.start, curr.end, (curr.end - prev.start) / 2));
            }
        }
    }

    public static void main(String[] args)
    {
        ExamRoom obj=new ExamRoom(10);
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        obj.leave(4);
        System.out.println(obj.seat());
    }

}
