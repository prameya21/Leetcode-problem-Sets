import java.util.*;
 class Interval
 {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
 }
class AlienDict2
{
    Map<Character, ArrayList<Character>> adjList= new HashMap<Character,ArrayList<Character>>();
    public AlienDict2(String[] words)
    {
        for(int i=0;i<words.length;i++)
        {
            for(int j=0;j<words[i].length();j++)
            {
                if(!adjList.containsKey(words[i].charAt(j)))
                {
                    adjList.put(words[i].charAt(j),new ArrayList<>());
                }
            }
        }
    }
    public void addEdge(char key,char value)
    {
        ArrayList<Character> temp=adjList.get(key);
        temp.add(value);
        adjList.put(key,temp);
    }
    public void topologicalSort()
    {
        HashSet<Character> visited=new HashSet<>();
        Stack<Character> st=new Stack<>();
        for(Character c:adjList.keySet())
        {
            if(!visited.contains(c))
            {
                topologicalSortUtil(c,visited,st);
            }
        }
        while(!st.isEmpty())
            System.out.print(st.pop()+" ");
    }
    public void topologicalSortUtil(Character c, HashSet<Character> visited,Stack<Character> st)
    {
        if(!visited.contains(c))
        {
            visited.add(c);
            for(Character ch:adjList.get(c))
            {
                if(!visited.contains(ch))
                {
                    topologicalSortUtil(ch,visited,st);
                }
            }
            st.add(c);
        }
    }
}
public class leetcode2 {

