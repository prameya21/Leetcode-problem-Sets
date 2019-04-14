public class MergeTwoSortedLists
{
    /*
    Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

    Example:

    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        if(l1==null && l2==null)
            return null;
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        if(l1.data<l2.data)
        {
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
        }
        else
        {
            l2.next=mergeTwoLists(l1,l2.next);
            return l2;
        }

    }
    public static void main(String[] args)
    {
        ListNode l1=new ListNode(1);
        l1.next=new ListNode(2);
        l1.next.next=new ListNode(4);
        ListNode l2=new ListNode(1);
        l2.next=new ListNode(3);
        l2.next.next=new ListNode(4);
        ListNode res=mergeTwoLists(l1,l2);
        System.out.println(res);
    }
}
