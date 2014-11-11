package tree;

import basicDS.TreeNode;

public class BalancedBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Balanced Binary Tree:
	 * to every node, abs (the height of its left subtree - height of its right subtree) <=1 
	 * */
	public static boolean balanced(TreeNode root){
		if(root == null)
			return true;
		return balancedHeight( root) >=0;
	}
	
	public static int balancedHeight(TreeNode node){
		if(node == null)
			return 0;
		int leftHeight = balancedHeight(node.left);
		int rightHeight = balancedHeight(node.right);
		
		if(leftHeight< 0 || rightHeight < 0 || Math.abs(leftHeight - rightHeight) >1)
			return -1;
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public static boolean balancedTree(TreeNode root){
		return getHeight(root) !=0;
	}
	
	public static int getHeight(TreeNode node){
		if(node == null)
			return 0;
		int leftHeight = getHeight(node.left);
		if(leftHeight < 0)
			return -1;
		int rightHeight = getHeight(node.right);
		if(rightHeight < 0)
			return -1;
		if(Math.abs(leftHeight - rightHeight) > 1)
			return -1;
		return Math.max(leftHeight, rightHeight) + 1;
	}
}
