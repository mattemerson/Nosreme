package org.nosreme.system.physical;

import java.util.Set;

import org.nosreme.system.logical.Product;

public interface ProductArtifact extends Artifact
{
	Product getProduct();
	Set<ComponentArtifact> getDependents();
}
