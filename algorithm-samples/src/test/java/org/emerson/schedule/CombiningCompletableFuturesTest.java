package org.emerson.schedule;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * This section covers how to put two <code>CompletableFuture</code>s together.
 * 
 * @Link https://www.callicoder.com/java-8-completablefuture-tutorial/
 * @author MEmerson
 *
 */
public class CombiningCompletableFuturesTest {

	/**
	 * In earlier examples, the Supplier function passed to thenApply() callback
	 * would return a simple value but in this case, it is returning a
	 * CompletableFuture. Therefore, the final result in the above case is a nested
	 * CompletableFuture. This is not what we want.
	 */
	@Test
	public void testCombineTwoCompletableFUturesUsingThenApplyShouldPass() {
		String userId = "42";
		CompletableFuture<CompletableFuture<Double>> result = getUserDetail(userId)
				.thenApply(user -> getCreditRating(user));
	}

	/**
	 * If you want the final result to be a top-level Future, use thenCompose()
	 * method instead
	 * 
	 * thenCompose is used to combine two futures when the results arer dependent on
	 * one another.
	 * 
	 * So, Rule of thumb here - If your callback function returns a
	 * CompletableFuture, and you want a flattened result from the CompletableFuture
	 * chain (which in most cases you would), then use thenCompose().
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 * @throws NumberFormatException
	 */
	@Test
	public void testCombineTwoCompletableFuturesUsingThenComposeShoudlPass()
			throws NumberFormatException, InterruptedException, ExecutionException {

		String userId = "42";
		CompletableFuture<Double> result = getUserDetail(userId).thenCompose(user -> getCreditRating(user));

		Assert.assertEquals(Double.valueOf(userId), result.get());
	}

	/**
	 * While thenCompose() is used to combine two Futures where one future is
	 * dependent on the other, thenCombine() is used when you want two Futures to
	 * run independently and do something after both are complete.
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void testCombineTwoCompletableFuturesUsingThenCombinShouldPass()
			throws InterruptedException, ExecutionException {

		// Again, in this case we are doing two independent things, and then combining
		// the results

		// This first part gets your weight in KG
		System.out.println("Retrieving weight.");
		CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return 65.0;
		});

		// This second part gets your height in CM
		System.out.println("Retrieving height.");
		CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return 177.8;
		});

		// This last part combines the two and uses a BiFunction functional interface to
		// put it all together.
		// Presumably, the input order is driven by the order of futures
		// The combine will ONLY be called when both Futures are complete
		System.out.println("Calculating BMI.");
		CompletableFuture<Double> combinedFuture = weightInKgFuture.thenCombine(heightInCmFuture,
				(weightInKg, heightInCm) -> {
					Double heightInMeter = heightInCm / 100;
					return weightInKg / (heightInMeter * heightInMeter);
				});

		System.out.println("Your BMI is - " + combinedFuture.get());
	}

	/**
	 * We used thenCompose() and thenCombine() to combine two CompletableFutures
	 * together. Now, what if you want to combine an arbitrary number of
	 * CompletableFutures? Well, you can use the following methods to combine any
	 * number of CompletableFutures -
	 * 
	 * static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs) static
	 * CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 * 
	 */

