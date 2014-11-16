package tree;

import basicDS.TreeNode;

public class BinaryTreeMaximumPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * Given a binary tree, find the maximum path sum.
	 * The path may start and end at any node in the tree.
	 */
	
	private static class ResultType {
		public int singlePath;
		public int maxPath;
		public ResultType(int single, int max) {
			// TODO Auto-generated constructor stub
			this.singlePath = single;
			this.maxPath = max;
		}
	}
	
	public static int maxPathSum(TreeNode root) {
        // write your code here
		if (root == null) {
			return 0;
		}//!!! pay attention here. if the node is null. return 0;
		ResultType result = getMaxPathSum(root);
		return result.maxPath;
		
    }
	
	
	//divide and conquer. This is a great idea so solve this kind of question. 
	//Related questions: FlatternBinaryTreeToLinkedList
	public static ResultType getMaxPathSum(TreeNode node) {
		if (node == null) {
			return new ResultType(0, Integer.MIN_VALUE);
		}
		
		//divide
		ResultType left = getMaxPathSum(node.left);
		ResultType right = getMaxPathSum(node.right);
		
		//conquer
		int singlePath = Math.max(left.singlePath, right.singlePath) + node.value;
		singlePath = Math.max(0, singlePath);
		
		int maxPath = Math.max(left.maxPath, right.maxPath);
		maxPath = Math.max(maxPath, left.singlePath + right.singlePath + node.value);
		
		return new ResultType(singlePath, maxPath);
	}
	
	public static TreeNode buildTree(){
		TreeNode root = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		root.right = node1;
		node1.right = node2;
		return root;
	}
	
	public static void test(){
		TreeNode root = buildTree();
		int rev =maxPathSum(root);
		System.out.println("rev = " + rev);
	}

}


