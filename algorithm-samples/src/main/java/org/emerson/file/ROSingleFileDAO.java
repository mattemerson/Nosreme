package org.emerson.file;

import java.io.File;
import java.nio.file.Path;

public interface ROSingleFileDAO<T>
{
	T read(String filename);
	T read(Path path);
	T read(File file);
}
