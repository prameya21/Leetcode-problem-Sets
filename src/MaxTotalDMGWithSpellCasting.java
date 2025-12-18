import java.util.Arrays;
import java.util.TreeMap;

public class MaxTotalDMGWithSpellCasting
{
    //3186
    /*
        A magician has various spells.
        You are given an array power, where each element represents the damage of a spell. Multiple spells can have the same damage value.
        It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.
        Each spell can be cast only once.
        Return the maximum possible total damage that a magician can cast.

        Example 1:
        Input: power = [1,1,3,4]
        Output: 6
        Explanation:
        The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.

        Example 2:
        Input: power = [7,1,6,6]
        Output: 13
        Explanation:
        The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.

        Constraints:
        1 <= power.length <= 105
        1 <= power[i] <= 109
     */

    public long maximumTotalDamage(int[] power)
    {
        if(power==null || power.length==0)
            return 0;
        TreeMap<Integer, Long> map=new TreeMap<>();
        for(int i:power)
            map.put(i,map.getOrDefault(i,0L)+i);
        Long[] dp=new Long[map.size()+1];
        Long[][] pwr=new Long[map.size()][2];
        int idx=0;
        for(int i:map.keySet())
        {
            pwr[idx][0] = (long) (i);
            pwr[idx][1]=map.get(i);
            idx++;
        }

        return helper(0,pwr, dp);
    }

    public long helper(int idx, Long[][] nums, Long[] dp)
    {
        if(idx>=nums.length)
            return 0;
        if(dp[idx]!=null)
            return dp[idx];
        long c1=helper(idx+1,nums,dp);
        long c2=nums[idx][1];
        for(int j=idx+1;j<=Math.min(idx+3,nums.length-1);j++)
        {
            if(nums[j][0]-nums[idx][0]>2)
            {
                c2+=helper(j,nums,dp);
                break;
            }
        }
        dp[idx]=Math.max(c1,c2);
        return dp[idx];
    }

    public static void main(String[] args)
    {
        MaxTotalDMGWithSpellCasting obj=new MaxTotalDMGWithSpellCasting();
        System.out.println(obj.maximumTotalDamage(new int[]{1,1,3,4}));
        System.out.println(obj.maximumTotalDamage(new int[]{7,1,6,6}));
    }
}
