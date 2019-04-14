public class RemoveDups2
{
    /*
    Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

    Example 1:

    Input: 1->2->3->3->4->4->5
    Output: 1->2->5
    Example 2:

    Input: 1->1->1->2->3
    Output: 2->3
     */
    public static ListNode remove(ListNode head)
    {
        ListNode fast=head;
        ListNode slow=new ListNode(0);
        slow.next=head;
        while(fast!=null)
        {
            while(fast.next!=null && fast.data==fast.next.data)
            {
                fast=fast.next;
            }
            if(slow.next!=fast)
            {
                slow.next=fast.next;
                fast=slow.next;
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
        l.next=new ListNode(2);
        l.next.next=new ListNode(3);
        l.next.next.next=new ListNode(3);
        l.next.next.next.next=new ListNode(4);
        l.next.next.next.next.next=new ListNode(4);
        l.next.next.next.next.next.next=new ListNode(5);
        ListNode res=remove(l);
        System.out.println(res);
    }
}
