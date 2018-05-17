package org.nosreme.semver;

public interface SemanticVersion
{
	Version getVersion();
	String getSuffix();
	Version getSubversion();
}
