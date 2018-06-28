package org.nosreme.system.physical;

import java.time.LocalDateTime;
import java.util.Set;

import org.nosreme.semver.AlterVersion;
import org.nosreme.semver.Version;
import org.nosreme.system.logical.Product;

public class BuildProcessImpl implements BuildProcess
{
	@Override
	public ProductArtifact build(Product product, BuildArtifactInformation artifactInformation, AlterVersion buildIncrement)
	{
		// Check the inputs
		if (artifactInformation == null)
		{
			throw new IllegalArgumentException("'artifactInformation' is a required parameter");
		}
		
		if (buildIncrement == null)
		{
			throw new IllegalArgumentException("'buildIncrement' is a required parameter");
		}
		
		// Increment the physical build version
		Version version = null;//increment.increment(product.getVersion());		

		// Verify that we have the required build information
		Build productBuild = artifactInformation.getProductBuild();
		Set<ComponentArtifact> componentArtifacts = artifactInformation.getComponentArtifacts();
		
		ProductArtifact productArtifact = PhysicalProduct.newPhysicalArtifact(product, productBuild, version, componentArtifacts);
		
		return productArtifact;
	}		
}
