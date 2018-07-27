package message.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CacheUtil {

	private static LoadingCache<Integer,Integer> fibonacciCache=CacheBuilder.newBuilder()
			.maximumSize(2)
			.build(new CacheLoader<Integer,Integer>()
					{

						@Override
						public Integer load(Integer i) throws Exception {
							// TODO Auto-generated method stub
							if(i==0)
							{
								return i;
							}
							if(i==1)
								return i;
							System.out.println("Calculating f("+i+")");
							return fibonacciCache.getUnchecked(i-2)+fibonacciCache.getUnchecked(i-1);
						}
						
					});
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++)
		{
			System.out.println("f("+i+")="+fibonacciCache.getUnchecked(i));
		}
	}

}
