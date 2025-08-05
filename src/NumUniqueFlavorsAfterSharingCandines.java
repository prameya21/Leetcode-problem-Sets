import java.util.HashMap;
import java.util.Map;

public class NumUniqueFlavorsAfterSharingCandines
{
    /*
        2107
        You are given a 0-indexed integer array candies, where candies[i] represents the flavor of the ith candy.
        Your mom wants you to share these candies with your little sister by giving her k consecutive candies, but you want to keep as many flavors of candies as possible.
        Return the maximum number of unique flavors of candy you can keep after sharing with your sister.

        Example 1:
        Input: candies = [1,2,2,3,4,3], k = 3
        Output: 3
        Explanation:
        Give the candies in the range [1, 3] (inclusive) with flavors [2,2,3].
        You can eat candies with flavors [1,4,3].
        There are 3 unique flavors, so return 3.

        Example 2:
        Input: candies = [2,2,2,2,3,3], k = 2
        Output: 2
        Explanation:
        Give the candies in the range [3, 4] (inclusive) with flavors [2,3].
        You can eat candies with flavors [2,2,2,3].
        There are 2 unique flavors, so return 2.
        Note that you can also share the candies with flavors [2,2] and eat the candies with flavors [2,2,3,3].

        Example 3:
        Input: candies = [2,4,5], k = 0
        Output: 3
        Explanation:
        You do not have to give any candies.
        You can eat the candies with flavors [2,4,5].
        There are 3 unique flavors, so return 3.
     */


    public int shareCandies(int[] candies, int k)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i:candies)
            map.put(i,map.getOrDefault(i,0)+1);
        int sz=map.size();
        if(k==0)
            return sz;

        for(int i=0;i<k;i++)
        {
            map.put(candies[i],map.get(candies[i])-1);
            if(map.get(candies[i])==0)
                sz--;
        }
        int res=sz;
        for(int i=k;i<candies.length;i++)
        {
            if(map.get(candies[i-k])==0)
                sz++;
            map.put(candies[i-k],map.get(candies[i-k])+1);
            map.put(candies[i],map.get(candies[i])-1);
            if(map.get(candies[i])==0)
                sz--;
            res=Math.max(res,sz);
        }
        return res;
    }

    public static void main(String[] args)
    {
        NumUniqueFlavorsAfterSharingCandines obj=new NumUniqueFlavorsAfterSharingCandines();
        System.out.println(obj.shareCandies(new int[]{1,2,2,3,4,3}, 3));
        System.out.println(obj.shareCandies(new int[]{2,2,2,2,3,3}, 2));
    }

}
