import java.util.*;

public class OraclePrep
{
    {/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].*/}
    public int[] twoSum(int[] nums, int target)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            int c=target-nums[i];
            if(map.containsKey(c))
                return new int[]{map.get(c),i};
            else
                map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

    {
        /*
        Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

        Basically, the deletion can be divided into two stages:

        Search for a node to remove.
        If the node is found, delete the node.
        Note: Time complexity should be O(height of tree).

        Example:

        root = [5,3,6,2,4,null,7]
        key = 3

            5
           / \
          3   6
         / \   \
        2   4   7

        Given key to delete is 3. So we find the node with value 3 and delete it.

        One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

            5
           / \
          4   6
         /     \
        2       7

        Another valid answer is [5,2,6,null,4,null,7].

            5
           / \
          2   6
           \   \
         */
    }
    public TreeNode deleteNode(TreeNode root,int key)
    {
        if(root==null)
            return null;
        else if(key<root.val)
            root.left=deleteNode(root.left,key);
        else if(key>root.val)
            root.right=deleteNode(root.right,key);
        else
        {
            if(root.left==null)
                return root.right;
            else if(root.right==null)
                return root.left;
            else
            {
                root.val=getMin(root.right);
                root.right=deleteNode(root.right,root.val);
            }
        }
        return root;
    }
    public int getMin(TreeNode root)
    {
        while(root.left!=null)
            root=root.left;
        return root.val;
    }

    {
        /*
        Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

        Example:

        Input:
        [
          1->4->5,
          1->3->4,
          2->6
        ]
        Output: 1->1->2->3->4->4->5->6
         */
    }
    public ListNode mergeKLists(ListNode[] lists)
    {
        return mergeList(lists,0,lists.length-1);
    }
    public ListNode mergeList(ListNode[] lists, int l,int h)
    {
        if(l>h)
            return null;
        if(l==h)
            return lists[l];
        int mid=(h+l)/2;
        ListNode l1=mergeList(lists,l,mid);
        ListNode l2=mergeList(lists,mid+1,h);
        return merge(l1,l2);
    }
    public ListNode merge(ListNode l1, ListNode l2)
    {
        if(l1==null)
            return l2;
        else if(l2==null)
            return l1;
        else
        {
            if(l1.data<l2.data)
            {
                l1.next=merge(l1.next,l2);
                return l1;
            }
            else
            {
                l2.next=merge(l1,l2.next);
                return l2;
            }
        }
    }
    {
        /*
        Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

        You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

        Example 1:
        Input:
        ["Shogun", "Tapioca Express", "Burger King", "KFC"]
        ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
        Output: ["Shogun"]
        Explanation: The only restaurant they both like is "Shogun".
        Example 2:
        Input:
        ["Shogun", "Tapioca Express", "Burger King", "KFC"]
        ["KFC", "Shogun", "Burger King"]
        Output: ["Shogun"]
        Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
        Note:
        The length of both lists will be in the range of [1, 1000].
        The length of strings in both lists will be in the range of [1, 30].
        The index is starting from 0 to the list length minus 1.
        No duplicates in both lists.
         */

    }
    public String[] findRestaurant(String[] list1, String[] list2)
    {
        List<String> res=new ArrayList<>();
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<list2.length;i++)
            map.put(list2[i],i);
        int min=Integer.MAX_VALUE;
        for(int i=0;i<list1.length;i++)
        {
            if(map.containsKey(list1[i]))
            {
                int tmp=i+map.get(list1[i]);
                if(tmp<min)
                {
                    res=new ArrayList<>();
                    res.add(list1[i]);
                    min=tmp;
                }
                else if(tmp==min)
                    res.add(list1[i]);
            }
        }
        return res.toArray(new String[res.size()]);
    }

    {
        /*
        Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

        Example 1:

        Input:
        11110
        11010
        11000
        00000

        Output: 1
        Example 2:

        Input:
        11000
        11000
        00100
        00011

        Output: 3
         */
    }
    public int numIslands(char[][] grid)
    {
        int cnt=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='1')
                {
                    dfs(grid,i,j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public void dfs(char[][] grid, int i, int j)
    {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!='1')
            return;
        grid[i][j]='0';
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
    }

    {
        /*
        Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

        A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



        Example:

        Input: "23"
        Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
        Note:

        Although the above answer is in lexicographical order, your answer could be in any order you want.
         */
    }
    public List<String> letterCombinations(String digits)
    {
        if(digits==""|| digits.length()==0)
            return new ArrayList<>();
        Map<Character,String> keypad=new HashMap<>();
        keypad.put('0'," ");
        keypad.put('1',"");
        keypad.put('2',"abc");
        keypad.put('3',"def");
        keypad.put('4',"ghi");
        keypad.put('5',"jkl");
        keypad.put('6',"mno");
        keypad.put('7',"pqrs");
        keypad.put('8',"tuv");
        keypad.put('9',"wxyz");
        List<String> res=new ArrayList<>();
        compute(res,keypad,digits,"",0);
        return res;
    }
    public void compute(List<String> res, Map<Character,String> keypad, String digits, String tmp,int offset)
    {
        if(tmp.length()>digits.length())
            return;
        if(tmp.length()==digits.length())
        {
            res.add(tmp);
            return;
        }
        char c=digits.charAt(offset);
        String str=keypad.get(c);
        for(int i=0;i<str.length();i++)
        {
            compute(res,keypad,digits,tmp+str.charAt(i),offset+1);
        }

    }

    {
        /*
        Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

        The same repeated number may be chosen from candidates unlimited number of times.

        Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

        Input: candidates = [2,3,6,7], target = 7,
        A solution set is:
        [
          [7],
          [2,2,3]
        ]
        Example 2:

        Input: candidates = [2,3,5], target = 8,
        A solution set is:
        [
          [2,2,2,2],
          [2,3,3],
          [3,5]
        ]
         */
    }
    public List<List<Integer>> combinationSum(int[] candidates,int target)
    {
        if(candidates==null || candidates.length==0)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        compute(candidates,target,0,res,new ArrayList<>(),0);
        return res;
    }
    public void compute(int[] candidates,int target, int sum, List<List<Integer>> res, List<Integer> temp,int start)
    {
        if(sum==target)
            res.add(new ArrayList<>(temp));
        if(sum>target)
            return;
        for(int i=start;i<candidates.length;i++)
        {
            temp.add(candidates[i]);
            compute(candidates,target,sum+candidates[i],res,temp,i);
            temp.remove(temp.size()-1);
        }
    }
    {
        /*
        Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

        Each number in candidates may only be used once in the combination.

        Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

        Input: candidates = [10,1,2,7,6,1,5], target = 8,
        A solution set is:
        [
          [1, 7],
          [1, 2, 5],
          [2, 6],
          [1, 1, 6]
        ]
        Example 2:

        Input: candidates = [2,5,2,1,2], target = 5,
        A solution set is:
        [
          [1,2,2],
          [5]
        ]
         */
    }
    public List<List<Integer>> combinationSum2(int[] candidates,int target)
    {
        if(candidates==null || candidates.length==0)
            return new ArrayList<>();
        Set<List<Integer>> res=new HashSet<>();
        Arrays.sort(candidates);
        compute2(candidates,target,0,0,res,new ArrayList<>());
        return new ArrayList<>(res);
    }
    public void compute2(int[] nums, int target, int sum, int start,Set<List<Integer>> res, List<Integer> temp)
    {
        if(sum>target)
            return;
        if(sum==target)
        {
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i=start;i<nums.length;i++)
        {
            temp.add(nums[i]);
            compute2(nums,target,sum+nums[i],i+1,res,temp);
            temp.remove(temp.size()-1);
        }

    }

    {
        /*
        Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

        Note:

        All numbers will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

        Input: k = 3, n = 7
        Output: [[1,2,4]]
        Example 2:

        Input: k = 3, n = 9
        Output: [[1,2,6], [1,3,5], [2,3,4]]
         */
    }

    public List<List<Integer>> combinationSum3(int k, int n)
    {
        if(k==0 && n!=0)
            return new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        compute3(k,n,0,1,res,new ArrayList<>());
        return res;
    }
    public void compute3(int k, int n, int sum, int start, List<List<Integer>> res, List<Integer> temp)
    {
        if(temp.size()>k || sum>n)
            return;
        else if(temp.size()==k && sum==n)
        {
            res.add(new ArrayList<>(temp));
            return;
        }
        else
        {
            for(int i=start;i<=9;i++)
            {
                temp.add(i);
                compute3(k,n,sum+i,i+1,res,temp);
                temp.remove(temp.size()-1);
            }
        }
    }
    {
        /*
        Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

        Example 1:

        Input: [[0, 30],[5, 10],[15, 20]]
        Output: 2
        Example 2:

        Input: [[7,10],[2,4]]
        Output: 1
        NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
         */
    }
    public int minMeetingRooms(int[][] intervals)
    {
        int[] start=new int[intervals.length];
        int[] end=new int[intervals.length];
        for(int i=0;i<intervals.length;i++)
        {
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int sp=0,ep=0,count=0;
        while(sp<start.length)
        {
            if(start[sp]<end[ep])
                count++;
            else
                ep++;
            sp++;
        }
        return count;
    }
    {
        /*
        Given a collection of intervals, merge all overlapping intervals.

        Example 1:

        Input: [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
        Example 2:

        Input: [[1,4],[4,5]]
        Output: [[1,5]]
        Explanation: Intervals [1,4] and [4,5] are considered overlapping.
        NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
         */
    }

    public int[][] merge(int[][] intervals)
    {
        Arrays.sort(intervals, new Comparator<int[]>()
        {
            public int compare(int[] a, int[] b)
            {
                return a[0]-b[0];
            }
        });
        LinkedList<int[]> res=new LinkedList<>();
        for(int[] i: intervals)
        {
            if(res.isEmpty() || res.getLast()[1]<i[0])
                res.add(i);
            else
                res.getLast()[1]=Math.max(res.getLast()[1],i[1]);
        }
        return res.toArray(new int[res.size()][]);
    }
    public static void main(String[] args)
    {
        OraclePrep obj=new OraclePrep();
        System.out.println(obj.twoSum(new int[]{2,7,11,15},9)[1]);
        System.out.println(obj.letterCombinations("23"));
        System.out.println(obj.combinationSum2(new int[]{10,1,2,7,6,1,5},8));
        System.out.println(obj.combinationSum3(3,9));
        System.out.println(obj.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
    }
}
