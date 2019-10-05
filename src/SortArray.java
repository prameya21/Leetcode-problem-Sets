 class SortArray
{
    /*
    Given an array of integers nums, sort the array in ascending order.
    Example 1:

    Input: [5,2,3,1]
    Output: [1,2,3,5]
    Example 2:

    Input: [5,1,1,2,0,0]
    Output: [0,0,1,1,2,5]


    Note:

    1 <= A.length <= 10000
    -50000 <= A[i] <= 50000
     */

    public int[] sortArray(int[] nums)
    {
        sort(nums,0,nums.length-1);
        return nums;
    }
    public void sort(int[] nums, int l, int r)
    {
       if(l==r)
           return;
        int mid=(l+r)/2;
        sort(nums,l,mid);
        sort(nums,mid+1,r);
        merge(nums,l,r,mid);

    }
    public void merge(int[] nums,int l, int r, int m)
    {
        int n1=m-l+1;
        int n2=r-m;

        int[] left=new int[n1];
        int[] right=new int[n2];
        for (int i=0; i<n1; ++i)
            left[i] = nums[l + i];
        for (int j=0; j<n2; ++j)
            right[j] = nums[m + 1+ j];

        int i=0,j=0,k=l;
        while(i<left.length && j<right.length)
        {
            if(left[i]<=right[j])
                nums[k++]=left[i++];
            else
                nums[k++]=right[j++];
        }
        while(i<left.length)
            nums[k++]=left[i++];
        while(j<right.length)
            nums[k++]=right[j++];
    }


    public int[] quickSort(int[] nums)
    {
        qsort(nums,0,nums.length-1);
        return nums;
    }
    public void qsort(int[] nums, int l, int r)
    {
        if(l<r)
        {
            int p=partition(nums,l,r);
            qsort(nums,l,p-1);
            qsort(nums,p+1,r);
        }
    }
    public int partition(int[] nums, int l, int r)
    {
        int pivot=nums[r];
        int i=l;
        for(int j=l;j<=r;j++)
        {
            if(nums[j]<pivot)
                swap(nums,i++,j);
        }
        swap(nums,i,r);
        return i;
    }
    public void swap(int[] nums, int l , int r)
    {
        int temp=nums[l];
        nums[l]=nums[r];
        nums[r]=temp;
    }


    public static void main(String[] args)
    {
        SortArray obj=new SortArray();
        int[] ret=obj.quickSort(new int[]{5,4,3,2,1}) ;
        for(int i:ret)
            System.out.print(i+",");
    }



}
