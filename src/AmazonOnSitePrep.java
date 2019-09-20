import java.util.*;

/*
    Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

    The cache is initialized with a positive capacity.

    Follow up:
    Could you do both operations in O(1) time complexity?

    Example:

    LRUCache cache = new LRUCache( 2  );

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
class LRUCache
{
    private class Node
    {
        int key,val;
        Node next,prev;
        public Node(int key,int val)
        {
            this.key=key;
            this.val=val;
        }
    }
    Map<Integer,Node> map;
    Node tail,head;
    int capacity;
    int count=0;
    public LRUCache(int c)
    {
        capacity=c;
        map=new HashMap<>();
        tail=new Node(0,0);
        head=new Node(0,0);
        tail.prev=head;
        head.next=tail;
        tail.next=null;
        head.prev=null;
    }
    public void addToHead(Node node)
    {
        node.next=head.next;
        head.next=node;
        node.prev=head;
        node.next.prev=node;
    }
    public void delete(Node node)
    {
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    public int get(int key)
    {
        if(!map.containsKey(key))
            return -1;
        else
        {
            Node node=map.get(key);
            delete(node);
            addToHead(node);
            return node.val;
        }
    }
    public void put(int key,int val)
    {
        if(map.containsKey(key))
        {
            Node node=map.get(key);
            node.val=val;
            delete(node);
            addToHead(node);
        }
        else
        {
            if(count<capacity)
            {
                Node node=new Node(key,val);
                map.put(key,node);
                addToHead(node);
                count++;
            }
            else
            {
                map.remove(tail.prev.key);
                delete(tail.prev);
                Node node=new Node(key,val);
                map.put(key,node);
                addToHead(node);
            }
        }
    }
}
/*
    Design a Tic-tac-toe game that is played between two players on a n x n grid.

    You may assume the following rules:

    A move is guaranteed to be valid and is placed on an empty block.
    Once a winning condition is reached, no more moves is allowed.
    A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
    Example:
    Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

    TicTacToe toe = new TicTacToe(3);

    toe.move(0, 0, 1); -> Returns 0 (no one wins)
    |X| | |
    | | | |    // Player 1 makes a move at (0, 0).
    | | | |

    toe.move(0, 2, 2); -> Returns 0 (no one wins)
    |X| |O|
    | | | |    // Player 2 makes a move at (0, 2).
    | | | |

    toe.move(2, 2, 1); -> Returns 0 (no one wins)
    |X| |O|
    | | | |    // Player 1 makes a move at (2, 2).
    | | |X|

    toe.move(1, 1, 2); -> Returns 0 (no one wins)
    |X| |O|
    | |O| |    // Player 2 makes a move at (1, 1).
    | | |X|

    toe.move(2, 0, 1); -> Returns 0 (no one wins)
    |X| |O|
    | |O| |    // Player 1 makes a move at (2, 0).
    |X| |X|

    toe.move(1, 0, 2); -> Returns 0 (no one wins)
    |X| |O|
    |O|O| |    // Player 2 makes a move at (1, 0).
    |X| |X|

    toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
    |X| |O|
    |O|O| |    // Player 1 makes a move at (2, 1).
    |X|X|X|
 */
class TicTacToe
{
    int[] row;
    int[] col;
    int diag;
    int antiDiag;
    int size;
    public TicTacToe(int n)
    {
        row=new int[n];
        col=new int[n];
        diag=0;
        antiDiag=0;
        size=n;
    }
    public int move(int r, int c, int player)
    {
        int val=player==1?1:-1;
        row[r]+=val;
        col[c]+=val;
        if(r==c)
            diag+=val;
        if(c==(size-1-r))
            antiDiag+=val;
        if(Math.abs(row[r])==size || Math.abs(col[c])==size || Math.abs(diag)==size || Math.abs(antiDiag)==size)
            return player;
        return 0;
    }
}
/*
    Implement FreqStack, a class which simulates the operation of a stack-like data structure.

    FreqStack has two functions:

    push(int x), which pushes an integer x onto the stack.
    pop(), which removes and returns the most frequent element in the stack.
    If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.


    Example 1:

    Input:
    ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
    [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
    Output: [null,null,null,null,null,null,null,5,7,5,4]
    Explanation:
    After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

    pop() -> returns 5, as 5 is the most frequent.
    The stack becomes [5,7,5,7,4].

    pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
    The stack becomes [5,7,5,4].

    pop() -> returns 5.
    The stack becomes [5,7,4].

    pop() -> returns 4.
    The stack becomes [5,7].
 */
