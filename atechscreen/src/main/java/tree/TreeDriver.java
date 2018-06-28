package tree;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TreeDriver {

	public static void main(String[] args) throws Exception
	{
		String cannedInputData = readFileAsString("C:\\Users\\Luke&Leia\\Downloads\\zaius-problems\\zaius-problems\\tree\\input.txt");
		String[] inputsAsString = cannedInputData.split(",");
		List<Integer> inputs = new ArrayList<Integer>(inputsAsString.length);
		for (String input : inputsAsString)
		{
			input = input.trim();
			System.out.println("parsing input='" + input + "'");
			inputs.add(Integer.parseInt(input));
		}		
		
		Tree tree = new Tree();
		for (Integer value : inputs)
		{
			tree.addNode(value);
		}
		
		String cannedOutputData = readFileAsString("C:\\Users\\Luke&Leia\\Downloads\\zaius-problems\\zaius-problems\\tree\\output.txt");
		System.out.println(cannedOutputData);
		System.out.print(tree.toString());
		
		if (cannedOutputData.equals(tree.toString()))
		{
			System.out.println("success!");
		}
		else
			System.out.println("Failure");
	}
	  public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }
}
