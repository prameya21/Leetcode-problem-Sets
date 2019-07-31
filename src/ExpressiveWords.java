public class ExpressiveWords
{
    /*
    Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

    For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation:
    choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

    For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.
    Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

    Given a list of query words, return the number of words that are stretchy.



    Example:
    Input:
    S = "heeellooo"
    words = ["hello", "hi", "helo"]
    Output: 1
    Explanation:
    We can extend "e" and "o" in the word "hello" to get "heeellooo".
    We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.


    Notes:

    0 <= len(S) <= 100.
    0 <= len(words) <= 100.
    0 <= len(words[i]) <= 100.
    S and all words in words consist only of lowercase letters
     */
    public int expressiveWords(String S, String[] words)
    {
        int ans=0;
        for(String str:words)
        {
            if(check(S,str))
                ans++;
        }
        return ans;
    }
    public boolean check(String S, String str)
    {
        int i=0,j=0;
        while(i<S.length())
        {
            if(j<str.length() && S.charAt(i)==str.charAt(j))
                j++;
            else if(i>0 && S.charAt(i)==S.charAt(i-1) && i+1<S.length() && S.charAt(i)==S.charAt(i+1))
                i++;
            else if(!(i>1 && S.charAt(i)==S.charAt(i-1) && S.charAt(i-2)==S.charAt(i)))
                break;
            i++;
        }
        return i==S.length() && j==str.length();
    }
    public static void main(String[] args)
    {
        ExpressiveWords obj=new ExpressiveWords();
        System.out.println(obj.expressiveWords("heeellooo",new String[]{"hello", "hi", "helo"}));
    }
}
