package org.emerson.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner
{
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private SampleRunner sampleRunner;
    
    public static void main(String args[])
    {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(ApplicationArguments args)
       throws Exception
	{
		LOG.info("START: Running Samples");
		sampleRunner.runSamples();
		LOG.info("FINISH: Running Samples");
	}
}