import java.util.*;
public class ArrayPrep
{
    public boolean checkUnique(String str)
    {
        if(str.length()>128)
            return false;
        boolean[] unique=new boolean [128];
        for(int i=0;i<str.length();i++)
        {
            int val=str.charAt(i);
            if(unique[val])
                return false;
            else
                unique[val]=true;
        }
        return true;
    }

    public boolean isPermutation(String str1, String str2)
    {
        if(str1.length()!=str2.length())
            return false;
        int[] charCheck=new int[128];
        char[] s_array=str1.toCharArray();
        for(char c:s_array)
            charCheck[c]++;
        s_array=str2.toCharArray();
        for(char c:s_array)
            charCheck[c]--;
        for(int i=0;i<128;i++)
            if(charCheck[i]!=0)
                return false;
        return true;
    }
    public char[] urlify(char c[], int truelength)
    {
        int spacecount=0;
        for(int i=0;i<truelength;i++)
        {
            if(c[i]==' ')
                spacecount++;
        }
        int index=truelength+spacecount*2;
        //index--;
        c[index]='\0';
        index--;
        for(int i=truelength;i>=0;i--)
        {
            if(c[i]==' ')
            {
                c[index]='0';
                c[index-1]='2';
                c[index-2]='%';
                index=index-3;
            }
            else
                c[index]=c[i];
        }
        return c;
    }
    public boolean isPalindrome(String str)
    {
        int[] vals=new int[128];
        char[] c=str.toCharArray();
        int count=0;
        for(char ch:c)
            vals[ch]++;
        for(int i=0;i<128;i++)
            count+=vals[i]%2;
        return count<=1;
    }
    public boolean oneEditAway(String str1, String str2)
    {
        if(str1.length()==str2.length())
            return oneReplacementAway(str1,str2);
        else if(str1.length()+1==str2.length())
            return oneInsertAway(str1,str2);
        else if(str2.length()+1==str1.length())
            return oneInsertAway(str2,str1);
        else
            return false;
    }
    public boolean oneReplacementAway(String str1,String str2)
    {
        int mismatchCount=0;
        for(int i=0;i<str1.length();i++)
        {
            if(str1.charAt(i)==str2.charAt(i))
                continue;
            else
                mismatchCount++;
        }
        return mismatchCount==1;
    }
    public boolean oneInsertAway(String str1,String str2)
    {
        int charHist[]=new int[128];
        int count=0;
        char c[]=str1.toCharArray();
        for(char ch:c)
            charHist[ch]++;
        c=str2.toCharArray();
        for(char ch:c)
            charHist[ch]--;
        for(int i=0;i<128;i++)
        {
            if(charHist[i]!=0)
                count++;
        }
        return count==1;
    }
    public String stringCompress(String str)
    {
        StringBuilder sb=new StringBuilder();
        int countConsqeutive=0;
        for(int i=0;i<str.length();i++)
        {
            countConsqeutive++;
            if(i+1>=str.length() || str.charAt(i)!=str.charAt(i+1))
            {
                sb.append(str.charAt(i));
                sb.append(countConsqeutive);
                countConsqeutive=0;
            }
        }
        return sb.length()>str.length()?str:sb.toString();
    }

    public void rotate(int mat[][])
    {
        if(mat.length==0 || mat.length!=mat[0].length)
            return;
        int length=mat.length;
        for(int i=0;i<length/2;i++)
        {
            int first=i;
            int last=length-i-1;
            for(int j=first;j<last;j++)
            {
                int offset=j-first;
                int temp=mat[first][j];
                mat[first][j]=mat[last-offset][first];
                mat[last-offset][first]=mat[last][last-offset];
                mat[last][last-offset]=mat[j][last];
                mat[j][last]=temp;
            }
        }
    }
    public void makeZero(int mat[][])
    {
        boolean rowHasZero=false;
        boolean columnHasZero=false;
        for(int i=0;i<mat.length;i++)
            if(mat[i][0]==0)
                rowHasZero=true;
        for(int i=0;i<mat[0].length;i++)
            if(mat[0][i]==0)
                columnHasZero=true;
        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++)
            {
                if(mat[i][j]==0)
                {
                    mat[i][0]=0;
                    mat[0][j]=0;
                }
            }
        }
        for(int i=0;i<mat.length;i++)
        {
            if(mat[i][0]==0)
            {
                for(int j=0;j<mat[0].length;j++)
                    mat[i][j]=0;
            }
        }
        for(int i=0;i<mat[0].length;i++)
        {
            if(mat[0][i]==0)
            {
                for(int j=0;j<mat.length;j++)
                    mat[j][i]=0;
            }
        }
        if(rowHasZero)
            for(int i=0;i<mat.length;i++)
                mat[i][0]=0;
        if(columnHasZero)
            for(int i=0;i<mat[0].length;i++)
                mat[0][i]=0;
    }
    public static void setZeroes(int[][] matrix)
    {
        boolean isRowZero=false,isColumnZero=false;
        for(int i=0;i<matrix.length;i++)
            if(matrix[i][0]==0)
                isRowZero=true;

        for(int i=0;i<matrix[0].length;i++)
            if(matrix[0][i]==0)
                isColumnZero=true;

        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                if(matrix[i][j]==0)
                {
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[i][0]==0)
            {
                for(int j=0;j<matrix[0].length;j++)
                    matrix[i][j]=0;
            }
        }

        for(int i=0;i<matrix[0].length;i++)
        {
            if(matrix[0][i]==0)
            {
                for(int j=0;j<matrix.length;j++)
                    matrix[j][i]=0;
            }
        }

        if(isRowZero)
            for(int i=0;i<matrix.length;i++)
                matrix[i][0]=0;

        if(isColumnZero)
            for(int i=0;i<matrix[0].length;i++)
                matrix[0][i]=0;

    }
    public static boolean searchMatrix(int[][] mat, int t)
    {
        if(mat.length==0 || mat[0].length==0)
            return false;
        int m=mat.length,n=mat[0].length;
        int l=0,r=(m*n)-1;
        while(l!=r)
        {
            int mid=(l+r)/2;
            if(mat[mid/n][mid%n]<t)
                l=mid+1;
            else
                r=mid;
        }
        return mat[r/mat.length][r%mat.length]==t;
    }
    static int c=0;
    public static void reverseString(char[] s)
    {
        helper(s,0);
    }
    public static void helper(char[] s,int index)
    {
        if(index>=s.length)
            return;
        helper(s,index+1);
        char temp=s[c];
        s[c]=s[index];
        s[index]=temp;
        c++;
    }
    public static void main(String[] args)
    {
        setZeroes(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});
        //searchMatrix(new int[][]{{1,1},{}},2);
        String s="hello";
        reverseString(s.toCharArray());

    }
}
