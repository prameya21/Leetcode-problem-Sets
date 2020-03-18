import java.util.ArrayList;
import java.util.List;

public class TwentyFourGame
{
    /*
        You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

        Example 1:
        Input: [4, 1, 8, 7]
        Output: True
        Explanation: (8-4) * (7-1) = 24
        Example 2:
        Input: [1, 2, 1, 2]
        Output: False
        Note:
        The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
        Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
        You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
     */
    public boolean judgePoint24(int[] nums)
    {
        List<Double> list=new ArrayList<>();
        for(int n:nums)
            list.add((double) n);

        return solve(list);
    }

    public boolean solve(List<Double> list)
    {
        if(list.size()==0)
            return false;
        if(list.size()==1 && Math.abs(list.get(0)-24.0)<=0.001)
            return true;

        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++)
            {
                for(double c: getPossibleValues(list.get(i),list.get(j)))
                {
                   List<Double> next=new ArrayList<>();
                   next.add(c);
                   for(int k=0;k<list.size();k++)
                   {
                       if(k==i || k==j)
                           continue;
                       next.add(list.get(k));
                   }
                   if(solve(next))
                       return true;
                }
            }
        }
        return false;
    }
    public List<Double> getPossibleValues(double a, double b)
    {
        List<Double> res=new ArrayList<>();
        res.add(a+b);
        res.add(a-b);
        res.add(b-a);
        res.add(a*b);
        res.add(a/b);
        res.add(b/a);
        return res;
    }

    public boolean judgePoint24Fast(int[] nums)
    {
        double[] arr=new double[]{nums[0],nums[1],nums[2],nums[3]};
        return solve(arr);
    }
    public boolean solve(double[] nums)
    {
        if(nums.length==1)
        {
            if(Math.abs(nums[0]-24.0)<0.001)
                return true;
            return false;
        }
        for(int i=0;i<nums.length;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                double[] next=new double[nums.length-1];
                int nextctr=0;
                for(int k=0;k<nums.length;k++)
                {
                    if(k==i || k==j)
                        continue;
                    next[nextctr++]=nums[k];
                }
                for(double c:getPossibleValuesFast(nums[i],nums[j]))
                {
                    next[next.length-1]=c;
                    if(solve(next))
                        return true;
                }
            }
        }
        return false;
    }
    public double[] getPossibleValuesFast(double a, double b)
    {
        return new double[]{a+b,a-b,b-a,a*b,a/b,b/a};
    }

    public static void main(String[] args)
    {
        TwentyFourGame obj=new TwentyFourGame();
        System.out.println(obj.judgePoint24Fast(new int[]{4,1,8,7}));
    }
}
