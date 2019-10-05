import java.util.*;

public class FBLeetcode
{
    {
    /*
    Given a string, find the length of the longest substring without repeating characters.

    Example 1:

    Input: "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
    Example 2:

    Input: "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:

    Input: "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
                 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */
    }
    public int lengthOfLongestSubstring(String s)
    {
        Set<Character> set=new HashSet<>();
        int l=0,r=0,ans=0;
        while(r<s.length() && l<s.length())
        {
            if(!set.contains(s.charAt(r)))
            {
                set.add(s.charAt(r));
                ans=Math.max(r-l+1,ans);
                r++;
            }
            else
            {
                set.remove(s.charAt(l));
                l++;
            }
        }
        return ans;
    }

    {
    /*
    Implement atoi which converts a string to an integer.

    The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

    The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

    If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

    If no valid conversion could be performed, a zero value is returned.

    Note:

    Only the space character ' ' is considered as whitespace character.
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
    Example 1:

    Input: "42"
    Output: 42
    Example 2:

    Input: "   -42"
    Output: -42
    Explanation: The first non-whitespace character is '-', which is the minus sign.
                 Then take as many numerical digits as possible, which gets 42.
    Example 3:

    Input: "4193 with words"
    Output: 4193
    Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
    Example 4:

    Input: "words and 987"
    Output: 0
    Explanation: The first non-whitespace character is 'w', which is not a numerical
                 digit or a +/- sign. Therefore no valid conversion could be performed.
    Example 5:

    Input: "-91283472332"
    Output: -2147483648
    Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
                 Thefore INT_MIN (−231) is returned.
     */
    }
    public int myAtoi(String str)
    {
        if(str==null || str.length()==0)
            return 0;
        str=str.trim();
        if(str.equals(""))
            return 0;
        int st=0,sign=1;
        long num=0;
        if(str.charAt(st)=='+')
        {
            sign=1;
            st++;
        }
        if(str.charAt(st)=='-')
        {
            sign=-1;
            st++;
        }
        for(int i=st;i<str.length();i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {
                num=num*10+str.charAt(i)-'0';
                if(num>Integer.MAX_VALUE)
                {
                    return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
                }
            }
            else
                return (int)(sign*num);
        }
        return (int)(sign*num);
    }
    {
        /*
        Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

        Note:

        The solution set must not contain duplicate triplets.

        Example:

        Given array nums = [-1, 0, 1, 2, -1, -4],

        A solution set is:
        [
          [-1, 0, 1],
          [-1, -1, 2]
        ]
         */
    }
    public List<List<Integer>> threeSum(int[] nums)
    {
        Set<List<Integer>> res=new HashSet<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++)
        {
            int j=i+1,k=nums.length-1;
            while(j<k)
            {
                int val=nums[i]+nums[j]+nums[k];
                if(val==0)
                {
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                }
                else if(val>0)
                    k--;
                else
                    j++;
            }
        }
        return new ArrayList<>(res);
    }
    {
        /*
        Given an array of strings, group anagrams together.

        Example:

        Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
        Output:
        [
          ["ate","eat","tea"],
          ["nat","tan"],
          ["bat"]
        ]
        Note:

        All inputs will be in lowercase.
        The order of your output does not matter.
         */
    }
    public List<List<String>> groupAnagrams(String[] strs)
    {
        Map<String,List<String>> map=new HashMap<>();
        for(String s:strs)
        {
            char[] c=s.toCharArray();
            Arrays.sort(c);
            String t=new String(c);
            if(!map.containsKey(t))
                map.put(t,new ArrayList<>());
            map.get(t).add(s);
        }
        return new ArrayList<>(map.values());
    }
    {
        /*
        Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

        Example 1:

        Input: num1 = "2", num2 = "3"
        Output: "6"
        Example 2:

        Input: num1 = "123", num2 = "456"
        Output: "56088"
        Note:

        The length of both num1 and num2 is < 110.
        Both num1 and num2 contain only digits 0-9.
        Both num1 and num2 do not contain any leading zero, except the number 0 itself.
        You must not use any built-in BigInteger library or convert the inputs to integer directly.
         */
    }
    public String multiply(String num1, String num2)
    {
        int m=num1.length(),n=num2.length();
        int[] pos=new int[m+n];
        for(int i=m-1;i>=0;i--)
        {
            for(int j=n-1;j>=0;j--)
            {
                int mul=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                int p1=i+j,p2=i+j+1;
                int sum=mul+pos[p2];
                pos[p2]=sum%10;
                pos[p1]+=(sum/10);
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i:pos)
        {
            sb.append(!(sb.length()==0 && i==0)?i:"");
        }
        return sb.length()==0?"0":sb.toString();
    }
    {
        /*
            * Given two binary strings, return their sum (also a binary string).

    The input strings are both non-empty and contains only characters 1 or 0.

    Example 1:

    Input: a = "11", b = "1"
    Output: "100"
    Example 2:

    Input: a = "1010", b = "1011"
    Output: "10101"*/
    }
    public String addBinary(String a, String b)
    {
        int i=a.length()-1,j=b.length()-1;
        int carry=0;
        StringBuilder sb=new StringBuilder();
        while(i>=0 || j>=0)
        {
            int sum=carry;
            if(i>=0)
                sum+=a.charAt(i--)-'0';
            if(j>=0)
                sum+=b.charAt(j--)-'0';
            sb.append(sum%2);
            carry=sum/2;
        }
        if(carry!=0)
            sb.append(carry);
        return sb.reverse().toString();
    }
    {
        /*
        Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

        Example:

        Input: S = "ADOBECODEBANC", T = "ABC"
        Output: "BANC"
        Note:

        If there is no such window in S that covers all characters in T, return the empty string "".
        If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
         */
    }
    public String minWindow(String s, String t)
    {
        Map<Character,Integer> map_t=new HashMap<>();
        for(char c:t.toCharArray())
            map_t.put(c,map_t.getOrDefault(c,0)+1);
        int ctr=0,w=map_t.size();
        int l=0,r=0;
        String str="";
        int len=Integer.MAX_VALUE;
        Map<Character,Integer> map=new HashMap<>();
        while(r<s.length())
        {
            char c=s.charAt(r);
            map.put(c,map.getOrDefault(c,0)+1);
            if(map_t.containsKey(c) && map_t.get(c)==map.get(c))
                ctr++;
            while(ctr==w && l<=r)
            {
                String temp=s.substring(l,r+1);
                if(temp.length()<len)
                {
                    str=temp;
                    len=str.length();
                }
                map.put(s.charAt(l),map.get(s.charAt(l))-1);
                if(map_t.containsKey(s.charAt(l)) && map.get(s.charAt(l))<map_t.get(s.charAt(l)))
                    ctr--;
                l++;
            }
            r++;
        }
        return str;

    }
    public static void main(String[] args)
    {
        FBLeetcode obj=new FBLeetcode();
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
        System.out.println(obj.myAtoi("4193 with words"));
        System.out.println(obj.threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(obj.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(obj.multiply("123","42"));
        System.out.println(obj.addBinary("101","111"));
        System.out.print(obj.minWindow("ADOBECODEBANC","ABC"));
    }
}
