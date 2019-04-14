public class RemoveNthNode
{
    /*
    Given a linked list, remove the n-th node from the end of list and return its head.

    Example:

    Given linked list: 1->2->3->4->5, and n = 2.

    After removing the second node from the end, the linked list becomes 1->2->3->5.
    Note:

    Given n will always be valid.

    Follow up:

    Could you do this in one pass?
     */
    public static ListNode removeNthFromEnd(ListNode head, int t)
    {
        ListNode fast=head;
        ListNode slow=head;
        for(int i=0;i<t;i++)
            fast=fast.next;
        while(fast.next!=null)
        {
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return head;
    }
    public static void main(String[] args)
    {
        ListNode l=new ListNode(1);
        l.next=new ListNode(2);
        l.next.next=new ListNode(3);
        l.next.next.next=new ListNode(4);
        l.next.next.next.next=new ListNode(5);
        ListNode res=removeNthFromEnd(l,2);
        System.out.println(res);
    }
}
