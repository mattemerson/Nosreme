package org.nosreme.semver;

import org.nosreme.util.InputChecker;

public class IncrementVersion implements AlterVersion
{
	private VersionComponent component;
	
	public IncrementVersion(VersionComponent component)
	{
		InputChecker.assertNonNull("component", component);
		
		this.component = component;
	}
	
	@Override
	public Version update(Version version)
	{
		Version updatedVersion = component.increment(version);
		
		return updatedVersion;
	}

}
