package org.nosreme.versioning;

import java.util.Set;

public class PhysicalProduct implements ProductArtifact
{
	private Product myProduct;
	private Build myBuild;
	private Version myVersion;
	private Set<ComponentArtifact> myDependents;
	
	private PhysicalProduct(Product product, Build build, Version version, Set<ComponentArtifact> dependents)
	{
		myProduct = product;
		myBuild = build;
		myVersion = version;
		myDependents = dependents;
	}
	
	public static ProductArtifact newPhysicalArtifact(Product product, Build build, Version version, Set<ComponentArtifact> dependents)
	{
		if (product == null)
		{
			throw new IllegalArgumentException("'product' is a required parameter");
		}
		
		if (build == null)
		{
			throw new IllegalArgumentException("'build' is a required parameter");
		}
		
		if (version == null)
		{
			throw new IllegalArgumentException("'version' is a required parameter");
		}

		if (dependents == null)
		{
			throw new IllegalArgumentException("'dependents' is a required parameter");
		}
		
		return new PhysicalProduct(product, build, version, dependents);
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
	public Product getProduct()
	{
		return myProduct;
	}

	@Override
	public Set<ComponentArtifact> getDependents()
	{
		return myDependents;
	}
	
}
