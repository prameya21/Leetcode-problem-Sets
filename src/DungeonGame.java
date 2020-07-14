import java.util.Arrays;

public class DungeonGame
{
    /*
    The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid.
    Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

    The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

    Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
    other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

    In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

    Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

    For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

    -2 (K)	-3	3
    -5	-10	1
    10	30	-5 (P)


    Note:

    The knight's health has no upper bound.
    Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
     */

    public int calculateMinimumHP(int[][] dungeon)
    {
        if(dungeon==null || dungeon.length==0)
            return 1;
        int[][] dp=new int[dungeon.length][dungeon[0].length];
        for(int i=dp.length-1;i>=0;i--)
        {
            for(int j=dp[0].length-1;j>=0;j--)
            {
                if(i==dp.length-1 && j==dp[0].length-1)
                {
                    dp[i][j]=Math.max(0,0-dungeon[i][j]);
                    continue;
                }
                else if(i==dp.length-1)
                {
                    dp[i][j]=Math.max(0,dp[i][j+1]-dungeon[i][j]);
                    continue;
                }
                else if(j==dp[0].length-1)
                {
                    dp[i][j]=Math.max(0,dp[i+1][j]-dungeon[i][j]);
                    continue;
                }
                else
                {
                    dp[i][j]=Math.max(0,Math.min(dp[i+1][j]-dungeon[i][j],dp[i][j+1]-dungeon[i][j]));
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][0]+1;
    }

    public static void main(String[] args)
    {
        DungeonGame obj=new DungeonGame();
        System.out.println(obj.calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}));
    }
}
