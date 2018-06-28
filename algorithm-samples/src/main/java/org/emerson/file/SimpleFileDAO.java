package org.emerson.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleFileDAO<T> implements ROMultiFileMapperDAO<T>, ROSingleFileMapperDAO<T> 
{
	protected long linesToSkip;
	
	public SimpleFileDAO()
	{
		this(0);
	}
	
	protected SimpleFileDAO(int linesToSkip)
	{
		this.linesToSkip = linesToSkip;
	}
	
	@Override
	public List<T> readLines(Function<String, T> mapper, String filename) {
		return readLines(mapper, FileSource.fromFilename(filename).toPath());
	}

	@Override
	public List<T> readLines(Function<String, T> mapper, Path path)
	{	
		File inputF = path.toFile();
        try(
      	      InputStream inputFS = new FileInputStream(inputF);
      	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
        	  Stream<String> stream = br.lines();
        	)
		//try(Stream<String> stream = Files.lines(path);)
		{					
			return stream.skip(linesToSkip).map(s->mapper.apply(s)).collect(Collectors.toList());
		}
		catch(IOException ioe)
		{
			throw new UncheckedIOException(ioe);
		}
	}

	@Override
	public List<T> readLines(Function<String, T> mapper, File file) {
		return readLines(mapper, FileSource.fromFile(file).toPath());
	}

	@Override
	public T read(Function<String, T> mapper, String filename) {
		
		return read(mapper, FileSource.fromFilename(filename).toPath());
	}

	@Override
	public T read(Function<String, T> mapper, Path path) {
		
		try
		{
			String whatsRead = new String(Files.readAllBytes(path));
			return mapper.apply(whatsRead);
		}
		catch(IOException ioe)
		{
			throw new UncheckedIOException(ioe);
		}
	}

	@Override
	public T read(Function<String, T> mapper, File file) {
		
		return read(mapper, FileSource.fromFile(file).toPath());
	}

}
