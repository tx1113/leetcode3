package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.omg.CORBA.SystemException;

import basicDS.TreeNode;

public class BinaryTreeTraversalVerticalOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testV1();
		testV2();
		testV3();
	}
	
	/*
	 * Idea
	 *         1
	 *        / \
	 *       2   3
	 *      / \ / \
	 *	   4  5 6  7
	 *			 \  \
	 *			  8	 9
	 * Print 
	 * 4
	 * 2
	 * 1 5 6
	 * 3 8
	 * 7
	 * 9
	 * 
	 * We traversal the tree and get the minimum and maximum horizontal distance from the root.
	 * e.g 4's hd = -2, 9's hd = 3
	 * 
	 * Then we pass the hd into printVertical. if we go left,  hd = hd - 1, go right, hd = hd + 1
	 * 
	 * Algorithm:
	 * min: the minimum horizontal distance of the tree
	 * max: the maximum horizontal distancd of the tree
	 * 
	 * findMinMax(TreeNode node, min, max, hd)
	 * 	if node == null
	 * 		return;
	 *  if(min < hd) 
	 *  	min = hd;
	 *  else if (max > hd)
	 *      max = hd;
	 *   findMinMax(node.left, min, max, hd - 1);
	 *   findMinMax(node.left, min, max, hd + 1);
	 *   
	 * printVerticalLine(TreeNode node, int lineNo, hd) 
	 * 		if node == null
	 * 			return ;
	 *      if lineNo == hd
	 *          print node.val
	 *      printVerticalLine(node.left, lineNo, hd - 1);
	 *      printVerticalLine(node.right, lineNo, hd + 1);
	 * 
	 * 
	 */ 
	
	/*
	 * Since min and max is int, in Java, we cannot pass them via reference. 
	 * We define a private class named MinMax, which contain min and max of the tree;
	 */
	
	private static class MinMax {
		public int min;
		public int max;
		public MinMax(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}
	
	public static void findMinMax(TreeNode node, int hd, MinMax min_max) {
		if (node == null) {
			return ;
		}
		if (min_max.min > hd) {  //!!! here, pay attention. Not min_max.min > hd
			min_max.min = hd;
		}
		if (min_max.max < hd) {
			min_max.max = hd;
		}
		findMinMax(node.left, hd-1, min_max);
		findMinMax(node.right, hd+1, min_max);
	}
	
	public static void printVerticalLine(TreeNode node, int lineNo, int hd) {
		if (node == null) 
			return ;
		if (lineNo == hd) {
			System.out.print(node.value + " ");
		}
		printVerticalLine(node.left, lineNo, hd - 1);
		printVerticalLine(node.right, lineNo, hd + 1);
	}
	public static void binaryTreeTraversalVerticalOrder(TreeNode root) {
		// TODO Auto-generated constructor stub
		MinMax min_max = new MinMax(0, 0);
		findMinMax(root, 0, min_max);
		
		
//		System.out.println(min_max.min);
//		System.out.println(min_max.max);
		for (int lineNo = min_max.min; lineNo <= min_max.max; lineNo ++) {
//			System.out.println("lineNo = " + lineNo);
			printVerticalLine(root, lineNo, 0);
			System.out.println();
		}
	}
	
	public static void testV1() {
		TreeNode root = BuildBinaryTree.buildTree();
		System.out.println(BinaryTreeTraversal.preOrderRec(root));
		System.out.println(BinaryTreeTraversal.inOrderRec(root));
		System.out.println(BinaryTreeTraversal.postOrderRec(root));
		
		binaryTreeTraversalVerticalOrder(root);
	}
	
	/*
	 * We use a hash map to optimize
	 * 
	 * Time: 
	 * 	if we use a hashMap, we can access the key in O(1) time. But the key is unsorted. 
	 * 		So, Sorting the key we need O(n log n). The final time complexity is O(n log n)
	 *  Also, 
	 *  if we use a treeMap, which is implemented by red-black tree. 
	 *       Its key is sorted. but getKey, remove, update,etc, we need O(log n)
	 *       So the total time is O(n log n)
	 */
	
	public static void printVerticalLine2(TreeNode root) {
		if (root == null) {
			return ;
		}
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		getVerticalOrder2(root, 0, map);
		
		//print the map out.
		ArrayList<Integer> keyArr = new ArrayList<Integer>();
		for (Integer key: map.keySet()) {
			keyArr.add(key);
		}
		
		Collections.sort(keyArr);
		System.out.println(keyArr);
		for (int i=0; i< keyArr.size(); i++) {
			int hd = keyArr.get(i);
			ArrayList<Integer> line = map.get(hd);
			for (Integer ele: line) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}
	
	public static void getVerticalOrder2(TreeNode node, int hd, 
			HashMap<Integer, ArrayList<Integer>> map) {
		if (node == null) {
			return ;
		}
		//push the node value of this hd into 
		if (map.containsKey(hd)) {
			map.get(hd).add(node.value);
		} else {
			ArrayList<Integer> newLine = new ArrayList<Integer>();
			newLine.add(node.value);
			map.put(hd, newLine);
		}
		getVerticalOrder2(node.left, hd - 1, map);
		getVerticalOrder2(node.right, hd + 1, map);
		
	}
	
	public static void testV2() {
		TreeNode root = BuildBinaryTree.buildTree();
		System.out.println(BinaryTreeTraversal.preOrderRec(root));
		System.out.println(BinaryTreeTraversal.inOrderRec(root));
		System.out.println(BinaryTreeTraversal.postOrderRec(root));
		printVerticalLine2(root);
	}
	
	//treeMap
	public static void getVerticalOrder3(TreeNode node, int hd, TreeMap<Integer, 
			ArrayList<Integer>> treeMap) {
		if (node == null) {
			return ;
		}
		
		ArrayList<Integer> curLine = null;
		if (!treeMap.containsKey(hd)) {
			curLine = new ArrayList<Integer>();
			treeMap.put(hd, curLine);
		}
		curLine = treeMap.get(hd);
		curLine.add(node.value);
		
		getVerticalOrder3(node.left, hd - 1, treeMap);
		getVerticalOrder3(node.right, hd + 1, treeMap);
		
	}
	
	public static void printVerticalLine3(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeMap<Integer, ArrayList<Integer>> treeMap = new TreeMap<Integer, ArrayList<Integer>>();
		getVerticalOrder3(root, 0, treeMap);
		
		//i must be a continuous Integer number from min to max
		for (int i =treeMap.firstKey(); i <= treeMap.lastKey(); i++) {
			ArrayList<Integer> line = treeMap.get(i);
			
			for(Integer j: line) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	
	public static void testV3() {
		TreeNode root = BuildBinaryTree.buildTree();
		System.out.println(BinaryTreeTraversal.preOrderRec(root));
		System.out.println(BinaryTreeTraversal.inOrderRec(root));
		System.out.println(BinaryTreeTraversal.postOrderRec(root));
		printVerticalLine3(root);
	}
	
}
