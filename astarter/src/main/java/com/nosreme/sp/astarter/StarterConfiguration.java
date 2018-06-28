package com.nosreme.sp.astarter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nosreme.sp.greeting.GreetingConfiguration;
import com.nosreme.sp.person.PersonConfiguration;
import com.nosreme.sp.quote.QuoteConfiguration;
import com.nosreme.sp.upload.UploadConfiguration;

@Configuration
@ComponentScan(basePackageClasses = { GreetingConfiguration.class, QuoteConfiguration.class, PersonConfiguration.class, UploadConfiguration.class })
public class StarterConfiguration {

}
