
    /*
    Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

    Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.



    Follow up:
    Could you do both operations in O(1) time complexity?



    Example:

    LFUCache cache = new LFUCache( 2  capacity  );

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.get(3);       // returns 3.
    cache.put(4, 4);    // evicts key 1.
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4
     */


import java.util.HashMap;
import java.util.Map;

public class LFUCache
{
    class Node
    {
        int key, val, freq;
        Node next,prev;
        public Node(int key, int val)
        {
            this.key=key;
            this.val=val;
            freq=1;
            next=null;
            prev=null;
        }
    }


    class DLList
    {
        Node head=new Node(-1,-1);
        Node tail=new Node(-1,-1);
        int size=0;
        public DLList()
        {
            head.next=tail;
            head.prev=null;
            tail.prev=head;
            tail.next=null;
        }
        public void addToHead(Node node)
        {
            node.next=head.next;
            head.next=node;
            node.prev=head;
            node.next.prev=node;
            size++;
        }
        public void delete(Node node)
        {
            node.next.prev=node.prev;
            node.prev.next=node.next;
            size--;
        }
    }



    Map<Integer,Node> map=new HashMap<>();
    Map<Integer,DLList> freqMap=new HashMap<>();
    int capacity,min,size;
    public LFUCache(int capacity)
    {
        this.capacity=capacity;
        size=0;
        min=0;
    }

    public int get(int key)
    {
        if(!map.containsKey(key))
            return -1;
        Node temp=map.get(key);
        update(temp);
        return temp.val;
    }

    public void put(int key, int value)
    {
        if(capacity==0)
            return;
        if(map.containsKey(key))
        {
            Node temp=map.get(key);
            temp.val=value;
            update(temp);
            map.put(key,temp);
            return;
        }
        else
        {
            if(size==capacity)
            {
                DLList old=freqMap.get(min);
                int toDelete=old.tail.prev.key;
                map.remove(toDelete);
                old.delete(old.tail.prev);
                size--;
            }
            min=1;
            size++;
            Node temp=new Node(key,value);
            DLList curr=freqMap.getOrDefault(min,new DLList());
            curr.addToHead(temp);
            freqMap.put(min,curr);
            map.put(key,temp);
        }
    }

    public void update(Node node)
    {
        DLList curr=freqMap.get(node.freq);
        curr.delete(node);
        if(node.freq==min && curr.size==0)
        {
            freqMap.remove(min);
            min++;
        }
        node.freq++;
        int freq=node.freq;
        DLList newList=freqMap.getOrDefault(freq,new DLList());
        newList.addToHead(node);
        freqMap.put(freq,newList);
    }

    public static void main(String[] args)
    {
        LFUCache cache = new LFUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}
