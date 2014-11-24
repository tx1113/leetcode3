package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import basicDS.TreeNode;

public class PerfectBinaryTreeSpecificLevelOrderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
	}
	
	/*
	 * http://www.geeksforgeeks.org/perfect-binary-tree-specific-level-order-traversal/
	 */
	
	/*
	 * Approach one
	 * do the standard level traversal.but instead of printing nodes directly, 
	 * we have to store nodes in current level in a temporary array or list 1st 
	 * and then take nodes from alternate ends (left and right) and print nodes. 
	 * Keep repeating this for all levels.
	 * This approach takes more memory than standard traversal.
	 */
	
	public static void approach1(TreeNode root) {
		if (root == null) {
			return ;
		}
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			ArrayList<Integer> curLevel = new ArrayList<Integer>();
			int size = q.size();
			for (int i =0; i < size; i++) {
				TreeNode curNode = q.remove();
				curLevel.add(curNode.value);
				if (curNode.left != null) {
					q.add(curNode.left);
				}
				if (curNode.right != null) {
					q.add(curNode.right);
				}
			}
			int start = 0, end = curLevel.size() - 1;
			while (start <= end) {
				if (start == end) {
					System.out.print(curLevel.get(start) +" ");
				}else {
					System.out.print(curLevel.get(start) + " " + curLevel.get(end) + " ");
				}
				start ++;
				end --;
			}
//			System.out.println();		
		}	
		System.out.println();
	}
	
	public static void  test1() {
		TreeNode root = BuildBinaryTree.buildPerfectBinaryTree();
		System.out.println(BinaryTreeTraversal.preOrderRec(root));
		System.out.println(BinaryTreeTraversal.inOrderRec(root));
		System.out.println(BinaryTreeTraversal.postOrderRec(root));
		
		approach1(root);
	}
	
	/*
	 * approach2
	 * Perfect Binary Tree, every interior node has two child.
	 * So we can add two node together per time into the q.
	 * 
	 * e.g
	 *                       1
	 *                  /        \
	 *                 2          3
	 *               /  \      /     \
	 *              4    5    6       7
	 *             / \   /\   / \    /  \
	 *            8  9  10 11 12 13  14 15
	 *            
	 *  we get node 2 and 3.
	 *  the next push is 2.left, 3.right, 2.right, 3.left
	 *                     4       7        5       6
	 */
	
	public static void approach2(TreeNode root) {
		if (root == null) {
			return ;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		System.out.print(root.value + " ");
		if (root.left == null && root.right == null) {
			return ;
		}
		q.add(root.left);
		q.add(root.right);
		while (!q.isEmpty()) {
			TreeNode first = q.remove();
			TreeNode second = q.remove();
			//print the first.val and second.val
			System.out.print(first.value + " " + second.value + " ");
			if (first.left != null && second.right != null) {
				q.add(first.left);
				q.add(second.right);
			}
			if (first.right != null && second.left != null) {
				q.add(first.right);
				q.add(second.left);
			}
		}
		System.out.println();
	}
	public static void  test2() {
		TreeNode root = BuildBinaryTree.buildPerfectBinaryTree();
//		System.out.println(BinaryTreeTraversal.preOrderRec(root));
//		System.out.println(BinaryTreeTraversal.inOrderRec(root));
//		System.out.println(BinaryTreeTraversal.postOrderRec(root));
		
		approach2(root);
	}
	
	
	/*
	 * following:
	 * (1) The above code prints specific level order from TOP to BOTTOM. 
	 *     How will you do specific level order traversal from BOTTOM to TOP 
	 * (2)What if tree is not perfect, but complete.
	 * (3)What if tree is neither perfect, nor complete. It can be any general binary tree.
	 */
	
	

}
