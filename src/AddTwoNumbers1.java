import java.util.Stack;

public class AddTwoNumbers1
{
    /*
    445
    You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Follow up:
    What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

    Example:

    Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 8 -> 0 -> 7
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        Stack<Integer> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();
        while(l1!=null)
        {
            s1.push(l1.data);
            l1=l1.next;
        }
        while(l2!=null)
        {
            s2.push(l2.data);
            l2=l2.next;
        }
        int sum=0;
        ListNode list=new ListNode(0);
        while(!s1.isEmpty() || !s2.isEmpty())
        {
            sum+=s1.isEmpty()?0:s1.pop();
            sum+=s2.isEmpty()?0:s2.pop();
            list.data=sum%10;
            ListNode head=new ListNode(sum/10);
            head.next=list;
            list=head;
            sum/=10;
        }
        return list.data==0?list.next:list;
    }

    public static void main(String[] args)
    {
        ListNode l1=new ListNode(7);
        l1.next=new ListNode(2);
        l1.next.next=new ListNode(4);
        l1.next.next.next=new ListNode(3);
        ListNode l2=new ListNode(5);
        l2.next=new ListNode(6);
        l2.next.next=new ListNode(4);
        ListNode res=addTwoNumbers(l1,l2);
        System.out.println(res);
    }
}
