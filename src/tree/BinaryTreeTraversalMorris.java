package tree;

import basicDS.Tree;
import basicDS.TreeNode;

import java.util.*;

public class BinaryTreeTraversalMorris {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testInOrder();
	}
	
	/*
	 * Morris InOrder Traversal
	 * (1) if current.left == null, print current.val, current = current.right
	 * (2) if current.left != null, get rightmost node of left subtree of current.
	 * 		(2.1) if the rightmost node.right == null, node.right = current
	 * 			  current = current.left;
	 * 		(2.2) if the rightmost node.right == current, 
	 * 					node.right = null (recover the tree)
	 * 					print current.value
	 * 					current = current.right;
	 * repeat (1) (2) until current == null
	 */
	public static ArrayList<Integer> inOrder(TreeNode root) {
		ArrayList<Integer> inorder = new ArrayList<Integer>();
		if (root == null) {
			return inorder;
		}
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				inorder.add(cur.value);
				cur = cur.right;
			} else {
				TreeNode predecessor = cur.left;
				while (predecessor.right != null && predecessor.right != cur) {
					//here, we should use && . since when we meet either one of them, 
					//we need to break the loop
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = cur;
					cur = cur.left;
				} else {
					//predecessor.right == cur
					predecessor.right = null;
					//output the cur.val
					inorder.add(cur.value);
					cur = cur.right;
				}	
			}
		}
		return inorder;
	}
	
	public static TreeNode buildTree() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.buildTreeFromSortedArray(a);
		return root;
	}
	
	public static void testInOrder() {
		TreeNode root = buildTree();
		ArrayList<Integer> inOrder = inOrder(root);
		System.out.println(inOrder);
		System.out.println(BinaryTreeTraversal.inOrder(root));
	}

}
