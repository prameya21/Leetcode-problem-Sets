import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumKnightMoves
{
    /*
    In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

    A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
    Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
    Example 1:

    Input: x = 2, y = 1
    Output: 1
    Explanation: [0, 0] → [2, 1]
    Example 2:

    Input: x = 5, y = 5
    Output: 4
    Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
    Constraints: |x| + |y| <= 300
     */
    public int minKnightMoves(int x, int y)
    {
        Queue<int[]> q=new LinkedList<>();
        Set<String> visited=new HashSet<>();
        q.offer(new int[]{0,0});
        visited.add(getHash(0,0));
        int count=0;
        int[][] dirs={{2,1},{2,-1},{-2,-1},{-2,1},{1,2},{1,-2},{-1,2},{-1,-2}};
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                int r=curr[0];
                int c=curr[1];
                if(r==x && c==y)
                    return count;
                for(int[]d:dirs)
                {
                    int nr=r+d[0];
                    int nc=c+d[1];
                    if(visited.contains(getHash(nr,nc)) || (nr*r<0 || nc*c<0))
                        continue;
                    q.offer(new int[]{nr,nc});
                    visited.add(getHash(nr,nc));
                }
            }
            count++;
        }
        return -1;
    }
    public String getHash(int x, int y)
    {
        return x+"_"+y;
    }

    public int minKnightMoves1(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int level = 0;
        int dr[] = new int[]{-1, -1, 1, 1, -2, -2, 2, 2};
        int dc[] = new int[]{-2, 2, -2, 2, -1, 1, -1, 1};
        HashSet<Integer> visited = new HashSet<>();

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                int[] curr = queue.remove();
                if(curr[0] == x && curr[1] == y) {
                    return level;
                }
                int hash = 1001 * curr[0] + curr[1]; //hash is an integer not a string like "x#y"
                if(!visited.contains(hash)) {
                    for(int i=0; i<dr.length; i++) {
                        int r = curr[0] + dr[i];
                        int c = curr[1] + dc[i];
                        if(Math.abs(r) + Math.abs(c) > 300) {  // constraint is |x| + |y| <= 300, pruning based on the constraint
                            continue;
                        }
                        queue.add(new int[]{r, c});
                    }
                    visited.add(hash);
                }
            }
            level++;
        }
        return -1;
    }


    public int minKnightMoves2(int x, int y)
    {
        x=Math.abs(x);
        y=Math.abs(y);
        Queue<int[]> q=new LinkedList<>();
        Set<String> visited=new HashSet<>();
        q.offer(new int[]{0,0});
        visited.add(getHash(0,0));
        int[][] dirs={{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

        int count=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                if(curr[0]==x && curr[1]==y)
                    return count;
                for(int[] d:dirs)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    if(visited.contains(getHash(nr,nc)) || (nr<0 && nc<0))
                        continue;
                    visited.add(getHash(nr,nc));
                    q.offer(new int[]{nr,nc});
                }
            }
            count++;
        }
        return -1;
    }
    public String getHash2(int x, int y)
    {
        return x+","+y;
    }

    public static void main(String[] args)
    {
        MinimumKnightMoves obj=new MinimumKnightMoves();
        System.out.println(obj.minKnightMoves2(300,0));
    }
}
