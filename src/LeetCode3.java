import java.util.*;

public class LeetCode3
{
    public static String addBoldTag(String s, String[] dict)
    {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0, end = 0; i < s.length(); i++)
        {
            for (String word : dict)
            {
                if (s.startsWith(word, i))
                {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            if (!bold[i])
            {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j])
                j++;
            result.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }

        return result.toString();
    }
    public static String shortestPalindrome(String s)
    {
        int n=s.length();
        int i=0;
        for(int j=n-1;j>=0;j--)
        {
            if(s.charAt(i)==s.charAt(j))
                i++;
        }
        if(i==n)
            return s;
        String sub=s.substring(i,n);
        StringBuffer sb=new StringBuffer(sub);
        return sb.reverse()+shortestPalindrome(s.substring(0,i))+s.substring(i);
    }
    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries)
    {
        Map<String, Map<String,Double>> graph=buildGraph(equations,values);
        double result[]=new double[queries.length];
        for(int i=0;i<result.length;i++)
            result[i]=getPathWeight(queries[i][0],queries[i][1],new HashSet<>(),graph);
        return result;
    }
    public static double getPathWeight(String u,String v,HashSet<String> visited,Map<String,Map<String,Double>> graph)
    {
        if(!graph.containsKey(u))
            return -1.0;
        if(graph.get(u).containsKey(v))
            return graph.get(u).get(v);
        visited.add(u);
        for(Map.Entry<String,Double> n: graph.get(u).entrySet())
        {
            if(visited.contains(n.getKey()))
                continue;
            double prodweight=getPathWeight(n.getKey(),v,visited,graph);
            if(prodweight!=-1.0)
                return prodweight*n.getValue();
        }
        return -1.0;
    }
    public static Map<String,Map<String,Double>> buildGraph(String[][] eq,double[] vals)
    {
        Map<String,Map<String,Double>> result=new HashMap<>();
        for(int i=0;i<vals.length;i++)
        {
            String u=eq[i][0];
            String v=eq[i][1];
            result.putIfAbsent(u,new HashMap<>());
            result.get(u).put(v,vals[i]);
            result.putIfAbsent(v,new HashMap<>());
            result.get(v).put(u,1/vals[i]);
        }
        return result;
    }
    public static void swap(int[] arr,int a,int b)
    {
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }
    public static int partition(int[] arr,int left,int right)
    {
        int pval=arr[right];


        int si=left;
        for(int i=left;i<=right;i++)
        {
            if(arr[i]<pval)
            {
                swap(arr,si,i);
                si++;
            }
        }
        swap(arr,si,right);
        return si;
    }
    public static int quickSelect(int[] arr,int k,int left,int right)
    {

        int pivot=right;
        int pivot_val=partition(arr,left,right);
        if(pivot_val==k)
            return arr[k];
        else if(k<pivot_val)
            return quickSelect(arr,k,left,pivot_val-1);
        else
            return quickSelect(arr,k,pivot_val+1,right);

    }
    public static int partition1(int[] arr,int lo,int hi)
    {
        int pval=arr[hi];
        int i=lo-1;
        for(int j=lo;j<=hi;j++)
        {
            if(arr[j]<pval)
            {
                i++;
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
            }
        }
        i++;
        int t=arr[hi];
        arr[hi]=arr[i];
        arr[i]=t;
        return i;
    }
    public static void quickSort(int[] arr,int lo,int hi)
    {
        if(lo<hi)
        {
            int pi=partition1(arr,lo,hi);
            quickSort(arr,lo,pi-1);;
            quickSort(arr,pi+1,hi);
        }
    }
    public static List<int[]> kSmallestPairs(int[] nums1,int[] nums2,int k)
    {
        PriorityQueue<Map.Entry<int[],Integer>> minHeap=new PriorityQueue<>((o1,o2) -> o1.getValue().compareTo(o2.getValue()));

        Map<int[],Integer> minVals=new HashMap<>();
        for(int i:nums1)
        {
            for(int j:nums2)
            {
                int[] arr={i,j};
                minVals.put(arr,i+j);
            }
        }
        for(Map.Entry<int[],Integer> entry:minVals.entrySet())
            minHeap.add(entry);
        List<int[]> result=new ArrayList<>();
        for(int i=0;i<k;i++)
        {
            if(!minHeap.isEmpty())
                result.add(minHeap.poll().getKey());
        }
        return result;
    }
    public static String compress(String s)
    {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            int count=1;
            int j=i;
            while(j+1<s.length() && s.charAt(j)==s.charAt(j+1))
            {
                count++;
                j++;
            }
            sb.append(s.charAt(i));
            sb.append(count);
            i=j;
        }
        return sb.toString();
    }
    public static String decodeString(String s)
    {
        if(s==null || s.length()==0)
            return "";
        Stack<Integer> countStack=new Stack<>();
        Stack<String> dataStack=new Stack<>();
        dataStack.push("");
        for(int i=0;i<s.length();i++)
        {
            char c =s.charAt(i);
            if(c>='0' && c<='9')
            {
                int j=i;
                while(s.charAt(i+1)>='0' && s.charAt(i+1)<='9')
                    i++;
                countStack.push(Integer.parseInt(s.substring(j,i+1)));
            }
            else if(c=='[')
            {
                dataStack.push("");
            }
            else if(c>='a' && c<='z')
            {
                String st=dataStack.pop();
                st+=String.valueOf(c);
                dataStack.push(st);
            }
            else
            {
                int counter=countStack.pop();
                String data=dataStack.pop();
                String val="";
                for(int k=0;k<counter;k++)
                    val+=data;
                if(dataStack.isEmpty())
                    dataStack.push(val);
                else
                    dataStack.push(dataStack.pop()+val);
            }
        }
        return dataStack.pop();
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        int m=nums1.length,n=nums2.length;
        int len=m+n;
        if(len%2==0)
        {
            double cd1=0,cd2=0;
            int i=0,j=0,count=0;
            while(i<m && j<n)
            {
                if(count==(len+1)/2)
                {
                    return (double) (cd2+Math.min(nums1[i],nums2[j]))/2;
                }
                else if(nums1[i]<nums2[j])
                {
                    cd1=cd2;
                    cd2=nums1[i];
                    i++;
                    count++;
                }
                else if(nums2[j]<nums1[i])
                {
                    cd1=cd2;
                    cd2=nums2[j];
                    j++;
                    count++;
                }
            }
            while(i<m)
            {
                if(count==(len+1)/2)
                {
                    return (double) (cd2+nums1[i])/2;
                }
                else
                {
                    cd1=cd2;
                    cd2=nums1[i];
                    count++;
                    i++;
                }
            }
            while(j<n)
            {
                if(count==(len+1)/2)
                {
                    return (double) (cd2+nums2[j])/2;
                }
                else
                {
                    cd1=cd2;
                    cd2=nums2[j];
                    count++;
                    j++;
                }
            }
        }
        else
        {
            double cd=0;
            int i=0,j=0,count=0;
            while(i<m && j<n)
            {
                if(count==len/2)
                {
                    return Math.min(nums1[i],nums2[j]);
                }
                if(nums1[i]<nums2[j])
                {
                    i++;
                    count++;
                }
                else if(nums2[j]<nums1[i])
                {
                    j++;
                    count++;
                }
            }
            while(i<m)
            {
                if(count==len/2)
                {
                    return Math.min(nums1[i],nums2[j]);
                }
                else
                {
                    count++;
                    i++;
                }
            }
            while(j<n)
            {
                if(count==len/2)
                {
                    return Math.min(nums1[i],nums2[j]);
                }
                else
                {
                    count++;
                    j++;
                }
            }

        }
        return -1.0;
    }

    public static int[] nextGreatest(int[] arr)
    {
        int[] res=new int[arr.length];
        Arrays.fill(res,-1);
        for(int i=0;i<arr.length;i++)
        {
            int count=1;
            int j=i+1;
            for(;j<arr.length;j++)
            {
                if(arr[j]>arr[i])
                {
                    break;
                }
                else
                    count++;
            }
            res[i]=j>=arr.length?res[i]:count;
        }
        return res;
    }
    public static int maxArea(int[] hist)
    {
        int maxArea=0;
        for(int i=0;i<hist.length;i++)
        {
            int minHeight=Integer.MAX_VALUE;
            for(int j=i;j<hist.length;j++)
            {
                minHeight=Math.min(minHeight,hist[j]);
                maxArea=Math.max(maxArea,minHeight*(j-i+1));
            }
        }
        return maxArea;
    }
    public static int maxArea(int[] hist,int start,int end)
    {
        if(start>end)
            return 0;
        int minIndex=start;
        for(int i=start;i<=end;i++)
        {
            if(hist[minIndex]>hist[i])
                minIndex=i;
        }
        return Math.max(hist[minIndex]*(end-start+1),Math.max(maxArea(hist,start,minIndex-1),maxArea(hist,minIndex+1,end)));
    }
    public static int maxAreaStack(int hist[])
    {
        Stack<Integer> st=new Stack<>();
        int i=0;
        int max_area=Integer.MIN_VALUE;
        while(i<hist.length)
        {
            if(st.isEmpty() || hist[st.peek()]<=hist[i])
                st.push(i++);
            else
            {
                int tp=st.peek();
                st.pop();
                int area_with_top=hist[tp]*(st.isEmpty()?i:i-st.peek()-1);
                max_area=Math.max(area_with_top,max_area);

            }
        }
        while(!st.isEmpty())
        {
            int area=hist[st.pop()] * (st.isEmpty()?i:i-st.peek()-1);
            max_area=Math.max(area,max_area);
        }
        return max_area;
    }
    public static List<String> wordBreak(String s,Set<String> wordDict)
    {
        return DFS(s,wordDict,new HashMap<String,LinkedList<String>>());
    }
    public static List<String> DFS(String s,Set<String> wordDict,Map<String,LinkedList<String>> map)
    {
        if(map.containsKey(s))
            return map.get(s);
        LinkedList<String> res=new LinkedList<>();
        if(s.length()==0)
        {
            res.add("");
            return res;
        }
        for(String word:wordDict)
        {
            if(s.startsWith(word))
            {
                List<String> subList = DFS(s.substring(word.length()), wordDict, map);
                for (String substr : subList)
                {
                    res.add(word+(substr.isEmpty()?"":" ")+substr);
                }
            }
        }
        map.put(s,res);
        return res;
    }
    public static boolean isMatch(String s, String p)
    {
        char[] sc=s.toCharArray();
        char[] pc=p.toCharArray();
        boolean[][] dp=new boolean[sc.length+1][pc.length+1];
        dp[0][0]=true;
        for(int i=1;i<pc.length;i++)
        {
            if(pc[i-1]=='*')
                dp[0][i]=dp[0][i-2];
        }
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                if(pc[j-1]=='.' || pc[j-1]==sc[i-1])
                    dp[i][j]=dp[i-1][j-1];
                else if(pc[j-1]=='*')
                {
                    dp[i][j]=dp[i][j-2];
                    if(pc[j-2]=='.' || pc[j-2]==sc[i-1])
                        dp[i][j]=dp[i][j] | dp[i-1][j];

                }
                else
                    dp[i][j]=false;
            }
        }
        return dp[sc.length][pc.length];
    }
    public static void main(String[] args)
    {
        String s="aaabbcc";
        String[] dict={"aaa","aab","bc"};
        addBoldTag(s,dict);
        System.out.println(shortestPalindrome("aacecaaa"));
        String[][] eq={{"a","b"},{"b","c"}};
        double[] vals={2.0,3.0};
        String[][] q={{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};
        double[] result=calcEquation(eq,vals,q);
        int[] arr={3,2,1,5,6,4};
        System.out.println(quickSelect(arr,arr.length-2,0,arr.length-1));
        quickSort(arr,0,arr.length-1);
        System.out.println(arr);
        System.out.println(kSmallestPairs(new int[]{1,7,11},new int[]{2,4,6},3));
        System.out.println(compress("aaabbccd"));
        //System.out.println(decodeString("3[a]2[b4[F]c]"));
        //int[] x = {1, 3, 8, 9, 15};
        //int[] y = {7, 11, 18, 19, 21, 25};
        int[] x={1,2};
        int[] y={3,4};
        System.out.println(findMedianSortedArrays(x,y));
        int[] input={2,3,7,2,1,9};
        System.out.println(Arrays.toString(nextGreatest(input)));
        int[] hist={2,1,5,6,2,3};
        System.out.println(maxArea(hist,0,hist.length-1));
        System.out.println((maxAreaStack(hist)));
        String str="pineapplepenapple";
        String[] wd={"apple", "pen", "applepen", "pine", "pineapple"};
        Set<String> wordDict=new HashSet<>(Arrays.asList(wd));
        System.out.println(wordBreak(str,wordDict));
        System.out.println(isMatch("aa","a*"));
        System.out.println("Last");
    }
}
