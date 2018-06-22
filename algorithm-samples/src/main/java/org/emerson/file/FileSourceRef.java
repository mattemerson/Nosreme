package org.emerson.file;

import java.io.File;
import java.nio.file.Path;

public interface FileSourceRef {

	Path toPath();
	File toFile();
	String toFilename();
}
