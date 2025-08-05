import java.util.HashSet;
import java.util.Set;

public class AddBoldTag
{
    /*
    616
    Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
    Example 1:
    Input:
    s = "abcxyz123"
    dict = ["abc","123"]
    Output:
    "<b>abc</b>xyz<b>123</b>"
    Example 2:
    Input:
    s = "aaabbcc"
    dict = ["aaa","aab","bc"]
    Output:
    "<b>aaabbc</b>c"
     */
    public static String addBoldTag(String s, Set<String> dict)
    {
        int end=0;
        boolean[] bold=new boolean[s.length()];
        for(int i=0;i<s.length();i++)
        {
            for(String str:dict)
            {
                if(s.startsWith(str,i))
                {
                    end=Math.max(end,i+str.length());
                }
            }
            bold[i]=end>i;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            if(!bold[i])
                sb.append(s.charAt(i));
            else
            {
                int j=i;
                while(j<s.length() && bold[j])
                    j++;
                sb.append("<b>");
                sb.append(s.substring(i,j));
                sb.append("</b>");
                i=j-1;
            }
        }
        return sb.toString();
    }
    public static void main(String[] args)
    {
        Set<String> dict=new HashSet<>();
        dict.add("abc");
        dict.add("123");
        System.out.println(addBoldTag("abcxyz123",dict));
    }
}
