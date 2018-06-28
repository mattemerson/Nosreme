package org.nosreme.system.logical;

import org.nosreme.semver.Version;

public class ComponentDescriptor implements NamedComponent
{
	private String myName;
	private String myDescription;
	private ComponentType myComponentType;
	private Version myVersion;
	
	private ComponentDescriptor(String name, String description, ComponentType componentType, Version version)
	{
		myName = name;
		myDescription = description;
		myComponentType = componentType;
		myVersion = version;
	}
	
	public static ComponentDescriptor newComponentDescriptor(String name, String description, ComponentType componentType, Version version)
	{
		if (name == null)
		{
			throw new IllegalArgumentException("'name' is a required parameter");
		}
		
		if (description == null)
		{
			throw new IllegalArgumentException("'description' is a required parameter");
		}
		
		if (componentType == null)
		{
			throw new IllegalArgumentException("'componentType' is a required parameter");
		}
		
		if (version == null)
		{
			throw new IllegalArgumentException("'version' is a required parameter");
		}
		
		return new ComponentDescriptor(name, description, componentType, version);
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
	public ComponentType getComponentType()
	{
		return myComponentType;
	}

	@Override
	public Version getVersion()
	{
		return myVersion;
	}

}
