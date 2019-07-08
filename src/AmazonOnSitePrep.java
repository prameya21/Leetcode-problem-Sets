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
    /*
    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example:

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        return add(l1,l2,0);
    }
    public ListNode add(ListNode l1,ListNode l2, int carry)
    {
        if(l1==null && l2==null && carry==0)
            return null;
        int sum=carry;
        sum+=l1==null?0:l1.data;
        sum+=l2==null?0:l2.data;
        ListNode head=new ListNode(sum%10);
        head.next=add(l1==null?null:l1.next,l2==null?null:l2.next,sum/10);
        return head;
    }
    /*
    Merge Two Sorted Lists
    Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

    Example:

    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1,ListNode l2)
    {
        if(l1==null && l2==null)
            return null;
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
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
    /*
    Copy List with Random Pointer
    A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

    Return a deep copy of the list.
    Example 1:
    Input:
    {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
    Explanation:
    Node 1's value is 1, both of its next and random pointer points to Node 2.
    Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
    Note:
    You must return the copy of the given head as a reference to the cloned list.
     */
    private class Node
    {
        int val;
        Node next;
        Node random;
        public Node(int val)
        {
            this.val=val;
        }
        public Node(int _val,Node _next,Node _random)
        {
            val = _val;
            next = _next;
            random = _random;
        }
    }
    public Node copyRandomList(Node head)
    {
        Map<Node,Node> map=new HashMap<>();
        Node node=head;
        while(node!=null)
        {
            map.put(node,new Node(node.val));
            node=node.next;
        }
        node=head;
        while(node!=null)
        {
            map.get(node).next=map.get(node.next);
            map.get(node).random=map.get(node.random);
            node=node.next;
        }
        return map.get(node);
    }
    /*
    Reverse a singly linked list.

    Example:

    Input: 1->2->3->4->5->NULL
    Output: 5->4->3->2->1->NULL
    Follow up:

    A linked list can be reversed either iteratively or recursively. Could you implement both?
     */
    public ListNode reverse(ListNode head)
    {
        ListNode prev=null;
        while(head!=null)
        {
            ListNode next=head.next;
            head.next=prev;
            prev=head;
            head=next;
        }
        return prev;
    }
    public ListNode recur_reverse(ListNode head,ListNode prev)
    {
        if(head==null)
            return prev;
        ListNode next=head.next;
        head.next=prev;
        return recur_reverse(next,head);
    }
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
    public ListNode mergeKLists(ListNode[] lists)
    {
        if(lists==null || lists.length==0)
            return null;
        return merge(lists,0,lists.length-1);
    }
    public ListNode merge(ListNode[] lists, int s, int e)
    {
        if(s>e)
            return null;
        if(s==e)
            return lists[s];
        int m=(s+e)/2;
        ListNode left=merge(lists,s,m);
        ListNode right=merge(lists,m+1,e);
        return mergeTwoLists(left,right);

    }
    /*
    Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

    k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
    Example:
    Given this linked list: 1->2->3->4->5
    For k = 2, you should return: 2->1->4->3->5
    For k = 3, you should return: 3->2->1->4->5
    Note:
    Only constant extra memory is allowed.
    You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
    public ListNode reverseKGroup(ListNode head, int k)
    {
        ListNode curr=head;
        int count=0;
        while(curr!=null && count!=k)
        {
            curr=curr.next;
            count++;
        }
        if(count==k)
        {
            curr=reverseKGroup(curr,k);
            while(count>0)
            {
                ListNode next=head.next;
                head.next=curr;
                curr=head;
                head=next;
                count--;
            }
            return curr;
        }
        return head;
    }
    /*
    Given a binary tree, determine if it is a valid binary search tree (BST).

    Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.


    Example 1:

        2
       / \
      1   3

    Input: [2,1,3]
    Output: true
    Example 2:

        5
       / \
      1   4
         / \
        3   6

    Input: [5,1,4,null,null,3,6]
    Output: false
    Explanation: The root node's value is 5 but its right child's value is 4.
     */
    public boolean isValidBST(TreeNode root)
    {
        return isBSTUtil(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }
    public boolean isBSTUtil(TreeNode root,long max, long min)
    {
        if(root==null)
            return true;
        if(root.val>=max || root.val<=min)
            return false;
        return isBSTUtil(root.left,root.val,min) && isBSTUtil(root.right,max,root.val);
    }
    /*
    Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

    For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

        1
       / \
      2   2
     / \ / \
    3  4 4  3


    But the following [1,2,2,null,3,null,3] is not:

        1
       / \
      2   2
       \   \
       3    3
     */
    public boolean isSymmetric(TreeNode root)
    {
        if(root==null)
            return true;
        return isSymmetric(root.left,root.right);
    }
    public boolean isSymmetric(TreeNode root1,TreeNode root2)
    {
        if(root1==null && root2==null)
            return true;
        else if(root1!=null && root2!=null && root1.val== root2.val)
            return isSymmetric(root1.left,root2.right) && isSymmetric(root2.left,root1.right);
        else
            return false;
    }
    /*
    Given a non-empty binary tree, find the maximum path sum.

    For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

    Example 1:

    Input: [1,2,3]

           1
          / \
         2   3

    Output: 6
    Example 2:

    Input: [-10,9,20,null,null,15,7]

       -10
       / \
      9  20
        /  \
       15   7

    Output: 42
     */
    int maxPath=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root)
    {
        max_gain(root);
        return maxPath;
    }
    public int max_gain(TreeNode root)
    {
        if(root==null)
            return 0;
        int left_gain=Math.max(max_gain(root.left),0);
        int right_gain=Math.max(max_gain(root.right),0);
        maxPath=Math.max(maxPath,left_gain+right_gain+root.val);
        return Math.max(left_gain,right_gain)+root.val;
    }
    /*
    Longest Univalue Path
    Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

    The length of path between two nodes is represented by the number of edges between them.
    Example 1:
    Input:

                  5
                 / \
                4   5
               / \   \
              1   1   5
    Output: 2
    Example 2:
    Input:

                  1
                 / \
                4   5
               / \   \
              4   4   5
    Output: 2
    Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
     */
    int ans;
    public int longestUnivaluePath(TreeNode root)
    {
        ans=0;
        if(root==null)
            return ans;
        compute(root);
        return ans;
    }
    public int compute(TreeNode root)
    {
        if(root==null)
            return 0;
        int left=compute(root.left);
        int right=compute(root.right);
        int lval=0,rval=0;
        if(root.left!=null && root.left.val==root.val)
            lval+=1;
        else if(root.right!=null && root.right.val==root.val)
            rval+=1;
        ans=Math.max(ans,lval+rval);
        return Math.max(lval,rval);

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
    public int ladderLength(String start,String end,Set<String> dict)
    {
        if(!dict.contains(end))
            return 0;
        Map<String,Integer> dist=new HashMap<>();
        dist.put(start,1);
        Queue<String> q=new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String curr=q.poll();
                if(curr.equals(end))
                    return dist.get(curr);
                int curr_dist=dist.get(curr);
                for(String s: dict)
                {
                    if(canUse(curr,s) && !dist.containsKey(s))
                    {
                        dist.put(s,curr_dist+1);
                        q.offer(s);
                    }
                }

            }
        }
        return 0;
    }
    public boolean canUse(String s, String t)
    {
        int ctr=0;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)!=t.charAt(i))
                ctr++;

        return ctr==1;
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
                    dfs(i,j,grid);
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(int i,int j, char[][] grid)
    {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]=='0')
            return;
        grid[i][j]='0';
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j+1,grid);
        dfs(i,j-1,grid);
    }
    /*
    An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

    Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

    To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

    At the end, return the modified image.

    Example 1:
    Input:
    image = [[1,1,1],[1,1,0],[1,0,1]]
    sr = 1, sc = 1, newColor = 2
    Output: [[2,2,2],[2,2,0],[2,0,1]]
    Explanation:
    From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
    by a path of the same color as the starting pixel are colored with the new color.
    Note the bottom corner is not colored 2, because it is not 4-directionally connected
    to the starting pixel.
    Note:

    The length of image and image[0] will be in the range [1, 50].
    The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
    The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        int c=image[sr][sc];
        if(c!=newColor)
            fill(image,sr,sc,c,newColor);
        return image;
    }
    public void fill(int[][] image,int i,int j,int c,int nc)
    {
        if(i<0 || i>=image.length || j<0 || j>=image[0].length || image[i][j]!=c)
            return;
        image[i][j]=nc;
        fill(image,i+1,j,c,nc);
        fill(image,i-1,j,c,nc);
        fill(image,i,j+1,c,nc);
        fill(image,i,j-1,c,nc);
    }
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
    Note:

    All of the nodes' values will be unique.
    p and q are different and both values will exist in the binary tree.
     */
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
        else
            return left==null?right:left;
    }
    /*
    Find the sum of all left leaves in a given binary tree.

    Example:

        3
       / \
      9  20
        /  \
       15   7

    There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
     */
    public int sumOfLeftLeaves(TreeNode root)
    {
        return sum(root,false);
    }
    public int sum(TreeNode root,boolean isLeft)
    {
        if(root==null)
            return 0;
        if(root.left==null && root.right==null && isLeft)
            return root.val;
        int s=0;
        s+=sum(root.left,true);
        s+=sum(root.right,false);
        return s;
    }
    /*
    Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
    Example:
    Input: [1,2,3,4,5]

              1
             / \
            2   3
           / \
          4   5

    Output: [[4,5,3],[2],[1]]
    Explanation:

    1. Removing the leaves [4,5,3] would result in this tree:

              1
             /
            2
    2. Now removing the leaf [2] would result in this tree:

              1
    3. Now removing the leaf [1] would result in the empty tree:

              []
     */
    public List<List<Integer>> findLeaves(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        while(root!=null)
        {
            List<Integer> temp=new ArrayList<>();
            root=collect(root,temp);
            res.add(temp);
        }
        return res;
    }
    public TreeNode collect(TreeNode root,List<Integer> res)
    {
        if(root==null)
            return null;
        if(root.left==null && root.right==null)
        {
            res.add(root.val);
            return null;
        }
        root.left=collect(root.left,res);
        root.right=collect(root.right,res);
        return root;
    }
    /*
    Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

    Note:
    A subtree must include all of its descendants.

    Example:

    Input: [10,5,15,1,8,null,7]

       10
       / \
      5  15
     / \   \
    1   8   7

    Output: 3
    Explanation: The Largest BST Subtree in this case is the highlighted one.
                 The return value is the subtree's size, which is 3.
     */
    public int largestBSTSubtree(TreeNode root)
    {
        int[] res=helper(root);
        return res[2];
    }
    public int[] helper(TreeNode root)
    {
        if(root==null)
            return(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,0});
        int[] left=helper(root.left);
        int[] right=helper(root.right);
        if(root.val>left[1] && root.val<right[0])
            return new int[]{Math.min(root.val,left[0]),Math.max(root.val,right[1]),left[2]+right[2]+1};
        else
            return new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,Math.max(left[2],right[2])};
    }
    /*
    Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)

    A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.

    Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.

    Example 1:
    Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
    Output: [1,2,3,4,null,null,7,8,9,null,14]
    Example 2:
    Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
    Output: [5,4,8,11,null,17,4,7,null,null,null,5]
    Example 3:
    Input: root = [1,2,-3,-5,null,4,null], limit = -1
    Output: [1,null,-3,4]
    Note:

    The given tree will have between 1 and 5000 nodes.
    -10^5 <= node.val <= 10^5
    -10^9 <= limit <= 10^9
     */
    public TreeNode sufficientSubset(TreeNode root, int limit)
    {
        if(root==null)
            return null;
        if(root.left==null && root.right==null)
            return root.val>limit?root:null;
        root.left=sufficientSubset(root.left,limit-root.val);
        root.right=sufficientSubset(root.right,limit-root.val);
        return root.left==null && root.right==null?null:root;

    }
    /*
    Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

    Only one letter can be changed at a time
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
    Note:

    Return an empty list if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.
    Example 1:

    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    Output:
    [
      ["hit","hot","dot","dog","cog"],
      ["hit","hot","lot","log","cog"]
    ]
    Example 2:

    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    Output: []

    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     */
    public List<String> canUse(String s, Set<String> dict)
    {
        List<String> res=new ArrayList<>();
        for(String str:dict)
        {
            int cnt=0;
            for(int i=0;i<s.length();i++)
                if(s.charAt(i)!=str.charAt(i))
                    cnt++;
            if(cnt==1)
                res.add(str);
        }
        return res;
    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        Map<String,List<String>> adj=new HashMap<>();
        Set<String> dict=new HashSet<>(wordList);
        if(!dict.contains(endWord))
            return new ArrayList<>();
        dict.add(beginWord);
        Map<String,Integer> dist=new HashMap<>();
        List<List<String>> res=new ArrayList<>();
        bfs(beginWord,endWord,dict,adj,dist);
        dfs(beginWord,endWord,dict,adj,dist,new ArrayList<>(),res);
        return res;
    }
    public void bfs(String start, String end, Set<String> dict, Map<String,List<String>> adj, Map<String,Integer> dist)
    {
        Queue<String> q=new LinkedList<>();
        for(String s:dict)
            adj.put(s,new ArrayList<>());
        q.offer(start);
        dist.put(start,1);
        while(!q.isEmpty())
        {
            int size=q.size();
            boolean found=false;
            for(int i=0;i<size;i++)
            {
                String currWord=q.poll();
                int currDistance=dist.get(currWord);
                for(String s:canUse(currWord,dict))
                {
                    adj.get(currWord).add(s);
                    if(!dist.containsKey(s))
                    {
                        dist.put(s,currDistance+1);
                        if(s.equals(end))
                            found=true;
                        else
                            q.offer(s);
                    }
                }
            }
            if(found)
                break;
        }
    }
    public void dfs(String start, String end, Set<String> dict, Map<String,List<String>> adj, Map<String,Integer> dist,List<String> temp,List<List<String>> result)
    {
        temp.add(start);
        if(start.equals(end))
            result.add(new ArrayList<>(temp));
        int currDistance=dist.get(start);
        for(String s:adj.get(start))
        {
            if(dist.get(s)==currDistance+1)
            {
                dfs(s,end,dict,adj,dist,temp,result);
            }

        }
        temp.remove(temp.size()-1);
    }
    public static void main(String[] args)
    {
        AmazonOnSitePrep obj=new AmazonOnSitePrep();
        System.out.println(obj.findLadders("hit","cog",new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"))));

    }
}
/*
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
 */