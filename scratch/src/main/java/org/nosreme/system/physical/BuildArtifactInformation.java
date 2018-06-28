package org.nosreme.system.physical;

import java.util.Set;

public class BuildArtifactInformation
{
	private Build myProductBuild;
	private Set<ComponentArtifact> myComponentArtifacts;
	
	
	public Build getProductBuild()
	{
		return myProductBuild;
	}
	
	public Set<ComponentArtifact> getComponentArtifacts()
	{
		return myComponentArtifacts;
	}
}
