
public class MaximumXORInArray
{
    /*
    Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.

    Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

    Could you do this in O(n) runtime?

    Example:

    Input: [3, 10, 5, 25, 2, 8]

    Output: 28

    Explanation: The maximum result is 5 ^ 25 = 28.
     */


    class TrieNode
    {
        TrieNode[] next;
        public TrieNode()
        {
            next=new TrieNode[2];
        }
    }

    public int findMaximumXOR(int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;
        TrieNode root=new TrieNode();
        for(int num:nums)
        {
            TrieNode curr=root;
            for(int i=31;i>=0;i--)
            {
                int currBit=(num>>>i)&1;
                if(curr.next[currBit]==null)
                    curr.next[currBit]=new TrieNode();
                curr=curr.next[currBit];
            }
        }

        int max=Integer.MIN_VALUE;
        for(int num:nums)
        {
            TrieNode curr=root;
            int sum=0;
            for(int i=31;i>=0;i--)
            {
                int currBit=(num>>>i)&1;
                if(curr.next[currBit^1]!=null)
                {
                    sum+=(1<<i);
                    curr=curr.next[currBit^1];
                }
                else
                    curr=curr.next[currBit];
            }
            max=Math.max(max,sum);
        }
        return max;
    }

    public static void main(String[] args)
    {
        MaximumXORInArray obj=new MaximumXORInArray();
        System.out.println(obj.findMaximumXOR(new int[]{3,10,5,25,2,8}));
    }
}
