import java.util.Stack;

public class DecodeString
{
    /*
    Given an encoded string, return it's decoded string.

    The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

    You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

    Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

    Examples:

    s = "3[a]2[bc]", return "aaabcbc".
    s = "3[a2[c]]", return "accaccacc".
    s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     */
    public static String decode(String str)
    {
        Stack<Integer> count=new Stack<>();
        Stack<String> data=new Stack<>();
        data.push("");
        for(int i=0;i<str.length();i++)
        {
            char c=str.charAt(i);
            if(c>='0' && c<='9')
            {
                int j=i;
                while(i+1<str.length() && (str.charAt(i+1)>='0' && str.charAt(i+1)<='9'))
                    i++;
                int val=Integer.parseInt(str.substring(j,i+1));
                count.push(val);
            }
            else if(c=='[')
                data.push("");
            else if(c==']')
            {
                String s=data.pop();
                int ctr=count.pop();
                String val="";
                for(int k=0;k<ctr;k++)
                    val+=s;
                data.push(data.pop()+val);
            }
            else
            {
                data.push(data.pop()+String.valueOf(c));
            }
        }
        return data.pop();
    }
    public static void main(String[] args)
    {
        System.out.println(decode("2[abc]3[cd]ef"));
    }
}
