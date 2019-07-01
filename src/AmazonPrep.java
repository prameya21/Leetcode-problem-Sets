import java.util.*;
/*
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
 */
class RandomSet
{
    Set<Integer> set;
    Random rand;
    public RandomSet()
    {
        set=new HashSet<>();
        rand=new Random();
    }
    public boolean insert(int val)
    {
        if(set.contains(val))
            return false;
        else
        {
            set.add(val);
            return true;
        }
    }
    public boolean remove(int val)
    {
        if(set.contains(val))
        {
            set.remove(val);
            return true;
        }
        return false;
    }
    public int getRandom()
    {
        if(set.size()==0)
            return 0;
        else
        {
            ArrayList<Integer> temp=new ArrayList<>(set);
            return temp.get(rand.nextInt(temp.size()));
        }
    }
}
/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
class BSTCodec
{
    public String serialize(TreeNode root)
    {
        StringBuilder sb=new StringBuilder();
        buildString(root,sb);
        return sb.toString();
    }
    public void buildString(TreeNode root,StringBuilder sb)
    {
        if(root==null)
            sb.append("null,");
        else
        {
            sb.append(root.val).append(",");
            buildString(root.left,sb);
            buildString(root.right,sb);
        }
    }

    public TreeNode deserialize(String s)
    {
        Queue<String> q=new LinkedList<>(Arrays.asList(s.split(",")));
        return buildTree(q);
    }
    public TreeNode buildTree(Queue<String> q)
    {
        if(q.isEmpty())
            return null;
        String curr=q.poll();
        if(curr.equals("null"))
            return null;
        TreeNode root=new TreeNode(Integer.parseInt(curr));
        root.left=buildTree(q);
        root.right=buildTree(q);
        return root;
    }
}

