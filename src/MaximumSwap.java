public class MaximumSwap
{
    /*
    Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

    Example 1:
    Input: 2736
    Output: 7236
    Explanation: Swap the number 2 and the number 7.
    Example 2:
    Input: 9973
    Output: 9973
    Explanation: No swap.
    Note:
    The given number is in the range [0, 108]
     */

    public int maximumSwap(int num)
    {
        char[] ch=toString(num).toCharArray();
        int[] loc=new int[10];
        for(int i=0;i<ch.length;i++)
            loc[ch[i]-'0']=i;

        for(int i=0;i<ch.length;i++)
        {
            for(int d=9;d>ch[i]-'0';d--)
            {
                if(loc[d]>i)
                {
                    int val1=i,val2=loc[d];
                    char temp=ch[val1];
                    ch[val1]=ch[val2];
                    ch[val2]=temp;
                    return Integer.valueOf(new String(ch));
                }
            }
        }
        return num;
    }

    public String toString(int num)
    {
        StringBuilder sb=new StringBuilder();
        while(num!=0)
        {
            sb.append(num%10);
            num/=10;
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args)
    {
        MaximumSwap obj=new MaximumSwap();
        System.out.println(obj.maximumSwap(2736));
    }
}
