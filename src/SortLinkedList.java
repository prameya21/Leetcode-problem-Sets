public class SortLinkedList
{
    public static ListNode getMiddle(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode fast=head,slow=head;
        while(fast.next!=null && fast.next.next!=null)
        {
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    public static ListNode mergeTwoList(ListNode l1,ListNode l2)
    {
        if(l1==null && l2==null)
            return null;
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        if(l1.data<l2.data)
        {
            l1.next=mergeTwoList(l1.next,l2);
            return l1;
        }
        else
        {
            l2.next=mergeTwoList(l1,l2.next);
            return l2;
        }
    }
    public static ListNode sortList(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode mid=getMiddle(head);
        ListNode midNext=mid.next;
        mid.next=null;
        ListNode left=sortList(head);
        ListNode right=sortList(midNext);
        return mergeTwoList(left,right);
    }
    public static void main(String[] args)
    {
        ListNode l=new ListNode(1);
        l.next=new ListNode(4);
        l.next.next=new ListNode(3);
        l.next.next.next=new ListNode(2);
        l.next.next.next.next=new ListNode(5);
        l.next.next.next.next.next=new ListNode(2);
        ListNode res=sortList(l);
        System.out.println(res);
    }
}