    public static int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target)
                high = mid;
            else
                return mid;
        }
        return -1;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] range = {-1, -1};
        int start = extremeInsertionIndex(nums, target, true);
        if (start == nums.length || nums[start] != target)
            return range;
        else
            return new int[]{start, extremeInsertionIndex(nums, target, false) - 1};
    }

    public static int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static void alienLexicon(String[] words) {
        AlienDict2 ad = new AlienDict2(words);
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    ad.addEdge(words[i].charAt(j), words[i + 1].charAt(j));
                    break;
                }
            }
        }
        ad.topologicalSort();
    }

    public static int extremeInsertionIndex1(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static boolean isValidSudoku(char[][] board) {
        Set<String> hs = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    char number = board[i][j];
                    String srow = number + " row " + i;
                    String scol = number + " col " + j;
                    String sblock = number + " block " + i / 3 + "-" + j / 3;
                    if (!hs.add(srow) || !hs.add(scol) || !hs.add(sblock))
                        return false;
                }
            }
        }
        return true;
    }

    public static void solveSudoku(char[][] board) {
        if (board.length == 0 || board == null)
            return;
        solve(board);
    }

    public static boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != ',' && board[i][col] == c)
                return false;
            if (board[row][i] != ',' && board[row][i] == c)
                return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
                return false;
        }
        return true;
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        char[][] board = new char[n][n];
        for (char[] ch : board) {
            Arrays.fill(ch, '.');
        }
        placeQueen(0, result, n, board);
        return result;
    }

    public static void placeQueen(int row, List<List<String>> result, int n, char[][] board) {
        if (row == n) {
            List<String> val = new ArrayList<>();
            for (char[] ch : board) {
                val.add(new String(ch));
            }
            result.add(val);
        } else {
            for (int i = 0; i < n; i++) {
                if (validQueen(row, i, board, n)) {
                    board[row][i] = 'Q';
                    placeQueen(row + 1, result, n, board);
                    board[row][i] = '.';
                }
            }
        }
    }

    public static boolean validQueen(int row, int col, char[][] board, int n) {
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 'Q')
                return false;
            if (board[i][col] == 'Q')
                return false;
        }
        int r = row, c = col;
        while (r >= 0 && c >= 0) {
            if (board[r--][c--] == 'Q')
                return false;
        }
        r = row;
        c = col;
        while (r >= 0 && c < n) {
            if (board[r--][c++] == 'Q')
                return false;
        }
        return true;
    }

    public static List<List<String>> solveNQueens1(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 1) {
            List<String> oneRow = new ArrayList<>();
            oneRow.add("Q");
            res.add(oneRow);
            return res;
        }
        if (n == 2) return res;
        boolean[] ldiag = new boolean[2 * n - 1];
        boolean[] rdiag = new boolean[2 * n - 1];
        boolean[] col = new boolean[n];

        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        dfs(board, ldiag, rdiag, col, 0, res);

        return res;
    }

    public static void dfs(char[][] board, boolean[] ldiag, boolean[] rdiag, boolean[] col, int queens, List<List<String>> res) {
        int n = board.length;

        if (queens == n) {
            res.add(getSol(board));
            return;
        }

        for (int c = 0; c < n; c++) {
            //If we haven't used this column, ldiag or rdiag
            if (!col[c] && !ldiag[n + queens - c - 1] && !rdiag[queens + c]) {
                //Use
                col[c] = ldiag[queens - c + n - 1] = rdiag[queens + c] = true;
                //Mark the board;
                board[queens][c] = 'Q';
                dfs(board, ldiag, rdiag, col, queens + 1, res);
                board[queens][c] = '.';
                //unuse
                col[c] = ldiag[queens - c + n - 1] = rdiag[queens + c] = false;
            }
        }
    }

    public static List<String> getSol(char[][] board) {
        List<String> ret = new ArrayList<>();
        for (char[] row : board) ret.add(new String(row));
        return ret;
    }

    public static int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] ch : board) {
            Arrays.fill(ch, '.');
        }
        return place(0, 0, n, board);

    }

    public static int place(int row, int count, int n, char[][] board) {
        for (int i = 0; i < n; i++) {
            if (isValid(row, i, board, n)) {
                board[row][i] = 'Q';
                if (row + 1 == n)
                    count++;
                else
                    count = place(row + 1, count, n, board);
                board[row][i] = '.';
            }
        }
        return count;

    }

    public static boolean isValid(int row, int col, char[][] board, int n) {
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 'Q')
                return false;
            if (board[i][col] == 'Q')
                return false;
        }
        int r = row, c = col;
        while (r >= 0 && c >= 0) {
            if (board[r--][c--] == 'Q')
                return false;
        }
        r = row;
        c = col;
        while (r >= 0 && c < n) {
            if (board[r--][c++] == 'Q')
                return false;
        }
        return true;
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combination_backtrack(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;

    }

    public static void combination_backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int target, int start) {
        if (target == 0)
            result.add(new ArrayList<>(temp));
        else if (target < 0)
            return;
        else {
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                combination_backtrack(result, temp, nums, target - nums[i], i);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static String multiply(String num1, String num2)
    {
        int m=num1.length(),n=num2.length();
        int [] res=new int[m+n];
        for(int i=m-1;i>=0;i--)
        {
            for(int j=n-1;j>=0;j--)
            {
                int mul=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                int pval=i+j+1,pcarry=i+j;
                int sum=mul+res[pval];
                res[pcarry]+=sum/10;
                res[pval]=sum%10;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int p: res)
        {
            if(!(p==0 && (sb.length()==0)))
                sb.append(p);
        }
        return sb.toString();
    }
    public static List<List<Integer>> permute(int[] nums)
    {
        Arrays.sort(nums);
        List<List<Integer>> result=new ArrayList<>();
        backtrack_perm(result,new ArrayList<Integer>(),nums);
        return result;
    }
    public static void backtrack_perm(List<List<Integer>> result,ArrayList<Integer> temp,int[] nums)
    {
        if(temp.size()==nums.length)
            result.add(new ArrayList<>(temp));
        else
        {
            for(int i=0;i<nums.length;i++)
            {
                if(temp.contains(nums[i]))
                    continue;
                temp.add(nums[i]);
                backtrack_perm(result,temp,nums);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static List<List<Integer>> permuteUnique(int[] nums)
    {
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(nums);
        backtrack_permUnique(result, new ArrayList<Integer>(),nums,new boolean[nums.length]);
        return result;
    }
    public static void backtrack_permUnique(List<List<Integer>> result, ArrayList<Integer> temp,int[] nums,boolean[] used)
    {
        if(temp.size()==nums.length)
            result.add(new ArrayList<>(temp));
        else
        {
            for(int i=0;i<nums.length;i++)
            {
                if(used[i] || i>0 && nums[i]==nums[i-1] && !used[i-1])
                    continue;
                temp.add(nums[i]);
                used[i]=true;
                backtrack_permUnique(result,temp,nums,used);
                used[i]=false;
                temp.remove(temp.size()-1);
            }
        }

    }
    public static List<List<String>> groupAnagrams(String[] strs)
    {
        if(strs.length==0)
            return new ArrayList<>();
        Map<String,List<String>> map=new HashMap<>();
        for(String s:strs)
        {
            char[] ca=s.toCharArray();
            Arrays.sort(ca);
            String key=String.valueOf(ca);
            if(!map.containsKey(key))
                map.put(key,new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
    public static double myPow(double x, int n)
    {
        if(x==1)
            return 1;
        if(n==0)
            return 1;
        if(n<0)
        {
            n=-n;
            x=1/x;
        }
        double temp=myPow(x,n/2);
        if(n%2==0)
            return temp*temp;
        else
            return x*temp*temp;
    }
    public static int maxSubArray(int[] nums)
    {
        int max_running=nums[0],max_final=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            max_running+=nums[i];
            if(max_running<0)
                max_running=nums[i];
            if(max_final<max_running)
                max_final=max_running;
        }
        return max_final;
    }
    public static int maxProfit(int[] prices)
    {
        int minPrice=Integer.MAX_VALUE,maxprofit=0;
        for(int i=0;i<prices.length;i++)
        {
            if(prices[i]<minPrice)
                minPrice=prices[i];
            else if(prices[i]-minPrice>maxprofit)
                maxprofit=prices[i]-minPrice;
        }
        return maxprofit;
    }
    public static List<Integer> spiralOrder(int[][] matrix)
    {
        int rowstart=0,rowend=matrix.length-1;
        int colstart=0,colend=matrix[0].length-1;
        List<Integer> result=new ArrayList<>();
        while(rowstart<=rowend && colstart<=colend)
        {
            for(int i=colstart;i<=colend;i++)
                result.add(matrix[rowstart][i]);
            rowstart++;
            for(int i=rowstart;i<=rowend;i++)
                result.add(matrix[i][colend]);
            colend--;
            if(rowstart<rowend)
            {
                for(int i=colend;i>=colstart;i--)
                    result.add(matrix[rowend][i]);
            }
            rowend--;
            if(colend>colstart)
            {
                for(int i=rowend;i>=rowstart;i--)
                    result.add(matrix[i][colstart]);
            }
            colstart++;
        }
        return result;
    }
    static class IntervalCompare implements Comparator<Interval>
    {
        public int compare(Interval a,Interval b)
        {
            return a.start>b.start?-1:a.start==b.start?0:1;
        }
    }
    public static List<Interval> merge(List<Interval> intervals)
    {
        Collections.sort(intervals,new IntervalCompare());
        LinkedList<Interval> merged=new LinkedList<>();
        for(Interval interval:intervals)
        {
            if(merged.isEmpty() || merged.getLast().end<interval.start)
                merged.add(interval);
            else
                merged.getLast().end=Math.max(merged.getLast().end,interval.end);
        }
        return merged;
    }
    public int uniquePaths(int m, int n)
    {
        int[][] data=new int[m][n];
        for(int i=0;i<m;i++)
            data[i][0]=1;
        for(int i=0;i<n;i++)
            data[0][i]=1;
        for(int i=1;i<m;i++)
        {
            for(int j=1;j<n;j++)
                data[i][j]=data[i-1][j]+data[i][j-1];
        }
        return data[m-1][n-1];
    }
    public static int[][] generateMatrix(int n)
    {
        int result[][]=new int[n][n];
        int count=1;
        int rowstart=0,rowend=n-1;
        int colstart=0,colend=n-1;
        while(rowstart<=rowend && colstart<=colend)
        {
            for(int i=colstart;i<=colend;i++)
            {
                result[rowstart][i]=count;
                count++;
            }
            rowstart++;
            for(int i=rowstart;i<=rowend;i++)
            {
                result[i][colend]=count;
                count++;
            }
            colend--;
            if(rowstart<=rowend)
            {
                for(int i=colend;i>=colstart;i--)
                {
                    result[rowend][i]=count;
                    count++;
                }
            }
            rowend--;
            if(colstart<=colend)
            {
                for(int i=rowend;i>=rowstart;i--)
                {
                    result[i][colstart]=count;
                    count++;
                }
            }
            colstart++;
        }
        return result;
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid)
    {
        if(obstacleGrid[0][0]==1)
            return 0;
        obstacleGrid[0][0]=1;
        for(int i=1;i<obstacleGrid.length;i++)
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        for(int i=0;i<obstacleGrid[0].length;i++)
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1]== 1) ? 1 : 0;
        for(int i=1;i<obstacleGrid.length;i++)
        {
            for(int j=1;j<obstacleGrid[0].length;j++)
            {
                if(obstacleGrid[0][0]==0)
                    obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                else
                    obstacleGrid[i][j]=0;
            }
        }
        return obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
    public static String getPermutation(int n, int k)
    {
        List<Integer> numbers=new ArrayList<>();
        int[] factorial=new int[n+1];
        StringBuilder sb=new StringBuilder();
        factorial[0]=1;
        int sum=1;
        for(int i=1;i<=n;i++)
        {
            sum*=i;
            factorial[i]=sum;
        }
        for(int i=1;i<=n;i++)
            numbers.add(i);
        k--;
        for(int i=1;i<=n;i++)
        {
            int index=k/factorial[n-i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k-=index*factorial[n-i];
        }
        return String.valueOf(sb);
    }
    public static int minPathSum(int[][] grid)
    {
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(i==0 && j==0)
                    continue;
                else if(i==0)
                    grid[i][j]+=grid[i][j-1];
                else if(j==0)
                    grid[i][j]+=grid[i-1][j];
                else
                {
                    grid[i][j]+=Math.min(grid[i-1][j],grid[i][j-1]);
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
    public static int[] plusOne(int[] digits)
    {
        for(int i=digits.length-1;i>=0;i--)
        {
            if(digits[i]<9)
            {
                digits[i]++;
                return digits;
            }
            digits[i]=0;
        }
        int []newnumber = new int[digits.length+1];
        newnumber[0]=1;
        return newnumber;
    }
    public static int mySqrt(int x)
    {
        if(x==0)
            return 0;
        if(x==1)
            return 1;
        int start=0;
        int end=x;
        while(start<=end)
        {
            int mid=(start+end)/2;
            if(mid<=x/mid && mid+1>x/(mid+1))
                return mid;
            else if(mid>x/mid)
                end=mid;
            else
                start=mid+1;
        }
        return start;
    }
    public static List<List<Integer>> generate(int numRows)
    {
        List<List<Integer>> result=new ArrayList<>();
        result.add(new ArrayList<Integer>(Arrays.asList(1)));
        for(int i=1;i<numRows;i++)
        {
            for(int j=0;j<=i;j++)
            {
                pascalTriangle(result,i,j);
            }
        }
        return result;
    }
    public static void pascalTriangle(List<List<Integer>> result,int i,int j) {
        if (j == 0 )
            result.add(i,new ArrayList<>(Arrays.asList(1)));
        else if(j==i)
            result.get(i).add(j,1);
        else {
            List<Integer> row = result.get(i);
            int col = j;
            row.add(j, result.get(i - 1).get(j) + result.get(i - 1).get(j - 1));
        }
    }
    public static ListNode rotateRight(ListNode head, int k)
    {
        if (head==null||head.next==null) return head;
        if(k==0)
            return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy;
        int i=0;
        while(fast.next!=null)
        {
            fast=fast.next;
            i++;
        }
        ListNode swapNode=dummy;
        int j=0;
        System.out.println(i-k%i);
        if(i-k%i==0)
            return dummy.next;
        while(j<i-k%i)
        {
            swapNode=swapNode.next;
            j++;
        }
        ListNode temp=swapNode.next;
        swapNode.next=null;
        fast.next=dummy.next;
        dummy.next=temp;
        return dummy.next;
    }
    public static ListNode rotateRight1(ListNode head, int n)
    {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        int i;
        for (i = 0; fast.next != null; i++)//Get the total length
            fast = fast.next;
        System.out.println(i - n % i);
        for (int j = i - n % i; j > 0; j--) //Get the i-n%i th node
            slow = slow.next;

        fast.next = dummy.next; //Do the rotation
        dummy.next = slow.next;
        slow.next = null;

        return dummy.next;
    }
    public static String addBinary(String a, String b)
    {
        int i=a.length()-1,j=b.length()-1;
        int carry=0;
        StringBuilder sb= new StringBuilder();
        while(i>=0 || j>=0)
        {
            int sum=carry;
            if(i>=0)
                sum+=a.charAt(i--)-'0';
            if(j>=0)
                sum+=b.charAt(j--)-'0';
            sb.append(sum%2);
            carry=sum/2;
        }
        if(carry!=0)
            sb.append(carry);
        return sb.reverse().toString();
    }
    public static String simplifyPath(String path)
    {
        int skip=0;
        String[] temp=path.split("/");
        String ans="";
        for(int i=temp.length-1;i>=0;i--)
        {
            if(temp[i].equals("") || temp[i].equals("."))
                continue;
            else if(temp[i].equals(".."))
                skip++;
            else if(skip>0)
                skip--;
            else
                ans="/"+temp[i]+ans;
        }
        return ans == "" ? "/" : ans;
    }
    public static String simplifyPath1(String path)
    {
        String[] temp=path.split("/");
        String[] stack=new String[temp.length];
        int ptr=0;
        for(int i=0;i<temp.length;i++)
        {

            if(temp[i].equals("") || temp[i].equals("."))
                continue;
            else if(temp[i].equals(".."))
            {
                if(ptr>0)
                    ptr--;
            }
            else
            {
                stack[ptr]=temp[i];
                ptr++;
            }
        }

        StringBuilder sb= new StringBuilder();
        for(int i=0;i<ptr;i++)
        {

            sb.append("/");
            sb.append(stack[i]);
        }
        return sb.length()==0?"/":sb.toString();
    }
    public static int removeDuplicates(int[] nums)
    {
        int i=0;
        for(int n:nums)
        {
            if(i<2 || n>nums[i-2])
                nums[i++]=n;
        }
        return i;
    }
    public boolean search(int[] nums, int target)
    {
        if(nums.length<1)
            return false;
        int lo=0,hi=nums.length-1;
        while(lo<=hi)
        {
            int mid=(lo+hi)/2;
            if(nums[mid]==target)
                return true;
            if(nums[mid]<nums[hi] || nums[lo]>nums[mid])
            {
                if(nums[mid]<target && nums[hi]>=target)
                {
                    lo=mid+1;
                }
                else
                    hi=mid-1;
            }
            else if(nums[hi]<nums[mid]||nums[lo]<nums[mid])
            {
                if(target>=nums[lo] && target<nums[mid])
                {
                    hi=mid-1;
                }
                else
                    lo=mid+1;
            }
            else hi--;

        }
        return nums[lo]==target;
    }
    public static void main(String[] args)
    {
        String[] s={"baa", "abcd", "abca", "cab", "cad"};
        alienLexicon(s);
        int nums[]={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println();
        //System.out.println(searchRange(nums,1));
        System.out.println(extremeInsertionIndex1(nums,4));
        char board[][]={{'5','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'}};
        if(isValidSudoku(board))
            System.out.println("True");
        solveSudoku(board);
        System.out.println(totalNQueens(4));
        System.out.println();
        System.out.println();
        System.out.println(multiply("123","456"));
        System.out.println();
        System.out.println();
        int[] ns={1,1,2};
        System.out.println(permuteUnique(ns));
        System.out.println();
        System.out.println();
        String[] str={"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(str));
        System.out.println();
        System.out.println(myPow(2,-2147483648));
        System.out.println(maxSubArray(nums));
        int[] prices={7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
        System.out.println(generateMatrix(3));
        System.out.println(getPermutation(4,14));
        int[] digits={0,9,9};
        System.out.println(plusOne(digits));
        System.out.println(generate(5));
        ListNode node=new ListNode(1);
        node.next=new ListNode(2);
        rotateRight1(node,2);
        System.out.println(addBinary("1010","1011"));
        removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4,4});

    }
}
