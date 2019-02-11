import java.util.HashSet;
import java.util.List;
import java.util.*;
class Result
{
    public ListNode node=null;
    public boolean result;
    public Result(ListNode n,boolean b)
    {
        node=n;
        result=b;
    }
}
public class InterviewPrep
{
    /*public static int kthlast(ListNode head,int k)
    {
        if(head==null)
            return 0;
        int index=kthlast(head.next,k)+1;
        if(index==k)
        {
            System.out.println("kth last element is :"+head.data);
        }
        return index;

    }
    */
    public static Result isPalindromeRecurse(ListNode head, int length)
    {
        if(head==null || length<=0)
            return new Result(head,true);
        else if(length==1)
            return new Result(head.next,true);
        Result res=isPalindromeRecurse(head.next,length/2);
        if(!res.result || res.node==null)
            return res;
        res.result=(head.data==res.node.data);
        res.node=res.node.next;
        return res;
    }
    public static ListNode isIntersect(ListNode l1, ListNode l2)
    {
        int length1=cnt(l1);
        int length2=cnt(l2);
        int diff=Math.abs(length1-length2);
        int cnt=0;
        while(cnt<diff)
        {
            if(length1>length2)
                l1=l1.next;
            else if(length2>length1)
                l2=l2.next;
            cnt++;
        }
        while(l1!=null && l2!=null)
        {
            if(l1==l2)
            {
                return l1;
            }
            l1=l1.next;
            l2=l2.next;
        }
        return null;
    }
    public static ListNode rearrange(ListNode hd, int data)
    {
        ListNode head=hd;
        ListNode tail=hd;
        ListNode current=hd;
        while(current!=null)
        {
            ListNode next=current.next;
            if(current.data<data)
            {
                current.next=head;
                head=current;
            }
            else
            {
                tail.next=current;
                tail=current;
            }
            current=next;
        }
        tail.next=null;
        return head;
    }
    public static int cnt(ListNode head)
    {
        if(head==null)
            return 0;
        else
            return cnt(head.next) + 1;

    }
    public static ListNode addRecursive(ListNode l1, ListNode l2, int carry)
    {
        if(l1==null && l2==null)
        {
            return null;
        }
        int value=carry;
        if(l1==null)
            value+=l2.data;
        else if(l2==null)
            value+=l1.data;
        else
            value+=l1.data+l2.data;
        ListNode result=new ListNode(0);
        result.data=value%10;
        result.next=addRecursive(l1.next==null?null:l1.next, l2.next==null?null:l2.next, value>10?1:0);
        return result;
    }
    public static ListNode findStart(ListNode l1)
    {
        ListNode slow=l1;
        ListNode fast=l1;
        while(fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                break;
        }
        if(fast==null || fast.next==null)
            return null;
        slow=l1;
        while(slow!=fast)
        {
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head)
    {
        if(head==null)
            return null;
        ListNode currNode=head;
        ListNode nextNode=null;
        ListNode prevNode=null;
        while(currNode!=null)
        {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode;
    }
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

	public static void main(String[] args)
	{
	    String str = "abcba";
		ArrayPrep ap = new ArrayPrep();
        boolean unique = ap.checkUnique(str);
        if(unique)
            System.out.println("Unique");
        else
            System.out.println("Duplicates found");

        if(ap.isPermutation("Hello","Hello"))
            System.out.println("True");
        else
            System.out.println("False");
        /*char c[] = str.toCharArray();
        System.out.println(c.length);
        char result[]=ap.urlify(c,10);
        for(char t:result)
            System.out.print(t);*/

        /*if(ap.isPalindrome(str))
            System.out.println("True");

        if(ap.oneEditAway("Hello","Hllo"))
            System.out.println("Pass");
        else
            System.out.println("Fail");

        System.out.println(ap.stringCompress("aabbccdddde"));
        int mat[][]={{1,1,1},{2,2,2},{3,3,3}};
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(mat[i][j]);
            System.out.println();
        }
        ap.rotate(mat);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(mat[i][j]);
            System.out.println();
        }
        int mat1[][]={{1,1,1},{1,0,1},{1,1,1}};
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(mat1[i][j]);
            System.out.println();
        }
        ap.makeZero(mat1);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(mat1[i][j]);
            System.out.println();
        }


        //Linked List problems
        ListNode head=new ListNode(1);
        head.add(2);
        head.add(3);
        head.add(3);
        head.add(4);
        head.add(5);
        head.add(5);
        System.out.println("Count= "+head.count(3));
        //System.out.println("nth= "+head.getNth(1));
        //System.out.println("head= "+head.pop());
        //head.insetNth(20,0);
        head.insetNth(12,2);
        //head.removeDupsHash();
        //head.remopvedups();
        //head.printList();
        //System.out.println("kthlast="+head.kthlast(3));
        //int data=kthlast(head,3);
        ListNode temp=rearrange(head,12);
        temp.printList();
        System.out.println(cnt(temp));
        ListNode l1=new ListNode(3);
        l1.add(2);
        l1.add(3);
        ListNode l2=new ListNode(3);
        l2.add(2);
        l2.add(1);
        ListNode result=addRecursive(l1,l2,0);
        System.out.println("add result");
        result.printList();
        System.out.println("Reverse result");
        ListNode tempList=reverse(result);
        tempList.printList();
        */
        ListNode p1=new ListNode(1);
        p1.add(2);
        p1.add(3);
        p1.add(2);
        p1.add(1);
        if(p1.isPalindrome())
            System.out.println("Palindrome");
        else
            System.out.println("Not a palindrome");
        Result res=isPalindromeRecurse(p1,5);
        if(res.result)
            System.out.println(true);
        else
            System.out.println(false);

        ListNode a1=new ListNode(10);
        a1.add(12);
        a1=a1.next;
        a1.next=p1;
        ListNode a2=new ListNode(13);
        a2.add(16);
        a2.add(19);
        a2.next.next.next=p1;
        ListNode result=isIntersect(a1,a2);
        System.out.println(result.data);
        if(findStart(a1)==null)
            System.out.println("Fail");
        else
            System.out.println("Pass");
        System.out.println("Highest length substring is:  "+lengthSubstring("pkewewkp"));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("maxlength is "+longestCommonSubstring("abacdgfdcaba"));
        System.out.println();
        System.out.println();
        System.out.println(longestPalindromicSubstring("abacdgfdcaba"));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(reverseInt(-123));
        System.out.println(Intreverse(-123));
        if(palindromereverse(121))
            System.out.println("TRUE");
        else
            System.out.println("FALSE");
        int heights[]={1,8,6,2,5,4,8,3,7};
        System.out.println("Max Area is :"+maxContainer(heights));
        System.out.println(intToRoman(69));
        System.out.print(romanToInt("MCMXCIV"));
        String[] strs={"fininsh","finger","finder"};
        System.out.println();
        System.out.println("LCP is : "+longestCommonPrefix(strs));
        System.out.println();
        System.out.println();
        int[] nums={-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        System.out.println("Three Sum sol is : "+threeSum(nums));
	}

}
