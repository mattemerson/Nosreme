package org.nosreme.versioning;

public interface Version
{
	int getMajor();
	int getMinor();
	int getPatch();	
		
	Version incrementMajor();
	Version incrementMinor();
	Version incrementPatch();
}
