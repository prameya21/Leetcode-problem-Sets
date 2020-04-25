import java.util.Arrays;

public class NightRoute
{
    /*
    Consider a big city located on n islands. There are bridges connecting the islands, but they all have only one-way traffic. To make matters worse, most of the bridges are closed at night, so there is at most one bridge with traffic going from any island A to any other island B.

    There is a programmer who turns a penny by working nights as an cab driver. One night his phone dies right after he picks up a rider going from island 0 to island (n - 1).
    He has the map of the city bridges in his laptop though (stored as a matrix of distances), so he decides to implement an algorithm that calculates the shortest path between those two islands and evaluate the cost based on the distance of the path.
    Assume that each mile of the trip is 1$.
    Example

    For

    city = [[-1, 5, 20],
            [21, -1, 10],
            [-1, 1, -1]]
    the output should be nightRoute(city) = 15.

    city[i][j] equals the distance between the ith and the jth islands in miles, or -1 if there is no bridge by which one can move from island i to island j.

    nightRoute(city) should be 15, since the shortest distance from the 0th to the 2nd island is 15. The distance from the 0th to the 1st is city[0][1] = 5, and from the 1st to the 2nd is city[1][2] = 10.

    For

    city = [[-1, 5, 2, 15],
            [2, -1, 0, 3],
            [1, -1, -1, 9],
            [0, 0, 0, -1]]
    the output should be nightRoute(city) = 8.

    The shortest path is 0 -> 1 -> 3 which costs 5 + 3 = 8.

    Input/Output

    [execution time limit] 3 seconds (java)

    [input] array.array.integer city

    The city is represented as a square matrix, where city[i][j] equals the distance between the ith and the jth islands in miles, or -1 if there is no bridge that allows moving in that direction.

    Guaranteed constraints:
    2 ≤ city.length ≤ 10,
    city[i].length = city.length,
    -1 ≤ city[i][j] ≤ 30.

    [output] integer

    The cost of the trip. It is guaranteed that there is a route from the 0th to the (n - 1)th island.
     */

    /*
    public int nightRoute(int[][] city)
    {
        if(city==null || city.length==0)
            return -1;
        int dist=Integer.MAX_VALUE;
        boolean[][] visited=new boolean[city.length][city[0].length];
        for(int col=0;col<city[0].length;col++)
            if(city[0][col]!=-1)
                dist=Math.min(dist,dfs(city,visited,0,col,city[0][col]));

        return dist==Integer.MAX_VALUE?-1:dist;
    }

    public int dfs(int[][] city, boolean[][] visited, int r, int c, int cost)
    {
        //System.out.println(cost+","+r+","+c);
        if(c==city[0].length-1)
            return cost;

        //if(r<0 || r>=city.length || c<0 || c>=city[0].length)
            //return Integer.MAX_VALUE;

        int min=Integer.MAX_VALUE;
        visited[r][c]=true;
        //System.out.println(Arrays.deepToString(visited));
        for(int j=0;j<city[0].length;j++)
        {
            if(city[c][j]!=-1 && !visited[c][j])
            {
                min=Math.min(min,dfs(city,visited,c,j,cost+city[c][j]));
            }
        }
        visited[r][c]=false;
        return min;
    }
    */

    public static int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < dist.length; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    private static int nightRoute(int[][] city) {
        int len = city.length;
        int dist[] = new int[len]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[len];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < len; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[0] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < len - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < len; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && city[u][v] != -1 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + city[u][v] < dist[v])
                    dist[v] = dist[u] + city[u][v];
        }

        return dist[dist.length - 1];
    }

    public static void main(String[] args)
    {
        NightRoute obj=new NightRoute();
        //System.out.println(obj.nightRoute(new int[][]{{-1, 5, 20},{21, -1, 10},{-1, 1, -1}}));

        //System.out.println(obj.nightRoute(new int[][]{{-1, 5, 2, 15},{2, -1, 0, 3},{1, -1, -1, 9},{0, 0, 0, -1}}));

        System.out.println(obj.nightRoute(new int[][]{{-1,-1,19,8,18,-1,-1,-1,-1,-1},{10,6,4,7,0,10,18,-1,0,-1},{-1,-1,15,-1,17,3,-1,14,16,3},{4,19,3,15,8,4,6,11,5,8},{5,3,10,-1,0,14,15,1,16,5},{-1,8,-1,-1,5,-1,5,0,1,-1},{-1,18,-1,19,2,-1,10,-1,8,6},{14,8,12,16,-1,-1,0,16,15,17},{4,5,1,12,0,4,8,15,1,-1},{13,7,17,-1,4,13,16,3,12,9}}));
    }

}        /*
        [-1,-1,19,8,18,-1,-1,-1,-1,-1],
        [10,6,4,7,0,10,18,-1,0,-1],
        [-1,-1,15,-1,17,3,-1,14,16,3],
        [4,19,3,15,8,4,6,11,5,8],
        [5,3,10,-1,0,14,15,1,16,5],
        [-1,8,-1,-1,5,-1,5,0,1,-1],
        [-1,18,-1,19,2,-1,10,-1,8,6],
        [14,8,12,16,-1,-1,0,16,15,17],
        [4,5,1,12,0,4,8,15,1,-1],
        [13,7,17,-1,4,13,16,3,12,9]]


        14
        */


























