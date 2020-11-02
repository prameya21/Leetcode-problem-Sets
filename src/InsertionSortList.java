public class InsertionSortList
{
    /*
    Algorithm of Insertion Sort:

    Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
    At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
    It repeats until no input elements remain.

    Example 1:

    Input: 4->2->1->3
    Output: 1->2->3->4
    Example 2:

    Input: -1->5->3->4->0
    Output: -1->0->3->4->5
     */

    public ListNode insertionSortList(ListNode head)
    {
        ListNode dummy=new ListNode(0);
        ListNode curr=head,prev,next;
        while(curr!=null)
        {
            prev=dummy;
            next=curr.next;
            while(prev.next!=null && prev.next.data<curr.data)
                prev=prev.next;
            curr.next=prev.next;
            prev.next=curr;
            curr=next;
        }
        return dummy.next;
    }
}
