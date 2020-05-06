public class ReorganizeString
{
    /*
    Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

    If possible, output any possible result.  If not possible, return the empty string.

    Example 1:

    Input: S = "aab"
    Output: "aba"
    Example 2:

    Input: S = "aaab"
    Output: ""
     */

    public String reorganizeString(String S)
    {
        if(S==null || S.length()==0)
            return "";
        int[] map=new int[26];
        for(char c:S.toCharArray())
            map[c-'a']++;
        int letter=0,max=0;
        for(int i=0;i<26;i++)
        {
            if(max<map[i])
            {
                max=map[i];
                letter=i;
            }
        }
        if(max>(S.length()+1)/2)
            return "";
        int idx=0;
        char[] ch=new char[S.length()];
        while(map[letter]>0)
        {
            ch[idx]=(char)(letter+'a');
            idx+=2;
            map[letter]--;
        }

        for(int i=0;i<map.length;i++)
        {
            while(map[i]>0)
            {
                if(idx>=S.length())
                    idx=1;
                ch[idx]=(char)(i+'a');
                map[i]--;
                idx+=2;
            }
        }
        return new String(ch);
    }

    public static void main(String[] args)
    {
        ReorganizeString obj=new ReorganizeString();
        System.out.println(obj.reorganizeString("vvvlo"));
    }
}
