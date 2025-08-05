public class TakeKofEachCharsFromLeftAndRight
{
    /*
        2516
        You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.
        Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.

        Example 1:
        Input: s = "aabaaaacaabc", k = 2
        Output: 8
        Explanation:
        Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
        Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
        A total of 3 + 5 = 8 minutes is needed.
        It can be proven that 8 is the minimum number of minutes needed.

        Example 2:
        Input: s = "a", k = 1
        Output: -1
        Explanation: It is not possible to take one 'b' or 'c' so return -1.


        Constraints:

        1 <= s.length <= 105
        s consists of only the letters 'a', 'b', and 'c'.
        0 <= k <= s.length
     */


    public int takeCharacters(String s, int k)
    {
        if(s.length()<3 & k>0)
            return -1;
        if(k==0)
            return 0;
        int[] map=new int[3];
        for(char c:s.toCharArray())
            map[c-'a']++;
        for(int i:map)
            if(i<k)
                return -1;

        char[] ch=s.toCharArray();
        int l=0, r=0;
        int res=0;
        while(r<ch.length)
        {
            map[ch[r]-'a']--;
            r++;
            while(l<r && !isValid(map,k))
            {
                map[ch[l]-'a']++;
                l++;
            }
            res=Math.max(r-l, res);
        }
        return s.length()-res;

    }
    public boolean isValid(int[] map, int k)
    {
        for(int i:map)
            if(i<k)
                return false;
        return true;
    }


    public static void main(String[] args)
    {
        TakeKofEachCharsFromLeftAndRight obj=new TakeKofEachCharsFromLeftAndRight();
        System.out.println(obj.takeCharacters("aabaaaacaabc", 2));
        System.out.println(obj.takeCharacters("abc", 0));
        System.out.println(obj.takeCharacters("abcabcabc", 2));
        System.out.println(obj.takeCharacters("aaaaaaabccbaa", 1));
    }
}
