import java.util.*;

public class PalindromePairs
{
    /*
    Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

    Example 1:

    Input: ["abcd","dcba","lls","s","sssll"]
    Output: [[0,1],[1,0],[3,2],[2,4]]
    Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
    Example 2:

    Input: ["bat","tab","cat"]
    Output: [[0,1],[1,0]]
    Explanation: The palindromes are ["battab","tabbat"]
     */
    private class TrieNode
    {
        TrieNode[] next;
        int index;
        List<Integer> list;
        public TrieNode()
        {
            next=new TrieNode[26];
            index=-1;
            list=new ArrayList<>();
        }
    }
    public String reverseStr(String str){
        StringBuilder sb= new StringBuilder(str);
        return sb.reverse().toString();
    }

    public boolean isPalindrome(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i <= j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public List<List<Integer>> palindromePairs3(String[] words)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(words == null || words.length == 0){
            return res;
        }
        //build the map save the key-val pairs: String - idx
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }

        //special cases: "" can be combine with any palindrome string
        if(map.containsKey("")){
            int blankIdx = map.get("");
            for(int i = 0; i < words.length; i++){
                if(isPalindrome(words[i])){
                    if(i == blankIdx) continue;
                    res.add(Arrays.asList(blankIdx, i));
                    res.add(Arrays.asList(i, blankIdx));
                }
            }
        }

        //find all string and reverse string pairs
        for(int i = 0; i < words.length; i++){
            String cur_r = reverseStr(words[i]);
            if(map.containsKey(cur_r)){
                int found = map.get(cur_r);
                if(found == i) continue;
                res.add(Arrays.asList(i, found));
            }
        }

        //find the pair s1, s2 that
        //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
        //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
        for(int i = 0; i < words.length; i++){
            String cur = words[i];
            for(int cut = 1; cut < cur.length(); cut++){
                if(isPalindrome(cur.substring(0, cut))){
                    String cut_r = reverseStr(cur.substring(cut));
                    if(map.containsKey(cut_r)){
                        int found = map.get(cut_r);
                        if(found == i) continue;
                        res.add(Arrays.asList(found, i));
                    }
                }
                if(isPalindrome(cur.substring(cut))){
                    String cut_r = reverseStr(cur.substring(0, cut));
                    if(map.containsKey(cut_r)){
                        int found = map.get(cut_r);
                        if(found == i) continue;
                        res.add(Arrays.asList(i, found));
                    }
                }
            }
        }

        return res;
    }
    public List<List<Integer>> palindromePairs2(String[] words)
    {
        if(words==null || words.length==0)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<words.length;i++)
            map.put(words[i],i);


        //Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.
        if(map.containsKey(""))
        {
            int blankidx=map.get("");
            for(int i=0;i<words.length;i++)
            {
                if(isPalindrom(words[i],0,words[i].length()-1))
                {
                    if(i==blankidx)
                        continue;
                    res.add(Arrays.asList(i,blankidx));
                    res.add(Arrays.asList(blankidx,i));
                }
            }
        }


        //Case 2: If s2 is the reverse of s1, then s1+s2 and s2+s1 are palindrome.
        for(int i=0;i<words.length;i++)
        {
            String rev=reverse(words[i]);
            if(map.containsKey(rev) && map.get(rev)!=i)
            {
                int id=map.get(rev);
                res.add(Arrays.asList(i,id));
            }
        }


        //Case 3: If s1[0:cut] is palindrome and there exists s2 as the reverse of s1[cut+1:] , then s2+s1 is palindrome
        //Case 4: Similarly, If s1[cut+1: ] is palindrome and there exists s2 as the reverse of s1[0:cut] , then s1+s2 is palindrome.
        for(int i=0;i<words.length;i++)
        {
            String curr=words[i];
            for(int cut=1;cut<curr.length();cut++)
            {
                if(isPalindrom(curr,0,cut-1))
                {
                    String rev=reverse(curr.substring(cut));
                    if(map.containsKey(rev))
                    {
                        int foundidx=map.get(rev);
                        if(foundidx==i)
                            continue;
                        res.add(Arrays.asList(foundidx,i));
                    }
                }
                if(isPalindrom(curr,cut+1,curr.length()-1))
                {
                    String rev=reverse(curr.substring(0,cut));
                    if(map.containsKey(rev))
                    {
                        int foundidx=map.get(rev);
                        if(foundidx==i)
                            continue;
                        res.add(Arrays.asList(foundidx,i));
                    }
                }
            }
        }


        return res;


    }
    public String reverse(String s)
    {
        StringBuilder sb=new StringBuilder(s);
        return sb.reverse().toString();
    }
    public List<List<Integer>> palindromePairs(String[] words)
    {
        List<List<Integer>> result=new ArrayList<>();
        TrieNode root=new TrieNode();
        for(int i=0;i<words.length;i++)
            addWords(root,words[i],i);
        for(int i=0;i<words.length;i++)
            search(words,i,root,result);
        return result;
    }
    public void addWords(TrieNode root,String s,int index)
    {
        for(int i=s.length()-1;i>=0;i--)
        {
            char c=s.charAt(i);
            if(root.next[c-'a']==null)
                root.next[c-'a']=new TrieNode();
            if(isPalindrom(s,0,i))
                root.list.add(index);
            root=root.next[c-'a'];
        }
        root.list.add(index);
        root.index=index;
    }
    public void search(String[] words, int i,TrieNode root,List<List<Integer>> res)
    {
        for(int j=0;j<words[i].length();j++)
        {
            if(root.index>=0 && root.index!=i && isPalindrom(words[i],j,words[i].length()-1))
                res.add(Arrays.asList(i,root.index));
            root=root.next[words[i].charAt(j)-'a'];
            if(root==null)
                return;
        }
        for(int j:root.list)
        {
            if(j==i)
                continue;
            res.add(Arrays.asList(i,j));
        }
    }
    public boolean isPalindrom(String s,int i,int j)
    {
        while(i<j)
        {
            if(s.charAt(i++)!=s.charAt(j--))
                return false;
        }
        return true;
    }
    public static void main(String[] args)
    {
        PalindromePairs obj=new PalindromePairs();
        System.out.println(obj.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
        System.out.println(obj.palindromePairs2(new String[]{"abcd","dcba","lls","s","sssll"}));
        System.out.println(obj.palindromePairs3(new String[]{"abcd","dcba","lls","s","sssll"}));
    }
}
