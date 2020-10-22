import java.util.*;

public class NumberOfAtoms
{
    /*
    Given a chemical formula (given as a string), return the count of each atom.

    An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

    1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

    Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

    A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

    Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

    Example 1:
    Input:
    formula = "H2O"
    Output: "H2O"
    Explanation:
    The count of elements are {'H': 2, 'O': 1}.
    Example 2:
    Input:
    formula = "Mg(OH)2"
    Output: "H2MgO2"
    Explanation:
    The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
    Example 3:
    Input:
    formula = "K4(ON(SO3)2)2"
    Output: "K4N2O14S4"
    Explanation:
    The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
    Note:

    All atom names consist of lowercase letters, except for the first character which is uppercase.
    The length of formula will be in the range [1, 1000].
    formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
     */


    public String countOfAtoms(String formula)
    {
        if(formula==null || formula.length()==0)
            return "";
        Map<String,Integer> map=helper(formula,1);

        PriorityQueue<String> pq=new PriorityQueue<>();
        pq.addAll(map.keySet());
        StringBuilder result=new StringBuilder();
        while(!pq.isEmpty())
        {
            String curr=pq.poll();
            result.append(curr);
            if(map.get(curr)>1)
                result.append(map.get(curr));
        }
        return result.toString();
    }

    public Map<String,Integer> helper(String s, int mult)
    {
        if(s==null || s.length()==0)
            return new HashMap<>();
        Map<String, Integer> map=new HashMap<>();
        int i=0;
        while(i<s.length())
        {
            if(s.charAt(i)=='(')
            {
                int j=i,count=0;
                for(;j<s.length();j++)
                {
                    if(s.charAt(j)=='(')
                        count++;
                    else if(s.charAt(j)==')')
                        count--;
                    if(count==0)
                        break;
                }
                String temp=s.substring(i+1,j);
                j++;
                i=j;
                int m=1;
                while(j<s.length() && Character.isDigit(s.charAt(j)))
                    j++;
                if(j<=s.length())
                    m=Integer.parseInt(s.substring(i,j));
                Map<String,Integer> subMap=helper(temp,m);
                for(String key:subMap.keySet())
                    map.put(key,map.getOrDefault(key,0)+subMap.get(key));
                i=j;
            }
            else if(Character.isUpperCase(s.charAt(i)))
            {
                int j=i;
                while(j+1<s.length() && !Character.isUpperCase(s.charAt(j+1)) && Character.isLetter(s.charAt(j+1)))
                    j++;
                String elem=s.substring(i,j+1);
                i=j+1;
                if(i>=s.length() || !Character.isDigit(s.charAt(i)))
                    map.put(elem,map.getOrDefault(elem,0)+1);
                else
                {
                    j=i;
                    while(j<s.length() && Character.isDigit(s.charAt(j)))
                        j++;
                    String mul_val=s.substring(i,j);
                    int val=Integer.parseInt(s.substring(i,j));
                    map.put(elem,map.getOrDefault(elem,0)+val);
                    i=j;
                }
            }
        }
        for(String key:map.keySet())
            map.put(key,map.get(key)*mult);
        return map;
    }


    public static void main(String[] args)
    {
        NumberOfAtoms obj=new NumberOfAtoms();
        System.out.println(obj.countOfAtoms("K4(ON(SO3)2)2"));
    }
}
