package org.emerson.file;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public interface ROMultiFileMapperDAO<T> {

	List<T> readLines(Function<String,T> mapper, String filename);
	List<T> readLines(Function<String,T> mapper, Path path);
	List<T> readLines(Function<String,T> mapper, File file);
}
