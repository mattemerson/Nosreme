package org.nosreme.versioning;

public enum BuildIncrement
{
	MAJOR
	{
		public Version increment(Version version)
		{
			return version.incrementMajor();
		}
	},
	MINOR
	{
		public Version increment(Version version)
		{
			return version.incrementMinor();
		}		
	},
	PATCH
	{
		public Version increment(Version version)
		{
			return version.incrementPatch();
		}	
	},
	NONE
	{
		public Version increment(Version version)
		{
			return version;
		}
	};
		
	public abstract Version increment(Version version);
}
