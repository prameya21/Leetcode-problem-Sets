public class ValidPalindrome
{
    /*
        Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

        Example 1:
        Input: "aba"
        Output: True
        Example 2:
        Input: "abca"
        Output: True
        Explanation: You could delete the character 'c'.
        Note:
        The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
     */
    public boolean helper(String s)
    {
        int r=s.length()-1;
        int l=0;
        while(l<=r)
        {
            if(s.charAt(l)!=s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
    public boolean validPalindrome(String s)
    {
        if(s==null || s.length()==0)
            return false;
        int r=s.length()-1;
        int l=0;
        while(l<=r)
        {
            if(s.charAt(l)!=s.charAt(r))
                return helper(s.substring(l,r)) || helper(s.substring(l+1,r+1));
            l++;
            r--;
        }
        return true;
    }
    public static void main(String[] args)
    {
        ValidPalindrome obj=new ValidPalindrome();
        System.out.println(obj.validPalindrome("abcdcbea"));
    }
}
