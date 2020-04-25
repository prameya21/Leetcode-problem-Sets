import java.util.HashMap;
import java.util.Map;

public class BinaryGap
{
    /*
    A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

    For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3.
    The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.

    Write a function:

    class Solution { public int solution(int N); }
    that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
    For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5.
    Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.
    Write an efficient algorithm for the following assumptions:
    N is an integer within the range [1..2,147,483,647].
     */

    public int solution(int N)
    {
        // write your code in Java SE 8
        int max=0;
        while(N!=0)
        {
            if((N&1)==0)
                N=N>>1;
            else
            {
                N=N>>1;
                int cnt=0;
                while((N&1)!=1 && N!=0)
                {
                    cnt++;
                    N=N>>1;
                }
                max=Math.max(max,cnt);
            }
        }
        return max;
    }
    public int solution(int[] A) {
        // write your code in Java SE 8
        Map<Integer,Integer> map=new HashMap<>();
        for(int i:A)
            map.put(i,map.getOrDefault(i,0)+1);

        for(int i:map.keySet())
        {
            System.out.println(i+","+map.get(i));
            if(map.get(i)%2!=0)
                return i;
        }

        return -1;
    }
    public int solution(int[] arr1, int [] arr2)
    {
        int[] prefixA=new int[arr1.length];
        int[] prefixB=new int[arr1.length];
        prefixA[0]=arr1[0];
        prefixB[0]=arr2[0];
        int ans=0;
        for(int i=1;i<arr1.length;i++)
        {
            prefixA[i]=arr1[i]+prefixA[i-1];
            prefixB[i]=arr2[i]+prefixB[i-1];
        }
        int n=prefixA.length-1;
        for(int i=0;i<prefixA.length-1;i++)
        {
            if(prefixA[i]==prefixA[n]-prefixA[i] && prefixB[i]==prefixB[n]-prefixB[i])
                ans++;
        }
        return ans;
    }

    public static void main(String[] args)
    {
        BinaryGap obj=new BinaryGap();
        //System.out.print(obj.solution(20));
        System.out.println(obj.solution(new int[]{9,3,9,3,9,7,9}));
        System.out.println(obj.solution(new int[]{4,-1,0,3},new int[]{-2,5,0,3}));
    }
}
