package tree;

import basicDS.TreeNode;

public class ValidateBinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static boolean isValidBST(TreeNode root) {
		return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static boolean helper(TreeNode node, int min, int max){
		if( node == null)
			return true;
		
		return  node.value > min && node.value < max &&
				helper(node.left, min, node.value) &&
				helper(node.right, node.value, max);
		
	}

}
