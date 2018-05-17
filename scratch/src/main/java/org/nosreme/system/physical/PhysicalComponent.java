package org.nosreme.system.physical;

import org.nosreme.semver.Version;
import org.nosreme.system.logical.Component;

public class PhysicalComponent implements ComponentArtifact
{
	private Component myComponent;
	private Build myBuild;
	private Version myVersion;
	
	private PhysicalComponent(Component component, Build build, Version version)
	{
		myComponent = component;
		myBuild = build;
		myVersion = version;
	}
	
	public static ComponentArtifact newComponentArtifact(Component component, Build build, Version version)
	{
		if (component == null)
		{
			throw new IllegalArgumentException("'component' is a required parameter");
		}
		
		if (build == null)
		{
			throw new IllegalArgumentException("'build' is a required parameter");
		}
		
		if (version == null)
		{
			throw new IllegalArgumentException("'version' is a required parameter");
		}
		
		return new PhysicalComponent(component, build, version);
	}
		
	@Override
	public Version getVersion()
	{
		return myVersion;
	}

	@Override
	public Build getBuild()
	{
		return myBuild;
	}

	@Override
	public Component getComponent()
	{
		return myComponent;
	}

}
