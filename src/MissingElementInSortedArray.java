public class MissingElementInSortedArray
{
    /*
    Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

    Example 1:

    Input: A = [4,7,9,10], K = 1
    Output: 5
    Explanation:
    The first missing number is 5.
    Example 2:

    Input: A = [4,7,9,10], K = 3
    Output: 8
    Explanation:
    The missing numbers are [5,6,8,...], hence the third missing number is 8.
    Example 3:

    Input: A = [1,2,4], K = 3
    Output: 6
    Explanation:
    The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
     */


    public int helper(int[] nums, int idx)
    {
        return nums[idx]-nums[0]-idx;
    }
    public int missingElement(int[] nums, int k)
    {
        int lo=0,hi=nums.length-1;
        if(k>helper(nums,nums.length-1))
            return nums[nums.length-1]+k-helper(nums,nums.length-1);
        while(lo!=hi)
        {
            int mid=lo+(hi-lo)/2;
            if(helper(nums,mid)<k)
                lo=mid+1;
            else
                hi=mid;
        }
        return nums[lo-1]+k-helper(nums,lo-1);
    }

    public static void main(String[] args)
    {
        MissingElementInSortedArray obj=new MissingElementInSortedArray();
        System.out.println(obj.missingElement(new int[]{4,7,9,10},1));
    }
}
