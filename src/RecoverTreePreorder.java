public class RecoverTreePreorder
{
    /*
    We run a preorder depth first search on the root of a binary tree.

    At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)

    If a node has only one child, that child is guaranteed to be the left child.

    Given the output S of this traversal, recover the tree and return its root.
    Example 1:
    Input: "1-2--3--4-5--6--7"
    Output: [1,2,5,3,4,6,7]

    Example 2:
    Input: "1-2--3---4-5--6---7"
    Output: [1,2,5,3,null,6,null,4,null,7]

    Example 3:
    Input: "1-401--349---90--88"
    Output: [1,401,null,349,88,90]


    Note:

    The number of nodes in the original tree is between 1 and 1000.
    Each node will have a value between 1 and 10^9.
     */
    int idx;
    public TreeNode recoverFromPreorder(String S)
    {
        idx=0;
        return build(S,0);
    }
    public TreeNode build(String S, int height)
    {
        int id=0;
        int dash=0;
        while(idx+dash<S.length() && S.charAt(idx+dash)=='-')
            dash++;
        if(dash!=height)
            return null;
        int num=idx+dash;
        while(num<S.length() && S.charAt(num)!='-')
            num++;
        TreeNode root=new TreeNode(Integer.parseInt(S.substring(idx+dash,num)));
        idx=num;
        root.left=build(S,height+1);
        root.right=build(S,height+1);
        return root;
    }



    public static void main(String[] args)
    {
        RecoverTreePreorder obj=new RecoverTreePreorder();
        TreeNode root=obj.recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println(root);
    }
}
