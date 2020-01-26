import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements
{
    /*
    Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

    Example 1:
    Input: [1,2,3,4,5], k=4, x=3
    Output: [1,2,3,4]
    Example 2:
    Input: [1,2,3,4,5], k=4, x=-1
    Output: [1,2,3,4]
    Note:
    The value k is positive and will always be smaller than the length of the sorted array.
    Length of the given array is positive and will not exceed 104
    Absolute value of elements in the array and x will not exceed 104
     */

    public List<Integer> findClosestElements(int[] arr, int k, int x)
    {
        int len=arr.length;
        List<Integer> res=new ArrayList<>();
        //Redundant
        /*if(x<=arr[0])
        {
            for(int i=0;i<k;i++)
                res.add(arr[i]);
            return res;
        }
        if(arr[len-1]<=x)
        {
            for(int i=len-1-k;i<=len;i++)
                res.add(arr[i]);
            return res;
        }*/

        int lo=0,hi=len-k;
        while(lo<hi)
        {
            int mid=lo+(hi-lo)/2;
            if(x-arr[mid]>arr[mid+k]-x)
                lo=mid+1;
            else
                hi=mid;
        }
        for(int i=lo;i<lo+k;i++)
            res.add(arr[i]);
        return res;
    }

    public static void main(String[] args)
    {
        FindKClosestElements obj=new FindKClosestElements();
        System.out.println(obj.findClosestElements(new int[]{1,2,3,4,5},4,3));
    }
}
