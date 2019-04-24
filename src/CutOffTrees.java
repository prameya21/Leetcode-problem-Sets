import java.util.*;

public class CutOffTrees
{
    /*
    You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

    0 represents the obstacle can't be reached.
    1 represents the ground can be walked through.
    The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.


    You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

    You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

    You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

    Example 1:

    Input:
    [
     [1,2,3],
     [0,0,4],
     [7,6,5]
    ]
    Output: 6


    Example 2:

    Input:
    [
     [1,2,3],
     [0,0,0],
     [7,6,5]
    ]
    Output: -1


    Example 3:

    Input:
    [
     [2,3,4],
     [0,0,5],
     [8,7,6]
    ]
    Output: 6
    Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
     */

    public static int cutOffTree(List<List<Integer>> forest)
    {
        if(forest.size()==0 || forest==null)
            return 0;
        int m=forest.size(),n=forest.get(0).size();
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2]-b[2];
            }
        });
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(forest.get(i).get(j)>1)
                    pq.offer(new int[]{i,j,forest.get(i).get(j)});
            }
        }
        int[] start=new int[2];
        int sum=0;
        while(!pq.isEmpty())
        {
            int[] tree=pq.poll();
            int step=bfs(tree,start,forest,m,n,dir);
            if(step<0)
                return -1;
            sum+=step;
            start[0]=tree[0];
            start[1]=tree[1];
        }
        return sum;
    }
    public static int bfs(int[] tree,int[] start,List<List<Integer>> forest,int m,int n,int[][] dirs)
    {
        boolean[][] visited=new boolean[m][n];
        Queue<int[]> q=new LinkedList<>();
        int step=0;
        q.offer(start);
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                visited[curr[0]][curr[1]] = true;
                if (curr[0] == tree[0] && curr[1] == tree[1])
                    return step;
                for (int[] d : dirs)
                {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];
                    if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc] || forest.get(nr).get(nc) == 0)
                        continue;
                    q.offer(new int[]{nr, nc});
                }
            }
            step++;
        }
        return -1;
    }
    public static void main(String[] args)
    {
        List<List<Integer>> forest=new ArrayList<>();
        forest.add(Arrays.asList(1,2,3));
        forest.add(Arrays.asList(0,0,4));
        forest.add(Arrays.asList(7,6,5));
        System.out.println(cutOffTree(forest));
    }
}
