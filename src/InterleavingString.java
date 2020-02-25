import java.util.Arrays;

public class InterleavingString
{
    /*
        Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

        Example 1:

        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        Output: true
        Example 2:

        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        Output: false
     */

    public boolean isInterleave(String s1, String s2, String s3)
    {
        //return recursiveHelper(s1,0,s2,0,"",s3);
        int[][] memo=new int[s1.length()][s2.length()];
        return recursiveHelperMemo(s1,0,s2,0,s3,0,memo);
    }

    public boolean recursiveHelperMemo(String s1, int i, String s2, int j, String s3, int k, int[][] memo)
    {
        if(i==s1.length())
            return s2.substring(j).equals(s3.substring(k));
        if(j==s2.length())
            return s1.substring(i).equals(s3.substring(k));
        if(memo[i][j]!=0)
            return memo[i][j]==1?true:false;
        boolean ans=false;
        if((s1.charAt(i)==s3.charAt(k) && recursiveHelperMemo(s1,i+1,s2,j,s3,k+1,memo)) || (s2.charAt(j)==s3.charAt(k) && recursiveHelperMemo(s1,i,s2,j+1,s3,k+1,memo)))
            ans=true;
        memo[i][j]=ans?1:-1;
        return ans;
    }



    public boolean recursiveHelper(String s1, int i, String s2, int j, String res, String s3)
    {
        if(s3.equals(res) && i==s1.length() && j==s2.length())
            return true;
        boolean ans=false;
        if(i<s1.length())
            ans=ans | recursiveHelper(s1,i+1,s2,j,res+s1.charAt(i),s3);
        if(j<s2.length())
            ans=ans | recursiveHelper(s1,i,s2,j+1,res+s2.charAt(j),s3);
        return ans;
    }

    public static void main(String[] args)
    {
        InterleavingString obj=new InterleavingString();
        System.out.println(obj.isInterleave("aabcc","dbbca","aadbbbaccc"));
    }


}
