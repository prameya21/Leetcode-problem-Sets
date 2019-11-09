public class MyLinkedList
{

    /*
    Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

    Implement these functions in your linked list class:

    get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
    addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
    addAtTail(val) : Append a node of value val to the last element of the linked list.
    addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. If index is negative, the node will be inserted at the head of the list.
    deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
    Example:

    MyLinkedList linkedList = new MyLinkedList();
    linkedList.addAtHead(1);
    linkedList.addAtTail(3);
    linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
    linkedList.get(1);            // returns 2
    linkedList.deleteAtIndex(1);  // now the linked list is 1->3
    linkedList.get(1);            // returns 3
    Note:

    All values will be in the range of [1, 1000].
    The number of operations will be in the range of [1, 1000].
    Please do not use the built-in LinkedList library.
     */
    class ListNode
    {
        ListNode next=null;
        int val;
        public ListNode(int x)
        {
            val=x;
        }
    }
    ListNode head;
    int count;
    /** Initialize your data structure here. */
    public MyLinkedList()
    {
        head=new ListNode(-1);
        count=0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index)
    {
        if(index<0 || index>count)
            return -1;
        ListNode node=head.next;
        for(int i=0;i<count;i++)
        {
            if(i==index)
                return node.val;
            node=node.next;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val)
    {
        addAtIndex(0,val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val)
    {
        addAtIndex(count,val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val)
    {
        if(index<0)
            index=0;
        if(index>count)
            return;
        ListNode node=head;
        for(int i=0;i<=count;i++)
        {
            if(index==i)
            {
                ListNode temp=new ListNode(val);
                temp.next=node.next==null?null:node.next;
                node.next=temp;
                count++;
                return;
            }
            node=node.next;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index)
    {
        if(index<0 || index>count)
            return;
        ListNode node=head;
        for(int i=0;i<count;i++)
        {
            if(index==i)
            {
                node.next=node.next.next;
                count--;
                return;
            }
            node=node.next;
        }
    }
    public static void main(String[] args)
    {
        MyLinkedList obj=new MyLinkedList();
        obj.addAtHead(1);
        obj.addAtIndex(1,2);
        System.out.println(obj.get(1));
        System.out.println(obj.get(0));
        System.out.println(obj.get(2));

    }
}

