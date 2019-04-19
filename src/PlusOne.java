import java.util.Stack;

public class PlusOne
{
    /*
    Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

    You may assume the integer do not contain any leading zero, except the number 0 itself.

    The digits are stored such that the most significant digit is at the head of the list.

    Example :

    Input: [1,2,3]
    Output: [1,2,4]
     */
    public static ListNode plusOne(ListNode l)
    {
        Stack<Integer> s=new Stack<>();
        while(l!=null)
        {
            s.push(l.data);
            l=l.next;
        }
        int sum=1;
        ListNode list=new ListNode(0);
        while(!s.isEmpty())
        {
            sum+=s.pop();
            list.data=sum%10;
            ListNode head=new ListNode(sum/10);
            head.next=list;
            list=head;
            sum/=10;
        }
        return list.data==0?list.next:list;
    }
    public static ListNode plusOne1(ListNode head)
    {
        if(DFS(head)==0)
            return head;
        else
        {
            ListNode newHead=new ListNode(1);
            newHead.next=head;
            return newHead;
        }
    }

    public static int DFS(ListNode head)
    {
        if(head==null)
            return 1;
        int carry=DFS(head.next);
        if(carry==0)
            return 0;
        int val=head.data+1;
        head.data=val%10;
        return val/10;
    }

    public static void main(String[] args)
    {
        ListNode l=new ListNode(9);
        l.next=new ListNode(9);
        l.next.next=new ListNode(9);
        ListNode res=plusOne1(l);
        System.out.println(res);
    }
}
