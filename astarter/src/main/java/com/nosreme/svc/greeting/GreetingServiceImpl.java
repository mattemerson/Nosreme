package com.nosreme.svc.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(GreetingProperties.class)
public class GreetingServiceImpl implements GreetingService
{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
   
    private final GreetingProperties greetingProperties;

    public GreetingServiceImpl(GreetingProperties greetingProperties) {
        this.greetingProperties = greetingProperties;
    }

    public String message() {
        return this.greetingProperties.getMessage();
    }
        
	@Override
	public Greeting getGreeting(String name)
	{
	    return new Greeting(counter.incrementAndGet(),
	            String.format(template, name));
	}

	@Override
	public Greeting getCannedGreeting() {
	    return new Greeting(counter.incrementAndGet(),message());
	}
}
