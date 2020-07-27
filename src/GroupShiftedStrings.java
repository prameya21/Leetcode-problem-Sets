//import sun.security.provider.certpath.Vertex;

import java.util.*;

public class GroupShiftedStrings
{
    /*
    Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

    "abc" -> "bcd" -> ... -> "xyz"
    Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

    Example:

    Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
    Output:
    [
      ["abc","bcd","xyz"],
      ["az","ba"],
      ["acef"],
      ["a","z"]
    ]
     */

    public List<List<String>> groupStrings(String[] strings)
    {
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strings)
        {
            String key="";
            for(int i=0;i<str.length()-1;i++)
            {
                char c1=str.charAt(i);
                char c2=str.charAt(i+1);
                int val1=c1-'a';
                int val2=c2-'a';
                if(c2<c1)
                    val2+=26;
                int diff=val1-val2;
                key+=diff+",";
            }
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args)
    {
        GroupShiftedStrings obj=new GroupShiftedStrings();
        System.out.println(obj.groupStrings(new String[]{"az","ba","abc","bcd","xyz","acef","cegh","a","z"}));
    }
}
