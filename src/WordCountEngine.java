import java.util.*;

public class WordCountEngine
{
    /*
        Implement a document scanning function wordCountEngine, which receives a string document and returns a list of all unique words in it and their number of occurrences, sorted by the number of occurrences in a descending order.
        If two or more words have the same count, they should be sorted according to their order in the original sentence. Assume that all letters are in english alphabet.
        Your function should be case-insensitive, so for instance, the words “Perfect” and “perfect” should be considered the same word.

        The engine should strip out punctuation (even in the middle of a word) and use whitespaces to separate words.

        Analyze the time and space complexities of your solution. Try to optimize for time while keeping a polynomial space complexity.

        Examples:

        input:  document = "Practice makes perfect. you'll only
        get Perfect by practice. just practice!"

        output: [ ["practice", "3"], ["perfect", "2"],
        ["makes", "1"], ["youll", "1"], ["only", "1"],
        ["get", "1"], ["by", "1"], ["just", "1"] ]
        Important: please convert the occurrence integers in the output list to strings (e.g. "3" instead of 3).
        We ask this because in compiled languages such as C#, Java, C++, C etc., it’s not straightforward to create mixed-type arrays (as it is, for instance, in scripted languages like JavaScript, Python, Ruby etc.).
        The expected output will simply be an array of string arrays.
     */

    public String[][] wordCountEngine(String document)
    {
        String[] data=buildArray(document);
        Map<String,Integer> map=buildMap(data);
        int maxFreq=0;
        for(String key:map.keySet())
            maxFreq=Math.max(maxFreq,map.get(key));
        Set<String> visited=new HashSet<>();
        List<String>[] temp=new ArrayList[maxFreq+1];

        for(int i=0;i<temp.length;i++)
            temp[i]=new ArrayList<String>();

        for(String s:data)
        {
            if(!visited.contains(s))
            {
                visited.add(s);
                int freq=map.get(s);
                temp[freq].add(s);
            }
        }

        String[][] res=new String[map.size()][2];
        int j=0;
        for(int i=temp.length-1;i>=0;i--)
        {
            if(temp[i]!=null && !temp[i].isEmpty())
            {
                for(String val:temp[i])
                {
                    res[j][0]=val;
                    res[j][1]=String.valueOf(map.get(res[j][0]));
                    j++;
                }
            }
        }
        return res;

    }

    public Map<String,Integer> buildMap(String[] data)
    {
        Map<String,Integer> map=new HashMap<>();
        for(String s:data)
            map.put(s,map.getOrDefault(s,0)+1);
        return map;
    }

    public String[] buildArray(String s)
    {
        s=s.trim();
        List<String> res=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        char[] ch=s.toCharArray();
        for(int i=0;i<ch.length;i++)
        {
            if(ch[i]==' ' || i==ch.length-1)
            {
                if(i==ch.length-1)
                {
                    sb.append(Character.isLetter(ch[i])?ch[i]:"");
                }
                res.add(sb.toString().toLowerCase());
                sb=new StringBuilder();
            }
            else if(!Character.isLetter(ch[i]))
                continue;
            else
                sb.append(ch[i]);

        }
        //System.out.print(res);
        return res.toArray(new String[res.size()]);
    }

    public static void main(String[] args)
    {
        WordCountEngine obj=new WordCountEngine();
        String[][] res=obj.wordCountEngine("Every book is a quotation; and every house is a quotation out of all forests, and mines, and stone quarries; and every man is a quotation from all his ancestors. ");
        System.out.println(Arrays.deepToString(res));
    }

}
