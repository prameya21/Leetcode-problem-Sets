import java.util.ArrayList;
import java.util.List;

public class DotProductOfTwoSparseVectors
{
    /*
    Given two sparse vectors, compute their dot product.
    Implement class SparseVector:
    SparseVector(nums) Initializes the object with the vector nums
    dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
    A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

    Follow up: What if only one of the vectors is sparse?

    Example 1:
    Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
    Output: 8
    Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
    v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8

    Example 2:
    Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
    Output: 0
    Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
    v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0

    Example 3:
    Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
    Output: 6
     */

    class SparseVector
    {
        List<int[]> v=new ArrayList<>();
        SparseVector(int[] nums)
        {
            for(int i=0;i<nums.length;i++)
            {
                if(nums[i]==0)
                    continue;
                v.add(new int[]{i,nums[i]});
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec)
        {
            int i=0,j=0,prod=0;
            while(i<v.size() && j<vec.v.size())
            {
                int[] v1=v.get(i);
                int[] v2=vec.v.get(j);
                if(v1[0]==v2[0])
                {
                    prod+=v1[1]*v2[1];
                    i++;
                    j++;
                }
                else if(v1[0]<v2[0])
                    i++;
                else j++;
            }
            return prod;
        }
    }
}
