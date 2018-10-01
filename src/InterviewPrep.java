
public class InterviewPrep
{
    /*public static int kthlast(ListNode head,int k)
    {
        if(head==null)
            return 0;
        int index=kthlast(head.next,k)+1;
        if(index==k)
        {
            System.out.println("kth last element is :"+head.data);
        }
        return index;

    }
    */
    public static ListNode rearrange(ListNode hd, int data)
    {
        ListNode head=hd;
        ListNode tail=hd;
        ListNode current=hd;
        while(current!=null)
        {
            ListNode next=current.next;
            if(current.data<data)
            {
                current.next=head;
                head=current;
            }
            else
            {
                tail.next=current;
                tail=current;
            }
            current=next;
        }
        tail.next=null;
        return head;
    }
	public static void main(String[] args)
	{

	    //Arrays and String calls
        //
        //
        //
        //
		//System.out.println("Hello World");
		String str = "abcba";
		ArrayPrep ap = new ArrayPrep();
        boolean unique = ap.checkUnique(str);
        if(unique)
            System.out.println("Unique");
        else
            System.out.println("Duplicates found");

        if(ap.isPermutation("Hello","Hello"))
            System.out.println("True");
        else
            System.out.println("False");
        /*char c[] = str.toCharArray();
        System.out.println(c.length);
        char result[]=ap.urlify(c,10);
        for(char t:result)
            System.out.print(t);*/

        if(ap.isPalindrome(str))
            System.out.println("True");

        if(ap.oneEditAway("Hello","Hllo"))
            System.out.println("Pass");
        else
            System.out.println("Fail");

        System.out.println(ap.stringCompress("aabbccdddde"));
        int mat[][]={{1,1,1},{2,2,2},{3,3,3}};
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(mat[i][j]);
            System.out.println();
        }
        ap.rotate(mat);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(mat[i][j]);
            System.out.println();
        }
        int mat1[][]={{1,1,1},{1,0,1},{1,1,1}};
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(mat1[i][j]);
            System.out.println();
        }
        ap.makeZero(mat1);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(mat1[i][j]);
            System.out.println();
        }


        //Linked List problems
        ListNode head=new ListNode(1);
        head.add(2);
        head.add(3);
        head.add(3);
        head.add(4);
        head.add(5);
        head.add(5);
        System.out.println("Count= "+head.count(3));
        //System.out.println("nth= "+head.getNth(1));
        //System.out.println("head= "+head.pop());
        //head.insetNth(20,0);
        head.insetNth(12,2);
        //head.removeDupsHash();
        //head.remopvedups();
        //head.printList();
        //System.out.println("kthlast="+head.kthlast(3));
        //int data=kthlast(head,3);
        ListNode temp=rearrange(head,12);
        temp.printList();
	}

}
