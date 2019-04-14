public class PartitionList
{
    /*
    Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

    You should preserve the original relative order of the nodes in each of the two partitions.

    Example:

    Input: head = 1->4->3->2->5->2, x = 3
    Output: 1->2->2->4->3->5
     */
    public static ListNode partition(ListNode head,int x)
    {
        ListNode before=new ListNode(0);
        ListNode after=new ListNode(0);
        ListNode running_before=before;
        ListNode running_after=after;
        before.next=head;
        while(head!=null)
        {
            if(head.data<x)
            {
                running_before.next=head;
                running_before=running_before.next;
            }
            else
            {
                running_after.next=head;
                running_after=running_after.next;
            }
            head=head.next;
        }
        running_after.next=null;
        running_before.next=after.next;
        return before.next;
    }
    public static void main(String[] args)
    {
        ListNode l=new ListNode(1);
        l.next=new ListNode(4);
        l.next.next=new ListNode(3);
        l.next.next.next=new ListNode(2);
        l.next.next.next.next=new ListNode(5);
        l.next.next.next.next.next=new ListNode(2);
        ListNode res=partition(l,3);
        System.out.println(l);
    }
}
