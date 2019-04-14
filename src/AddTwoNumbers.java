public class AddTwoNumbers
{
    /*
    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example:

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        return addTwoNumbers(l1,l2,0);
    }
    public static ListNode addTwoNumbers(ListNode l1,ListNode l2,int carry)
    {
        if(l1==null && l2==null && carry==0)
            return null;
        int x=l1==null?0:l1.data;
        int y=l2==null?0:l2.data;
        int sum=x+y+carry;
        carry=sum/10;
        sum=sum%10;
        ListNode l=new ListNode(sum);
        l.next=addTwoNumbers(l1.next,l2.next,carry);
        return l;
    }
    public static void main(String[] args)
    {
        ListNode l1=new ListNode(2);
        l1.next=new ListNode(4);
        l1.next.next=new ListNode(3);
        ListNode l2=new ListNode(5);
        l2.next=new ListNode(6);
        l2.next.next=new ListNode(4);
        ListNode l=addTwoNumbers(l1,l2);
        System.out.println(l);
    }
}
