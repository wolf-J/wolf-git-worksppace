/**
 * 
 */
package wolf_j.com.github.common;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wolf-J
 *
 */
public class CallableCase {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Long> future = executor.submit(new Callable<Long>() {
			 @Override
		        public Long call() throws Exception {

		            long sum = 0;
		            for (int i = 0; i < 90; i++) {
		                sum += i;
		            }

		            return sum;
		        }
		});
		System.out.println(future.get());
	}

}
