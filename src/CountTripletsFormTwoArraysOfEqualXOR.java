import java.util.Arrays;

public class CountTripletsFormTwoArraysOfEqualXOR
{
    /*
        Given an array of integers arr.
        We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
        Let's define a and b as follows:
        a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
        b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
        Note that ^ denotes the bitwise-xor operation.
        Return the number of triplets (i, j and k) Where a == b.

        Example 1:
        Input: arr = [2,3,1,6,7]
        Output: 4
        Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)

        Example 2:
        Input: arr = [1,1,1,1,1]
        Output: 10

        Constraints:
        1 <= arr.length <= 300
        1 <= arr[i] <= 108
     */

    public int countTriplets(int[] arr)
    {
        if(arr==null || arr.length==0)
            return 0;
        int[] pre=new int[arr.length+1];
        pre[0]=0;
        for(int i=0;i<arr.length;i++)
            pre[i+1]=pre[i]^arr[i];
        //System.out.println(Arrays.toString(pre));
        int res=0;
        for(int i=0;i<arr.length;i++)
            for(int k=i+1;k<arr.length;k++)
                if(pre[i]==pre[k+1])
                    res+=(k-i);
        return res;
    }

    public static void main(String[] args)
    {
        CountTripletsFormTwoArraysOfEqualXOR obj=new CountTripletsFormTwoArraysOfEqualXOR();
        System.out.println(obj.countTriplets(new int[]{2,3,1,6,7}));
    }
}
