package tree;

import basicDS.Tree;
import basicDS.TreeNode;

public class BuildBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static TreeNode buildTree(){
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.buildTreeFromSortedArray(a);
		return root;
	}

}
