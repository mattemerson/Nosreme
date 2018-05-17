package org.nosreme.system.physical;

import org.nosreme.semver.AlterVersion;
import org.nosreme.system.logical.Product;

public interface BuildProcess
{
	ProductArtifact build(Product product, BuildArtifactInformation artifactInformation, AlterVersion buildIncrement);
}
