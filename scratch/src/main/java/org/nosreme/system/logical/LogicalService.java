package org.nosreme.system.logical;

import org.nosreme.semver.Version;

public class LogicalService implements Service
{
	private String myName;
	private String myDescription;
	private Version myVersion;
	
	private LogicalService(String name, String description, Version version)
	{
		myName = name;
		myDescription = description;
		myVersion = version;
	}
	
	public static Service newService(String name, String description, Version version)
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
		
		return new LogicalService(name, description, version);
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

}
