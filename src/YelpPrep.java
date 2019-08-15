import java.util.*;

public class YelpPrep
{
    /*
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:

    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
     */
    public int[] twoSum(int[] nums, int target)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            int comp=target-nums[i];
            if(map.containsKey(comp))
                return new int[]{i,map.get(comp)};
            else
                map.put(nums[i],i);
        }
        return new int[0];
    }
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
    public int lengthOfLongestSubstring(String s)
    {
        int res=0,i=0,j=0;
        Set<Character> hs=new HashSet<>();
        while(i<s.length() && j<s.length())
        {
            if(!hs.contains(s.charAt(j)))
            {
                hs.add(s.charAt(j));
                res=Math.max(res,j-i+1);
                j++;
            }
            else
            {
                hs.remove(s.charAt(i));
                i++;
            }
        }
        return res;
    }

    /*
    Write a function to find the longest common prefix string amongst an array of strings.

    If there is no common prefix, return an empty string "".

    Example 1:

    Input: ["flower","flow","flight"]
    Output: "fl"
    Example 2:

    Input: ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.
     */
    public String longestCommonPrefix(String[] strs)
    {
        if(strs==null || strs.length==0)
            return "";
        return lcp(strs,0,strs.length-1);
    }
    public String lcp(String[] strs, int start, int end)
    {
        if(start==end)
            return strs[start];
        int mid=(start+end)/2;
        String lcpRight=lcp(strs,start,mid);
        String lcpLeft=lcp(strs,mid+1,end);
        return commonPrefix(lcpLeft,lcpRight);
    }
    public String commonPrefix(String left,String right)
    {
        int min=Math.min(left.length(),right.length());
        for(int i=0;i<min;i++)
        {
            if(left.charAt(i)!=right.charAt(i))
                return left.substring(0,i);
        }
        return left.substring(0,min);
    }
    public String longestCommonPrefix2(String[] strs)
    {
        if(strs.length == 0) return "";
        String prefix = strs[0];
        int length = strs.length;
        for(int i = 0; i < length; ++i)
        {
            while(strs[i].indexOf(prefix) != 0)
            {
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";
            }
        }

        return prefix;
    }
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
    public List<List<Integer>> threeSum(int[] nums)
    {
        if(nums==null || nums.length==0)
            return new ArrayList<>();
        Set<List<Integer>> result=new HashSet<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
        {
            int j=i+1,k=nums.length-1;
            while(j<k)
            {
                int val=nums[i]+nums[j]+nums[k];
                if(val==0)
                    result.add(new ArrayList<>(Arrays.asList(nums[i],nums[j],nums[k])));
                if(val>0)
                    k--;
                else
                    j++;
            }
        }
        return new ArrayList<>(result);
    }
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
    public List<List<String>> groupAnagrams(String[] strs)
    {
        Map<String,List<String>> map=new HashMap<>();
        for(int i=0;i<strs.length;i++)
        {
            char[] ch=strs[i].toCharArray();
            Arrays.sort(ch);
            String key=String.valueOf(ch);
            if(!map.containsKey(key))
                map.put(key,new ArrayList<>());
            map.get(key).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }
    /*
    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

    Example 1:

    Input:
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
    Output: [1,2,3,6,9,8,7,4,5]
    Example 2:

    Input:
    [
      [1, 2, 3, 4],
      [5, 6, 7, 8],
      [9,10,11,12]
    ]
    Output: [1,2,3,4,8,12,11,10,9,5,6,7]
     */
    public List<Integer> spiralOrder(int[][] matrix)
    {
        if(matrix==null || matrix.length<0)
            return new ArrayList<>();
        int rowStart=0,rowEnd=matrix.length-1;
        int colStart=0,colEnd=matrix[0].length-1;
        List<Integer> res=new ArrayList<>();
        while(rowStart<=rowEnd && colStart<=colEnd)
        {
            for(int i=colStart;i<=colEnd;i++)
                res.add(matrix[rowStart][i]);
            rowStart++;
            for(int i=rowStart;i<=rowEnd;i++)
                res.add(matrix[i][colEnd]);
            colEnd--;
            if(rowStart<=rowEnd)
            {
                for(int i=colEnd;i>=colStart;i--)
                    res.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            if(colStart<=colEnd)
            {
                for(int i=rowEnd;i>=rowStart;i--)
                    res.add(matrix[i][colStart]);
            }
            colStart++;
        }
        return res;
    }
    /*
    Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

    Note:

    The number of elements initialized in nums1 and nums2 are m and n respectively.
    You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
    Example:

    Input:
    nums1 = [1,2,3,0,0,0], m = 3
    nums2 = [2,5,6],       n = 3

    Output: [1,2,2,3,5,6]
     */
    public void merge(int[] num1, int m, int[] num2, int n)
    {
        int l=m+n-1;
        m--;
        n--;
        while(m>=0 && n>=0)
        {
            if(num1[m]>num2[n])
                num1[l--]=num1[m--];
            else if(num2[n]>=num1[m])
                num1[l--]=num2[n--];

        }
        while(n>=0)
            num1[l--]=num2[n--];
    }
    /*
    Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


    In Pascal's triangle, each number is the sum of the two numbers directly above it.

    Example:

    Input: 5
    Output:
    [
         [1],
        [1,1],
       [1,2,1],
      [1,3,3,1],
     [1,4,6,4,1]
    ]
     */
    public List<List<Integer>> generate(int numRows)
    {
        if(numRows==0)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        res.add(0,new ArrayList<>());
        res.get(0).add(1);
        for(int i=1;i<numRows;i++)
        {
            for(int j=0;j<=i;j++)
            {
                populate(res,i,j);
            }
        }
        return res;
    }
    public void populate(List<List<Integer>> res, int i, int j)
    {
        if(j==0)
            res.add(i,new ArrayList<>(Arrays.asList(1)));
        else if(j==i)
            res.get(i).add(j,1);
        else
        {
            List<Integer> row=res.get(i);
            row.add(j,res.get(i-1).get(j)+res.get(i-1).get(j-1));
        }
    }
    /*
    Implement a basic calculator to evaluate a simple expression string.

    The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

    Example 1:

    Input: "3+2*2"
    Output: 7
    Example 2:

    Input: " 3/2 "
    Output: 1
    Example 3:

    Input: " 3+5 / 2 "
    Output: 5
    Note:

    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
     */
    public int calculate(String s)
    {
        Stack<Integer> st=new Stack<>();
        char sign='+';
        int num=0;
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(Character.isDigit(c))
                num=num*10+(c-'0');
            if(!Character.isDigit(c) && c!=' ' || i==s.length()-1)
            {
                if(sign=='+')
                    st.push(num);
                if(sign=='-')
                    st.push(-num);
                if(sign=='*')
                    st.push(st.pop()*num);
                if(sign=='/')
                    st.push(st.pop()/num);
                sign=c;
                num=0;
            }
        }
        int res=0;
        while(!st.isEmpty())
            res+=st.pop();
        return res;
    }
    /*
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
    Given two strings s and t, determine if they are isomorphic.

    Two strings are isomorphic if the characters in s can be replaced to get t.

    All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

    Example 1:

    Input: s = "egg", t = "add"
    Output: true
    Example 2:

    Input: s = "foo", t = "bar"
    Output: false
    Example 3:

    Input: s = "paper", t = "title"
    Output: true
    Note:
    You may assume both s and t have the same length.
     */
    public boolean isIsomorphic(String s, String t)
    {
        if(s.length()!=t.length())
            return false;
        Map<Character,Character> map=new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            char cs=s.charAt(i);
            char ct=t.charAt(i);
            if(!map.containsKey(cs))
                map.put(cs,ct);
            else
            {
                if(map.get(cs)!=ct)
                    return false;
            }
        }
        map.clear();
        for(int i=0;i<s.length();i++)
        {
            char cs=s.charAt(i);
            char ct=t.charAt(i);
            if(!map.containsKey(ct))
                map.put(ct,cs);
            else
            {
                if(map.get(ct)!=cs)
                    return false;
            }
        }
        return true;
    }
    /*
    Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

    You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

    Example 1:
    Input:
    ["Shogun", "Tapioca Express", "Burger King", "KFC"]
    ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
    Output: ["Shogun"]
    Explanation: The only restaurant they both like is "Shogun".
    Example 2:
    Input:
    ["Shogun", "Tapioca Express", "Burger King", "KFC"]
    ["KFC", "Shogun", "Burger King"]
    Output: ["Shogun"]
    Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
    Note:
    The length of both lists will be in the range of [1, 1000].
    The length of strings in both lists will be in the range of [1, 30].
    The index is starting from 0 to the list length minus 1.
    No duplicates in both lists.
     */
    public String[] findRestaurant(String[] list1, String[] list2)
    {
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<list1.length;i++)
            map.put(list1[i],i);
        List<String> ans=new ArrayList<>();
        int min=Integer.MAX_VALUE;
        for(int i=0;i<list2.length;i++)
        {
            if(map.containsKey(list2[i]))
            {
                int t=i+map.get(list2[i]);
                if(t<min)
                {
                    ans=new ArrayList<>();
                    min=t;
                    ans.add(list2[i]);
                }
                else if(t==min)
                    ans.add(list2[i]);
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
    /*
    Write code to generate all possible case combinations of a given lower-cased string. (e.g.
    "0ab" -> ["0ab", "0aB", "0Ab", "0AB"])
    */
    public List<String> generateCombs(String s)
    {
        if(s.length()==0)
            return new ArrayList<>();
        List<String> res=new ArrayList<>();
        generate(res,s,0,"");
        return res;
    }
    public void generate(List<String> res, String s, int start, String prefix)
    {
        if(start==s.length())
        {
            res.add(prefix);
            return;
        }
        char c=s.charAt(start);
        if(!Character.isLetter(c))
            generate(res,s,start+1,prefix+c);
        else
        {
            generate(res,s,start+1,prefix+Character.toLowerCase(c));
            generate(res,s,start+1,prefix+Character.toUpperCase(c));
        }
    }
    /*
        Write a function to generate all possible permutations of a given string
     */
    public List<String> permutate(String str)
    {
        char[] ch=str.toCharArray();
        List<String> res=new ArrayList<>();
        permutate(ch,res,"");
        return res;
    }
    public void permutate(char[] ch, List<String> res, String prefix)
    {
        if(prefix.length()==ch.length)
        {
            res.add(prefix);
            return;
        }
        for(int i=0;i<ch.length;i++)
            if(!prefix.contains(String.valueOf(ch[i])))
                permutate(ch,res,prefix+ch[i]);

    }
    /*
    Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

    Example:

    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        if(l1==null && l2==null)
            return null;
        else if(l1==null)
            return l2;
        else if(l2==null)
            return l1;
        else
        {
            if(l1.data<l2.data)
            {
                l1.next=mergeTwoLists(l1.next,l2);
                return l1;
            }
            else
            {
                l2.next=mergeTwoLists(l1,l2.next);
                return l2;
            }
        }
    }
    /*
    Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
    Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.
    Example 1:

    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    Output: 5

    Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.
    Example 2:

    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    Output: 0

    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        Set<String> dict=new HashSet<>(wordList);
        if(!dict.contains(endWord))
            return 0;
        Queue<String> q=new LinkedList<>();
        q.offer(beginWord);
        int count=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String curr=q.poll();
                if(curr.equals(endWord))
                    return count+1;
                for(String s:wordList)
                {
                    if(canUse(s,curr) && dict.contains(s))
                    {
                        q.offer(s);
                        dict.remove(s);
                    }
                }
            }
            count++;
        }
        return count;

    }
    public boolean canUse(String s,String t)
    {
        int count=0;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)!=t.charAt(i))
                count++;
        return count==1;
    }
    /*
    Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    Input:
    11110
    11010
    11000
    00000

    Output: 1
    Example 2:

    Input:
    11000
    11000
    00100
    00011

    Output: 3
     */
    public int numIslands(char[][] grid)
    {
        int count=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='1')
                {
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int i, int j)
    {
        if(i<0 || i>=grid[0].length || j<0 || j>=grid[0].length || grid[i][j]!='1')
            return;
        grid[i][j]='0';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
    /*
    There are a total of n courses you have to take, labeled from 0 to n-1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

    Example 1:

    Input: 2, [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0. So it is possible.
    Example 2:

    Input: 2, [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0, and to take course 0 you should
                 also have finished course 1. So it is impossible.
     */
    public boolean canFinish(int numCourses,int[][] prerequistites)
    {
        List<Integer>[] graph=new ArrayList[numCourses];
        int[] inDegree=new int[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i]=new ArrayList<>();
        for(int[] p:prerequistites)
        {
            graph[p[1]].add(p[0]);
            inDegree[p[0]]++;
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<inDegree.length;i++)
            if(inDegree[i]==0)
                q.offer(i);

        Set<Integer> visited=new HashSet<>();

        while(!q.isEmpty())
        {
            int curr=q.poll();
            visited.add(curr);
            for(int g: graph[curr])
            {
                inDegree[g]--;
                if(inDegree[g]==0)
                    q.offer(g);
            }
        }
        return visited.size()==numCourses;
    }
    /*
    Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

    Note:

    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.
    Example 1:

    Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
    Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
    Example 2:

    Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                 But it is larger in lexical order.
     */
    public List<String> findItinerary(List<List<String>> tickets)
    {
        Map<String,PriorityQueue<String>> map=new HashMap<>();
        for(List<String> s:tickets)
        {
            String from=s.get(0);
            String to=s.get(1);
            map.computeIfAbsent(from,k->new PriorityQueue()).add(to);
        }
        List<String> res=new ArrayList<>();
        dfs(map,res,"JFK");
        return res;
    }
    public void dfs(Map<String,PriorityQueue<String>> map, List<String> res,String s)
    {
        while(map.containsKey(s) && !map.get(s).isEmpty())
                dfs(map,res,map.get(s).poll());
        res.add(0,s);
    }
    /*
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
    Example:

    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    Note:

    Although the above answer is in lexicographical order, your answer could be in any order you want.
     */
    public List<String> letterCombinations(String digits)
    {
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
        List<String> res=new ArrayList<>();
        compute(keypad,res,digits,0,"");
        return res;
    }
    public void compute(Map<Character,String> map,List<String> res,String digits, int start, String prefix)
    {
        if(start==digits.length())
        {
            res.add(prefix);
            return;
        }
        String val=map.get(digits.charAt(start));
        for(char c:val.toCharArray())
            compute(map,res,digits,start+1,prefix+c);
    }
    /*
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    For example, given n = 3, a solution set is:

    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]
     */
    public List<String> generateParenthesis(int n)
    {
        List<String> res=new ArrayList<>();
        generate(res,0,0,n,"");
        return res;
    }
    public void generate(List<String> res,int l, int r, int n, String prefix)
    {
        if(l==n && r==n)
        {
            res.add(prefix);
            return;
        }
        if(l>n || r>n)
            return;
        if(l<r)
            return;
        generate(res,l+1,r,n,prefix+'(');
        generate(res,l,r+1,n,prefix+')');
    }
    /*
    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

    The same repeated number may be chosen from candidates unlimited number of times.

    Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.
    Example 1:

    Input: candidates = [2,3,6,7], target = 7,
    A solution set is:
    [
      [7],
      [2,2,3]
    ]
    Example 2:

    Input: candidates = [2,3,5], target = 8,
    A solution set is:
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]
     */
    public List<List<Integer>> combinationSum(int[] candidates,int target)
    {
        List<List<Integer>> res=new ArrayList<>();
        compute(res,new ArrayList<>(),candidates,target,0);
        return res;
    }
    public void compute(List<List<Integer>> res, List<Integer> temp, int[] arr,int target,int start)
    {
        if(target==0)
        {
            res.add(new ArrayList<>(temp));
            return;
        }
        if(target<0)
            return;
        for(int i=start;i<arr.length;i++)
        {
            temp.add(arr[i]);
            compute(res,temp,arr,target-arr[i],i);
            temp.remove(temp.size()-1);
        }
    }
    public static void main(String[] args)
    {
        YelpPrep obj=new YelpPrep();
        System.out.println(obj.combinationSum(new int[]{2,3,6,7}, 7));

    }
}
