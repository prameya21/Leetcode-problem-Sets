    /*
    Design a hit counter which counts the number of hits received in the past 5 minutes.

    Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

    It is possible that several hits arrive roughly at the same time.

    Example:

    HitCounter counter = new HitCounter();

    // hit at timestamp 1.
    counter.hit(1);

    // hit at timestamp 2.
    counter.hit(2);

    // hit at timestamp 3.
    counter.hit(3);

    // get hits at timestamp 4, should return 3.
    counter.getHits(4);

    // hit at timestamp 300.
    counter.hit(300);

    // get hits at timestamp 300, should return 4.
    counter.getHits(300);

    // get hits at timestamp 301, should return 3.
    counter.getHits(301);
    */

public class HitCounter
{
    private int[] time;
    private int[] hit;
    public HitCounter()
    {
        time=new int[300];
        hit=new int[300];
    }
    public void hit(int timestamp)
    {
        int id=timestamp%300;
        if(time[id]!=timestamp)
        {
            time[id]=timestamp;
            hit[id]=1;
        }
        else
            hit[id]++;
    }
    public int getHits(int timestamp)
    {
        int total=0;
        for(int i=0;i<time.length;i++)
        {
            if(timestamp-time[i]<300)
                total+=hit[i];
        }
        return total;
    }

    public static void main(String[] args)
    {
        HitCounter counter=new HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println(counter.getHits(4));
        counter.hit(300);
        System.out.println(counter.getHits(300));
        System.out.println(counter.getHits(301));
    }
}
