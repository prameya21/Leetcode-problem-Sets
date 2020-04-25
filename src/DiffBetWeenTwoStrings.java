import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiffBetWeenTwoStrings
{
    /*
        Given two strings of uppercase letters source and target, list (in string form) a sequence of edits to convert from source to target that uses the least edits possible.

        For example, with strings source = "ABCDEFG", and target = "ABDFFGH" we might return: ["A", "B", "-C", "D", "-E", "F", "+F", "G", "+H"

        More formally, for each character C in source, we will either write the token C, which does not count as an edit; or write the token -C, which counts as an edit.

        Additionally, between any token that we write, we may write +D where D is any letter, which counts as an edit.

        At the end, when reading the tokens from left to right, and not including tokens prefixed with a minus-sign, the letters should spell out target (when ignoring plus-signs.)

        In the example, the answer of A B -C D -E F +F G +H has total number of edits 4 (the minimum possible), and ignoring subtraction-tokens, spells out A, B, D, F, +F, G, +H which represents the string target.

        If there are multiple answers, use the answer that favors removing from the source first.

        Constraints:

        [time limit] 5000ms
        [input] string source
        2 ≤ source.length ≤ 12
        [input] string target
        2 ≤ target.length ≤ 12
        [output] array.string
    */
    List<String> diffBetweenTwoStrings(String source, String target)
    {
        int[][] dp=new int[source.length()+1][target.length()+1];
        for(int i=0;i<=source.length();i++)
            dp[i][0]=i;
        for(int i=0;i<=target.length();i++)
            dp[0][i]=i;
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                if(source.charAt(i-1)==target.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1];
                else
                    dp[i][j]=1+Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }

        char[] s=source.toCharArray();
        char[] t=target.toCharArray();
        int i=0,j=0;
        List<String> res=new ArrayList<>();
        while(i<s.length && j<t.length)
        {
            if(s[i]==t[i])
            {
                res.add(""+s[i]);
                i++;
                j++;
            }
            else
            {
                if(dp[i+1][j]<=dp[i][j+1])
                    res.add("-"+s[i++]);
                else
                    res.add("+"+t[j++]);
            }
        }

        System.out.println(Arrays.deepToString(dp));
        return null;
    }

    public static void main(String[] args)
    {
        DiffBetWeenTwoStrings obj=new DiffBetWeenTwoStrings();
        obj.diffBetweenTwoStrings("ABCDEFG","ABDFFGH");
    }

}