
public class ArrayPrep
{
    public boolean checkUnique(String str)
    {
        if(str.length()>128)
            return true;
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

}
