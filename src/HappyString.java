import java.util.Comparator;
import java.util.PriorityQueue;

public class HappyString
{
    /*
    A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.

    Given three integers a, b and c, return any string s, which satisfies following conditions:

    s is happy and longest possible.
    s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
    s will only contain 'a', 'b' and 'c' letters.
    If there is no such string s return the empty string "".



    Example 1:

    Input: a = 1, b = 1, c = 7
    Output: "ccaccbcc"
    Explanation: "ccbccacc" would also be a correct answer.
    Example 2:

    Input: a = 2, b = 2, c = 1
    Output: "aabbc"
    Example 3:

    Input: a = 7, b = 1, c = 0
    Output: "aabaa"
    Explanation: It's the only correct answer in this case.


    Constraints:

    0 <= a, b, c <= 100
    a + b + c > 0
     */

    public String longestDiverseString(int a, int b, int c)
    {
        PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] i, int[] j)
            {
                return j[1]-i[1];
            }
        });
        pq.offer(new int[]{0,a});
        pq.offer(new int[]{1,b});
        pq.offer(new int[]{2,c});
        StringBuilder sb=new StringBuilder();
        while(!pq.isEmpty())
        {
            if(!pq.isEmpty())
            {
                int curr[]=pq.poll();
                if(curr[1]<=0)
                    continue;
                sb.append((char)(curr[0]+'a'));
                curr[1]--;
                if(curr[1]>0)
                    pq.offer(curr);
            }
            if(!pq.isEmpty())
            {
                int curr[]=pq.poll();
                if(curr[1]<=0)
                    continue;
                sb.append((char)(curr[0]+'a'));
                curr[1]--;
                int[] nxt=pq.isEmpty()?null:pq.poll();
                if(nxt==null || nxt[1]<=0)
                    continue;
                sb.append((char)(nxt[0]+'a'));
                nxt[1]--;
                if(nxt[1]>0)
                    pq.offer(nxt);
                if(curr[1]>0)
                    pq.offer(curr);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        HappyString obj=new HappyString();
        System.out.println(obj.longestDiverseString(1,1,7));
        System.out.println(obj.longestDiverseString(2,2,1));
        System.out.println(obj.longestDiverseString(7,1,0));
        System.out.println(obj.longestDiverseString(1,0,3));

    }
}
