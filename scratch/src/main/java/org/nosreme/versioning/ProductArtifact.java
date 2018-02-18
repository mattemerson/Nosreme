package org.nosreme.versioning;

import java.util.Set;

public interface ProductArtifact extends Artifact
{
	Product getProduct();
	Set<ComponentArtifact> getDependents();
}
