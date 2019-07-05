import java.util.*;

public class AmazonOnSitePrep
{
    /*
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:

    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
     */
    public int[] twoSum(int[] nums,int target)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            int comp=target-nums[i];
            if(map.containsKey(comp))
            {
                return new int[]{map.get(comp),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{0,0};
    }
    /*
    Longest Substring Without repeating characters
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
    public int lengthOfLongestSubstring(String str)
    {
        Set<Character> set=new HashSet<>();
        int i=0,j=0;
        int ans=0;
        while(i<str.length() && j<str.length())
        {
            if(!set.contains(str.charAt(j)))
            {
                set.add(str.charAt(j));
                j++;
                ans=Math.max(ans,j-i);
            }
            else
            {
                set.remove(str.charAt(i));
                i++;
            }
        }
        return ans;
    }
    /*
    String to Integer
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
    public int myAtoi(String str)
    {
        if(str==null || str.length()==0)
            return 0;
        str=str.trim();
        if(str.equals(""))
            return 0;
        int sign=1,start=0;
        long num=0;
        if(str.charAt(0)=='+')
        {
            sign=1;
            start++;
        }
        else if(str.charAt(0)=='-')
        {
            sign=-1;
            start++;
        }
        for(int i=start;i<str.length();i++)
        {
            char c=str.charAt(i);
            if(Character.isDigit(c))
            {
                num=num*10+(c-'0');
                if(sign==1 && num>Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                if(sign==-1 && num*sign<Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;
            }
            else
                return (int)(sign*num);
        }
        return (int)num*sign;
    }
    /*
    Container with  most water
    Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

    Note: You may not slant the container and n is at least 2.
    The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
    Example:

    Input: [1,8,6,2,5,4,8,3,7]
    Output: 49
     */
    public int maxArea(int[] height)
    {
        int ans=0,l=0,r=height.length-1;
        while(l<r)
        {
            ans=Math.max(Math.min(height[l],height[r])*(r-l),ans);
            if(height[l]<height[r])
                l++;
            else
                r--;
        }
        return ans;
    }
    /*
    Rotate a matrix
    You are given an n x n 2D matrix representing an image.

    Rotate the image by 90 degrees (clockwise).

    Note:

    You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

    Example 1:

    Given input matrix =
    [
      [1,2,3],
      [4,5,6],
      [7,8,9]
    ],

    rotate the input matrix in-place such that it becomes:
    [
      [7,4,1],
      [8,5,2],
      [9,6,3]
    ]
    Example 2:

    Given input matrix =
    [
      [ 5, 1, 9,11],
      [ 2, 4, 8,10],
      [13, 3, 6, 7],
      [15,14,12,16]
    ],

    rotate the input matrix in-place such that it becomes:
    [
      [15,13, 2, 5],
      [14, 3, 4, 1],
      [12, 6, 8, 9],
      [16, 7,10,11]
    ]
     */
    public void rotate(int[][] nums)
    {
        if(nums==null || nums.length==0)
            return;
        for(int i=0;i<nums.length/2;i++)
        {
            int first=i;
            int last=nums.length-first-1;
            for(int j=0;j<last;j++)
            {
                int offset=j-first;
                int second=last-offset;
                int temp=nums[first][j];
                nums[first][j]=nums[second][first];
                nums[second][first]=nums[last][second];
                nums[last][second]=nums[j][last];
                nums[j][last]=temp;
            }
        }
    }
    /*
    Minimum Window Substring
    Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

    Example:

    Input: S = "ADOBECODEBANC", T = "ABC"
    Output: "BANC"
    Note:

    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
     */
    public String minWindow(String s, String t)
    {
        Map<Character,Integer> t_freq=new HashMap<>();
        for(char c: t.toCharArray())
            t_freq.put(c,t_freq.getOrDefault(c,0)+1);
        int maxLen=Integer.MAX_VALUE,cnt=0,i=0,j=0;
        String ans="";
        Map<Character,Integer> window=new HashMap<>();
        while(j<s.length())
        {
            char c=s.charAt(j);
            window.put(c,window.getOrDefault(c,0)+1);
            if(t_freq.containsKey(c) && t_freq.get(c)==window.get(c))
                cnt++;
            while(cnt==t_freq.size())
            {
                String str=s.substring(i,j+1);
                if(maxLen>str.length())
                {
                    maxLen=str.length();
                    ans=str;
                }
                char ch=s.charAt(i);
                window.put(ch,window.get(ch)-1);
                if(t_freq.containsKey(ch) && window.get(ch)<t_freq.get(ch))
                    cnt--;
                i++;
            }
            j++;
        }
        return ans;
    }
    /*
        Compare two version numbers version1 and version2.
    If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

    You may assume that the version strings are non-empty and contain only digits and the . character.

    The . character does not represent a decimal point and is used to separate number sequences.

    For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

    You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.



    Example 1:

    Input: version1 = "0.1", version2 = "1.1"
    Output: -1
    Example 2:

    Input: version1 = "1.0.1", version2 = "1"
    Output: 1
    Example 3:

    Input: version1 = "7.5.2.4", version2 = "7.5.3"
    Output: -1
    Example 4:

    Input: version1 = "1.01", version2 = "1.001"
    Output: 0
    Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
    Example 5:

    Input: version1 = "1.0", version2 = "1.0.0"
    Output: 0
    Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"
     */
    public int compareVersions(String v1, String v2)
    {
        String[] ver1=v1.split("\\.");
        String[] ver2=v2.split("\\.");
        for(int i=0;i<Math.max(ver1.length,ver2.length);i++)
        {
            Integer i1=i>ver1.length?0:Integer.parseInt(ver1[i]);
            Integer i2=i>ver2.length?0:Integer.parseInt(ver2[i]);
            int cmp=i1.compareTo(i2);
            if(cmp!=0)
                return cmp;
        }
        return 0;
    }
    /*
    Product of array except self
    Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

    Example:

    Input:  [1,2,3,4]
    Output: [24,12,8,6]
    Note: Please solve it without division and in O(n).

    Follow up:
    Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
     */
    public int[] productExceptSelf(int[] nums)
    {
        int[] res=new int[nums.length];
        int left=1;
        for(int i=0;i<nums.length;i++)
        {
            res[i]=left;
            left=left*nums[i];
        }
        int right=1;
        for(int i=nums.length-1;i>=0;i--)
        {
            res[i]=res[i]*right;
            right=right*nums[i];
        }
        return res;
    }
    /*
    Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

    Example 1:

    Input: 123
    Output: "One Hundred Twenty Three"
    Example 2:

    Input: 12345
    Output: "Twelve Thousand Three Hundred Forty Five"
    Example 3:

    Input: 1234567
    Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
    Example 4:

    Input: 1234567891
    Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
     */
    String[] belowTen=new String[]{"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
    String[] belowTwenty=new String[]{"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    String[] belowHundred=new String[]{"","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    public String numberToWords(int num)
    {
        if(num==0)
            return "Zero";
        return convert(num);
    }
    public String convert(int num)
    {
        if(num<10)
            return belowTen[num];
        else if(num<20)
            return belowTwenty[num-10];
        else if(num<100)
            return belowHundred[num/10]+" "+belowTen[num%10];
        else if(num<1000)
            return convert(num/100)+" Hundred "+convert(num%100);
        else if(num<1000000)
            return convert(num/1000)+" Thousand "+convert(num%1000);
        else if(num<1000000000)
            return convert(num/1000000)+" Million "+convert(num%1000000);
        else
            return convert(num/1000000000)+" Billion "+convert(num%1000000000);
    }
    /*
    Reorder Log Files
    You have an array of logs.  Each log is a space delimited string of words.

    For each log, the first word in each log is an alphanumeric identifier.  Then, either:

    Each word after the identifier will consist only of lowercase letters, or;
    Each word after the identifier will consist only of digits.
    We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

    Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

    Return the final order of the logs.
    Example 1:

    Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
    Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
    Note:

    0 <= logs.length <= 100
    3 <= logs[i].length <= 100
    logs[i] is guaranteed to have an identifier, and a word after the identifier.
     */
    public String[] reorderLogFiles(String[] logs)
    {
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2)
            {
                String[] l1=s1.split(" ",2);
                String[] l2=s2.split(" ",2);
                boolean isDigit1=Character.isDigit(l1[1].charAt(0));
                boolean isDigit2=Character.isDigit(l2[1].charAt(0));
                if(!isDigit1 && !isDigit2)
                {
                    int cmp=l1[1].compareTo(l2[1]);
                    if(cmp!=0)
                        return cmp;
                    else
                        return l1[0].compareTo(l2[0]);
                }
                return isDigit1?(isDigit2?0:1):-1;
            }
        });
        return logs;
    }
    /*
        Trapping Rain Water
    */
    public int trap(int[] heights)
    {
        int l=0,r=heights.length-1,area=0,lmax=0,rmax=0;
        while(l<r)
        {
            lmax=Math.max(lmax,heights[l]);
            rmax=Math.max(rmax,heights[r]);
            if(lmax<rmax)
            {
                area+=(lmax-heights[l]);
                l++;
            }
            else
            {
                area+=(rmax-heights[r]);
                r--;
            }
        }
        return area;
    }
    public static void main(String[] args)
    {
        AmazonOnSitePrep obj=new AmazonOnSitePrep();
        System.out.println(obj.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

    }
}
