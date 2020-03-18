import java.util.Arrays;
import java.util.Comparator;

public class RussianDollEnvelopes
{
    /*
        You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

        What is the maximum number of envelopes can you Russian doll? (put one inside other)

        Note:
        Rotation is not allowed.

        Example:

        Input: [[5,4],[6,4],[6,7],[2,3]]
        Output: 3
        Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
    */
    public int maxEnvelopes(int[][] envelopes)
    {
        if(envelopes==null || envelopes.length==0)
            return 0;
        Arrays.sort(envelopes,new Comparator<int[]>(){
            public int compare(int[] a, int[] b)
            {
                int cmp=Integer.compare(a[0],b[0]);
                if(cmp==0)
                    return Integer.compare(b[1],a[1]);
                return cmp;
            }
        });

        int[] dp=new int[envelopes.length];
        Arrays.fill(dp,1);
        for(int i=1;i<envelopes.length;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(envelopes[i][0]>envelopes[j][0])
                    dp[i]=Math.max(dp[i],dp[j]+1);
            }
        }
        int ans=0;
        for(int i:dp)
            ans=Math.max(ans,i);
        return ans;
    }

    public static void main(String[] args)
    {
        RussianDollEnvelopes obj=new RussianDollEnvelopes();
        System.out.println(obj.maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
    }
}
