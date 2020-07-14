import java.util.*;

//OA questions from Leetcode user Sithis
public class AmazonOAQuestions
{


    //Top K Frequently Mentioned Keywords
    {
        /*
        Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to least frequently mentioned.

        The comparison of strings is case-insensitive.
        Multiple occurances of a keyword in a review should be considred as a single mention.
        If keywords are mentioned an equal number of times in reviews, sort alphabetically.

        Example 1:

        Input:
        k = 2
        keywords = ["anacell", "cetracular", "betacellular"]
        reviews = [
          "Anacell provides the best services in the city",
          "betacellular has awesome services",
          "Best services provided by anacell, everyone should use anacell",
        ]

        Output:
        ["anacell", "betacellular"]

        Explanation:
        "anacell" is occuring in 2 different reviews and "betacellular" is only occuring in 1 review.
        Example 2:

        Input:
        k = 2
        keywords = ["anacell", "betacellular", "cetracular", "deltacellular", "eurocell"]
        reviews = [
          "I love anacell Best services; Best services provided by anacell",
          "betacellular has great services",
          "deltacellular provides much better services than betacellular",
          "cetracular is worse than anacell",
          "Betacellular is better than deltacellular.",
        ]

        Output:
        ["betacellular", "anacell"]

        Explanation:
        "betacellular" is occuring in 3 different reviews. "anacell" and "deltacellular" are occuring in 2 reviews, but "anacell" is lexicographically smaller.
         */
    }
    public List<String> kFrequentlyMentionedKeywords(int k, List<String> keywords, List<String> reviews)
    {
        if(keywords==null || keywords.size()==0)
            return new ArrayList<>();
        Map<String,Integer> freq=new HashMap<>();

        for(String s:keywords)
            for(String review:reviews)
                if(review.toLowerCase().contains(s.toLowerCase()))
                    freq.put(s,freq.getOrDefault(s,0)+1);



        PriorityQueue<String> pq=new PriorityQueue<String>(new Comparator<String>(){
            public int compare(String s1, String s2)
            {
                int cmp=freq.get(s2)-freq.get(s1);
                if(cmp==0)
                    return s1.compareTo(s2);
                return cmp;
            }
        });

        pq.addAll(freq.keySet());
        List<String> result=new ArrayList<>();
        for(int i=0;i<k;i++)
            result.add(pq.poll());
        return result;
    }

    //Zombies in a matrix
    {
        /*
        Given a 2D grid, each cell is either a zombie 1 or a human 0. Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour. Find out how many hours does it take to infect all humans?

        Example:

        Input:
        [[0, 1, 1, 0, 1],
         [0, 1, 0, 1, 0],
         [0, 0, 0, 0, 1],
         [0, 1, 0, 0, 0]]

        Output: 2

        Explanation:
        At the end of the 1st hour, the status of the grid:
        [[1, 1, 1, 1, 1],
         [1, 1, 1, 1, 1],
         [0, 1, 0, 1, 1],
         [1, 1, 1, 0, 1]]

        At the end of the 2nd hour, the status of the grid:
        [[1, 1, 1, 1, 1],
         [1, 1, 1, 1, 1],
         [1, 1, 1, 1, 1],
         [1, 1, 1, 1, 1]]
         */
    }

    public int minHours(List<List<Integer>> grid)
    {
        if(grid==null || grid.size()==0)
            return 0;
        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<grid.size();i++)
            for(int j=0;j<grid.get(0).size();j++)
                if(grid.get(i).get(j)==1)
                    q.offer(new int[]{i,j});

