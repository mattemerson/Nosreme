package org.emerson.file;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class SimpleFileDAO<T> implements ROMultiFileMapperDAO<T>, ROSingleFileMapperDAO<T> 
{

	@Override
	public List<T> readLines(Function<String, T> mapper, String filename) {
		return null;
	}

	@Override
	public List<T> readLines(Function<String, T> mapper, Path path) {
		return null;
	}

	@Override
	public List<T> readLines(Function<String, T> mapper, File file) {
		return null;
	}

	@Override
	public T read(Function<String, T> mapper, String filename) {
		return null;
	}

	@Override
	public T read(Function<String, T> mapper, Path path) {
				
		return null;
	}

	@Override
	public T read(Function<String, T> mapper, File file) {
		return null;
	}

}
