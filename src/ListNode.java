import java.util.HashSet;

public class ListNode
{
    int data;
    ListNode next;

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
    public int pop()
    {
        ListNode temp=this;
        int rt=temp.data;
        this.data=temp.next.data;
        this.next=temp.next.next;
        return rt;
    }
    public void insetNth(int data, int pos)
    {
        if(pos==0)
        {
            ListNode temp=new ListNode(data);
            temp.next=this;
            this.data=temp.data;
            this.next=temp.next;
        }
        else
        {
            int p=0;
            ListNode current=this;
            while(current!=null)
            {
                if(pos==p+1)
                {
                    ListNode temp = new ListNode(data);
                    temp.next = current.next;
                    current.next = temp;
                    return;
                }
                else
                {
                    current=current.next;
                    p++;
                }

            }

        }
    }
    public void removeDupsHash()
    {
        ListNode current=this;
        HashSet<Integer> hs=new HashSet<>();
        hs.add(current.data);
        while(current.next!=null)
        {
            if(hs.contains(current.next.data))
            {
                current.next=current.next.next;
            }
            else
            {
                hs.add(current.next.data);
                current=current.next;

            }
        }
    }
    public void remopvedups()
    {
        ListNode current=this;
        while(current!=null)
        {
            ListNode runner=current;
            while(runner.next!=null)
            {
                if(current.data==runner.next.data)
                {
                    runner.next=runner.next.next;
                }
                else
                    runner=runner.next;
            }
            current=current.next;
        }
    }
    public int kthlast(int k)
    {
        ListNode current=this;
        int count=0;
        if(this==null)
            return -1;
        while(count!=k)
        {
            if(current==null)
                return -1;
            current=current.next;
            count++;
        }
        ListNode runner=this;
        while(current!=null)
        {
            runner=runner.next;
            current=current.next;
        }
        return runner.data;
    }


    public void printList()
    {
        if(this==null)
        {
            System.out.println("Empty List");
            return;
        }

        ListNode current=this;
        while(current!=null)
        {
            System.out.println(current.data);
            current=current.next;
        }
    }
}

