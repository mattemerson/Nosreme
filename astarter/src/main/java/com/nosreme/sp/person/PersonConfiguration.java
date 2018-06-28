package com.nosreme.sp.person;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nosreme.sp.greeting.GreetingConfiguration;
import com.nosreme.sp.quote.QuoteConfiguration;

@Configuration
//@ComponentScan(basePackageClasses = { Person.class, PersonRepository.class })
//@ComponentScan(basePackages="com.nosreme.svc.person.*")
public class PersonConfiguration
{
}
