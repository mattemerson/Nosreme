package org.nosreme.system.physical;

import java.time.LocalDateTime;

public class BuildImpl implements Build
{
	private String myNotes;
	private LocalDateTime myBuildTimestamp;
	private String myCommitHash;
	private String myTag;
	
	public static Build newBuild(String notes, LocalDateTime buildTimestamp, String commitHash, String tag)
	{
		if (notes == null)
		{
			throw new IllegalArgumentException("'notes' is a required parameter");
		}
		
		if (buildTimestamp == null)
		{
			throw new IllegalArgumentException("'buildTimestamp' is a required parameter");
		}
		
		if (commitHash == null)
		{
			throw new IllegalArgumentException("'commitHash' is a required parameter");
		}
		
		if (tag == null)
		{
			throw new IllegalArgumentException("'tag' is a required parameter");
		}
		
		return new BuildImpl(notes, buildTimestamp, commitHash, tag);
	}
	
	private BuildImpl(String notes, LocalDateTime buildTimestamp, String commitHash, String tag)
	{		
		myNotes = notes;
		myBuildTimestamp = buildTimestamp;
		myCommitHash = commitHash;
		myTag = tag;
	}
	
	@Override
	public String getNotes()
	{
		return myNotes;
	}

	@Override
	public LocalDateTime getBuildTimestamp()
	{
		return myBuildTimestamp;
	}

	@Override
	public String getCommitHash()
	{
		return myCommitHash;
	}

	@Override
	public String getTag()
	{
		return myTag;
	}

}
