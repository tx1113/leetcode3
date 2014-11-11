package basicDS;
import tree.*;

import java.util.ArrayList;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Tree {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static TreeNode buildTreeFromSortedArray(int[] a){
		int left = 0, right = a.length - 1;
		TreeNode root = buildTreeFromSortedArrayHelper(a, left, right);
		return root;
	} 
	
	public static TreeNode buildTreeFromSortedArrayHelper(int[] a, int left, int right){
		if(left > right){
			return null;
		}
		int mid = (left + right)/2;
		TreeNode node = new TreeNode(a[mid]);
		node.left = buildTreeFromSortedArrayHelper(a, left, mid-1);
		node.right = buildTreeFromSortedArrayHelper(a, mid+1, right);
		return node;
	}
	
	public static void test(){
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = buildTreeFromSortedArray(a);
		ArrayList<Integer> preOrder1 = BinaryTreeTraversal.preOrderRec(root);
		ArrayList<Integer> preOrder2 = BinaryTreeTraversal.preOrderRec(root);
		System.out.println("preOrder");
		System.out.println(preOrder1);
		System.out.println(preOrder2);
		
		
		
	}
}
