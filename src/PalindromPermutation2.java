import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromPermutation2
{
    /*
    Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

    Example 1:

    Input: "aabb"
    Output: ["abba", "baab"]
    Example 2:

    Input: "abc"
    Output: []
     */

    public List<String> generatePalindromes(String s)
    {
        if(s==null || s.length()==0)
            return new ArrayList<>();
        List<String> res=new ArrayList<>();
        Map<Character,Integer> map=new HashMap<>();
        for(char c:s.toCharArray())
            map.put(c,map.getOrDefault(c,0)+1);
        int oddCount=0;
        for(char c:map.keySet())
            if(map.get(c)%2!=0)
                oddCount++;
        if(oddCount>1)
            return res;
        Character odd=null;
        for(char c:map.keySet())
        {
            if(map.get(c)%2!=0)
                odd=c;
            map.put(c,map.get(c)/2);
        }
        getPermutations(res,map,"",s.length()/2,odd);
        return res;
    }
    public void getPermutations(List<String> res, Map<Character,Integer> map, String s, int len,Character odd)
    {
        if(s.length()==len)
        {
            if(odd==null)
            {
                StringBuilder sb=new StringBuilder(s);
                s=s+sb.reverse().toString();
                System.out.println(s);
                res.add(s);
            }
            else
            {
                StringBuilder sb=new StringBuilder(s);
                s=s+odd+sb.reverse().toString();
                System.out.println(s);
                res.add(s);
            }
            return;
        }
        else
        {
            for(char c:map.keySet())
            {
                if(map.get(c)>0)
                {
                    int temp=map.get(c);
                    map.put(c,temp-1);
                    getPermutations(res,map,s+c,len,odd);
                    map.put(c,temp);
                }
            }
        }
    }



    public static void main(String[] args)
    {
        PalindromPermutation2 obj=new PalindromPermutation2();
        System.out.println(obj.generatePalindromes("cccaabb"));
    }
}
