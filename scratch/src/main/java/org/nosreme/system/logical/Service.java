package org.nosreme.system.logical;

import org.nosreme.semver.Version;

public interface Service
{
	String getName();
	String getDescription();
	Version getVersion();
}
