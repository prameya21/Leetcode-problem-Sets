import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DuplicateNumberOn
{
    /*
    Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

    Example 1:

    Input: [1,3,4,2,2]
    Output: 2
    Example 2:

    Input: [3,1,3,4,2]
    Output: 3

    Note:
    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n2).
    There is only one duplicate number in the array, but it could be repeated more than once.
     */


    //Approach 1: sort
    public int findDuplicate1(int[] nums)
    {
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i]==nums[i+1])
                return nums[i];
        }
        return -1;
    }

    //Approach 2 Set
    public int findDuplicate2(int[] nums)
    {
        Set<Integer> set=new HashSet<>();
        for(int i:nums)
        {
            if(set.contains(i))
                return i;
            else
                set.add(i);
        }
        return -1;
    }

    //Floyd's tortoise and hare cycle detection
    public int findDuplicate3(int[] nums)
    {
        if(nums.length>1)
        {
            int slow=nums[0];
            int fast=nums[nums[0]];
            while(fast!=slow)
            {
                slow=nums[slow];
                fast=nums[nums[fast]];
            }
            fast=0;
            while(slow!=fast)
            {
                slow=nums[slow];
                fast=nums[fast];
            }
            return slow;
        }
        return -1;
    }
    public static void main(String[] args)
    {
        DuplicateNumberOn obj=new DuplicateNumberOn();
        System.out.println(obj.findDuplicate1(new int[]{1,3,4,2,2}));
        System.out.println(obj.findDuplicate2(new int[]{1,3,4,2,2}));
        System.out.println(obj.findDuplicate3(new int[]{1,3,4,2,2}));
    }

}
