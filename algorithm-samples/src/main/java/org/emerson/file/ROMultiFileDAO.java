package org.emerson.file;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public interface ROMultiFileDAO<T>
{
	List<T> readLines(String filename);
	List<T> readLines(Path path);
	List<T> readLines(File file);
}
