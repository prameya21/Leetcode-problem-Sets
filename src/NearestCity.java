import java.util.*;
public class NearestCity
{
    /*
        Amazon has Fulfillment Centers in multiple cities within a large geographic region. The cities are arranged on a group that has been divided up like an ordinary Cartesian plane.
        Each city is located at an integral(x,y) coordinate intersection. City names and locations are given in the form of three arrays: c,x, and y, which are aligned by the index to provide the city name (c[i]), and its coordinates, (x[i],y[i]).

        Write an algorithm to determine the name of the nearest city that shares an x or a y coordinate with the queried city. If no cities share an x or y coordinate, return none.
        If two cities have the same distance to the queried city, q[i], consider the one with an alphabetically smaller name (e.e 'ab' < 'aba' < 'abb') as the closest choice.

        The distance is denoted on a Euclidean plan: the difference in x plus the difference in y.

        Input
        the input to the function/method consists of six arguments:
        numOfCities, an integer representing the number of cities;
        cities, a list of strings representing the names of each city[i];
        xCoordinates, a list of integers representing the X-coordinates of each city[i];
        yCoordinates, a list of integers representing the Y-coordinates of each city[i];
        numOfQueries, an integer representing the number of queries;
        queries, a list of strings representing the names of the queried cities.

        Output
        Return a list of strings representing the name of the nearest city that shares either an x or a y coordinate with the queried city.

        Constraints
        1 ≤ numOfCities, numOfQueries ≤ 10^5
        1 ≤ xCoordinates[i], yCoordinates[i] <= 10^9
        1 ≤ length of queries[i] and cities[i] ≤ 10

        Note
        Each character of all c[i] and q[i] is in the range ascii[a-z, 0-9,-]
        All city name values, c[i] are unique. All cities have unique coordinates.

        Example:

        Input:

        numOfCities = 3
        cities = ["c1", "c2", "c3"]
        xCoordinates = [3,2,1]
        yCoordinates = [3,2,3]
        numOfQueries = 3
        queries = ["c1", "c2", "c3"]

        Output:

        [c3, None, c1]
     */

    public List<String> getNearsetCities(String[] cities, int[] xs, int[] ys, String[] queries)
    {
        if(cities.length==0 || queries.length==0)
            return new ArrayList<>();
        Map<String,int[]> cityMap=new HashMap<>();
        Map<Integer,List<String>> xMap=new HashMap<>();
        Map<Integer,List<String>> yMap=new HashMap<>();
        for(int i=0;i< cities.length;i++)
        {
            String city=cities[i];
            int x=xs[i];
            int y=ys[i];
            cityMap.put(city,new int[]{x,y});
            xMap.putIfAbsent(x,new ArrayList<>());
            xMap.get(x).add(city);
            yMap.putIfAbsent(y,new ArrayList<>());
            yMap.get(y).add(city);
        }

        List<String> result=new ArrayList<>();
        for(String q:queries)
        {
            if(!cityMap.containsKey(q))
            {
                result.add("None");
                continue;
            }
            String city=q;
            int x=cityMap.get(city)[0];
            int y=cityMap.get(city)[1];
            int minDist=Integer.MAX_VALUE;
            String temp="";
            for(String c:xMap.get(x))
            {
                if(c.equals(q))
                    continue;
                int x1=x,y1=y;
                int x2=cityMap.get(c)[0];
                int y2=cityMap.get(c)[1];
                int dist=getDist(x1,y1,x2,y2);
                if(dist<=minDist)
                {
                    minDist=dist;
                    if(temp.compareTo(c)>1)
                        temp=c;
                }
            }

            for(String c:yMap.get(y))
            {
                if(c.equals(q))
                    continue;
                int x1=x,y1=y;
                int x2=cityMap.get(c)[0];
                int y2=cityMap.get(c)[1];
                int dist=getDist(x1,y1,x2,y2);
                if(dist<=minDist)
                {
                    minDist=dist;
                    if(temp.equals("") || temp.compareTo(c)>1)
                        temp=c;
                }
            }
            result.add(temp==""?"None":temp);
        }
        return result;
    }
    public int getDist(int x1,int y1, int x2, int y2)
    {
        return (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
    }

    public static void main(String[] args)
    {
        NearestCity obj=new NearestCity();
        System.out.println(obj.getNearsetCities(new String[]{"c1","c2","c3"},new int[]{3,2,1},new int[]{3,2,3},new String[]{"c1","c2","c3"}));
    }
}


