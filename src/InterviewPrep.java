import java.util.List;
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



	public static void main(String[] args)
	{

	    //Arrays and String calls
        //
        //
        //
        //
		//System.out.println("Hello World");
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

	}

}
