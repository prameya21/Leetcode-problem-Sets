import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringPermutation
{
    /*
        Given a String of alphabets from the english lexicon, return a list of all possible permutations of the letters

        ip: abc
        op: abc,acb,cab,cba,bac,bca
     */

    public List<String> permute(String str)
    {
        if(str==null || str.length()==0)
            return new ArrayList<>();
        Map<Character,Integer> map=new HashMap<>();
        for(char c:str.toCharArray())
            map.put(c,map.getOrDefault(c,0)+1);
        List<String> res=new ArrayList<>();
        permuteHelper(str.length(),map,res,"");
        return res;
    }
    public void permuteHelper(int n,Map<Character,Integer> map,List<String> res, String str)
    {
        if(str.length()==n)
        {
            res.add(str);
            return;
        }
        if(str.length()>n)
            return;
        for(char c:map.keySet())
        {
            int count=map.get(c);
            if(count>0)
            {
                map.put(c,count-1);
                permuteHelper(n,map,res,str+c);
                map.put(c,count);
            }
        }
    }
    public static void main(String[] args)
    {
        StringPermutation obj=new StringPermutation();
        System.out.println(obj.permute("aabc"));
    }
}
