package com.aia.ui.utils;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class AsyncTest {

	public static void test()
	{
		ExecutorService executor=Executors.newSingleThreadExecutor();
				//ExecutorService executor1=Executors.newSingleThreadExecutor();
			/*	Future<String> future=executor.submit( () -> 
				{
					TimeUnit.SECONDS.sleep(3);
					return "hello";
				});*/
				
				CompletableFuture<String> future=CompletableFuture.supplyAsync(new Supplier<String>(){

					@Override
					public String get() {
						// TODO Auto-generated method stub
						try
						{
							TimeUnit.SECONDS.sleep(3);
							System.out.println(Thread.currentThread().getName());
						}catch(InterruptedException e)
						{
							e.printStackTrace();
						}
						return "Hi";
					}
					
				},executor);
				
				future.exceptionally(new Function<Throwable, String>(){

					@Override
					public String apply(Throwable t) {
						// TODO Auto-generated method stub
						System.out.println(t.getMessage());
						return t.getMessage();
					}
					
				}).thenAccept(new Consumer<String>(){

					@Override
					public void accept(String t) {
						// TODO Auto-generated method stub
						System.out.println("accept--"+t);
						System.out.println("accept--"+Thread.currentThread().getName());
					}
					
				});
				
			/*	System.out.println(future.thenAcceptAsync(new Consumer<String>(){

					@Override
					public void accept(String t) {
						// TODO Auto-generated method stub
						System.out.println(t);
						System.out.println(Thread.currentThread().getName());
					}
					
				}).exceptionally(new Function<Throwable,Void>(){

					@Override
					public Void apply(Throwable t) {
						// TODO Auto-generated method stub
						System.out.println(t.getMessage());
						return null;
					}
					
				}));
				*/
				future.completeExceptionally(new Exception("error"));
				System.out.println("Jason");
	}
	public static void main(String ...args) throws InterruptedException, ExecutionException
	{
		ExecutorService executor=Executors.newFixedThreadPool(5); 
		CompletableFuture<String> f1=CompletableFuture.supplyAsync(new Supplier<String>(){

			@Override
			public String get() {
				try
				{
					TimeUnit.SECONDS.sleep(2);
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				System.out.println("f1, over");
				return "Jason Zhu";
			}
			
		});
		
		failAfter(Duration.ofSeconds(2)).acceptEither(f1, (x) -> {
			System.out.println("responseFuture is over successed! the response is " + x);
		}).exceptionally(throwable -> {
	        System.out.println("responseFuture is not over on time!");  
	        return null;  
	    }); ;
	}
	
	public static CompletableFuture<String> calculate(String input)
	{
		ExecutorService excutor=Executors.newFixedThreadPool(5);
		CompletableFuture<String> future=CompletableFuture.supplyAsync(()->{
			System.out.println(input);
			return input+"-----"+input.length();
		},excutor);
		return future;
	}
	
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public static <T> CompletableFuture<T> failAfter(Duration duration)
	{
		final CompletableFuture<T> promise=new CompletableFuture();
		scheduler.schedule(() -> {
			final Exception ex=new Exception("TimeOut afer: "+duration);
			return promise.completeExceptionally(ex);
		}, duration.toMillis(), TimeUnit.MILLISECONDS);
		
		return promise;
	}
}
