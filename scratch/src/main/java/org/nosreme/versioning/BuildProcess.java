package org.nosreme.versioning;

public interface BuildProcess
{
	ProductArtifact build(Product product, BuildArtifactInformation artifactInformation, BuildIncrement buildIncrement);
}
