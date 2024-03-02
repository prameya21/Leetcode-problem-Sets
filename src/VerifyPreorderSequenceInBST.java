import java.util.Stack;

public class VerifyPreorderSequenceInBST
{
    /*
        Given an array of unique integers preorder, return true if it is the correct preorder traversal sequence of a binary search tree.
        Example 1:
        Input: preorder = [5,2,1,3,6]
        Output: true

        Example 2:
        Input: preorder = [5,2,6,1,3]
        Output: false


        Constraints:

        1 <= preorder.length <= 104
        1 <= preorder[i] <= 104
        All the elements of preorder are unique.


        Follow up: Could you do it using only constant space complexity?
     */

    public boolean verifyPreorder(int[] preorder)
    {
        Stack<Integer> st=new Stack<>();
        int min=Integer.MIN_VALUE;
        for(int i:preorder)
        {
            while(!st.isEmpty() && st.peek()<i)
                min=st.pop();
            if(i<=min)
                return false;
            st.push(i);
        }
        return true;
    }
}
