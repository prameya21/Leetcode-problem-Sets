import java.util.*;
public class LinkedinExplore
{
    //Two Sum
    {
        /*
        Given an array of integers, return indices of the two numbers such that they add up to a specific target.

        You may assume that each input would have exactly one solution, and you may not use the same element twice.

        Example:

        Given nums = [2, 7, 11, 15], target = 9,

        Because nums[0] + nums[1] = 2 + 7 = 9,
        return [0, 1].

         */
    }
    public int[] twoSum(int[] nums, int target)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            int comp=target-nums[i];
            if(map.containsKey(comp))
                return new int[]{i,map.get(comp)};
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

    //Valid Number
    {
        /*
        Validate if a given string can be interpreted as a decimal number.

        Some examples:
        "0" => true
        " 0.1 " => true
        "abc" => false
        "1 a" => false
        "2e10" => true
        " -90e3   " => true
        " 1e" => false
        "e3" => false
        " 6e-1" => true
        " 99e2.5 " => false
        "53.5e93" => true
        " --6 " => false
        "-+3" => false
        "95a54e53" => false

        Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

        Numbers 0-9
        Exponent - "e"
        Positive/negative sign - "+"/"-"
        Decimal point - "."
        Of course, the context of these characters also matters in the input.

        Update (2015-02-10):
        The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.
         */
    }
    public boolean isNumber(String s)
    {
        boolean pointSeen=false;
        boolean numberSeen=false;
        boolean eSeen=false;
        boolean numberAftere=true;
        s=s.trim();
        for(int i=0;i<s.length();i++)
        {
            if(Character.isDigit(s.charAt(i)))
            {
                numberSeen=true;
                numberAftere=true;
            }
            else if(s.charAt(i)=='.')
            {
                if(pointSeen||eSeen)
                    return false;
                pointSeen=true;
            }
            else if(s.charAt(i)=='e')
            {
                if(eSeen||!numberSeen)
                    return false;
                eSeen=true;
                numberAftere=false;
            }
            else if(s.charAt(i)=='+' || s.charAt(i)=='-')
            {
                if(i!=0 && s.charAt(i-1)!='e')
                    return false;
            }
            else
                return false;
        }
        return numberAftere && numberSeen;
    }

