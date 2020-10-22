public class NthDigit
{
    /*
    Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

    Note:
    n is positive and will fit within the range of a 32-bit signed integer (n < 231).

    Example 1:

    Input:
    3

    Output:
    3
    Example 2:

    Input:
    11

    Output:
    0

    Explanation:
    The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
     */

    public int findNthDigit(int n)
    {
        int digit=1;
        long count=9;
        while(n-digit*count>0)
        {
            n-=digit*count;
            digit++;
            count*=10;
        }
        int baseNumber=(int)Math.pow(10,digit-1);
        int number=(n-1)/digit+baseNumber;
        int mod=(n-1)%digit;
        return String.valueOf(number).charAt(mod)-'0';
    }

    public static void main(String[] args)
    {
        NthDigit obj=new NthDigit();
        System.out.println(obj.findNthDigit(11));
    }

}
