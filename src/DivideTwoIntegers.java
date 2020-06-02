public class DivideTwoIntegers
{
    /*
    Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

    Return the quotient after dividing dividend by divisor.

    The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

    Example 1:

    Input: dividend = 10, divisor = 3
    Output: 3
    Explanation: 10/3 = truncate(3.33333..) = 3.
    Example 2:

    Input: dividend = 7, divisor = -3
    Output: -2
    Explanation: 7/-3 = truncate(-2.33333..) = -2.
    Note:

    Both dividend and divisor will be 32-bit signed integers.
    The divisor will never be 0.
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
     */

    public int divide(int dividend, int divisor)
    {
        if(dividend==0)
            return 0;
        long d1=dividend;
        long d2=divisor;
        int sign=d1*d2<0?-1:1;
        d1=Math.abs(d1);
        d2=Math.abs(d2);
        long result=helper(d1,d2)*sign;
        return result>Integer.MAX_VALUE?Integer.MAX_VALUE:(int) result;
    }

    public long helper(long d1, long d2)
    {
        if(d1<d2)
            return 0;
        long sum=d2, count=1;
        while(sum+sum<=d1)
        {
            sum+=sum;
            count+=count;
        }
        return count+helper(d1-sum,d2);
    }


    public static void main(String[] args)
    {
        DivideTwoIntegers obj=new DivideTwoIntegers();
        System.out.println(obj.divide(-2147483648,-1));
    }
}