/*
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
class Codec
{
    Map<Integer,String> map=new HashMap<>();
    public String encode(String url)
    {
        map.put(url.hashCode(),url);
        return "http://tinyurl.com/"+url.hashCode();
    }
    public String decode(String url)
    {
        url=url.replaceAll("http://tinyurl.com","");
        return map.get(url.hashCode());
    }
}
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */
class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        // only push the old minimum value when the current
        // minimum value changes after pushing the new value x
        if(x <= min){
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value,
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
public class AmazonPrep
{
    /*
    Jewels and Stone
    You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

    The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

    Example 1:

    Input: J = "aA", S = "aAAbbbb"
    Output: 3
    Example 2:

    Input: J = "z", S = "ZZ"
    Output: 0
     */
    public int numJewelsInStone(String S,String J)
    {
        Set<Character> set=new HashSet<>();
        for(char c: J.toCharArray())
            set.add(c);
        int count=0;
        for(char c: S.toCharArray())
        {
            if(set.contains(c))
                count++;
        }
        return count;
    }
    /*
    Merge Binary trees
    Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

    You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

    Example 1:

    Input:
        Tree 1                     Tree 2
              1                         2
             / \                       / \
            3   2                     1   3
           /                           \   \
          5                             4   7
    Output:
    Merged tree:
             3
            / \
           4   5
          / \   \
         5   4   7
     */
    public Node merge(Node root1,Node root2)
    {
        if(root1==null && root2==null)
            return null;
        else if(root1==null)
            return root2;
        else if(root2==null)
            return root1;
        else
        {
            Node root=new Node(root1.data+root2.data);
            root.left=merge(root1.left,root2.left);
            root.right=merge(root1.right,root2.right);
            return root;
        }
    }
    /*
    Reverse a singly linked list.

    Example:

    Input: 1->2->3->4->5->NULL
    Output: 5->4->3->2->1->NULL
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
    /*
    valid anagram
    Given two strings s and t , write a function to determine if t is an anagram of s.

    Example 1:

    Input: s = "anagram", t = "nagaram"
    Output: true
    Example 2:

    Input: s = "rat", t = "car"
    Output: false
     */
    public boolean isAnagram(String s,String t)
    {
        int[] freq=new int[26];
        for(char c:s.toCharArray())
            freq[c-'a']++;
        for(char c: t.toCharArray())
            freq[c-'a']--;
        for(int i=0;i<26;i++)
        {
            if(freq[i]>0)
                return false;
        }
        return true;
    }
    /*
    Two sum: sorted array
    Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

    The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

    Note:

    Your returned answers (both index1 and index2) are not zero-based.
    You may assume that each input would have exactly one solution and you may not use the same element twice.
    Example:

    Input: numbers = [2,7,11,15], target = 9
    Output: [1,2]
    Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
     */
    public int[] twoSum(int[] arr,int target)
    {
        int l=0,r=arr.length-1;
        while(l<r)
        {
            int val=arr[l]+arr[r];
            if(val==target)
                return new int[]{l,r};
            else if(val<target)
                l++;
            else
                r--;
        }
        return new int[]{-1,-1};
    }
    /*
    First Unique Character in string
    Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

    Examples:

    s = "leetcode"
    return 0.

    s = "loveleetcode",
    return 2.
     */
    public int firstUniqueChar(String s)
    {
        int[] freq=new int[128];
        for(char c: s.toCharArray())
            freq[c-'a']++;
        for(int i=0;i<s.length();i++)
            if(freq[s.charAt(i)-'a']==1)
                return i;
        return -1;
    }
    /*
    Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

    Example:

    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4
     */
    public ListNode merge(ListNode l1, ListNode l2)
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
                l1.next=merge(l1.next,l2);
                return l1;
            }
            else
                l2.next=merge(l1,l2.next);
                return l2;
        }
    }
    /*
    Best time to buy and sell stocks
    Say you have an array for which the ith element is the price of a given stock on day i.

    If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

    Note that you cannot sell a stock before you buy one.

    Example 1:

    Input: [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
                 Not 7-1 = 6, as selling price needs to be larger than buying price.
    Example 2:

    Input: [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */
    public int maxProfit(int[] prices)
    {
        int min_price=Integer.MAX_VALUE, max_profit=Integer.MIN_VALUE;
        for(int price:prices)
        {
            if(price<min_price)
                min_price=price;
            else
                max_profit=Math.max(max_profit,price-min_price);
        }
        return max_profit;
    }
    /*
    Min Cost Climbing Stairs
    On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

    Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

    Example 1:
    Input: cost = [10, 15, 20]
    Output: 15
    Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
    Example 2:
    Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
    Output: 6
    Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
     */
    public int minCostClimbingStairs(int[] cost)
    {
        int f1=0,f2=0;
        for(int c:cost)
        {
            int f0=c+Math.min(f1,f2);
            f1=f2;
            f2=f0;
        }
        return Math.min(f1,f2);
    }
    /*
    LCA of Binary search tree
    Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
    Example 1:

    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
    Output: 6
    Explanation: The LCA of nodes 2 and 8 is 6.
    Example 2:

    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
    Output: 2
    Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
     */
    public TreeNode lcaBST(TreeNode root,TreeNode p,TreeNode q)
    {
        if(root==null)
            return null;
        if(p==null && q==null)
            return root;
        while(root!=null)
        {
            if(root.val<p.val && root.val<q.val)
                root=root.right;
            else if(root.val>p.val && root.val>q.val)
                root=root.left;
            else
                return root;
        }
        return null;
    }
    /*
    Two Sum Standard
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:

    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
     */
    public int[] twoSum1(int[] arr,int target)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++)
        {
            int comp=target-arr[i];
            if(map.containsKey(comp))
                return new int[]{i,map.get(comp)};
            map.put(arr[i],i);
        }
        return new int[]{-1,-1};
    }
    /*
    Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

    Note that the row index starts from 0.

    In Pascal's triangle, each number is the sum of the two numbers directly above it.

    Example:

    Input: 3
    Output: [1,3,3,1]
            1
           1,1
          1,2,1
         1,3,3,1
     */
    public List<Integer> getRow(int row)
    {
        List<Integer> prev=new ArrayList<>();
        int i=0;
        while(i<=row)
        {
            List<Integer> curr=new ArrayList<>();
            for(int j=0;j<i+1;j++)
            {
                if(j==0 || j==i)
                    curr.add(1);
                else
                    curr.add(prev.get(j)+prev.get(j-1));
            }
            prev=curr;
            i++;
        }
        return prev;
    }
    /*
    Given a linked list, determine if it has a cycle in it.

    To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.


    Follow up:

    Can you solve it using O(1) (i.e. constant) memory?
     */
    public boolean isCycle(ListNode head)
    {
        ListNode fast=head,slow=head;
        while(fast!=null && fast.next!=null)
        {
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow)
                return true;
        }
        return false;
    }
    /*
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Note that an empty string is also considered valid.

    Example 1:

    Input: "()"
    Output: true
    Example 2:

    Input: "()[]{}"
    Output: true
    Example 3:

    Input: "(]"
    Output: false
    Example 4:

    Input: "([)]"
    Output: false
    Example 5:

    Input: "{[]}"
    Output: true
     */
    public boolean isValid(String s)
    {
        Stack<Character> st=new Stack<>();
        for(char c: s.toCharArray())
        {
            if(c=='(')
                st.push(')');
            else if(c=='{')
                st.push('}');
            else if(c=='[')
                st.push(']');
            else if(s.isEmpty() || c!=st.pop())
                return false;
        }
        return st.isEmpty();
    }
    /*
    Palindrome Linked List
    Given a singly linked list, determine if it is a palindrome.

    Example 1:

    Input: 1->2
    Output: false
    Example 2:

    Input: 1->2->2->1
    Output: true
    Follow up:
    Could you do it in O(n) time and O(1) space?
     */
    public boolean isPalindrome(ListNode head)
    {
        ListNode fast=head,slow=head;
        while(fast!=null && fast.next!=null)
        {
            fast=fast.next.next;
            slow=slow.next;
        }
        if(fast!=null)
            slow=slow.next;
        slow=reverse(slow);
        fast=head;
        while(slow!=null)
        {
            if(fast.data!=slow.data)
                return false;
            slow=slow.next;
            fast=fast.next;
        }
        return true;
    }
    /*
    Intersection of Two Linked List




     */
    public ListNode getIntersection(ListNode headA, ListNode headB)
    {
        int lenA=length(headA),lenB=length(headB);
        while(lenA>lenB)
        {
            headA=headA.next;
            lenA--;
        }
        while(lenB>lenA)
        {
            headB=headB.next;
            lenB--;
        }
        while(lenA>=0)
        {
            if(headA==headB)
                return headA;
            headA=headA.next;
            headB=headB.next;
            lenA--;
        }
        return null;
    }
    public int length(ListNode head)
    {
        int count=0;
        while(head!=null)
        {
            head=head.next;
            count++;
        }
        return count;
    }
    /*
    Rotate a 1D array
    Given an array, rotate the array to the right by k steps, where k is non-negative.

    Example 1:

    Input: [1,2,3,4,5,6,7] and k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4]
    Example 2:

    Input: [-1,-100,3,99] and k = 2
    Output: [3,99,-1,-100]
    Explanation:
    rotate 1 steps to the right: [99,-1,-100,3]
    rotate 2 steps to the right: [3,99,-1,-100]
     */
    public void rotate(int[] nums,int k)
    {
        reverse(nums,0,nums.length-1);
        int val=k%nums.length;
        reverse(nums,0,val-1);
        reverse(nums,val,nums.length);
    }
    public void reverse(int[] nums,int l,int r)
    {
        while(l<r)
        {
            int temp=nums[l];
            nums[l]=nums[r];
            nums[r]=temp;
            l++;
            r--;
        }
    }
    /*
    Count the number of prime numbers less than a non-negative number, n.

    Example:

    Input: 10
    Output: 4
    Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
     */
    public int countPrimes(int n)
    {
        boolean[] isPrime=new boolean[n];
        for(int i=2;i<n;i++)
            isPrime[i]=true;
        for(int i=2;i*i<n;i++)
        {
            if(!isPrime[i])
                continue;
            for(int j=i*i;j<n;j+=i)
            {
                isPrime[j]=false;
            }
        }
        int cnt=0;
        for(int i=2;i<n;i++)
            if(isPrime[i]) cnt++;
        return cnt;
    }
    /*
    Partition Labels
    A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

    Example 1:
    Input: S = "ababcbacadefegdehijhklij"
    Output: [9,7,8]
    Explanation:
    The partition is "ababcbaca", "defegde", "hijhklij".
    This is a partition so that each letter appears in at most one part.
    A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
     */
    public List<Integer> partitionLables(String s)
    {
        int[] last=new int[26];
        for(int i=0;i<s.length();i++)
            last[s.charAt(i)-'a']=i;
        List<Integer> result=new ArrayList<>();
        int count=0,l=0;
        for(int i=0;i<s.length();i++)
        {
            count++;
            l=Math.max(l,last[s.charAt(i)-'a']);
            if(l==i)
            {
                result.add(count);
                count=0;
            }
        }
        return result;
    }
    /*
    Product of arrays except self
    Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

    Example:

    Input:  [1,2,3,4]
    Output: [24,12,8,6]
     */
    public int[] productExceptSelf(int[] nums)
    {
        int left=1;
        int[] ret=new int[nums.length];
        for(int i=0;i<nums.length;i++)
        {
            ret[i]=left;
            left=left*nums[i];
        }
        int right=1;
        for(int i=nums.length-1;i>=0;i--)
        {
            ret[i]=ret[i]*right;
            right=right*nums[i];
        }
        return ret;
    }
    /*
    Subsets
    Given a set of distinct integers, nums, return all possible subsets (the power set).

    Note: The solution set must not contain duplicate subsets.

    Example:

    Input: nums = [1,2,3]
    Output:
    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]
     */
    public List<List<Integer>> subsets(int[] nums)
    {
        List<List<Integer>> result=new ArrayList<>();
        backtrack(result,nums,0,new ArrayList<>());
        return result;
    }
    public void backtrack(List<List<Integer>> result,int[] nums,int start,List<Integer> temp)
    {
        result.add(new ArrayList<>(temp));
        for(int i=start;i<nums.length;i++)
        {
            temp.add(nums[i]);
            backtrack(result,nums,i+1,temp);
            temp.remove(temp.size()-1);
        }
    }
    /*
    Dry Run needed Rotate a matrix
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
    public void rotate(int[][] mat)
    {
        if(mat.length==0 || mat.length!=mat[0].length)
            return;
        int length=mat.length;
        for(int i=0;i<length/2;i++)
        {
            int first=i;
            int last=length-i-1;
            for(int j=first;j<last;j++)
            {
                int offset=j-first;
                int temp=mat[first][j];
                mat[first][j]=mat[last-offset][first];
                mat[last-offset][first]=mat[last][last-offset];
                mat[last][last-offset]=mat[j][last];
                mat[j][last]=temp;
            }
        }
    }
    /*
    Level order traversal binary tree
    Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

    For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its level order traversal as:
    [
      [3],
      [9,20],
      [15,7]
    ]
     */
    public List<List<Integer>> levelOrder(TreeNode root)
    {
        List<List<Integer>> result=new ArrayList<>();
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty())
        {
            int size=q.size();
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                TreeNode curr=q.poll();
                temp.add(curr.val);
                if(curr.left!=null)
                    q.offer(curr.left);
                if(curr.right!=null)
                    q.offer(curr.right);
            }
            result.add(new ArrayList(temp));
        }
        return result;
    }
    /*
    Binary Tree right side view
    Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

    Example:

    Input: [1,2,3,null,5,null,4]
    Output: [1, 3, 4]
    Explanation:

       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---
     */
    public List<Integer> rightSideView(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        helper(root,0,result);
        return result;
    }
    public void helper(TreeNode root,int depth,List<Integer> result)
    {
        if(root==null)
            return;
        if(depth==result.size())
            result.add(root.val);
        helper(root.right,depth+1,result);
        helper(root.left,depth+1,result);
    }
    /*
    Kth Largest(QuickSelect)
    Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

    Example 1:

    Input: [3,2,1,5,6,4] and k = 2
    Output: 5
    Example 2:

    Input: [3,2,3,1,2,4,5,5,6] and k = 4
    Output: 4
    Note:
    You may assume k is always valid, 1 ≤ k ≤ array's length.
    */
    public int findkthLargest(int[] nums,int k)
    {
        return quickSelect(nums,nums.length-k,0,nums.length-1);
    }
    public void swap(int[] nums,int l,int r)
    {
        int temp=nums[l];
        nums[l]=nums[r];
        nums[r]=temp;
    }
    public int partition(int[] nums,int left,int right)
    {
        int pivot_val=nums[right];
        int si=left;
        for(int i=left;i<=right;i++)
        {
            if(nums[i]<pivot_val)
            {
                swap(nums,si,i);
                si++;
            }
        }
        swap(nums,si,right);
        return si;
    }
    public int quickSelect(int[] nums,int k,int left,int right)
    {
        int p=partition(nums,left,right);
        if(p==k)
            return nums[p];
        else if(p<k)
            return quickSelect(nums,k,p+1,right);
            else
                return quickSelect(nums,k,left,p-1);
    }
    /*
    Group Anagrams
    Given an array of strings, group anagrams together.

    Example:

    Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Output:
    [
      ["ate","eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]
    */
    public List<List<String>> groupAnagrams(String[] str)
    {
        Map<String,ArrayList<String>> map=new HashMap<>();
        for(String s:str)
        {
            char[] c=s.toCharArray();
            Arrays.sort(c);
            String key=String.valueOf(c);
            if(!map.containsKey(key))
                map.put(key,new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
    /*
    Gray Code
    The gray code is a binary numeral system where two successive values differ in only one bit.

    Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

    Example 1:

    Input: 2
    Output: [0,1,3,2]
    Explanation:
    00 - 0
    01 - 1
    11 - 3
    10 - 2

    For a given n, a gray code sequence may not be uniquely defined.
    For example, [0,2,3,1] is also a valid gray code sequence.

    00 - 0
    10 - 2
    11 - 3
    01 - 1
    Example 2:

    Input: 0
    Output: [0]
    Explanation: We define the gray code sequence to begin with 0.
                 A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
                 Therefore, for n = 0 the gray code sequence is [0].
     */
    public List<Integer> grayCode(int n)
    {
        List<Integer> result=new ArrayList<>();
        result.add(0);
        for(int i=0;i<n;i++)
        {
            int size=result.size();
            for(int j=size-1;j>=0;j--)
                result.add(result.get(j)|1<<i);
        }
        return result;
    }
    /*
    Letter Combination of a phone number
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

    Example:

    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
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
        List<String> result=new ArrayList<>();
        compute(0,"",digits,keypad,result);
        return result;
    }
    public void compute(int offset,String prefix,String digits,Map<Character,String> map,List<String> result)
    {
        if(offset==digits.length())
        {
            result.add(prefix);
            return;
        }
        String s=map.get(digits.charAt(offset));
        for(int i=0;i<s.length();i++)
            compute(offset+1,prefix+s.charAt(i),digits,map,result);
    }
    /*
    Count number of islands
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
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        int count=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='1' && !visited[i][j])
                {
                    dfs(i,j,visited,grid);
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(int i,int j,boolean[][] visited,char[][] grid)
    {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || visited[i][j] || grid[i][j]!='1')
            return;
        visited[i][j]=true;
        dfs(i+1,j,visited,grid);
        dfs(i-1,j,visited,grid);
        dfs(i,j+1,visited,grid);
        dfs(i,j-1,visited,grid);
    }
    /*
    Search a 2d Matrix
    Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.
    Example:

    Consider the following matrix:

    [
      [1,   4,  7, 11, 15],
      [2,   5,  8, 12, 19],
      [3,   6,  9, 16, 22],
      [10, 13, 14, 17, 24],
      [18, 21, 23, 26, 30]
    ]
    Given target = 5, return true.

    Given target = 20, return false.
     */
    public boolean searchMatrix(int[][] mat,int target)
    {
        int col=0,row=mat.length-1;
        while(row>=0 && col<mat[0].length)
        {
            if(mat[row][col]<target)
                col++;
            else if(mat[row][col]>target)
                row--;
            else
                return true;
        }
        return false;
    }
    /*
    Set Matrix Zero
    Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

    Example 1:

    Input:
    [
      [1,1,1],
      [1,0,1],
      [1,1,1]
    ]
    Output:
    [
      [1,0,1],
      [0,0,0],
      [1,0,1]
    ]
    Example 2:

    Input:
    [
      [0,1,2,0],
      [3,4,5,2],
      [1,3,1,5]
    ]
    Output:
    [
      [0,0,0,0],
      [0,4,5,0],
      [0,3,1,0]
    ]
     */
    public void setZeroes(int[][] matrix)
    {
        boolean isRowZero=false,isColumnZero=false;
        for(int i=0;i<matrix.length;i++)
            if(matrix[i][0]==0)
                isRowZero=true;

        for(int i=0;i<matrix[0].length;i++)
            if(matrix[0][i]==0)
                isColumnZero=true;

        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                if(matrix[i][j]==0)
                {
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        for(int i=1;i<matrix.length;i++)
        {
            for(int j=1;j<matrix[0].length;j++)
            {
                if(matrix[i][0]==0 || matrix[0][j]==0)
                    matrix[i][j]=0;
            }
        }
        if(isRowZero)
            for(int i=0;i<matrix.length;i++)
                matrix[i][0]=0;

        if(isColumnZero)
            for(int i=0;i<matrix[0].length;i++)
                matrix[0][i]=0;

    }
    /*
    LCA of Binary Tree
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
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q)
    {
        if(root==null)
            return null;
        if(root==p || root==q)
            return root;
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null && right!=null)
            return root;
        return left!=null?left:right;

    }
    /*
    Word Break
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
    public boolean wordBreakBFS(String str,Set<String> dict)
    {
        boolean[] visited=new boolean[str.length()];
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        while(!q.isEmpty())
        {
            int i=q.poll();
            if(!visited[i])
            {
                for(int j=i+1;j<=str.length();j++)
                {
                    if(dict.contains(str.substring(i,j)))
                    {
                        q.offer(j);
                        if(j==str.length())
                            return true;
                    }
                }
            }
            visited[i]=true;
        }
        return false;
    }
    public boolean wordBreakMemo(String s,Set<String> dict)
    {
        boolean[] visited=new boolean[s.length()];
        return wordBreakHelper( s, 0, dict, visited);
    }
    public boolean wordBreakHelper(String s,int start,Set<String> dict,boolean[] visited)
    {
        if(start==s.length())
            return true;
        if(visited[start])
            return true;
        for(int end=start+1;end<=s.length();end++)
        {
            if(dict.contains(s.substring(start,end)) && wordBreakHelper(s,end,dict,visited))
            {
                visited[start]=true;
                return true;
            }
        }
        visited[start]=false;
        return false;
    }
    /*
    Add Two Numbers represented by a linked list
    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example:

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.
     */
    public ListNode addTwoNumbers(ListNode l1,ListNode l2)
    {
        return addListNode(l1,l2,0);
    }
    public ListNode addListNode(ListNode l1,ListNode l2,int carry)
    {
        if(l1 ==null && l2==null && carry==0)
            return null;
        int sum=carry;
        sum+=l1==null?0:l1.data;
        sum+=l2==null?0:l2.data;
        ListNode head=new ListNode(sum%10);
        head.next=addListNode(l1==null?null:l1,l2==null?null:l2,sum/10);
        return head;
    }
    /*
    Longest Substring without repeating characters
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
        Set<Character> set=new HashSet<>();
        int i=0,j=0,ans=0;
        while(i<s.length() && j<s.length())
        {
            if(!set.contains(s.charAt(j)))
            {
                set.add(s.charAt(j));
                j++;
                ans=Math.max(ans,j-i);
            }
            else
            {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return ans;
    }
    /*
    Longest Palindromic Substring
    Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

    Example 1:

    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
    Example 2:

    Input: "cbbd"
    Output: "bb"
     */
    public String longestPalindrome(String s)
    {
        String res="";
        for(int i=0;i<s.length();i++)
        {
            int len1=expand(s,i,i);
            int len2=expand(s,i,i+1);
            int len=Math.max(len1,len2);
            if(len>res.length())
            {
                int start=i-(len-1)/2;
                int end=i+len/2;
                res=s.substring(start,end+1);
            }
        }
        return res;
    }
    public int expand(String s,int l,int r)
    {
        while(l>0 && r<s.length() && s.charAt(l)==s.charAt(r))
        {
            l--;
            r++;
        }
        return r-l- 1;
    }
    /*
    Trapping Rain Water
    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


    The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

    Example:

    Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
     */
    public int trap(int[] nums)
    {
        int l=0,r=nums.length-1,sum=0,lmax=0,rmax=0;
        while(l<r)
        {
            lmax=Math.max(lmax,nums[l]);
            rmax=Math.max(rmax,nums[r]);
            if(lmax<rmax)
            {
                sum+=lmax-nums[l];
                l++;
            }
            else
            {
                sum+=rmax-nums[r];
                r--;
            }
        }
        return sum;
    }
    /*
    Sliding Window maximum
    Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

    Example:

    Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
    Output: [3,3,5,5,6,7]
    Explanation:

    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
    Note:
    You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

    Follow up:
     */

    public int[] maxSlidingWindow(int[] nums,int k)
    {
        int[] max_left=new int[nums.length];
        int[] max_right=new int[nums.length];
        max_left[0]=nums[0];
        max_right[nums.length-1]=nums[nums.length-1];

        for(int i=1;i<nums.length;i++)
        {
            max_left[i]=i%k==0?nums[i]:Math.max(nums[i],max_left[i-1]);
        }
        for(int i=nums.length-2;i>=0;i--)
        {
            max_right[i]=i%k==0?nums[i]:Math.max(max_right[i+1],nums[i]);
        }

        int[] res=new int[nums.length-k+1];
        for(int i=0,j=0;i+k<=nums.length;i++)
            res[j++]=Math.max(max_right[i],max_left[i+k-1]);
        return res;
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
        return merge(lists,0,lists.length-1);
    }
    public ListNode merge(ListNode[] lists, int start, int end)
    {
        if(start==end)
            return lists[start];
        if(start>end)
            return null;
        int mid=(start+end)/2;
        ListNode l1=merge(lists,start,mid);
        ListNode l2=merge(lists,mid+1,end);
        return merge(l1,l2);
    }
    /*
    Copy List with a random pointer

     */
    class RandomNode
    {
        public int val;
        public RandomNode next;
        public RandomNode random;

        public RandomNode() {}
        public RandomNode(int val)
        {
            this.val=val;
        }

        public RandomNode(int _val,RandomNode _next,RandomNode _random)
        {
            val = _val;
            next = _next;
            random = _random;
        }
    }
    public RandomNode copyRandomList(RandomNode head)
    {
        Map<RandomNode,RandomNode> map=new HashMap<>();
        RandomNode node=head;
        while(node!=null)
        {
            map.put(node,new RandomNode(node.val));
            node=node.next;
        }
        node=head;
        while(node!=null)
        {
            map.get(node).next=map.get(node.next);
            map.get(node).random=map.get(node.random);
            node=node.next;
        }
        return map.get(head);
    }
    /*
    Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

    The cache is initialized with a positive capacity.

    Follow up:
    Could you do both operations in O(1) time complexity?

    Example:

    LRUCache cache = new LRUCache( 2  capacity  );

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4
     */
    class LRUNode
    {
        int val,key;
        LRUNode next,prev;
        public LRUNode(int key,int val)
        {
            this.key=key;
            this.val=val;
        }
    }
    class LRU
    {
        Map<Integer,LRUNode> map=new HashMap<>();
        int capacity, count;
        LRUNode tail,head;
        public LRU(int capacity)
        {
            this.capacity=capacity;
            count=0;
            head=new LRUNode(0,0);
            tail=new LRUNode(0,0);
            head.next=tail;
            tail.prev=head;
            head.prev=null;
            tail.next=null;
        }
        public void delete(LRUNode node)
        {
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }
        public void addToHead(LRUNode node)
        {
            node.next=head.next;
            node.next.prev=node;
            node.prev=head;
            head.next=node;
        }

        public int get(int key)
        {
            if(!map.containsKey(key))
                return -1;
            LRUNode node=map.get(key);
            delete(node);
            addToHead(node);
            return node.val;
        }
        public void put(int key,int val)
        {
            if(map.containsKey(key))
            {
                LRUNode node=map.get(key);
                node.val=val;
                delete(node);
                addToHead(node);
            }
            else
            {
                LRUNode node=new LRUNode(key,val);
                if(count<capacity)
                {
                    map.put(key,node);
                    addToHead(node);
                    count++;
                }
                else
                {
                    map.remove(tail.prev.key);
                    delete(tail.prev);
                    addToHead(node);
                    map.put(key,node);

                }
            }
        }
    }
    /*
    3Sum
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
    public List<List<Integer>> threeSum(int[] nums, int target)
    {
        Arrays.sort(nums);
        Set<List<Integer>> result=new HashSet<>();
        for(int i=0;i<nums.length;i++)
        {
            int j=i+1,k=nums.length-1;
            while(j<k)
            {
                int val=nums[i]+nums[j]+nums[k];
                if(val==target)
                {
                    result.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                }
                else if(val>target)
                {
                    k--;
                }
                else
                    j++;
            }

        }
        return new ArrayList<>(result);
    }
    /*
    Word Ladder 1
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

    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.     */
    public boolean canUse(String word, String target)
    {
        int i=0;
        for(int k=0;k<word.length();k++)
        {
            if(word.charAt(k)!=target.charAt(k))
                i++;
        }
        return i==1;
    }
    public int ladderLength(String word, String target, Set<String> dict)
    {
        if(!dict.contains(target))
            return 0;
        Queue<String> q=new LinkedList<>();
        Set<String> visited=new HashSet<>();
        q.offer(word);
        int count=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String curr_word=q.poll();
                if(curr_word.equals(target))
                    return count+1;
                visited.add(curr_word);
                for(String w:dict)
                {
                    if(canUse(curr_word,w) && !visited.contains(w))
                    {
                        q.offer(w);
                    }
                }
            }
            count++;
        }
        return 0;
    }
    /*
    Word Ladder 2
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
    public List<String> canUse(String word,Set<String> dict)
    {
        List<String> sol=new ArrayList<>();
        for(String w:dict)
        {
            int i=0;
            for(int k=0;k<w.length();k++)
                if(word.charAt(k)!=w.charAt(k))
                    i++;
            if(i==1)
                sol.add(w);

        }
        return sol;
    }
    public List<List<String>> wordladder(String word, String target, Set<String> dict)
    {
        if(!dict.contains(target))
            return new ArrayList<>();
        Map<String,List<String>> adjList=new HashMap<>();
        Map<String,Integer> distance=new HashMap<>();
        wordLadderBFS(word,target,dict,adjList,distance);
        List<List<String>> result=new ArrayList<>();
        wordLadderDFS(word,target,dict,adjList,distance,new ArrayList<>(),result);
        return result;
    }
    public void wordLadderBFS(String word, String target, Set<String> dict, Map<String,List<String>> adjList,Map<String,Integer> distance)
    {
        for(String w:dict)
            adjList.put(w,new ArrayList<>());
        if(!adjList.containsKey(word))
            adjList.put(word,new ArrayList<>());
        Queue<String> q=new LinkedList<>();
        q.offer(word);
        distance.put(word,0);
        while(!q.isEmpty())
        {
            int size=q.size();
            boolean found=false;
            for(int i=0;i<size;i++)
            {
                String curr_word=q.poll();
                int currDistance=distance.get(curr_word);
                List<String> neighbors=canUse(curr_word,dict);
                for(String w:neighbors)
                {
                    adjList.get(curr_word).add(w);
                    if(!distance.containsKey(w))
                    {
                        distance.put(w,currDistance+1);
                        if(w.equals(target))
                            found=true;
                        else
                            q.offer(w);

                    }
                }
            }
            if(found)
                break;
        }
    }
    public void wordLadderDFS(String word, String target,Set<String> dict,Map<String,List<String>> adjList,Map<String,Integer> distance, List<String> temp,List<List<String>> result)
    {
        temp.add(word);
        if(word.equals(target))
            result.add(new ArrayList<>(temp));
        int curr_dist=distance.get(word);
        for(String s:adjList.get(word))
        {
            if(distance.get(s)==curr_dist+1)
            {
                wordLadderDFS(s,target,dict,adjList,distance,temp,result);
            }
        }
        temp.remove(temp.size()-1);

    }
    /*
    Single Element in a sorted array
    Given a sorted array consisting of only integers where every element appears exactly twice except for one element which appears exactly once. Find this single element that appears only once.
    Example 1:

    Input: [1,1,2,3,3,4,4,8,8]
    Output: 2
    Example 2:

    Input: [3,3,7,7,10,11,11]
    Output: 10


    Note: Your solution should run in O(log n) time and O(1) space.
     */
    public int singleNonDuplicate(int[] nums)
    {
        int lo=0,hi=nums.length/2;
        while(lo<hi)
        {
            int mid=(lo+hi)/2;
            if(nums[2*mid]!=nums[2*mid+1])
                hi=mid;
            else
                lo=mid+1;
        }
        return nums[2*lo];
    }

    /*
    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

    (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

    You are given a target value to search. If found in the array return its index, otherwise return -1.

    You may assume no duplicate exists in the array.

    Your algorithm's runtime complexity must be in the order of O(log n).

    Example 1:

    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4
    Example 2:

    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1
     */

    public int search(int[] nums, int target)
    {
        if(nums.length==0)
            return -1;
        if(nums.length==1 && nums[0]==target)
            return 0;
        if(nums.length==1 && nums[0]!=target)
            return -1;
        int pivot=findPivot(nums,0,nums.length-1);
        if(pivot==0)
            return binary(nums,0,nums.length-1,target);


        if(target<nums[0])
            return binary(nums,pivot,nums.length-1,target);
        else
            return binary(nums,0,pivot,target);

    }
    public int findPivot(int[] nums,int l, int r)
    {
        if(nums[l]<nums[r])
            return 0;
        while(l<=r)
        {
            int mid=(l+r)/2;
            if(nums[mid]>nums[mid+1])
                return mid+1;
            else
            {
                if(nums[mid]<nums[l])
                    r=mid-1;
                else
                    l=mid+1;
            }
        }
        return 0;
    }
    public int binary(int[] nums,int l, int r, int target)
    {
        while(l<=r)
        {
            int mid=(l+r)/2;
            if(nums[mid]==target)
                return mid;
            else
            {
                if(target<nums[mid])
                    r=mid-1;
                else
                    l=mid+1;
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        AmazonPrep obj=new AmazonPrep();
        System.out.println(obj.search(new int[]{4,5,6,7,0,1,2},3));
    }
}
