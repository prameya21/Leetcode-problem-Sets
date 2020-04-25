import java.lang.reflect.Array;
import java.util.*;

public class Uber_Onsite
{
    //Palindrome Permutation II
    {
        /*
        Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

        Example 1:

        Input: "aabb"
        Output: ["abba", "baab"]
        Example 2:

        Input: "abc"
        Output: []
         */
    }

    public List<String> generatePalindromes(String s)
    {
        List<String> res=new ArrayList<>();
        if(s==null || s.length()==0)
            return res;
        Map<Character,Integer> map=new HashMap<>();
        for(char c:s.toCharArray())
            map.put(c,map.getOrDefault(c,0)+1);
        int oddCount=0;
        for(char c:map.keySet())
            if(map.get(c)%2!=0)
                oddCount++;

        if(oddCount>1)
            return res;
        Character odd=null;
        for(char c:map.keySet())
        {
            if(map.get(c)%2!=0)
                odd=c;
            map.put(c,map.get(c)/2);
        }
        generatePalindromes(map,odd,res,"",s.length()/2);
        return res;
    }
    public void generatePalindromes(Map<Character,Integer> map, Character odd, List<String> res, String s, int len)
    {
        if(s.length()==len)
        {
            if(odd==null)
            {
                StringBuilder sb=new StringBuilder(s);
                s+=sb.reverse().toString();
                res.add(s);
                return;
            }
            else
            {
                StringBuilder sb=new StringBuilder(s);
                s+=odd+sb.reverse().toString();
                res.add(s);
                return;
            }
        }
        else
        {
            for(char c:map.keySet())
            {
                if(map.get(c)!=0)
                {
                    map.put(c,map.get(c)-1);
                    generatePalindromes(map,odd,res,s+c,len);
                    map.put(c,map.get(c)+1);
                }
            }
        }
    }


    //Cherry Pickup
    {
        /*
        In a N x N grid representing a field of cherries, each cell is one of three possible integers.

        0 means the cell is empty, so you can pass through;
        1 means the cell contains a cherry, that you can pick up and pass through;
        -1 means the cell contains a thorn that blocks your way.


        Your task is to collect maximum number of cherries possible by following the rules below:

        Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
        After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
        When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
        If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.

        Example 1:

        Input: grid =
        [[0, 1, -1],
         [1, 0, -1],
         [1, 1,  1]]
        Output: 5
        Explanation:
        The player started at (0, 0) and went down, down, right right to reach (2, 2).
        4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
        Then, the player went left, up, up, left to return home, picking up one more cherry.
        The total number of cherries picked up is 5, and this is the maximum possible.


        Note:

        grid is an N by N 2D array, with 1 <= N <= 50.
        Each grid[i][j] is an integer in the set {-1, 0, 1}.
        It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
         */
    }
    public int cherryPickup(int[][] grid)
    {
        int N=grid.length;
        //return Math.max(0,cherryPickup2(grid,grid.length,0,0,0,0,new Integer[N][N][N]));
        return cherryPickup(grid,0,0,grid.length-1,grid.length-1,new Integer[N][N][N]);
        //return Math.max(0,cherryPickup(grid,grid.length,0,0,N-1,N-1));
    }

    public int cherryPickup(int[][] grid, int n, int r1, int c1, int r2, int c2)
    {
        if(r1>=n || r2<0 || c1>=n || c2<0 || grid[r1][c1]==-1 || grid[r2][c2]==-1)
            return Integer.MIN_VALUE;
        if(r1==n-1 && c1==n-1)
            return grid[r1][c1];
        if(r2==0 && c2==0)
            return grid[r2][c2];
        int cherries=0;

        if(r1==r2 && c1==c2)
            cherries=grid[r1][c1];
        else
            cherries=grid[r1][c1]+grid[r2][c2];

        int val1=grid[r1][c1],val2=grid[r2][c2];
        grid[r1][c1]=0;
        grid[r2][c2]=0;
        cherries+=Math.max(Math.max(cherryPickup(grid,n,r1+1,c1,r2-1,c2),cherryPickup(grid,n,r1,c1+1,r2,c2-1)),Math.max(cherryPickup(grid,n,r1+1,c1,r2,c2-1),cherryPickup(grid,n,r1,c1+1,r2-1,c2)));
        grid[r1][c1]=val1;
        grid[r2][c2]=val2;
        return cherries;
    }
    public int cherryPickup(int[][] grid, int r1, int c1, int r2, int c2, Integer[][][][] memo)
    {
        if(r1>=grid.length || c1>=grid.length || r2<0 || c2<0 || grid[r1][c1]==-1 || grid[r2][c2]==-1)
            return Integer.MIN_VALUE;
        if(r1==grid.length-1 && c1==grid.length-1)
            return grid[r1][c1];
        if(r2==0 && c2==0)
            return grid[r2][c2];
        if(memo[r1][c1][r2][c2]!=null)
            return memo[r1][c1][r2][c2];
        int cherries=0;

        if(r1==r2 && c1==c2)
            cherries=grid[r1][c1];
        else
            cherries=grid[r1][c1]+grid[r2][c2];

        int t1=grid[r1][c1];
        int t2=grid[r2][c2];
        grid[r1][c1]=0;
        grid[r2][c2]=0;
        cherries+=Math.max(Math.max(cherryPickup(grid,r1+1,c1,r2-1,c2,memo),cherryPickup(grid,r1,c1+1,r2,c2-1,memo)),Math.max(cherryPickup(grid,r1+1,c1,r2,c2-1,memo),cherryPickup(grid,r1,c1+1,r2-1,c2,memo)));
        grid[r1][c1]=t1;
        grid[r2][c2]=t2;
        memo[r1][c1][r2][c2]=cherries;
        return cherries;
    }
    public int cherryPickup(int[][] grid, int r1, int c1, int r2, int c2, Integer[][][] memo)
    {
        if(r1>=grid.length || c1>=grid.length || r2<0 || c2<0 || grid[r1][c1]==-1 || grid[r2][c2]==-1)
            return Integer.MIN_VALUE;
        if(r1==grid.length-1 && c1==grid.length-1)
            return grid[r1][c1];
        if(r2==0 && c2==0)
            return grid[r2][c2];
        if(memo[r1][c1][r2]!=null)
            return memo[r1][c1][r2];
        int cherries=0;

        if(r1==r2 && c1==c2)
            cherries=grid[r1][c1];
        else
            cherries=grid[r1][c1]+grid[r2][c2];

        int t1=grid[r1][c1];
        int t2=grid[r2][c2];
        grid[r1][c1]=0;
        grid[r2][c2]=0;
        cherries+=Math.max(Math.max(cherryPickup(grid,r1+1,c1,r2-1,c2,memo),cherryPickup(grid,r1,c1+1,r2,c2-1,memo)),Math.max(cherryPickup(grid,r1+1,c1,r2,c2-1,memo),cherryPickup(grid,r1,c1+1,r2-1,c2,memo)));
        grid[r1][c1]=t1;
        grid[r2][c2]=t2;
        memo[r1][c1][r2]=cherries;
        return cherries;
    }

    public int cherryPickup2(int[][] grid, int n, int r1, int c1, int r2, int c2,Integer[][][] memo)
    {
        if(r1>=n || r2>=n || c1>=n || c2>=n || grid[r1][c1]==-1 || grid[r2][c2]==-1)
            return Integer.MIN_VALUE;
        if(memo[r1][c1][r2]!=null)
            return memo[r1][c1][r2];
        if(r1==n-1 && c1==n-1)
            return grid[r1][c1];
        if(r2==n-1 && c2==n-1)
            return grid[r2][c2];

        int cherries=0;
        if(r1==r2 && c1==c2)
            cherries+=grid[r2][c2];
        else
            cherries+=grid[r1][c1]+grid[r2][c2];
        cherries+=Math.max(Math.max(cherryPickup2(grid,n,r1+1,c1,r2+1,c2,memo),cherryPickup2(grid,n,r1,c1+1,r2,c2+1,memo)),Math.max(cherryPickup2(grid,n,r1+1,c1,r2,c2+1,memo),cherryPickup2(grid,n,r1,c1+1,r2+1,c2,memo)));
        memo[r1][c1][r2]=cherries;
        return cherries;
    }

