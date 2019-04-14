public class ReverseLinkedList
{
    /*
    Reverse a singly linked list.

    Example:

    Input: 1->2->3->4->5->NULL
    Output: 5->4->3->2->1->NULL
    Follow up:

    A linked list can be reversed either iteratively or recursively. Could you implement both?
     */
    public static ListNode reverse(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode p=reverse(head.next);
        head.next.next=head;
        head.next=null;
        return p;
    }
    public static ListNode reverse(ListNode head,ListNode newHead)
    {
        if(head==null)
            return newHead;
        ListNode next=head.next;
        head.next=newHead;
        return reverse(next,head);
    }
    public static void main(String[] args)
    {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        ListNode res=reverse_iterative(head);
        System.out.println(res);
    }
    public static ListNode reverse_iterative(ListNode head)
    {
        ListNode curr=head;
        ListNode prev=null;
        while(curr!=null)
        {
            ListNode temp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=temp;
        }
        return prev;
    }
}
