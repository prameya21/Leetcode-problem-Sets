import java.util.*;

public class CheckTypo
{
    /*
    Given a dictionary of valid words, write a function isTypoBecauseStuckKey() that accepts a string to determine if the string has a typo that is strictly caused by a stuck key.

    Example:

    Input:
    Dictionary: { hello, cat, world, dog, bird, grass, green, help, greet, great }
    String: bbbirrrdddd

    Output: True

    Explanation: The character's 'b', 'r', & 'd' all repeat. Assuming their keys got stuck, we can form the word 'bird', which exists in the dictionary.
    Example:

    Input:
    Dictionary: { hello, cat, world, dog, bird, grass, green, help, greet, great }
    String: gggraasssa

    Output: False

    Explanation: The a at the end is not the result of a stuck key, and thus it is impossible for it to exist in the dictionary.
     */

    public boolean isTypoBecauseStuckKey(String S, Set<String> words)
    {
        Map<String,String> map=new HashMap<>();
        for(String str:words)
            map.put(str,encode(str));
        String str=encode(S);
        for(String s:map.keySet())
        {
            String val=map.get(s);
            if(str.length()==val.length())
            {
                boolean match=true;
                for(int i=0;i<str.length();i++)
                {
                    char c1=str.charAt(i);
                    char c2=val.charAt(i);
                    if(c1==c2)
                        continue;
                    else
                    {
                        if(Character.isDigit(c1) && Character.isDigit(c2))
                        {
                            if(Integer.parseInt(""+c1)>=Integer.parseInt(""+c2))
                                continue;
                        }
                        match=false;
                        break;
                    }
                }
                if(match)
                    return true;
            }
        }
        return false;
    }

    public String encode(String str)
    {
        StringBuilder sb=new StringBuilder();
        int count=0;
        char[] ch=str.toCharArray();
        sb.append(ch[0]+"");
        for(int i=0;i<ch.length;i++)
        {
            if(i>0 && ch[i]!=ch[i-1])
            {
                sb.append(count);
                sb.append(""+ch[i]);
                count=0;
            }
            count++;
        }
        sb.append(count);
        return sb.toString();
    }

    public static void main(String[] args)
    {
        CheckTypo obj=new CheckTypo();
        System.out.println(obj.isTypoBecauseStuckKey("gggraasssa",new HashSet<>(Arrays.asList("hello", "cat", "world", "dog", "bird", "grass", "green", "help", "greet", "great"))));

    }
}
