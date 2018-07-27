import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SingleObject {
	
	private static SingleObject instance;
	
	private SingleObject()
	{}
	
	public static SingleObject getInstance()
	{
		if(instance==null)
			return new SingleObject();
		return instance;
	}
	
	
	public static void test(int threadsCount) throws InterruptedException
	{
		ExecutorService executor=Executors.newCachedThreadPool();
		final CountDownLatch startFlag=new CountDownLatch(1);
		final CountDownLatch counter=new CountDownLatch(threadsCount);
		
		final Set<String> instanceSet=Collections.synchronizedSet(new HashSet<String>());
		for(int i=0;i<threadsCount;i++)
		{
			executor.submit(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try
					{
						startFlag.await();
					}catch(InterruptedException e){}
					instanceSet.add(SingleObject.getInstance().toString());
					counter.countDown();
				}
				
			});
		}
		
		startFlag.countDown();
		counter.await();
		SingleObject.instance=null;
		if(instanceSet.size()>1)
		{
			System.out.print("{");
			for(String instance : instanceSet)
			{
				System.out.print("["+instance+"]");
			}
			System.out.println("}");
		}
		
		executor.shutdown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int threadCounts = 100;
        int testCounts = 10000;
        for (int i = 0; i < testCounts; i++) {
            test(threadCounts);
        }
	}

}
