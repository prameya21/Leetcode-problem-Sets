public class IntegerToEnglishWords
{
    /*
    Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

    Example 1:

    Input: 123
    Output: "One Hundred Twenty Three"
    Example 2:

    Input: 12345
    Output: "Twelve Thousand Three Hundred Forty Five"
    Example 3:

    Input: 1234567
    Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
    Example 4:

    Input: 1234567891
    Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
     */
    static final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    static  final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String numberToWords(int num)
    {
        if(num==0)
            return "Zero";
        return convert(num);
    }
    public static String convert(int num)
    {
        String result="";
        if(num<10)
            result=belowTen[num];
        else if(num<20)
            result=belowTwenty[num-10];
        else if(num<100)
            result=belowHundred[num/10]+" "+belowTen[num%10];
        else if(num<1000)
            result=convert(num/100)+" Hundred "+convert(num%100);
        else if(num<1000000)
            result=convert(num/1000)+" Thousand "+convert(num%1000);
        else if(num<1000000000)
            result=convert(num/1000000)+" Million "+convert(num%1000000);
        else
            result=convert(num/1000000000)+" Billion "+convert(num%1000000000);
        return result;
    }
    public static void main(String[] args)
    {
        System.out.println(numberToWords(1345));
    }
}
