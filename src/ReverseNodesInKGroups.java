public class ReverseNodesInKGroups
{
    /*
    Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

    k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
    Example:
    Given this linked list: 1->2->3->4->5
    For k = 2, you should return: 2->1->4->3->5
    For k = 3, you should return: 3->2->1->4->5
    Note:
    Only constant extra memory is allowed.
    You may not alter the values in the list's nodes, only nodes itself may be changed.
     */
    public ListNode reverseKGroup(ListNode head, int k)
    {
        ListNode curr=head;
        int count=0;
        while(curr!=null && count!=k)
        {
            curr=curr.next;
            count++;
        }
        if(count==k)
        {
            curr=reverseKGroup(curr,k);
            while(count>0)
            {
                ListNode next=head.next;
                head.next=curr;
                curr=head;
                head=next;
                count--;
            }
            return curr;
        }
        return head;
    }

    public static void main(String[] args)
    {
        ReverseNodesInKGroups obj=new ReverseNodesInKGroups();
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        ListNode res=obj.reverseKGroup(head,3);
        while(res!=null)
        {
            System.out.print(res.data);
            res=res.next;
        }
    }
}
