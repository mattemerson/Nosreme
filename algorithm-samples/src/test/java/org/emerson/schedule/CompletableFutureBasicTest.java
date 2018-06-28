package org.emerson.schedule;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;



/**
 * The purpose of this is just to show the basic notes and bolts of the CompletableFuture class.  We'll add more in other tests.
 * 
 * @Link https://www.callicoder.com/java-8-completablefuture-tutorial/
 * @author memerson
 *
 */
public class CompletableFutureBasicTest {

	@Test
	public void testSimplestCompletableFutureShouldPass() throws InterruptedException, ExecutionException
	{
		// Can just build one
		CompletableFuture<String> completableFuture = new CompletableFuture<String>();
		
		// Watch me manually complete
		completableFuture.complete("done");
		
		// This would block forever if I hadn't manually completed
		String result = completableFuture.get();
		
		Assert.assertEquals("done", result);
	}
	
	/**
	 * Simple asynchronous operation that has no return value, but we want to wait for it to do its thing
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Test	
	public void testRunningAsynchronousOperationUsingRunnableShouldPass() throws InterruptedException, ExecutionException
	{
		// Run a task specified by a Runnable Object asynchronously.
		// Anonymous flavor
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
		    @Override
		    public void run() {
		        // Simulate a long-running Job
		        try {
		            TimeUnit.SECONDS.sleep(1);
		        } catch (InterruptedException e) {
		            throw new IllegalStateException(e);
		        }
		        System.out.println("I'll run in a separate thread than the main thread.");
		    }
		});

		// Block and wait for the future to complete
		future.get();
	}

	/**
	 * Simple asynchronous operation that has no return value, but we want to wait for it to do its thing
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Test
	public void testRunningAsynchronousOperationUsingLamdaShouldPass() throws InterruptedException, ExecutionException
	{
		// Using Lambda Expression
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		    // Simulate a long-running Job   
		    try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		        throw new IllegalStateException(e);
		    }
		    System.out.println("I'll run in a separate thread than the main thread.");
		});
		
		future.get();
	}
	
	/**
	 * As above but using the Supplier functional interface to get something back
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Test
	public void testRunningAsynchOperationUsingSupplierShouldPass() throws InterruptedException, ExecutionException
	{
		// Run a task specified by a Supplier object asynchronously
		/*
		CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
		    @Override
		    public String get() {
		        try {
		            TimeUnit.SECONDS.sleep(1);
		        } catch (InterruptedException e) {
		            throw new IllegalStateException(e);
		        }
		        return "done";
		    }
		});
		 */
		
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		        try {
		            TimeUnit.SECONDS.sleep(1);
		        } catch (InterruptedException e) {
		            throw new IllegalStateException(e);
		        }
		        return "done";
		    }
		);
		
		// Block and get the result of the Future
		String result = future.get();
		Assert.assertEquals("done", result);
	}
	
	/**
	 * This is like above, but it uses my own thread pool rather than ForkJoinPool.commonPool()
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void testRunningAsynchOperationUsingSupplierAndMyOwnThreadPoolShouldPass() throws InterruptedException, ExecutionException
	{
		Executor executor = Executors.newFixedThreadPool(10);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		        throw new IllegalStateException(e);
		    }
		    return "done";
		}, executor);
		
		// Block and get the result of the Future
		String result = future.get();
		Assert.assertEquals("done", result);
	}
}
