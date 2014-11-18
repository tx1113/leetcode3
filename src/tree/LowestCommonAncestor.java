package tree;

import basicDS.TreeNode;

public class LowestCommonAncestor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
		if (root == null || root == A || root == B){
			return root;
		}
		
		//Divide
		TreeNode left = lowestCommonAncestor(root.left, A, B);
		TreeNode right = lowestCommonAncestor(root.right, A, B);
		
		//Conquer
		if (left != null && right != null) {
			//one is in left and the other is in the right.
			return root;
		}
		if (left != null) {
			//both in the left
			return left;
		}
		if (right != null) {
			//both in the right
			return right;
		}
		return null;
    }
	
}
