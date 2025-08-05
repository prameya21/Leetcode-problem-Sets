public class ShortestSubArrayRemovalToSort
{
    /*
        1574
        Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
        Return the length of the shortest subarray to remove.
        A subarray is a contiguous subsequence of the array.

        Example 1:
        Input: arr = [1,2,3,10,4,2,3,5]
        Output: 3
        Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
        Another correct solution is to remove the subarray [3,10,4].

        Example 2:
        Input: arr = [5,4,3,2,1]
        Output: 4
        Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].

        Example 3:
        Input: arr = [1,2,3]
        Output: 0
        Explanation: The array is already non-decreasing. We do not need to remove any elements.
     */
    public int findLengthOfShortestSubarray(int[] arr)
    {
        int r=arr.length-1;
        while(r>0 && arr[r]>arr[r-1])
            r--;

        int res=r, l=0;
        while(l<r && (l==0 || arr[l-1]<arr[l]))
        {
            while(r<arr.length && arr[l]>arr[r])
                r++;
            res=Math.min(res,r-l-1);
            l++;
        }
        return res;
    }


    public static void main(String[] args)
    {
        ShortestSubArrayRemovalToSort obj=new ShortestSubArrayRemovalToSort();
        System.out.println(obj.findLengthOfShortestSubarray(new int[]{1,2,3,10,4,2,3,5}));
    }
}
