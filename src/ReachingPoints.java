import java.util.HashSet;
import java.util.Set;

public class ReachingPoints
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

    public boolean reachingPoints(int sx, int sy, int tx, int ty)
    {
        Set<String> set=new HashSet<>();
        return helper(sx,sy,tx,ty,set);
    }
    public boolean helper(int sx, int sy, int tx, int ty,Set<String> set)
    {
        if(sx>tx || sy>ty || set.contains(sx+","+sy))
            return false;
        if(sx==tx && sy==ty)
            return true;

        set.add(sx+","+sy);
        return helper(sx,sx+sy,tx,ty,set) || helper(sx+sy,sy,tx,ty,set);
    }
    public boolean reachingPoints2(int sx, int sy, int tx, int ty)
    {
        while(tx>=sx && ty>=sy)
        {
            if(sx==tx && sy==ty)
                return true;
            if(tx>ty)
                tx-=ty;
            else
                ty-=tx;
        }
        return true;
    }

    public boolean reachingPoints3(int sx, int sy, int tx, int ty)
    {
        while(tx>=sx && ty>=sy)
        {
            if(tx==ty)
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
    public static void main(String[] args)
    {
        ReachingPoints obj=new ReachingPoints();
        System.out.println(obj.reachingPoints3(2,5,19,12));
    }
}
