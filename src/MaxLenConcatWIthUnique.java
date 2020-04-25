import java.util.Arrays;
import java.util.List;

public class MaxLenConcatWIthUnique
{
    /*
        Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

        Return the maximum possible length of s.



        Example 1:

        Input: arr = ["un","iq","ue"]
        Output: 4
        Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
        Maximum length is 4.
        Example 2:

        Input: arr = ["cha","r","act","ers"]
        Output: 6
        Explanation: Possible solutions are "chaers" and "acters".
        Example 3:

        Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
        Output: 26
     */
    int max=0;
    public int maxLength(List<String> arr)
    {

        if(arr.size()==0)
            return 0;
        helper(arr,0,"");
        return max;
    }
    public void helper(List<String> arr, int start, String str)
    {
        if(start>=arr.size())
        {
            if(checkUnique(str))
                max=Math.max(max,str.length());
            return;
        }
        if(!checkUnique(str))
            return;
        for(int i=start;i<arr.size();i++)
        {
            max=Math.max(max,str.length());
             helper(arr,i+1,str+arr.get(i));
        }
    }
    public boolean checkUnique(String str)
    {
        int[] map=new int[26];
        for(char c:str.toCharArray())
        {
            if(map[c-'a']>=1)
                return false;
               map[c-'a']++;
        }
        return true;
    }

    public static void main(String[] args)
    {
        MaxLenConcatWIthUnique obj=new MaxLenConcatWIthUnique();
        System.out.println(obj.maxLength(Arrays.asList("cha","r","act","ers")));
    }

}
