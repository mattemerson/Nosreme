package org.nosreme.system.logical;

import java.util.Set;

import org.nosreme.semver.Versioned;

public interface Product extends Versioned, ServiceProvider
{	
	String getName();
	String getDescription();
	Set<ComponentDescriptor> getDependencies();
}
