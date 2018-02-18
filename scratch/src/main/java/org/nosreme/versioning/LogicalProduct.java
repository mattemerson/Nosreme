package org.nosreme.versioning;

import java.util.Set;

public class LogicalProduct implements Product
{
	private String myName;
	private String myDescription;
	private Version myVersion;
	private Set<ComponentDescriptor> myDependencies;
	private Set<Service> myServices;
	
	private LogicalProduct(String name, String description, Version version, Set<ComponentDescriptor> dependencies, Set<Service> services)
	{
		myName = name;
		myDescription = description;
		myVersion = version;
		myDependencies = dependencies;
		myServices = services;
	}
	
	public static Product newProduct(String name, String description, Version version, Set<ComponentDescriptor> dependencies, Set<Service> services)
	{
		if (name == null)
		{
			throw new IllegalArgumentException("'name' is a required parameter");
		}
		
		if (description == null)
		{
			throw new IllegalArgumentException("'description' is a required parameter");
		}	
		
		if (version == null)
		{
			throw new IllegalArgumentException("'version' is a required parameter");
		}			
		
		if (dependencies == null)
		{
			throw new IllegalArgumentException("'dependencies' is a required parameter");
		}
		
		if (services == null)
		{
			throw new IllegalArgumentException("'services' is a required parameter");
		}	
		
		return new LogicalProduct(name, description, version, dependencies, services);
	}
	
	
	@Override
	public String getName()
	{
		return myName;
	}

	@Override
	public String getDescription()
	{
		return myDescription;
	}

	@Override
	public Version getVersion()
	{
		return myVersion;
	}

	@Override
	public Set<Service> getServices()
	{
		return myServices;
	}
	
	public Set<ComponentDescriptor> getDependencies()
	{
		return myDependencies;
	}
}
