import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Vector;

import sun.security.action.GetPropertyAction;


public class Test {
	
	public static int count=0;
	public static void recursion(long a, long b, long c)
	{
		long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
		count++;
		recursion(a,b,c);
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		try
		{
			recursion(0L,0L,0L);
		}catch(Throwable t)
		{
			System.out.println("deep of calling="+count);
			t.printStackTrace();
		}
		
		/*PrivilegedAction pa=new GetPropertyAction("os.name");
		String osName=(String)AccessController.doPrivileged(pa);
		System.out.println(osName);*/	
	}

}
