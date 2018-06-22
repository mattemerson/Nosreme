package org.emerson.tree;

/**
 * https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
 * @author memerson
 *
 */
public class MaxPossibleSum implements TreeMaxSumFinder
{
	private IntSolution solvedValue;
	
	public MaxPossibleSum()
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
		
		// Possible Candidate Solutions
		// 1. Node (the node itself)
		// 2. LeftSolution
		// 3. RightSolution
		// 4. LeftSolution + Node + RightSolution

		// Consider the combined solution, if any....
		IntSolution bestCandidate = IntSolution.max(leftSolution,
												    rightSolution,
												    nodeSolution,
												    IntSolution.add(leftSolution, rightSolution, nodeSolution));
		
		solvedValue = IntSolution.chooseBestSolution(solvedValue, bestCandidate);
		
		// To Pass up, max of (N, N+L, N+R)
		IntSolution passOnUp = IntSolution.max(nodeSolution,
											   IntSolution.add(leftSolution, nodeSolution),
											   IntSolution.add(rightSolution, nodeSolution));
		
		return passOnUp;
	}
	
}
