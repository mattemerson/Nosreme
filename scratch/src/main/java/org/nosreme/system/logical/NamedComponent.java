package org.nosreme.system.logical;

import org.nosreme.semver.Version;
import org.nosreme.semver.Versioned;

public interface NamedComponent extends Versioned
{
	String getName();
	String getDescription();
	ComponentType getComponentType();
	Version getVersion();
}
