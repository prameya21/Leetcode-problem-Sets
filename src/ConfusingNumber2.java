import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConfusingNumber2
{
    /*
    We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

    A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)

    Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.



    Example 1:

    Input: 20
    Output: 6
    Explanation:
    The confusing numbers are [6,9,10,16,18,19].
    6 converts to 9.
    9 converts to 6.
    10 converts to 01 which is just 1.
    16 converts to 91.
    18 converts to 81.
    19 converts to 61.
    Example 2:

    Input: 100
    Output: 19
    Explanation:
    The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].


    Note:

    1 <= N <= 10^9
     */

    public int confusingNumberIITLE(int N)
    {
        Set<Integer> set=new HashSet<>();
        for(int i=2;i<=N;i++)
        {
            if(set.contains(i))
                continue;
            int rev=confusingNumber(i);
            if(rev==-1)
                continue;
            if(rev!=i)
                set.add(i);

        }
        return set.size();
    }

    public int confusingNumber(int N)
    {
        int[] rotated=new int[]{0,1,-1,-1,-1,-1,9,-1,8,6};
        int val=0;
        int num=N;
        while(N!=0)
        {
            int n=N%10;
            if(rotated[n]==-1)
                return -1;
            val=val*10+rotated[n];
            N/=10;
        }
        return val;
    }

    public int confusingNumberII(int N)
    {
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        map.put(6,9);
        map.put(8,8);
        map.put(9,6);

        return helper(0,N,map);
    }
    public int helper(long i, int N, Map<Integer,Integer> map)
    {
        if(i>N || i<0)
            return 0;
        int cnt=0;
        if(isConfusing((int)i))
            cnt+=1;
        for(int u:map.keySet())
        {
            i=i*10+map.get(u);
            if(i!=0)
                cnt+=helper(i,N,map);
            i/=10;
        }
        return cnt;
    }

    public boolean isConfusing(int i)
    {
        int[] rotated={0,1,-1,-1,-1,-1,9,-1,8,6};
        int num=i;
        int val=0;
        while(num!=0)
        {
            int n=num%10;
            if(rotated[n]==-1)
                return false;
            val=val*10+rotated[n];
            num/=10;
        }
        return val!=i;
    }
    public static void main(String[] args)
    {
        ConfusingNumber2 obj=new ConfusingNumber2();
        System.out.println(obj.confusingNumberII(100));
    }
}
