
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

	}

}
