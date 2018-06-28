package org.emerson.filereadsamples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/**
 * https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
 * 
 * @author Luke&Leia
 *
 */
public class DifferentFileReaderSamplesTest {

	private String filename = "src/test/java/org/emerson/filereadsamples/samplefile";

	/*
	 * Using BufferedReader: This method reads text from a character-input stream.
	 * It does buffering for efficient reading of characters, arrays, and lines. The
	 * buffer size may be specified, or the default size may be used. The default is
	 * large enough for most purposes.
	 * 
	 * In general, each read request made of a Reader causes a corresponding read
	 * request to be made of the underlying character or byte stream. It is
	 * therefore advisable to wrap a BufferedReader around any Reader whose read()
	 * operations may be costly, such as FileReaders and InputStreamReaders
	 */
	@Test
	public void testBufferedReaderShouldPass() throws IOException {
		// We need to provide file path as the parameter:
		// double backquote is to avoid compiler interpret words
		// like \test as \t (ie. as a escape sequence)
		// File file = new File(filename);
		File file = Paths.get(filename).toFile();

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		while ((st = br.readLine()) != null) {
			System.out.println(st);
		}
	}

	/**
	 * Using FileReader class: Convenience class for reading character files. The
	 * constructors of this class assume that the default character encoding and the
	 * default byte-buffer size are appropriate.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFileReaderShouldPass() throws IOException {
		// pass the path to the file as a parameter
		FileReader fr = new FileReader(Paths.get(filename).toFile());

		int i;
		while ((i = fr.read()) != -1)
			System.out.print((char) i);

	}

	/**
	 * A simple text scanner which can parse primitive types and strings using
	 * regular expressions. A Scanner breaks its input into tokens using a delimiter
	 * pattern, which by default matches whitespace. The resulting tokens may then
	 * be converted into values of different types using the various next methods.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testScannerShouldPass() throws FileNotFoundException {
		// pass the path to the file as a parameter
		File file = Paths.get(filename).toFile();
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine())
			System.out.println(sc.nextLine());
	}

	/**
	 * File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt"); Scanner sc =
	 * new Scanner(file);
	 * 
	 * // we just need to use \\Z as delimiter sc.useDelimiter("\\Z");
	 * 
	 * System.out.println(sc.next());
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testScannerReadWholeFileWithoutLoops() throws FileNotFoundException {
		File file = Paths.get(filename).toFile();
		Scanner sc = new Scanner(file);

		// we just need to use \\Z as delimiter
		sc.useDelimiter("\\Z");

		System.out.println(sc.next());
	}

	/**
	 * Reading the whole file in a List: Read all lines from a file. This method
	 * ensures that the file is closed when all bytes have been read or an I/O
	 * error, or other runtime exception, is thrown. Bytes from the file are decoded
	 * into characters using the specified charset.
	 */
	@Test
	public void testReadFileLineByLineShouldPass() {
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		}

		catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		Iterator<String> itr = lines.iterator();
		while (itr.hasNext())
			System.out.println(itr.next());

	}

	/**
	 * // Java Program to illustrate reading from text file // as string in Java
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadFileAsASingleStringShouldPass() throws IOException {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(filename)));
		System.out.println(data);
	}
}
