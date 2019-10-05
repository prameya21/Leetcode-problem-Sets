import java.util.*;

public class SafeCracker
{
    /*
    You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

    The lock initially starts at '0000', a string representing the state of the 4 wheels.

    You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

    Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

    Example 1:
    Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
    Output: 6
    Explanation:
    A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
    Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
    because the wheels of the lock become stuck after the display becomes the dead end "0102".
    Example 2:
    Input: deadends = ["8888"], target = "0009"
    Output: 1
    Explanation:
    We can turn the last wheel in reverse to move from "0000" -> "0009".
    Example 3:
    Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
    Output: -1
    Explanation:
    We can't reach the target without getting stuck.
    Example 4:
    Input: deadends = ["0000"], target = "8888"
    Output: -1
    Note:
    The length of deadends will be in the range [1, 500].
    target will not be in the list deadends.
    Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.
     */

    public int openLock(String[] deadends, String target)
    {
        Set<String> deadEnd=new HashSet<String>(Arrays.asList(deadends));
        if(target==null || target.length()==0 || deadEnd.contains("0000") || deadEnd.contains(target))
            return -1;
        int count=0;
        Queue<String> q=new LinkedList<>();
        Set<String> visited=new HashSet<>();
        q.offer("0000");
        visited.add("0000");
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String curr=q.poll();
                if(curr.equals(target))
                    return count;
                List<String> combinations=generate(curr);
                for(String sub:combinations)
                {
                    if(deadEnd.contains(sub) || visited.contains(sub))
                        continue;
                    q.offer(sub);
                    visited.add(sub);
                }
            }
            count++;
        }
        return -1;
    }
    public List<String> generate(String s)
    {
        List<String> res=new ArrayList<>();
        for(int i=0;i<s.length();i++)
        {
            int val=s.charAt(i)-'0';
            int plusVal=(val+1+10)%10;
            int minusVal=(val-1+10)%10;
            String p=s.substring(0,i)+plusVal+s.substring(i+1);
            String m=s.substring(0,i)+minusVal+s.substring(i+1);
            res.add(p);
            res.add(m);
        }
        return res;
    }

    public static void main(String[] args)
    {
        SafeCracker obj=new SafeCracker();
        System.out.println(obj.openLock(new String[]{"0201","0101","0102","1212","2002"},"0202"));
    }
}
