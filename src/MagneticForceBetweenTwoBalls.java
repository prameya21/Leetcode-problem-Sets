import java.util.Arrays;

public class MagneticForceBetweenTwoBalls
{
    /*
        In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket.
        Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
        Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
        Given the integer array position and the integer m. Return the required force.

        Example 1:
        Input: position = [1,2,3,4,7], m = 3
        Output: 3
        Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.

        Example 2:
        Input: position = [5,4,3,2,1,1000000000], m = 2
        Output: 999999999
        Explanation: We can use baskets 1 and 1000000000.
     */


    public int maxDistance(int[] position, int m)
    {
        if(position==null || position.length==0 || m==0)
            return 0;
        int l=1, h=Integer.MAX_VALUE;
        //int h = (int) Math.ceil(position[position.length - 1] / (m - 1.0));
        Arrays.sort(position);
        int res=0;
        while(l<=h)
        {
            int mid=l+(h-l)/2;
            if(helper(position, m ,mid))
            {
                res=mid;
                l=mid+1;
            }
            else
                h=mid-1;

        }
        return res;
    }

    public boolean helper(int[] arr, int x, int m)
    {
        int curr=arr[0], ctr=1;

        for(int i=1;i<arr.length && ctr<x;i++)
        {
            if(arr[i]-curr>=m)
            {
                ctr++;
                curr=arr[i];
            }
        }
        return ctr==x;
    }

    public static void main(String[] args)
    {
        MagneticForceBetweenTwoBalls obj=new MagneticForceBetweenTwoBalls();
        System.out.println(obj.maxDistance(new int[]{1,2,3,4,7}, 3));
        System.out.println(obj.maxDistance(new int[]{269826447,974181916,225871443,189215924,664652743,592895362,754562271,335067223,996014894,510353008,48640772,228945137}, 3)); //461712236
    }
}
