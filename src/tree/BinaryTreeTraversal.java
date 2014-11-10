package tree;
import java.util.ArrayList;

import basicDS.*;

public class BinaryTreeTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//Result: ArrayList<Integer> preOrder
	//preOrder
	//1 Recursion
	public static ArrayList<Integer> preOrderRec(TreeNode root){
		ArrayList<Integer> preOrder = new ArrayList<Integer>();
		preHelper(root, preOrder);
		return preOrder;
	}
	
	private static void  preHelper(TreeNode node, ArrayList<Integer> preOrder) {
		if(node == null)
			return;
		preOrder.add(node.value);
		preHelper(node.left, preOrder);
		preHelper(node.right, preOrder);
	}
	
	//inOrder
	public static ArrayList<Integer> inOrderRec(TreeNode root){
		return null;
	}
	
	
	//postOrder
	
	

}