    //Number Of Islands2
    {
        /*
        A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

        Example:

        Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
        Output: [1,1,2,3]
        Explanation:

        Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

        0 0 0
        0 0 0
        0 0 0
        Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

        1 0 0
        0 0 0   Number of islands = 1
        0 0 0
        Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

        1 1 0
        0 0 0   Number of islands = 1
        0 0 0
        Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

        1 1 0
        0 0 1   Number of islands = 2
        0 0 0
        Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

        1 1 0
        0 0 1   Number of islands = 3
        0 1 0
         */
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions)
    {
        List<Integer> res=new ArrayList<>();
        if(m<0 || n<0)
            return res;
        int[] roots=new int[m*n];
        Arrays.fill(roots,-1);
        int count=0;
        int[][] dirs=new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        for(int[] p:positions)
        {
            int root=n*p[0]+p[1];
            if(root<0 || root>=roots.length)
                continue;
            if(roots[root]!=-1)
            {
                res.add(count);
                continue;
            }
            roots[root]=root;
            count++;
            for(int[] d:dirs)
            {
                int x=p[0]+d[0];
                int y=p[1]+d[1];
                int newRoot=n*x+y;
                if(x<0 || x>=m || y<0 || y>=n || roots[newRoot]==-1)
                    continue;
                int parent=findIsland(roots,newRoot);
                if(root!=parent)
                {
                    roots[root]=parent;
                    root=parent;
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }

    public int findIsland(int[] roots, int newRoot)
    {
        while(roots[newRoot]!=newRoot)
        {
            roots[newRoot]=roots[roots[newRoot]];
            newRoot=roots[newRoot];
        }
        return newRoot;

    }

    //Set Intersection Size at least 2
    {
        /*
        An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.

        Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has size at least 2.

        Example 1:
        Input: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
        Output: 3
        Explanation:
        Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
        Also, there isn't a smaller size set that fulfills the above condition.
        Thus, we output the size of this set, which is 3.
        Example 2:
        Input: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
        Output: 5
        Explanation:
        An example of a minimum sized set is {1, 2, 3, 4, 5}.
        Note:

        intervals will have length in range [1, 3000].
        intervals[i] will have length 2, representing some integer interval.
        intervals[i][j] will be an integer in [0, 10^8].
         */
    }

    public int intersectionSizeTwo(int[][] intervals)
    {
        if(intervals==null || intervals.length==0)
            return 0;
        int res=0;
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] a, int[] b)
            {
                int cmp=Integer.compare(a[1],b[1]);
                if(cmp==0)
                    return Integer.compare(b[0],a[0]);
                return cmp;
            }
        });
        int left=intervals[0][1]-1;
        int right=intervals[0][1];
        res+=2;
        for(int i=1;i<intervals.length;i++)
        {
            int[] curr=intervals[i];
            if(left<curr[0] && right>=curr[0])
            {
                res++;
                left=right;
                right=curr[1];
            }
            else if(curr[0]>right)
            {
                res+=2;
                right=curr[1];
                left=right-1;

            }
        }
        return res;
    }

    //Construct Quad Tree
    {
        /*
        Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.

        Return the root of the Quad-Tree representing the grid.

        Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.

        A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

        val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
        isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
        class Node {
            public boolean val;
            public boolean isLeaf;
            public Node topLeft;
            public Node topRight;
            public Node bottomLeft;
            public Node bottomRight;
        }
         */
    }
    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    public Node construct(int[][] grid)
    {
        return helper(grid,0,0,grid.length);
    }
    public Node helper(int[][] grid, int i, int j, int len)
    {
        if(len==1)
            return new Node(grid[i][j]==1,true,null,null,null,null);
        Node res=new Node();
        Node topLeft=helper(grid,i,j,len/2);
        Node topRight=helper(grid, i,j+len/2,len/2);
        Node bottomLeft=helper(grid,i+len/2,j,len/2);
        Node bottomRight=helper(grid,i+len/2,j+len/2,len/2);
        if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf && topLeft.val==topRight.val && topRight.val==bottomRight.val && bottomRight.val==bottomLeft.val && bottomLeft.val==topLeft.val)
        {
            res.isLeaf=true;
            res.val=topLeft.val;
        }
        else
        {
            res.topLeft=topLeft;
            res.topRight=topRight;
            res.bottomLeft=bottomLeft;
            res.bottomRight=bottomRight;
        }
        return res;
    }

    //Minesweeper
    {
        /*
        Let's play the minesweeper game (Wikipedia, online game)!

        You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines,
        digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

        Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

        If a mine ('M') is revealed, then the game is over - change it to 'X'.
        If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
        If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
        Return the board when no more squares will be revealed.


        Example 1:

        Input:

        [['E', 'E', 'E', 'E', 'E'],
         ['E', 'E', 'M', 'E', 'E'],
         ['E', 'E', 'E', 'E', 'E'],
         ['E', 'E', 'E', 'E', 'E']]

        Click : [3,0]

        Output:

        [['B', '1', 'E', '1', 'B'],
         ['B', '1', 'M', '1', 'B'],
         ['B', '1', '1', '1', 'B'],
         ['B', 'B', 'B', 'B', 'B']]
         */
    }
    public char[][] updateBoard(char[][] board,int[] click)
    {
        int r=click[0];
        int c=click[1];
        if(board[r][c]=='M')
            board[r][c]='X';
        else
            dfs(board,r,c);
        return board;
    }
    public void dfs(char[][] board, int r, int c)
    {
        if(r<0 || c<0 || r>=board.length || c>=board[0].length || board[r][c]!='E')
            return;
        int count=countMines(board,r,c);
        if(count>0)
        {
            board[r][c]=(char)(count+'0');
            return;
        }
        else
        {
            board[r][c]='B';
            dfs(board,r+1,c);
            dfs(board,r-1,c);
            dfs(board,r,c+1);
            dfs(board,r,c-1);
            dfs(board,r+1,c+1);
            dfs(board,r+1,c-1);
            dfs(board,r-1,c+1);
            dfs(board,r-1,c-1);
        }
    }

    public int countMines(char[][] board, int r, int c)
    {
        int ctr=0;
        for(int i=r-1;i<=r+1;i++)
        {
            for(int j=c-1;j<=c+1;j++)
            {
                if(i<0 || i>=board.length || j<0 || j>=board[0].length)
                    continue;
                else if(board[i][j]=='M')
                    ctr++;
            }
        }
        return ctr;
    }

    //Stamping The Sequence
    {
        /*
        You want to form a target string of lowercase letters.

        At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.

        On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.  You can make up to 10 * target.length turns.

        For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)

        If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  If the sequence is not possible to stamp, return an empty array.

        For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".

        Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.
         */
    }

    public int[] movesToStamp(String stamp, String target)
    {
        if(stamp.length()>target.length())
            return new int[0];
        char[] s=stamp.toCharArray();
        char[] t=target.toCharArray();
        int stars=0;
        boolean[] visited=new boolean[t.length];
        List<Integer> res=new ArrayList<>();
        while(stars<t.length)
        {
            boolean flag=false;
            for(int i=0;i<=t.length-s.length;i++)
            {
                if(!visited[i] && canReplace(i,s,t))
                {
                    stars+=doReplace(i,s,t);
                    flag=true;
                    visited[i]=true;
                    res.add(i);
                    if(stars==t.length)
                        break;
                }
            }
            if(!flag)
                return new int[0];
        }
        System.out.println(res);
        int[] ret=new int[res.size()];
        int j=0;
        for(int i=res.size()-1;i>=0;i--)
            ret[j++]=res.get(i);
        return ret;
    }
    public boolean canReplace(int i, char[] s, char[] t)
    {
        int ctr=0;
        for(int j=0;j<s.length;j++)
        {
            if(s[j]==t[i] || t[i]=='*')
                ctr++;
            i++;
        }
        return ctr==s.length;
    }
    public int doReplace(int i, char[] s, char[] t)
    {
        int j=0;
        int ctr=0;
        while(j<s.length)
        {
            if(t[i+j]!='*')
            {
                t[i+j]='*';
                ctr++;
            }
            j++;
        }
        return ctr;
    }

    //Word Pattern 2
    {
        /*
        Given a pattern and a string str, find if str follows the same pattern.

        Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

        Example 1:

        Input: pattern = "abab", str = "redblueredblue"
        Output: true
        Example 2:

        Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
        Output: true
        Example 3:

        Input: pattern = "aabb", str = "xyzabcxzyabc"
        Output: false
        Notes:
        You may assume both pattern and str contains only lowercase letters.
         */
    }
    public boolean wordPatternMatch(String pattern, String str)
    {
        Map<Character,String> map=new HashMap<>();
        Set<String> set=new HashSet<>();
        return helper(str,pattern,map,set);
    }
    public boolean helper(String str, String pattern, Map<Character,String> map, Set<String> set)
    {
        if(pattern.length()==0)
            return str.length()==0;
        else if(map.containsKey(pattern.charAt(0)))
        {
            String value=map.get(pattern.charAt(0));
            if(str.length()<value.length() || !str.substring(0,value.length()).equals(value))
                return false;
            if(helper(str.substring(value.length()),pattern.substring(1),map,set))
                return true;
        }
        else
        {
            for(int i=1;i<=str.length();i++)
            {
                String value=str.substring(0,i);
                if(set.contains(value))
                    continue;
                set.add(value);
                map.put(pattern.charAt(0),value);
                if(helper(str.substring(i),pattern.substring(1),map,set))
                    return true;
                set.remove(value);
                map.remove(pattern.charAt(0));
            }
        }
        return false;
    }

    //Reconstruct Itenary
    {
        /*
        Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

        Note:

        If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
        All airports are represented by three capital letters (IATA code).
        You may assume all tickets form at least one valid itinerary.
        Example 1:

        Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
        Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
        Example 2:

        Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
        Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                     But it is larger in lexical order.
         */
    }
    public List<String> findItinerary(List<List<String>> tickets)
    {
        if(tickets==null || tickets.size()==0)
            return new ArrayList<>();
        Map<String,PriorityQueue<String>> map=new HashMap<>();
        for(List<String> str:tickets)
        {
            map.putIfAbsent(str.get(0),new PriorityQueue<>());
            map.get(str.get(0)).offer(str.get(1));
        }
        List<String> res=new ArrayList<>();
        dfs(res,map,"JFK");
        return res;
    }
    public void dfs(List<String> res, Map<String,PriorityQueue<String>> map, String s)
    {
        while(map.containsKey(s) && !map.get(s).isEmpty())
            dfs(res,map,map.get(s).poll());
        res.add(0,s);
    }

    //Print Binary Tree
    {
        /*
        Print a binary tree in an m*n 2D string array following these rules:

        The row number m should be equal to the height of the given binary tree.
        The column number n should always be an odd number.
        The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
        The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part).
        You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size.
        Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree.
        However, if two subtrees are none, then you don't need to leave space for both of them.

        Each unused space should contain an empty string "".
        Print the subtrees following the same rules.
        Example 1:
        Input:
             1
            /
           2
        Output:
        [["", "1", ""],
         ["2", "", ""]]
         */
    }
    public List<List<String>> printTree(TreeNode root)
    {
        if(root==null)
            return new ArrayList<>();
        int h=getHeight(root);
        int w=(1<<h)-1;
        String[][] res=new String[h][w];
        for(String[] s:res)
            Arrays.fill(s,"");
        fill(res,root,0,0,w);
        List<List<String>> ret=new ArrayList<>();
        for(String[] s:res)
            ret.add(Arrays.asList(s));
        return ret;
    }
    public void fill(String[][] res,TreeNode root, int i, int l, int r)
    {
        if(root==null)
            return;
        int mid=l+(r-l)/2;
        res[i][mid]=""+root.val;
        fill(res,root.left,i+1,l,mid);
        fill(res,root.right,i+1,mid+1,r);
    }
    public int getHeight(TreeNode root)
    {
        if(root==null)
            return 0;
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }

    //Optimal Account Balancing
    {
        /*
        A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride.
        We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID),
        the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

        Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

        Note:

        A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
        Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
        Example 1:

        Input:
        [[0,1,10], [2,0,5]]

        Output:
        2

        Explanation:
        Person #0 gave person #1 $10.
        Person #2 gave person #0 $5.

        Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
        Example 2:

        Input:
        [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

        Output:
        1

        Explanation:
        Person #0 gave person #1 $10.
        Person #1 gave person #0 $1.
        Person #1 gave person #2 $5.
        Person #2 gave person #0 $5.

        Therefore, person #1 only need to give person #0 $4, and all debt is settled.
         */
    }
    public int minTransfers(int[][] transactions)
    {
        if(transactions==null || transactions.length==0)
            return 0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int[] t:transactions)
        {
            map.put(t[0],map.getOrDefault(t[0],0)+t[2]);
            map.put(t[1],map.getOrDefault(t[1],0)-t[2]);
        }
        int[] debt=new int[map.size()];
        int i=0;
        for(int val:map.values())
            debt[i++]=val;
        return settle(debt,0);
    }

    public int settle(int[] debt, int id)
    {
        while(id<debt.length && debt[id]==0)
            id++;
        if(id==debt.length)
            return 0;
        int min=Integer.MAX_VALUE;
        for(int i=id+1;i<debt.length;i++)
        {
            if(debt[i]<0 || debt[id]<0)
            {
                debt[i]+=debt[id];
                min=Math.min(min,settle(debt,id+1)+1);
                debt[i]-=debt[id];
            }
        }
        return min;
    }

    //Parallel Courses
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
         */
    }
    public int minimumSemesters(int N, int[][] relations)
    {
        if(relations==null || relations.length==0)
            return 0;
        int[] inDegrees=new int[N+1];
        Map<Integer,List<Integer>> map=new HashMap<>();
        for(int[] i:relations)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.get(i[0]).add(i[1]);
            inDegrees[i[1]]++;
        }
        Queue<Integer> q=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();
        for(int i=1;i<inDegrees.length;i++)
            if(inDegrees[i]==0)
                q.offer(i);

        int ret=0;
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
            ret++;
        }
        return visited.size()==N?ret:-1;
    }

    //Basic Calculator 3
    {
        /*
        Implement a basic calculator to evaluate a simple expression string.

        The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

        The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

        You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

        Some examples:

        "1 + 1" = 2
        " 6-4 / 2 " = 4
        "2*(5+5*2)/3+(6/2+8)" = 21
        "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
         */
    }
    public int calculate3(String s)
    {
        if(s==null || s.length()==0)
            return 0;
        Stack<Integer> st=new Stack<>();
        int nums=0;
        char sign='+';
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(c=='(')
            {
                int j=i,cnt=0;
                while(j<s.length())
                {
                    if(s.charAt(j)=='(')
                        cnt++;
                    if(s.charAt(j)==')')
                        cnt--;
                    if(cnt==0)
                        break;
                }
                nums+=calculate3(s.substring(i+1,j));
                i=j;
            }
            if(Character.isLetter(c))
                nums=nums*10+(c-'0');
            if(i==s.length()-1 || c=='+' || c=='-' || c=='*' || c=='/')
            {
                if(sign=='+')
                    st.push(nums);
                if(sign=='-')
                    st.push(-nums);
                if(sign=='*')
                    st.push(st.pop()*nums);
                if(sign=='/')
                    st.push(st.pop()/nums);
                sign=c;
                nums=0;
            }
        }
        int res=0;
        while(!st.isEmpty())
            res+=st.pop();
        return res;
    }

    //Palindrome Partitioning 3
    {
        /*
        You are given a string s containing lowercase letters and an integer k. You need to :

        First, change some characters of s to other lowercase English letters.
        Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
        Return the minimal number of characters that you need to change to divide the string.



        Example 1:

        Input: s = "abc", k = 2
        Output: 1
        Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
        Example 2:

        Input: s = "aabbc", k = 3
        Output: 0
        Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
        Example 3:

        Input: s = "leetcode", k = 8
        Output: 0
         */
    }
    public int palindromePartition(String s, int k)
    {
        if(k==s.length())
            return 0;
        int len=s.length();
        int[][] cache=new int[len][len];
        for(int[] i:cache)
            Arrays.fill(i,-1);
        Map<String,Integer> memo=new HashMap<>();
        return dfs(0,s,k,memo,cache);
    }
    public int dfs(int start, String s, int k, Map<String,Integer> memo, int[][] cache)
    {
        String key=start+"#"+k;
        if(memo.containsKey(key))
            return memo.get(key);
        if(s.length()-start==k)
            return 0;
        if(k==1)
            return cost(s,start,s.length()-1,cache);
        int min=Integer.MAX_VALUE;
        for(int i=start+1;i<s.length()-k+2;i++)
            min=Math.min(min,dfs(i,s,k-1,memo,cache)+cost(s,start,i-1,cache));
        memo.put(key,min);
        return min;
    }
    public int cost(String s, int left, int right, int[][] cache)
    {
        if(cache[left][right]!=-1)
            return cache[left][right];
        int diff=0,l=left,r=right;
        while(l<=r)
            if(s.charAt(l++)!=s.charAt(r--))
                diff++;
        cache[left][right]=diff;
        return diff;
    }
    public int palindromePartition2(String s, int k)
    {
        if(s==null || s.length()==0)
            return 0;
        if(k==s.length())
            return s.length();
        int[][] cache=new int[s.length()][s.length()];
        int[][] dp=new int[k][s.length()];
        for(int[] i:cache)
            Arrays.fill(i,-1);
        for(int i=0;i<k;i++)
        {
            for(int j=i;j<s.length();j++)
            {
                if(i==0)
                    dp[i][j]=cost(s,i,j,cache);
                else
                {
                    int min=Integer.MAX_VALUE;
                    for(int p=i;p<=j;p++)
                        min=Math.min(min,dp[i-1][p-1]+cost(s,p,j,cache));
                    dp[i][j]=min;
                }
            }
        }
        return dp[k-1][s.length()-1];
    }

    //Reaching Points
    {
        /*
        A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).

        Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

        Examples:
        Input: sx = 1, sy = 1, tx = 3, ty = 5
        Output: True
        Explanation:
        One series of moves that transforms the starting point to the target is:
        (1, 1) -> (1, 2)
        (1, 2) -> (3, 2)
        (3, 2) -> (3, 5)

        Input: sx = 1, sy = 1, tx = 2, ty = 2
        Output: False

        Input: sx = 1, sy = 1, tx = 1, ty = 1
        Output: True
         */
    }
    public boolean reachingPoints(int sx, int sy, int tx, int ty)
    {
        while(tx>=sx && ty>=sy)
        {
            if(tx==sx)
                break;
            if(tx>ty)
            {
                if(ty>sy)
                    tx%=ty;
                else
                    return (tx-sx)%ty==0;
            }
            else
            {
                if(tx>sx)
                    ty%=tx;
                else
                    return (ty-sy)%tx==0;
            }
        }
        return tx==sx && ty==sy;
    }

    //Bomb Enemy
    {
        /*
        Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
        The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
        Note: You can only put the bomb at an empty cell.

        Example:

        Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
        Output: 3
        Explanation: For the given grid,

        0 E 0 0
        E 0 W E
        0 E 0 0

        Placing a bomb at (1,1) kills 3 enemies.
         */
    }

    public int maxKilledEnemies(char[][] grid)
    {
        if(grid==null || grid.length==0)
            return 0;
        int[] col=new int[grid[0].length];
        int max=0,row=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='W')
                    continue;
                if(j==0 || grid[i][j-1]=='W')
                    row=computeRow(grid,i,j);
                if(i==0 || grid[i-1][j]=='W')
                    col[j]=computeCol(grid,i,j);
                if(grid[i][j]=='0')
                    max=Math.max(max,row+col[j]);
            }
        }
        return max;
    }
    public int computeRow(char[][] grid, int i, int j)
    {
        int ctr=0;
        while(j<grid[0].length && grid[i][j]!='W')
        {
            if(grid[i][j]=='E')
                ctr++;
            j++;
        }
        return ctr;
    }
    public int computeCol(char[][] grid, int i, int j)
    {
        int ctr=0;
        while(i<grid.length && grid[i][j]!='W')
        {
            if(grid[i][j]=='E')
                ctr++;
            i++;
        }
        return ctr;
    }

    //Connecting cities with minimum cost
    {
        /*
        There are N cities numbered from 1 to N.

        You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

        Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.



        Example 1:



        Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
        Output: 6
        Explanation:
        Choosing any 2 edges will connect all cities so we choose the minimum 2.
        Example 2:



        Input: N = 4, connections = [[1,2,3],[3,4,4]]
        Output: -1
        Explanation:
        There is no way to connect all cities even if all edges are used.
         */
    }
    public int minimumCost(int N, int[][] connections)
    {
        if(N==1)
            return 1;
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] i:connections)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.putIfAbsent(i[1],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
            map.get(i[1]).add(new int[]{i[0],i[2]});
        }

        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b)
            {
                return Integer.compare(a[1],b[1]);
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
            if(map.containsKey(curr[0]))
            {
                for(int[] conn:map.get(curr[0]))
                {
                    if(visited.contains(conn[0]))
                        continue;
                    pq.offer(conn);
                }
            }
        }
        return visited.size()==N?cost:-1;
    }

    //First and Last position in a sorted Array
    {
        /*
        Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

        Your algorithm's runtime complexity must be in the order of O(log n).

        If the target is not found in the array, return [-1, -1].

        Example 1:

        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]
        Example 2:

        Input: nums = [5,7,7,8,8,10], target = 6
        Output: [-1,-1]
         */
    }
    public int[] searchRange(int[] nums, int target)
    {
        if(nums==null || nums.length==0)
            return new int[]{-1,-1};
        int lo=search(nums,target);
        if(lo>=nums.length || nums[lo]!=target)
            return new int[]{-1,-1};
        return new int[]{lo,search(nums,target+1)-1};
    }
    public int search(int[] nums, int target)
    {
        int lo=0,hi=nums.length;
        while(lo<hi)
        {
            int mid=lo+(hi-lo)/2;
            if(nums[mid]>=target)
               hi=mid;
            else
                lo=mid+1;
        }
        return lo;
    }

    //Basic Calculator 2
    {
        /*
        Implement a basic calculator to evaluate a simple expression string.

        The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

        Example 1:

        Input: "3+2*2"
        Output: 7
        Example 2:

        Input: " 3/2 "
        Output: 1
        Example 3:

        Input: " 3+5 / 2 "
        Output: 5
         */
    }
    public int calculate2(String s)
    {
        if(s==null || s.length()==0)
            return 0;
        int nums=0;
        char sign='+';
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(Character.isDigit(c))
                nums=nums*10+(c-'0');
            if(c=='+' || c=='-' || c=='*' || c=='/' || i==s.length()-1)
            {
                if(sign=='+')
                    st.push(nums);
                else if(sign=='-')
                    st.push(-nums);
                else if(sign=='*')
                    st.push(st.pop()*nums);
                else if(sign=='/')
                    st.push(st.pop()/nums);
                sign=c;
                nums=0;
            }
        }
        int res=0;
        while(!st.isEmpty())
            res+=st.pop();
        return res;
    }

    //Design Snake Game
    {
        /*
        Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

        The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

        You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

        Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

        When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

        Example:

        Given width = 3, height = 2, and food = [[1,2],[0,1]].

        Snake snake = new Snake(width, height, food);

        Initially the snake appears at position (0,0) and the food at (1,2).

        |S| | |
        | | |F|

        snake.move("R"); -> Returns 0

        | |S| |
        | | |F|

        snake.move("D"); -> Returns 0

        | | | |
        | |S|F|

        snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

        | |F| |
        | |S|S|

        snake.move("U"); -> Returns 1

        | |F|S|
        | | |S|

        snake.move("L"); -> Returns 2 (Snake eats the second food)

        | |S|S|
        | | |S|

        snake.move("U"); -> Returns -1 (Game over because snake collides with border)
         */
    }
    private class SnakeGame
    {
        int width,height,foodIndex;
        int[][] food;
        LinkedList<int[]> snake;
        public SnakeGame(int w, int h, int[][] f)
        {
            width=w;
            height=h;
            food=f;
            snake=new LinkedList<>();
            foodIndex=0;
            snake.add(new int[]{0,0});
        }
        public int move(String direction)
        {
            int x=snake.get(0)[0];
            int y=snake.get(0)[1];
            if(direction.equals("U"))
                x-=1;
            else if(direction.equals("D"))
                x+=1;
            else if(direction.equals("L"))
                y-=1;
            else if(direction.equals("R"))
                y+=1;

            if(x<0 || x>=height || y<0 || y>=width)
                return -1;
            int[] tail=snake.get(snake.size()-1);
            snake.remove(snake.size()-1);
            if(collision(x,y,snake))
                return -1;
            snake.add(0,new int[]{x,y});
            if(food.length>foodIndex && x==food[foodIndex][0] && y==food[foodIndex][1])
            {
                snake.add(tail);
                foodIndex++;
            }
            return foodIndex;
        }
        public boolean collision(int x, int y, LinkedList<int[]> snake)
        {
            for(int[] i:snake)
                if(i[0]==x && i[1]==y)
                    return true;
            return false;
        }
    }

    //Maximal Square
    {
        /*
        Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

        Example:

        Input:

        1 0 1 0 0
        1 0 1 1 1
        1 1 1 1 1
        1 0 0 1 0

        Output: 4
         */
    }

    public int maximalSquare(char[][] matrix)
    {
        if(matrix==null || matrix.length==0)
            return 0;
        int[][] dp=new int[matrix.length][matrix[0].length];
        int max=0;
        for(int i=1;i<matrix.length;i++)
        {
            for(int j=1;j<matrix[0].length;j++)
            {
                if(matrix[i-1][j-1]=='1')
                {
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                    max=Math.max(max,dp[i][j]);
                }
            }
        }
        return max*max;
    }

    //Open The Lock
    {
        /*
        You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
        The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

        The lock initially starts at '0000', a string representing the state of the 4 wheels.

        You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

        Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

        Example 1:
        Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
        Output: 6
        Explanation:
        A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
        Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
        because the wheels of the lock become stuck after the display becomes the dead end "0102".
        Example 2:
        Input: deadends = ["8888"], target = "0009"
        Output: 1
        Explanation:
        We can turn the last wheel in reverse to move from "0000" -> "0009".
        Example 3:
        Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
        Output: -1
        Explanation:
        We can't reach the target without getting stuck.
        Example 4:
        Input: deadends = ["0000"], target = "8888"
        Output: -1
         */
    }

    public int openLock(String[] deadends, String target)
    {
        Set<String> deadEnd=new HashSet<>(Arrays.asList(deadends));
        if(target==null || target.length()==0 || deadEnd.contains("0000") || deadEnd.contains(target))
            return -1;
        int count=0;
        Queue<String> q=new LinkedList<>();
        Set<String> visited=new HashSet<>();
        q.offer("0000");
        visited.add("0000");
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                String curr=q.poll();
                if(curr.equals(target))
                    return count;
                List<String> combinations=getNext(curr);
                for(String sub:combinations)
                {
                    if(deadEnd.contains(sub) || visited.contains(sub))
                        continue;
                    q.offer(sub);
                    visited.add(sub);
                }
            }
            count++;
        }
        return -1;
    }

    public List<String> getNext(String s)
    {
        List<String> res=new ArrayList<>();
        for(int i=0;i<s.length();i++)
        {
            int val=s.charAt(i)-'0';
            int plusVal=(val+1+10)%10;
            int minusVal=(val-1+10)%10;
            String p=s.substring(0,i)+plusVal+s.substring(i+1);
            String m=s.substring(0,i)+minusVal+s.substring(i+1);
            res.add(p);
            res.add(m);
        }
        return res;
    }

    //Surrounded Regions
    {
        /*
        Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

        A region is captured by flipping all 'O's into 'X's in that surrounded region.

        Example:

        X X X X
        X O O X
        X X O X
        X O X X
        After running your function, the board should be:

        X X X X
        X X X X
        X X X X
        X O X X
        Explanation:

        Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
        Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
        Two cells are connected if they are adjacent cells connected horizontally or vertically.
         */
    }
    public void solve(char[][] board)
    {
        if(board==null || board.length<2 || board[0].length<2)
            return;
        for(int i=0;i<board.length;i++)
        {
            if(board[i][0]=='O')
                flip(board,i,0);
            if(board[i][board[0].length-1]=='O')
                flip(board,i,board[0].length-1);
        }
        for(int j=0;j<board[0].length;j++)
        {
            if(board[0][j]=='O')
                flip(board,0,j);
            if(board[board.length-1][j]=='O')
                flip(board,board.length-1,j);
        }

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]=='O')
                    board[i][j]='X';
                else if(board[i][j]=='*')
                    board[i][j]='O';
            }
        }
        return;
    }

    public void flip(char[][] board, int i, int j)
    {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!='O')
            return;
        board[i][j]='*';
        flip(board,i+1,j);
        flip(board,i-1,j);
        flip(board,i,j+1);
        flip(board,i,j-1);
    }

    //Merge Intervals
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
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0],b[0]);
            }
        });

        LinkedList<int[]> res=new LinkedList<>();
        for(int[] i: intervals)
        {
            if(res.isEmpty() || res.getLast()[1]<i[0])
                res.add(i);
            else if(res.getLast()[1]>i[0])
                res.getLast()[1]=Math.max(i[1],res.getLast()[1]);
        }
        return res.toArray(new int[res.size()][]);
    }

    //Meeting Rooms 2
    {
        /*
        Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

        Example 1:

        Input: [[0, 30],[5, 10],[15, 20]]           0,5,15      10,20,30
        Output: 2
        Example 2:

        Input: [[7,10],[2,4]]
        Output: 1
        NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
         */
    }
    public int minMeetingRooms(int[][] intervals)
    {
        if(intervals==null || intervals.length==0)
            return 0;
        int[] start=new int[intervals.length];
        int[] end=new int[intervals.length];
        for(int i=0;i<intervals.length;i++)
        {
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int sp=0,ep=0;
        int count=0;
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

    //Exam Room
    {
        /*
        In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

        When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.
        If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)
        Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.
        It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.



        Example 1:

        Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
        Output: [null,0,9,4,2,null,5]
        Explanation:
        ExamRoom(10) -> null
        seat() -> 0, no one is in the room, then the student sits at seat number 0.
        seat() -> 9, the student sits at the last seat number 9.
        seat() -> 4, the student sits at the last seat number 4.
        seat() -> 2, the student sits at the last seat number 2.
        leave(4) -> null
        seat() -> 5, the student sits at the last seat number 5.
        ​​​​​​​

        Note:

        1 <= N <= 10^9
        ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
        Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
         */
    }

    private class ExamRoom
    {
        private class Interval
        {
            int start,end,length;
            public Interval(int s, int e, int l)
            {
                start=s;
                end=e;
                length=l;
            }
        }
        PriorityQueue<Interval> pq;
        int count;
        public ExamRoom(int N)
        {
            count=N;
            pq=new PriorityQueue<Interval>(new Comparator<Interval>(){
                public int compare(Interval a, Interval b)
                {
                    int cmp=Integer.compare(b.length,a.length);
                    if(cmp==0)
                        return Integer.compare(a.start,b.start);
                    return cmp;
                }
            });
            pq.offer(new Interval(-1,count,count));
        }

        public int seat()
        {
            Interval curr=pq.poll();
            if(curr.start==-1)
            {
                pq.offer(new Interval(0,curr.end,curr.length-1));
                return 0;
            }
            else if(curr.end==count)
            {
                pq.offer(new Interval(curr.start,count-1,curr.length-1));
                return count-1;
            }
            else
            {
                int seat=curr.start+(curr.end-curr.start)/2;
                pq.offer(new Interval(curr.start,seat,(seat-curr.start)/2));
                pq.offer(new Interval(seat,curr.end,(curr.end-seat)/2));
                return seat;
            }
        }

        public void leave(int p)
        {
            Interval prev=null;
            Interval curr=null;
            for(Interval i:pq)
            {
                if(i.end==p)
                    prev=i;
                if(i.start==p)
                    curr=i;
            }
            if(prev==null && curr!=null)
            {
                pq.remove(curr);
                pq.offer(new Interval(-1,curr.end,curr.end-curr.start));
            }
            else if(curr==null && prev!=null)
            {
                pq.remove(prev);
                pq.offer(new Interval(prev.start,count,prev.end-prev.start));
            }
            else
            {
                pq.remove(prev);
                pq.remove(curr);
                if(prev.start==-1 || curr.end==count)
                    pq.offer(new Interval(prev.start,curr.end,curr.end-prev.start));
                else
                    pq.offer(new Interval(prev.start,curr.end,(curr.end-prev.start)/2));
            }
        }
    }

    //My Calendar
    {
        /*
        Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

        Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

        A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

        For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

        Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
        Example 1:

        MyCalendar();
        MyCalendar.book(10, 20); // returns true
        MyCalendar.book(15, 25); // returns false
        MyCalendar.book(20, 30); // returns true
        Explanation:
        The first event can be booked.  The second can't because time 15 is already booked by another event.
        The third event can be booked, as the first event takes every time less than 20, but not including 20.
         */
    }
    private class MyTreeNode
    {
        int start,end;
        MyTreeNode left,right;
        MyTreeNode(int s, int e)
        {
            left=null;
            right=null;
            start=s;
            end=e;
        }
    }

    class MyCalendar2
    {
        MyTreeNode root;
        boolean inserted;
        public MyCalendar2()
        {
            inserted=false;
            root=null;
        }
        public MyTreeNode insert(MyTreeNode node, int start, int end)
        {
            if(node==null)
            {
                inserted=true;
                return new MyTreeNode(start,end);
            }
            if(node.start>=end)
                node.left=insert(node.left,start,end);
            else if(node.end<=start)
                node.right=insert(node.right,start,end);
            return node;
        }
        public boolean book(int start, int end)
        {
            if(end<start || start<0)
                return false;
            root=insert(root,start,end);
            if(inserted)
            {
                inserted=false;
                return true;
            }
            else
                return false;
        }
    }

    //Largest Rectangle in Histogram
    {
        /*
        Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
        Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
        The largest rectangle is shown in the shaded area, which has area = 10 unit.

        Example:
        Input: [2,1,5,6,2,3]
        Output: 10
         */
    }
    public int largestRectangleArea(int[] hist)
    {
        int i=0,max_area=0;
        Stack<Integer> st=new Stack<>();
        while(i<hist.length)
        {
            if(st.isEmpty() || hist[st.peek()]<=hist[i])
                st.push(i++);
            else
            {
                int tp=st.pop();
                int area=hist[tp]*(st.isEmpty()?i:i-st.peek()-1);
                max_area=Math.max(area,max_area);
            }
        }
        while(!st.isEmpty())
        {
            int tp=st.pop();
            int area=hist[tp]*(st.isEmpty()?i:i-st.peek()-1);
            max_area=Math.max(area,max_area);
        }
        return max_area;
    }

    //Maximal Rectangle
    {
        /*
        Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

        Example:

        Input:
        [
          ["1","0","1","0","0"],
          ["1","0","1","1","1"],
          ["1","1","1","1","1"],
          ["1","0","0","1","0"]
        ]
        Output: 6
         */
    }
    public int maximalRectangle(char[][] matrix)
    {
        if(matrix==null || matrix.length==0)
            return 0;
        int maxArea=0;
        int[] dp=new int[matrix[0].length];
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
                dp[j]=matrix[i][j]=='1'?dp[j]+1:0;

            maxArea=Math.max(maxArea,largestRectangleArea(dp));
        }
        return maxArea;
    }

    //WordBreak
    {
        /*
        Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

        Note:

        The same word in the dictionary may be reused multiple times in the segmentation.
        You may assume the dictionary does not contain duplicate words.
        Example 1:

        Input: s = "leetcode", wordDict = ["leet", "code"]
        Output: true
        Explanation: Return true because "leetcode" can be segmented as "leet code".
        Example 2:

        Input: s = "applepenapple", wordDict = ["apple", "pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
                     Note that you are allowed to reuse a dictionary word.
        Example 3:

        Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        Output: false
         */
    }
    public boolean wordBreak(String s, List<String> wordDict)
    {
        if(s==null || s.length()==0)
            return false;
        Set<String> dict=new HashSet<>(wordDict);
        boolean[] visited=new boolean[s.length()+1];
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        visited[0]=true;
        while(!q.isEmpty())
        {
            int curr=q.poll();
            for(int i=curr+1;i<=s.length();i++)
            {
                if(dict.contains(s.substring(curr,i)) && !visited[i])
                {
                    if(i==s.length())
                        return true;
                    else
                    {
                        q.offer(i);
                        visited[i]=true;
                    }
                }
            }
        }
        return false;
    }

    //WordBreak2
    {
        /*
        Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

        Note:

        The same word in the dictionary may be reused multiple times in the segmentation.
        You may assume the dictionary does not contain duplicate words.
        Example 1:

        Input:
        s = "catsanddog"
        wordDict = ["cat", "cats", "and", "sand", "dog"]
        Output:
        [
          "cats and dog",
          "cat sand dog"
        ]
        Example 2:

        Input:
        s = "pineapplepenapple"
        wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
        Output:
        [
          "pine apple pen apple",
          "pineapple pen apple",
          "pine applepen apple"
        ]
        Explanation: Note that you are allowed to reuse a dictionary word.
        Example 3:

        Input:
        s = "catsandog"
        wordDict = ["cats", "dog", "sand", "and", "cat"]
        Output:
        []
         */
    }
    public List<String> wordBreak2(String s, List<String> wordDict)
    {
        return DFS(s,new HashSet<>(wordDict),new HashMap<String,List<String>>());
    }
    public List<String> DFS(String s, Set<String> dict, Map<String,List<String>> memo)
    {
        if(memo.containsKey(s))
            return memo.get(s);
        List<String> res=new ArrayList<>();
        if(s.length()==0)
            res.add("");
        for(int i=0;i<=s.length();i++)
        {
            String str=s.substring(0,i);
            if(dict.contains(str))
            {
                List<String> sub=DFS(s.substring(i),dict,memo);
                for(String t:sub)
                {
                    res.add(str+(t.isEmpty()?"":" ")+t);
                }
            }
        }
        memo.put(s,res);
        return res;
    }

    //Concatenated Words
    {
        /*
        Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
        A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

        Example:
        Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

        Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

        Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
         "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
        "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
        Note:
        The number of elements of the given array will not exceed 10,000
        The length sum of elements in the given array will not exceed 600,000.
        All the input string will only include lower case letters.
        The returned elements order does not matter.
         */
    }
    public List<String> findAllConcatenatedWordsInADict(String[] words)
    {
        if(words==null || words.length==0)
            return new ArrayList<>();
        Set<String> dict=new HashSet<>(Arrays.asList(words));
        List<String> res=new ArrayList<>();
        for(String w:words)
        {
            dict.remove(w);
            if(canForm(w,dict))
                res.add(w);
            dict.add(w);
        }
        return res;
    }
    public boolean canForm(String s, Set<String> dict)
    {
        boolean[] visited=new boolean[s.length()+1];
        visited[0]=true;
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        while(!q.isEmpty())
        {
            int curr=q.poll();
            for(int i=curr+1;i<=s.length();i++)
            {
                if(dict.contains(s.substring(curr,i)) && !visited[i])
                {
                    if(i==s.length())
                        return true;
                    q.offer(i);
                    visited[i]=true;
                }
            }
        }
        return false;
    }

    //Longest Substring with at most 2 distinct characters
    {
        /*
        Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

        Example 1:

        Input: "eceba"
        Output: 3
        Explanation: t is "ece" which its length is 3.
        Example 2:

        Input: "ccaabbb"
        Output: 5
        Explanation: t is "aabbb" which its length is 5.
         */
    }
    public int lengthOfLongestSubstringTwoDistinct(String s)
    {
        Map<Character,Integer> map=new HashMap<>();
        int i=0,j=0,res=0;
        while(j<s.length())
        {
            char c=s.charAt(j);
            map.put(c,map.getOrDefault(c,0)+1);
            while(map.size()>2)
            {
                map.put(s.charAt(i),map.get(s.charAt(i))-1);
                if(map.get(s.charAt(i))==0)
                    map.remove(s.charAt(i));
                i++;
            }
            res=Math.max(j-i+1,res);
            j++;
        }
        return res;
    }

    //Longest Substring with At Most K Distinct Characters
    {
        /*
        Given a string, find the length of the longest substring T that contains at most k distinct characters.

        Example 1:

        Input: s = "eceba", k = 2
        Output: 3
        Explanation: T is "ece" which its length is 3.
        Example 2:

        Input: s = "aa", k = 1
        Output: 2
        Explanation: T is "aa" which its length is 2.
         */
    }
    public int lengthOfLongestSubstringKDistinct(String s, int k)
    {
        int i=0,j=0,res=0;
        Map<Character,Integer> map=new HashMap<>();
        while(j<s.length())
        {
            char c=s.charAt(j);
            map.put(c,map.getOrDefault(c,0)+1);
            while(map.size()>k)
            {
                char ch=s.charAt(i);
                map.put(ch,map.get(ch)-1);
                if(map.get(ch)==0)
                    map.remove(ch);
                i++;
            }
            res=Math.max(res,j-i+1);
            j++;
        }
        return res;
    }

    //Implement a Trie
    {
        /*
        Implement a trie with insert, search, and startsWith methods.

        Example:

        Trie trie = new Trie();

        trie.insert("apple");
        trie.search("apple");   // returns true
        trie.search("app");     // returns false
        trie.startsWith("app"); // returns true
        trie.insert("app");
        trie.search("app");     // returns true
        Note:

        You may assume that all inputs are consist of lowercase letters a-z.
        All inputs are guaranteed to be non-empty strings.
         */
    }

    private class Trie1
    {
        Trie1[] next;
        boolean isWord;
        public Trie1()
        {
            next=new Trie1[26];
            isWord=false;
        }

        public void insert(String s,Trie1 root)
        {
            for(char c:s.toCharArray())
            {
                if(root.next[c-'a']==null)
                    root.next[c-'a']=new Trie1();
                root=root.next[c-'a'];
            }
            root.isWord=true;
        }

        public boolean search(String s, Trie1 root)
        {
            for(char c:s.toCharArray())
            {
                if(root.next[c-'a']==null)
                    return false;
                root=root.next[c-'a'];
            }
            return root.isWord;
        }

        public boolean startsWith(String prefix,Trie1 root)
        {
            for(char c:prefix.toCharArray())
            {
                if(root.next[c-'a']==null)
                    return false;
                root=root.next[c-'a'];
            }
            return true;
        }
    }
    public void testTrie1()
    {
        Trie1 obj1=new Trie1();
        obj1.insert("apple",obj1);
        System.out.println(obj1.search("apple",obj1));
        System.out.println(obj1.search("app",obj1));
        System.out.println(obj1.startsWith("app",obj1));
        obj1.insert("app",obj1);
        System.out.println(obj1.search("app",obj1));
    }

    //Longest Word in Dictionary
    {
        /*
        Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

        If there is no answer, return the empty string.
        Example 1:
        Input:
        words = ["w","wo","wor","worl", "world"]
        Output: "world"
        Explanation:
        The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
        Example 2:
        Input:
        words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
        Output: "apple"
        Explanation:
        Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
        Note:

        All the strings in the input will only contain lowercase letters.
        The length of words will be in the range [1, 1000].
        The length of words[i] will be in the range [1, 30].
         */
    }

    private class Trie2
    {
        Trie2[] next;
        String word;
        public Trie2()
        {
            next=new Trie2[26];
            word=null;
        }
    }
    public void insert(String s, Trie2 root)
    {
        for(char c:s.toCharArray())
        {
            if(root.next[c-'a']==null)
                root.next[c-'a']=new Trie2();
            root=root.next[c-'a'];
        }
        root.word=s;
    }
    String res="";
    public void dfs(Trie2 root)
    {
        if(root==null)
            return;
        if(root.word!=null)
        {
            if(root.word.length()>res.length())
                res=root.word;
        }
        for(Trie2 node:root.next)
        {
            if(node!=null && node.word!=null)
                dfs(node);
        }
    }

    public String longestWord(String[] words)
    {
        Trie2 root=new Trie2();
        for(String word:words)
            insert(word,root);

        dfs(root);
        return res;
    }

    //Word Search 2

    {
        /*
        Given a 2D board and a list of words from the dictionary, find all words in the board.

        Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



        Example:

        Input:
        board = [
          ['o','a','a','n'],
          ['e','t','a','e'],
          ['i','h','k','r'],
          ['i','f','l','v']
        ]
        words = ["oath","pea","eat","rain"]

        Output: ["eat","oath"]


        Note:

        All inputs are consist of lowercase letters a-z.
        The values of words are distinct.
         */
    }

    public List<String> findWords(char[][] board, String[] words)
    {
        if(board==null || board.length==0)
            return new ArrayList<>();
        List<String> res=new ArrayList<>();
        for(String str:words)
        {
            if(exist(str,board))
                res.add(str);
        }

        List<String> result=new ArrayList<>();
        Trie2 root=new Trie2();
        for(String s:words)
            insert(s,root);
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                dfs(board,i,j,root,result);

        return result;
    }

    public void dfs(char[][] board, int i, int j, Trie2 root, List<String> result)
    {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]=='#')
            return;
        char c=board[i][j];
        if(root.next[c-'a']==null)
            return;
        root=root.next[c-'a'];
        if(root.word!=null)
        {
            result.add(root.word);
            root.word=null;
        }
        board[i][j]='#';
        dfs(board,i+1,j,root,result);
        dfs(board,i-1,j,root,result);
        dfs(board,i,j+1,root,result);
        dfs(board,i,j-1,root,result);
        board[i][j]=c;

    }
    public boolean exist(String s, char[][] board)
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]==s.charAt(0) && exist(board,s,i,j,new boolean[board.length][board[0].length],0))
                    return true;
            }
        }
        return false;
    }
    public boolean exist(char[][] board,String s, int i, int j, boolean[][] visited, int idx)
    {
        if(idx==s.length())
            return true;
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j] || board[i][j]!=s.charAt(idx))
            return false;
        visited[i][j]=true;
        boolean ans=false;
        ans|=exist(board,s,i+1,j,visited,idx+1);
        ans|=exist(board,s,i-1,j,visited,idx+1);
        ans|=exist(board,s,i,j+1,visited,idx+1);
        ans|=exist(board,s,i,j-1,visited,idx+1);
        visited[i][j]=false;
        return ans;
    }

    //AutoComplete Search System
    {
        /*
        Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
        For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

        The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
        The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
        If less than 3 hot sentences exist, then just return as many as you can.
        When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
        Your job is to implement the following functions:

        The constructor function:

        AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

        Now, the user wants to input a new sentence. The following function will provide the next character the user types:

        List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
        Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


        Example:
        Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
        The system have already tracked down the following sentences and their corresponding times:
        "i love you" : 5 times
        "island" : 3 times
        "ironman" : 2 times
        "i love leetcode" : 2 times
        Now, the user begins another search:

        Operation: input('i')
        Output: ["i love you", "island","i love leetcode"]
        Explanation:
        There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman".
        Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

        Operation: input(' ')
        Output: ["i love you","i love leetcode"]
        Explanation:
        There are only two sentences that have prefix "i ".

        Operation: input('a')
        Output: []
        Explanation:
        There are no sentences that have prefix "i a".

        Operation: input('#')
        Output: []
        Explanation:
        The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.


        Note:

        The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
        The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
        Please use double-quote instead of single-quote when you write test cases even for a character input.
        Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
         */
    }

    private class AutocompleteSystem
    {
        private class Trie
        {
            Trie[] next;
            Map<String,Integer> freq;
            public Trie()
            {
                next=new Trie[27];
                freq=new HashMap<>();
            }
        }
        Trie root=new Trie();
        String prefix="";
        public int toInt(char c)
        {
            return c==' '?26:c-'a';
        }
        public void insert(String s, int time, Trie node)
        {
            for(char c:s.toCharArray())
            {
                int val=toInt(c);
                if(node.next[val]==null)
                    node.next[val]=new Trie();
                node=node.next[val];
                node.freq.put(s,node.freq.getOrDefault(s,0)+time);
            }

        }
        public AutocompleteSystem(String[] sentences, int[] times)
        {
            for(int i=0;i<times.length;i++)
                insert(sentences[i],times[i],root);
        }
        public List<String> input(char ch)
        {
            if(ch=='#')
            {
                insert(prefix,1,root);
                prefix="";
                return new ArrayList<>();
            }
            else
            {
                prefix+=ch;
                Trie node=root;
                for(char c:prefix.toCharArray())
                {
                    if(node.next[toInt(c)]==null)
                        return new ArrayList<>();
                    node=node.next[toInt(c)];
                }
                Map<String,Integer> map=node.freq;
                PriorityQueue<String> pq=new PriorityQueue<String>(new Comparator<String>() {
                    @Override
                    public int compare(String a, String b)
                    {
                        if(map.get(a)==map.get(b))
                            return a.compareTo(b);
                        else
                            return map.get(b)-map.get(a);
                    }
                });
                pq.addAll(map.keySet());
                List<String> res=new ArrayList<>();
                int k=3;
                while(!pq.isEmpty() && k>0)
                {
                    res.add(pq.poll());
                    k--;
                }
                return res;
            }
        }
    }

    public void testAutoComplete()
    {
        AutocompleteSystem obj=new AutocompleteSystem(new String[]{"i love you","island","iroman","i love leetcode"},new int[]{5,3,2,2});
        System.out.println(obj.input('i'));
        System.out.println(obj.input(' '));
        System.out.println(obj.input('a'));
        System.out.println(obj.input('#'));
    }


    //Search Suggestion System
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


        Constraints:

        1 <= products.length <= 1000
        There are no repeated elements in products.
        1 <= Σ products[i].length <= 2 * 10^4
        All characters of products[i] are lower-case English letters.
        1 <= searchWord.length <= 1000
        All characters of searchWord are lower-case English letters.
         */
    }

    private class Trie3
    {
        Trie3[] next;
        LinkedList<String> suggestions;
        public Trie3()
        {
            next=new Trie3[26];
            suggestions=new LinkedList<>();
        }
    }
    public void add(String str, Trie3 node)
    {
        for(char c:str.toCharArray())
        {
            if(node.next[c-'a']==null)
                node=node.next[c-'a'];
            node=node.next[c-'a'];
            node.suggestions.add(str);
            if(node.suggestions.size()>3)
                node.suggestions.pollLast();

        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord)
    {
        Arrays.sort(products);
        Trie3 root=new Trie3();
        for(String s:products)
            add(s,root);

        List<List<String>> res=new ArrayList<>();
        for(char c:searchWord.toCharArray())
        {
            if(root!=null)
                root=root.next[c-'a'];
            res.add(root==null?new ArrayList<>():root.suggestions);
        }
        return res;
    }

    //Network Delay Time
    {
        /*
        There are N network nodes, labelled 1 to N.

        Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

        Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.



        Example 1:



        Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
        Output: 2


        Note:

        N will be in the range [1, 100].
        K will be in the range [1, N].
        The length of times will be in the range [1, 6000].
        All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
         */
    }
    public int networkDelayTime(int[][] times, int N, int K)
    {
        if(N==0)
            return 0;
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] i:times)
        {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
        }
        int[] dist=new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[K]=0;
        Queue<Integer> q=new LinkedList<>();
        q.offer(K);
        while(!q.isEmpty())
        {
            int curr=q.poll();
            int d=dist[curr];
            if(!map.containsKey(curr))
                continue;
            for(int[] i:map.get(curr))
            {
                if(dist[i[0]]>d+i[1])
                {
                    dist[i[0]]=d+i[1];
                    q.offer(i[1]);
                }
            }
        }
        int res=0;
        for(int i=1;i<dist.length;i++)
        {
            if(dist[i]==Integer.MAX_VALUE)
                return -1;
            res=Math.max(res,dist[i]);
        }
        return res;
    }

    //Cheapest Flights Within K stops
    {
        /*
        There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

        Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

        Example 1:
        Input:
        n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        src = 0, dst = 2, k = 1
        Output: 200
        Explanation:
        The graph looks like this:


        The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
        Example 2:
        Input:
        n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        src = 0, dst = 2, k = 0
        Output: 500
        Explanation:
        The graph looks like this:


        The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
        Note:

        The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
        The size of flights will be in range [0, n * (n - 1) / 2].
        The format of each flight will be (src, dst, price).
        The price of each flight will be in the range [1, 10000].
        k is in the range of [0, n - 1].
        There will not be any duplicated flights or self cycles.
         */
    }

    int ans_dfs=Integer.MAX_VALUE;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K)
    {
        if(flights==null || flights.length==0)
            return -1;
        Map<Integer,List<int[]>> graph=new HashMap<>();
        for(int[] i:flights)
        {
            graph.putIfAbsent(i[0],new ArrayList<>());
            graph.get(i[0]).add(new int[]{i[1],i[2]});
        }
        dfs(graph,src,dst,K+1,0);
        int ans_bfs=bfs(graph,src,dst,K);
        int ans=bellmanFord(n,flights,src,dst,K);
        int ans_d=dijkstra(graph,src,dst,K);
        return ans_d;
    }
    public int dijkstra(Map<Integer,List<int[]>> graph, int src, int dst, int K)
    {
        PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b)
            {
                return Integer.compare(a[0],b[0]);
            }
        });
        pq.offer(new int[]{0,src,K+1});
        while(!pq.isEmpty())
        {
            int[] curr=pq.poll();
            int cost=curr[0],node=curr[1],stops=curr[2];
            if(dst==node)
                return cost;
            if(stops>0)
            {
                if(!graph.containsKey(node))
                    continue;
                for(int[] next:graph.get(node))
                    pq.offer(new int[]{cost+next[1],next[0],stops-1});
            }
        }
        return -1;
    }
    public int bellmanFord(int n, int[][] flights, int src, int dst, int K)
    {
        int[] cost=new int[n];
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[src]=0;
        for(int i=0;i<=K;i++)
        {
            int[] temp=Arrays.copyOf(cost,n);
            for(int[] f:flights)
            {
                int curr=f[0],next=f[1],val=f[2];
                if(cost[curr]==Integer.MAX_VALUE)
                    continue;
                temp[next]=Math.min(cost[curr]+val,temp[next]);
            }
            cost=temp;
        }
        return cost[dst]==Integer.MAX_VALUE?-1:cost[dst];
    }
    public int bfs(Map<Integer,List<int[]>> graph,int src, int dst, int K)
    {
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{src,0});
        int ans=Integer.MAX_VALUE;
        int steps=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[] curr=q.poll();
                if(curr[0]==dst)
                    ans=Math.min(ans,curr[1]);
                if(!graph.containsKey(curr[0]))
                    continue;
                for(int[] node:graph.get(curr[0]))
                {
                    if(curr[1]+node[1]>ans)
                        continue;
                    q.offer(new int[]{node[0],node[1]+curr[1]});
                }
            }
            if(steps++>K)
                break;
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    public void dfs(Map<Integer,List<int[]>> graph, int src, int dst, int stops, int cost)
    {
        if(stops<0)
            return;
        if(src==dst)
        {
            ans_dfs=Math.min(ans_dfs,cost);
            return;
        }
        if(!graph.containsKey(src))
            return;
        for(int[] i:graph.get(src))
        {
            int d=i[0];
            int c=i[1];
            if(cost+c>ans_dfs)
                continue;
            dfs(graph,d,dst,stops-1,cost+c);
        }
    }

    //Maximum Vacation Days
    {
        /*
        LeetCode wants to give one of its best employees the option to travel among N cities to collect algorithm problems. But all work and no play makes Jack a dull boy,
        you could take vacations in some particular cities and weeks. Your job is to schedule the traveling to maximize the number of vacation days you could take,
        but there are certain rules and restrictions you need to follow.

        Rules and restrictions:
        You can only travel among N cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on Monday.
        The cities are connected by flights. The flights are represented as a N*N matrix (not necessary symmetrical), called flights representing the airline status from the city i to the city j.
        If there is no flight from the city i to the city j, flights[i][j] = 0; Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.
        You totally have K weeks (each week has 7 days) to travel. You can only take flights at most once per day and can only take flights on each week's Monday morning. Since flight time is so short, we don't consider the impact of flight time.
        For each city, you can only have restricted vacation days in different weeks, given an N*K matrix called days representing this relationship. For the value of days[i][j], it represents the maximum days you could take vacation in the city i in the week j.
        You're given the flights matrix and days matrix, and you need to output the maximum vacation days you could take during K weeks.

        Example 1:
        Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
        Output: 12
        Explanation:
        Ans = 6 + 3 + 3 = 12.

        One of the best strategies is:
        1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day.
        (Although you start at city 0, we could also fly to and start at other cities since it is Monday.)
        2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
        3rd week : stay at city 2, and play 3 days and work 4 days.
        Example 2:
        Input:flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
        Output: 3
        Explanation:
        Ans = 1 + 1 + 1 = 3.

        Since there is no flights enable you to move to another city, you have to stay at city 0 for the whole 3 weeks.
        For each week, you only have one day to play and six days to work.
        So the maximum number of vacation days is 3.
        Example 3:
        Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
        Output: 21
        Explanation:
        Ans = 7 + 7 + 7 = 21

        One of the best strategies is:
        1st week : stay at city 0, and play 7 days.
        2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
        3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
        Note:
        N and K are positive integers, which are in the range of [1, 100].
        In the matrix flights, all the values are integers in the range of [0, 1].
        In the matrix days, all the values are integers in the range [0, 7].
        You could stay at a city beyond the number of vacation days, but you should work on the extra days, which won't be counted as vacation days.
        If you fly from the city A to the city B and take the vacation on that day, the deduction towards vacation days will count towards the vacation days of city B in that week.
        We don't consider the impact of flight hours towards the calculation of vacation days.
         */
    }

    public int maxVacationDays(int[][] flights, int[][] days)
    {
        int[][] memo=new int[flights.length][days[0].length];
        for(int[] i:memo)
            Arrays.fill(i,Integer.MIN_VALUE);
        return dfs(flights,days,0,0,memo);
    }
    public int dfs(int[][] flights, int[][] days, int city, int weekno, int[][] memo)
    {
        if(weekno==days[0].length)
            return 0;
        if(memo[city][weekno]!=Integer.MIN_VALUE)
            return memo[city][weekno];
        int max=0;
        for(int j=0;j<flights[0].length;j++)
        {
            if(j==city || flights[city][j]==1)
            {
                int val=days[j][weekno]+dfs(flights,days,j,weekno+1,memo);
                max=Math.max(max,val);
            }
        }
        memo[city][weekno]=max;
        return max;
    }

    //Night Route
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
    }
    public int nightRoute(int[][] city)
    {
        int[] dist=new int[city.length];
        boolean sptset[]=new boolean[city.length];
        for(int i=0;i< dist.length;i++)
        {
            dist[i]=Integer.MAX_VALUE;
            sptset[i]=false;
        }
        dist[0]=0;
        for(int i=0;i<city.length-1;i++)
        {
            int u=minDistance(dist,sptset);
            sptset[u]=true;
            for(int v=0;v<city.length;v++)
            {
                if(!sptset[v] && city[u][v]!=-1 && dist[u]!=Integer.MAX_VALUE && dist[u]+city[u][v]<dist[v])
                    dist[v]=dist[u]+city[u][v];
            }
        }
        return dist[dist.length-1];
    }
    public int minDistance(int[] dist, boolean[] sptset)
    {
        int minIndex=0,min=Integer.MAX_VALUE;
        for(int i=0;i<dist.length;i++)
        {
            if(dist[i]<min && sptset[i]==false)
            {
                minIndex=i;
                min=dist[i];
            }
        }
        return minIndex;
    }
    public static void main(String[] args)
    {
        Uber_Onsite obj=new Uber_Onsite();
        System.out.println(obj.findCheapestPrice(3,new int[][]{{0,1,100},{1,2,100},{0,2,500}},0,2,1));
        //obj.testAutoComplete();
        //System.out.println(obj.longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));
        //obj.testTrie1();
        //System.out.println(obj.lengthOfLongestSubstringKDistinct("eceba",2));
        //System.out.println(obj.findAllConcatenatedWordsInADict(new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"}));
        //String[] wordDict={"cat","cats","and","sand","dog"};
        //System.out.println(obj.wordBreak2("catsanddog",new ArrayList<>(Arrays.asList(wordDict))));
        //System.out.println(obj.maximalRectangle(new char[][] {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
        //System.out.println(obj.largestRectangleArea(new int[]{4,0,0,3,0}));
        //System.out.println(obj.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        //obj.solve(new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}});
        //System.out.println(obj.calculate2("3+5 / 2"));
        //System.out.println(obj.searchRange(new int[]{5,7,7,8,8,10},8));
        //System.out.println(obj.minimumCost(3,new int[][]{{1,2,5},{1,3,6},{2,3,1}}));
        //System.out.println(obj.palindromePartition2("abc",2));
        //System.out.println(obj.minTransfers(new int[][]{{0,1,10},{1,0,1},{1,2,5},{2,0,5}}));
        //System.out.println(obj.wordPatternMatch("abab","redblueredblue"));
        //System.out.println(obj.movesToStamp("abca","aabcaca"));
        //Node ret=obj.construct(new int[][]{{1,1,0,0},{1,1,0,0},{0,0,1,1},{0,0,1,1}});
        //System.out.println(ret);
        //System.out.println(obj.intersectionSizeTwo(new int[][]{{1,3},{1,4},{2,5},{3,5}}));
        //System.out.println(obj.numIslands2(3,3,new int[][]{{0,0},{0,1},{1,2},{2,1}}));
        //System.out.println(obj.cherryPickup(new int[][]{{1,1,1,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,1},{1,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,1,1,1}}));
        //System.out.println(obj.cherryPickup(new int[][]{{1,1,1,0,0},{0,0,0,0,0},{0,0,1,0,1},{1,0,1,0,0},{0,0,1,1,1}}));
        //System.out.println(obj.cherryPickup(new int[][]{{1,1,0},{1,1,1},{0,1,1}}));
    }

}
