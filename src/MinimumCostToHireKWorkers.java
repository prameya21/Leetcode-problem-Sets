import javafx.scene.layout.Priority;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers
{
    /*
    There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

    Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

    Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
    Every worker in the paid group must be paid at least their minimum wage expectation.
    Return the least amount of money needed to form a paid group satisfying the above conditions.



    Example 1:

    Input: quality = [10,20,5], wage = [70,50,30], K = 2
    Output: 105.00000
    Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
    Example 2:

    Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
    Output: 30.66667
    Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
     */
    class Worker
    {
        int quality;
        double rate;
        public Worker(int q, int wage)
        {
            quality=q;
            rate=(double)(wage/quality);
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int K)
    {
        Worker[] arr=new Worker[quality.length];
        for(int i=0;i<quality.length;i++)
            arr[i]=new Worker(quality[i],wage[i]);
        Arrays.sort(arr, new Comparator<Worker>() {
            @Override
            public int compare(Worker a, Worker b) {
                if(a.rate==b.rate)
                    return a.quality-b.quality;
                else
                {
                    if(a.rate<b.rate)
                        return -1;
                    else if(a.rate>b.rate)
                        return 1;
                    else
                        return 0;
                }
            }
        });
        PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int qsum=0;
        double res=Double.MAX_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            if(pq.size()==K)
                qsum-=pq.poll();
            pq.offer(arr[i].quality);
            qsum+=arr[i].quality;
            if(i>=K-1)
                res=Math.min(res,qsum*arr[i].rate);

        }
        return res;
    }
    public static void main(String[] args)
    {
        MinimumCostToHireKWorkers obj=new MinimumCostToHireKWorkers();
        System.out.println(obj.mincostToHireWorkers(new int[]{10,20,5},new int[]{70,50,30},2));
    }
}
