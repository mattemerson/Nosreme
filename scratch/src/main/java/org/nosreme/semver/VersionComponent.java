package org.nosreme.semver;

public enum VersionComponent
{
	MAJOR
	{
		@Override
		public Version increment(Version version)
		{
			int newMajor = version.getMajor() + 1;
			return VersionImpl.newVersion(newMajor, 0, 0);
		}
	},
	MINOR
	{
		@Override
		public Version increment(Version version)
		{
			int newMinor = version.getMinor() + 1;
			return VersionImpl.newVersion(version.getMajor(), newMinor, 0);	
		}
	},
	PATCH
	{
		@Override
		public Version increment(Version version)
		{
			int newPatch = version.getPatch() + 1;
			return VersionImpl.newVersion(version.getMajor(), version.getMinor(), newPatch);
		}
	};
		
	public abstract Version increment(Version version);
}
