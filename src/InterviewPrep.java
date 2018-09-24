
public class InterviewPrep {

	public static void main(String[] args)
	{
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
	}

}
