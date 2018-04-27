package com.nosreme.sp.greeting;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.nosreme.sp.greeting.protocol.rest,com.nosreme.svc.greeting")
public class GreetingConfiguration {

}
