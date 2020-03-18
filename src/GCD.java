public class GCD
{
    /*
        GCD (Greatest Common Divisor) or HCF (Highest Common Factor) of two numbers is the largest number that divides both of them.
        For example GCD of 20 and 28 is 4 and GCD of 98 and 56 is 14.
     */
    public int gcd(int a, int b)
    {
        int min=Math.min(a,b);
        int gcd=0;
        for(int i=1;i<=min;i++)
        {
            if(a%i==0 && b%i==0)
                gcd=i;
        }
        return gcd;
    }
    public int gcd_fast(int a, int b)
    {
        if(a==0)
            return b;
        if(b==0)
            return a;
        if(a==b)
            return a;
        if(a>b)
            return gcd(a-b,b);
        else
            return gcd(b-a,a);
    }
    public static void main(String[] args)
    {
        GCD obj=new GCD();
        System.out.println(obj.gcd_fast(60,36));
    }
}
