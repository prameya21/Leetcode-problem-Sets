public class MinimumInRotatedSortedArray1
{
    /*
    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

    (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

    Find the minimum element.

    You may assume no duplicate exists in the array.

    Example 1:

    Input: [3,4,5,1,2]
    Output: 1
    Example 2:

    Input: [4,5,6,7,0,1,2]
    Output: 0
     */
    public int findMin(int[] nums)
    {
        if(nums==null || nums.length==0)
            return -1;
        if(nums.length==1)
            return nums[0];
        int lo=0,hi=nums.length-1;
        if(nums[lo]<nums[hi])
            return nums[lo];
        while(lo<=hi)
        {
            int mid=lo+(hi-lo)/2;
            if(nums[mid]>nums[mid+1])
                return nums[mid+1];
            else if(nums[mid]<nums[lo])
                hi=mid-1;
            else
                lo=mid+1;
        }
        return 0;
    }
}
