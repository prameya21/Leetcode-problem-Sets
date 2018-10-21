import java.util.Stack;

class StackThree
{
    int []tos_loc=new int[3];
    int data[];
    int total_capacity;
    public StackThree(int size)
    {
        data=new int[size*3];
        total_capacity=size;
        for(int i=0;i<tos_loc.length;i++)
            tos_loc[i]=-1;
    }
    public void push(int stacknum, int val)
    {
        if(tos_loc[stacknum]==total_capacity-1)
        {
            System.out.println("Unable to push");
            return;
        }
        else
        {
            tos_loc[stacknum]++;
            data[stacknum*total_capacity+tos_loc[stacknum]]=val;
        }

    }
    public int pop(int StackNum)
    {
        if(tos_loc[StackNum]<0)
        {
            System.out.println("Empty Stack");
            return -1;
        }
        else
        {
            int val=data[StackNum*total_capacity+tos_loc[StackNum]];
            data[StackNum*total_capacity+tos_loc[StackNum]]=0;
            tos_loc[StackNum]--;
            return val;
        }
    }
    public int peek(int StackNum)
    {
        if(tos_loc[StackNum]<0)
        {
            System.out.println("Empty Stack");
            return -1;
        }
        else
        {
            int val=data[StackNum*total_capacity+tos_loc[StackNum]];
            return val;
        }

    }
}
class StackWithMin extends Stack<Integer>
{
    Stack<Integer> s2;
    public StackWithMin()
    {
        s2=new Stack<Integer>();
    }
    public int min()
    {
        if(s2.isEmpty())
            return Integer.MAX_VALUE;
        else
            return s2.peek();
    }
    public void push(int data)
    {
        if(data<min())
            s2.push(data);
        super.push(data);
    }
    @Override
    public Integer pop()
    {
        int val=super.pop();
        if(val==min())
            s2.pop();
        return val;
    }


}

class MyQueue
{
    Stack<Integer> newStack;
    Stack<Integer> oldStack;
    public MyQueue()
    {
        newStack=new Stack<>();
        oldStack=new Stack<>();
    }
    public void push(int data)
    {
        newStack.push(data);
    }
    public void shiftStacks()
    {
        if(oldStack.isEmpty())
        {
            while(!newStack.isEmpty())
            {
                oldStack.push(newStack.pop());
            }
        }
    }
    public int peek()
    {
        shiftStacks();
        return oldStack.peek().intValue();
    }
    public int remove()
    {
        shiftStacks();
        return oldStack.pop().intValue();
    }
    public boolean isEmpty()
    {
        return oldStack.isEmpty() && newStack.isEmpty();
    }
}

public class StackPrep
{
    public static void insertAtBottom(Stack<Integer> S, int temp)
    {
        if(S.isEmpty())
            S.push(temp);
        else
        {
            int t=S.pop().intValue();
            insertAtBottom(S,temp);
            S.push(t);
        }
    }
    public static void reverse(Stack<Integer> S)
    {
        int temp;
        if(!S.isEmpty()) {
            temp = S.pop().intValue();
            reverse(S);
            insertAtBottom(S, temp);
        }

    }
    public static void sort(Stack<Integer> S)
    {
        if(!S.isEmpty())
        {
            int temp=S.pop().intValue();
            sort(S);
            insertSorted(S,temp);
        }
    }
    public static void insertSorted(Stack<Integer> S , int temp)
    {
        if(S.isEmpty()||temp<S.peek())
            S.push(temp);
        else
        {
            int t=S.pop().intValue();
            insertSorted(S,temp);
            S.push(t);
        }
    }
    public static void iterativeSort(Stack<Integer> s)
    {
        Stack<Integer> r=new Stack<Integer>();
        while(!s.isEmpty())
        {
            int tmp=s.pop().intValue();
            while(!r.isEmpty() && r.peek() < tmp)
            {
                s.push(r.pop());
            }
            r.push(tmp);
        }
        while(!r.isEmpty())
            s.push(r.pop());
    }
    public static void main(String args[])
    {
        //System.out.println("Hello world");
        StackThree st=new StackThree(10);
        st.push(0,12);
        st.push(1,13);
        st.push(2,15);
        st.push(0,13);
        st.push(1,14);
        st.push(2,16);
        st.push(0,13);
        st.push(1,14);
        st.push(2,17);
        st.pop(0);
        st.pop(1);
        st.pop(2);
        System.out.println(st.peek(0));
        System.out.println(st.peek(1));
        System.out.println(st.peek(2));

        StackWithMin s=new StackWithMin();
        s.push(1);
        s.push(10);
        s.push(20);
        s.push(-8);
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());
        Stack<Integer> testStack=new Stack<Integer>();
        testStack.push(1);
        testStack.push(10);
        testStack.push(20);
        testStack.push(-8);
        //sort(testStack);
        iterativeSort(testStack);
        while(!testStack.isEmpty())
            System.out.println(testStack.pop());

    }
}
