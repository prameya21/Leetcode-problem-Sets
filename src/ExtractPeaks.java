import java.util.*;

public class ExtractPeaks
{
    class Node
    {
        Node next,prev;
        int val;
        public Node(int val)
        {
            this.val=val;
            prev=null;
            next=null;
        }
    }
    public void delete(Node node)
    {
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public int[] getPeaks(int[] nums)
    {
        Node head=new Node(-1);
        Node tail=new Node(-1);
        head.next=tail;
        tail.prev=head;

        for(int i:nums)
        {
            Node node=new Node(i);
            node.prev=tail.prev;
            tail.prev.next=node;
            tail.prev=node;
            node.next=tail;
        }
        PriorityQueue<Node> pq=new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node i, Node j)
            {
                return i.val-j.val;
            }
        });
        Node node=head.next;
        while(node!=tail)
        {
            if(node.val>node.prev.val && node.val>node.next.val)
                pq.offer(node);
            node=node.next;
        }

        List<Integer> res=new ArrayList<>();
        while(!pq.isEmpty())
        {
            node=pq.poll();
            Node pre=node.prev;
            Node nxt=node.next;
            res.add(node.val);
            delete(node);
            if(pre!=head && pre.val>pre.prev.val && pre.val>pre.next.val)
                pq.offer(pre);
            if(nxt!=tail && nxt.val>nxt.prev.val && nxt.val>nxt.next.val)
                pq.offer(nxt);
        }

        System.out.println(res);
        int[] ret=new int[res.size()];
        for(int i=0;i<ret.length;i++)
            ret[i]=res.get(i);
        return ret;
    }

    public static void main(String[] args)
    {
        ExtractPeaks obj=new ExtractPeaks();
        System.out.println(Arrays.toString(obj.getPeaks(new int[]{2,7,8,5,1,6,3,9,4})));
    }
}
