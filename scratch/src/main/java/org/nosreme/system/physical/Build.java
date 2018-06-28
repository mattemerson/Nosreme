package org.nosreme.system.physical;

import java.time.LocalDateTime;

public interface Build
{
	String getNotes();
	LocalDateTime getBuildTimestamp();
	String getCommitHash();
	String getTag();
}
