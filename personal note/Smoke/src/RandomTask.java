import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;


public class RandomTask {

	private static final long COUNT=10000000;
	private static final long THREADS=2;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static class RanTask implements Runnable
	{
		private final Random rnd;
		protected final int id;
		private final long cnt;
		private final CountDownLatch latch;
		
		private RanTask(Random rnd, int id, long cnt, CountDownLatch latch)
		{
			this.rnd=rnd;
			this.id=id;
			this.cnt=cnt;
			this.latch=latch;
		}
		
		protected Random getRandom()
		{
			return this.rnd;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try
			{
				final Random r=getRandom();
				latch.countDown();
				latch.await();
				final long start = System.currentTimeMillis();
				int sum=0;
				for(long j=0; j<cnt;++j)
				{
					sum+=r.nextInt();
				}
				final long time=System.currentTimeMillis()-start;
				System.out.println( "Thread #" + id + " Time = " + time / 1000.0 + " sec, sum = " + sum );
			}catch(InterruptedException e)
			{}
		}
		
		private static void testRandom(final int threads, final long cnt)
		{
			final CountDownLatch latch=new CountDownLatch(threads);
			final Random r=new Random(100);
			for(int i=0;i<threads;++i)
			{
				final Thread thread=new Thread(new RanTask(r,i,cnt,latch));
				thread.start();
			}
		}
		
		private static void testRamdonArray(final int threads, final long cnt, final int padding)
		{
			final CountDownLatch latch=new CountDownLatch(threads);
			final Random[] rnd=new Random[threads*padding];
			for(int i=0;i<threads*padding;++i)
			{
				rnd[i]=new Random(100);
			}
			
			for(int i=0;i<threads;++i)
			{
				final Thread thread = new Thread(new RanTask(rnd[i*padding],i,cnt,latch));
				thread.start();
			}
		}
		
		private static void testTLRandom(final int threads, final long cnt)
		{
			final CountDownLatch latch=new CountDownLatch(threads);
			for(int i=0;i<threads;++i)
			{
				final Thread thread = new Thread(new RanTask(null,i,cnt,latch){
					@Override
					protected Random getRandom()
					{
						return ThreadLocalRandom.current();
						
					}
				});
				thread.start();
			}
		}
		
		
		
	}
	
	
}
