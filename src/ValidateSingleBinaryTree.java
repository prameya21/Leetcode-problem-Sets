import java.util.*;

public class ValidateSingleBinaryTree
{
    /*
    Given a list of TreeNodes. TreeNode is standard LC class:

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
    Find out if all these nodes belong to the same valid binary tree.

    public boolean isBinaryTree(List<TreeNode> nodes) {
    }
    Example 1:

    Let's say we have the following binary tree

            1
           ↙ ↘
          2   3
             ↙
            4

    We can create it like this
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);

    n1.left = n2;
    n1.right = n3;
    n3.left = n4;

    Input: [n4, n2, n3, n1]
    Output: true
    Example 2:

             1
           ↙  ↘
          2    3
           ↘  ↙
            4

    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);

    n1.left = n2;
    n1.right = n3;
    n2.right = n4;
    n3.left = n4;

    Input: [n2, n3, n4, n1]
    Output: false
    Example 3:

         1
        ⤤ ⤦
         2

    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);

    n1.left = n2;
    n2.left = n1;

    Input: [n1, n2]
    Output: false
    Example 4:

            1           4
           ↙ ↘        ↙  ↘
          2   3      5     6

    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);
    TreeNode n5 = new TreeNode(5);
    TreeNode n6 = new TreeNode(6);

    n1.left = n2;
    n1.right = n3;

    n4.left = n5;
    n4.right = n6;

    Input: [n2, n6, n4, n1, n3, n5]
    Output: false
    Example 5:

            1
           ↙ ↘
          2   3
             ↙
            4

    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);

    n1.left = n2;
    n1.right = n3;
    n3.left = n4;

    Input: [n2, n3, n1]
    Output: false
    Explanation: l4 is a part of the tree but it's missing in the input list so return false.
     */

    public boolean isBinaryTree(List<TreeNode> nodes)
    {
        if(nodes==null || nodes.size()==0)
            return true;
        Set<TreeNode> roots=new HashSet<>();
        Set<TreeNode> seen=new HashSet<>();
        for(TreeNode node:nodes)
        {
            if(!seen.contains(node))
            {
                helper(node,seen,roots);
                roots.add(node);
            }

        }

        return roots.size()==1 && seen.size()==nodes.size();
    }

    public void helper(TreeNode node, Set<TreeNode> seen, Set<TreeNode> roots)
    {
        if(seen.contains(node) || node==null)
            return;
        roots.remove(node);
        seen.add(node);
        helper(node.left,seen,roots);
        helper(node.right,seen,roots);
    }

    public static void main(String[] args)
    {
        ValidateSingleBinaryTree obj=new ValidateSingleBinaryTree();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n3;

        n4.left = n5;
        n4.right = n6;

        List<TreeNode> nodes=new ArrayList<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);

        System.out.println(obj.isBinaryTree(nodes));
    }
}


/*
            1
           ↙ ↘
          2   3
             ↙
            4

 */
