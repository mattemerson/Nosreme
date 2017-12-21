package biz.matt.scheduler.driver;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

import biz.matt.scheduler.JobScheduler;
import biz.matt.scheduler.scheduler.JobPriority;
import biz.matt.scheduler.scheduler.JobScheduleConfig;
import biz.matt.scheduler.scheduler.JobScheduleFactory;
import biz.matt.scheduler.scheduler.ScheduleOption;
import biz.matt.scheduler.state.DefaultJobStateConfig;
import biz.matt.scheduler.state.JobStateConfig;
import biz.matt.scheduler.time.SystemTime;
import biz.matt.scheduler.work.ScheduledJobFactory;
import biz.matt.scheduler.work.ScheduledRunnable;
import biz.matt.scheduler.worker.WorkerPoolConfiguration;
import biz.matt.scheduler.worker.WorkerPoolManager;
import biz.matt.scheduler.worker.WorkerPoolManagerConfiguration;

public class JobSchedulerDriver {

	public JobSchedulerDriver()
	{
	}

	public static void main(String[] args)
	{
		JobSchedulerDriver driver = new JobSchedulerDriver();
		driver.test();

	}

	public void test()
	{
		System.out.println("TEST SUITE: BEGIN");
		SystemTime systemTime = SystemTime.newInstance().with("America/New_York");
		
		WorkerPoolManager workerPoolManager = newWorkerPoolManager(systemTime);		
		JobStateConfig jobStateConfig = new DefaultJobStateConfig();	
		ScheduledJobFactory jobFactory = new ScheduledJobFactory(jobStateConfig, systemTime, workerPoolManager);
		JobScheduleFactory jobScheduleFactory = newJobSchedulerFactory(systemTime);
		JobScheduler jobScheduler = new JobScheduler(workerPoolManager, jobScheduleFactory, jobFactory);
		
		System.out.println("Now=" + systemTime.now() + "'");
		// These are the run-only-once jobs
		//testSchedule(jobScheduler, new SimpleRunnable(), newRunOnlyOnceOnSubmissionConfig(), 10);
		//testSchedule(jobScheduler, new SimpleRunnable(), newRunAtScheduledTimeConfig(systemTime, 5), 10);
		
		// These are the run-periodically jobs
		testSchedule(jobScheduler, new SimpleScheduledRunnable(), newRunPeriodicallyConfig(10), 30);
		workerPoolManager.stopPools();
		
		System.out.println("TEST SUITE: END");
	}
	
	private JobScheduleConfig newRunPeriodicallyConfig(int durationInSeconds)
	{
		JobScheduleConfig config = new JobScheduleConfig();
		config.setScheduleOption(ScheduleOption.RUN_WITH_FIXED_RATE);
		config.setPeriod(Duration.ofSeconds(durationInSeconds));
		config.setMaxIterations(2);
		
		return config;
	}
	
	public class SimpleScheduledRunnable implements ScheduledRunnable
	{
		private int myCounter = 0;
		
		@Override
		public Runnable newRunnable(ZonedDateTime scheduledTime)
		{
			CounterRunnable runnable = new CounterRunnable(myCounter, scheduledTime);
			myCounter++;
			
			return runnable;
		}
		
	}
	
	public class CounterRunnable implements Runnable
	{
		private ZonedDateTime myZonedDateTime;
		private int myCounter;
		
		public CounterRunnable(int counter, ZonedDateTime zonedDateTime)
		{
			myCounter = counter;
			myZonedDateTime = zonedDateTime;
		}
		
		@Override
		public void run()
		{
			System.out.println("(Counter,scheduledTime)=('" + myCounter + "','" + myZonedDateTime + "')");
		}
		
	}
	
	private void testSchedule(JobScheduler jobScheduler, ScheduledRunnable scheduledRunnable, JobScheduleConfig config, int howLongInSecnodsToWait)
	{
		System.out.println("TEST: PERIODIC BEGIN");
		jobScheduler.submit(scheduledRunnable, config);
		
		try {
			TimeUnit.SECONDS.sleep(howLongInSecnodsToWait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("TEST: PERIODIC END");
	}
		
	private void testSchedule(JobScheduler jobScheduler, Runnable bizWork, JobScheduleConfig jobScheduleConfig, int howLongInSecondsToWait)
	{
		System.out.println("TEST: BEGIN");
		
		jobScheduler.submit(bizWork, jobScheduleConfig);
		
		try {
			TimeUnit.SECONDS.sleep(howLongInSecondsToWait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TEST: END");		
	}
	
	
	private JobScheduleConfig newRunOnlyOnceOnSubmissionConfig()
	{
		JobScheduleConfig config = new JobScheduleConfig();
		config.setScheduleOption(ScheduleOption.RUN_ONLY_ONCE_ON_SUBMISSION);
		
		return config;
	}
	
	private JobScheduleConfig newRunAtScheduledTimeConfig(SystemTime systemTime, int secondsToWait)
	{
		ZonedDateTime now = systemTime.now();
		ZonedDateTime scheduledTime = now.plusNanos(TimeUnit.SECONDS.toNanos(secondsToWait));
		
		JobScheduleConfig config = new JobScheduleConfig();
		config.setScheduleOption(ScheduleOption.RUN_ONLY_ONCE_AT_SCHEDULED_TIME);
		config.setScheduledTime(scheduledTime);
		
		return config;
	}
	
	
	private class SimpleRunnable implements Runnable
	{
		@Override
		public void run() {
			System.out.println("Don't mess with Texas");
			
		}
	}
	
	private JobScheduleFactory newJobSchedulerFactory(SystemTime systemTime)
	{
		return new JobScheduleFactory(systemTime);
	}
	
	private WorkerPoolManager newWorkerPoolManager(SystemTime systemTime)
	{		
		WorkerPoolManagerConfiguration workerPoolManagerConfiguration = new WorkerPoolManagerConfiguration();
		workerPoolManagerConfiguration.addWorkPool(new WorkerPoolConfiguration(JobPriority.HIGH, 1, systemTime));
		workerPoolManagerConfiguration.addWorkPool(new WorkerPoolConfiguration(JobPriority.NORMAL, 1, systemTime));
		workerPoolManagerConfiguration.addWorkPool(new WorkerPoolConfiguration(JobPriority.LOW, 1, systemTime));
				
		WorkerPoolManager workerPoolManager = WorkerPoolManager.newWorkQueueManager(workerPoolManagerConfiguration);
	
		return workerPoolManager;
	}
	
}
