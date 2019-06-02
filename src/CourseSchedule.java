import java.lang.reflect.Array;
import java.util.*;

public class CourseSchedule
{
    /*
    There are a total of n courses you have to take, labeled from 0 to n-1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

    Example 1:

    Input: 2, [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0. So it is possible.
    Example 2:

    Input: 2, [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0, and to take course 0 you should
                 also have finished course 1. So it is impossible.
     */
    public static boolean canFinish(int numCourses,int[][] prerequistites)
    {
        ArrayList<Integer>[] graph=new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i]=new ArrayList<Integer>();
        boolean[] visited=new boolean[numCourses];
        for(int i=0;i<prerequistites.length;i++)
        {
            graph[prerequistites[i][1]].add(prerequistites[i][0]);
        }
        for(int i=0;i<prerequistites.length;i++)
        {
            if(!dfs(i,graph,visited))
                return false;
        }
        return true;
    }
    public static boolean dfs(int vertex,ArrayList[] graph,boolean[] visited)
    {
        if(visited[vertex])
            return false;
        visited[vertex]=true;
        for(int i=0;i<graph[vertex].size();i++)
        {
            if(!dfs((int)graph[vertex].get(i),graph,visited))
                return false;
        }
        visited[vertex]=false;
        return true;
    }

    public static boolean canFinishBFS(int numCourses,int[][] prereqs)
    {
        ArrayList[] graph= new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i]=new ArrayList<>();
        int[] degree=new int[numCourses];
        for(int i=0;i<prereqs.length;i++)
        {
            graph[prereqs[i][0]].add(prereqs[i][1]);
            degree[prereqs[i][1]]++;
        }
        Queue<Integer> q=new LinkedList<>();
        int count=0;
        for(int i=0;i<degree.length;i++)
            if(degree[i]==0)
            {
                q.offer(i);
                count++;
            }

        while(!q.isEmpty())
        {
            int vertex=q.poll();
            for(int i=0;i<graph[vertex].size();i++)
            {
                int curr_vertex=(int)graph[vertex].get(i);
                degree[curr_vertex]--;
                if(degree[curr_vertex]==0)
                {
                    count++;
                    q.offer(curr_vertex);
                }

            }
        }
        return count==numCourses;
    }




    public static void main(String[] args)
    {
        int[][] prerequisites={{2,0},{1,0},{3,2},{3,1}};
        System.out.println(canFinishBFS(4,prerequisites));
    }



}


