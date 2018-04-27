package com.nosreme.svc.greeting;

public interface GreetingService
{
	Greeting getGreeting(String name);
	
	Greeting getCannedGreeting();
}
