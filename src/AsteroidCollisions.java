import java.util.Stack;

public class AsteroidCollisions
{
    /*
    735
    We are given an array asteroids of integers representing asteroids in a row.

    For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

    Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.



    Example 1:

    Input: asteroids = [5,10,-5]
    Output: [5,10]
    Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
    Example 2:

    Input: asteroids = [8,-8]
    Output: []
    Explanation: The 8 and -8 collide exploding each other.
    Example 3:

    Input: asteroids = [10,2,-5]
    Output: [10]
    Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
    Example 4:

    Input: asteroids = [-2,-1,1,2]
    Output: [-2,-1,1,2]
    Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.


    Constraints:

    1 <= asteroids <= 104
    -1000 <= asteroids[i] <= 1000
    asteroids[i] != 0
     */

    public int[] asteroidCollision(int[] asteroids)
    {
        if(asteroids==null || asteroids.length==0)
            return new int[0];
        Stack<Integer> st=new Stack<>();
        for(int i:asteroids)
        {
            if(i>0)
                st.add(i);
            else
            {
                while(!st.isEmpty() && st.peek()>0 && st.peek()<-i)
                    st.pop();
                if(!st.isEmpty() && st.peek()==-i)
                    st.pop();
                else if(st.isEmpty() || st.peek()<0)
                    st.push(i);
            }
        }
        int[] ret=new int[st.size()];
        int idx=0;
        for(int i:st)
            ret[idx++]=i;
        return ret;
    }

    public static void main(String[] args)
    {
        AsteroidCollisions obj=new AsteroidCollisions();
        System.out.println(obj.asteroidCollision(new int[]{5,10,-5}));
        System.out.println(obj.asteroidCollision(new int[]{8,-8}));
        System.out.println(obj.asteroidCollision(new int[]{-2,-1,1,2}));
        System.out.println(obj.asteroidCollision(new int[]{10,2,-5}));
        System.out.println(obj.asteroidCollision(new int[]{10,2,-12,15}));
    }
}

