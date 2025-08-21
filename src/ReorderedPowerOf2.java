import java.util.Arrays;

public class ReorderedPowerOf2
{
    //869
    /*
        You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
        Return true if and only if we can do this so that the resulting number is a power of two.

        Example 1:
        Input: n = 1
        Output: true

        Example 2:
        Input: n = 10
        Output: false

        Constraints:
        1 <= n <= 109
     */

    public boolean reorderedPowerOf2(int n)
    {
        int[] A=helper(n);
        for(int i=0;i<31;i++)
        {
            if(Arrays.equals(A,helper(1<<i)))
                return true;
        }
        return false;
    }

    public int[] helper(int n)
    {
        int[] tmp=new int[10];
        while(n>0)
        {
            int r=n%10;
            tmp[r]++;
            n/=10;
        }
        return tmp;
    }

    public static void main(String[] args)
    {
        ReorderedPowerOf2 obj=new ReorderedPowerOf2();
        System.out.println(obj.reorderedPowerOf2(1));
        System.out.println(obj.reorderedPowerOf2(10));
        System.out.println(obj.reorderedPowerOf2(46));
    }
}
