package wolf_J.com.github.common;

import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App 
{
	String value   = null;
	public App(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	public int hashCode() {
		return 0;
		
	}
    public static void main( String[] args )
    {
        Stack<String> stack = new Stack<>();
        stack.push("hello");
        String string = "abc";
        StringBuffer stringBuffer = new StringBuffer("abc");
        System.out.println(stringBuffer.equals(new StringBuffer("abc")));
        System.out.println(stringBuffer.equals("abc"));
        System.out.println(string.equals("abc"));
        System.out.println("abc".getClass());
        Object object = new Object();
        App app1 = new App("abc");
        App app2 = new App("abc");
        System.out.println(app1.equals(app2));
        System.out.println(app1==app2);
    }
}
