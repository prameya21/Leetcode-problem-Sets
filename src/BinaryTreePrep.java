import java.util.ArrayList;

class Node
{
    int data;
    Node left=null;
    Node right=null;
    public Node(int i)
    {
        data=i;
    }
}
public class BinaryTreePrep
{
    public static int maxDepth(Node root)
    {
        if(root==null)
            return 0;
        int ldepth=maxDepth(root.left)+1;
        int rdepth=maxDepth(root.right)+1;
        return ldepth>rdepth?ldepth:rdepth;
    }
    public static int minBST(Node root)
    {
        while(root.left!=null)
            root=root.left;
        return root.data;
    }
    public static int minBT(Node root)
    {
        if(root==null)
            return Integer.MAX_VALUE;
        int res=root.data;
        int lmin=minBT(root.left);
        int rmin=minBT(root.right);
        if(lmin<rmin && lmin<res)
            return lmin;
        else if(rmin<lmin && rmin<res)
            return rmin;
        else
            return res;
    }
    public static int maxBT(Node root)
    {
        if(root==null)
            return Integer.MIN_VALUE;
        int res=root.data;
        int rmax=maxBT(root.right);
        int lmax=maxBT(root.left);
        if(rmax>lmax && rmax>res)
            return rmax;
        else if(lmax>rmax && lmax>res)
            return lmax;
        else
            return res;
    }
    public static void preorder(Node root)
    {
        if(root==null)
            return;
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
    public static void inorder(Node root)
    {
        if(root==null)
            return;
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    public static void postorder(Node root)
    {
        if(root==null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data+" ");
    }
    public static boolean hasPathSum(Node root, int sum)
    {
        if(root==null)
            return sum==0;
        else
        {
            int subsum=sum-root.data;
            return hasPathSum(root.right,subsum)||hasPathSum(root.left,subsum);
        }
    }
    public static void pathPrinter(Node root)
    {
        ArrayList<Integer> path=new ArrayList<Integer>();
        printPaths(root,path);

    }
    public static void printPaths(Node root, ArrayList<Integer> path)
    {
        if(root==null)
            return;
        path.add(root.data);
        if(root.left==null && root.right==null)
        {
            System.out.println(path);
            path.remove(path.size()-1);
        }
        printPaths(root.left,path);
        printPaths(root.right,path);

    }
    public static void swap(Node root)
    {
        if(root==null)
            return;
        Node temp=root;
        swap(root.left);
        swap(root.right);
        temp=root.left;
        root.left=root.right;
        root.right=temp;
    }
    public static void doubleTree(Node root)
    {
        if(root==null)
            return;
        Node temp;
        doubleTree(root.left);
        doubleTree(root.right);
        temp=root.left;
        root.left=new Node(root.data);
        root.left.left=temp;
    }
    public static boolean sameTree(Node root1, Node root2)
    {
        if(root1==null && root2==null)
            return true;
        else if(root1!=null && root2!=null)
        {
            return (root1==root2 && sameTree(root1.right,root2.right) && sameTree(root1.left,root2.left));
        }
        else
            return false;
        
    }
    public static boolean isBST1(Node root)
    {
        if(root==null)
            return true;
        if(root.left!=null && root.data<minBT(root.left))
            return false;
        if(root.right!=null && root.data>maxBT(root.right))
            return false;
        if(!isBST1(root.left) || !isBST1(root.right))
            return false;
        return true;
    }
    public static boolean isBST2(Node root)
    {
        return isBSTUtil(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public static boolean isBSTUtil(Node root,int min,int max)
    {
        if(root==null)
            return true;
        if(root.data<min || root.data>max)
            return false;
        return isBSTUtil(root.left,min,root.data) && isBSTUtil(root.right,root.data+1,max);
    }
    public static void main(String[] args)
    {
        Node root=new Node(5);
        root.left=new Node(2);
        root.left.left=new Node (1);
        root.left.right=new Node(3);
        root.right=new Node(7);
        root.right.right=new Node (10);
        root.right.left=new Node (6);
        System.out.println("The depth is : "+maxDepth(root));
        System.out.println("The min is : "+minBT(root));
        System.out.println("The max is : "+maxBT(root));
        preorder(root);
        System.out.println();
        inorder(root);
        System.out.println();
        postorder(root);
        System.out.println();
        if(hasPathSum(root,10))
            System.out.println("Test123");
        System.out.println();
        //swap(root);
        //doubleTree(root);
        pathPrinter(root);
        if(isBST2(root))
            System.out.println("Success");
        else
            System.out.println("Fail");
    }
}