        int count=0;
        int[][] dirs={{1,0},{0,1},{-1,0},{0,-1}};
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                for(int[] d:dirs)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    if(nr<0 || nr>=grid.size() || nc<0 || nc>=grid.get(0).size() || grid.get(nr).get(nc)!=0)
                        continue;
                    grid.get(nr).set(nc,1);
                    q.offer(new int[]{nr,nc});
                }
            }
            count++;
        }
        return count-1;
    }

    //Critical Routers
    {
       /*
        Get the articulation point/strongly connected vertices for a given graph.


        You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which,
        when removed along with associated edges, makes the graph disconnected (or more precisely,
        increases the number of connected components in the graph).

        The task is to find all articulation points in the given graph.

        Input:
        The input to the function/method consists of three arguments:

        numNodes, an integer representing the number of nodes in the graph.
        numEdges, an integer representing the number of edges in the graph.
        edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
        Output:
        Return a list of integers representing the critical nodes.
     */
    }

    int time=1;
    public List<Integer> getArticulationPoints(int[][] graph, int n)
    {
        List<Integer>[] adjList=new List[n];
        for(int i=0;i<n;i++)
            adjList[i]=new ArrayList<>();
        int[] timer=new int[n];
        List<Integer> result=new ArrayList<>();
        for(int[] i:graph)
        {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
        }

        for(int i=0;i<n;i++)
        {
            if(timer[i]==0)
                trajan_dfs(i,-1,timer,result,adjList);

        }
        return result;
    }
    public int trajan_dfs(int src, int p, int[] timer, List<Integer> result, List<Integer>[] adjList)
    {
        if(timer[src]>0)
            return timer[src];
        timer[src]=time++;
        if(adjList[src].size()==0)
            return timer[src];
        int min=Integer.MAX_VALUE;
        for(int nxt:adjList[src])
        {
            if(nxt==p)
                continue;
            min=Math.min(min,trajan_dfs(nxt,src,timer,result,adjList));
        }

        if(p!=-1 && min!=Integer.MAX_VALUE)
        {
            int max=Integer.MIN_VALUE;
            for(int nxt:adjList[src])
                max=Math.max(max,timer[nxt]);
            if(timer[src]<=max)
                result.add(src);
        }
        timer[src]=Math.min(timer[src],min);
        return timer[src];
    }

    //Product Suggestions
    {
        /*
        Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed.
        Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

        Return list of lists of the suggested products after each character of searchWord is typed.
        Example 1:

        Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
        Output: [
        ["mobile","moneypot","monitor"],
        ["mobile","moneypot","monitor"],
        ["mouse","mousepad"],
        ["mouse","mousepad"],
        ["mouse","mousepad"]
        ]
        Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
        After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
        After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
        Example 2:

        Input: products = ["havana"], searchWord = "havana"
        Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
        Example 3:

        Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
        Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
        Example 4:

        Input: products = ["havana"], searchWord = "tatiana"
        Output: [[],[],[],[],[],[],[]]
         */
    }
    class ProductTrie
    {
        ProductTrie[] next;
        LinkedList<String> products;
        public ProductTrie()
        {
            products=new LinkedList<>();
            next=new ProductTrie[26];
        }
    }

    public void insert(ProductTrie root, String product)
    {
        for(char c:product.toCharArray())
        {
            if(root.next[c-'a']==null)
                root.next[c-'a']=new ProductTrie();
            root=root.next[c-'a'];
            root.products.add(product);
            if(root.products.size()>3)
                root.products.pollLast();
        }

    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord)
    {
        Arrays.sort(products);
        ProductTrie root=new ProductTrie();
        for(String str:products)
            insert(root,str);

        List<List<String>> result=new ArrayList<>();
        for(char c:searchWord.toCharArray())
        {
            if(root!=null)
                root=root.next[c-'a'];
            result.add(root==null?new ArrayList<>():root.products);
        }
        return result;
    }

    //Number Of Clusters
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
        if(grid==null || grid[0].length==0)
            return 0;
        int count=0;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
                if(grid[i][j]=='1')
                {
                    count++;
                    dfs(grid,i,j);
                }
        return count;
    }

    public void dfs(char[][] grid,int i, int j)
    {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!='1')
            return;
        grid[i][j]='0';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }

    //Reorder Data in Log Files
    {
        /*
        You have an array of logs.  Each log is a space delimited string of words.

        For each log, the first word in each log is an alphanumeric identifier.  Then, either:

        Each word after the identifier will consist only of lowercase letters, or;
        Each word after the identifier will consist only of digits.
        We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

        Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

        Return the final order of the logs.

        Example 1:

        Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
        Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]

        Constraints:

        0 <= logs.length <= 100
        3 <= logs[i].length <= 100
        logs[i] is guaranteed to have an identifier, and a word after the identifier.
         */
    }

    public String[] reorderLogFiles(String[] logs)
    {
        Arrays.sort(logs,new Comparator<String>(){
            public int compare(String s1, String s2)
            {
                String[] l1=s1.split(" ",2);
                String[] l2=s2.split(" ",2);
                boolean isLetter1=Character.isLetter(l1[1].charAt(0));
                boolean isLetter2=Character.isLetter(l2[1].charAt(0));
                if(isLetter1 && isLetter2)
                {
                    int cmp=l1[1].compareTo(l2[1]);
                    if(cmp==0)
                        return l1[0].compareTo(l2[0]);
                    return cmp;
                }
                else
                {
                    if(!isLetter1 && !isLetter2)
                        return 0;
                    else if(isLetter1 && !isLetter2)
                        return -1;
                    else
                        return 1;
                }
            }
        });
        return logs;
    }

    //Partition Labels
    {
        /*
        A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

        Example 1:
        Input: S = "ababcbacadefegdehijhklij"
        Output: [9,7,8]
        Explanation:
        The partition is "ababcbaca", "defegde", "hijhklij".
        This is a partition so that each letter appears in at most one part.
        A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
         */
    }

    public List<Integer> partitionLabels(String S)
    {
        if(S==null || S.length()==0)
            return new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        int[] map=new int[26];
        for(int i=0;i<S.length();i++)
            map[S.charAt(i)-'a']=i;

        int last=0;
        int prev=0;
        for(int i=0;i<S.length();i++)
        {
            last=Math.max(last,map[S.charAt(i)-'a']);
            if(last==i)
            {
                result.add(i-prev+1);
                prev=i+1;
            }
        }
        return result;
    }

    //Optimal Utilization
    {
        /*
        Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value.
        Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible.
        Return a list of ids of selected elements. If no pair is possible, return an empty list.

        Example 1:
        Input:
        a = [[1, 2], [2, 4], [3, 6]]
        b = [[1, 2]]
        target = 7
        Output: [[2, 1]]

        Explanation:
        There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
        Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
        Example 2:

        Input:
        a = [[1, 3], [2, 5], [3, 7], [4, 10]]
        b = [[1, 2], [2, 3], [3, 4], [4, 5]]
        target = 10

        Output: [[2, 4], [3, 2]]

        Explanation:
        There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
        Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
        These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
        Example 3:

        Input:
        a = [[1, 8], [2, 7], [3, 14]]
        b = [[1, 5], [2, 10], [3, 14]]
        target = 20

        Output: [[3, 1]]
        Example 4:

        Input:
        a = [[1, 8], [2, 15], [3, 9]]
        b = [[1, 8], [2, 11], [3, 12]]
        target = 20

        Output: [[1, 3], [3, 2]]
         */
    }
    public List<int[]> getPairs(List<int[]> a, List<int[]> b, int target)
    {
        Collections.sort(a,new Comparator<int[]>(){
            public int compare(int[] i, int[] j)
            {
                return i[1]-j[1];
            }
        });

        Collections.sort(b, new Comparator<int[]>() {
            public int compare(int[] i, int[] j) {
                return i[1]-j[1];
            }
        });

        int sum=Integer.MIN_VALUE;
        List<int[]> result=new ArrayList<>();
        int i=0,j=b.size()-1;
        while(i<a.size() && j>=0)
        {
            int val=a.get(i)[1]+b.get(j)[1];
            if(val<=target)
            {
                if(val==sum)
                    result.add(new int[]{a.get(i)[0],b.get(j)[0]});
                else if(val>sum)
                {
                    result=new ArrayList<>();
                    result.add(new int[]{a.get(i)[0],b.get(j)[0]});
                    sum=val;
                }
                i++;
            }
            else
                j--;
        }
        return result;
    }


    //Min Cost to Connect Ropes
    {
        /*
        Given n ropes of different lengths, we need to connect these ropes into one rope. We can connect only 2 ropes at a time.
        The cost required to connect 2 ropes is equal to sum of their lengths. The length of this connected rope is also equal to the sum of their lengths.
        This process is repeated until n ropes are connected into a single rope. Find the min possible cost required to connect all ropes.

        Example 1:

        Input: ropes = [8, 4, 6, 12]
        Output: 58
        Explanation: The optimal way to connect ropes is as follows
        1. Connect the ropes of length 4 and 6 (cost is 10). Ropes after connecting: [8, 10, 12]
        2. Connect the ropes of length 8 and 10 (cost is 18). Ropes after connecting: [18, 12]
        3. Connect the ropes of length 18 and 12 (cost is 30).
        Total cost to connect the ropes is 10 + 18 + 30 = 58
        Example 2:

        Input: ropes = [20, 4, 8, 2]
        Output: 54
        Example 3:

        Input: ropes = [1, 2, 5, 10, 35, 89]
        Output: 224
        Example 4:

        Input: ropes = [2, 2, 3, 3]
        Output: 20
         */
    }

    public int connectSticks(int[] sticks)
    {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        int cost=0;
        for(int i:sticks)
            pq.offer(i);
        while(pq.size()>1)
        {
            int sum=pq.poll()+pq.poll();
            pq.offer(sum);
            cost+=sum;
        }
        return cost;
    }

    //Treasure Island 1
    {
        /*
        You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous reefs.
        Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to the treasure island.
        Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from the top-left corner of the map and can move one block up, down, left or right at a time.
        The treasure island is marked as X in a block of the matrix. X will not be at the top-left corner. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks.
        You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is always safe. Output the minimum number of steps to get to the treasure.
         */
    }

    public int treasureIsland1BFS(char[][] grid)
    {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        int count=0;
        Queue<int[]> q=new LinkedList<>();
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        q.offer(new int[]{0,0});
        visited[0][0]=true;
        int[][] dirs={{0,1},{1,0},{0,-1},{-1,0}};
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                if(grid[curr[0]][curr[1]]=='X')
                    return count;
                for(int[] d:dirs)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || visited[nr][nc] || grid[nr][nc]=='D')
                        continue;
                    q.offer(new int[]{nr,nc});
                    visited[nr][nc]=true;
                }
            }
            count++;
        }
        return -1;
    }

    int treasureIlsandMin=Integer.MAX_VALUE;
    public int treasureIsland1DFS(char[][] grid)
    {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        treasureIlsand1Helper(grid,0,0,new boolean[grid.length][grid[0].length],0);
        return treasureIlsandMin;
    }
    public void treasureIlsand1Helper(char[][] grid, int i, int j, boolean[][] visited, int count)
    {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]=='D' || visited[i][j] || count>treasureIlsandMin)
            return;
        if(grid[i][j]=='X')
            treasureIlsandMin=Math.min(treasureIlsandMin,count);
        visited[i][j]=true;
        treasureIlsand1Helper(grid,i+1,j,visited,count+1);
        treasureIlsand1Helper(grid,i-1,j,visited,count+1);
        treasureIlsand1Helper(grid,i,j+1,visited,count+1);
        treasureIlsand1Helper(grid,i,j-1,visited,count+1);
    }


    //Treasure Island 2
    {
        /*
        You have a map that marks the locations of treasure islands. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in.
        There are other explorers trying to find the treasure. So you must figure out a shortest route to one of the treasure islands.

        Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from one of the starting point (marked as S) of the map and can move one block up, down, left or right at a time.
        The treasure island is marked as X. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks.
        You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number of steps to get to any of the treasure islands.

        Example:

        Input:
        [['S', 'O', 'O', 'S', 'S'],
         ['D', 'O', 'D', 'O', 'D'],
         ['O', 'O', 'O', 'O', 'X'],
         ['X', 'D', 'D', 'O', 'O'],
         ['X', 'D', 'D', 'D', 'O']]

        Output: 3
        Explanation:
        You can start from (0,0), (0, 3) or (0, 4). The treasure locations are (2, 4) (3, 0) and (4, 0). Here the shortest route is (0, 3), (1, 3), (2, 3), (2, 4).
         */
    }

    public int treasureIsland2BFS(char[][] grid)
    {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        Queue<int[]> q=new LinkedList<>();
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
                if(grid[i][j]=='S')
                {
                    q.offer(new int[]{i,j});
                    visited[i][j]=true;
                }


        int count=0;
        int[][] dirs={{1,0},{0,1},{-1,0},{0,-1}};
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                if(grid[curr[0]][curr[1]]=='X')
                    return count;
                for(int[] d:dirs)
                {
                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];
                    if(nr<0 || nr>=grid.length || nc<0 || nc>=grid[0].length || grid[nr][nc]=='D' || visited[nr][nc])
                        continue;
                    q.offer(new int[]{nr,nc});
                    visited[nr][nc]=true;
                }
            }
            count++;
        }
        return -1;
    }

    //Find Pairs with given sum
    {
        /*
        Given a list of positive integers nums and an int target, return indices of the two numbers such that they add up to a target - 30.

        Conditions:

        You will pick exactly 2 numbers.
        You cannot pick the same element twice.
        If you have muliple pairs, select the pair with the largest number.
        Example 1:

        Input: nums = [1, 10, 25, 35, 60], target = 90
        Output: [2, 3]
        Explanation:
        nums[2] + nums[3] = 25 + 35 = 60 = 90 - 30
        Example 2:

        Input: nums = [20, 50, 40, 25, 30, 10], target = 90
        Output: [1, 5]
        Explanation:
        nums[0] + nums[2] = 20 + 40 = 60 = 90 - 30
        nums[1] + nums[5] = 50 + 10 = 60 = 90 - 30
        You should return the pair with the largest number.
         */
    }
    public List<Integer> findPairs(List<Integer> nums, int sum)
    {
        if(nums==null || nums.size()==0)
            return new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.size();i++)
        {
            int comp=sum-30-nums.get(i);
            if(map.containsKey(comp))
            {
                if(result.isEmpty() || Math.max(result.get(0),result.get(1))<Math.max(i,map.get(comp)))
                    result=new ArrayList<>(Arrays.asList(i,map.get(comp)));

            }
            map.put(nums.get(i),i);
        }
        return result;
    }

    //Merge Two Sorted Lists
    {
        /*
        Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.

        Example:

        Input: 1->2->4, 1->3->4
        Output: 1->1->2->3->4->4
         */
    }
    class ListNode
    {
        int val;
        ListNode next;
        public ListNode(int val){this.val=val;}
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        if(l1==null && l2==null)
            return null;
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        if(l1.val<l2.val)
        {
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
        }
        else
        {
            l2.next=mergeTwoLists(l1,l2.next);
            return l2;
        }
    }


    //Subtree of another Tree
    {
        /*
        Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s.
        A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

        Example 1:
        Given tree s:

             3
            / \
           4   5
          / \
         1   2
        Given tree t:
           4
          / \
         1   2
        Return true, because t has the same structure and node values with a subtree of s.


        Example 2:
        Given tree s:

             3
            / \
           4   5
          / \
         1   2
            /
           0
        Given tree t:
           4
          / \
         1   2
        Return false.
         */
    }
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right)
        {
            this.val = val;
            this.left = left;
            this.right = right;
      }
    }

    public boolean isSubtree(TreeNode s, TreeNode t)
    {
        if(s==null && t==null)
            return true;
        if(s==null || t==null)
            return false;
        if(s.val==t.val && isSubTreeHelper(s,t))
            return true;
        return isSubtree(s.left,t) || isSubtree(s.right,t);
    }

    public boolean isSubTreeHelper(TreeNode s, TreeNode t)
    {
        if(s==null && t==null)
            return true;
        if(s==null || t==null)
            return false;
        if(s.val!=t.val)
            return false;
        return isSubTreeHelper(s.left,t.left) && isSubTreeHelper(s.right,t.right);
    }

    //Search a 2D Matrix II
    {
        /*
        Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

        Integers in each row are sorted in ascending from left to right.
        Integers in each column are sorted in ascending from top to bottom.
        Example:

        Consider the following matrix:

        [
          [1,   4,  7, 11, 15],
          [2,   5,  8, 12, 19],
          [3,   6,  9, 16, 22],
          [10, 13, 14, 17, 24],
          [18, 21, 23, 26, 30]
        ]
        Given target = 5, return true.

        Given target = 20, return false.
         */
    }
    public boolean searchMatrix(int[][] matrix, int target)
    {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return false;
        int row=matrix.length-1, col=0;
        while(row>=0 && col<matrix[0].length)
        {
            if(matrix[row][col]==target)
                return true;
            else if(matrix[row][col]<target)
                col++;
            else
                row--;
        }
        return false;
    }

    //Critical Connections
    {
        /*
        There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b]
        represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

        A critical connection is a connection that, if removed, will make some server unable to reach some other server.

        Return all critical connections in the network in any order.

        Example 1:

        Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        Output: [[1,3]]
        Explanation: [[3,1]] is also accepted.

        Constraints:

        1 <= n <= 10^5
        n-1 <= connections.length <= 10^5
        connections[i][0] != connections[i][1]
        There are no repeated connections.
         */
    }
    int T=1;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections)
    {
        if(n==0 || connections==null || connections.size()==0)
            return new ArrayList<>();
        List<Integer>[] adjList=new List[n];
        for(int i=0;i<n;i++)
            adjList[i]=new ArrayList<>();

        for(List<Integer> i:connections)
        {
            adjList[i.get(0)].add(i.get(1));
            adjList[i.get(1)].add(i.get(0));
        }
        List<List<Integer>> result=new ArrayList<>();
        int[] timer=new int[n];
        criticalConnectionsDFS(adjList,0,-1,result,timer);
        return result;
    }

    public int criticalConnectionsDFS(List<Integer>[] adjList, int src, int p, List<List<Integer>> result, int[] timer)
    {
        if(timer[src]>0)
            return timer[src];
        timer[src]=T++;
        int min=Integer.MAX_VALUE;
        for(int nxt:adjList[src])
        {
            if(nxt==p)
                continue;
            min=Math.min(min,criticalConnectionsDFS(adjList,nxt,src,result,timer));
        }

        if(min>=timer[src])
        {
            if(p!=-1)
                result.add(Arrays.asList(src,p));
        }
        return Math.min(timer[src],min);
    }

    //Favorite Genres
    {
        /*
        Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.

        Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.

        The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s).
        Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.

        Example 1:

        Input:
        userSongs = {
           "David": ["song1", "song2", "song3", "song4", "song8"],
           "Emma":  ["song5", "song6", "song7"]
        },
        songGenres = {
           "Rock":    ["song1", "song3"],
           "Dubstep": ["song7"],
           "Techno":  ["song2", "song4"],
           "Pop":     ["song5", "song6"],
           "Jazz":    ["song8", "song9"]
        }

        Output: {
           "David": ["Rock", "Techno"],
           "Emma":  ["Pop"]
        }

        Explanation:
        David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
        Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
        Example 2:

        Input:
        userSongs = {
           "David": ["song1", "song2"],
           "Emma":  ["song3", "song4"]
        },
        songGenres = {}

        Output: {
           "David": [],
           "Emma":  []
        }
         */
    }

    public Map<String,List<String>> favoriteGenre(Map<String,List<String>> userSongs, Map<String,List<String>> songGenres)
    {
        if(userSongs==null || userSongs.size()==0)
            return new HashMap<>();
        Map<String,String> genreMap=new HashMap<>();
        for(String key:songGenres.keySet())
            for(String song:songGenres.get(key))
                genreMap.put(song,key);


        Map<String,List<String>> result=new HashMap<>();
        for(String name:userSongs.keySet())
        {
            result.put(name,new ArrayList<>());
            Map<String,Integer> genreFreq=new HashMap<>();
            int max=0;
            for(String song:userSongs.get(name))
            {
                String genre=genreMap.get(song);
                genreFreq.put(genre,genreFreq.getOrDefault(genre,0)+1);
                max=Math.max(max,genreFreq.get(genre));
            }
            for(String genre:genreFreq.keySet())
            {
                if(genreFreq.get(genre)==max)
                    result.get(name).add(genre);
            }
        }

        return result;
    }

    //Two Sum Unique Pairs
    {
        /*
        Given an int array nums and an int target, find how many unique pairs in the array such that their sum is equal to target. Return the number of pairs.

        Example 1:

        Input: nums = [1, 1, 2, 45, 46, 46], target = 47
        Output: 2
        Explanation:
        1 + 46 = 47
        2 + 45 = 47
        Example 2:

        Input: nums = [1, 1], target = 2
        Output: 1
        Explanation:
        1 + 1 = 2
        Example 3:

        Input: nums = [1, 5, 1, 5], target = 6
        Output: 1
        Explanation:
        [1, 5] and [5, 1] are considered the same.
         */
    }

    public int twoSumUniquePairs(int[] nums, int target)
    {
        if(nums==null || nums.length==0)
            return 0;
        Map<Integer,Integer> map=new HashMap<>();
        Set<String> set=new HashSet<>();
        for(int i=0;i<nums.length;i++)
        {
            int comp=target-nums[i];
            if(map.containsKey(comp))
            {
                int min=Math.min(nums[i],nums[map.get(comp)]);
                int max=Math.max(nums[i],nums[map.get(comp)]);
                String key=min+","+max;
                if(set.contains(key))
                    continue;
                set.add(key);
            }
            map.put(nums[i],i);
        }
        return set.size();
    }

    //Spiral Matrix II
    {
        /*
            Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

            Example:

            Input: 3
            Output:
            [
             [ 1, 2, 3 ],
             [ 8, 9, 4 ],
             [ 7, 6, 5 ]
            ]
         */
    }

    public int[][] generateMatrix(int n)
    {
        if(n==0)
            return new int[0][];
        int[][] result=new int[n][n];
        int count=1;
        int rs=0,re=n-1;
        int cs=0,ce=n-1;
        while(rs<=re && cs<=ce)
        {
            for(int i=cs;i<=ce;i++)
            {
                result[rs][i]=count;
                count++;
            }
            rs++;
            for(int i=rs;i<=re;i++)
            {
                result[i][ce]=count;
                count++;
            }
            ce--;
            if(rs<=re)
            {
                for(int i=ce;i>=cs;i--)
                {
                    result[re][i]=count;
                    count++;
                }
            }
            re--;
            if(cs<=ce)
            {
                for(int i=re;i>=rs;i--)
                {
                    result[i][cs]=count;
                    count++;
                }
            }
            cs++;
        }
        return result;

    }

    //Susbtrings with exactly k different chars
    {
        /*
        Given a string s and an int k, return an int representing the number of substrings (not unique) of s with exactly k distinct characters. If the given string doesn't have k distinct characters, return 0.

        Example 1:

        Input: s = "pqpqs", k = 2
        Output: 7
        Explanation: ["pq", "pqp", "pqpq", "qp", "qpq", "pq", "qs"]
        Example 2:

        Input: s = "aabab", k = 3
        Output: 0
        Constraints:

        The input string consists of only lowercase English letters [a-z]
        0 ≤ k ≤ 26
         */
    }
    public int substringWithExactlyKDifferentchars(String s, int k)
    {
        if(s==null || s.length()<k)
            return 0;
        return substringWithExactlyKDifferentcharsHelper(s,k)-substringWithExactlyKDifferentcharsHelper(s,k-1);
    }

    public int substringWithExactlyKDifferentcharsHelper(String s, int k)
    {
        Map<Character, Integer> freqMap=new HashMap<>();
        int res=0,l=0,r=0;
        while(r<s.length())
        {
            char c=s.charAt(r);
            freqMap.put(c,freqMap.getOrDefault(c,0)+1);
            while(freqMap.size()>k)
            {
                char ch=s.charAt(l);
                freqMap.put(ch,freqMap.get(ch)-1);
                if(freqMap.get(ch)==0)
                    freqMap.remove(ch);
                l++;
            }
            res+=r-l+1;
            r++;
        }
        return res;
    }

    //Max of Min altitudes
    {
        /*
        Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and ending at [r-1, c-1]. The score of a path is the minimum value in that path. For example, the score of the path 8 → 4 → 5 → 9 is 4.

        Don't include the first or final entry. You can only move either down or right at any point in time.

        Example 1:

        Input:
        [[5, 1],
         [4, 5]]

        Output: 4
        Explanation:
        Possible paths:
        5 → 1 → 5 => min value is 1
        5 → 4 → 5 => min value is 4
        Return the max value among minimum values => max(4, 1) = 4.
        Example 2:

        Input:
        [[1, 2, 3]
         [4, 5, 1]]

        Output: 4
        Explanation:
        Possible paths:
        1-> 2 -> 3 -> 1
        1-> 2 -> 5 -> 1
        1-> 4 -> 5 -> 1
        So min of all the paths = [2, 2, 4]. Note that we don't include the first and final entry.
        Return the max of that, so 4.
         */
    }
    public int maxMinPaths(int[][] mat)
    {
        if(mat==null || mat.length==0 || mat[0].length==0)
            return 0;
        int[][]dp=new int[mat.length][mat[0].length];
        dp[0][0]=Integer.MAX_VALUE;

        for(int i=1;i<dp.length;i++)
            dp[i][0]=Math.min(dp[i-1][0],mat[i][0]);
        for(int i=1;i<dp[0].length;i++)
            dp[0][i]=Math.min(dp[0][i-1],mat[0][i]);

        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                if(i==dp.length-1 && j==dp[0].length-1)
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                else
                {
                    int val1=Math.min(dp[i-1][j],mat[i][j]);
                    int val2=Math.min(dp[i][j-1],mat[i][j]);
                    dp[i][j]=Math.max(val1,val2);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[dp.length-1][dp[0].length-1];
    }

    //Longest Palindromic Substring
    {
        /*
        Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

        Example 1:

        Input: "babad"
        Output: "bab"
        Note: "aba" is also a valid answer.
        Example 2:

        Input: "cbbd"
        Output: "bb"
         */
    }
    public String longestPalindrome(String s)
    {
        if(s==null || s.length()==0)
                return "";
        int len=0;
        int start=0, end=0;
        for(int i=0;i<s.length();i++)
        {
            int l1=lpsHelper(s,i,i);
            int l2=lpsHelper(s,i,i+1);
            int maxLen=Math.max(l1,l2);
            if(len<maxLen)
            {
                len=maxLen;
                start=i-(maxLen-1)/2;
                end=i+(maxLen/2);
            }
        }
        return s.substring(start,end+1);
    }
    public int lpsHelper(String s, int left, int right)
    {
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right))
        {
            left--;
            right++;
        }
        return right-left-1;
    }

    //Substring of Size K with K distinct characters
    {
        /*
        Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.

        Example 1:

        Input: s = "abcabc", k = 3
        Output: ["abc", "bca", "cab"]
        Example 2:

        Input: s = "abacab", k = 3
        Output: ["bac", "cab"]
        Example 3:

        Input: s = "awaglknagawunagwkwagl", k = 4
        Output: ["wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"]
        Explanation:
        Substrings in order are: "wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag", "wagl"
        "wagl" is repeated twice, but is included in the output once.
         */
    }

    public List<String> kSubstring(String s, int k)
    {
        if(s==null || s.length()<k)
            return new ArrayList<>();
        Set<Character> set=new HashSet<>();
        Set<String> result=new HashSet<>();
        int r=0,l=0;
        while(r<s.length())
        {
            if(set.contains(s.charAt(r)))
            {
                set.remove(s.charAt(l));
                l++;
            }
            if(!set.contains(s.charAt(r)))
            {
                set.add(s.charAt(r));
                r++;
            }
            if(set.size()==k)
            {
                result.add(s.substring(l,r));
                set.remove(s.charAt(l));
                l++;
            }
        }

        return new ArrayList<String>(result);
    }

    //Most Common Word
    {
        /*
        Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

        Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

        Example:

        Input:
        paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
        banned = ["hit"]
        Output: "ball"
        Explanation:
        "hit" occurs 3 times, but it is a banned word.
        "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
        Note that words in the paragraph are not case sensitive,
        that punctuation is ignored (even if adjacent to words, such as "ball,"),
        and that "hit" isn't the answer even though it occurs more because it is banned.


        Note:

        1 <= paragraph.length <= 1000.
        0 <= banned.length <= 100.
        1 <= banned[i].length <= 10.
        The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
        paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
        There are no hyphens or hyphenated words.
        Words only consist of letters, never apostrophes or other punctuation symbols.
         */
    }

    public String mostCommonWord(String paragraph, String[] banned)
    {
        Map<String,Integer> freqMap=new HashMap<>();
        Set<String> dict=new HashSet<>(Arrays.asList(banned));

        int maxFreq=0;
        String ans="";

        int i=0;
        StringBuilder sb=new StringBuilder();
        char[] c=paragraph.toCharArray();

        while(i<c.length)
        {
            if(i==c.length-1 || !Character.isLetter(c[i]))
            {
                if(i==c.length-1 && Character.isLetter(c[i]))
                    sb.append(Character.toLowerCase(c[i]));
                if(sb.length()>0 && !dict.contains(sb.toString()))
                {
                    freqMap.put(sb.toString(),freqMap.getOrDefault(sb.toString(),0)+1);
                    if(maxFreq<freqMap.get(sb.toString()))
                    {
                        maxFreq=freqMap.get(sb.toString());
                        ans=sb.toString();
                    }
                }
                sb=new StringBuilder();
            }
            else if(Character.isLetter(c[i]))
                sb.append(Character.toLowerCase(c[i]));
            i++;
        }
        return ans;
    }

    //K closest points to origin
    {
        /*
        We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
        (Here, the distance between two points on a plane is the Euclidean distance.)

        You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

        Example 1:

        Input: points = [[1,3],[-2,2]], K = 1
        Output: [[-2,2]]
        Explanation:
        The distance between (1, 3) and the origin is sqrt(10).
        The distance between (-2, 2) and the origin is sqrt(8).
        Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
        We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
        Example 2:

        Input: points = [[3,3],[5,-1],[-2,4]], K = 2
        Output: [[3,3],[-2,4]]
        (The answer [[-2,4],[3,3]] would also be accepted.)
         */
    }

    public int[][] kClosest(int[][] points, int K)
    {
        if(points==null || points.length==0)
            return new int[0][];
        PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2)
            {
                int sq1=(p1[0]*p1[0])+(p1[1]*p1[1]);
                int sq2=(p2[0]*p2[0])+(p2[1]*p2[1]);
                return sq2-sq1;
            }
        });

        for(int[] i:points)
        {
            pq.offer(i);
            if(pq.size()>K)
                pq.poll();
        }
        int[][] res=new int[pq.size()][2];
        for(int i=0;i<res.length;i++)
            res[i]=pq.poll();
        return res;
    }

    //Generate Parentheses
    {
        /*
        Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

        For example, given n = 3, a solution set is:

        [
          "((()))",
          "(()())",
          "(())()",
          "()(())",
          "()()()"
        ]
         */
    }

    public List<String> generateParenthesis(int n)
    {
        if(n==0)
            return new ArrayList<>();
        List<String> result=new ArrayList<>();
        generateParenthesisHelper(result,n,n,"");
        return result;
    }
    public void generateParenthesisHelper(List<String> result, int left, int right, String str)
    {
        if(left==0 && right==0)
        {
            result.add(str);
            return;
        }
        else if(left<0 || right<0 || right<left)
            return;

        generateParenthesisHelper(result,left-1,right,str+"(");
        generateParenthesisHelper(result,left,right-1,str+")");
    }

    //Min cost to connect all nodes
    {
        /*
        Given an undirected graph with n nodes labeled 1..n. Some of the nodes are already connected. The i-th edge connects nodes edges[i][0] and edges[i][1] together.
        Your task is to augment this set of edges with additional edges to connect all the nodes. Find the minimum cost to add new edges between the nodes such that all the nodes are accessible from each other.

        Input:

        n, an int representing the total number of nodes.
        edges, a list of integer pair representing the nodes already connected by an edge.
        newEdges, a list where each element is a triplet representing the pair of nodes between which an edge can be added and the cost of addition, respectively (e.g. [1, 2, 5] means to add an edge between node 1 and 2, the cost would be 5).
        Example 1:

        Input: n = 6, edges = [[1, 4], [4, 5], [2, 3]], newEdges = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
        Output: 7
        Explanation:
        There are 3 connected components [1, 4, 5], [2, 3] and [6].
        We can connect these components into a single component by connecting node 1 to node 2 and node 1 to node 6 at a minimum cost of 5 + 2 = 7.
         */
    }
    public int minCostToConnectAllNNodes(int n, int[][] edges, int[][] newEdges)
    {
        if(n==0 || newEdges==null || newEdges.length==0)
            return 0;
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] i:newEdges)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.putIfAbsent(i[1],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
            map.get(i[1]).add(new int[]{i[0],i[2]});
        }
        for(int[] i:edges)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.putIfAbsent(i[1],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],0});
            map.get(i[1]).add(new int[]{i[0],0});
        }
        PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] i, int[] j)
            {
                return i[1]-j[1];
            }
        });
        Set<Integer> visited=new HashSet<>();

        pq.offer(new int[]{1,0});
        int totalCost=0;
        while(!pq.isEmpty())
        {
            int[] curr=pq.poll();
            if(visited.contains(curr[0]))
                continue;
            totalCost+=curr[1];
            visited.add(curr[0]);
            for(int[] nxt:map.get(curr[0]))
            {
                if(visited.contains(nxt[0]))
                    continue;
                pq.offer(nxt);
            }
        }
        return totalCost;
    }

    //Min Cost to Repair Edges
    {
        /*
        There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been broken disconnecting the graph. Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.

        Input:

        n, an int representing the total number of nodes.
        edges, a list of integer pair representing the nodes connected by an edge.
        edgesToRepair, a list where each element is a triplet representing the pair of nodes between which an edge is currently broken and the cost of repearing that edge, respectively (e.g. [1, 2, 12] means to repear an edge between nodes 1 and 2, the cost would be 12).
        Example 1:

        Input: n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
        Output: 20
        Explanation:
        There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
        We can connect these components into a single component by repearing the edges between nodes 1 and 2, and nodes 1 and 5 at a minimum cost 12 + 8 = 20.
        Example 2:

        Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]], edgesToRepair = [[1, 6, 410], [2, 4, 800]]
        Output: 410
        Example 3:

        Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]], edgesToRepair = [[1, 5, 110], [2, 4, 84], [3, 4, 79]]
        Output: 79
         */
    }

    public int minCostToRepairEdges(int n, int[][] edges, int[][] edgesToRepair)
    {
        if(edgesToRepair==null || edgesToRepair.length==0)
            return 0;
        Set<String> brokenEdges=new HashSet<>();
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] i:edgesToRepair)
        {
            String key=i[0]+":"+i[1];
            brokenEdges.add(key);
            map.putIfAbsent(i[0],new ArrayList<>());
            map.putIfAbsent(i[1],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
            map.get(i[1]).add(new int[]{i[0],i[2]});
        }
        for(int[] i:edges)
        {
            String key=i[0]+":"+i[1];
            if(brokenEdges.contains(key))
                continue;
            map.putIfAbsent(i[0],new ArrayList<>());
            map.putIfAbsent(i[1],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],0});
            map.get(i[1]).add(new int[]{i[0],0});
        }

        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        pq.offer(new int[]{1,0});
        Set<Integer> visited=new HashSet<>();
        int cost=0;
        while(!pq.isEmpty())
        {
            int[] curr=pq.poll();
            if(visited.contains(curr[0]))
                continue;
            visited.add(curr[0]);
            cost+=curr[1];
            for(int[] nxt:map.get(curr[0]))
            {
                if(visited.contains(nxt[0]))
                    continue;
                pq.offer(nxt);
            }
        }
        return cost;
    }

    //SubTree With Maximum Average
    {
        /*
        Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
        A subtree of a tree is the node which have at least 1 child plus all its descendants. The average value of a subtree is the sum of its values, divided by the number of nodes.

        Example 1:

        Input:
                 20
               /   \
             12     18
          /  |  \   / \
        11   2   3 15  8

        Output: 18
        Explanation:
        There are 3 nodes which have children in this tree:
        12 => (11 + 2 + 3 + 12) / 4 = 7
        18 => (18 + 15 + 8) / 3 = 13.67
        20 => (12 + 11 + 2 + 3 + 18 + 15 + 8 + 20) / 8 = 11.125

        18 has the maximum average so output 18.
         */
    }
    class Node
    {
        int val;
        List<Node> children;
        public Node(int val)
        {
            this.val=val;
            children=new ArrayList<>();
        }
    }
    double maximumAverage=0;
    Node ans=null;
    public Node maximumAverageSubtree(Node root)
    {
        if(root==null)
            return null;
        maximumAverageSubtreeHelper(root);
        return ans;
    }

    public int[] maximumAverageSubtreeHelper(Node root)
    {
        if(root==null)
            return new int[]{0,0};
        int sum=root.val, count=1;
        for(Node child:root.children)
        {
            int[] arr=maximumAverageSubtreeHelper(child);
            sum+=arr[0];
            count+=arr[1];
        }
        double avg=(1.0*sum)/count;
        if(count>1 && avg>maximumAverage)
        {
            ans=root;
            maximumAverage=avg;
        }
        return new int[]{sum,count};
    }


    //SubTree With Maximum Average
    public void testMaximumAverageSubtree()
    {
        Node root=new Node(20);
        Node left=new Node(12);
        Node right=new Node(18);
        left.children=Arrays.asList(new Node(11),new Node(2),new Node(3));
        right.children=Arrays.asList(new Node(15),new Node(8));
        root.children=Arrays.asList(left,right);

        System.out.println(maximumAverageSubtree(root).val);
    }


    public int checkWinner(List<List<String>> codeList, List<String> shoppingCart)
    {
        if(codeList==null || codeList.size()==0 || shoppingCart==null || shoppingCart.size()==0)
            return 0;

        if(checkWinnerHelper(codeList,0,shoppingCart,0))
            return 1;
        return 0;
    }

    public boolean checkWinnerHelper(List<List<String>> code, int codeIdx, List<String> cart, int cartIdx)
    {
        int cartSize=cart.size()-cartIdx;
        int codeListSize=0;
        for(int i=codeIdx;i<code.size();i++)
            codeListSize+=code.get(i).size();

        if(codeListSize>cartSize)
            return false;

        if(codeIdx==code.size())
            return true;

        List<String> subList=code.get(codeIdx);
        for(int i=cartIdx;i<cart.size();i++)
        {
            if(!subList.get(0).equals("anything") && !subList.get(0).equals(cart.get(i)))
                continue;
            if(checkMatch(cart,i,subList,0) && checkWinnerHelper(code,codeIdx+1,cart,i))
                return true;
        }
        return false;
    }

    public boolean checkMatch(List<String> cart, int cartIdx, List<String> code, int codeIdx)
    {
        while(cartIdx<cart.size() && codeIdx<code.size())
        {
            if(!cart.get(cartIdx).equals(code.get(codeIdx)) && !code.get(codeIdx).equals("anything"))
                return false;
            codeIdx++;
            cartIdx++;
        }
        return codeIdx==code.size();
    }

    public static void main(String[] args)
    {
        //23280666889332
        AmazonOAQuestions obj=new AmazonOAQuestions();
        //Top K Frequently Mentioned Keywords driver
        /*
        List<String> keywords=new ArrayList<>(Arrays.asList("anacell", "betacellular", "cetracular", "deltacellular", "eurocell"));
        List<String> reviews=new ArrayList<>(Arrays.asList("I love anacell Best services; Best services provided by anacell","betacellular has great services","deltacellular provides much better services than betacellular","cetracular is worse than anacell","Betacellular is better than deltacellular."));
        System.out.println(obj.kFrequentlyMentionedKeywords(2,keywords,reviews));
        */


        //Zombies in a matrix driver
        /*
        List<List<Integer>> grid=new ArrayList<>();
        grid.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 1, 0, 1})));
        grid.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 0, 1, 0})));
        grid.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 1})));
        grid.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 0, 0, 0})));
        System.out.println(obj.minHours(grid));
        */

        //Articulation Points driver
        //System.out.println(obj.getArticulationPoints(new int[][]{{0,1},{0,2},{1,3},{2,3},{3,4},{2,5},{5,6}},7));

        //Product Suggestions driver
        /*
        String[] products={"mobile","mouse","moneypot","monitor","mousepad"};
        System.out.println(obj.suggestedProducts(products,"mouse"));
        */

        //Letter Logs Driver
        /*String[] logs={"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        logs=obj.reorderLogFiles(logs);
        for(String str:logs)
            System.out.println(str);
        */
        //Partition Label Driver
        //System.out.println(obj.partitionLabels("ababcbacadefegdehijhklij"));

        //Optimal Utilization Driver
        /*
        List<int[]> a=new ArrayList<int[]>();
        List<int[]> b=new ArrayList<int[]>();
        a.add(new int[]{1,3});
        a.add(new int[]{2,5});
        a.add(new int[]{3,7});
        a.add(new int[]{4,10});

        b.add(new int[]{1,2});
        b.add(new int[]{2,3});
        b.add(new int[]{3,4});
        b.add(new int[]{4,5});

        List<int[]> result=obj.getPairs(a,b,10);
        for(int[] i:result)
            System.out.println(i[0]+","+i[1]);
        */

        //Min Cost to Connect Ropes driver
        //System.out.println(obj.connectSticks(new int[]{20,4,8,2}));

        //Treasure Island 1 driver
        /*
        char[][] grid={{'O', 'O', 'O', 'O'},{'D', 'O', 'D', 'O'},{'O', 'O', 'O', 'O'},{'X', 'D', 'D', 'O'}};
        System.out.println(obj.treasureIsland1DFS(grid));
        */

        //Treasure Island 2 driver
        /*
        char[][] grid={{'S', 'O', 'O', 'S', 'S'},{'D', 'O', 'D', 'O', 'D'},{'O', 'O', 'O', 'O', 'X'},{'X', 'D', 'D', 'O', 'O'},{'X', 'D', 'D', 'D', 'O'}};
        System.out.println(obj.treasureIsland2BFS(grid));
        */

        //Find Pairs with given sum
        //System.out.println(obj.findPairs(Arrays.asList(20,50,40,25,30,10),90));

        //Search a 2D Matrix II driver
        /*
        int[][] matrix={{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(obj.searchMatrix(matrix,20));
        */

        //Favorite Genres driver
        /*
        Map<String,List<String>> userSongs=new HashMap<>();
        //userSongs.put("David",new ArrayList<>(Arrays.asList("song1", "song2", "song3", "song4", "song8")));
        //userSongs.put("Emma",new ArrayList<>(Arrays.asList("song5", "song6", "song7")));

        userSongs.put("David",new ArrayList<>(Arrays.asList("song1", "song2")));
        userSongs.put("Emma",new ArrayList<>(Arrays.asList("song3", "song4")));

        Map<String,List<String>> songGenres=new HashMap<>();
        //songGenres.put("Rock",new ArrayList<>(Arrays.asList("song1","song3")));
        //songGenres.put("Dubstep",new ArrayList<>(Arrays.asList("song7")));
        //songGenres.put("Techno",new ArrayList<>(Arrays.asList("song2","song4")));
        //songGenres.put("Pop",new ArrayList<>(Arrays.asList("song5","song6")));
        //songGenres.put("Jazz",new ArrayList<>(Arrays.asList("song8","song9")));

        Map<String,List<String>> result=obj.favoriteGenre(userSongs,songGenres);
        for(String key:result.keySet())
            System.out.println(key+" : "+result.get(key));
        */

        //Two Sum Unique Pairs driver
        /*
        System.out.println(obj.twoSumUniquePairs(new int[]{1,1,2,45,46,46},47));
        System.out.println(obj.twoSumUniquePairs(new int[]{1,1},2));
        System.out.println(obj.twoSumUniquePairs(new int[]{1,5,1,5},6));
        */

        //Susbtrings with exactly k different chars driver
        //System.out.println(obj.substringWithExactlyKDifferentchars("pqrs",2));

        //Max of Min altitudes drivers
        //System.out.println(obj.maxMinPaths(new int[][]{{6,7,8},{5,4,2},{8,7,6}}));

        //Longest Palindromic Substring driver
        //System.out.println(obj.longestPalindrome("cbbd"));

        //Substring of Size K with K distinct characters driver
        //System.out.println(obj.kSubstring("abacab",3));

        //K closest points to origin driver
        /*
        int[][] res=obj.kClosest(new int[][]{{3,3},{5,-1},{-2,4}},2);
        for(int[] i:res)
            System.out.println(i[0]+", "+i[1]);
        */


        //Generate Parentheses Driver
        //System.out.println(obj.generateParenthesis(3));


        //Min cost to connect all nodes driver
        //System.out.println(obj.minCostToConnectAllNNodes(6,new int[][]{{1,4},{4,5},{2,3}},new int[][]{{1,2,5},{1,3,10},{1,6,2},{5,6,5}}));

        //Min Cost to Repair Edges driver
        /*
        System.out.println(obj.minCostToRepairEdges(5,new int[][]{{1,2},{2,3},{3,4},{4,5},{1,5}},new int[][]{{1,2,12},{3,4,30},{1,5,8}}));

        System.out.println(obj.minCostToRepairEdges(6,new int[][]{{1,2},{2,3},{4,5},{3,5},{1,6},{2,4}},new int[][]{{1,6,410},{2,4,800}}));

        System.out.println(obj.minCostToRepairEdges(5,new int[][]{{1,2},{2,3},{4,5},{5,6},{1,5},{2,4},{3,4}},new int[][]{{1,5,110},{2,4,84},{3,4,79}}));
        */

        //SubTree With Maximum Average driver
        List<List<String>> codeList=new ArrayList<>();
        codeList.add(new ArrayList<>(Arrays.asList("orange")));
        codeList.add(new ArrayList<>(Arrays.asList("apple","apple")));
        codeList.add(new ArrayList<>(Arrays.asList("banana","orange","apple")));
        codeList.add(new ArrayList<>(Arrays.asList("banana")));

        List<String> cart=new ArrayList<>(Arrays.asList("orange","apple","apple","banana","pear","apple","apple","banana","orange","apple","banana"));

        System.out.println(obj.checkWinner(codeList,cart));

    }
}

