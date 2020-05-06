import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiagonalTraversal
{
    /*
    Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.



    Example:

    Input:
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]

    Output:  [1,2,4,7,5,3,6,8,9]

    Explanation:



    Note:

    The total number of elements of the given matrix will not exceed 10,000.
    */

    public List<Integer> getList(int[][] nums, int i, int j)
    {
        List<Integer> temp=new ArrayList<>();
        while(i>=0 && j<nums[0].length)
        {
            temp.add(nums[i][j]);
            i--;
            j++;
        }
        return temp;
    }

    public int[] findDiagonalOrder(int[][] matrix)
    {
        if(matrix==null || matrix.length==0)
            return new int[0];
        int[] ret=new int[matrix.length*matrix[0].length];
        int k=0;

        int r=0,c=0;
        while(r<matrix.length)
        {
            List<Integer> temp=getList(matrix,r,c);
            if((r+c)%2!=0)
                Collections.reverse(temp);
            for(int i:temp)
                ret[k++]=i;
            r++;
        }
        r=matrix.length-1;
        c+=1;
        while(c<matrix[0].length)
        {
            List<Integer> temp=getList(matrix,r,c);
            if((r+c)%2!=0)
                Collections.reverse(temp);
            for(int i:temp)
                ret[k++]=i;
            c++;
        }
        return ret;
    }

    public int[] findDiagonalOrder2(int[][] matrix)
    {
        if(matrix==null || matrix.length==0)
            return new int[0];
        int r=0,c=0,m=matrix.length,n=matrix[0].length;
        int[] ret=new int[m*n];
        for(int i=0;i<ret.length;i++)
        {
            ret[i]=matrix[r][c];
            if((r+c)%2==0)
            {
                if(c==n-1)
                    r++;
                else if(r==0)
                    c++;
                else
                {
                    r--;
                    c++;
                }
            }
            else
            {
                if(r==m-1)
                    c++;
                else if(c==0)
                    r++;
                else
                {
                    r++;
                    c--;
                }
            }
        }
        return ret;
    }
    public static void main(String[] args)
    {
        DiagonalTraversal obj=new DiagonalTraversal();
        System.out.println(obj.findDiagonalOrder2(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

}