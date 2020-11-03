import java.util.*;
public class SkyLineProblem
{
    /*
    A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
    Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

    Buildings Skyline Contour
    The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
    It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

    For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

    The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment.
    Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

    For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

    Notes:

    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
     */

    public List<List<Integer>> getSkyline(int[][] buildings)
    {
        if(buildings==null || buildings.length==0)
            return new ArrayList<>();
        int[][] bldg=new int[buildings.length*2][3];
        int j=0;
        for(int i=0;i<buildings.length;i++)
        {
            int[] curr=buildings[i];
            bldg[j][0]=curr[0];
            bldg[j][1]=curr[2];
            bldg[j][2]=0;
            j++;
            bldg[j][0]=curr[1];
            bldg[j][1]=curr[2];
            bldg[j][2]=1;
            j++;
        }

        Arrays.sort(bldg, new Comparator<int[]>() {
            @Override
            public int compare(int[] i, int[] j)
            {
                int cmp=Integer.compare(i[0],j[0]);
                if(cmp!=0)
                    return cmp;
                else
                {
                    if(i[2]==0 && j[2]==0)
                        return j[1]-i[1];
                    else if(i[2]==1 && j[2]==1)
                        return i[1]-j[1];
                    else
                        return i[2]==0 && j[2]==1?-1:1;
                }
            }
        });

        List<List<Integer>> res=new ArrayList<>();
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);
        int currMax=pq.peek();
        for(int[] i:bldg)
        {
            if(i[2]==0)
            {
                currMax=pq.peek();
                pq.offer(i[1]);
                if(currMax!=pq.peek())
                {
                    currMax=pq.peek();
                    res.add(new ArrayList<>(Arrays.asList(i[0],currMax)));
                }
            }
            else
            {
                int ht=i[1];
                currMax=pq.peek();
                pq.remove(ht);
                if(currMax!=pq.peek())
                    res.add(new ArrayList<>(Arrays.asList(i[0],pq.peek())));
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        int[][] buildings={{1,3,3},{2,4,4},{5,8,2},{6,7,4},{8,9,4}};
        SkyLineProblem obj=new SkyLineProblem();
        System.out.println(obj.getSkyline(buildings));
    }
}
