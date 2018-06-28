package org.emerson.file;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class StringFileDAO implements ROMultiFileDAO<String>, ROSingleFileDAO<String>
{
	private SimpleFileDAO<String> dao;
	private Function<String,String> mapper;
	
	public StringFileDAO()
	{
		dao = new SimpleFileDAO<String>();
		mapper = (s) -> { return s; };
	}
	@Override
	public String read(String filename) {
		return dao.read(mapper, filename);
	}

	@Override
	public String read(Path path) {
		return dao.read(mapper, path);
	}

	@Override
	public String read(File file) {
		return dao.read(mapper, file);
	}

	@Override
	public List<String> readLines(String filename) {
		return dao.readLines(mapper, filename);
	}

	@Override
	public List<String> readLines(Path path) {
		return dao.readLines(mapper, path);
	}

	@Override
	public List<String> readLines(File file) {
		return dao.readLines(mapper, file);
	}

}
