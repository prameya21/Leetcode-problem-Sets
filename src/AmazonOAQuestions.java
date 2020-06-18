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



    public static void main(String[] args)
    {
        AmazonOAQuestions obj=new AmazonOAQuestions();
        //Top K Frequently Mentioned Keywords driver
        /*
        {
            List<String> keywords=new ArrayList<>(Arrays.asList("anacell", "betacellular", "cetracular", "deltacellular", "eurocell"));
            List<String> reviews=new ArrayList<>(Arrays.asList("I love anacell Best services; Best services provided by anacell","betacellular has great services","deltacellular provides much better services than betacellular","cetracular is worse than anacell","Betacellular is better than deltacellular."));
            System.out.println(obj.kFrequentlyMentionedKeywords(2,keywords,reviews));
        }
        */

        //Zombies in a matrix driver
        {
            List<List<Integer>> grid=new ArrayList<>();
            grid.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 1, 0, 1})));
            grid.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 0, 1, 0})));
            grid.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 1})));
            grid.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 0, 0, 0})));
            System.out.println(obj.minHours(grid));
        }
    }
}
