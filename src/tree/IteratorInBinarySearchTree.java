package tree;

import java.util.Stack;

import basicDS.*;

public class IteratorInBinarySearchTree {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Iterator
	 * Example of iterate a tree:
	 * Solution iterator = new Solution(root);
	 * while (iterator.hasNext()) {
	 * TreeNode node = iterator.next();
	 * do something for node
	 * 
	 * Also, the Iterator should be internally store the current location
	 */
	
	public  Stack<TreeNode> st = new Stack<TreeNode>();
	public  TreeNode current; 

	public IteratorInBinarySearchTree(TreeNode root) {
		// write your code here
		current = root;
	}

	// @return: True if there has next node, or false
	public boolean hasNext() {
		// write your code here
		return (current != null || !st.isEmpty());
	}

	// @return: return next node
	public  TreeNode next() {
		// write your code here
		while (current != null) {
			st.push(current); //push into stack
			current = current.left;
		}
		
		current = st.pop();
		TreeNode node = current;
		current = current.right;
		
		return node;
	}
}
