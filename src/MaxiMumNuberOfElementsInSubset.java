import java.util.HashMap;
import java.util.Map;

public class MaxiMumNuberOfElementsInSubset
{
    //3020
    /*
        You are given an array of positive integers nums.
        You need to select a subset of nums which satisfies the following condition:
        You can place the selected elements in a 0-indexed array such that it follows the pattern: [x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x]
        (Note that k can be be any non-negative power of 2). For example, [2, 4, 16, 4, 2] and [3, 9, 3] follow the pattern while [2, 4, 8, 4, 2] does not.
        Return the maximum number of elements in a subset that satisfies these conditions.

        Example 1:
        Input: nums = [5,4,1,2,2]
        Output: 3
        Explanation: We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the pattern and 22 == 4. Hence the answer is 3.

        Example 2:
        Input: nums = [1,3,2,4]
        Output: 1
        Explanation: We can select the subset {1}, which can be placed in the array as [1] which follows the pattern. Hence the answer is 1.
        Note that we could have also selected the subsets {2}, {3}, or {4}, there may be multiple subsets which provide the same answer.

        Constraints:
        2 <= nums.length <= 105
        1 <= nums[i] <= 109
     */
    public int maximumLength(int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;

        Map<Long,Integer> map=new HashMap<>();
        for(int i:nums)
            map.put(i*1L,map.getOrDefault(i*1L,0)+1);
        int res=1;
        if(map.containsKey(1L))
        {
            int freq=map.get(1L);
            res=freq%2==0?freq-1:freq;
        }
        map.remove(1L);
        for(int i:nums)
        {
            long val=i;
            int ctr=0;
            while(map.containsKey(val))
            {
                if(val>Integer.MAX_VALUE)
                    break;
                if(map.get(val)>1)
                {
                    ctr++;
                    val*=val;
                }
                else if(map.get(val)==1)
                {
                    ctr*=2;
                    ctr++;
                    res=Math.max(res,ctr);
                    break;
                }
                res=Math.max(res,(ctr*2)-1);
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        MaxiMumNuberOfElementsInSubset obj=new MaxiMumNuberOfElementsInSubset();
        System.out.println(obj.maximumLength(new int[]{5,4,1,2,2}));
        System.out.println(obj.maximumLength(new int[]{1,1,1,1,1,1,1,1,1,1,2,4,8,16,32,64,128,256,512,1024}));
        System.out.println(obj.maximumLength(new int[]{65025,312481,107584,148996,322624,194481,570025,15376,123904,848241,88804,47961,117649,66564,295936,271441,16900,474721,27556,285156,11236,175561,917764,968256,16,38025,312481,426409,354025,8464,522729,60516,210681,378225,638401,101124,697225,427716,262144,940900,988036,324900,151321,309136,178929,168921,189225,4,301401,659344,786769,964324,15625,302500,56644,61504,31684,369664,345744,19321,59049,5041,40000,147456,372100,708964,171396,214369,707281,484,49729,82944,100489,103684,58564,208849,946729,84100,4,600625,334084,683929,9604,245025,97969,147456,160801,434281,223729,294849,166464,432964,518400,376996,17424,315844,256,737881,10000,632025}));
        System.out.println(obj.maximumLength(new int[]{15,15,225,225,50625,50625}));
    }
}
