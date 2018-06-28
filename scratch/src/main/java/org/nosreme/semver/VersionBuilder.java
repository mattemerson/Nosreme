package org.nosreme.semver;

/**
 * Does SemanticVersioning
 * @author memerson
 *
 */
public class VersionBuilder
{
	private Version version;
	
	public VersionBuilder(Version version)
	{
		version = new SemanticVersion(version);
	}
	
	public VersionBuilder()
	{
		this.version = new SemanticVersion(0, 0, 0, null, null);
	}
	
	public Version major(int major)
	{
		
	}
	
	public Version minor(int minor)
	{
		
	}
	
	public Version patch(int patch)
	{
		
	}
	
	public Version incrementMajor()
	{
		
	}
	
	public Version incrementMinor()
	{
		
	}
	
	public Version incrementPatch()
	{
		
	}
	
	public Version versionNumber(VersionNumber versionNumber)
	{
		
	}
	
	public Version preRelease()
	{
		
	}
	
	public Version buildInfo()
	{
		
	}
}
