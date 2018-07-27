import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class DemoTask implements Runnable {
	
	private static final AtomicInteger nextId = new AtomicInteger(0);
	private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue()
		{
			return nextId.getAndIncrement();
		}
	};
	
	public int getThreadId()
	{
		return threadId.get();
	}
	
	 private static final ThreadLocal<Date> startDate =
		       new ThreadLocal<Date>() {
		           public Date get() {
		               return new Date();
		           }
		       };
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.printf("Starting Thread: %s : %s\n", getThreadId(), startDate.get());
		
		try
		{
			TimeUnit.SECONDS.sleep(2);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n",
                getThreadId(), startDate.get());
	
	}

	
	public static void main(String ...args)
	{
		Long num=1000001L;
		
		long prefix = Long.parseLong(num.toString().substring(0, 2));
		
		System.out.println("Prefix: "+prefix);
		prefix=prefix+1L;
		System.out.println("Prefix: "+prefix);
		
		String pref="UAT0"+Long.toString(prefix,36).toUpperCase();
		System.out.println(pref);
		String suffix = String.format("%05d", Long.parseLong(num.toString().substring(2)));
		System.out.println("suffix: "+suffix);
		System.out.println(pref+suffix);
		
		
	}
}
