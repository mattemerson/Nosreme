package org.nosreme.versioning;

public interface NamedComponent extends Versioned
{
	String getName();
	String getDescription();
	ComponentType getComponentType();
	Version getVersion();
}
