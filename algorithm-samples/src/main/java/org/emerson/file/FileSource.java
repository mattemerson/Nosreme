package org.emerson.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileSource {
	
	public static class PathSourceRef implements FileSourceRef
	{
		private Path path;
		
		public PathSourceRef(Path path)
		{
			this.path = path;
		}
		
		@Override
		public Path toPath() {
			return path;
		}

		@Override
		public File toFile() {
			return path.toFile();
		}

		@Override
		public String toFilename() {
			return path.toFile().toString();
		}
		
	}
	
	public static class FileSourceRefImpl implements FileSourceRef
	{
		private File file;
		
		public FileSourceRefImpl(File file)
		{
			this.file = file;
		}
		
		@Override
		public Path toPath() {
			return file.toPath();
		}

		@Override
		public File toFile() {
			return file;
		}

		@Override
		public String toFilename() {
			return file.toString();
		}
		
	}
	
	public static class FilenameSourceRef implements FileSourceRef
	{
		private String filename;
		
		FilenameSourceRef(String filename)
		{
			this.filename = filename;
		}
		
		@Override
		public Path toPath() {
			return Paths.get(filename);
		}

		@Override
		public File toFile() {
			return toPath().toFile();
		}

		@Override
		public String toFilename() {
			return filename;
		}
		
	}
	
	public static FileSourceRef fromPath(Path path)
	{
		Objects.requireNonNull(path, "'path' is a required parameter");
		return new PathSourceRef(path);
	}
	
	public static FileSourceRef fromFile(File file)
	{
		Objects.requireNonNull(file, "file' is a required parameter");
		return new FileSourceRefImpl(file);
	}
	
	public static FileSourceRef fromFilename(String filename)
	{
		Objects.requireNonNull(filename, "'filename' is a required parameter");
		return new FilenameSourceRef(filename);
	}
}
