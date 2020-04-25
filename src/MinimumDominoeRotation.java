public class MinimumDominoeRotation
{
    /*
    In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

    We may rotate the i-th domino, so that A[i] and B[i] swap values.

    Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

    If it cannot be done, return -1.



    Example 1:



    Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
    Output: 2
    Explanation:
    The first figure represents the dominoes as given by A and B: before we do any rotations.
    If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
    Example 2:

    Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
    Output: -1
    Explanation:
    In this case, it is not possible to rotate the dominoes to make one row of values equal.


    Note:

    1 <= A[i], B[i] <= 6
    2 <= A.length == B.length <= 20000
     */

    public int minDominoRotations(int[] A, int[] B)
    {
        int minA=minSwaps(A,B,A[0]);
        if(minA!=-1)
            return minA;
        return minSwaps(A,B,B[0]);
    }
    int minSwaps(int[] A, int [] B, int x)
    {
        int rA=0,rB=0;
        for(int i=0;i<A.length;i++)
        {
            if(A[i]!=x && B[i]!=x)
                return -1;
            else if(A[i]!=x)
                rA++;
            else if(B[i]!=x)
                rB++;
        }
        return Math.min(rA,rB);
    }

    public static void main(String[] args)
    {
        MinimumDominoeRotation obj=new MinimumDominoeRotation();
        System.out.println(obj.minDominoRotations(new int[]{2,1,2,4,2,2},new int[]{5,2,6,2,3,2}));
    }
}
