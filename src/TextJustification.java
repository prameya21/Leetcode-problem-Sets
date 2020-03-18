import java.util.ArrayList;
import java.util.List;

public class TextJustification
{
    /*
    Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

    You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

    Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

    For the last line of text, it should be left justified and no extra space is inserted between words.

    Note:

    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.
    Example 1:

    Input:
    words = ["This", "is", "an", "example", "of", "text", "justification."]
    maxWidth = 16
    Output:
    [
       "This    is    an",
       "example  of text",
       "justification.  "
    ]
    Example 2:

    Input:
    words = ["What","must","be","acknowledgment","shall","be"]
    maxWidth = 16
    Output:
    [
      "What   must   be",
      "acknowledgment  ",
      "shall be        "
    ]
    Explanation: Note that the last line is "shall be    " instead of "shall     be",
                 because the last line must be left-justified instead of fully-justified.
                 Note that the second line is also left-justified becase it contains only one word.
    Example 3:

    Input:
    words = ["Science","is","what","we","understand","well","enough","to","explain",
             "to","a","computer.","Art","is","everything","else","we","do"]
    maxWidth = 20
    Output:
    [
      "Science  is  what we",
      "understand      well",
      "enough to explain to",
      "a  computer.  Art is",
      "everything  else  we",
      "do                  "
    ]
     */

    public List<String> fullJustify(String[] words, int maxWidth)
    {
        int left=0;
        List<String> result=new ArrayList<>();
        while(left<words.length)
        {
            int right=findRight(words,left,maxWidth);
            String str=justify(left,right,words,maxWidth);
            result.add(str);
            left=right+1;
        }
        return result;
    }
    public int findRight(String[] words,int left, int maxWidth)
    {
        int right=left;
        int sum=words[right++].length();
        while(right<words.length && sum+1+words[right].length()<=maxWidth)
        {
            sum+=1+words[right++].length();
        }
        return right-1;
    }
    public String justify(int left, int right, String[] words, int maxWidth)
    {
        if(right-left==0)
            return words[left]+blank(maxWidth-words[left].length());
        boolean isLastLine=right==words.length-1;
        int numSpaces=right-left;
        int totalSpaces=maxWidth-wordsLength(left,right,words);

        String blankSpace=isLastLine?" ":blank(totalSpaces/numSpaces);
        int remainder=isLastLine?0:totalSpaces%numSpaces;

        StringBuilder sb=new StringBuilder();
        for(int i=left;i<=right;i++)
        {
            sb.append(words[i]);
            sb.append(blankSpace);
            sb.append(remainder-->0?" ":"");
        }
        return sb.toString().trim()+blank(maxWidth-sb.toString().trim().length());

    }
    public int wordsLength(int left, int right, String[] words)
    {
        int totalLength=0;
        for(int i=left;i<=right;i++)
        {
            totalLength+=words[i].length();
        }
        return totalLength;
    }
    public String blank(int len)
    {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<len;i++)
            sb.append(" ");
        return sb.toString();
    }



    public static void main(String[] args)
    {
        TextJustification obj=new TextJustification();
        System.out.println(obj.fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"},16));
    }
}
