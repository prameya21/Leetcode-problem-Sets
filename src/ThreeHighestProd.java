public class ThreeHighestProd
{
    public static void main(String[] args)
    {
        int[] arr={-10,-9,1,2,3,4};
        System.out.println(highestProd(arr));
    }
    public static int highestProd(int[] arr)
    {
        int max=Integer.MIN_VALUE;
        int max1=Integer.MIN_VALUE;
        int max2=Integer.MIN_VALUE;
        int min1=Integer.MAX_VALUE;
        int min2=Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]>max)
            {
                max2=max1;
                max1=max;
                max=arr[i];
            }
            else if(arr[i]>max1)
            {
                max2=max1;
                max1=arr[i];
            }
            else if(arr[i]>max2)
                max2=arr[i];
            if(arr[i]<min1)
            {
                min2=min1;
                min1=arr[i];
            }
            else if(arr[i]<min2)
                min2=arr[i];
        }
        return Math.max(max1*max2,min1*min2)*max;
    }
}
