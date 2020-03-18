import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferencePairs
{
    /*
    Given an array arr of distinct integers and a nonnegative integer k, write a function findPairsWithGivenDifference that returns an array of all pairs [x,y] in arr, such that x - y = k.
    If no such pairs exist, return an empty array.

    Note: the order of the pairs in the output array should maintain the order of the y element in the original array.

    Examples:

    input:  arr = [0, -1, -2, 2, 1], k = 1
    output: [[1, 0], [0, -1], [-1, -2], [2, 1]]

    input:  arr = [1, 7, 5, 3, 32, 17, 12], k = 17
    output: []
    Constraints:

    [time limit] 5000ms

    [input] array.integer arr

    0 ≤ arr.length ≤ 100
    [input]integer k

    k ≥ 0
     */



    int[][] findPairsWithGivenDifference(int[] arr, int k)
    {
        List<int[]> res=new ArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
        for(int i:arr)
            map.put(i-k,i);
        for(int i:arr)
            if(map.containsKey(i))
                res.add(new int[]{map.get(i),i});
        System.out.println(res);
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args)
    {
        DifferencePairs obj=new DifferencePairs();
        int[][] res=obj.findPairsWithGivenDifference(new int[]{0,-1,-2,2,1},1);
        for(int[] i:res)
            System.out.println(i[0]+" , "+i[1]);
    }
}
