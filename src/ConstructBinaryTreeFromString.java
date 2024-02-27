public class ConstructBinaryTreeFromString
{
    /*
        You need to construct a binary tree from a string consisting of parenthesis and integers.
        The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
        You always start to construct the left child node of the parent first if it exists.

        Example 1:
        Input: s = "4(2(3)(1))(6(5))"
        Output: [4,2,6,3,1,5]

        Example 2:
        Input: s = "4(2(3)(1))(6(5)(7))"
        Output: [4,2,6,3,1,5,7]

        Example 3:
        Input: s = "-4(2(3)(1))(6(5)(7))"
        Output: [-4,2,6,3,1,5,7]


        Constraints:

        0 <= s.length <= 3 * 104
        s consists of digits, '(', ')', and '-' only.
     */

    public TreeNode str2tree(String s)
    {
        if(s==null || s.length()==0)
            return null;
        int fp=s.indexOf('(');
        int val=fp==-1?Integer.parseInt(s):Integer.parseInt(s.substring(0,fp));
        TreeNode root=new TreeNode(val);
        if(fp==-1)
            return root;
        int score=0, i=fp;
        for(;i<s.length();i++)
        {
            if(s.charAt(i)=='(')
                score++;
            else if(s.charAt(i)==')')
                score--;
            if(score==0)
                break;
        }
        root.left=str2tree(s.substring(fp+1,i));
        root.right=i+2<=s.length()-1?str2tree(s.substring(i+2,s.length()-1)):null;
        return root;
    }

    public static void main(String[] args)
    {
        ConstructBinaryTreeFromString obj=new ConstructBinaryTreeFromString();
        System.out.println(obj.str2tree("4(2(3)(1))(6(5))"));
        System.out.println(obj.str2tree("4(2(3)(1))(6(5)(7))"));
        System.out.println(obj.str2tree("-4(2(3)(1))(6(5)(7))"));
    }


}
