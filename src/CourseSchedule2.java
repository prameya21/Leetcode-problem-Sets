import java.util.ArrayList;
import java.util.Stack;

public class CourseSchedule2
{
    /*
    There are a total of n courses you have to take, labeled from 0 to n-1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

    There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

    Example 1:

    Input: 2, [[1,0]]
    Output: [0,1]
    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
                 course 0. So the correct course order is [0,1] .
    Example 2:

    Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,1,2,3] or [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
                 courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
                 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
    Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
     */
    public static int[] findOrder(int numCourses,int[][] prereqs)
    {
        ArrayList[] graph=new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i]=new ArrayList<Integer>();
        for(int i=0;i<prereqs.length;i++)
            graph[prereqs[i][1]].add(prereqs[i][0]);
        for(int i=0;i<numCourses;i++)
        {
            if(!detectCycles(i,graph,new boolean[numCourses]))
                return new int[0];
        }
        boolean[] visited=new boolean[numCourses];
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<prereqs.length;i++)
        {
            if(!visited[i])
                topologicalSort(i,graph,st,visited);
        }
        int[] res=new int[st.size()];
        int c=0;
        while(!st.isEmpty())
        {
            res[c]=st.pop();
            c++;
        }
        return res;
    }
    public static boolean detectCycles(int vertex,ArrayList[] graph,boolean[] visited)
    {
        if(visited[vertex])
            return false;
        visited[vertex]=true;
        for(int i=0;i<graph[vertex].size();i++)
        {
            if(!detectCycles((int)graph[vertex].get(i),graph,visited))
                return false;
        }
        visited[vertex]=false;
        return true;
    }
    public static void topologicalSort(int vertex,ArrayList[] graph,Stack<Integer> st,boolean[] visited)
    {
        if(visited[vertex])
            return;
        visited[vertex]=true;
        for(int i=0;i<graph[vertex].size();i++)
        {
            topologicalSort((int)graph[vertex].get(i),graph,st,visited);
        }
        st.push(vertex);
    }

    public static void main(String[] args)
    {
        int[][] prerequisites={{1,0},{0,3},{0,2},{3,2},{2,5},{4,5},{5,6},{2,4}};
        int[] res=findOrder(7,prerequisites);
        for(int i:res)
            System.out.println(i);
    }
}
