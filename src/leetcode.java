import java.util.*;
class AlienGraph
{
    ArrayList<Integer>[] adjList;
    public AlienGraph (int n)
    {
        adjList=new ArrayList[n];
        for(int i=0;i<n;i++)
        {
            adjList[i]=new ArrayList<>();
        }
    }
    public void addEdge(int to,int from)
    {
        adjList[to].add(from);
    }
    public void topologicalSort()
    {
        Stack<Integer> st=new Stack<>();
        boolean[] visited=new boolean[adjList.length];
        for(int i=0;i<adjList.length;i++)
        {
            if(!visited[i])
                topologicalSortUtil(i,visited,st);
        }
        while(!st.isEmpty())
            System.out.print((char)('a'+st.pop())+" ");
    }
    public void topologicalSortUtil(int i,boolean[] visited,Stack<Integer> st)
    {
        if(visited[i])
            return;
        else
        {
            visited[i]=true;
            for(int adjvertex:adjList[i])
            {
                if(!visited[adjvertex])
                    topologicalSortUtil(adjvertex,visited,st);
            }
            st.push(i);
        }
    }
}
public class leetcode
{
    public static Node prev=null;

    public static int lengthSubstring(String s)
    {
        int n=s.length();
        int i=0,j=0;
        int ans=0;
        Set<Character> set=new HashSet<Character>();
        while(i<n && j<n)
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
    public static int longestCommonSubstring(String str)
    {
        int start=0,maxlength=0;
        int n=str.length();
        int[][] table=new int[n][n];
        maxlength=1;
        for(int i=0;i<n;i++)
        {
            table[i][i]=1;
        }
        for(int i=0;i<n-1;i++)
        {
            if(str.charAt(i)==str.charAt(i+1))
            {
                table[i][i+1]=1;
                start=i;
                maxlength=2;
            }
        }
        for(int k=3;k<n;k++)
        {
            for(int i=0;i<n-k+1;i++)
            {
                int j=i+k-1;
                if(table[i+1][j-1]==1 && str.charAt(i)==str.charAt(j))
                {
                    table[i][j]=1;
                    if(k>maxlength)
                    {
                        maxlength=k;
                        start=i;
                    }
                }
            }
        }
        printSubstring(start,maxlength,str);
        return maxlength;
    }
    public static void printSubstring(int start,int maxlength,String str)
    {
        String s=str.substring(start,start+maxlength);
        System.out.print(s);
    }

    public static String longestPalindromicSubstring(String str)
    {
        int start=0,end=0;
        int len1,len2,len;
        int n=str.length();
        for(int i=0;i<n;i++)
        {
            len1=expand(str,i,i);
            len2=expand(str,i,i+1);
            len=Math.max(len1,len2);
            if(len>end-start)
            {
                start=i-(len-1)/2;
                end=i+len/2;
            }
        }
        return str.substring(start,end+1);

    }
    public static int expand(String str,int start,int end)
    {
        int L=start,R=end;
        while(L>0&&R<str.length()&&str.charAt(L)==str.charAt(R))
        {
            L--;
            R++;
        }
        return R-L-1;
    }
    public static int reverseInt(int i)
    {
        int rev=0;
        while(i!=0)
        {
            int j=i%10;
            rev=rev*10+j;
            i=i/10;
        }
        return rev;
    }
    public static int Intreverse(int x)
    {
        boolean isPositive = x >= 0;
        char[] chars = (x + "").substring(isPositive ? 0 : 1).toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars)
        {
            sb.append(c);
        }
        long res = isPositive ? Long.valueOf(sb.reverse().toString()) : - Long.valueOf(sb.reverse().toString());
        return  res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }
    public static boolean palindromereverse(int x)
    {
        int rev=0;
        while(x>=rev)
        {
            int j=x%10;
            rev=rev*10+j;
            x=x/10;
        }
        if(rev==x || rev/10==x)
            return true;
        else
            return false;
    }
    public static boolean isPalindrome(int x)
    {
        if(x < 0) return false;
        String s = x + "";
        char[] c = s.toCharArray();
        int len = c.length;
        for(int i = 0; i < len / 2; i++)
        {
            if(c[i] != c[len - i - 1]) return false;
        }
        return true;
    }
    public static int maxContainer(int[] heights)
    {
        int maxArea=0;
        int l=0,r=heights.length-1;
        while(l<r)
        {
            maxArea=Math.max(maxArea,(Math.min(heights[l],heights[r])*r-l));
            if(heights[l]>heights[r])
                r--;
            else
                l++;
        }
        return maxArea;
    }

