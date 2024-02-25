public class ZigZagConversion
{
    /*
        The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

        P   A   H   N
        A P L S I I G
        Y   I   R
        And then read line by line: "PAHNAPLSIIGYIR"

        Write the code that will take a string and make this conversion given a number of rows:

        string convert(string s, int numRows);


        Example 1:

        Input: s = "PAYPALISHIRING", numRows = 3
        Output: "PAHNAPLSIIGYIR"
        Example 2:

        Input: s = "PAYPALISHIRING", numRows = 4
        Output: "PINALSIGYAHRPI"
        Explanation:
        P     I    N
        A   L S  I G
        Y A   H R
        P     I
        Example 3:

        Input: s = "A", numRows = 1
        Output: "A"
     */
    public String convert(String s, int numRows)
    {
        if(numRows==1)
            return s;
        Character[][] arr=new Character[numRows][s.length()];
        int i=0, j=0, k=0;
        boolean flag=false;
        while(k<s.length())
        {
            char c=s.charAt(k);
            arr[i][j]=c;
            if(i==numRows-1)
                flag=true;
            if(i==0)
                flag=false;
            if(flag)
            {
                i--;
                j++;
            }
            else
                i++;
            k++;
        }
        StringBuilder sb=new StringBuilder();
        for(i=0;i<arr.length;i++)
        {
            for(j=0;j<arr[0].length;j++)
            {
                if(arr[i][j]==null)
                    continue;
                sb.append(arr[i][j]);
            }
        }
        return sb.toString();
    }
}
