import java.util.LinkedList;

public class SnakeGame
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
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    int width,height,foodIndex;
    int[][] food;
    LinkedList<int[]> snake;
    public SnakeGame(int width, int height, int[][] food)
    {
        snake=new LinkedList<>();
        snake.add(new int[]{0,0});
        this.width=width;
        this.height=height;
        foodIndex=0;
        this.food=food;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction)
    {
        int x=snake.get(0)[0];
        int y=snake.get(0)[1];
        if(direction.equals("R"))
            y+=1;
        else if(direction.equals("L"))
            y-=1;
        else if(direction.equals("U"))
            x-=1;
        else if(direction.equals("D"))
            x+=1;
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
            return foodIndex;
        }
        return foodIndex;
    }
    public boolean collision(int x, int y, LinkedList<int[]> snake)
    {
        for(int[] i:snake)
        {
            if(i[0]==x && i[1]==y)
                return true;
        }
        return false;
    }
    public static void main(String[] args)
    {
        SnakeGame obj=new SnakeGame(3,2,new int[][]{{1,2},{0,1}});
        System.out.println(obj.move("R"));
        System.out.println(obj.move("D"));
        System.out.println(obj.move("R"));
        System.out.println(obj.move("U"));
        System.out.println(obj.move("L"));
        System.out.println(obj.move("U"));
    }
}
