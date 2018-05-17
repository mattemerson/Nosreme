package org.nosreme.system.physical;

import org.nosreme.semver.Version;

public interface Artifact extends Built
{
	Version getVersion();
}
