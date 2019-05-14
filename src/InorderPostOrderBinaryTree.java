import java.util.HashMap;
import java.util.Map;

public class InorderPostOrderBinaryTree
{
    /*
    Given inorder and postorder traversal of a tree, construct the binary tree.

    Note:
    You may assume that duplicates do not exist in the tree.

    For example, given

    inorder = [9,3,15,20,7]
    postorder = [9,15,7,20,3]
    Return the following binary tree:

        3
       / \
      9  20
        /  \
       15   7
     */


    public static TreeNode buildTree(int[] inorder, int[] postorder)
    {
        Map<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<inorder.length;i++)
            hm.put(inorder[i],i);
        return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1,hm);
    }
    public static TreeNode build(int[] inorder,int inorder_start,int inorder_end,int[] postorder,int postorder_start,int postorder_end,Map<Integer,Integer> hm)
    {
        if(postorder_start>postorder_end || inorder_start>inorder_end)
            return null;
        int breakIndex=hm.get(postorder[postorder_end]);
        TreeNode root=new TreeNode(postorder[postorder_end]);
        root.left=build(inorder,inorder_start,breakIndex-1,postorder,postorder_start,postorder_start+breakIndex-inorder_start-1,hm);
        root.right=build(inorder,breakIndex+1,inorder_end,postorder,postorder_start+breakIndex-inorder_start,postorder_end-1,hm);
        return root;
    }
    public static void main(String[] args)
    {
        int[] inorder={9,3,15,20,7};
        int[] postorder={9,15,7,20,3};
        TreeNode root=buildTree(inorder,postorder);
        System.out.println(root);
    }
}
