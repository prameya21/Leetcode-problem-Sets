import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreatestElement1
{
    /*
    You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

    The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

    Example 1:
    Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
    Output: [-1,3,-1]
    Explanation:
        For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
        For number 1 in the first array, the next greater number for it in the second array is 3.
        For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
    Example 2:
    Input: nums1 = [2,4], nums2 = [1,2,3,4].
    Output: [3,-1]
    Explanation:
        For number 2 in the first array, the next greater number for it in the second array is 3.
        For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2)
    {
        int[] res=new int[nums1.length];
        Stack<Integer> st=new Stack<>();
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums2.length;i++)
        {
            while(!st.isEmpty() && nums2[i]>st.peek())
                map.put(st.pop(),nums2[i]);
            st.push(nums2[i]);
        }
        while(!st.isEmpty())
            map.put(st.pop(),-1);
        for(int i=0;i<nums1.length;i++)
            res[i]=map.get(nums1[i]);
        return res;
    }
    public static void main(String[] args)
    {
        int[] ans=nextGreaterElement(new int[]{1,3,5,2,4},new int[]{6,5,4,3,2,1,7});
        for(int i=0;i<ans.length;i++)
            System.out.println(ans[i]);
    }


}
