import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Linkedin_Leetcode
{
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval)
    {
        int i=0;
        while(i<intervals.size() && intervals.get(i).start<=newInterval.start)
        {
            if(intervals.get(i).end>=newInterval.start)
            {
                if(intervals.get(i).end>=newInterval.end)
                    return intervals;
                else if(intervals.get(i).end<newInterval.end)
                {
                    newInterval.start=intervals.get(i).start;
                    intervals.remove(i);
                    i--;
                }
            }
            i++;
        }
        while(i<intervals.size() && newInterval.end>=intervals.get(i).start)
        {
            if(intervals.get(i).end>newInterval.end)
            {
                newInterval.end=intervals.get(i).end;
            }
            intervals.remove(i);
        }
        intervals.add(i,newInterval);
        return intervals;
    }
    public static int evalRPN(String[] tokens)
    {
        Stack<Integer> st=new Stack<>();
        for(String s:tokens)
        {
            if(s.equals("*"))
                st.add(st.pop()*st.pop());
            else if(s.equals("+"))
                st.add(st.pop()+st.pop());
            else if(s.equals("/"))
            {
                int b=st.pop();
                int a=st.pop();
                st.add(a/b);
            }
            else if(s.equals("-"))
            {
                int b=st.pop();
                int a=st.pop();
                st.add(a-b);
            }
            else
                st.add(Integer.parseInt(s));
        }
        return st.pop();
    }
    public static String minWindow(String s, String t)
    {
        Map<Character,Integer> map_t=new HashMap<>();
        for(int i=0;i<t.length();i++)
            map_t.put(t.charAt(i),map_t.getOrDefault(t.charAt(i),0)+1);
        int sol_size=map_t.size(),ctr=0;
        String str="";
        Map<Character,Integer> window=new HashMap<>();
        int l=0,r=0;
        int len=Integer.MAX_VALUE;
        while(r<s.length())
        {
            char c=s.charAt(r);
            window.put(c,window.getOrDefault(c,0)+1);
            if(map_t.containsKey(c) && window.get(c).intValue()==map_t.get(c).intValue())
                ctr++;
            while(ctr==sol_size && l<=r)
            {
                String str1=s.substring(l,r+1);
                if(str1.length()<len)
                {
                    str=str1;
                    len=str1.length();
                }
                char ch=s.charAt(l);
                window.put(ch,window.get(ch)-1);
                if(map_t.containsKey(ch) && window.get(ch).intValue()<map_t.get(ch).intValue())
                    ctr--;
                l++;
            }
            r++;
        }
        return str;
    }
    public static boolean isMirror(TreeNode node1, TreeNode node2)
    {
        if(node1==null && node2==null)
            return true;
        if(node1==null || node2==null)
            return false;
        if(node1.val==node2.val)
            return isMirror(node1.left,node2.right) && isMirror(node1.right, node2.left);
        else
            return false;
    }
    public static boolean isSymmetric(TreeNode root)
    {
        if(root==null)
            return true;
        return isMirror(root,root);
    }
    public static boolean isSymmetric1(TreeNode root)
    {
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty())
        {
            TreeNode t1=q.poll();
            TreeNode t2=q.poll();
            if(t1==null && t2==null)
                continue;
            if(t1==null || t2==null)
                return false;
            if(t1.val!=t2.val)
                return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
    public static List<List<Integer>> levelOrder(TreeNode root)
    {
        Queue<TreeNode> q=new LinkedList();
        Queue<Integer> valq=new LinkedList<>();
        List<List<Integer>> result=new ArrayList<>();
        q.add(root);
        valq.add(root.val);
        int ct=0;
        int nodecount;

        while(!q.isEmpty())
        {
            nodecount=q.size();
            result.add(new ArrayList<Integer>(valq));
            while(nodecount>0)
            {
                TreeNode temp=q.poll();
                valq.poll();
                if(temp.left!=null)
                {
                    q.add(temp.left);
                    valq.add(temp.left.val);
                }
                if(temp.right!=null)
                {
                    q.add(temp.right);
                    valq.add(temp.right.val);
                }
                nodecount--;
            }
        }
        return result;
    }
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root)
    {
        Queue<TreeNode> q=new LinkedList();
        Queue<Integer> valq=new LinkedList<>();
        List<List<Integer>> result=new ArrayList<>();
        int rc=0;
        q.add(root);
        valq.add(root.val);
        int ct=0;
        int nodecount;

        while(!q.isEmpty())
        {
            nodecount=q.size();
            if(rc%2==0)
                result.add(new ArrayList<Integer>(valq));
            else
            {
                List<Integer> tempList=new LinkedList<>(valq);
                Stack<Integer> st=new Stack<>();
                while(!tempList.isEmpty())
                {
                    st.push(tempList.remove(tempList.size()-1));
                }
                result.add(new ArrayList<>(st));
            }
            while(nodecount>0)
            {
                TreeNode temp=q.poll();
                valq.poll();
                if(temp.left!=null)
                {
                    q.add(temp.left);
                    valq.add(temp.left.val);
                }
                if(temp.right!=null)
                {
                    q.add(temp.right);
                    valq.add(temp.right.val);
                }
                nodecount--;
            }
            rc++;
        }
        return result;
    }
    public static int maxDepth(TreeNode root)
    {
        if(root==null)
            return 0;
        int left=maxDepth(root.left)+1;
        int right=maxDepth(root.right)+1;
        return left>=right?left:right;
    }
    public static boolean canUse(String word1,String word2)
    {
        int ctr=0;
        for(int i=0;i<word1.length();i++)
        {
            if(word1.charAt(i)!=word2.charAt(i))
                ctr++;
        }
        return ctr==1;
    }
    public static int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        Set<String> visited=new HashSet<>();
        //Map<String,Integer> changeCtr=new HashMap<>();
        Queue<String> q=new LinkedList<>();
        Set<String> dict=new HashSet<>(wordList);
        q.offer(beginWord);
        //changeCtr.put(beginWord,1);
        visited.add(beginWord);
        int cnt=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String checkWord=q.poll();
                if(checkWord.equals(endWord))
                    return cnt+1;
                for(String s:wordList)
                {
                    if (!visited.contains(s) && canUse(checkWord, s) && dict.contains(s))
                    {
                        q.offer(s);
                        //changeCtr.put(s,changeCtr.get(checkWord)+1);
                        visited.add(s);
                        dict.remove(s);
                    }
                }
            }
            cnt++;
        }
        return 0;
    }
    public static boolean judgeSquareSum(int c)
    {
        int l=0,r=(int)Math.sqrt(c);
        while(l<r)
        {
            int curr=l*l+r*r;
            if(curr>c)
                r--;
            else if(curr<c)
                l++;
            else
                return true;
        }
        return false;
    }
    public static boolean isPerfectSquare(int num)
    {
        int x=1,y=num;
        while(x<=y)
        {
            int mid=(x+y)/2;
            if(mid<=num/mid && (mid+1)>num/(mid+1))
            {
                if(mid*mid==num)
                    return true;
                else
                    return false;
            }
            else if(mid*mid<num)
            {
                x=mid;
            }
            else
                y=mid;
        }
        return false;
    }
    public static boolean canPlaceFlowers(int[] flowerbed, int n)
    {
        int i = 0, count = 0;
        while(i < flowerbed.length)
        {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0))
            {
                flowerbed[i++] = 1;
                count++;
            }
            if(count>=n)
                return true;
            i++;
        }
        return false;
    }
    public static int maxProduct(int[] nums)
    {
        if(nums.length==0)
            return 0;
        int maxherepre=nums[0],minherepre=nums[0],maxsofar=nums[0],maxhere,minhere;
        for(int i=1;i<nums.length;i++)
        {
            maxhere=Math.max(Math.max(minherepre*nums[i],maxherepre*nums[i]),nums[i]);
            minhere=Math.min(Math.min(minherepre*nums[i],maxherepre*nums[i]),nums[i]);
            maxsofar=Math.max(maxhere,maxsofar);
            minherepre=minhere;
            maxherepre=maxhere;
        }
        return maxsofar;
    }
    public static TreeNode upsideDownBinaryTree(TreeNode root)
    {
        if(root==null|| root.left==null)
            return root;
        TreeNode rootleft=root.left,rootright=root.right;
        root.left=null;
        root.right=null;
        return invert(rootleft,root,rootright);
    }
    public static TreeNode invert(TreeNode rootleft,TreeNode root,TreeNode rootright)
    {
        if(rootleft==null)
            return root;
        TreeNode rootleftleft=rootleft.left,rootleftright=rootleft.right;
        rootleft.left=rootright;
        rootleft.right=root;
        return invert(rootleftleft,rootleft,rootleftright);
    }
    public static List<String> findRepeatedDnaSequences(String s)
    {
        int start=0;
        int end=10;
        Map<String,Integer> freq=new HashMap<>();
        List<String> result=new ArrayList<>();
        if(s==null||s.length()<10)
            return result;
        String str=s.substring(start,end);
        freq.put(str,1);
        start++;
        end++;
        while(end<=s.length())
        {
            String st=s.substring(start,end);
            if(freq.containsKey(st) && freq.get(st)==1)
                result.add(st);
            freq.put(st,freq.getOrDefault(st,0)+1);
            end++;
            start++;
        }
        return result;
    }
    public static int rob(int[] nums)
    {
        int[] memo=new int[nums.length+1];
        Arrays.fill(memo,-1);
        return rob(nums,nums.length-1,memo);
    }
    public static int rob(int[] nums,int i,int[] memo)
    {
        if(i<0)
            return 0;
        if(memo[i]>0)
            return memo[i];
        int result=Math.max(rob(nums,i-2,memo)+nums[i],rob(nums,i-1,memo));
        memo[i]=result;
        return memo[i];
    }
    public static int[][] multiply(int[][] A, int[][] B)
    {
        int[][] C=new int[A.length][B[0].length];
        for(int i=0;i<A.length;i++)
        {
            for(int k=0;k<A[0].length;k++)
            {
                if(A[i][k]!=0)
                    for(int j=0;j<B[0].length;j++)
                    {
                        if(B[k][j]!=0)
                            C[i][j]+=A[i][k]*B[k][j];
                    }
            }
        }
        return C;
    }
    public static List<Integer> largestValues(TreeNode root)
    {
        Queue<TreeNode> q=new LinkedList();
        Queue<Integer> valq=new LinkedList<>();
        List<Integer> result=new ArrayList<>();
        q.add(root);
        valq.add(root.val);
        int nodecount;

        while(!q.isEmpty())
        {
            nodecount=q.size();
            int max=Integer.MIN_VALUE;
            while(nodecount>0)
            {
                TreeNode temp=q.poll();
                max=Math.max(max,temp.val);
                valq.poll();
                if(temp.left!=null)
                {
                    q.add(temp.left);
                    valq.add(temp.left.val);
                }
                if(temp.right!=null)
                {
                    q.add(temp.right);
                    valq.add(temp.right.val);
                }
                nodecount--;
            }
            result.add(max);
        }
        return result;
    }
    public static int[] productExceptSelf(int[] nums)
    {
        if(nums.length == 0 || nums.length == 1)
            return nums;
        int[] result = new int[nums.length];

        int leftSide = 1;
        for(int i = 0; i < nums.length; i++)
        {
            result[i] = leftSide;
            leftSide = leftSide * nums[i];
        }

        int rightSide = 1;
        for(int i = nums.length - 1; i >= 0; i--)
        {
            result[i] = result[i] * rightSide;
            rightSide = rightSide * nums[i];
        }

        return result;
    }
    public static boolean isIsomorphic(String s, String t)
    {
        if(s.length()!=t.length())
            return false;
        Map<Character,Character> map=new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            char cs=s.charAt(i);
            char ct=t.charAt(i);
            if(!map.containsKey(cs))
            {
                map.put(cs,ct);
            }
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
            {
                map.put(ct,cs);
            }
            else
            {
                if(map.get(ct)!=cs)
                    return false;
            }
        }
        return true;
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root==null)
            return null;
        if(root==p || root==q)
            return root;
        if(p==null && q==null)
            return null;
        TreeNode l_lca=lowestCommonAncestor(root.left,p,q);
        TreeNode r_lca=lowestCommonAncestor(root.right,p,q);
        if(l_lca!=null && r_lca!=null)
            return root;
        return l_lca==null?r_lca:l_lca;
    }
    public static int shortestDistance(String[] words, String word1, String word2)
    {
        List<Integer> word1loc=new ArrayList<>();
        List<Integer> word2loc=new ArrayList<>();
        for(int i=0;i<words.length;i++)
        {
            if(words[i].equals(word1))
                word1loc.add(i);
            if(words[i].equals(word2))
                word2loc.add(i);
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<word1loc.size();i++)
        {
            for(int j=0;j<word2loc.size();j++)
            {
                int dist=Math.abs(word1loc.get(i)-word2loc.get(j));
                min=Math.min(min,dist);
            }
        }
        return min;
    }
    /*public static int findCelebrity(int n)
    {
        int candidate=0;
        for(int i=1;i<n;i++)
        {
            if(knows(candidate,i))
                candidate=i;
        }
        for(int i=0;i<n;i++)
        {
            if(i!=candidate && knows(candidate,i) && !knows(i,candidate))
                return -1;
        }
        return candidate;
    }*/
    public static int countSubstrings(String s)
    {
        int ct=0;
        for(int i=0;i<2*s.length()-1;i++)
        {
            ct+=expand(s,i);
        }
        return ct;
    }
    public static int expand(String s,int i)
    {
        int ct=0;
        int l=i/2,r=l+i%2;
        while(l>=0 && r<s.length())
        {
            if(s.charAt(l)==s.charAt(r))
            {
                ct++;
                l--;
                r++;
            }
            else
                break;
        }
        return ct;
    }
    public static int longestPalindromeSubseq(String s)
    {
        int[][] dp=new int[s.length()][s.length()];
        for(int i=0;i<s.length();i++)
            dp[i][i]=1;
        for(int l=2;l<=s.length();l++)
        {
            for(int i=0;i<s.length()-l+1;i++)
            {
                int j=i+l-1;
                if(s.charAt(i)==s.charAt(j) && l==2)
                    dp[i][j]=2;
                else if(s.charAt(i)==s.charAt(j))
                    dp[i][j]=2+dp[i+1][j-1];
                else
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][s.length()-1];
    }
    public static List<List<Integer>> findLeaves(TreeNode root)
    {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null)
            return result;
        while(root!=null)
        {
            List<Integer> leaves=new ArrayList<>();
            root=removeLeaves(root,leaves);
            result.add(leaves);
        }
        return result;
    }
    public static TreeNode removeLeaves(TreeNode root,List leaves)
    {
        if(root==null)
            return null;
        if(root.left==null && root.right==null)
        {
            leaves.add(root.val);
            return null;
        }
        root.left=removeLeaves(root.left,leaves);
        root.right=removeLeaves(root.right,leaves);
        return root;
    }
    public static List<List<Integer>> getFactors(int n)
    {
        List<List<Integer>> result=new ArrayList<>();
        computeFactors(2,n,result,new ArrayList<Integer>());
        return result;
    }
    public static void computeFactors(int start,int n,List<List<Integer>> result,List<Integer> temp)
    {
        if(n<=1)
        {
            if(temp.size()>1)
            {
                result.add(new ArrayList<>(temp));
            }
            return;
        }
        else
        {
            for(int i=start;i<=n;i++)
            {
                if(n%i==0)
                {
                    temp.add(i);
                    computeFactors(i,n/i,result,temp);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
    public static boolean exist(char[][] board, String word)
    {
        boolean[][] visited=new boolean[board.length][board[0].length];
        int len=word.length();
        int cnt=0;
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(word.charAt(0)==board[i][j])
                {
                    if(wordFind(visited,board,i,j,word,0))
                        cnt++;
                }
            }
        }
        if(cnt==0)
            return false;
        else
            return true;
    }
    public static boolean wordFind(boolean[][] visited,char[][] board,int i,int j,String word,int len)
    {
        if(len==word.length())
            return true;
        if(i<0 || i>=board.length)
            return false;
        if(j<0 || j>=board[0].length)
            return false;
        if(visited[i][j])
            return false;

        if(board[i][j]==word.charAt(len))
        {
            visited[i][j] = true;
            if (wordFind(visited, board, i + 1, j, word, len+1) || wordFind(visited, board, i - 1, j, word, len+1) || wordFind(visited, board, i, j + 1, word, len+1) || wordFind(visited, board, i, j - 1, word, len+1))
                return true;
        }
        visited[i][j]=false;
        return false;
    }
    public static int[] searchRange(int[] nums,int target)
    {
        int[] result={-1,-1};
        int start=search(nums,target);
        if(start==nums.length || nums[start]!=target)
            return result;
        else
            return new int[]{start,search(nums,target+1)-1};
    }
    public static int search(int[] nums,int target)
    {
        int start=0,end=nums.length;
        while(start<end)
        {
            int mid=(start+end)/2;
            if(nums[mid]>=target)
                end=mid;
            else
                start=mid+1;
        }
        return start;
    }
    public static List<Integer> closestKValues(TreeNode root, double target, int k)
    {
        List<Integer> result=new ArrayList<>();
        Stack<Integer> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();
        successor(s1,root,target);
        predecessor(s2,root,target);
        while(k>0)
        {
            if(s1.isEmpty())
                result.add(s2.pop());
            else if(s2.isEmpty())
                result.add(s1.pop());
            else if(Math.abs(s1.peek()-target)<Math.abs(s2.peek()-target))
                result.add(s1.pop());
            else
                result.add(s2.pop());
            k--;
        }
        return result;
    }
    public static void successor(Stack<Integer> s,TreeNode root,double target)
    {
        if(root==null)
            return;
        successor(s,root.right,target);
        if(root.val<=target)
            return;
        s.push(root.val);
        successor(s,root.left,target);
    }
    public static void predecessor(Stack<Integer> s, TreeNode root,double target)
    {
        if(root==null)
            return;
        predecessor(s,root.left,target);
        if(root.val>target)
            return;
        s.push(root.val);
        predecessor(s,root.right,target);
    }
    public static boolean canPartitionKSubsets(int[] nums, int k)
    {
        int sum=Arrays.stream(nums).sum();
        if(sum%k!=0 || k<=0)
            return false;
        boolean[] visited=new boolean[nums.length];
        return canPartition(nums,visited,sum/k,0,0);
    }
    public static boolean canPartition(int[] nums,boolean[] visited,int target,int curr_sum,int index)
    {
        if(curr_sum==target)
            return true;
        if(curr_sum>target)
            return false;
        for(int i=index;i<nums.length;i++)
        {
            if(!visited[i])
            {
                visited[i] = true;
                if(canPartition(nums,visited,target,curr_sum+nums[i],i+1))
                    return true;
                visited[i]=false;
            }
        }
        return false;
    }
    public static List<List<Integer>> subsets(int[] nums)
    {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    public static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
    public static int minDistance(String word1, String word2)
    {
        int n=word1.length(),m=word2.length();
        if(n*m==0)
            return n+m;
        int[][] dp=new int [n+1][m+1];
        for(int i=0;i<dp.length;i++)
            dp[i][0]=i;
        for(int j=0;j<dp[0].length;j++)
            dp[0][j]=j;
        for(int i=1;i<n+1;i++)
        {
            for(int j=1;j<m+1;j++)
            {
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
                else
                    dp[i][j]=1+Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
            }
        }
        return dp[n][m];
    }
    public static boolean isNumber(String s)
    {
        s = s.trim();
        String pattern = "^[+-]?([0-9]+\\.[0-9]*|[0-9]*\\.[0-9]+|[0-9]+)([eE][+-]?[0-9]+)?$";
        Pattern patternObject = Pattern.compile(pattern);
        Matcher m = patternObject.matcher(s);
        return m.find();
    }
    public static TreeNode buildTree(int[] inorder, int[] postorder)
    {
        if(postorder==null || inorder==null || postorder.length==0 || inorder.length==0)
            return null;
        int n=postorder.length-1;
        Map<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<inorder.length;i++)
            hm.put(inorder[i],i);
        return buildPost(postorder,0,postorder.length-1,inorder,0,inorder.length-1,hm);
    }
    public static TreeNode buildPost(int[] postorder,int ps,int pe,int[] inorder,int is,int ie,Map<Integer,Integer> hm)
    {
        if(ps>pe || is>ie)
            return null;
        int breakIndex=hm.get(postorder[pe]);
        TreeNode root=new TreeNode(postorder[pe]);
        root.left=buildPost(postorder,ps,ps+breakIndex-is-1,inorder,is,breakIndex-1,hm);
        root.right=buildPost(postorder,ps+breakIndex-is,pe-2,inorder,breakIndex+1,ie,hm);
        return root;
    }
    public static void main(String[] args)
    {
        //[[1,2],[3,5],[6,7],[8,10],[12,16]]
        List<Interval> intervals=new ArrayList<>();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,5));
        intervals.add(new Interval(6,7));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(12,16));
        insert(intervals,new Interval(4,8));
        TreeNode node=new TreeNode(3);
        node.left=new TreeNode(1);
        node.right=new TreeNode(4);
        node.left.right=new TreeNode(2);
        System.out.println(minWindow("cabwefgewcwaefgcf","cae"));
        System.out.println(largestValues(node));
        String[] wordList={"hot","dot","dog","lot","log","cog"};
        List<String> words=Arrays.asList(wordList);
        System.out.println(ladderLength("hit","cog",words));
        if(isPerfectSquare(16))
            System.out.println("Success");
        int[] nums={-2,3,-4};
        System.out.println(maxProduct(nums));
        String str="AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(findRepeatedDnaSequences(str));
        int[] vals={1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(vals)));
        if(isIsomorphic("paper","tifle"))
            System.out.println("is Isomorphic");
        System.out.println(countSubstrings("aaa"));
        System.out.println(longestPalindromeSubseq("a"));
        System.out.println("factors are");
        System.out.println(getFactors(16));
        char[][] board={{'C','A','A'},{'A','A','A'},{'B','C','D'}};
        if(exist(board,"AAB"))
            System.out.println("EXISTS");
        System.out.println(searchRange(new int[]{1,2,3,4,4,4,5,6},4));
        System.out.println(closestKValues(node,2.00000,1));
        System.out.println(canPartitionKSubsets(new int[]{2,2,2,2,3,4,5},4));
        System.out.println(subsets(new int[]{1,2,3}));
        System.out.println(minDistance("horse","ros"));
        int [] in={9,3,15,20,7};
        int [] pos={9,15,7,20,3};
        buildTree(in,pos);




    }
}
