package tree;


import basicDS.TreeNode;

public class BinarySearchOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static TreeNode insertNode(TreeNode currentParent, TreeNode node) {
		if (currentParent == null) {
			return node;
		}
		if(currentParent.value < node.value) {
			//insert to currentParent.lfet
			currentParent.left = insertNode(currentParent.left, node);
		}else if (currentParent.value > node.value) {
			//insert to currentParent.right
			currentParent.right = insertNode(currentParent.right, node);
		}else {
			//duplicate, do nothing
		}
		return currentParent;
	}
	
	
	public static TreeNode removeNode(TreeNode currentParent, int value) {
		return null;
	}

}
