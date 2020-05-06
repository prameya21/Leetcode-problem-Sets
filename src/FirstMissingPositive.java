public class FirstMissingPositive
{
    /*
    Given an unsorted integer array, find the smallest missing positive integer.

    Example 1:

    Input: [1,2,0]
    Output: 3
    Example 2:

    Input: [3,4,-1,1]
    Output: 2
    Example 3:

    Input: [7,8,9,11,12]
    Output: 1
    Note:

    Your algorithm should run in O(n) time and uses constant extra space.
     */

    public int firstMissingPositive(int[] nums)
    {
        int n=nums.length;
        int c=0;
        for(int i:nums)
            if(i==1)
                c++;

        if(c==0)
            return 1;
        if(n==1)
            return 2;

        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]<=0 || nums[i]>n)
                nums[i]=1;
        }

        for(int i=0;i<nums.length;i++)
        {
            int a=Math.abs(nums[i]);
            if(a==n)
                nums[0]=nums[0]<0?nums[0]:-nums[0];
            else
                nums[a]=nums[a]<0?nums[a]:-nums[a];
        }
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]>0)
                return i;
        }
        if(nums[0]>0)
            return n;
        return n+1;
    }

    public static void main(String[] args)
    {
        FirstMissingPositive obj=new FirstMissingPositive();
        System.out.println(obj.firstMissingPositive(new int[]{1,2,0}));
        System.out.println(obj.firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(obj.firstMissingPositive(new int[]{7,8,9,11,12}));
        System.out.println(obj.firstMissingPositive(new int[]{2,3,4,5,6,7,8,9,1}));
        System.out.println(obj.firstMissingPositive(new int[]{1,2,3,4,5,6,-1}));
    }
}
