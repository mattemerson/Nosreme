package org.emerson.file;

public class ROSimpleCSVFileDAO<T> extends SimpleFileDAO<T>
{
	public ROSimpleCSVFileDAO()
	{
		// You always skip the first line in a CSV file
		super(1);
	}
}
