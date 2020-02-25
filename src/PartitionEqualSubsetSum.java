import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PartitionEqualSubsetSum
{
    /*
        Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

        Note:

        Each of the array element will not exceed 100.
        The array size will not exceed 200.


        Example 1:

        Input: [1, 5, 11, 5]

        Output: true

        Explanation: The array can be partitioned as [1, 5, 5] and [11].


        Example 2:

        Input: [1, 2, 3, 5]

        Output: false

        Explanation: The array cannot be partitioned into equal sum subsets.
     */
    public boolean canPartition(int[] nums)
    {
        if(nums==null || nums.length==0)
            return false;
        int total=0;
        for(int i:nums)
            total+=i;
        if(total%2!=0)
            return false;
        return helper(nums,0,0,total,new int[nums.length][total/2]);
    }



    public boolean helper(int[] nums, int idx, int sum, int total, int[][] map)
    {
        if(sum*2==total)
            return true;
        if(idx>=nums.length || sum>total/2)
            return false;
        if(map[idx][sum]==1)
            return true;
        if(map[idx][sum]==-1)
            return false;
        boolean state=helper(nums,idx+1,sum,total,map) || helper(nums,idx+1,sum+nums[idx],total,map);
        map[idx][sum]=state?1:-1;
        return state;
    }

    //Logic fails for 4,3,2,3,5,2,1
    public boolean helperFail(int[] nums,int l, int r)
    {
        Arrays.sort(nums);
        int lsum=0,rsum=0;
        boolean left=true,right=true;
        while(l<r)
        {
            lsum+=left?nums[l]:0;
            rsum+=right?nums[r]:0;
            left=false;
            right=false;
            if(lsum==rsum && r-l==1)
                return true;
            else if(lsum<=rsum)
            {
                l++;
                left=true;
            }
            else
            {
                r--;
                right=true;
            }
        }
        return false;
    }


    public static void main(String[] args)
    {
        PartitionEqualSubsetSum obj =new PartitionEqualSubsetSum();
        System.out.println(obj.canPartition(new int[]{1,5,11,5}));
        System.out.println(obj.canPartition(new int[]{4,3,2,3,5,2,1}));
        System.out.println(obj.canPartition(new int[]{1,2,3,5}));
        System.out.println(obj.canPartition(new int[]{100,100,100,100,100,100,100,100}));
    }
}
