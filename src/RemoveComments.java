import java.util.ArrayList;
import java.util.List;

public class RemoveComments
{
    /*
    Remove Comments
     */

    public List<String> removeComments(String[] source)
    {
        if(source==null || source.length==0)
            return new ArrayList<>();
        boolean block=false;
        List<String> result=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        for(String str:source)
        {
            if(!block)
                sb.setLength(0);
            for(int i=0;i<str.length();i++)
            {
                if(!block && i+1<str.length() && str.charAt(i)=='/' && str.charAt(i+1)=='*')
                {
                    block=true;
                    i++;
                }
                else if(block && i+1<str.length() && str.charAt(i)=='*' && str.charAt(i+1)=='/')
                {
                    block=false;
                    i++;
                }
                else if(!block && i+1<str.length() && str.charAt(i)=='/' && str.charAt(i+1)=='/')
                    break;
                else if(!block)
                    sb.append(str.charAt(i));
            }
            if(sb.length()>0 && !block)
                result.add(sb.toString());


        }
        return result;
    }

    public static void main(String[] args)
    {
        RemoveComments obj=new RemoveComments();
        List<String> result=obj.removeComments(new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"});
        for(String str:result)
            System.out.println(str);

        System.out.println("-------------------------------------------------------------------------------");
        result=obj.removeComments(new String[]{"void func(int k) {", "// this function does nothing /*", "   k = k*2/4;", "   k = k/2;*/", "}"});
        for(String str:result)
            System.out.println(str);
    }
}
