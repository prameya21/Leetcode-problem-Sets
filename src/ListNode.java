

public class ListNode
{
    private int data;
    private ListNode next;

    public ListNode(int data)
    {
        this.data=data;
        next=null;
    }
    public void add(int data)
    {
        ListNode temp=new ListNode(data);
        ListNode current=this;
        while(current.next!=null)
        {
            current=current.next;
        }
        current.next=temp;


    }
    public int count(int data)
    {
        ListNode current=this;
        int ct=0;
        while(current!=null)
        {
            if(current.data==data)
                ct++;
            current=current.next;
        }
        return ct;
    }
    public int getNth(int n)
    {
        ListNode current=this;
        int cnt=0;
        while(current!=null)
        {
            if(n==cnt)
                return current.data;
            cnt++;
            current=current.next;
        }
        return -1;
    }
}
