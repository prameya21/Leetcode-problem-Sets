public class BuyAndSellStock1
{
    public static void main(String[] args)
    {
        int[] arr={6,1,3,2,4,7};
        System.out.println(maxProfit2(arr));
    }
    public static int maxProfit(int[] arr)
    {
        int minPrice=Integer.MAX_VALUE,maxprofit=0;
        for(int i:arr)
        {
            if(i<minPrice)
                minPrice=i;
            else
                maxprofit=Math.max(maxprofit,i-minPrice);
        }
        return maxprofit;
    }
    public static int maxProfit2(int[] arr)
    {
        int i=0;
        int peak=arr[0];
        int valley=arr[0];
        int maxProfit=0;
        while(i<arr.length-1)
        {
            while(i<arr.length-1 && arr[i]>=arr[i+1])
                i++;
            valley=arr[i];
            while(i<arr.length-1 && arr[i]<=arr[i+1])
                i++;
            peak=arr[i];
            maxProfit+=peak-valley;
        }
        return maxProfit;
    }
}
