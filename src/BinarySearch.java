public class BinarySearch
{
    public int binary_search(int[] nums, int target)
    {
        int l=0, r=nums.length;
        while(l<r)
        {
            int mid=(l+r)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                l=mid+1;
            else
                r=mid-1;
        }
        return l;
    }


    //Sqrt(x)
    {
        /*
        Implement int sqrt(int x).

        Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

        Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

        Example 1:

        Input: 4
        Output: 2
        Example 2:

        Input: 8
        Output: 2
        Explanation: The square root of 8 is 2.82842..., and since
                     the decimal part is truncated, 2 is returned.
         */
    }
    public int mysqrt(int x)
    {
        if(x==0)
            return 0;
        if(x==1)
            return 1;
        int l=0,r=x/2;
        while(l<r)
        {
            int mid=(l+r)/2;
            if(mid<x/mid && (mid+1)>x/(mid+1))
                return mid;
            else if(mid>x/mid)
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }

    //Search in a Rotated Sorted Array
    {
        /*
        Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

        (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

        You are given a target value to search. If found in the array return its index, otherwise return -1.

        You may assume no duplicate exists in the array.

        Your algorithm's runtime complexity must be in the order of O(log n).

        Example 1:

        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4
        Example 2:

        Input: nums = [4,5,6,7,0,1,2], target = 3
        Output: -1
         */
    }
    public int search(int[] nums, int target)
    {
        if(nums.length==0)
            return -1;
        if(nums.length==1 && nums[0]==target)
            return 0;
        if(nums.length==1 && nums[0]!=target)
            return -1;
        int pivot=findPivot(nums);
        if(pivot==0)
            return search(nums,target,0,nums.length-1);
        else if(nums[0]<target)
            return search(nums,target,pivot,nums.length-1);
        else
            return search(nums,target,0,pivot);
    }

    public int findPivot(int[] nums)
    {
        int l=0,r=nums.length-1;
        if(nums[l]<nums[r])
            return 0;
        while(l<=r)
        {
            int mid=l+(r-l)/2;
            if(nums[mid]>nums[mid+1])
                return mid+1;
            else if(nums[mid]<nums[l])
                r=mid-1;
            else
                l=mid+1;
        }
        return 0;
    }

    public int search(int[] nums, int target, int l, int r)
    {
        while(l<=r)
        {
            int mid=l+(r-l)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                l=mid+1;
            else
                r=mid-1;
        }
        return -1;
    }

    //Find Peak Element
    {
        /*
        A peak element is an element that is greater than its neighbors.

        Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

        The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

        You may imagine that nums[-1] = nums[n] = -∞.

        Example 1:

        Input: nums = [1,2,3,1]
        Output: 2
        Explanation: 3 is a peak element and your function should return the index number 2.
        Example 2:

        Input: nums = [1,2,1,3,5,6,4]
        Output: 1 or 5
        Explanation: Your function can return either index number 1 where the peak element is 2,
                     or index number 5 where the peak element is 6.
        Note:

        Your solution should be in logarithmic complexity.
         */
    }
    public int findPeakElement(int[] nums)
    {
        int l=0,r=nums.length-1;
        while(l<r)
        {
            int mid=l+(r-l)/2;
            if(nums[mid]>nums[mid+1])
                mid=r;
            else
                l=mid+1;
        }
        return l;
    }

    //Minimum in a rotated Sorted array
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
    }
    public int findMin(int[] nums)
    {
        if(nums.length==0)
            return -1;
        if(nums.length==1)
            return nums[0];
        int l=0,r=nums.length-1;
        if(nums[l]<nums[r])
            return nums[l];
        while(l<=r)
        {
            int mid=l+(r-l)/2;
            if(nums[mid]>nums[mid+1])
                return nums[mid+1];
            else if(nums[mid]<nums[l])
                r=mid-1;
            else
                l=mid+1;
        }
        return 0;
    }

    //Search for a range
    {
        /*
        Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

        Your algorithm's runtime complexity must be in the order of O(log n).

        If the target is not found in the array, return [-1, -1].

        Example 1:

        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]
        Example 2:

        Input: nums = [5,7,7,8,8,10], target = 6
        Output: [-1,-1]
         */
    }
    public int[] searchRange(int[] nums, int target)
    {
        if(nums==null || nums.length==0)
            return new int[]{-1,-1};
        int l=rangeHelper(nums,target);
        if(l>nums.length || nums[l]!=target)
            return new int[]{-1,-1};
        return new int[]{l,rangeHelper(nums,target+1)-1};
    }
    public int rangeHelper(int[] nums,int target)
    {
        int l=0,r=nums.length;
        while(l<r)
        {
            int mid=l+(r-l)/2;
            if(nums[mid]>=target)
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }











    public static void main(String[] args)
    {
        int[] arr={1,2,3,5,5,5,5,7,9,12,14};
        BinarySearch obj=new BinarySearch();
        System.out.println(obj.binary_search(arr,5));
    }
}
