package com.nosreme.sp.starter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nosreme.sp.greeting.GreetingConfiguration;
import com.nosreme.sp.quote.QuoteConfiguration;

@Configuration
@ComponentScan(basePackageClasses = { GreetingConfiguration.class, QuoteConfiguration.class })
public class StarterConfiguration {

}
