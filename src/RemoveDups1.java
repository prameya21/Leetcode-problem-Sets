public class RemoveDups1
{
    /*
    Given a sorted linked list, delete all duplicates such that each element appear only once.

    Example 1:

    Input: 1->1->2
    Output: 1->2
    Example 2:

    Input: 1->1->2->3->3
    Output: 1->2->3
     */
    public static ListNode remove(ListNode head)
    {
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null)
        {
            while(fast.next!=null && fast.data==fast.next.data)
            {
                fast=fast.next;
            }
            if(fast!=slow)
            {
                slow.next=fast.next;
                fast=slow;
            }
            else
            {
                fast=fast.next;
                slow=slow.next;
            }
        }
        return head;
    }
    public static void main(String[] args)
    {
        ListNode l=new ListNode(1);
        l.next=new ListNode(1);
        l.next.next=new ListNode(2);
        l.next.next.next=new ListNode(3);
        l.next.next.next.next=new ListNode(3);
        ListNode res=remove(l);
        System.out.println(res);
    }
}