    //Minimum Window Substring
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
            map_t.put(c, map_t.getOrDefault(c,0)+1);
        int l=0,r=0;
        String res="";
        int len=Integer.MAX_VALUE;
        int ctr=0;
        int t_size=map_t.size();
        Map<Character,Integer> map_s=new HashMap<>();
        while(r<s.length())
        {
            char c=s.charAt(r);
            map_s.put(c,map_s.getOrDefault(c,0)+1);
            if(map_t.containsKey(c) && map_t.get(c).intValue()==map_s.get(c).intValue())
                ctr++;
            while(ctr==t_size && l<=r)
            {
                String temp=s.substring(l,r+1);
                if(temp.length()<len)
                {
                    res=temp;
                    len=temp.length();
                }
                char ch=s.charAt(l);
                map_s.put(ch,map_s.get(ch)-1);
                if(map_t.containsKey(ch) && map_t.get(ch).intValue()>map_s.get(ch).intValue())
                    ctr--;
                l++;
            }
            r++;
        }
        return res;
    }

    //Shortest Word Distance
    {
        /*
        Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

        Example:
        Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

        Input: word1 = “coding”, word2 = “practice”
        Output: 3
        Input: word1 = "makes", word2 = "coding"
        Output: 1
        Note:
        You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
         */
    }
    public int shortestDistance(String[] words, String word1, String word2)
    {
        List<Integer> word1Loc=new ArrayList<>();
        List<Integer> word2Loc=new ArrayList<>();
        for(int i=0;i<words.length;i++)
        {
            if(words[i].equals(word1))
                word1Loc.add(i);
            if(words[i].equals(word2))
                word2Loc.add(i);
        }
        int min=Integer.MAX_VALUE;
        for(int i:word1Loc)
            for(int j:word2Loc)
                min=Math.min(min,i-j);

        return min;
    }

    //Integer To English Words
    {
        /*
        Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

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
    }
    private String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String numberToWords(int num)
    {
        if(num==0)
            return "Zero";
        return convert(num);
    }
    public String convert(int num)
    {
        String result="";
        if(num<10)
            result=belowTen[num];
        else if(num<20)
            result=belowTwenty[num-10];
        else if(num<100)
            result=belowHundred[num/10]+" "+belowTen[num%10];
        else if(num<1000)
            result=convert(num/100)+" Hundred "+convert(num%100);
        else if(num<1000000)
            result=convert(num/1000)+" Thousand "+convert(num%1000);
        else if(num<1000000000)
            result=convert(num/1000000)+" Million "+convert(num%1000000);
        else
            result=convert(num/1000000000)+" Billion "+convert(num%1000000000);
        return result.trim();
    }

    //Isomorphic Strings
    {
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
    }
    public boolean isIsomorphic(String s, String t)
    {
        if(s.length()!=t.length())
            return false;
        Map<Character,Character> map=new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            char cs=s.charAt(i);
            char ct=t.charAt(i);
            if(map.containsKey(cs) && !map.get(cs).equals(ct))
                return false;
            map.putIfAbsent(cs,ct);
        }
        map.clear();
        for(int i=0;i<s.length();i++)
        {
            char cs=s.charAt(i);
            char ct=t.charAt(i);
            if(map.containsKey(ct) && !map.get(ct).equals(cs))
                return false;
            map.putIfAbsent(ct,cs);
        }
        return true;
    }

    //Maximum Depth of a Binary Tree
    {
        /*
        Given a binary tree, find its maximum depth.

        The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

        Note: A leaf is a node with no children.

        Example:

        Given binary tree [3,9,20,null,null,15,7],

            3
           / \
          9  20
            /  \
           15   7
        return its depth = 3.
         */
    }
    public int maxDepth(TreeNode root)
    {
        if(root==null)
            return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    //Word Ladder
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
        int cnt=0;
        Queue<String> q=new LinkedList<>();
        q.offer(beginWord);
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String currWord=q.poll();
                if(currWord.equals(endWord))
                    return cnt+1;
                for(String s:wordList)
                {
                    if(canForm(currWord,s) && dict.contains(s))
                    {
                        dict.remove(s);
                        q.offer(s);
                    }
                }
            }
            cnt++;
        }
        return 0;
    }

    public boolean canForm(String s, String t)
    {
        int ctr=0;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)!=t.charAt(i))
                ctr++;
        return ctr==1;
    }

    //Lowest Common Ancestor of Binary Tree
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


        Note:

        All of the nodes' values will be unique.
        p and q are different and both values will exist in the binary tree.
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

    //Closest Binary Search Tree Values
    {
        /*
        Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

        Note:

        Given target value is a floating point.
        You may assume k is always valid, that is: k ≤ total nodes.
        You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
        Example:

        Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

            4
           / \
          2   5
         / \
        1   3

        Output: [4,3]
        Follow up:
        Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
         */
    }
    public List<Integer> closestKValues(TreeNode root, double target, int k)
    {
        Stack<Integer> s=new Stack<>();
        Stack<Integer> p=new Stack<>();
        successor(s,root,target);
        predessecor(p,root,target);
        List<Integer> res=new ArrayList<>();
        while(k>0)
        {
            if(s.isEmpty())
                res.add(p.pop());
            else if(p.isEmpty())
                res.add(s.pop());
            else if(Math.abs(s.peek()-target)<Math.abs(p.peek()-target))
                res.add(s.pop());
            else
                res.add(p.pop());
        }
        return res;
    }
    public void successor(Stack<Integer> st, TreeNode root,double target)
    {
        if(root==null)
            return;
        successor(st,root.right,target);
        if(root.val<=target)
            return;
        st.push(root.val);
        successor(st,root.left,target);
    }
    public void predessecor(Stack<Integer> st, TreeNode root,double target)
    {
        if(root==null)
            return;
        predessecor(st,root.left,target);
        if(root.val>target)
            return;
        st.push(root.val);
        predessecor(st,root.right,target);
    }

    //Find Leaves of Binary Tree
    {
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
    }
    public List<List<Integer>> findLeaves(TreeNode root)
    {
        List<List<Integer>> res=new ArrayList<>();
        if(root==null)
            return res;
        while(root!=null)
        {
            List<Integer> temp=new ArrayList<>();
            root=helper(root,temp);
            res.add(temp);
        }
        return res;
    }
    public TreeNode helper(TreeNode root,List<Integer> temp)
    {
        if(root==null)
            return null;
        if(root.left==null && root.right==null)
        {
            temp.add(root.val);
            return null;
        }
        root.left=helper(root.left,temp);
        root.right=helper(root.right,temp);
        return root;
    }

    //Combination Sum
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
        List<List<Integer>> res=new ArrayList<>();
        if(candidates==null || candidates.length==0)
            return res;
        Arrays.sort(candidates);
        helper(candidates,0,target,res,new ArrayList<>());
        return res;
    }
    public void helper(int[] nums,int start,int target,List<List<Integer>> res,List<Integer> temp)
    {
        if(target==0)
        {
            res.add(new ArrayList<>(temp));
        }
        else if(target<0)
            return;
        else
        {
            for(int i=start;i<nums.length;i++)
            {
                temp.add(nums[i]);
                helper(nums,i,target-nums[i],res,temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    //Permutations
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
        List<List<Integer>> res=new ArrayList<>();
        if(nums==null || nums.length==0)
            return res;
        helper(nums,res,new ArrayList<>());
        return res;
    }
    public void helper(int[] nums, List<List<Integer>> res,List<Integer> temp)
    {
        if(temp.size()==nums.length)
            res.add(new ArrayList<>(temp));
        else
        {
            for(int i=0;i<nums.length;i++)
            {
                if(temp.contains(nums[i]))
                    continue;
                temp.add(nums[i]);
                helper(nums,res,temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    //Permutations 2
    {
        /*
        Given a collection of numbers that might contain duplicates, return all possible unique permutations.

        Example:

        Input: [1,1,2]
        Output:
        [
          [1,1,2],
          [1,2,1],
          [2,1,1]
        ]
         */
    }
    public List<List<Integer>> permuteUnique(int[] nums)
    {
        if(nums==null || nums.length==0)
            return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        helper(nums,res,new ArrayList<>(),new boolean[nums.length]);
        return res;
    }
    public void helper(int[] nums,List<List<Integer>> res,List<Integer> temp,boolean[] visited)
    {
        if(temp.size()==nums.length)
            res.add(new ArrayList<>(temp));
        else
            for(int i=0;i<nums.length;i++)
            {
                if(visited[i] || i>0 && nums[i]==nums[i-1] && !visited[i-1])
                    continue;
                temp.add(nums[i]);
                visited[i]=true;
                helper(nums,res,temp,visited);
                visited[i]=false;
                temp.remove(temp.size()-1);
            }
    }

    //Factor Combinations
    {
        /*
        Numbers can be regarded as product of its factors. For example,

        8 = 2 x 2 x 2;
          = 2 x 4.
        Write a function that takes an integer n and return all possible combinations of its factors.

        Note:

        You may assume that n is always positive.
        Factors should be greater than 1 and less than n.
        Example 1:

        Input: 1
        Output: []
        Example 2:

        Input: 37
        Output:[]
        Example 3:

        Input: 12
        Output:
        [
          [2, 6],
          [2, 2, 3],
          [3, 4]
        ]
        Example 4:

        Input: 32
        Output:
        [
          [2, 16],
          [2, 2, 8],
          [2, 2, 2, 4],
          [2, 2, 2, 2, 2],
          [2, 4, 4],
          [4, 8]
        ]
         */
    }
    public List<List<Integer>> getFactors(int n)
    {
        List<List<Integer>> res=new ArrayList<>();
        if(n==0)
            return res;
        helper_factors(2,n,res,new ArrayList<>());
        return res;

    }
    public void helper_factors(int start,int n, List<List<Integer>> res, List<Integer> temp)
    {
        if(n<=1)
        {
            if(temp.size()>1)
                res.add(new ArrayList<>(temp));
            return;
        }
        else
        {
            for(int i=start;i<=n;i++)
            {
                if(n%i==0)
                {
                    temp.add(i);
                    helper_factors(i,n/i,res,temp);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }

    //Valid Paranthesis
    {
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
    }
    public boolean isValid(String s)
    {
        Stack<Character> st=new Stack<>();
        for(char c:s.toCharArray())
        {
            if(c=='{')
                st.push('}');
            else if(c=='[')
                st.push(']');
            else if(c=='(')
                st.push(')');
            else if(st.isEmpty() || st.pop()!=c)
                return false;
        }
        return st.isEmpty();
    }

    //Evaluate Reverse Polish Notation
    {
        /*
        Evaluate the value of an arithmetic expression in Reverse Polish Notation.

        Valid operators are +, -, *, /. Each operand may be an integer or another expression.

        Note:

        Division between two integers should truncate toward zero.
        The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
        Example 1:

        Input: ["2", "1", "+", "3", "*"]
        Output: 9
        Explanation: ((2 + 1) * 3) = 9
        Example 2:

        Input: ["4", "13", "5", "/", "+"]
        Output: 6
        Explanation: (4 + (13 / 5)) = 6
        Example 3:

        Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
        Output: 22
        Explanation:
          ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        = ((10 * (6 / (12 * -11))) + 17) + 5
        = ((10 * (6 / -132)) + 17) + 5
        = ((10 * 0) + 17) + 5
        = (0 + 17) + 5
        = 17 + 5
        = 22
         */
    }
    public int evalRPN(String[] tokens)
    {
        Stack<Integer> st=new Stack<>();
        for(String s:tokens)
        {
            if(s.equals("+"))
                st.push(st.pop()+st.pop());
            else if(s.equals("*"))
                st.push(st.pop()*st.pop());
            else if(s.equals("-"))
            {
                int b=st.pop();
                int a=st.pop();
                st.push(a-b);
            }
            else if(s.equals("/"))
            {
                int b=st.pop();
                int a=st.pop();
                st.push(a/b);
            }
            else
                st.push(Integer.parseInt(s));
        }
        return st.pop();
    }

    //Kth Largest Element in an Array
    {
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
    }
    public int findKthLargest(int[] nums, int k)
    {
        return quickSelect(nums,nums.length-k,0,nums.length-1);
    }
    public int quickSelect(int[] nums, int k,int l, int r)
    {
        int pivot_val=partition(nums,l,r);
        if(pivot_val==k)
            return nums[k];
        else if(pivot_val<k)
            return quickSelect(nums,k,l,pivot_val-1);
        else
            return quickSelect(nums,k,pivot_val+1,r);
    }
    public int partition(int[] nums,int l, int r)
    {
        int p_val=nums[r];
        int si=l;
        for(int i=l;i<=r;i++)
        {
            if(nums[l]<p_val)
            {
                swap(nums,si,i);
                si++;
            }
        }
        swap(nums,si,r);
        return si;
    }
    public void swap(int[] nums, int l, int r)
    {
        int temp=nums[l];
        nums[l]=nums[r];
        nums[r]=temp;
    }
    //Exclusive time of functions
    {
        /*
        On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
        We store logs in timestamp order that describe when a function is entered or exited.
        Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
        A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
        The CPU is single threaded which means that only one function is being executed at a given time unit.
        Return the exclusive time of each function, sorted by their function id.


        Input:
        n = 2
        logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
        Output: [3, 4]
        Explanation:
        Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
        Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
        Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time.
        So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.

         */

    }
    public int[] exclusiveTime(int n, List<String> logs)
    {
        Stack<Integer> st=new Stack<>();
        int[] res=new int[logs.size()];
        String[] t=logs.get(0).split(":");
        st.push(Integer.parseInt(t[0]));
        int prev=Integer.parseInt(t[2]);
        for(int i=1;i<logs.size();i++)
        {
            String[] s=logs.get(i).split(":");
            if(s[1].equals("start"))
            {
                if(!st.isEmpty())
                    res[st.peek()]+=Integer.parseInt(s[2])-prev;
                st.push(Integer.parseInt(s[0]));
                prev=Integer.parseInt(s[2]);
            }
            else
            {
                res[st.peek()]+=Integer.parseInt(s[2])-prev+1;
                prev=Integer.parseInt(s[2])+1;
                st.pop();
            }
        }
        return res;
    }
    //Search in a Rotated Sorted Array
    {
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
    }
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
            return binarySearch(nums,target,0,nums.length-1);

        if(target<nums[0])
            return binarySearch(nums,target,pivot,nums.length-1);
        else
            return binarySearch(nums,target,0,pivot-1);

    }
    public int findPivot(int[] nums, int l, int r)
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
                if(nums[mid]>nums[r])
                    l=mid+1;
                else
                    r=mid-1;

            }
        }
        return 0;
    }
    public int binarySearch(int[] nums, int target, int l, int r)
    {
        while(l<=r)
        {
            int mid=(l+r)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                l=mid+1;
            else
                r=mid-1;
        }
        return -1;
    }

    //First and Last positions in a sorted array
    {
        /*
        Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
        Your algorithm's runtime complexity must be in the order of O(log n).
        If the target is not found in the array, return [-1, -1].
        Example 1:
        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]
        Example 2:
        Input: nums = [5,7,7,8,8,10], target = 6
        Output: [-1,-1]
         */
    }

    public int[] searchRange(int[] nums, int target)
    {
        if(nums==null || nums.length==0)
            return new int[]{-1,-1};
        int l=searchRangeHelper(nums,target);
        if(nums[l]!=target)
            return new int[]{-1,-1};
        else
            return new int[]{l,searchRangeHelper(nums,target+1)-1};
    }
    public int searchRangeHelper(int[] nums, int target)
    {
        int l=0,r=nums.length;
        while(l<r)
        {
            int mid=(l+r)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]>target)
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }

    //Pow(x,n)
    {
        /*
        Implement pow(x, n), which calculates x raised to the power n (xn).

        Example 1:

        Input: 2.00000, 10
        Output: 1024.00000
        Example 2:

        Input: 2.10000, 3
        Output: 9.26100
        Example 3:

        Input: 2.00000, -2
        Output: 0.25000
        Explanation: 2-2 = 1/22 = 1/4 = 0.25
        Note:

        -100.0 < x < 100.0
        n is a 32-bit signed integer, within the range [−231, 231 − 1]
         */
    }
    public double pow(int x, int n)
    {
        long N=n;
        if(n<0)
        {
            N=-N;
            x=1/x;
        }
        return fastPow(x,N);
    }
    public double fastPow(int x,long n)
    {
        if(n==1.0)
            return 1.0;
        if(x==0)
            return 1.0;
        double temp=fastPow(x,n>>1);
        if(temp%2==0)
            return temp*temp;
        else
            return temp*temp*x;
    }

    //Merge Intervals
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
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });
        LinkedList<int[]> res=new LinkedList<>();
        for(int[] i:intervals)
        {
            if(res.isEmpty() || res.getLast()[1]<i[1])
                res.add(i);
            else
                res.getLast()[1]=Math.max(res.getLast()[1],i[1]);
        }
        return res.toArray(new int[res.size()][]);
    }

    //Sqrt(n)
    {
        /*
        Implement int sqrt(int x).

        Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

        Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

        Example 1:

        Input: 4
        Output: 2
        Example 2:

        Input: 8
        Output: 2
        Explanation: The square root of 8 is 2.82842..., and since
                     the decimal part is truncated, 2 is returned.
         */
    }
    public int mySqrt(int x)
    {
        if(x==0)
            return 0;
        if(x==1)
            return 1;
        int l=1,r=x/2;
        while(l<r)
        {
            int mid=(l+r)/2;
            if(mid<x/mid && (mid+1)>(mid+1)/x)
                return mid;
            else if(mid>x/mid)
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }

    //Maximum Subarray
    {
        /*
        Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
        Example:
        Input: [-2,1,-3,4,-1,2,1,-5,4],
        Output: 6
        Explanation: [4,-1,2,1] has the largest sum = 6.
        Follow up:
        If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
         */
    }
    public int maxSubArray(int[] nums)
    {
        int max_so_far=nums[0],max=nums[0];
        for(int i:nums)
        {
            max_so_far=Math.max(max_so_far+i,i);
            max=Math.max(max,max_so_far);
        }
        return max;
    }

    //Edit Distance
    {
        /*
        Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

        You have the following 3 operations permitted on a word:

        Insert a character
        Delete a character
        Replace a character
        Example 1:

        Input: word1 = "horse", word2 = "ros"
        Output: 3
        Explanation:
        horse -> rorse (replace 'h' with 'r')
        rorse -> rose (remove 'r')
        rose -> ros (remove 'e')
        Example 2:

        Input: word1 = "intention", word2 = "execution"
        Output: 5
        Explanation:
        intention -> inention (remove 't')
        inention -> enention (replace 'i' with 'e')
        enention -> exention (replace 'n' with 'x')
        exention -> exection (replace 'n' with 'c')
        exection -> execution (insert 'u')
         */
    }
    public int minDistance(String word1, String word2)
    {
        int m=word1.length(),n=word2.length();
        if(m*n==0)
            return 0;
        int[][] dp=new int[m+1][n+1];
        for(int i=0;i<m;i++)
            dp[i][0]=i;
        for(int i=0;i<n;i++)
            dp[0][i]=i;
        for(int i=1;i<m+1;i++)
        {
            for(int j=1;j<n+1;j++)
            {
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1];
                else
                    dp[i][j]=1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
            }
        }
        return dp[m][n];
    }







    public static void main(String[] args)
    {
        LinkedinExplore obj=new LinkedinExplore();
        System.out.println(obj.permuteUnique(new int[]{1,1,2}));
        System.out.println(obj.getFactors(32));
        System.out.println(obj.minDistance("ros","horse"));
    }
}
