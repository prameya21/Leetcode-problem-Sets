import java.util.*;

public class TaskScheduler
{
    /*
    Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
    Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
    However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
    You need to return the least number of intervals the CPU will take to finish all the given tasks.

    Example:

    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


    Note:

    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].
     */
    public int leastInterval(char[] tasks, int n)
    {
        char idle='#';
        Map<Character,Integer> freqMap=new HashMap<>();
        for(char c:tasks)
            freqMap.put(c,freqMap.getOrDefault(c,0)+1);
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(char c:freqMap.keySet())
            pq.offer(freqMap.get(c));

        int timer=0;
        while(!pq.isEmpty())
        {
            int i=0;
            List<Integer> temp=new ArrayList<>();
            while(i<=n)
            {
                if(!pq.isEmpty())
                {
                    if(pq.peek()>1)
                        temp.add(pq.poll()-1);
                    else
                        pq.poll();
                }
                timer++;
                if(pq.isEmpty() && temp.isEmpty())
                    break;
                i++;
            }
            for(int t:temp)
                pq.offer(t);
        }
        return timer;
    }


    public int leastInterval2(char[] tasks, int n)
    {
        if(n==0)
            return tasks.length;
        Map<Character,Integer> map=new HashMap<>();
        for(char c:tasks)
            map.put(c,map.getOrDefault(c,0)+1);

        PriorityQueue<Character> pq=new PriorityQueue<Character>(new Comparator<>(){
            public int compare(Character c1, Character c2)
            {
                return map.get(c2)-map.get(c1);
            }
        });
        pq.addAll(map.keySet());
        int ctr=0;
        while(!pq.isEmpty())
        {
            List<Character> temp=new ArrayList<>();
            for(int i=0;i<=n;i++)
            {
                if(!pq.isEmpty())
                {
                    char curr=pq.poll();
                    temp.add(curr);
                    map.put(curr,map.get(curr)-1);
                    if(map.get(curr)==0)
                        map.remove(curr);
                }
                ctr++;
                if(map.isEmpty() && pq.isEmpty())
                    break;
            }
            for(char c:temp)
            {
                if(map.containsKey(c))
                    pq.offer(c);
            }

        }
        return ctr;
    }

    public static void main(String[] args)
    {
        TaskScheduler obj=new TaskScheduler();
        System.out.println(obj.leastInterval2(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'},2));
    }
}
