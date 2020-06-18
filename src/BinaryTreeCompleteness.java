import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeCompleteness
{
    /*
    Given a binary tree, determine if it is a complete binary tree.

    Definition of a complete binary tree from Wikipedia:
    In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.



    Example 1:



    Input: [1,2,3,4,5,6]
    Output: true
    Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
    Example 2:



    Input: [1,2,3,4,5,null,7]
    Output: false
    Explanation: The node with value 7 isn't as far left as possible.

    Note:

    The tree will have between 1 and 100 nodes.
     */

    public boolean isCompleteTree(TreeNode root)
    {
        if(root==null)
            return false;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while(q.peek()!=null)
        {
            TreeNode curr=q.poll();
            q.offer(curr.left);
            q.offer(curr.right);
        }

        while(!q.isEmpty() && q.peek()==null)
            q.poll();
        return q.isEmpty();
    }

    public static void main(String[] args)
    {
        BinaryTreeCompleteness obj=new BinaryTreeCompleteness();
        TreeNode root1=new TreeNode(1);
        root1.left=new TreeNode(2);
        root1.right=new TreeNode(3);
        root1.left.left=new TreeNode(4);
        root1.left.right=new TreeNode(5);
        root1.right.left=new TreeNode(6);

        System.out.println(obj.isCompleteTree(root1));

        TreeNode root2=new TreeNode(1);
        root2.left=new TreeNode(2);
        root2.right=new TreeNode(3);
        root2.left.left=new TreeNode(4);
        root2.left.right=new TreeNode(5);
        root2.right.right=new TreeNode(7);

        System.out.println(obj.isCompleteTree(root2));;


    }
}
