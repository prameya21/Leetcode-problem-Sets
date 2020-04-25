import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning
{
    /*
    Given a string s, partition s such that every substring of the partition is a palindrome.

    Return all possible palindrome partitioning of s.

    Example:

    Input: "aab"
    Output:
    [
      ["aa","b"],
      ["a","a","b"]
    ]
     */

    public List<List<String>> partition(String s)
    {
        if(s==null || s.length()==0)
            return new ArrayList<>();
        List<List<String>> res=new ArrayList<>();
        helper(s,res,new ArrayList<>());
        return res;
    }
    public void helper(String s, List<List<String>> res, List<String> temp)
    {
        if(s.length()==0 || s.isEmpty())
        {
            res.add(new ArrayList<>(temp));
            return;
        }
        else
        {
            for(int i=1;i<=s.length();i++)
            {
                String str=s.substring(0,i);
                if(isPalindrome(str))
                {
                    temp.add(str);
                    helper(s.substring(i),res,temp);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }

    public boolean isPalindrome(String s)
    {
        int l=0,r=s.length()-1;
        while(l<=r)
        {
            if(s.charAt(l++)!=s.charAt(r--))
                return false;
        }
        return true;
    }


    public static void main(String[] args)
    {
        PalindromePartitioning obj=new PalindromePartitioning();
        System.out.println(obj.partition("aab"));
    }
}