    public static String intToRoman(int num)
    {
        String[] str={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] vals={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<vals.length;i++)
        {
            while(num>=vals[i])
            {
                num-=vals[i];
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }
    public static int romanToInt(String str)
    {
        char[] c=str.toCharArray();
        int num=0;
        for(int i=0;i<c.length;i++)
        {
            if(c[i]=='I')
            {
                if(i<c.length-1 && c[i+1]=='V')
                {
                    num+=4;
                    i++;
                    continue;
                }
                else if(i<c.length-1 && c[i+1]=='X')
                {
                    num+=9;
                    i++;
                    continue;
                }
                else
                {
                    num+=1;
                }
            }
            else if(c[i]=='X')
            {
                if(i<c.length-1 && c[i+1]=='L')
                {
                    num+=40;
                    i++;
                    continue;
                }
                else if(i<c.length-1 && c[i+1]=='C')
                {
                    num+=90;
                    i++;
                    continue;
                }
                else
                    num+=10;
            }
            else if(c[i]=='C')
            {
                if(i<c.length-1 && c[i+1]=='D')
                {
                    num+=400;
                    i++;
                    continue;
                }
                else if(i<c.length-1 && c[i+1]=='M')
                {
                    num+=900;
                    i++;
                    continue;
                }
                else
                    num+=100;
            }
            else if(c[i]=='V')
                num+=5;
            else if(c[i]=='L')
                num+=50;
            else if(c[i]=='D')
                num+=500;
            else if(c[i]=='M')
                num+=1000;
            else
            {
                System.out.println("Incorrect character");
                return -1;
            }
        }
        return num;
    }
    public static String longestCommonPrefix(String[] strs)
    {
        if(strs==null || strs.length==0)
            return "";
        return longestCommonPrefix(strs,0,strs.length-1);
    }
    public static String longestCommonPrefix(String[] strs, int start, int end)
    {
        if(start==end)
            return strs[start];
        int mid=(start+end)/2;
        String lcpleft=longestCommonPrefix(strs,start,mid);
        String lcpright=longestCommonPrefix(strs,mid+1,end);
        return commonPrefix(lcpleft,lcpright);
    }
    public static String commonPrefix(String left, String right)
    {
        int min=Math.min(left.length(),right.length());
        for(int i=0;i<min;i++)
        {
            if(left.charAt(i)!=right.charAt(i))
                return left.substring(0,i);
        }
        return left.substring(0,min);
    }
    public static List<List<Integer>> threeSum(int[] nums)
    {
        if(nums.length<3)
            return null;
        Set<List<Integer>> res=new HashSet<>();
        Arrays.sort(nums);

        for(int start=0;start<nums.length-2;start++)
        {
            int k=start+1;
            int end=nums.length-1;
            while(k<end)
            {
                if(nums[start]+nums[k]+nums[end]==0)
                {
                    res.add(Arrays.asList(nums[start], nums[k], nums[end]));
                    k++;
                    end--;
                }
                else if(nums[start]+nums[k]+nums[end]>0)
                {
                    end--;
                }
                else
                    k++;
            }
        }
        return new ArrayList<>(res);
    }
    public static int closestThreeSum(int[] nums,int target)
    {
        Arrays.sort(nums);
        int closestDist=Integer.MAX_VALUE;
        int finalSum=0;
        for(int i=0;i<nums.length-2;i++)
        {
            int k=i+1;
            int j=nums.length-1;
            while(k<j)
            {
                int sum=nums[i]+nums[k]+nums[j];
                if(sum==target)
                    return sum;
                int currDist=Math.abs(sum-target);
                if(currDist<closestDist)
                {
                    closestDist=currDist;
                    finalSum=sum;
                }
                if(sum<target)
                    k++;
                else
                    j--;
            }
        }
        return finalSum;
    }
    public static List<String> letterCombinations(String digits)
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
        keypad.put('9',"xyz");
        List<String> result=new ArrayList<>();
        compute("",digits,0,keypad,result);
        return result;
    }
    public static void compute(String prefix,String digits,int offset,Map<Character,String> map,List<String> res)
    {
        if(offset>=digits.length())
        {
            res.add(prefix);
            return;
        }
        String val=map.get(digits.charAt(offset));
        for(int i=0;i<val.length();i++)
        {
            compute(prefix+val.charAt(i),digits,offset+1,map,res);
        }
    }
    public static List<List<Integer>> fourSum(int[] nums, int target)
    {
        Set<List<Integer>> res=new HashSet<>();
        if(nums.length<4)
            return new ArrayList<>(res);
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++)
        {
            for(int j=i+1;j<nums.length-2;j++)
            {
                int k=j+1;
                int l=nums.length-1;
                while(k<l)
                {
                    int sum=nums[i]+nums[j]+nums[k]+nums[l];
                    if(sum==target)
                    {
                        res.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                        k++;l--;
                    }
                    else if(sum<target)
                    {
                        k++;
                    }
                    else
                        l--;
                }
            }
        }
        return new ArrayList<>(res);

    }
    public static ListNode removeFromNth(ListNode head, int t)
    {
        ListNode dummy=head;
        ListNode first=dummy;
        ListNode second=dummy;
        for(int i=0;i<t;i++)
        {
            first=first.next;
            i++;
        }
        while(first!=null)
        {
            first=first.next;
            second=second.next;
        }
        second.next=second.next.next;
        return dummy;
    }
    public static boolean isValid(String str)
    {
        int len=str.length();
        if(str.length()<0 || str.isEmpty())
            return true;
        if(str.length()<2)
            return false;
        Stack<Character> s=new Stack<>();
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='{' || str.charAt(i)=='[' || str.charAt(i)=='(')
            {
                s.push(str.charAt(i));
            }
            else if(str.charAt(i)=='}' || str.charAt(i)==']' || str.charAt(i)==')')
            {
                if(s.isEmpty())
                    return false;
                Character c=s.pop();
                if(c=='{')
                {
                    if(str.charAt(i)==']' || str.charAt(i)==')')
                        return false;
                }
                if(c=='[')
                {
                    if(str.charAt(i)=='}' || str.charAt(i)==')')
                        return false;
                }
                if(c=='(')
                {
                    if(str.charAt(i)==']' || str.charAt(i)=='}')
                        return false;
                }
            }
            else
                return false;
        }
        return s.isEmpty();
    }
    public static ListNode mergeTwoList(ListNode l1, ListNode l2)
    {
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        if(l1.data<l2.data)
        {
            l1.next=mergeTwoList(l1.next,l2);
            return l1;
        }
        else
        {
            l2.next=mergeTwoList(l1,l2.next);
            return l2;
        }
    }
    public static List<String> generateParanthesis(int n)
    {
        List<String> res=new ArrayList<>();
        generate("",n,n,res);
        return res;
    }
    public static void generate(String str,int l,int r,List<String> res)
    {
        if(l>r)
            return;
        if(r==0)
            res.add(str);
        if(l>0)
            generate(str+"(",l-1,r,res);
        if(r>0)
            generate(str+")",l,r-1,res);
    }
    public static Node head=null;
    public static void btreeToDLL(Node root)
    {
        if(root==null)
            return;
        btreeToDLL(root.left);
        if(prev==null)
        {
            head=root;
        }
        else
        {
            root.left=prev;
            prev.right=root;
        }
        prev=root;
        btreeToDLL(root.right);

    }
    public static ListNode mergeKLists(ListNode [] lists)
    {
        if(lists==null || lists.length==0)
            return null;
        return merge(lists,0,lists.length-1);
    }
    public static ListNode merge(ListNode[] lists,int left,int right)
    {
        if(left==right)
            return lists[left];
        int mid=(left+right)/2;
        ListNode leftList=merge(lists,left,mid);
        ListNode righList=merge(lists,mid+1,right);
        ListNode current=mergeTwoList(leftList,righList);
        return current;
    }
    public static ListNode swapPairs(ListNode head)
    {
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode curr=head;
        ListNode prev=dummy;
        while(curr!=null && curr.next!=null) {
            ListNode next = curr.next; // 2

            curr.next = next.next; // 1->3
            next.next = curr;// 2->1
            prev.next = next; // D->2

            prev = curr; // 1
            curr = curr.next; //3
        }
        return dummy.next;
    }
    public static int removeDuplicates(int[] nums)
    {
        if(nums.length==0)
            return 0;
        int i=0;
        for(int j=1;j<nums.length;j++)
        {
            if(nums[i]!=nums[j])
            {
                i++;
                nums[i]=nums[j];
            }
        }
        return i+1;
    }
    public static int removeElement(int[] nums,int val)
    {
        if(nums.length==0)
            return 0;
       int i=0;
       int n=nums.length;
       while(i<n)
       {
           if(nums[i]==val)
           {
               nums[i]=nums[n-1];
               n--;
           }
           else i++;
       }
       return n;
    }
    public static int removeElement1(int[] nums,int val)
    {
        if(nums.length==0)
            return 0;
        int i=0;
        for(int j=0;j<nums.length;j++)
        {
            if(nums[j]!=val)
            {
                nums[i]=nums[j];
                i++;
            }
        }
        return i;
    }
    public static int strStr(String haystack,String needle)
    {
        if(haystack.length()==0)
            return 0;
        for(int i=0;i<haystack.length();i++)
        {
            if(haystack.charAt(i)==needle.charAt(0))
            {
                int start=i;
                int j=0;
                int count=0;
                while(j<needle.length())
                {
                    if(haystack.charAt(start)==needle.charAt(j))
                    {
                        start++;count++;
                    }
                    j++;
                }
                if(count==needle.length())
                {
                    return i;
                }
            }
        }
        return -1;
    }
    public static int divide(int dividend,int divisor)
    {
        long d1=dividend,d2=divisor;
        long result=dividelong(Math.abs(d1),Math.abs(d2));
        result=dividend*divisor<0?-result:result;
        return result>Integer.MAX_VALUE ? Integer.MAX_VALUE:(int) result;
    }
    public static long dividelong(long d1,long d2)
    {
        if(d1<d2)
            return 0;
        long sum=d2,dividetime=1;
        while(sum+sum<=d1)
        {
            sum+=sum;
            dividetime+=dividetime;
        }
        return dividetime+dividelong(d1-sum,d2);
    }
    public static int divide1(int dividend, int divisor) {
        if (dividend < 0) dividend = -dividend;
        long result = divideLong1(dividend, divisor);
        return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)result;
    }

    // It's easy to handle edge cases when