	/*
	 * CompletableFuture.allOf is used in scenarios when you have a List of
	 * independent futures that you want to run in parallel and do something after
	 * all of them are complete.
	 * 
	 * Let’s say that you want to download the contents of 100 different web pages
	 * of a website. You can do this operation sequentially but this will take a lot
	 * of time. So, you have written a function which takes a web page link, and
	 * returns a CompletableFuture, i.e. It downloads the web page’s content
	 * asynchronously -
	 * 
	 * Now, when all the web pages are downloaded, you want to count the number of
	 * web pages that contain a keyword - ‘CompletableFuture’. Let’s use
	 * CompletableFuture.allOf() to achieve this
	 */
	@Test
	public void testCountingAnArbitraryNumberOfWebPagesUsingAsynchAndAllOfShouldPass()
			throws InterruptedException, ExecutionException {
		// Build up a 100 pages
		List<String> pageLinks = IntStream.iterate(1, n -> n + 1).limit(100).mapToObj(n -> {
			return "link-" + n;
		}).collect(Collectors.toList());

		// Build up our futures.
		List<CompletableFuture<String>> downloadWebPage = pageLinks.stream()
				.map(CombiningCompletableFuturesTest::downloadWebPage).collect(Collectors.toList());

		// Create a combined Future using allOf()
		// The problem with CompletableFuture.allOf() is that it returns
		// CompletableFuture<Void>. But we can get the results of all the wrapped
		// CompletableFutures by writing few additional lines of code -
		CompletableFuture<Void> allFutures = CompletableFuture
				.allOf(downloadWebPage.toArray(new CompletableFuture[downloadWebPage.size()]));

		// When all the Futures are completed, call `future.join()` to get their results
		// and collect the results in a list -
		// Since we’re calling future.join() when all the futures are complete, we’re
		// not blocking anywhere :-)
		// The join() method is similar to get(). The only difference is that it throws
		// an unchecked exception if the underlying CompletableFuture completes
		// exceptionally.

		// This should return the list of page content
		CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
			return downloadWebPage.stream().map(pageContentFuture -> pageContentFuture.join())
					.collect(Collectors.toList());
		});

		// Count the number of web pages having "cool" keyword.
		CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
			return pageContents.stream().filter(pageContent -> pageContent.contains("cool")).count();
		});

		Long count = countFuture.get();
		System.out.println("Number of Web Pages having CompletableFuture keyword - " + count);

		Assert.assertEquals(19L, count.longValue());
	}

	/*
	 * CompletableFuture.anyOf() as the name suggests, returns a new
	 * CompletableFuture which is completed when any of the given CompletableFutures
	 * complete, with the same type of result.
	 * 
	 * In the above example, the anyOfFuture is completed when any of the three
	 * CompletableFutures complete. Since future2 has the least amount of sleep
	 * time, it will complete first, and the final result will be - Result of Future
	 * 2.
	 * 
	 * CompletableFuture.anyOf() takes a varargs of Futures and returns
	 * CompletableFuture<Object>. The problem with CompletableFuture.anyOf() is that
	 * if you have CompletableFutures that return results of different types, then
	 * you won’t know the type of your final CompletableFuture.
	 */
	@Test
	public void testProcessingAnnArbitraryNumberOfFuturesAndAnyOfThemCountOfShouldPass()
			throws InterruptedException, ExecutionException {

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of Future 1";
		});

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of Future 2";
		});

		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of Future 3";
		});

		// This is kind of a pain....
		// CompletableFuture.anyOf() takes a varargs of Futures and returns
		// CompletableFuture<Object>. The problem with CompletableFuture.anyOf() is that
		// if you have CompletableFutures that return results of different types, then
		// you won’t know the type of your final CompletableFuture.
		CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

		System.out.println(anyOfFuture.get()); // Result of Future 2 since it had the lowest latency
	}

	/**
	 * This section describes how to handle exceptions in asynchronous methods.
	 * 
	 * We explored How to create CompletableFuture, transform them, and combine
	 * multiple CompletableFutures. Now let’s understand what to do when anything
	 * goes wrong.
	 * 
	 * Let’s first understand how errors are propagated in a callback chain.
	 * Consider the following CompletableFuture callback chain -
	 * 
	 * CompletableFuture.supplyAsync(() -> { // Code which might throw an exception
	 * return "Some result"; }).thenApply(result -> { return "processed result";
	 * }).thenApply(result -> { return "result after further processing";
	 * }).thenAccept(result -> { // do something with the final result });
	 * 
	 * If an error occurs in the original supplyAsync() task, then none of the
	 * thenApply() callbacks will be called and future will be resolved with the
	 * exception occurred. If an error occurs in first thenApply() callback then 2nd
	 * and 3rd callbacks won’t be called and the future will be resolved with the
	 * exception occurred, and so on.
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test // (expected=IndexOutOfBoundsException.class)
	public void testHandleExceptionsWithExceptionallyCallbackShouldPass()
			throws InterruptedException, ExecutionException {
		// The exceptionally() callback gives you a chance to recover from errors
		// generated from the original Future. You can log the exception here and return
		// a default value.

		//Note that, the error will not be propagated further in the callback chain if you handle it once.
		Integer age = -1;

		CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
			if (age < 0) {
				throw new IllegalArgumentException("Age can not be negative");
			}
			if (age > 18) {
				return "Adult";
			} else {
				return "Child";
			}
		}).exceptionally(ex -> {
			System.out.println("Oops! We have an exception - " + ex.getMessage());
			return "Unknown!";
		});

		String maturity = maturityFuture.get();

		Assert.assertEquals("Unknown!", maturity);
	}

	/*
	 * The API also provides a more generic method - handle() to recover from exceptions. It is called whether or not an exception occurs.
	 * 
	 * If an exception occurs, then the res argument will be null, otherwise, the ex argument will be null.
	 */
	@Test
	public void testHandleExceptionsUsingTheGenericHandleMethodShouldFail() throws InterruptedException, ExecutionException {

		Integer age = -1;

		CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
		    if(age < 0) {
		        throw new IllegalArgumentException("Age can not be negative");
		    }
		    if(age > 18) {
		        return "Adult";
		    } else {
		        return "Child";
		    }
		}).handle((res, ex) -> {
		    if(ex != null) {
		        System.out.println("Oops! We have an exception - " + ex.getMessage());
		        return "Unknown!";
		    }
		    return res;
		});

		String maturity = maturityFuture.get();		
		System.out.println("Maturity : " + maturityFuture.get());
		Assert.assertEquals("Unknown!", maturity);
	}

	private static CompletableFuture<String> downloadWebPage(String pageLink) {
		return CompletableFuture.supplyAsync(() -> {
			// Code to download and return the web page's content
			// In general, this could do something really non-trivial
			if (pageLink.contains("2")) // every other one
				return "link:" + pageLink + " content:" + "cool content";
			else
				return "link:" + pageLink + " content:" + "content";
		});
	}

	/**
	 * Mocks a remote call to the userDetails service
	 * 
	 * @param userId
	 * @return
	 */
	private CompletableFuture<User> getUserDetail(String userId) {
		return CompletableFuture.supplyAsync(() -> {
			return new UserService().getUser(userId);
		});
	}

	/**
	 * Mocks a remote call to the creditrating service
	 * 
	 * @param user
	 * @return
	 */
	private CompletableFuture<Double> getCreditRating(User user) {
		return CompletableFuture.supplyAsync(() -> {
			return new CreditRatingService().getCreditRating(user);
		});
	}

	public class UserService {
		public User getUser(String userId) {
			return new User(userId, "name-" + userId);
		}
	}

	public class CreditRatingService {
		public Double getCreditRating(User user) {
			return Double.valueOf(user.getId());
			// return new CreditRating(user, 42);
		}
	}

	public class User {
		private String id;
		private String name;

		public User(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}

	public class CreditRating {
		private int rating;
		private User user;

		public CreditRating(User user, int rating) {
			this.user = user;
			this.rating = rating;
		}

		public User getUser() {
			return this.user;
		}

		public int getRating() {
			return this.rating;
		}
	}
}
