import java.util.*;

public class UberExplore
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
        if(nums==null || nums.length<2)
            return new int[]{-1,-1};
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            int c=target-nums[i];
            if(map.containsKey(c))
                return new int[]{map.get(c),i};
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

    //First and Last Position of element in a sorted array
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
        int lo=extremeInsertionIndex(nums,target);
        if(lo>=nums.length || nums[lo]!=target)
            return new int[]{-1,-1};
        return new int[]{lo,extremeInsertionIndex(nums, target+1)-1};
    }
    public int extremeInsertionIndex(int[] nums, int target)
    {
        int lo=0,hi=nums.length;
        while(lo<hi)
        {
            int mid=lo+(hi-lo)/2;
            if(nums[mid]>=target)
                hi=mid;
            else
                lo=mid+1;
        }
        return lo;
    }

    //Group Anagrams
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
    public List<List<String>> groupAnagrams(String[] str)
    {
        if(str.length==0)
            return new ArrayList<>();
        Map<String,List<String>> map=new HashMap<>();
        for(String s:str)
        {
            char[] c=s.toCharArray();
            Arrays.sort(c);
            String key=new String(c);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
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
    public String minnWindow(String s, String t)
    {
        Map<Character,Integer> map_t=new HashMap<>();
        for(char c:t.toCharArray())
            map_t.put(c,map_t.getOrDefault(c,0)+1);
        int size_t=map_t.size();
        int l=0,r=0,len=Integer.MAX_VALUE,ctr=0;
        String res="";
        Map<Character,Integer> window=new HashMap<>();
        while(r<s.length())
        {
            char c=s.charAt(r);
            window.put(c,window.getOrDefault(c,0)+1);
            if(map_t.containsKey(c) && map_t.get(c).equals(window.get(c)))
                ctr++;
            while(ctr==size_t && l<=r)
            {
                String str=s.substring(l,r+1);
                if(str.length()<len)
                {
                    res=str;
                    len=str.length();
                }
                char ch=s.charAt(l);
                if(map_t.containsKey(ch) && map_t.get(ch).equals(window.get(ch)))
                    ctr--;
                window.put(ch,window.get(ch)-1);
                l++;
            }
            r++;
        }
        return res;
    }

    //Validate IP Address
    {
        /*
        Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

        IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

        Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

        IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
        The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one.
        Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones,
        so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

        However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

        Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

        Note: You may assume there is no extra space or special characters in the input string.

        Example 1:
        Input: "172.16.254.1"

        Output: "IPv4"

        Explanation: This is a valid IPv4 address, return "IPv4".
        Example 2:
        Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

        Output: "IPv6"

        Explanation: This is a valid IPv6 address, return "IPv6".
        Example 3:
        Input: "256.256.256.256"

        Output: "Neither"

        Explanation: This is neither a IPv4 address nor a IPv6 address.
         */
    }
    public String validIPAddress(String IP)
    {
        if(isIPv4(IP))
            return "IPv4";
        else if(isIPv6(IP.toUpperCase()))
            return "IPv6";
        else
            return "Neither";
    }
    public boolean isIPv4(String s)
    {
        int cnt=0;
        for(char c:s.toCharArray())
            if(c=='.')
                cnt++;

        if(cnt!=3)
            return false;
        String[] bytes=s.split("\\.");
        if(bytes.length!=4)
            return false;
        for(String b:bytes)
        {
            if(b==null || b.length()==0 || b.length()>3)
                return false;
            for(char c:b.toCharArray())
                if(!Character.isDigit(c))
                    return false;

            int b_val=Integer.parseInt(b);
            if(!String.valueOf(b_val).equals(b) || b_val<0 || b_val>255)
                return false;

        }
        return true;
    }

    public boolean isIPv6(String s)
    {
        int cnt=0;
        for(char c:s.toCharArray())
            if(c==':')
                cnt++;

        if(cnt!=7)
            return false;
        String[] bytes=s.split("\\:");
        if(bytes.length!=8)
            return false;
        for(String b:bytes)
        {
            if(b==null || b.length()==0 || b.length()>4)
                return false;

            for(char c:b.toCharArray())
                if(!Character.isDigit(c) && (c<'A' || c>'F'))
                    return false;
        }
        return true;
    }

    //Merge K Sorted List
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
        if(lists==null || lists.length==0)
            return null;
        return mergeLists(lists,0,lists.length);
    }

    public ListNode mergeLists(ListNode[] lists, int start, int end)
    {
        if(start==end)
            return lists[start];
        if(start>end)
            return null;
        int mid=(start+end)/2;
        ListNode left=mergeLists(lists,start,mid);
        ListNode right=mergeLists(lists,mid+1,end);
        return mergeTwoLists(left,right);
    }

    public ListNode mergeTwoLists(ListNode left, ListNode right)
    {
        if(left==null && right==null)
            return null;
        else if(left==null)
            return right;
        else if(right==null)
            return left;
        else
        {
            if(left.data<right.data)
            {
                left.next=mergeTwoLists(left.next,right);
                return left;
            }
            else
            {
                right.next=mergeTwoLists(left,right.next);
                return right;
            }
        }
    }

    //Reconstruct Itenary
    {
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
    }
    public List<String> findItinerary(List<List<String>> tickets)
    {
        if(tickets==null || tickets.size()==0)
            return new ArrayList<>();
        Map<String,PriorityQueue<String>> map=new HashMap<>();
        for(List<String> str:tickets)
        {
            map.putIfAbsent(str.get(0),new PriorityQueue<>());
            map.get(str.get(0)).offer(str.get(1));
        }
        List<String> res=new ArrayList<>();
        res.add("JFK");
        dfs(res,map,"JFK");
        return res;
    }
    public void dfs(List<String> res, Map<String,PriorityQueue<String>> map, String s)
    {
        while(map.containsKey(s) && !map.get(s).isEmpty())
        {
            dfs(res,map,map.get(s).poll());
        }
        res.add(0,s);
    }


    //Evaluate Division
    {
        /*
        Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
        Example:
        Given a / b = 2.0, b / c = 3.0.
        queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
        return [6.0, 0.5, -1.0, 1.0, -1.0 ].
        The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

        According to the example above:
        equations = [ ["a", "b"], ["b", "c"] ],
        values = [2.0, 3.0],
        queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].


        The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
         */
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries)
    {
        Map<String,Map<String,Double>> graph=buildGraph(equations,values);
        double[] result=new double[queries.size()];
        for(int i=0;i<result.length;i++)
            result[i]=graphSearch(graph,queries.get(i).get(0),queries.get(i).get(1),new HashSet<>());
        return result;
    }
    public double graphSearch(Map<String,Map<String,Double>> graph,String u, String v,HashSet visited)
    {
        if(!graph.containsKey(u))
            return -1.0;
        if(graph.get(u).containsKey(v))
            return graph.get(u).get(v);
        visited.add(u);
        for(Map.Entry<String,Double> n:graph.get(u).entrySet())
        {
            if(visited.contains(n.getKey()))
                continue;
            double prod=graphSearch(graph,n.getKey(),v,visited);
            if(prod!=-1)
                return prod*n.getValue();
        }
        return -1.0;
    }
    public Map<String,Map<String,Double>> buildGraph(List<List<String>> equations,double[] values)
    {
        Map<String,Map<String,Double>> graph=new HashMap<>();
        for(int i=0;i<values.length;i++)
        {
            String u=equations.get(i).get(0);
            String v=equations.get(i).get(1);
            double val=values[i];
            graph.putIfAbsent(u,new HashMap<>());
            graph.get(u).put(v,val);
            graph.putIfAbsent(v,new HashMap<>());
            graph.get(v).put(u,1/val);
        }
        return graph;
    }


    //Find Duplicate Subtrees
    {
        /*
            Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
            Two trees are duplicate if they have the same structure with same node values.
            Example 1:

                    1
                   / \
                  2   3
                 /   / \
                4   2   4
                   /
                  4
            The following are two duplicate subtrees:

                  2
                 /
                4
            and

                4
            Therefore, you need to return above trees' root in the form of a list.
         */
    }
    public List<TreeNode> findDuplicateSubtrees(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        List<TreeNode> res=new ArrayList<>();
        serialize(root,res,new HashMap<>());
        return res;
    }

    public String serialize(TreeNode root,List<TreeNode> res, Map<String,Integer> map)
    {
        if(root==null)
            return "#";
        String s=root.val+","+serialize(root.left,res,map)+","+serialize(root.right,res,map);
        map.put(s,map.getOrDefault(s,0)+1);
        if(map.get(s)==2)
            res.add(root);
        return s;
    }


    //Print Binary Tree
    {
        /*
        Print a binary tree in an m*n 2D string array following these rules:

        The row number m should be equal to the height of the given binary tree.
        The column number n should always be an odd number.
        The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
        Each unused space should contain an empty string "".
        Print the subtrees following the same rules.
        Example 1:
        Input:
             1
            /
           2
        Output:
        [["", "1", ""],
         ["2", "", ""]]
        Example 2:
        Input:
             1
            / \
           2   3
            \
             4
        Output:
        [["", "", "", "1", "", "", ""],
         ["", "2", "", "", "", "3", ""],
         ["", "", "4", "", "", "", ""]]
        Example 3:
        Input:
              1
             / \
            2   5
           /
          3
         /
        4
        Output:

        [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
         ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
         ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
         ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
        Note: The height of binary tree is in the range of [1, 10].
         */
    }
    public List<List<String>> printTree(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        int height=getHeight(root);
        int width=(1<<height)-1;
        String[][] res=new String[height][width];
        for(String[] str:res)
            Arrays.fill(str,"");
        fill(res,root,0,0,width);
        List<List<String>> ans=new ArrayList<>();
        for(String[] str:res)
            ans.add(Arrays.asList(str));
        return ans;

    }

    public void fill(String[][] res,TreeNode root,int i,int l, int r)
    {
        if(root==null)
            return;
        int mid=(l+r)/2;
        res[i][mid]=""+root.val;
        fill(res,root.left,i+1,l,mid);
        fill(res,root.right,i+1,mid+1,r);
    }

    public int getHeight(TreeNode root)
    {
        if(root==null)
            return 0;
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }
    // All Nodes distance k in Binary Tree
    {
        /*
            We are given a binary tree (with root node root), a target node, and an integer value K.
            Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
            Example 1:

            Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

            Output: [7,4,1]

            Explanation:
            The nodes that are a distance 2 from the target node (with value 5)
            have values 7, 4, and 1.

            Note that the inputs "root" and "target" are actually TreeNodes.
            The descriptions of the inputs above are just serializations of these objects.

            Note:

            The given tree is non-empty.
            Each node in the tree has unique values 0 <= node.val <= 500.
            The target node is a node in the tree.
            0 <= K <= 1000.
         */
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K)
    {
        if(root==null)
            return new ArrayList<>();
        Map<TreeNode,TreeNode> parentMap=new HashMap<>();
        buildMap(root,null,parentMap);
        int dist=0;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(null);
        q.offer(target);
        Set<TreeNode> visited=new HashSet<>();
        visited.add(target);
        visited.add(null);
        List<Integer> res=new ArrayList<>();
        while(!q.isEmpty())
        {
            TreeNode curr=q.poll();
            if(curr==null)
            {
                if(dist==K)
                {
                    for(TreeNode n:q)
                        res.add(n.val);
                    return res;
                }
                dist++;
                q.offer(null);
            }
            else
            {
                if(!visited.contains(root.left))
                {
                    visited.add(root.left);
                    q.offer(root.left);
                }
                if(!visited.contains(root.right))
                {
                    visited.add(root.right);
                    q.offer(root.right);
                }
                TreeNode parent=parentMap.get(root);
                if(!visited.contains(parent))
                {
                    visited.add(parent);
                    q.offer(parent);
                }

            }
        }
        return new ArrayList<>();

    }

    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K)
    {
        Map<TreeNode,TreeNode> parentmap=new HashMap<>();
        buildMap(root,null,parentmap);
        Queue<TreeNode> q =new LinkedList<>();
        q.offer(target);
        Set<TreeNode> visited=new HashSet<>();
        visited.add(target);
        visited.add(null);
        int d=0;
        List<Integer> res=new ArrayList<>();
        while(!q.isEmpty())
        {
            if(d==K)
            {
                for(TreeNode n:q)
                    res.add(n.val);
                return res;
            }
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                TreeNode curr=q.poll();
                if(!visited.contains(curr.left))
                {
                    visited.add(curr.left);
                    q.offer(curr.left);
                }
                if(!visited.contains(curr.right))
                {
                    visited.add(curr.right);
                    q.offer(curr.right);
                }
                TreeNode parent=parentmap.get(curr);
                if(!visited.contains(parent))
                {
                    visited.add(parent);
                    q.offer(parent);
                }
            }
            d++;
        }
        return new ArrayList<>();
    }
    public void buildMap(TreeNode root, TreeNode parent, Map<TreeNode,TreeNode> map)
    {
        map.put(root,parent);
        buildMap(root.left,parent,map);
        buildMap(root.right,parent,map);
    }


    //Trapping Rain Water
    {
        /*
        Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
        The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

        Example:
        Input: [0,1,0,2,1,0,1,3,2,1,2,1]
        Output: 6
         */
    }
    public int trap(int[] height)
    {
        int l=0,r=height.length-1,lmax=0,rmax=0,max=0;
        while(l<r)
        {
            lmax=Math.max(lmax,height[l]);
            rmax=Math.max(rmax,height[r]);
            if(lmax<rmax)
            {
                max+=(lmax-height[l]);
                l++;
            }
            else
            {
                max+=(rmax-height[r]);
                r--;
            }
        }
        return max;
    }
    public int trap_stack(int[] height)
    {
        if(height==null || height.length<2)
            return 0;
        Stack<Integer> st=new Stack<>();
        int i=0,ans=0;
        while(i<height.length)
        {
            if(st.isEmpty() || height[i]<height[st.peek()])
                st.push(i++);
            else
            {
                int pre=st.pop();
                if(!st.isEmpty())
                {
                    int minHeight=Math.min(height[i],height[st.peek()])-height[pre];
                    int distance=i-st.peek()-1;
                    ans+=minHeight*distance;
                }
            }
        }
        return ans;
    }

    //Basic Calculator 1
    {
        /*
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
    }
    public int calculate1(String s)
    {
        if(s==null || s.length()==0)
            return 0;
        Stack<Integer> st=new Stack<>();
        int sign=1;
        int result=0;
        for(int i=0;i<s.length();i++)
        {
            if(Character.isDigit(s.charAt(i)))
            {
                int start=i;
                while(i+1<s.length() && Character.isDigit(s.charAt(i)))
                    i++;
                int val=Integer.parseInt(s.substring(start,i+1));
                result+=sign*val;
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
                result=result*st.pop()+st.pop();
            }
        }
        return result;
    }


    //Basic Calculator 2
    {
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
    }
    public int calculate2(String s)
    {
        if(s==null || s.length()==0)
            return 0;
        Stack<Integer> st=new Stack<>();
        int res=0,num=0;
        char sign='+';
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(Character.isDigit(c))
                num=num*10+(c-'0');
            if(c=='+' || c=='-' || c=='/' || c=='*' || i==s.length()-1)
            {
                if(sign=='+')
                    st.push(num);
                else if(sign=='-')
                    st.push(-(num));
                else if(sign=='/')
                    st.push(st.pop()/num);
                else if(sign=='*')
                    st.push(st.pop()*num);
                sign=c;
                num=0;
            }
        }
        while (!st.isEmpty())
            res+=st.pop();
        return res;
    }

    //Basic Calculator 3
    {
        /*
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
    }
    public int calculate3(String s)
    {
        if(s==null || s.length()==0)
            return 0;
        Stack<Integer> st=new Stack<>();
        int res=0,num=0;
        char sign='+';
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(Character.isDigit(c))
                num=num*10+(c-'0');
            if(c=='(')
            {
                int cnt=0,j=i;
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
            if(c=='+' || c=='-' || c=='*' || c=='/' || i==s.length()-1)
            {
                if(sign=='+')
                    st.push(num);
                else if(sign=='-')
                    st.push(-num);
                else if(sign=='/')
                    st.push(st.pop()/num);
                else if(sign=='*')
                    st.push(st.pop()*num);
                sign=c;
                num=0;
            }
        }
        while(!st.isEmpty())
            res+=st.pop();
        return res;
    }


    //Meeting Rooms 2
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
        if(intervals==null || intervals.length==0)
            return 0;
        int[] start=new int[intervals.length];
        int[] end=new int[intervals.length];
        for(int i=0;i<intervals.length;i++)
        {
            start[i]=intervals[i][1];
            end[i]=intervals[i][0];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int ctr=0,i=0,j=0;
        while(i<start.length)
        {
            if(start[i]<end[j])
                ctr++;
            else
                j++;
            i++;
        }
        return ctr;
    }
    public int minMeetingRoomsPriorityQueue(int[][] intervals)
    {
        if(intervals==null || intervals.length==0)
            return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                return o1[0]-o2[0];
            }
        });
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        pq.offer(intervals[0][1]);
        for(int i=1;i<intervals.length;i++)
        {
            if(intervals[i][0]>=pq.peek())
                pq.poll();
            pq.offer(intervals[i][1]);
        }
        return pq.size();
    }

    //Task Scheduler
    {
        /*
            Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
            Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
            However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
            You need to return the least number of intervals the CPU will take to finish all the given tasks.

            Example:

            Input: tasks = ["A","A","A","B","B","B"], n = 2
            Output: 8
            Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


            Note:

            The number of tasks is in the range [1, 10000].
            The integer n is in the range [0, 100].
         */
    }

    public int leastInterval(char[] tasks, int n)
    {
        if(n==0)
            return tasks.length;
        Map<Character,Integer> freqMap=new HashMap<>();
        for(char c: tasks)
            freqMap.put(c,freqMap.getOrDefault(c,0)+1);
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(char c:freqMap.keySet())
            pq.offer(freqMap.get(c));
        int timer=0;
        while(!pq.isEmpty())
        {
            int i=0;
            List<Integer> temp=new ArrayList<>();
            while(i<=n)
            {
                if(!pq.isEmpty())
                {
                    if(pq.peek()>1)
                        temp.indexOf(pq.poll()-1);
                    else
                        pq.poll();
                }
                timer++;
                if(pq.isEmpty() && temp.isEmpty())
                    break;
            }
            for(int t:temp)
                pq.offer(t);
        }
        return timer;
    }


    //Employee Free Time

    {
        /*
        We are given a list schedule of employees, which represents the working time for each employee.

        Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

        Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

        (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.



        Example 1:

        Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
        Output: [[3,4]]
        Explanation: There are a total of three employees, and all common
        free time intervals would be [-inf, 1], [3, 4], [10, inf].
        We discard any intervals that contain inf as they aren't finite.
        Example 2:

        Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
        Output: [[5,6],[7,9]]
     */
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule)
    {
        if(schedule==null || schedule.size()==0)
            return new ArrayList<>();
        PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b)
            {
                return a.start-b.start;
            }
        });
        for(List<Interval> temp:schedule)
            pq.addAll(temp);

        List<Interval> res=new ArrayList<>();
        Interval temp=pq.poll();
        while(!pq.isEmpty())
        {
            if(temp.end<pq.peek().start)
            {
                res.add(new Interval(temp.end,pq.peek().start));
                temp=pq.poll();
            }
            else
            {
                if(temp.end<pq.peek().end)
                    temp=pq.peek();
                pq.poll();
            }
        }
        return res;
    }

    //Letter Combinatios of a Phone Number
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
        if(digits==null || digits.length()==0)
            return new ArrayList<>();
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
        letterCombinationHelper(res,0,map,"",digits);
        return res;
    }
    public void letterCombinationHelper(List<String> res, int start,Map<Character,String> map,String s, String digits)
    {
        if(s.length()==digits.length())
        {
            res.add(s);
            return;
        }
        String val=map.get(digits.charAt(start));
        for(char c:val.toCharArray())
            letterCombinationHelper(res,start+1,map,s+String.valueOf(c),digits);

    }

    //Subsets
    {
        /*
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
    }

    public List<List<Integer>> subsets(int[] nums)
    {
        if(nums==null || nums.length==0)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        subsetHelper(res,nums,new ArrayList<>(),0);
        return res;
    }

    public void subsetHelper(List<List<Integer>> res, int[] nums,List<Integer> temp,int start)
    {
        if(start>nums.length)
            return;
        res.add(new ArrayList<>(temp));
        for(int i=start;i<nums.length;i++)
        {
            temp.add(nums[i]);
            subsetHelper(res,nums,temp,i+1);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args)
    {
        UberExplore obj=new UberExplore();
        List<List<String>> tickets=new ArrayList<>();
        tickets.add(Arrays.asList("JFK","KUL"));
        tickets.add(Arrays.asList("JFK","NRT"));
        tickets.add(Arrays.asList("NRT","JFK"));
        System.out.println(obj.trap_stack(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(obj.calculate3("2*(5+5*2)/3+(6/2+8)"));
        System.out.println(obj.minMeetingRoomsPriorityQueue(new int[][]{{1,10},{2,7},{3,19},{8,12},{10,20},{11,30}}));
        System.out.println(obj.subsets(new int[]{1,2,3}));

    }














































}