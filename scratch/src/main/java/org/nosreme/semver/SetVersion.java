package org.nosreme.semver;

import org.nosreme.util.InputChecker;

public class SetVersion implements AlterVersion
{
	private Version targetVersion;
	
	public SetVersion(Version version)
	{
		InputChecker.assertNonNull("version", version);
		
		this.targetVersion = version;
	}
		
	@Override
	public Version update(Version version)
	{
		return this.targetVersion;
	}

}
