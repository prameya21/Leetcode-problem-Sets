import java.util.*;

public class SearchSuggestionnSystem
{
    /*
    Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed.
    Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

    Return list of lists of the suggested products after each character of searchWord is typed.



    Example 1:

    Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
    Output: [
    ["mobile","moneypot","monitor"],
    ["mobile","moneypot","monitor"],
    ["mouse","mousepad"],
    ["mouse","mousepad"],
    ["mouse","mousepad"]
    ]
    Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
    After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
    After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
    Example 2:

    Input: products = ["havana"], searchWord = "havana"
    Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
    Example 3:

    Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
    Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
    Example 4:

    Input: products = ["havana"], searchWord = "tatiana"
    Output: [[],[],[],[],[],[],[]]


    Constraints:

    1 <= products.length <= 1000
    There are no repeated elements in products.
    1 <= Σ products[i].length <= 2 * 10^4
    All characters of products[i] are lower-case English letters.
    1 <= searchWord.length <= 1000
    All characters of searchWord are lower-case English letters.
     */


    private class Trie
    {
        Trie[] next=new Trie[26];
        LinkedList<String> suggestions=new LinkedList<>();
    }

    public void add(Trie root, String s)
    {
        for(char c:s.toCharArray())
        {
            if(root.next[c-'a']==null)
                root.next[c-'a']=new Trie();
            root=root.next[c-'a'];
            root.suggestions.add(s);
            if(root.suggestions.size()>3)
                root.suggestions.pollLast();
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord)
    {
        Trie root=new Trie();
        Arrays.sort(products);
        for(String p:products)
            add(root,p);

        List<List<String>> res=new ArrayList<>();
        for(char c:searchWord.toCharArray())
        {
            if(root!=null)
                root=root.next[c-'a'];
            res.add(root==null?new ArrayList<>():root.suggestions);
        }
        return res;
    }














    public List<List<String>> suggestedProducts2(String[] products, String searchWord)
    {
        List<List<String>> res=new ArrayList<>();
        Arrays.sort(products);
        for(int i=1;i<=searchWord.length();i++)
        {
            String cur=searchWord.substring(0,i);
            int k=Arrays.binarySearch(products,cur);
            while(k>0 && products[k-1].equals(cur))
                k--;
            if(k<0)
                k=~k;
            List<String> suggestions=new ArrayList<>();
            for(int j=k+3;k<products.length && k<j && products[k].startsWith(cur);k++)
                suggestions.add(products[k]);
            res.add(suggestions);
        }
        return res;
    }


    public static void main(String[] args)
    {
        SearchSuggestionnSystem obj=new SearchSuggestionnSystem();
        System.out.println(obj.suggestedProducts2(new String[]{"bags","baggage","banner","box","cloths"},"bags"));
    }




}
