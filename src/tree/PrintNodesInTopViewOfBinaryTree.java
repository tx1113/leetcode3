package tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import basicDS.TreeNode;

public class PrintNodesInTopViewOfBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
		test2();
	}
	
	/*
	 * http://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
	 * Idea: modify BinaryTreeTraversalVerticalOrder
	 * 
	 * Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. 
	 * Given a binary tree, print the top view of it. 
	 * The output nodes can be printed in ANY order. 
	 * Expected time complexity is O(n)
	 * A node x is there in output if x is the topmost node at its horizontal distance. 
	 * Horizontal distance of a child of a node x is equal to horizontal distance of x minus 1, 
	 * and that of right child is horizontal distance of x plus 1.
	 * 
	 * e.g
	 *          1
	 *        /   \
	 *       2     3
	 *      / \   /  \
	 *     4   5  6   7
	 *  The top view is
	 *  4 2 1 3 7 
	 *  
	 *           1
	 *         /    \
	 *        2      3
	 *          \
	 *           4
	 *             \ 
	 *               5
	 *                 \
	 *                   6
	 *  The top view is
	 *  2 1 3 6
	 *  
	 *  Every node has a horizontal distance. 
	 *  we do a level traversal. to make sure that the we print 
	 *  out the upper node for those have the same hd. 
	 *  We need a hashSet to check if a certain hd is seen or not.
	 *  
	 */
	
	public static class Item {
		TreeNode node;
		int hd;

		public Item(TreeNode n, int h) {
			this.node = n;
			this.hd = h;
		}
	}

	public static void printTopView(TreeNode root) {
		if (root == null) {
			return;
		}
		Set<Integer> set = new HashSet<Integer>();
		Queue<Item> q = new LinkedList<Item>();
		Item rootItem = new Item(root, 0);
		q.add(rootItem);
		
		while (!q.isEmpty()) {
			Item curItem = q.remove();
			TreeNode curNode = curItem.node;
			int curHd = curItem.hd;
			if (!set.contains(curItem.hd)) {
				set.add(curHd);
				System.out.print(curNode.value + " ");
			}
			if (curNode.left != null) {
				q.add(new Item(curNode.left, curHd - 1));
			}
			
			if (curNode.right != null) {
				q.add(new Item(curNode.right, curHd + 1));
			}
		}
	}
	
	public static void test() {
		TreeNode root = BuildBinaryTree.buildTree();
		System.out.println(BinaryTreeTraversal.preOrderRec(root));
		System.out.println(BinaryTreeTraversal.inOrder(root));
		System.out.println(BinaryTreeTraversal.postOrder(root));
		
		printTopView(root);
		System.out.println();
	}
	
	public static void printTopViewNoSet(TreeNode root) {
		if (root == null) {
			return ;
		}
		
		Queue<Item> q = new LinkedList<Item>();
		Item rootItem = new Item(root, 0);
		int maxHd = Integer.MIN_VALUE, minHd = Integer.MAX_VALUE;
		q.add(rootItem);
		while (!q.isEmpty()) {
			Item curItem = q.remove();
			int curHd = curItem.hd;
			TreeNode curNode = curItem.node;
			
			if (curHd < minHd) {
				minHd = curHd;
				System.out.print(curNode.value + " ");
			}else if (curHd > maxHd) {   
				//for the root, if without the "else" it will execute the curHd < midHd 
				// and curHd > maxHd, printing the root.val twice
				maxHd = curHd;
				System.out.print(curNode.value + " ");
			}
			if (curNode.left != null) {
				q.add(new Item(curNode.left, curHd - 1));
			}
			if (curNode.right != null) {
				q.add(new Item(curNode.right, curHd + 1));
			}	
		}
	}
	
	public static void test2() {
		TreeNode root = BuildBinaryTree.buildTree();
		System.out.println(BinaryTreeTraversal.preOrderRec(root));
		System.out.println(BinaryTreeTraversal.inOrder(root));
		System.out.println(BinaryTreeTraversal.postOrder(root));
		
		printTopViewNoSet(root);
	}
	
	
	

}
