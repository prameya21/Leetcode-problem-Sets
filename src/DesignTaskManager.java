import java.util.*;

public class DesignTaskManager
{
    //3408
    /*
        There is a task management system that allows users to manage their tasks, each associated with a priority. The system should efficiently handle adding, modifying, executing, and removing tasks.

        Implement the TaskManager class:
        TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples. Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified user with the given priority.
        void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user with userId. It is guaranteed that taskId does not exist in the system.
        void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority. It is guaranteed that taskId exists in the system.
        void rmv(int taskId) removes the task identified by taskId from the system. It is guaranteed that taskId exists in the system.
        int execTop() executes the task with the highest priority across all users. If there are multiple tasks with the same highest priority, execute the one with the highest taskId. After executing, the taskId is removed from the system. Return the userId associated with the executed task. If no tasks are available, return -1.

        Note that a user may be assigned multiple tasks.



        Example 1:

        Input:
        ["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"]
        [[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]

        Output:
        [null, null, null, 3, null, null, 5]

        Explanation

        TaskManager taskManager = new TaskManager([[1, 101, 10], [2, 102, 20], [3, 103, 15]]); // Initializes with three tasks for Users 1, 2, and 3.
        taskManager.add(4, 104, 5); // Adds task 104 with priority 5 for User 4.
        taskManager.edit(102, 8); // Updates priority of task 102 to 8.
        taskManager.execTop(); // return 3. Executes task 103 for User 3.
        taskManager.rmv(101); // Removes task 101 from the system.
        taskManager.add(5, 105, 15); // Adds task 105 with priority 15 for User 5.
        taskManager.execTop(); // return 5. Executes task 105 for User 5.


        Constraints:

        1 <= tasks.length <= 105
        0 <= userId <= 105
        0 <= taskId <= 105
        0 <= priority <= 109
        0 <= newPriority <= 109
        At most 2 * 105 calls will be made in total to add, edit, rmv, and execTop methods.
        The input is generated such that taskId will be valid.
     */

    class TaskManager
    {
        PriorityQueue<int[]> pq;
        Map<Integer,Integer> taskOwner;
        Map<Integer,Integer> taskPriority;
        public TaskManager(List<List<Integer>> tasks)
        {
            pq=new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] i, int[] j)
                {
                    int res=j[0]-i[0];
                    return res==0?j[1]-i[1]:res;
                }
            });
            taskOwner=new HashMap<>();
            taskPriority=new HashMap<>();
            for(List<Integer> l:tasks)
                add(l.get(0),l.get(1), l.get(2));
        }

        public void add(int userId, int taskId, int priority)
        {
            pq.offer(new int[]{priority,taskId});
            taskOwner.put(taskId,userId);
            taskPriority.put(taskId,priority);
        }

        public void edit(int taskId, int newPriority)
        {
            pq.offer(new int[]{newPriority,taskId});
            taskPriority.put(taskId,newPriority);
        }

        public void rmv(int taskId)
        {
            taskPriority.put(taskId,-1);
        }

        public int execTop()
        {
            while(!pq.isEmpty())
            {
                int[] curr=pq.poll();
                int priority=curr[0], taskId=curr[1];
                if(taskPriority.get(taskId)==priority)
                {
                    taskPriority.put(taskId,-1);
                    return taskOwner.get(taskId);
                }
            }
            return -1;
        }
    }

    public void test()
    {
        List<List<Integer>> l =new ArrayList<>();
        l.add(Arrays.asList(1,101,10));
        l.add(Arrays.asList(2,102,20));
        l.add(Arrays.asList(3,103,15));
        TaskManager obj=new TaskManager(l);
        obj.add(4,104,5);
        obj.edit(102,8);
        System.out.println(obj.execTop());
        obj.rmv(101);
        obj.add(5,105,15);
        System.out.println(obj.execTop());
    }

    public static void main(String[] args)
    {
        DesignTaskManager obj=new DesignTaskManager();
        obj.test();
    }
}
