package org.emerson.file;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public interface FileDAO<T>
{	
	List<T> readFile(String filename);
	List<T> readFile(Path path);
	List<T> readFile(InputStream is);
}
