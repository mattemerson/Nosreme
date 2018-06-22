package org.emerson.tree;

/**
 * Operation is O(n)
 * https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
 * @author memerson
 *
 */
public class MaxLeafSum implements TreeMaxSumFinder
{
	private IntSolution solvedValue;
	
	public MaxLeafSum()
	{
		solvedValue = IntSolution.newNoSolution();
	}
	
	@Override
	public IntSolution findMaxSum(BinaryNode node)
	{
		apply(node);
		
		return solvedValue;
	}
			
	private IntSolution apply(BinaryNode node)
	{
		// If there is no node, return no solution
		if (node == null)
		{
			return IntSolution.newNoSolution();
		}
		
		// If this is a leaf, return this as the start of a solution
		if (node.isLeaf())
		{
			return IntSolution.newSolution(node.getValue());
		}
		
		// Otherwise, one or both sides need to lead to a leaf
		
		// Apply Left
		IntSolution leftSolution = apply(node.getLeft());
		
		// Apply Right
		IntSolution rightSolution = apply(node.getRight());
		
		// Get myself to use later
		IntSolution nodeSolution = IntSolution.newSolution(node.getValue());
		
		// Consider the combined solution, if any....
		IntSolution candidate = IntSolution.add(leftSolution, nodeSolution, rightSolution);
		solvedValue = IntSolution.chooseBestSolution(solvedValue, candidate);
					
		// Finally pass up the maxLeft or right + myself up to the top, whichever is bigger
		IntSolution passOnUp = IntSolution.max(IntSolution.add(leftSolution, nodeSolution), IntSolution.add(rightSolution, nodeSolution));
		
		return passOnUp;
	}
	
}
