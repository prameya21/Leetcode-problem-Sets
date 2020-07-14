public class SingleNumberII
{
    /*
    Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

    Note:

    Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

    Example 1:

    Input: [2,2,3,2]
    Output: 3
    Example 2:

    Input: [0,1,0,1,0,1,99]
    Output: 99
     */

    public int singleNumber(int[] nums)
    {
        int val1=0,val2=0;
        for(int num:nums)
        {
            int t1=val1^num;
            val1=~val2 & t1;

            int t2=val2^num;
            val2=~val1 & t2;
        }
        return val1;
    }

    public static void main(String[] args)
    {
        SingleNumberII obj=new SingleNumberII();
        System.out.println(obj.singleNumber(new int[]{0,1,0,1,0,1,99}));
    }
}
