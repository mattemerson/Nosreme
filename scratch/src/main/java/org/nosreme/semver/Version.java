package org.nosreme.semver;

public interface Version
{
	int getMajor();
	int getMinor();
	int getPatch();	
	String getPrefix();
	String getSuffix();
	Version getSubversion();
}
