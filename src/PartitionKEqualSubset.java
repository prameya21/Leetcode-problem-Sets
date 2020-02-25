public class PartitionKEqualSubset
{
    /*
        Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
        Example 1:

        Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
        Output: True
        Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
        Note:

        1 <= k <= len(nums) <= 16.
        0 < nums[i] < 10000.
     */

    public boolean canPartitionKSubsets(int[] nums, int k)
    {
        if(nums==null || nums.length==0 || k<0)
            return false;
        if(k==1)
            return true;
        int total=0;
        for(int i:nums)
            total+=i;
        if(total%k!=0)
            return false;

        return helper(nums,0,0,total/k,k,new boolean[nums.length]);
    }

    public boolean helper(int[] nums, int curr_sum,int idx,int total, int k,boolean[] visited)
    {
        if(k==1)
            return true;
        if(curr_sum>total || idx>=nums.length)
            return false;
        if(curr_sum==total)
            return(helper(nums,0,0,total,k-1,visited));
        for(int i=idx;i<nums.length;i++)
        {
            if(visited[i])
                continue;
            visited[i]=true;
            if(helper(nums,curr_sum+nums[i],idx+1,total,k,visited))
                return true;
            visited[i]=false;
        }
        return false;
    }
}
