public class ReplaceElementsWithGreaterElements
{
    /*
    Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

    After doing so, return the array.
    Example 1:

    Input: arr = [17,18,5,4,6,1]
    Output: [18,6,6,6,1,-1]

    Constraints:

    1 <= arr.length <= 10^4
    1 <= arr[i] <= 10^5
     */

    public int[] replaceElements(int[] arr)
    {
        if(arr==null || arr.length==0)
            return arr;
        int max=arr[arr.length-1];
        arr[arr.length-1]=-1;
        for(int i=arr.length-2;i>=0;i--)
        {
            int curr=arr[i];
            arr[i]=max;
            max=Math.max(max,curr);
        }
        return arr;
    }
}
