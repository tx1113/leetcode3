package tree;

import basicDS.TreeNode;

public class BinaryTreeTraversalVerticalOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testV1();
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
}