class FreqStack
{
    int maxFreq=0;
    Map<Integer,Integer> freq;
    Map<Integer,Stack<Integer>> map;
    public FreqStack()
    {
        freq=new HashMap<>();
        map=new HashMap<>();
    }
    public void push(int x)
    {
        freq.put(x,freq.getOrDefault(x,0)+1);
        int f=freq.get(x);
        maxFreq=Math.max(f,maxFreq);
        if(!map.containsKey(f))
            map.put(f,new Stack<>());
        map.get(f).push(x);
    }
    public int pop()
    {
        int ret=map.get(maxFreq).pop();
        freq.put(maxFreq,freq.get(maxFreq)-1);
        if(map.get(maxFreq).size()==0)
            maxFreq--;
        return ret;
    }
}
/*
    Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

    For example,
    [2,3,4], the median is 3

    [2,3], the median is (2 + 3) / 2 = 2.5

    Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.


    Example:

    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3)
    findMedian() -> 2


    Follow up:

    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
class MedianFinder
{
    PriorityQueue<Integer> maxHeap=new PriorityQueue<>();
    PriorityQueue<Integer> minHeap=new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2)
        {
            return o2-o1;
        }
    });
    public MedianFinder()
    {}
    public void addNum(int i)
    {
        maxHeap.offer(i);
        minHeap.offer(maxHeap.poll());
        if(maxHeap.size()<minHeap.size())
            maxHeap.offer(minHeap.poll());
    }
    public double findMedian()
    {
        return maxHeap.size()>minHeap.size()?maxHeap.peek():(minHeap.peek()+maxHeap.peek())/2.0;
    }
}
/*
      Design Search Autocomplete System
      Go to Discuss
    Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

    The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
    The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
    If less than 3 hot sentences exist, then just return as many as you can.
    When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
    Your job is to implement the following functions:

    The constructor function:

    AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

    Now, the user wants to input a new sentence. The following function will provide the next character the user types:

    List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


    Example:
    Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
    The system have already tracked down the following sentences and their corresponding times:
    "i love you" : 5 times
    "island" : 3 times
    "ironman" : 2 times
    "i love leetcode" : 2 times
    Now, the user begins another search:

    Operation: input('i')
    Output: ["i love you", "island","i love leetcode"]
    Explanation:
    There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

    Operation: input(' ')
    Output: ["i love you","i love leetcode"]
    Explanation:
    There are only two sentences that have prefix "i ".

    Operation: input('a')
    Output: []
    Explanation:
    There are no sentences that have prefix "i a".

    Operation: input('#')
    Output: []
    Explanation:
    The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 */
