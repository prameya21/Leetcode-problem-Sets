import java.util.*;

public class RemoveInvalidParanthesis
{
    /*
        Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

        Note: The input string may contain letters other than the parentheses ( and ).

        Example 1:

        Input: "()())()"
        Output: ["()()()", "(())()"]
        Example 2:

        Input: "(a)())()"
        Output: ["(a)()()", "(a())()"]
        Example 3:

        Input: ")("
        Output: [""]
     */

    public boolean validString(String s,int len)
    {
        if(s.length()!=len)
            return false;
        int count=0;
        for(char c:s.toCharArray())
        {
            if(c=='(')
                count++;
            else if(c==')')
            {
                count--;
                if(count<0)
                    return false;
            }
        }
        return count==0;
    }
    public int[] getValdiStates(String s)
    {
        int count=0,lcount=0,rcount=0,lerror=0,rerror=0,ctr=0;
        for(char c:s.toCharArray())
        {
            if(c=='(')
            {
                count++;
                lcount++;
            }
            else if(c==')')
            {
                rcount++;
                if(count==0)
                    rerror++;
                else
                    count--;
            }
            else
                ctr++;
        }
        lerror=count;
        lcount=lcount-lerror;
        rcount=rcount-rerror;
        int len=(lcount*2)+ctr;
        return new int[]{lcount,len};
    }

    public List<String> removeInvalidParentheses(String s)
    {
        if(s==null || s.length()==0)
            return new ArrayList<>(Arrays.asList(""));
        //List<String> res=new ArrayList<>();
        Set<String> res=new HashSet<>();
        int[] validStates=getValdiStates(s);

        //helper2(res,s,new HashSet<>(),validStates);
        helper(res,validStates[1],s,0,"");
        if(res.isEmpty())
            res.add("");
        return new ArrayList<>(res);
    }

    public void helper(Set<String> res,int len,String s, int idx, String str)
    {
        if(idx>s.length())
            return;
        if(str.length()>len)
            return;
        if(str.length()==len && validString(str,len))
        {
            res.add(str);
        }
        if(idx<s.length() && s.charAt(idx)!='(' && s.charAt(idx)!=')')
            helper(res,len,s,idx+1,str+s.charAt(idx));
        helper(res,len,s,idx+1,str);
        helper(res,len,s,idx+1,str+(idx<s.length()?s.charAt(idx):""));
    }


    public void helper2(List<String> res,String s,Set<String> set,int len)
    {
        if(validString(s,len))
            res.add(s);
        if(s.length()<len)
            return;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)!='(' && s.charAt(i)!=')')
                continue;
            String t=s.substring(0,i)+s.substring(i+1);
            if(!set.contains(t))
            {
                set.add(t);
                helper2(res,t,set,len);
            }
        }
    }

    public static void main(String[] args)
    {
        RemoveInvalidParanthesis obj=new RemoveInvalidParanthesis();
        System.out.println(obj.removeInvalidParentheses("(a)())()"));
    }








    public List<String> removeInvalidParenthesesBFS(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            if (found) continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }

        return count == 0;
    }










    /*



    */

}
