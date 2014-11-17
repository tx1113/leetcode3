package tree;
import java.util.*;
import basicDS.*;
public class SearchRangeInBinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public static ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
		ArrayList<Integer> result = new ArrayList<Integer>();
		helper(root, k1, k2, result);
		return result;
    }
	
	//inOrder traversal with a small optimization.
	public static void helper(TreeNode node, int k1, int k2, ArrayList<Integer> result) {
		if (node == null) {
			return ;
		}
		
		if (node.value > k1) {
			//only when node.value > k1, we go node.left. 
			//otherwise, node.value < k1, its left subtree must < k1. 
			//			 we don't go left.
			// if we contain "=" here, its also OK
			helper(node.left, k1, k2, result);
		}
		if (node.value >= k1 && node.value <= k2) {
			result.add(node.value);
		}
		if (node.value < k2) {
			//the same idea as above. this is a small optimization.
			helper(node.right, k1, k2, result);
		}
	}
	
}
