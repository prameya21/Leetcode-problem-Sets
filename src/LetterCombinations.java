import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations
{
    /*
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



    Example:

    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    Note:

    Although the above answer is in lexicographical order, your answer could be in any order you want.
     */
    public static List<String> letterCombinations(String digits)
    {
        if(digits==""|| digits.length()==0)
            return new ArrayList<>();
        Map<Character,String> keypad=new HashMap<>();
        keypad.put('0'," ");
        keypad.put('1',"");
        keypad.put('2',"abc");
        keypad.put('3',"def");
        keypad.put('4',"ghi");
        keypad.put('5',"jkl");
        keypad.put('6',"mno");
        keypad.put('7',"pqrs");
        keypad.put('8',"tuv");
        keypad.put('9',"wxyz");
        List<String> result=new ArrayList<>();
        compute(digits,"",keypad,result,0);
        return result;
    }
    public static void compute(String digits,String prefix,Map<Character,String> keypad,List<String> result,int offset)
    {
        if(prefix.length()>digits.length())
            return;
        if(prefix.length()==digits.length())
        {
            result.add(prefix);
            return;
        }
        String val=keypad.get(digits.charAt(offset));
        for(char c: val.toCharArray())
            compute(digits,prefix+String.valueOf(c),keypad,result,offset+1);
    }
    public static void main(String[] args)
    {
        System.out.println(letterCombinations("23"));
    }
}