// operate with long numbers rather than int
    public static long divideLong1(long dividend, long divisor) {

        // Remember the sign
        boolean negative = dividend < 0 != divisor < 0;

        // Make dividend and divisor unsign
        if (dividend < 0) dividend = -dividend;
        if (divisor < 0) divisor = -divisor;

        // Return if nothing to divide
        if (dividend < divisor) return 0;

        // Sum divisor 2, 4, 8, 16, 32 .... times
        long sum = divisor;
        long divide = 1;
        while ((sum+sum) <= dividend) {
            sum += sum;
            divide += divide;
        }

        // Make a recursive call for (devided-sum) and add it to the result
        return negative ? -(divide + divideLong1((dividend-sum), divisor)) :
                (divide + divideLong1((dividend-sum), divisor));
    }
    public static List<Integer> findSubstring(String s,String[] words)
    {
        List<Integer> res=new LinkedList<>();
        Map<String,Integer> freq=new HashMap<>();
        for(int i=0;i<words.length;i++)
        {
            freq.put(words[i],freq.getOrDefault(words[i],0)+1);
        }
        int wordlen=words[0].length();
        int totalLength=wordlen*words.length;
        for(int start=0;start<=s.length()-totalLength;start++)
        {
            Map<String,Integer> seen=new HashMap<>();
            String con=s.substring(start,start+totalLength);
            int j=0;
            for(int i=0;i<=con.length()-wordlen;i+=wordlen)
            {
                String temp=con.substring(i,i+wordlen);
                if(freq.containsKey(temp))
                {
                    seen.put(temp,seen.getOrDefault(temp,0)+1);
                    if(seen.get(temp)>freq.get(temp))
                        break;
                }
                else break;
                j++;
            }
            if(j==words.length)
                res.add(start);

        }
        return res;
    }
    public static int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
    public static int longestValidParenthesis1(String s)
    {
        int left=0,right=0,maxans=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='(')
                left++;
            else
                right++;
            if(left==right)
                maxans=Math.max(maxans,left*2);
            else if(right>=left)
                left=right=0;
        }
        for(int i=s.length()-1;i>=0;i--)
        {
            if(s.charAt(i)=='(')
                left++;
            else
                right++;
            if(left==right)
                maxans=Math.max(maxans,left*2);
            else if(left>=right)
                right=left=0;
        }
        return maxans;
    }
    public static int search(int[] nums,int target)
    {
        return binarysearch(nums,target,0,nums.length-1);
    }
    public static int binarysearch(int[] nums,int target,int l,int r)
    {
        if(l<=r)
        {
            int mid=(l+r)/2;
            if(nums[mid]==target)
                return mid;
            if(nums[l]>nums[mid])
            {
                if(target>=nums[l] || target<nums[mid])
                    return binarysearch(nums,target,l,mid-1);
                else
                    return binarysearch(nums,target,mid+1,r);
            }
            if(nums[r]<nums[mid])
            {
                if(target>nums[mid] || target<=nums[r])
                    return binarysearch(nums,target,mid+1,r);
                else
                    return binarysearch(nums,target,l,mid-1);

            }
            if(nums[mid]<target)
                return binarysearch(nums,target,mid+1,r);
            else
                return binarysearch(nums,target,l,mid-1);
        }
        else
            return -1;
    }
    public static void alienLexicon(String[] str,int size)
    {
        AlienGraph gr=new AlienGraph(size);
        for(int i=0;i<str.length-1;i++)
        {
            for(int j=0;j<Math.min(str[i].length(),str[i+1].length());j++)
            {
                if(str[i].charAt(j)!=str[i+1].charAt(j))
                {
                    gr.addEdge(str[i].charAt(j)-'a',str[i+1].charAt(j)-'a');
                    break;
                }
            }
        }
        gr.topologicalSort();
    }
    public static void main(String args[])
    {
        //System.out.println(longestCommonSubstring("abcbc"));
        int[] nums={-1, 2, 1, -4};
        //System.out.println(closestThreeSum(nums,1));
        //System.out.println(letterCombinations("23"));
        int nums1[]={1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(nums1,0));
        if(isValid("]"))
            System.out.println("Success");
        System.out.println(generateParanthesis(3));
        Node root=new Node(5);
        root.left=new Node(2);
        root.left.left=new Node (1);
        root.left.right=new Node(3);
        root.right=new Node(7);
        root.right.right=new Node (10);
        root.right.left=new Node (6);
        btreeToDLL(root);
        System.out.println(head);
        ListNode[] lists=new ListNode[3];
        lists[0]=new ListNode(0);
        lists[0].next=new ListNode(1);
        lists[0].next.next=new ListNode(3);
        lists[1]=new ListNode(1);
        lists[1].next=new ListNode(2);
        lists[2]=new ListNode(1);
        lists[2].next=new ListNode(3);
        ListNode res=mergeKLists(lists);
        //System.out.println(res);
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        ListNode ans=swapPairs(head);
        //System.out.println(ans);
        int[] nums2={0,0,1,1,1,2,2,3,3,4};
        int ans123=removeDuplicates(nums2);
        System.out.println(ans123);
        System.out.println(strStr("hello","ll"));
        System.out.println(divide(-2147483648,-1));
        String[] words={"word","good","best","good"};
        System.out.println(findSubstring("wordgoodgoodgoodbestword",words));
        System.out.println(longestValidParenthesis1(")()())"));
        int[] testnum={4,5,6,7,0,1,2};
        System.out.println("testnum search  "+search(testnum,0));
        String[] s={"baa", "abcd", "abca", "cab", "cad"};
        alienLexicon(s,4);
    }
}
