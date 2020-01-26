import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestElement
{
    /*
    Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

    Example 1:

    Input: [3,2,1,5,6,4] and k = 2
    Output: 5
    Example 2:

    Input: [3,2,3,1,2,4,5,5,6] and k = 4
    Output: 4
    Note:
    You may assume k is always valid, 1 ≤ k ≤ array's length.
     */

    public int findKthLargest(int[] nums, int k)
    {
        /*PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b-a;
            }
        });*/
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int n:nums)
        {
            pq.offer(n);
            if(pq.size()>k)
                pq.poll();
        }
        return pq.poll();
    }
    public int findKthLargest1(int[] nums, int k)
    {
        return quickSelect(nums,nums.length-k,0,nums.length-1);
    }
    public int quickSelect(int[] nums,int k,int l, int r)
    {
        int pivot=partition(nums,l,r);
        if(pivot==k)
            return nums[k];
        else if(pivot<k)
            return quickSelect(nums,k,l,pivot-1);
        else
            return quickSelect(nums,k,pivot+1,r);
    }
    public int partition(int[] nums, int l, int r)
    {
        int p=nums[r];
        int si=l;
        for(int i=l;i<=r;i++)
        {
            if(nums[l]<p)
            {
                swap(nums,si,i);
                si++;
            }
        }
        swap(nums,r,si);
        return si;
    }
    public void swap(int[] nums, int l, int r)
    {
        int temp=nums[l];
        nums[l]=nums[r];
        nums[r]=temp;
    }
    public static void main(String[] args)
    {
        KthLargestElement obj=new KthLargestElement();
        System.out.println(obj.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4));

    }
}
