import java.util.HashMap;
import java.util.Map;

public class InorderPreorderBinaryTree
{
    /*
    Given preorder and inorder traversal of a tree, construct the binary tree.

    Note:
    You may assume that duplicates do not exist in the tree.

    For example, given

    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]
    Return the following binary tree:

        3
       / \
      9  20
        /  \
       15   7
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder)
    {
        Map<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<inorder.length;i++)
            hm.put(inorder[i],i);
        return build(preorder,0,inorder,0,inorder.length-1,hm);
    }
    public static TreeNode build(int[] preorder,int pre_start,int [] inorder,int in_start,int in_end,Map<Integer,Integer> hm)
    {
        if(pre_start>preorder.length-1|| in_start>in_end)
            return null;
        TreeNode root=new TreeNode(preorder[pre_start]);
        int breakIndex=hm.get(preorder[pre_start]);
        root.left=build(preorder,pre_start+1,inorder,in_start,breakIndex-1,hm);
        root.right=build(preorder,pre_start+breakIndex-in_start+1,inorder,breakIndex+1,in_end,hm);
        return root;
    }
    public static void main(String[] args)
    {
        int[] inorder={9,3,15,20,7};
        int[] preorder={3,9,20,15,7};
        TreeNode root=buildTree(preorder,inorder);
        System.out.println(root);
    }
}
