public class StudentAttendanceRecordII
{
    /*
        An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
        'A': Absent.
        'L': Late.
        'P': Present.

        Any student is eligible for an attendance award if they meet both of the following criteria:
        The student was absent ('A') for strictly fewer than 2 days total.
        The student was never late ('L') for 3 or more consecutive days.
        Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.

        Example 1:
        Input: n = 2
        Output: 8
        Explanation: There are 8 records with length 2 that are eligible for an award:
        "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
        Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).

        Example 2:
        Input: n = 1
        Output: 3
        Example 3:

        Input: n = 10101
        Output: 183236316
     */

    int mod=(int)1e9+7;
    int MOD=1000000007;
    public int checkRecord(int n)
    {
        if(n==0)
            return 0;

        Integer[][][] memo=new Integer[n+1][2][3];
        return helper(memo,n,0,0);
    }

    public int helper(Integer[][][] memo, int n, int absent, int late)
    {
        if(absent>=2 || late>=3)
            return 0;
        if(n==0)
            return 1;
        if(memo[n][absent][late]!=null)
            return memo[n][absent][late];


        int count = 0;
        // We choose 'P' for the current position.
        count = helper(memo,n - 1, absent, 0) % MOD;
        // We choose 'A' for the current position.
        count = (count + helper(memo, n - 1, absent + 1, 0)) % MOD;
        // We choose 'L' for the current position.
        count = (count + helper(memo, n - 1, absent, late + 1)) % MOD;

        memo[n][absent][late]=count;
        return count;
    }

    public static void main(String[] args)
    {
        StudentAttendanceRecordII obj=new StudentAttendanceRecordII();
        System.out.println(obj.checkRecord(2));
        System.out.println(obj.checkRecord(1));
        System.out.println(obj.checkRecord(10101));
    }
}
