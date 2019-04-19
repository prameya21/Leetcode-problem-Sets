public class ReverseWordsInString2
{
    /*
    Given an input string , reverse the string word by word.

    Example:

    Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
    Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
    Note:

    A word is defined as a sequence of non-space characters.
    The input string does not contain leading or trailing spaces.
    The words are always separated by a single space.
    Follow up: Could you do it in-place without allocating extra space?
     */
    public static void reverseWords(char[] c)
    {
        reverse(c,0,c.length-1);
        for(int i=0;i<c.length;i++)
        {
            if(c[i]==' ')
                continue;
            else
            {
                int start=i;
                while(i+1<c.length && c[i+1]!=' ')
                    i++;
                reverse(c,start,i);
            }
        }
    }
    public static void reverse(char[] c, int s, int e)
    {
        while(s<=e)
        {
            char ch=c[s];
            c[s]=c[e];
            c[e]=ch;
            s++;
            e--;
        }
    }
    public static void main(String args[])
    {
        String str="the sky is blue";
        char[] c=str.toCharArray();
        reverseWords(c);
        System.out.println(c);
    }
}
