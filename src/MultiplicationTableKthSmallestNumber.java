public class MultiplicationTableKthSmallestNumber
{
    /*
    Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

    Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

    Example 1:
    Input: m = 3, n = 3, k = 5
    Output:
    Explanation:
    The Multiplication Table:
    1	2	3
    2	4	6
    3	6	9

    The 5-th smallest number is 3 (1, 2, 2, 3, 3).
    Example 2:
    Input: m = 2, n = 3, k = 6
    Output:
    Explanation:
    The Multiplication Table:
    1	2	3
    2	4	6

    The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
    Note:
    The m and n will be in the range [1, 30000].
    The k will be in the range [1, m * n]
     */

    public int findKthNumber(int m, int n, int k)
    {
        int lo=1,hi=m*n;
        while(lo<hi)
        {
            int mid=lo+(hi-lo)/2;
            int count=count(mid,m,n);
            if(count>=k)
                hi=mid;
            else
                lo=mid+1;
        }
        return lo;
    }

    public int count(int val, int m, int n)
    {
        int count=0;
        for(int i=1;i<=m;i++)
            count+=Math.min(val/i,n);
        return count;
    }



    public static void main(String[] args)
    {
        MultiplicationTableKthSmallestNumber obj=new MultiplicationTableKthSmallestNumber();
        System.out.println(obj.findKthNumber(3,3,5));
    }
}
