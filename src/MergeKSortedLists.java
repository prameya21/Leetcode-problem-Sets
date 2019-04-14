public class MergeKSortedLists
{
    /*
    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

    Example:

    Input:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    Output: 1->1->2->3->4->4->5->6
    */
    public static ListNode mergeKLists(ListNode[] lists)
    {
        if(lists==null || lists.length==0)
            return null;
        return mergeKLists(lists,0,lists.length);
    }
    public static ListNode mergeKLists(ListNode[] lists,int start,int end)
    {
        if(start==end)
            return lists[start];
        else if(start>end)
            return null;
        else
        {
            int mid=(start+end)/2;
            ListNode left=mergeKLists(lists,start,mid);
            ListNode right=mergeKLists(lists,mid+1,end);
            return mergeTwoList(left,right);
        }
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
}
