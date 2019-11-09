import java.util.*;

public class OraclePrep
{
    {/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].*/}
    public int[] twoSum(int[] nums, int target)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            int c=target-nums[i];
            if(map.containsKey(c))
                return new int[]{map.get(c),i};
            else
                map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

    {
        /*
        Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

        Basically, the deletion can be divided into two stages:

        Search for a node to remove.
        If the node is found, delete the node.
        Note: Time complexity should be O(height of tree).

        Example:

        root = [5,3,6,2,4,null,7]
        key = 3

            5
           / \
          3   6
         / \   \
        2   4   7

        Given key to delete is 3. So we find the node with value 3 and delete it.

        One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

            5
           / \
          4   6
         /     \
        2       7

        Another valid answer is [5,2,6,null,4,null,7].

            5
           / \
          2   6
           \   \
         */
    }
    public TreeNode deleteNode(TreeNode root,int key)
    {
        if(root==null)
            return null;
        else if(key<root.val)
            root.left=deleteNode(root.left,key);
        else if(key>root.val)
            root.right=deleteNode(root.right,key);
        else
        {
            if(root.left==null)
                return root.right;
            else if(root.right==null)
                return root.left;
            else
            {
                root.val=getMin(root.right);
                root.right=deleteNode(root.right,root.val);
            }
        }
        return root;
    }
    public int getMin(TreeNode root)
    {
        while(root.left!=null)
            root=root.left;
        return root.val;
    }

    {
        /*
        Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

        Example:

        Input:
        [
          1->4->5,
          1->3->4,
          2->6
        ]
        Output: 1->1->2->3->4->4->5->6
         */
    }
    public ListNode mergeKLists(ListNode[] lists)
    {
        return mergeList(lists,0,lists.length-1);
    }
    public ListNode mergeList(ListNode[] lists, int l,int h)
    {
        if(l>h)
            return null;
        if(l==h)
            return lists[l];
        int mid=(h+l)/2;
        ListNode l1=mergeList(lists,l,mid);
        ListNode l2=mergeList(lists,mid+1,h);
        return merge(l1,l2);
    }
    public ListNode merge(ListNode l1, ListNode l2)
    {
        if(l1==null)
            return l2;
        else if(l2==null)
            return l1;
        else
        {
            if(l1.data<l2.data)
            {
                l1.next=merge(l1.next,l2);
                return l1;
            }
            else
            {
                l2.next=merge(l1,l2.next);
                return l2;
            }
        }
    }
    {
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

    }
    public String[] findRestaurant(String[] list1, String[] list2)
    {
        List<String> res=new ArrayList<>();
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<list2.length;i++)
            map.put(list2[i],i);
        int min=Integer.MAX_VALUE;
        for(int i=0;i<list1.length;i++)
        {
            if(map.containsKey(list1[i]))
            {
                int tmp=i+map.get(list1[i]);
                if(tmp<min)
                {
                    res=new ArrayList<>();
                    res.add(list1[i]);
                    min=tmp;
                }
                else if(tmp==min)
                    res.add(list1[i]);
            }
        }
        return res.toArray(new String[res.size()]);
    }

    {
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
    }
    public int numIslands(char[][] grid)
    {
        int cnt=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='1')
                {
                    dfs(grid,i,j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public void dfs(char[][] grid, int i, int j)
    {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!='1')
            return;
        grid[i][j]='0';
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
    }

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
    }
    public List<String> letterCombinations(String digits)
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
        List<String> res=new ArrayList<>();
        compute(res,keypad,digits,"",0);
        return res;
    }
    public void compute(List<String> res, Map<Character,String> keypad, String digits, String tmp,int offset)
    {
        if(tmp.length()>digits.length())
            return;
        if(tmp.length()==digits.length())
        {
            res.add(tmp);
            return;
        }
        char c=digits.charAt(offset);
        String str=keypad.get(c);
        for(int i=0;i<str.length();i++)
        {
            compute(res,keypad,digits,tmp+str.charAt(i),offset+1);
        }

    }

    {
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
    }
    public List<List<Integer>> combinationSum(int[] candidates,int target)
    {
        if(candidates==null || candidates.length==0)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        compute(candidates,target,0,res,new ArrayList<>(),0);
        return res;
    }
    public void compute(int[] candidates,int target, int sum, List<List<Integer>> res, List<Integer> temp,int start)
    {
        if(sum==target)
            res.add(new ArrayList<>(temp));
        if(sum>target)
            return;
        for(int i=start;i<candidates.length;i++)
        {
            temp.add(candidates[i]);
            compute(candidates,target,sum+candidates[i],res,temp,i);
            temp.remove(temp.size()-1);
        }
    }
    {
        /*
        Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

        Each number in candidates may only be used once in the combination.

        Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

        Input: candidates = [10,1,2,7,6,1,5], target = 8,
        A solution set is:
        [
          [1, 7],
          [1, 2, 5],
          [2, 6],
          [1, 1, 6]
        ]
        Example 2:

        Input: candidates = [2,5,2,1,2], target = 5,
        A solution set is:
        [
          [1,2,2],
          [5]
        ]
         */
    }
    public List<List<Integer>> combinationSum2(int[] candidates,int target)
    {
        if(candidates==null || candidates.length==0)
            return new ArrayList<>();
        Set<List<Integer>> res=new HashSet<>();
        Arrays.sort(candidates);
        compute2(candidates,target,0,0,res,new ArrayList<>());
        return new ArrayList<>(res);
    }
    public void compute2(int[] nums, int target, int sum, int start,Set<List<Integer>> res, List<Integer> temp)
    {
        if(sum>target)
            return;
        if(sum==target)
        {
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i=start;i<nums.length;i++)
        {
            temp.add(nums[i]);
            compute2(nums,target,sum+nums[i],i+1,res,temp);
            temp.remove(temp.size()-1);
        }

    }

    {
        /*
        Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

        Note:

        All numbers will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

        Input: k = 3, n = 7
        Output: [[1,2,4]]
        Example 2:

        Input: k = 3, n = 9
        Output: [[1,2,6], [1,3,5], [2,3,4]]
         */
    }

    public List<List<Integer>> combinationSum3(int k, int n)
    {
        if(k==0 && n!=0)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        compute3(k,n,0,1,res,new ArrayList<>());
        return res;
    }
    public void compute3(int k, int n, int sum, int start, List<List<Integer>> res, List<Integer> temp)
    {
        if(temp.size()>k || sum>n)
            return;
        else if(temp.size()==k && sum==n)
        {
            res.add(new ArrayList<>(temp));
            return;
        }
        else
        {
            for(int i=start;i<=9;i++)
            {
                temp.add(i);
                compute3(k,n,sum+i,i+1,res,temp);
                temp.remove(temp.size()-1);
            }
        }
    }
    {
        /*
        Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

        Example 1:

        Input: [[0, 30],[5, 10],[15, 20]]
        Output: 2
        Example 2:

        Input: [[7,10],[2,4]]
        Output: 1
        NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
         */
    }
    public int minMeetingRooms(int[][] intervals)
    {
        int[] start=new int[intervals.length];
        int[] end=new int[intervals.length];
        for(int i=0;i<intervals.length;i++)
        {
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int sp=0,ep=0,count=0;
        while(sp<start.length)
        {
            if(start[sp]<end[ep])
                count++;
            else
                ep++;
            sp++;
        }
        return count;
    }
    {
        /*
        Given a collection of intervals, merge all overlapping intervals.

        Example 1:

        Input: [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
        Example 2:

        Input: [[1,4],[4,5]]
        Output: [[1,5]]
        Explanation: Intervals [1,4] and [4,5] are considered overlapping.
        NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
         */
    }

    public int[][] merge(int[][] intervals)
    {
        Arrays.sort(intervals, new Comparator<int[]>()
        {
            public int compare(int[] a, int[] b)
            {
                return a[0]-b[0];
            }
        });
        LinkedList<int[]> res=new LinkedList<>();
        for(int[] i: intervals)
        {
            if(res.isEmpty() || res.getLast()[1]<i[0])
                res.add(i);
            else
                res.getLast()[1]=Math.max(res.getLast()[1],i[1]);
        }
        return res.toArray(new int[res.size()][]);
    }
    {
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
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        Set<String> dict=new HashSet<>(wordList);
        if(!dict.contains(endWord))
            return 0;
        Queue<String> q=new LinkedList<>();
        int count=0;
        q.offer(beginWord);
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
                    if(dict.contains(s) && canUse(curr,s))
                    {
                        dict.remove(s);
                        q.offer(s);
                    }
                }
            }
            count++;
        }
        return -1;
    }
    public boolean canUse(String s, String t)
    {
        int count=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)!=t.charAt(i))
                count++;
        }
        return count==1;

    }
    public int ladderLength2(String start, String end, List<String> wordList)
    {
        Set<String> dict=new HashSet<>(wordList);
        if(!dict.contains(end))
            return 0;
        Map<String,Integer> dist=new HashMap<>();
        dist.put(start,0);
        Queue<String> q=new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty())
        {
            String curr=q.poll();
            if(curr.equals(end))
                return dist.get(curr)+1;
            for(String s:wordList)
            {
                if(canUse(curr,s) && !dist.containsKey(s))
                {
                    int d=dist.get(curr);
                    dist.put(s,d+1);
                    q.offer(s);
                }
            }
        }
        return -1;
    }

    {
        /*
        Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

        Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

        The order of output does not matter.

        Example 1:

        Input:
        s: "cbaebabacd" p: "abc"

        Output:
        [0, 6]

        Explanation:
        The substring with start index = 0 is "cba", which is an anagram of "abc".
        The substring with start index = 6 is "bac", which is an anagram of "abc".
        Example 2:

        Input:
        s: "abab" p: "ab"

        Output:
        [0, 1, 2]

        Explanation:
        The substring with start index = 0 is "ab", which is an anagram of "ab".
        The substring with start index = 1 is "ba", which is an anagram of "ab".
        The substring with start index = 2 is "ab", which is an anagram of "ab".
         */
    }
    public List<Integer> findAnagrams(String s, String p)
    {
        if(s==null || s.length()<p.length())
            return new ArrayList<>();
        List<Integer> res=new ArrayList<>();
        int plen=p.length();
        int[] pfreq=new int[26];
        for(char c:p.toCharArray())
            pfreq[c-'a']++;
        for(int i=0;i<Math.min(s.length(),i+plen);i++)
        {
            if(isAnagram(s,plen+i,i,pfreq))
                res.add(i);
        }
        return res;
    }
    public boolean isAnagram(String s, int plen, int i, int[] pref)
    {
        int[] sfreq=new int[26];
        while(i<plen)
        {
            if(i>=s.length())
                return false;
            char c=s.charAt(i);
            sfreq[c-'a']++;
            i++;
        }
        for(int j=0;j<sfreq.length;j++)
        {
            if(sfreq[j]!=pref[j])
                return false;
        }
        return true;
    }
    {
        /*
        Given an encoded string, return its decoded string.

        The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

        You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

        Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

        Examples:

        s = "3[a]2[bc]", return "aaabcbc".
        s = "3[a2[c]]", return "accaccacc".
        s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
         */
    }
    public String decodeString(String s)
    {
        if(s==null || s.length()==0)
            return "";
        Stack<Integer> numStack=new Stack<>();
        Stack<String> dataStack=new Stack<>();
        dataStack.push("");

        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(Character.isDigit(c))
            {
                int j=i;
                while(Character.isDigit(s.charAt(i+1)))
                    i++;
                numStack.push(Integer.parseInt(s.substring(j,i+1)));
            }
            else if(c=='[')
                dataStack.push("");
            else if(c==']')
            {
                int cnt=numStack.pop();
                String val="";
                String data=dataStack.pop();
                for(int k=0;k<cnt;k++)
                    val+=data;
                if(dataStack.isEmpty())
                    dataStack.push(val);
                else
                    dataStack.push(dataStack.pop()+val);
            }
            else
            {
                String val=dataStack.pop();
                val+=String.valueOf(c);
                dataStack.push(val);
            }
        }
        return dataStack.pop();
    }
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
        if(s==null || s.length()==0)
            return 0;
        Set<Character> set=new HashSet<>();
        int i=0,j=0,ans=0;
        while(i<s.length() && j<s.length())
        {
            char c=s.charAt(j);
            if(!set.contains(c))
            {
                set.add(c);
                ans=Math.max(ans,j-i+1);
                j++;
            }
            else
            {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return ans;
    }
    {
        /*
        You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

        You may assume the two numbers do not contain any leading zero, except the number 0 itself.

        Example:

        Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
        Output: 7 -> 0 -> 8
        Explanation: 342 + 465 = 807.
         */
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        return helper(l1,l2,0);
    }
    public ListNode helper(ListNode l1,ListNode l2, int carry)
    {
        if(l1==null && l2==null && carry==0)
            return null;
        int sum=0;
        sum+=l1==null?0:l1.data;
        sum+=l2==null?0:l2.data;
        sum+=carry;
        ListNode node=new ListNode(sum%10);
        node.next=helper(l1==null?null:l1.next,l2==null?null:l2.next,sum/10);
        return  node;
    }
    {
        /*
        Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

        If two nodes are in the same row and column, the order should be from left to right.

        Examples 1:

        Input: [3,9,20,null,null,15,7]

           3
          /\
         /  \
         9  20
            /\
           /  \
          15   7

        Output:

        [
          [9],
          [3,15],
          [20],
          [7]
        ]
        Examples 2:

        Input: [3,9,8,4,0,1,7]

             3
            /\
           /  \
           9   8
          /\  /\
         /  \/  \
         4  01   7

        Output:

        [
          [4],
          [9],
          [3,0,1],
          [8],
          [7]
        ]
        Examples 3:

        Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

             3
            /\
           /  \
           9   8
          /\  /\
         /  \/  \
         4  01   7
            /\
           /  \
           5   2

        Output:

        [
          [4],
          [9,5],
          [3,0,1],
          [8,2],
          [7]
        ]
         */
    }
    public List<List<Integer>> verticalOrder(TreeNode r)
    {
        List<List<Integer>> res=new ArrayList<>();
        if(r==null)
            return res;
        Map<Integer,List<Integer>> map=new HashMap<>();
        Queue<TreeNode> q=new LinkedList<>();
        Queue<Integer> cols=new LinkedList<>();
        q.offer(r);
        cols.offer(0);
        int min=0,max=0;
        while(!q.isEmpty())
        {
            TreeNode curr=q.poll();
            int col=cols.poll();
            if(!map.containsKey(col))
                map.put(col,new ArrayList<>());
            if(curr.left!=null)
            {
                q.offer(curr.left);
                cols.offer(col-1);
                min=Math.min(col-1,min);
            }
            if(curr.right!=null)
            {
                q.offer(curr.right);
                cols.offer(col+1);
                max=Math.max(col+1,max);
            }
        }
        for(int i=min;i<=max;i++)
            res.add(map.get(i));
        return res;
    }
    {
        /*
        Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

        For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

        Example:

        Given the sorted linked list: [-10,-3,0,5,9],

        One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

              0
             / \
           -3   9
           /   /
         -10  5
         */
    }
    public TreeNode sortedListToBST(ListNode head)
    {
        if(head==null)
            return null;
        ListNode mid=findMid(head);
        TreeNode root=new TreeNode(mid.data);
        root.left=sortedListToBST(head);
        root.right=sortedListToBST(mid.next);
        return root;
    }
    public ListNode findMid(ListNode head)
    {
        ListNode slow=head,fast=head;
        ListNode prev=null;
        while(fast!=null && fast.next!=null)
        {
            prev=slow;
            fast=fast.next.next;
            slow=slow.next;
        }
        if(prev!=null)
            prev.next=null;
        return slow;
    }
    {
        /*
        Given a 2D board and a word, find if the word exists in the grid.

        The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

        Example:

        board =
        [
          ['A','B','C','E'],
          ['S','F','C','S'],
          ['A','D','E','E']
        ]

        Given word = "ABCCED", return true.
        Given word = "SEE", return true.
        Given word = "ABCB", return false.
         */
    }
    public boolean exist(char[][] board, String word)
    {
        if(board==null || word==null)
            return false;
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(helper_exist(board,i,j,0,word,new boolean[board.length][board[0].length]))
                    return true;
            }
        }
        return false;
    }
    public boolean helper_exist(char[][] board, int i, int j, int len,String word,boolean[][] visited)
    {
        if(len==word.length())
            return true;
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j] || board[i][j]!=word.charAt(len))
            return false;
        visited[i][j]=true;
        if(helper_exist(board,i+1,j,len+1,word,visited) || helper_exist(board,i-1,j,len+1,word,visited) || helper_exist(board,i,j+1,len+1,word,visited) || helper_exist(board,i,j-1,len+1,word,visited))
            return true;
        visited[i][j]=false;
        return false;
    }
    {
        /*
        Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

        According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

        Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
        Example 1:

        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        Output: 3
        Explanation: The LCA of nodes 5 and 1 is 3.
        Example 2:

        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        Output: 5
        Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
         */
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root==null)
            return null;
        if(root==p || root==q)
            return root;
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null && right!=null)
            return root;
        return left==null?right:left;
    }
    {
        /*
        Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

        Note:

        The same word in the dictionary may be reused multiple times in the segmentation.
        You may assume the dictionary does not contain duplicate words.
        Example 1:

        Input: s = "leetcode", wordDict = ["leet", "code"]
        Output: true
        Explanation: Return true because "leetcode" can be segmented as "leet code".
        Example 2:

        Input: s = "applepenapple", wordDict = ["apple", "pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
                     Note that you are allowed to reuse a dictionary word.
        Example 3:

        Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        Output: false
         */
    }
    public boolean wordBreak(String s, List<String> wordDict)
    {
        boolean[] visited=new boolean[s.length()];
        return helper_wordBreak(new HashSet<>(wordDict), s, 0, visited);
    }
    public boolean helper_wordBreak(Set<String> dict, String s, int len, boolean[] visited)
    {
        if(len==s.length())
            return true;
        if(visited[len])
            return true;
        for(int i=len+1;i<=s.length();i++)
        {
            if(dict.contains(s.substring(len,i)) && helper_wordBreak(dict,s,i,visited))
            {
                visited[len]=true;
                return true;
            }
        }
        visited[len]=false;
        return false;
    }

    public boolean wordBreakBFS(String s, List<String> wordDict)
    {
        boolean[] visited=new boolean[s.length()];
        Queue<Integer> q=new LinkedList<>();
        Set<String> dict=new HashSet<>(wordDict);
        q.offer(0);
        visited[0]=true;
        while(!q.isEmpty())
        {
            int curr=q.poll();
            for(int i=curr+1;i<=s.length();i++)
            {
                if(dict.contains(s.substring(curr,i)) && !visited[i])
                {
                    if(i==s.length())
                        return true;
                    visited[i]=true;
                    q.offer(i);
                }
            }
        }
        return false;
    }

    {
        /*
        Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

        You may assume that the intervals were initially sorted according to their start times.

        Example 1:

        Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
        Output: [[1,5],[6,9]]
        Example 2:

        Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        Output: [[1,2],[3,10],[12,16]]
        Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
        NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
         */
    }
    public int[][] insert(int[][] intervals, int[] newInterval)
    {
        List<int[]> list_intervals=new ArrayList<>();
        for(int[] i:intervals)
            list_intervals.add(i);
        int i=0;
        while(i<list_intervals.size() && list_intervals.get(i)[0]<=newInterval[0])
        {
            if(list_intervals.get(i)[1]>=newInterval[0])
            {
                if(list_intervals.get(i)[1]>=newInterval[1])
                    return list_intervals.toArray(new int[list_intervals.size()][]);
                else if(list_intervals.get(i)[1]<newInterval[1])
                {
                    newInterval[0]=list_intervals.get(i)[0];
                    list_intervals.remove(i);
                    i--;
                }
            }
            i++;
        }
        while(i<list_intervals.size() && newInterval[1]>=list_intervals.get(i)[0])
        {
            if(list_intervals.get(i)[1]>newInterval[1])
                newInterval[1]=list_intervals.get(i)[1];
            list_intervals.remove(i);
        }
        list_intervals.add(i,newInterval);
        return list_intervals.toArray(new int[list_intervals.size()][]);
    }

    {
        /*
        Given a collection of distinct integers, return all possible permutations.

        Example:

        Input: [1,2,3]
        Output:
        [
          [1,2,3],
          [1,3,2],
          [2,1,3],
          [2,3,1],
          [3,1,2],
          [3,2,1]
        ]
         */
    }
    public  List<List<Integer>> permute(int[] nums)
    {
        if(nums==null || nums.length==0)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        permute_helper(nums,res,new ArrayList<>());
        return res;
    }

    public void permute_helper(int[] nums, List<List<Integer>> res, List<Integer> temp)
    {
        if(nums.length==temp.size())
        {
            res.add(new ArrayList<>(temp));
            return;
        }
        else
        {
            for(int i=0;i<nums.length;i++)
            {
                if(temp.contains(nums[i]))
                    continue;
                temp.add(nums[i]);
                permute_helper(nums,res,temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    {
        /*
        There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary,
        where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

        Example 1:

        Input:
        [
          "wrt",
          "wrf",
          "er",
          "ett",
          "rftt"
        ]

        Output: "wertf"
        Example 2:

        Input:
        [
          "z",
          "x"
        ]

        Output: "zx"
        Example 3:

        Input:
        [
          "z",
          "x",
          "z"
        ]

        Output: ""

        Explanation: The order is invalid, so return "".
        Note:

        You may assume all letters are in lowercase.
        You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
        If the order is invalid, return an empty string.
        There may be multiple valid order of letters, return any one of them is fine.
         */
    }
    public String alienOrder(String[] words)
    {
        Map<Character,Set<Character>> graph=new HashMap<>();
        Map<Character,Integer> degree=new HashMap<>();
        for(String w:words)
        {
            for(char c:w.toCharArray())
            {
                graph.putIfAbsent(c,new HashSet<>());
                degree.put(c,0);
            }
        }
        for(int i=0;i<words.length-1;i++)
        {
            String w1=words[i];
            String w2=words[i+1];
            for(int j=0;j<Math.min(w1.length(),w2.length());j++)
            {
                if(w1.charAt(j)!=w2.charAt(j))
                {
                    if(!graph.get(w1.charAt(j)).contains(w2.charAt(j)))
                    {
                        graph.get(w1.charAt(j)).add(w2.charAt(j));
                        degree.put(w2.charAt(j),degree.get(w2.charAt(j))+1);
                        break;
                    }
                }

            }
        }
        Queue<Character> q=new LinkedList<>();
        Set<Character> visited=new HashSet<>();
        StringBuilder sb=new StringBuilder();
        for(char c:degree.keySet())
            if(degree.get(c)==0)
            {
                q.offer(c);
                visited.add(c);
            }

        while(!q.isEmpty())
        {
            char c=q.poll();
            sb.append(c);
            for(char ch:graph.get(c))
            {
                if(!visited.contains(ch))
                {
                    degree.put(ch,degree.get(ch)-1);
                    if(degree.get(ch)==0)
                    {
                        visited.add(ch);
                        q.offer(ch);
                    }
                }
            }
        }
        return visited.size()==degree.size()?sb.toString():"";

    }
    public String alienOrderDFS(String[] words)
    {
        boolean[][] graph=new boolean[26][26];
        int[] visited=new int[26];
        Arrays.fill(visited,-1);
        for(String w:words)
            for(char c:w.toCharArray())
                visited[c-'a']=0;

        for(int i=0;i<words.length-1;i++)
        {
            String w1=words[i];
            String w2=words[i+1];
            for(int j=0;j<Math.min(w1.length(),w2.length());j++)
            {
                char c1=w1.charAt(j);
                char c2=w2.charAt(j);
                if(c1!=c2)
                {
                    graph[c1-'a'][c2-'a']=true;
                    break;
                }
            }
        }
        Stack<Character> st=new Stack<>();
        for(int i=0;i<visited.length;i++)
        {
            if(visited[i]==0)
                if(!dfs(graph,visited,st,i))
                    return "";
        }
        StringBuilder sb=new StringBuilder();
        while(!st.isEmpty())
            sb.append(st.pop());
        return sb.toString();
    }
    public boolean dfs(boolean[][] graph,int[] visited, Stack<Character> st, int i)
    {
        visited[i]=1;
        for(int j=0;j<visited.length;j++)
        {
            if(graph[i][j])
            {
                if(visited[j]==1)
                    return false;
                if(visited[j]==0 && !dfs(graph,visited,st,j))
                    return false;
            }
        }
        visited[i]=2;
        char c=(char)(i+'a');
        st.push(c);
        return true;
    }

    {
        /*
        Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

        Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.
        Example 1:

        Input: queries = ["cbd"], words = ["zaaaz"]
        Output: [1]
        Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
        Example 2:

        Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
        Output: [1,2]
        Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").

        Constraints:

        1 <= queries.length <= 2000
        1 <= words.length <= 2000
        1 <= queries[i].length, words[i].length <= 10
        queries[i][j], words[i][j] are English lowercase letters.
         */
    }
    public int[] numSmallerByFrequency(String[] queries, String[] words)
    {
        int[] q=new int[queries.length],w=new int[words.length];

        for(int i=0;i<queries.length;i++)
            q[i]=minValFreq(queries[i]);

        for(int i=0;i<words.length;i++)
            w[i]=minValFreq(words[i]);

        Arrays.sort(w);
        int[] res=new int[q.length];
        for(int i=0;i<res.length;i++)
        {
            int cnt=0;
            for(int j=0;j<w.length;j++)
            {
                if(q[i]<w[j])
                    cnt++;
            }
            res[i]=cnt;
        }
        return res;
    }
    public int minValFreq(String s)
    {
        int[] map=new int[26];
        for(char c:s.toCharArray())
            map[c-'a']++;
        for(int i=0;i<26;i++)
            if(map[i]>0)
                return map[i];
        return 0;
    }
    public static void main(String[] args)
    {
        OraclePrep obj=new OraclePrep();
        System.out.println(obj.permute(new int[]{1,2,3}));
        System.out.println(obj.alienOrderDFS(new String[]{"wrt","wrf","er","ett","rftt"}));
        int[] res=obj.numSmallerByFrequency(new String[]{"bbb","cc"},new String[]{"a","aa","aaa","aaaa"});
        for(int i:res)
            System.out.println(i);
    }
    {
        /*
        System.out.println(obj.twoSum(new int[]{2,7,11,15},9)[1]);
        System.out.println(obj.letterCombinations("23"));
        System.out.println(obj.combinationSum2(new int[]{10,1,2,7,6,1,5},8));
        System.out.println(obj.combinationSum3(3,9));
        System.out.println(obj.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(obj.ladderLength2("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog")));
        System.out.println(obj.ladderLength("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog")));
        System.out.println(obj.findAnagrams("cbaebabacd","abc"));
        System.out.println(obj.decodeString("2[abc]3[cd]ef"));
        System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(obj.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED"));
        System.out.println(obj.wordBreak("catsanddog",Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(obj.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}},new int[]{4,8}));
         */
    }
}
