package org.emerson.file;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Function;

public interface ROSingleFileMapperDAO<T>
{	
	T read(Function<String,T> mapper, String filename);
	T read(Function<String,T> mapper, Path path);
	T read(Function<String,T> mapper, File file);
}
