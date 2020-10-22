import java.util.*;

public class HandOfStraights
{
    /*
    Alice has a hand of cards, given as an array of integers.
    Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
    Return true if and only if she can.

    Example 1:
    Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
    Output: true
    Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].

    Example 2:
    Input: hand = [1,2,3,4,5], W = 4
    Output: false
    Explanation: Alice's hand can't be rearranged into groups of 4.


    Constraints:

    1 <= hand.length <= 10000
    0 <= hand[i] <= 10^9
    1 <= W <= hand.length
     */

    public boolean isNStraightHand(int[] hand, int W)
    {
        if(hand==null || hand.length<W)
            return false;
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int i:hand)
            map.put(i,map.getOrDefault(i,0)+1);
        int ctr=0;
        for(int i:map.keySet())
        {
            if(map.get(i)==0)
                continue;
            while(helper(map,i,W));

        }
        for(int i:map.keySet())
            if(map.get(i)>0)
                return false;

        return true;
    }

    public boolean helper(TreeMap<Integer,Integer> map, int i, int W)
    {
        int val=i;
        for(int idx=0;idx<W;idx++)
        {
            if(!map.containsKey(val) || map.get(val)<=0)
                return false;
            val++;
        }
        for(int idx=0;idx<W;idx++)
        {
            map.put(i,map.get(i)-1);
            i++;
        }
        return true;
    }

    public static void main(String[] args)
    {
        HandOfStraights obj=new HandOfStraights();
        System.out.println(obj.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8},3));
        System.out.println(obj.isNStraightHand(new int[]{8,8,9,7,7,7,6,7,10,6},2));





    }
}

