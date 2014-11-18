package tree;

import basicDS.Tree;
import basicDS.TreeNode;

import java.util.*;

public class BinaryTreeTraversalMorris {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testInOrder();
//		testPreOrder();
		testPostOrder();
	}
	
	/*
	 * Morris InOrder Traversal
	 * (1) if current.left == null, PRINT current.val, current = current.right
	 * (2) if current.left != null, get rightmost node of left subtree of current.
	 * 		(2.1) if the rightmost node.right == null, node.right = current
	 * 			  current = current.left;
	 * 		(2.2) if the rightmost node.right == current, 
	 * 					node.right = null (recover the tree)
	 * 					PRINT current.value
	 * 					current = current.right;
	 * repeat (1) (2) until current == null
	 */
	public static ArrayList<Integer> inOrder(TreeNode root) {
		ArrayList<Integer> inorder = new ArrayList<Integer>();
		if (root == null) {
			return inorder;
		}
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				inorder.add(cur.value);
				cur = cur.right;
			} else {
				TreeNode predecessor = cur.left;
				while (predecessor.right != null && predecessor.right != cur) {
					//here, we should use && . since when we meet either one of them, 
					//we need to break the loop
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = cur;
					cur = cur.left;
				} else {
					//predecessor.right == cur
					predecessor.right = null;
					//output the cur.val
					inorder.add(cur.value);
					cur = cur.right;
				}	
			}
		}
		return inorder;
	}
	
	public static TreeNode buildTree() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.buildTreeFromSortedArray(a);
		return root;
	}
	
	public static void testInOrder() {
		TreeNode root = buildTree();
		ArrayList<Integer> inOrder = inOrder(root);
		System.out.println(inOrder);
		System.out.println(BinaryTreeTraversal.inOrder(root));
	}
	
	/* preOrder
	 * (1) if cur.left == null, it's the leaf node, PRINT it. cur = cur.right. 
	 * 			(the right points to its inOrder successor)
	 * (2) if cur.left != null, get the inOrder predecessor of cur in cur.left subtree.
	 * 	(2.1) if predecessor.right == null, 
	 * 						PRINT the cur.val
	 * 						predecessor.right = cur;
	 * 						cur = cur.left;
	 *  (2.2) if predecessor.right == cur
	 *  					predecessor.right = null;
	 *  					cur = cur.right;
	 * repeat (1)(2) until cur == null
	 */
	
	public static ArrayList<Integer> preOrder(TreeNode root) {
		ArrayList<Integer> preorder = new ArrayList<Integer>();
		if(root == null) {
			return preorder;
		}
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				preorder.add(cur.value);
				cur = cur.right;
			} else {
				//cur.left != null
				TreeNode predec = cur.left;
				while (predec.right != null && predec.right != cur) {
					predec = predec.right;
				}
				if(predec.right == null ) {
					//first time we get here.
					//print the cur.val
					preorder.add(cur.value);
					predec.right = cur;
					cur = cur.left; //go left
				}else {
					//predec.right == cur
					//recover the tree
					predec.right = null;
					cur = cur.right;
				}
			}
		}
		return preorder;
	}
	public static void testPreOrder() {
		TreeNode root = buildTree();
		System.out.println(preOrder(root));
		System.out.println(BinaryTreeTraversal.preOrderRec(root));
	}
	
	
	
	/*
	 * PostOrder 
	 * we need create a TreeNode dump. 
	 * 	   dump.left = root;
	 * let cur = dump
	 * (1) if cur.left == null
	 * 			cur = cur.right;
	 * (2) if cur.left != null, get the inOrder predecessor(pre) of cur in cur.lfetsubstree.
	 * 		(2.1) if pre.right == null
	 * 				 pre.right = cur;
	 * 		(2.2) if pre.right == cur
	 * 				 pre.right = null
	 * 			     PRINT reversely from = cur.left, to = pre
	 * 				 cur = cur.right;
	 * repeat (1)(2) until cur == null
	 */
	public static ArrayList<Integer> postOrder(TreeNode root){
		ArrayList<Integer> postorder = new ArrayList<Integer>();
		if(root == null)
			return postorder;
		TreeNode dump = new TreeNode(-1);
		dump.left = root;
		TreeNode cur = dump;
		TreeNode pre = null;
		
		while (cur != null) {
			if (cur.left == null) {
				cur = cur.right;
			} else {
				//cur.left != null
				pre = cur.left;
				while (pre.right != null && pre.right != cur) {
					pre = pre.right;
				}
				if (pre.right == null) {
					pre.right = cur;
					cur = cur.left;
				} else {
					printReverse(cur.left, pre, postorder);
					pre.right = null;
					cur = cur.right;
				}
			}
		}
		return postorder;
	}
	
	private static void reverse(TreeNode from, TreeNode to) {
		if (from == to) {
			return ;
		}
		TreeNode x = from, y = from.right;
		TreeNode z = null;
		while (true) {
			z = y.right;
			y.right = x;
			x = y;
			y = z;
			if (x == to)
				break;
		}
	}
	
	private static void printReverse(TreeNode from, TreeNode to, ArrayList<Integer> postorder) {
		reverse(from, to);
		TreeNode p = to;
		while (true) {
			postorder.add(p.value);
			if (p == from) {
				break;
			}
			p = p.right;
		}
		reverse(to, from);
	}
	
	public static void testPostOrder() {
		TreeNode root = buildTree();
		System.out.println(postOrder(root));
		System.out.println(BinaryTreeTraversal.postOrderRec(root));
	}
	
	
}
