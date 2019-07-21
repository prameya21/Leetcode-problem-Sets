import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses
{
    /*
    Given a string containing only digits, restore it by returning all possible valid IP address combinations.

    Example:

    Input: "25525511135"
    Output: ["255.255.11.135", "255.255.111.35"]
     */
    public List<String> restoreIpAddresses(String s)
    {
        if(s==null || s.length()==0)
            return new ArrayList<>();
        List<String> res=new ArrayList<>();
        backtrack(res,s,"",0,s.length());
        return res;
    }
    public void backtrack(List<String> res,String s,String prefix,int dot,int len)
    {
        if(dot>4)
            return;
        if(dot==4 && prefix.length()==len+3 )
        {
            res.add(prefix);
            return;
        }
        for(int i=1;i<4 && i<=s.length();i++)
        {
            String temp=s.substring(0,i);
            if(Integer.parseInt(temp)>255 || (temp.charAt(0)=='0' && temp.length()>1))
                continue;
            backtrack(res,s.substring(i),prefix+temp+(dot==3?"":"."),dot+1,len);
        }
    }
    public static void main(String[] args)
    {
        RestoreIPAddresses obj=new RestoreIPAddresses();
        System.out.println(obj.restoreIpAddresses("25525511135"));
    }
}
