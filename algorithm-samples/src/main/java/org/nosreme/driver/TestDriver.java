package org.nosreme.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nosreme.collections.bst.BinaryTree;
import org.nosreme.collections.linkedlist.LinkedListNode;
import org.nosreme.overloading.DerivedClass;

public class TestDriver {

	public static void main(String[] args)
	{
		System.out.println("START: MAIN");
		TestDriver driver = new TestDriver();
		driver.runTest();
		System.out.println("FINISH: MAIN");
	}

	public void runTest()
	{
		System.out.println("START:runTest");
		//testLinkedListNode();
		//testBinarySearchTree();
		//testRBSearchTree();
		//testOverriding();
		testMinHeap();
		System.out.println("FINISH: runTest");
	}
	
	private void testMinHeap()
	{
		int array[] = new int[]{1,3,5,2,8,7,6,10,-1};
		
		//MinHeap minHeap = new MinHeap();
		/*
		for (int value : array)
		{
			minHeap.insert(value);
		}
		
		System.out.println(minHeap);
		
		System.out.println(minHeap.extractMin());
		
		System.out.println(minHeap);
		*/
	}
	
	private void testOverriding()
	{
		DerivedClass derived = new DerivedClass();
	}
	
	private void testLinkedListNode()
	{
		String fullList = "((0),(1),(2),(3),)";
		String deleteList = "((0),(1),(3),)";
		
		LinkedListNode linkedList = LinkedListNode.newLinkedList(0);
		linkedList = linkedList.addNext(1);
		LinkedListNode node2 = linkedList.addNext(2);		
		linkedList = node2.addNext(3);
		
		if (!fullList.equals(linkedList.toString()))
		{
			throw new IllegalStateException("fail!");
		}
		
		node2.delete();
		if (!deleteList.equals(linkedList.toString()))
		{
			throw new IllegalStateException("fail!");
		}		
	}
	
	private void testRBSearchTree()
	{
		
	}
	
	private void testBinarySearchTree()
	{
		List<Integer> list = createListFrom1To10();
		
		BinaryTree tree = new BinaryTree();
		
		for (Integer val : list)
		{
			tree.insert(val);
		}
		
		List<Integer> orderedList = tree.toOrderedList();
		System.out.println("Ordered List:");
		System.out.println(orderedList);
	}
	
	private List<Integer> createListFrom1To10()
	{
		List<Integer> list = new ArrayList<Integer>(11);
		for (int ii=1; ii<=10;ii++)
		{
			list.add(ii);
		}
		
		list.add(6);
		
		System.out.println("Original List:");
		System.out.println(list);
		Collections.shuffle(list);
		System.out.println("Shuffled List:");
		System.out.println(list);
				
		return list;
	}
}
