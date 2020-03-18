import java.util.*;

public class ParallelCourses
{
    /*
    There are N courses, labelled from 1 to N.

    We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.

    In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.

    Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.



    Example 1:



    Input: N = 3, relations = [[1,3],[2,3]]
    Output: 2
    Explanation:
    In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
    Example 2:



    Input: N = 3, relations = [[1,2],[2,3],[3,1]]
    Output: -1
    Explanation:
    No course can be studied because they depend on each other.


    Note:

    1 <= N <= 5000
    1 <= relations.length <= 5000
    relations[i][0] != relations[i][1]
    There are no repeated relations in the input.
     */

    public int minimumSemesters(int N, int[][] relations)
    {
        if(N==1)
            return 1;
        int[] inDegrees=new int[N+1];
        inDegrees[0]=Integer.MAX_VALUE;
        Map<Integer, List<Integer>> map=new HashMap<>();
        for(int[] i:relations)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.get(i[0]).add(i[1]);
            inDegrees[i[1]]++;
        }
        Queue<Integer> q=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();
        for(int i=0;i<inDegrees.length;i++)
        {
            if(inDegrees[i]==0)
                q.offer(i);
        }
        int count=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int curr=q.poll();
                visited.add(curr);
                if(map.containsKey(curr))
                {
                    for(int nextCourse:map.get(curr))
                    {
                        if(visited.contains(nextCourse))
                            continue;
                        inDegrees[nextCourse]--;
                        if(inDegrees[nextCourse]==0)
                        {
                            q.offer(nextCourse);
                            visited.add(nextCourse);
                        }
                    }
                }
            }
            count++;
        }
        return visited.size()==N?count:-1;
    }

    public static void main(String[] args)
    {
        ParallelCourses obj=new ParallelCourses();
        System.out.println(obj.minimumSemesters(3,new int[][]{{1,3},{2,3}}));
        System.out.println(obj.minimumSemesters(3,new int[][]{{1,2},{2,3},{3,1}}));
    }

}
