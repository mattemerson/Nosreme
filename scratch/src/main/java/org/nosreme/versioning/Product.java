package org.nosreme.versioning;

import java.util.Set;

public interface Product extends Versioned, ServiceProvider
{	
	String getName();
	String getDescription();
	Set<ComponentDescriptor> getDependencies();
}
