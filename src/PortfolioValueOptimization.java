import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class PortfolioValueOptimization
{
    /*
    You have some securities available to buy that each has a price Pi.
    Your friend predicts for each security the stock price will be Si at some future date.
    But based on volatility of each share, you only want to buy up to Ai shares of each security i.
    Given M dollars to spend, calculate the maximum value you could potentially
    achieve based on the predicted prices Si (and including any cash you have remaining).

    Pi = Current Price
    Si = Expected Future Price
    Ai = Maximum units you are willing to purchase
    M = Dollars available to invest
    Example 1:
    Input:
    M = $140 available

    P1=15, S1=45, A1=3 (AAPL)
    P2=40, S2=50, A2=3 (BYND)
    P3=25, S3=35, A3=3 (SNAP)
    P4=30, S4=25, A4=4 (TSLA)

    Output: $265 (no cash remaining) (I'm not sure if this is with fractional or without) This was not specified in the question.

    But we'll have two answers based on our implementation:

    With fractional buying (Unbounded knapsack)
    Without fractional buying (1/0 Knapsack)
    Example 2:
    Input:
    M = $30
    P1=15, S1=30, A1=3 (AAPL)
    P2=20, S2=45, A2=3 (TSLA)

    Output:
    When buying fractionals,
    Buy 1.5 shares of TSLA ($67.5 value)

    When buying whole shares,
    Buy 2 shares of AAPL ($60 value)
     */


    public int portfolioOptimizationWhole(int amount, int[] pi, int[] si, int[] ai)
    {
        return helper(amount,pi,si,ai,0,new HashMap<>());
    }

    public int helper(int val, int[] p, int[] s, int[] a, int idx, Map<String,Integer> map)
    {
        if(idx>=p.length || val==0)
            return val;
        if(p[idx]>s[idx])
            return 0;
        String key=val+","+idx;
        if(map.containsKey(key))
            return map.get(key);

        int profit=0;
        for(int i=0;i<=a[idx];i++)
        {
            if(val<i*p[idx])
                continue;
            profit=Math.max(profit,helper(val-i*p[idx],p,s,a,idx+1,map)+i*s[idx]);
        }
        map.put(key,profit);
        return profit;
    }

    public double portfolioOptimizationFraction(int val, int[] pi, int[] si, int[] ai)
    {
        double[][] st=new double[pi.length][4];
        double amount=val*1.0;
        for(int i=0;i<pi.length;i++)
        {
            double d=(double)si[i]*1.0/pi[i];
            st[i][0]=d;
            st[i][1]=pi[i];
            st[i][2]=si[i];
            st[i][3]=ai[i];
        }
        Arrays.sort(st, new Comparator<double[]>() {
            @Override
            public int compare(double[] i, double[] j) {
                return Double.compare(j[0],i[0]);
            }
        });
        double revenue=0;
        for(int i=0;i<st.length && amount>0;i++)
        {
            if(st[i][1]>st[i][2])
                continue;
           double count=Math.min(amount/st[i][1],st[i][3]);
           revenue+=count*st[i][2];
           amount-=count*st[i][1];
        }
        return revenue;
    }

    public static void main(String[] args)
    {
        PortfolioValueOptimization obj=new PortfolioValueOptimization();
        System.out.println(obj.portfolioOptimizationWhole(30,new int[]{15,30},new int[]{30,45},new int[]{3,3}));
        System.out.println(obj.portfolioOptimizationWhole(140,new int[]{15,40,25,30},new int[]{45,50,35,25},new int[]{3,3,3,4}));
        System.out.println(obj.portfolioOptimizationFraction(140,new int[]{15,40,25,30},new int[]{45,50,35,25},new int[]{3,3,3,4}));


        System.out.println(obj.portfolioOptimizationWhole(35,new int[]{15,20},new int[]{30,45},new int[]{3,3}));
        System.out.println(obj.portfolioOptimizationWhole(45,new int[]{15,20},new int[]{30,45},new int[]{3,3}));
        System.out.println(obj.portfolioOptimizationWhole(10,new int[]{1,2,3,4},new int[]{4,3,2,1},new int[]{4,3,2,1}));
        System.out.println(obj.portfolioOptimizationWhole(10,new int[]{1,2,3,4},new int[]{4,3,2,1},new int[]{1,2,3,4}));
    }
}


