import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintBinaryTree
{
    /*
        Print a binary tree in an m*n 2D string array following these rules:

        The row number m should be equal to the height of the given binary tree.
        The column number n should always be an odd number.
        The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
        Each unused space should contain an empty string "".
        Print the subtrees following the same rules.
        Example 1:
        Input:
             1
            /
           2
        Output:
        [["", "1", ""],
         ["2", "", ""]]
        Example 2:
        Input:
             1
            / \
           2   3
            \
             4
        Output:
        [["", "", "", "1", "", "", ""],
         ["", "2", "", "", "", "3", ""],
         ["", "", "4", "", "", "", ""]]
        Example 3:
        Input:
              1
             / \
            2   5
           /
          3
         /
        4
        Output:

        [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
         ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
         ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
         ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
     */

    public List<List<String>> printTree(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        int height=getHeight(root);
        int width=(1<<height)-1;
        String[][] res=new String[height][width];
        for(String[] str:res)
            Arrays.fill(str,"");
        fill(res,root,0,0,width);
        List<List<String>> ans=new ArrayList<>();
        for(String[] str:res)
            ans.add(Arrays.asList(str));
        return ans;

    }

    public void fill(String[][] res,TreeNode root,int i,int l, int r)
    {
        if(root==null)
            return;
        int mid=(l+r)/2;
        res[i][mid]=""+root.val;
        fill(res,root.left,i+1,l,mid);
        fill(res,root.right,i+1,mid+1,r);
    }

    public int getHeight(TreeNode root)
    {
        if(root==null)
            return 0;
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }

    public static void main(String[] args)
    {
        TreeNode root=new TreeNode(1);
        root.right=new TreeNode(5);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(3);
        root.left.left.left=new TreeNode(4);
        PrintBinaryTree obj=new PrintBinaryTree();
        System.out.println(obj.printTree(root));

    }
//[[, , , , , , , 1, , , , , , , ], [, , , 2, , , , , , , , 5, , , ], [, 3, , , , , , , , , , , , , ], [4, , , , , , , , , , , , , , ]]
//[[, , , , , , , 1, , , , , , , ], [, , , 2, , , , , , , , 5, , , ], [, 3, , , , , , , , , , , , , ], [4, , , , , , , , , , , , , , ]]
}
