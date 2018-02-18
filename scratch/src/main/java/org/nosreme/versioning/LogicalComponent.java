package org.nosreme.versioning;

import java.util.HashSet;
import java.util.Set;

public class LogicalComponent implements Component 
{
	private ComponentDescriptor myDescriptor;
	private Set<Service> myServices = new HashSet<Service>();
	private Set<Component> myDependencies = new HashSet<Component>();
	
	public static Component newComponent(ComponentDescriptor descriptor, Set<Service> services, Set<Component> dependencies)
	{
		if (descriptor == null)
		{
			throw new IllegalArgumentException("'descriptor' is a required parameter");
		}
		
		if (services == null)
		{
			throw new IllegalArgumentException("'services' is a required parameter");
		}
		
		if (dependencies == null)
		{
			throw new IllegalArgumentException("'dependencies' is a required parameter");
		}
		
		return new LogicalComponent(descriptor, services, dependencies);		
	}
	
	private LogicalComponent(ComponentDescriptor descriptor, Set<Service> services, Set<Component> dependencies)
	{
		myDescriptor = descriptor;
		myServices = services;
		myDependencies = dependencies;
	}
		
	
	@Override
	public Set<Component> getDependencies()
	{
		return myDependencies;
	}

	@Override
	public Set<Service> getServices()
	{
		return myServices;
	}

	@Override
	public ComponentDescriptor getDescriptor()
	{
		return myDescriptor;
	}

}
