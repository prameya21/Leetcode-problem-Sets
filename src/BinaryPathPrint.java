import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BinaryPathPrint
{
    /*
        Given a Binary Tree of distinct nodes and a pair of nodes. The task is to find and print the path between the two given nodes in the binary tree.
     */

    public List<Integer> pathPrint(TreeNode root, int val1, int val2)
    {
        TreeNode lca=lca(root,val1,val2);
        System.out.println(lca.val);
        List<Integer> path1=new ArrayList<>();
        List<Integer> path2=new ArrayList<>();
        getPath(path1,lca,val1);
        getPath(path2,lca,val2);
        System.out.println(path1);
        System.out.println(path2);
        if(path1.size()==1)
            return path2;
        else if(path2.size()==1)
            return path1;
        else
        {
            Collections.reverse(path1);
            path1.remove(path1.size()-1);
            path1.addAll(path2);
            return path1;
        }
    }
    public TreeNode lca(TreeNode root, int p, int q)
    {
        if(root==null)
            return null;
        if(root.val==p || root.val==q)
            return root;
        TreeNode left=lca(root.left,p,q);
        TreeNode right=lca(root.right,p,q);
        if(left!=null && right!=null)
            return root;
        return left==null?right:left;
    }
    public boolean getPath(List<Integer> path, TreeNode root, int val1)
    {
        if(root==null)
            return false;
        path.add(root.val);
        if(root.val==val1 || getPath(path,root.left,val1) || getPath(path,root.right,val1))
            return true;
        path.remove(path.size()-1);
        return false;
    }


    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.right.right=new TreeNode(7);
        root.right.left=new TreeNode(6);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.left.right.left=new TreeNode(9);
        root.left.right.right=new TreeNode(8);
        BinaryPathPrint obj=new BinaryPathPrint();
        System.out.println(obj.pathPrint(root,4,8));
    }

}
