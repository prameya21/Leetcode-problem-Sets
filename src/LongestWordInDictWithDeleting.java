import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestWordInDictWithDeleting
{
    /*
    Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string.
    If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

    Example 1:
    Input:
    s = "abpcplea", d = ["ale","apple","monkey","plea"]

    Output:
    "apple"
    Example 2:
    Input:
    s = "abpcplea", d = ["a","b","c"]

    Output:
    "a"
     */
    public static boolean isSubSequence(String s,String x)
    {
        int i=0,j=0;
        while(i<s.length() && j<x.length())
        {
            if(s.charAt(i)==x.charAt(j))
                j++;
            i++;
        }
        return j==x.length();
    }
    public static String findLongestWord(String s, List<String> dict)
    {
        String ans="";
        for(String str:dict)
        {
            if(isSubSequence(s,str) && str.length()>ans.length())
                ans=str;
        }
        return ans;
    }
    public static void main(String[] args)
    {
        List<String> dict=new ArrayList<>(Arrays.asList("ale","apple","monkey","plea"));
        System.out.println(findLongestWord("abpcplea",dict));
    }
}
