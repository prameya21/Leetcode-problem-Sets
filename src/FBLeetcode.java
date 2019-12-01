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
    //Validate Binary Search Tree
    {
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
    }
    public boolean isValidBST(TreeNode root)
    {
        return BSTUtil(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public boolean BSTUtil(TreeNode root,int min,int max)
    {
        if(root==null)
            return true;
        else if(root.val<=min || root.val>=max)
            return false;
        else
            return BSTUtil(root.left,min,root.val) && BSTUtil(root.right,root.val,max);
    }

    //Flatten Binary Tree to Linked List
    {
        /*
        Given a binary tree, flatten it to a linked list in-place.

        For example, given the following tree:

            1
           / \
          2   5
         / \   \
        3   4   6
        The flattened tree should look like:

        1
         \
          2
           \
            3
             \
              4
               \
                5
                 \
                  6
         */
    }
    TreeNode prev=null;
    public void flatten(TreeNode root)
    {
        if(root==null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.left=null;
        root.right=prev;
        prev=root;
    }
    //Binary Tree Right Side View
    {
        /*
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
    }

    public List<Integer> rightSideView(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        List<Integer> res=new ArrayList<>();
        helper(root,0,res);
        return res;
    }
    public void helper(TreeNode root, int depth, List<Integer> res)
    {
        if(root==null)
            return;
        if(depth==res.size())
            res.add(root.val);
        helper(root.right,depth+1,res);
        helper(root.left,depth+1,res);
    }
    //Alien Dictionary
    {
        /*
        There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

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
        Map<Character,Integer> degree=new HashMap<>();
        Map<Character,Set<Character>> adj=new HashMap<>();
        for(int i=0;i<words.length;i++)
        {
            for(char c:words[i].toCharArray())
            {
                degree.putIfAbsent(c,0);
                adj.putIfAbsent(c,new HashSet<>());
            }
        }
        for(int i=0;i<words.length-1;i++)
        {
            String word1=words[i];
            String word2=words[i+1];
            for(int j=0;j<Math.min(word1.length(),word2.length());j++)
            {
                char c1=word1.charAt(j);
                char c2=word2.charAt(j);
                if(c1!=c2)
                {
                    if(!adj.get(c1).contains(c2))
                    {
                        adj.get(c1).add(c2);
                        degree.put(c2,degree.get(c2)+1);
                    }
                    break;
                }
            }
        }
        Set<Character> visited=new HashSet<>();
        Queue<Character> q=new LinkedList<>();
        for(char c:degree.keySet())
            if(degree.get(c)==0)
                q.offer(c);
        StringBuilder sb=new StringBuilder();
        while(!q.isEmpty())
        {
            char curr=q.poll();
            visited.add(curr);
            sb.append(curr);
            for(char ch:adj.get(curr))
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
        boolean[][] adj=new boolean[26][26];
        int[] visited=new int[26];
        Arrays.fill(visited,-1);
        for(String w:words)
            for(char c:w.toCharArray())
                visited[c-'a']=0;

        for(int i=0;i<words.length-1;i++)
        {
            String word1=words[i];
            String word2=words[i+1];
            for(int j=0;j<Math.max(word1.length(),word2.length());j++)
            {
                char c1=word1.charAt(j);
                char c2=word2.charAt(j);
                if(c1!=c2)
                {
                    adj[c1-'a'][c2-'a']=true;
                    break;
                }
            }
        }
        Stack<Character> st=new Stack<>();
        for(int i=0;i<26;i++)
        {
            if(visited[i]==0)
            {
                if(!dfs(visited,adj,i,st))
                    return "";
            }
        }
        StringBuilder sb=new StringBuilder();
        while(!st.isEmpty())
            sb.append(st.pop());
        return sb.toString();
    }
    public boolean dfs(int[] visited, boolean[][] adj,int i, Stack<Character> st)
    {
        visited[i]=1;
        for(int j=0;j<26;j++)
        {
            if(adj[i][j])
            {
                if(visited[j]==1)
                    return false;
                if(visited[j]==0 && !dfs(visited,adj,j,st))
                    return false;
            }
        }
        visited[i]=2;
        char c=(char)(i+'a');
        st.push(c);
        return true;
    }
    //Binary Tree Vertical order Traversal
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
        Map<Integer,List<Integer>> map=new HashMap<>();
        Queue<TreeNode> q=new LinkedList<>();
        Queue<Integer> cols=new LinkedList<>();
        q.offer(r);
        cols.offer(0);
        int min=0,max=0;
        while(!q.isEmpty())
        {
            TreeNode curr=q.poll();
            int c=cols.poll();
            map.putIfAbsent(c,new ArrayList<>());
            map.get(c).add(curr.val);
            if(curr.left!=null)
            {
                q.offer(curr.left);
                cols.offer(c-1);
                min=Math.min(min,c-1);
            }
            if(curr.right!=null)
            {
                q.offer(curr.right);
                cols.offer(c+1);
                max=Math.max(max,c+1);
            }
        }
        List<List<Integer>> res=new ArrayList<>();
        for(int i=min;i<=max;i++)
            res.add(map.get(i));
        return res;
    }
    //Vertical order traversal where nodes are sorted at their respective depths
    {
        /*
        Given a binary tree, return the vertical order traversal of its nodes values.

        For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

        Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

        If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

        Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.



        Example 1:



        Input: [3,9,20,null,null,15,7]
        Output: [[9],[3,15],[20],[7]]
        Explanation:
        Without loss of generality, we can assume the root node is at position (0, 0):
        Then, the node with value 9 occurs at position (-1, -1);
        The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
        The node with value 20 occurs at position (1, -1);
        The node with value 7 occurs at position (2, -2).
        Example 2:



        Input: [1,2,3,4,5,6,7]
        Output: [[4],[2],[1,5,6],[3],[7]]
        Explanation:
        The node with value 5 and the node with value 6 have the same position according to the given scheme.
        However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
         */
    }
    class Node
    {
        int x,y;
        TreeNode node;
        public Node(TreeNode node, int x, int y)
        {
            this.node=node;
            this.x=x;
            this.y=y;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root)
    {
        if(root==null)
            return null;
        int min=0,max=0;
        Queue<Node> q=new LinkedList<>();
        Queue<Integer> cols=new LinkedList<>();
        Map<Integer,List<Node>> map=new HashMap<>();
        q.offer(new Node(root,0,0));
        cols.offer(0);
        while(!q.isEmpty())
        {
            Node curr=q.poll();
            int c=cols.poll();
            map.putIfAbsent(c,new ArrayList<>());
            map.get(c).add(curr);
            if(curr.node.left!=null)
            {
                q.offer(new Node(curr.node.left,c-1,curr.y+1));
                cols.offer(c-1);
                min=Math.min(min,c-1);
            }
            if(curr.node.right!=null)
            {
                q.offer(new Node(curr.node.right,c+1,curr.y+1));
                cols.offer(c+1);
                max=Math.max(max,c+1);
            }
        }
        List<List<Integer>> res=new ArrayList<>();
        for(int i=min;i<=max;i++)
        {
            Collections.sort(map.get(i), new Comparator<Node>()
            {
                @Override
                public int compare(Node a, Node b)
                {
                    if(a.y==b.y)
                        return a.node.val-b.node.val;
                    return 0;
                }
            });
            List<Integer> temp=new ArrayList<>();
            for(Node n:map.get(i))
                temp.add(n.node.val);
            res.add(new ArrayList<>(temp));
        }
        return res;
    }
    //Accounts Merge
    {
        /*
        Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

        Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts.
        Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

        After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

        Example 1:
        Input:
        accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
        Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
        Explanation:
        The first and third John's are the same person as they have the common email "johnsmith@mail.com".
        The second John and Mary are different people as none of their email addresses are used by other accounts.
        We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
        ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
        Note:

        The length of accounts will be in the range [1, 1000].
        The length of accounts[i] will be in the range [1, 10].
        The length of accounts[i][j] will be in the range [1, 30].
         */
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts)
    {
        Map<String,Set<String>> graph=new HashMap<>();
        Map<String,String> name=new HashMap<>();
        for(List<String> li:accounts)
        {
            String u_nname=li.get(0);
            for(int i=1;i<li.size();i++)
            {
                graph.putIfAbsent(li.get(i),new HashSet<>());
                name.put(li.get(i),u_nname);
                if(i==1)
                    continue;
                graph.get(li.get(i)).add(li.get(i-1));
                graph.get(li.get(i-1)).add(li.get(i));
            }
        }
        Set<String> visited=new HashSet<>();
        List<List<String>> res=new ArrayList<>();
        for(String s:name.keySet())
        {
            List<String> temp=new ArrayList<>();
            if(!visited.contains(s))
            {
                dfs(temp,visited,s,graph);
                Collections.sort(temp);
                temp.add(0,name.get(s));
                res.add(new ArrayList<>(temp));
            }
        }
        return res;
    }
    public void dfs(List<String> temp,Set<String> visited,String s,Map<String,Set<String>> graph)
    {
        if(visited.contains(s))
            return;
        temp.add(s);
        visited.add(s);
        for(String str:graph.get(s))
            dfs(temp,visited,str,graph);

    }
    //Convert BST to Sorted Doubly Linked List
    {
        /*
        Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

        You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
        For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
        We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor,and the right pointer should point to its successor.
        You should return the pointer to the smallest element of the linked list.


        Example 1:
        Input: root = [4,2,5,1,3]
        Output: [1,2,3,4,5]
        Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
        Example 2:
        Input: root = [2,1,3]
        Output: [1,2,3]
        Example 3:
        Input: root = []
        Output: []
        Explanation: Input is an empty tree. Output is also an empty Linked List.
        Example 4:
        Input: root = [1]
        Output: [1]

        Constraints:
        -1000 <= Node.val <= 1000
        Node.left.val < Node.val < Node.right.val
        All values of Node.val are unique.
        0 <= Number of Nodes <= 2000
         */

    }
    TreeNode head=null;
    TreeNode tail=null;
    public TreeNode treeToDoublyList(TreeNode root)
    {
        if(root==null)
            return null;
        helper(root);
        prev.right=head;
        head.left=prev;
        return head;
    }
    public void helper(TreeNode root)
    {
        if(root==null)
            return;
        helper(root.left);
        if(prev==null)
            head=root;
        else
        {
            prev.right=root;
            root.left=prev;
        }
        prev=root;
        helper(root.right);
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
        System.out.println(obj.minWindow("ADOBECODEBANC","ABC"));
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(1);
        root.right=new TreeNode(4);
        root.right.left=new TreeNode(3);
        root.right.right=new TreeNode(6);
        System.out.println(obj.isValidBST(root));
        System.out.println(obj.rightSideView(root));
        System.out.println(obj.alienOrderDFS(new String[]{"wrt","wrf","er","ett","rftt"}));
        System.out.println(obj.verticalTraversal(root));
        List<List<String>> accounts=new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        System.out.println(obj.accountsMerge(accounts));
    }
}
