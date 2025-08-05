import java.util.Arrays;

public class CountPairsInTwoArrays
{
    /*
        Given two integer arrays nums1 and nums2 of length n, count the pairs of indices (i, j) such that i < j and nums1[i] + nums1[j] > nums2[i] + nums2[j].
        Return the number of pairs satisfying the condition.

        Example 1:
        Input: nums1 = [2,1,2,1], nums2 = [1,2,1,2]
        Output: 1
        Explanation: The pairs satisfying the condition are:
        - (0, 2) where 2 + 2 > 1 + 1.

        Example 2:
        Input: nums1 = [1,10,6,2], nums2 = [1,4,1,5]
        Output: 5
        Explanation: The pairs satisfying the condition are:
        - (0, 1) where 1 + 10 > 1 + 4.
        - (0, 2) where 1 + 6 > 1 + 1.
        - (1, 2) where 10 + 6 > 4 + 1.
        - (1, 3) where 10 + 2 > 4 + 5.
        - (2, 3) where 6 + 2 > 1 + 5.


        Constraints:

        n == nums1.length == nums2.length
        1 <= n <= 105
        1 <= nums1[i], nums2[i] <= 105
     */

    public long countPairs(int[] nums1, int[] nums2)
    {
        int N = nums1.length;
        long diff[] = new long[N];
        for (int i = 0; i < N; i++)
            diff[i] = nums1[i] - nums2[i];
        Arrays.sort(diff);
        long res = 0;
        int l = 0, r = N - 1;
        while (l < r) {
            if (diff[l] + diff[r] > 0) {
                res += r - l;
                r--;
            } else
                l++;
        }
        return res;
    }

    public static void main(String[] args)
    {
        CountPairsInTwoArrays obj=new CountPairsInTwoArrays();
        System.out.println(obj.countPairs(new int[]{1,10,6,2}, new int[]{1,4,1,5}));
    }

}
