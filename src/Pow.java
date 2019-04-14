public class Pow
{
    public static int pow(int x,int n)
    {
        if(n<0)
        {
            n=-n;
            x=1/x;
        }
        return helper(x,n);
    }
    public static int helper(int x,int n)
    {
        if(n==0)
            return 1;
        int temp=helper(x,n/2);
        if(n%2==0)
            return temp*temp;
        else
            return temp*temp*x;
    }
    public static void main(String[] args)
    {
        System.out.println(pow(2,4));
    }
}
