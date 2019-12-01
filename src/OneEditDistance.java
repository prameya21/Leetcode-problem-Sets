public class OneEditDistance
{
    /*
    Given two strings s and t, determine if they are both one edit distance apart.

    Note:

    There are 3 possiblities to satisify one edit distance apart:

    Insert a character into s to get t
    Delete a character from s to get t
    Replace a character of s to get t
    Example 1:

    Input: s = "ab", t = "acb"
    Output: true
    Explanation: We can insert 'c' into s to get t.
    Example 2:

    Input: s = "cab", t = "ad"
    Output: false
    Explanation: We cannot get t from s by only one step.
    Example 3:

    Input: s = "1203", t = "1213"
    Output: true
    Explanation: We can replace '0' with '1' to get t.
     */
    public boolean isOneEditDistance(String s, String t)
    {
        if(s.length()==t.length())
            return oneMismatch(s,t);
        if(s.length()+1==t.length())
            return oneEdit(s,t);
        if(t.length()+1==s.length())
            return oneEdit(t,s);
        return false;
    }
    public boolean oneEdit(String s, String t)
    {
        for(int i=0,j=0;i<s.length() && j<t.length();i++,j++)
            if(s.charAt(i)!=t.charAt(j))
                return s.substring(i+1).equals(t.substring(j));
        return true;
    }
    public boolean oneMismatch(String s, String t)
    {
        int mismatch=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)!=t.charAt(i))
                mismatch++;
        }
        return mismatch==1;
    }
    public static void main(String[] args)
    {
        OneEditDistance obj =new OneEditDistance();
        System.out.println(obj.isOneEditDistance("1203","1213"));
    }
}