class AutocompleteSystem
{
    private class Trie
    {
        Trie[] next;
        Map<String,Integer> freq;
        public Trie()
        {
            next=new Trie[27];
            freq=new HashMap<>();
        }
    }
    Trie root;
    String prefix="";
    public int toInt(char c)
    {
        return c==' '?26:c-'a';
    }
    public void insert(Trie node,String s, int times)
    {
        for(char c:s.toCharArray())
        {
            if(node.next[toInt(c)]==null)
                node.next[toInt(c)]=new Trie();
            node=node.next[toInt(c)];
            node.freq.put(s,node.freq.getOrDefault(s,0)+1);

        }
    }
    public AutocompleteSystem(String[] sentences, int[] times)
    {
        for(int i=0;i<sentences.length;i++)
            insert(root,sentences[i],times[i]);
    }
    public List<String> input(char c)
    {
        if(c=='#')
        {
            insert(root,prefix,1);
            prefix="";
            return new ArrayList<>();
        }
        else
        {
            prefix+=c;
            Trie node=root;
            for(char ch:prefix.toCharArray())
            {
                if(node.next[toInt(ch)]==null)
                    return new ArrayList<>();
                node=node.next[toInt(ch)];
            }
            PriorityQueue<Map.Entry<String,Integer>> pq=new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2)
                {
                    if(m1.getValue()==m2.getValue())
                        return m1.getKey().compareTo(m2.getKey());
                    else
                        return m2.getValue()-m1.getValue();
                }
            });
            pq.addAll(node.freq.entrySet());
            List<String> res=new ArrayList<>();
            for(int i=0;i<3 && !pq.isEmpty();i++)
                res.add(pq.poll().getKey());
            return res;
        }
    }
}
/*
    Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

    Example:

    You may serialize the following tree:

        1
       / \
      2   3
         / \
        4   5

    as "[1,2,3,null,null,4,5]"
    Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

    Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
class codec
{
    public String serialiaze(TreeNode root)
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
            sb.append(root.val+",");
            buildString(root.left,sb);
            buildString(root.right,sb);
        }
    }
    public TreeNode deserialize(String str)
    {
        String[] s=str.split(",");
        Queue<String> q=new LinkedList<>(Arrays.asList(s));
        return buildTree(q);
    }
    TreeNode buildTree(Queue<String> q)
    {
        if(q.isEmpty())
            return null;
        String s=q.poll();
        TreeNode root=new TreeNode(Integer.parseInt(s));
        if(s=="null")
            return null;
        root.left=buildTree(q);
        root.right=buildTree(q);
        return root;
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
class MinStack_Dup
{
    Stack<Integer> st;
    Stack<Integer> minstack;
    public MinStack_Dup()
    {
        st=new Stack<Integer>();
        minstack=new Stack<Integer>();
    }
    public void push(int x)
    {
        st.push(x);
        if(minstack.isEmpty())
            minstack.push(x);
        else
            minstack.push(Math.min(minstack.peek(),x));
    }
    public void pop()
    {
        if(!st.isEmpty())
            st.pop();
        if(!minstack.isEmpty())
            minstack.pop();
    }
    public int top()
    {
        if(!st.isEmpty())
            return st.peek();
        else
            return -1;
    }
    public int getMin()
    {
        int min=minstack.pop();
        Stack<Integer> temp=new Stack();
        while(!st.isEmpty() && st.peek()!=min)
            temp.push(st.pop());
        st.pop();
        while(!temp.isEmpty())
            st.push(temp.pop());
        return min;
    }

}
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
            return root.val<limit?null:root;
        root.left=sufficientSubset(root.left,limit-root.val);
        root.right=sufficientSubset(root.right,limit-root.val);
        return root.right==null && root.left==null?null:root;

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
    /*
    You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

    0 represents the obstacle can't be reached.
    1 represents the ground can be walked through.
    The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.


    You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

    You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

    You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

    Example 1:

    Input:
    [
     [1,2,3],
     [0,0,4],
     [7,6,5]
    ]
    Output: 6


    Example 2:

    Input:
    [
     [1,2,3],
     [0,0,0],
     [7,6,5]
    ]
    Output: -1


    Example 3:

    Input:
    [
     [2,3,4],
     [0,0,5],
     [8,7,6]
    ]
    Output: 6
    Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
     */
    public int cutOffTree(List<List<Integer>> forest)
    {
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b)
            {
                return a[2]-b[2];
            }
        });
        for(int i=0;i<forest.size();i++)
        {
            for(int j=0;j<forest.get(i).size();j++)
                if(forest.get(i).get(j)>1)
                    pq.offer(new int[]{i,j,forest.get(i).get(j)});
        }
        int[] start=new int[3];
        int sum=0;
        while(!pq.isEmpty())
        {
            int[] end=pq.poll();
            int step=bfs(forest,start,end);
            if(step==-1)
                return -1;
            sum+=step;
            start=end;
        }
        return sum;
    }
    public int bfs(List<List<Integer>> forest, int[] start, int[] end)
    {
        int[][] dir=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> q=new LinkedList<>();
        q.offer(start);
        Set<int[]> visited=new HashSet<>();
        int cnt=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                if(curr[0]==end[0] && curr[1]==end[1])
                    return cnt;
                int r=curr[0];
                int c=curr[1];
                for(int[] d: dir)
                {
                    int nr=r+d[0];
                    int nc=c+d[1];
                    if(nr<0 || nr>=forest.size() || nc<0 || nc>=forest.get(i).size() || forest.get(nr).get(nc)==0 || visited.contains(new int[]{nr,nc}))
                        continue;
                    q.offer(new int[]{nr,nc});
                    visited.add(new int[]{nr,nc});

                }

            }
            cnt++;
        }
        return -1;
    }
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
    public boolean wordBreak(String s, List<String> wordDict)
    {
        return wordBreak(s,new HashSet<>(wordDict), 0,new int[s.length()]);
    }
    public boolean wordBreak(String s, Set<String> dict,int start,int[] memo)
    {
        if(start==s.length())
            return true;
        if(memo[start]==1)
            return true;
        else if(memo[start]==2)
            return false;
        for(int i=start+1;i<=s.length();i++)
        {
            if(dict.contains(s.substring(0,i)) && wordBreak(s.substring(i),dict,i,memo))
            {
                memo[start]=1;
                return true;
            }

        }
        memo[start]=2;
        return false;
    }
    public boolean wordBreakBFS(String s, List<String> wordDict)
    {
        Set<String> dict=new HashSet<>(wordDict);
        boolean[] visited=new boolean[s.length()+1];
        Queue<Integer> q=new LinkedList<>();
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
                    else
                    {
                        visited[i]=true;
                        q.offer(i);
                    }
                }
            }
        }
        return false;
    }
    /*
    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

    Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.
    Example 1:

    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
    [
      "cats and dog",
      "cat sand dog"
    ]
    Example 2:

    Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    Output:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    Explanation: Note that you are allowed to reuse a dictionary word.
    Example 3:

    Input:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output:
    []
     */
    public List<String> wordBreak2(String s, List<String> wordDict)
    {
        return dfs(s,new HashSet<>(wordDict),new HashMap<String,List<String>>());
    }
    public List<String> dfs(String s, Set<String> dict, Map<String,List<String>> memo)
    {
        if(memo.containsKey(s))
            return memo.get(s);
        List<String> res=new ArrayList<>();
        if(s.length()==0)
            res.add("");
        for(int i=1;i<=s.length();i++)
        {
            if(dict.contains(s.substring(0,i)))
            {
                List<String> temp=dfs(s.substring(i),dict,memo);
                {
                    for(String str:temp)
                    {
                        res.add(s.substring(0,i)+(str.isEmpty()?"":" ")+str);
                    }
                }
            }
        }
        memo.put(s,res);
        return res;
    }
    /*
    Basic Calculator 1
    Implement a basic calculator to evaluate a simple expression string.

    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

    Example 1:

    Input: "1 + 1"
    Output: 2
    Example 2:

    Input: " 2-1 + 2 "
    Output: 3
    Example 3:

    Input: "(1+(4+5+2)-3)+(6+8)"
    Output: 23
    Note:
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
     */
    public int calculate1(String s)
    {
        int result=0,sign=1;
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)>='0' && s.charAt(i)<='9')
            {
                int start=i;
                while(start+1<s.length() && s.charAt(i+1)>='0' && s.charAt(i+1)<='9')
                    i++;
                result+=Integer.parseInt(s.substring(start,i+1))*sign;
            }
            else if(s.charAt(i)=='+')
                sign=1;
            else if(s.charAt(i)=='-')
                sign=-1;
            else if(s.charAt(i)=='(')
            {
                st.push(result);
                st.push(sign);
                result=0;
                sign=1;
            }
            else if(s.charAt(i)==')')
            {
                result+=st.pop()*st.pop();
            }
        }
        return result;
    }
    /*
    Basic Calculator 2
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
    public int calculate2(String s)
    {
        Stack<Integer> st=new Stack<>();
        int num=0;
        char sign='+';
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(Character.isDigit(c))
                num=num*10+(c-'0');
            else if(!Character.isDigit(c) && c!=' '|| i==s.length()-1)
            {
                if(sign=='+')
                    st.push(num);
                else if(sign=='-')
                    st.push(-num);
                else if(sign=='*')
                    st.push(st.pop()*num);
                else if(sign=='/')
                    st.push(st.pop()/num);
                num=0;
                sign=c;
            }
        }
        int result=0;
        while(!st.isEmpty())
            result+=st.pop();
        return result;
    }
    /*
    Basic Calculator3
    Implement a basic calculator to evaluate a simple expression string.

    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

    The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

    You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

    Some examples:

    "1 + 1" = 2
    " 6-4 / 2 " = 4
    "2*(5+5*2)/3+(6/2+8)" = 21
    "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
     */
    public int calculate3(String s)
    {
        if(s==null || s.length()==0)
            return 0;
        Stack<Integer> st=new Stack<>();
        int num=0;
        char sign='+';
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(Character.isDigit(c))
                num=num*10+(c-'0');
            else if(c=='(')
            {
                int cnt=0;
                int j=i;
                for(;j<s.length();j++)
                {
                    if(s.charAt(j)=='(')
                        cnt++;
                    if(s.charAt(j)==')')
                        cnt--;
                    if(cnt==0)
                        break;
                }
                num=calculate3(s.substring(i+1,j));
                i=j;
            }
            if(!Character.isDigit(c) && c!=' ' || i==s.length()-1)
            {
                if(sign=='+')
                    st.push(num);
                else if(sign=='-')
                    st.push(-num);
                else if(sign=='*')
                    st.push(st.pop()*num);
                else if(sign=='/')
                    st.push(st.pop()/num);
                num=0;
                sign=c;
            }
        }
        int result=0;
        while(!st.isEmpty())
            result+=st.pop();
        return result;
    }
    /*
    You are climbing a stair case. It takes n steps to reach to the top.

    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

    Note: Given n will be a positive integer.

    Example 1:

    Input: 2
    Output: 2
    Explanation: There are two ways to climb to the top.
    1. 1 step + 1 step
    2. 2 steps
    Example 2:

    Input: 3
    Output: 3
    Explanation: There are three ways to climb to the top.
    1. 1 step + 1 step + 1 step
    2. 1 step + 2 steps
    3. 2 steps + 1 step
     */
    public int climbStairs(int n)
    {
        //return countways1(0,n);
        //return countways2(0,n,new int[n]);
        if(n==1)
            return 1;
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<dp.length;i++)
            dp[i]=dp[i-1]+dp[i-2];
        return dp[n];
    }
    public int countways2(int i, int n, int[] memo)
    {
        if(i>n)
            return 0;
        if(i==n)
            return 1;
        if(memo[i]>0)
            return memo[i];
        int val=countways2(i+1,n,memo)+countways2(i+2,n,memo);
        memo[i]=val;
        return val;

    }
    public int countways1(int i,int n)
    {
        if(i>n)
            return 0;
        if(i==n)
            return 1;
        return countways1(i+1,n)+countways1(i+2,n);
    }
    /*
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
        int f0=0,f1=0;
        for(int i=0;i<cost.length;i++)
        {
            int f2=cost[i]+Math.min(f0,f1);
            f0=f1;
            f1=f2;
        }
        return Math.min(f1,f0);
    }
    /*
    Given an integer array, find three numbers whose product is maximum and output the maximum product.

    Example 1:

    Input: [1,2,3]
    Output: 6


    Example 2:

    Input: [1,2,3,4]
    Output: 24
     */
    public int maximumProduct(int[] arr)
    {
        int max=Integer.MIN_VALUE;
        int max1=Integer.MIN_VALUE;
        int max2=Integer.MIN_VALUE;
        int min1=Integer.MAX_VALUE;
        int min2=Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]>max)
            {
                max2=max1;
                max1=max;
                max=arr[i];
            }
            else if(arr[i]>max1)
            {
                max2=max1;
                max1=arr[i];
            }
            else if(arr[i]<min1)
            {
                min2=min1;
                min1=arr[i];
            }
            else if(arr[i]<min2)
                min2=arr[i];

        }
        return Math.max(min1*min2,max1*max2)*max;
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
        Map<Character,String> map=new HashMap<>();
        map.put('0'," ");
        map.put('1',"");
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        List<String> res=new ArrayList<>();
        helper(res,digits,map,0,"");
        return res;
    }
    public void helper(List<String> res,String digits,Map<Character,String> map,int start, String prefix)
    {
        if(start>digits.length())
            return;
        if(start==digits.length())
        {
            res.add(prefix);
            return;
        }
        char c=digits.charAt(start);
        String val=map.get(c);
        for(char ch:val.toCharArray())
        {
            helper(res,digits,map,start+1,prefix+String.valueOf(ch));
        }
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
        helper(n,n,res,"");
        return res;
    }
    public void helper(int l,int r,List<String> res,String prefix)
    {
        if(l<0 || r<0)
            return;
        if(l==0 && r==0)
        {
            res.add(prefix);
            return;
        }
        if(l>r)
            return;
        helper(l-1,r,res,prefix+"(");
        helper(l,r-1,res,prefix+")");
    }
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
    public boolean exist(char[][] board, String word)
    {
        boolean[][] visited=new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(helper(word,board,i,j,visited,0))
                    return true;
            }
        }
        return false;
    }
    public boolean helper(String word,char[][] board, int i, int j, boolean[][] visited, int len)
    {
        if(len==word.length())
            return true;
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j] || board[i][j]!=word.charAt(len))
            return false;
        visited[i][j]=true;
        if(helper(word,board,i-1,j,visited,len+1) || helper(word,board,i+1,j,visited,len+1) || helper(word,board,i,j-1,visited,len+1) || helper(word,board,i,j+1,visited,len+1))
            return true;
        visited[i][j]=false;
        return false;
    }
    /*
    Given a 2D board and a list of words from the dictionary, find all words in the board.

    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



    Example:

    Input:
    board = [
      ['o','a','a','n'],
      ['e','t','a','e'],
      ['i','h','k','r'],
      ['i','f','l','v']
    ]
    words = ["oath","pea","eat","rain"]
    Output: ["eat","oath"]

     */
    private class Trie
    {
        Trie[] next;
        String word;
        public Trie()
        {
            next=new Trie[26];
            word=null;
        }
    }
    public void insert(Trie node, String s)
    {
        for(char c: s.toCharArray())
        {
            if(node.next[c-'a']==null)
                node.next[c-'a']=new Trie();
            node=node.next[c-'a'];
        }
        node.word=s;
    }
    public List<String> findWords(char[][] board, String[] words)
    {
        Trie root=new Trie();
        for(String s: words)
            insert(root,s);
        List<String> res=new ArrayList<>();
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                dfs(i,j,board,root,res);
            }
        }
        return res;
    }
    public void dfs(int i, int j,char[][] board,Trie root,List<String> res)
    {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]=='#')
            return;
        char c=board[i][j];
        if(root.next[c-'a']==null)
            return;
        root=root.next[c-'a'];
        if(root.word!=null)
        {
            res.add(root.word);
            root.word=null;
        }
        board[i][j]='#';
        dfs(i+1,j,board,root,res);
        dfs(i-1,j,board,root,res);
        dfs(i,j+1,board,root,res);
        dfs(i,j-1,board,root,res);
        board[i][j]=c;
    }
    /*
    You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

    Define a pair (u,v) which consists of one element from the first array and one element from the second array.

    Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

    Example 1:

    Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
    Output: [[1,2],[1,4],[1,6]]
    Explanation: The first 3 pairs are returned from the sequence:
                 [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
    Example 2:

    Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
    Output: [1,1],[1,1]
    Explanation: The first 2 pairs are returned from the sequence:
                 [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
    Example 3:

    Input: nums1 = [1,2], nums2 = [3], k = 3
    Output: [1,3],[2,3]
    Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k)
    {
        Map<int[],Integer> map=new HashMap<>();
        for(int i:nums1)
        {
            for(int j:nums2)
                map.put(new int[]{i,j},i+j);
        }
        PriorityQueue<Map.Entry<int[],Integer>> pq=new PriorityQueue<>(new Comparator<Map.Entry<int[], Integer>>() {
            @Override
            public int compare(Map.Entry<int[], Integer> a, Map.Entry<int[], Integer> b)
            {
                return a.getValue()-b.getValue();
            }
        });
        for(Map.Entry<int[],Integer> entry:map.entrySet())
            pq.offer(entry);
        List<int[]> res=new ArrayList<>();
        for(int i=0;i<k;i++)
            res.add(pq.poll().getKey());
        return res;
    }
    /*
    Given a non-empty array of integers, return the k most frequent elements.

    Example 1:

    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
    Example 2:

    Input: nums = [1], k = 1
    Output: [1]
     */
    public List<Integer> topKFrequent(int[] nums, int k)
    {
        Map<Integer,Integer> freq=new HashMap<>();
        for(int i:nums)
        {
            freq.put(i,freq.getOrDefault(i,0)+1);
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer i1, Integer i2)
            {
                return freq.get(i2)-freq.get(i1);
            }
        });
        for(int i:freq.keySet())
            pq.offer(i);
        List<Integer> res=new ArrayList<>();
        for(int i=0;i<k;i++)
            res.add(pq.poll());
        return res;


    }
    /*
    We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

    (Here, the distance between two points on a plane is the Euclidean distance.)

    You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



    Example 1:

    Input: points = [[1,3],[-2,2]], K = 1
    Output: [[-2,2]]
    Explanation:
    The distance between (1, 3) and the origin is sqrt(10).
    The distance between (-2, 2) and the origin is sqrt(8).
    Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
    We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
    Example 2:

    Input: points = [[3,3],[5,-1],[-2,4]], K = 2
    Output: [[3,3],[-2,4]]
    (The answer [[-2,4],[3,3]] would also be accepted.)
     */
    public int[][] kClosest(int[][] points, int K)
    {
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>()
        {
            public int compare(int[] a, int[] b)
            {
                int val1=a[0]*a[0]+a[1]*a[1];
                int val2=b[0]*b[0]+b[1]*b[1];
                return val1-val2;
            }
        });
        for(int[] i:points)
            pq.offer(i);
        int[][] res=new int[K][2];
        for(int i=0;i<K;i++)
            res[i]=pq.poll();

        return res;
    }
    /*
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

    Example 1:

    Input: [[0, 30],[5, 10],[15, 20]]
    Output: 2
    Example 2:

    Input: [[7,10],[2,4]]
    Output: 1
     */
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
        int count=0,j=0;
        for(int i=0;i<start.length;i++)
        {
            if(start[i]<end[j])
                count++;
            else
                j++;
        }
        return count;
    }
    /*
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
    public int findKthLargest(int[] nums, int k)
    {
        return quickSelect(nums,nums.length-k,nums.length-1,0);
    }
    public int quickSelect(int[] arr, int k, int right, int left)
    {
        int pval=partition(arr,left,right);
        if(pval==k)
            return arr[k];
        else if(k<pval)
            return quickSelect(arr,k,pval-1,left);
            else
                return quickSelect(arr,k,right,pval+1);
    }
    public int partition(int[] arr, int left, int right)
    {
        int pval=arr[right];
        int si=left;
        for(int i=left;i<=right;i++)
        {
            if(arr[i]>pval)
            {
                swap(arr,i,si);
                si++;
            }
        }
        swap(arr,si,right);
        return si;

    }
    public void swap(int[] arr, int l, int r)
    {
        int temp=arr[l];
        arr[l]=arr[r];
        arr[r]=temp;
    }
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
     */
    public int[][] merge(int[][] intervals)
    {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });
        LinkedList<int[]> res=new LinkedList<>();
        for(int[] i:intervals)
        {
            if(res.isEmpty() || res.getLast()[1]<i[0])
                res.add(i);
            else
                res.getLast()[1]=Math.max(i[1],res.getLast()[1]);
        }
        return res.toArray(new int[res.size()][]);
    }
    /*
    There are two sorted arrays nums1 and nums2 of size m and n respectively.

    Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

    You may assume nums1 and nums2 cannot be both empty.

    Example 1:

    nums1 = [1, 3]
    nums2 = [2]

    The median is 2.0
    Example 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    The median is (2 + 3)/2 = 2.5
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        if(nums1.length>nums2.length)
            return findMedianSortedArrays(nums2,nums1);
        int x=nums1.length,y=nums2.length,lo=0,hi=x;
        while(lo<=hi)
        {
            int partitionX=(lo+hi)/2;
            int partitionY=(x+y+1)/2-partitionX;

            int maxLeftX=partitionX==0?Integer.MIN_VALUE:nums1[partitionX-1];
            int minnRightX=partitionX==x?Integer.MAX_VALUE:nums1[partitionX];

            int maxLeftY=partitionY==0?Integer.MIN_VALUE:nums2[partitionY-1];
            int minRightY=partitionY==y?Integer.MAX_VALUE:nums2[partitionY];
            if(maxLeftX<=minRightY && maxLeftY<=minnRightX)
            {
                if((x+y)%2==0)
                    return(double)(Math.max(maxLeftX,maxLeftY)+Math.min(minnRightX,minRightY))/2.0;
                else return (double)(Math.max(maxLeftX,maxLeftY));
            }
            else if(maxLeftX>minRightY)
                hi=partitionX-1;
            else
                lo=partitionX+1;
        }
        return -1;
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
        if(nums.length==1 && target==nums[0])
            return nums[0];
        if(nums.length==1 && nums[0]!=target)
            return -1;
        int pivot=pivot(nums,0,nums.length-1);
        if(pivot==0)
            return binary(nums,0,nums.length-1,target);
        else if(target<nums[0])
            return binary(nums,pivot,nums.length-1,target);
        else
            return binary(nums,0,pivot-1,target);
    }
    public int pivot(int[] nums, int l, int r)
    {
        if(nums[l]<nums[r])
            return 0;
        int lo=l,hi=r;
        while(lo<hi)
        {
            int mid=(lo+hi)/2;
            if(nums[mid]>nums[mid+1])
                return mid+1;
            else
            {
                if(nums[mid]>nums[l])
                    lo=mid+1;
                else
                    hi=mid-1;
            }
        }
        return -1;
    }
    public int binary(int[] nums, int l, int r, int target)
    {
        while(l<=r)
        {
            int mid=(l+r)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                l=mid+1;
            else r=mid-1;
        }
        return -1;
    }
    /*
    Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
    A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

    Example:
    Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

    Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

    Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
     "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
    "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words)
    {
        Set<String> dict=new HashSet<>(Arrays.asList(words));
        List<String> res=new ArrayList<>();
        for(String s: words)
        {
            dict.remove(s);
            if(canForm(s,dict))
                res.add(s);
            dict.add(s);
        }
        return res;
    }
    public boolean canForm(String s,Set<String> dict)
    {
        boolean[] visited=new boolean[s.length()+1];
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        while(!q.isEmpty())
        {
            int curr=q.poll();
            visited[curr]=true;
            for(int i=curr;i<=s.length();i++)
            {
                if(dict.contains(s.substring(curr,i)) && !visited[i])
                {
                    if(i==s.length())
                        return true;
                    q.offer(i);
                }
            }
        }
        return false;
    }
    /*
    Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

    Example 1:

    Input: "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()"
    Example 2:

    Input: ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()"
     */
    public int longestValidParentheses(String s)
    {
        int left=0,right=0;
        int maxAns=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='(')
                left++;
            else
                right++;
            if(left==right)
                maxAns=Math.max(maxAns,left*2);
            else if(right>left)
                left=right=0;
        }
        left=right=0;
        for(int i=s.length()-1;i>=0;i--)
        {
            if(s.charAt(i)=='(')
                left++;
            else
                right++;
            if(left==right)
                maxAns=Math.max(maxAns,left*2);
            else if(right>left);
                left=right=0;
        }
        return maxAns;
    }
    /*
    Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

    Example 1:

    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
    Example 2:

    Input: "cbbd"
    Output: "bb"
     */
    public String longestPanlindrome(String str)
    {
        if(str==null || str.length()==0)
            return "";
        int len=0,s=0,e=0;
        for(int i=0;i<str.length();i++)
        {
            int len1=expand(str,i,i);
            int len2=expand(str,i,i+1);
            len=Math.max(len1,len2);
            if(len>e-s)
            {
                s=i-(len-1)/2;
                e=i+(len/2);
            }
        }
        return str.substring(s,e+1);
    }
    public int expand(String s, int l, int r)
    {
        while(l>0 && r<s.length() && s.charAt(l)==s.charAt(r))
        {
            l--;
            r++;
        }
        return r-l-1;
    }
    /*
    Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

    Example:

    Input: [-2,1,-3,4,-1,2,1,-5,4],
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.
     */
    public int maxSubArray(int[] nums)
    {
        int maxSoFar=nums[0],maxFinal=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            maxSoFar=Math.max(maxSoFar+nums[i],nums[i]);
            if(maxFinal<maxSoFar)
                maxFinal=maxSoFar;
        }
        return maxFinal;
    }
    /*
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
    public int maxProfit(int[] nums)
    {
        int maxProfit=0,minVal=Integer.MAX_VALUE;
        for(int i:nums)
        {
            if(i<minVal)
                minVal=i;
            else
                maxProfit=Math.max(maxProfit,i-minVal);
        }
        return maxProfit;
    }
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
    public boolean wordBreak3(String s, List<String> wordDict)
    {
        boolean[] visited=new boolean[s.length()+1];
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        while(!q.isEmpty())
        {
            int curr=q.poll();
            visited[curr]=true;
            for(int i=curr+1;i<=s.length();i++)
            {
                if(wordDict.contains(s.substring(curr,i)) && !visited[i])
                {
                    if(i==s.length())
                        return true;
                    q.offer(i);
                }
            }
        }
        return false;
    }
    /*
    You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

    Example 1:

    Input: coins = [1, 2, 5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1
    Example 2:

    Input: coins = [2], amount = 3
    Output: -1
    Note:
    You may assume that you have an infinite number of each kind of coin.
     */
    public int coinChange(int[] coins, int amount)
    {
        int[] dp=new int[amount+1];
        for(int i=1;i<=amount;i++)
        {
            dp[i]=Integer.MAX_VALUE;
            for(int j=0;j<coins.length;j++)
            {
                if(coins[j]<=i && dp[i-coins[j]]!=Integer.MAX_VALUE)
                    dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];

    }
    /*
    For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

    Return the largest string X such that X divides str1 and X divides str2.



    Example 1:

    Input: str1 = "ABCABC", str2 = "ABC"
    Output: "ABC"
    Example 2:

    Input: str1 = "ABABAB", str2 = "ABAB"
    Output: "AB"
    Example 3:

    Input: str1 = "LEET", str2 = "CODE"
    Output: ""
     */
    public String gcdOfStrings(String str1, String str2)
    {
        if(str1.length()<str2.length())
            return gcdOfStrings(str2,str1);
        else if(!str1.startsWith(str2))
            return "";
        else if(str2.isEmpty())
            return str1;
        return gcdOfStrings(str1.substring(str2.length()),str2);
    }
    public static void main(String[] args)
    {
        PriorityQueue<Integer> pq=new PriorityQueue<>(3);
        pq.offer(4);
        pq.offer(5);
        pq.offer(8);
        pq.offer(2);
        pq.poll();
    }
}
