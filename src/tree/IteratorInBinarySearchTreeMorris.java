package tree;

import basicDS.TreeNode;

public class IteratorInBinarySearchTreeMorris {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public TreeNode cur;

	public IteratorInBinarySearchTreeMorris(TreeNode root) {
		this.cur = root;
	}

	// @return: True if there has next node, or false
	public boolean hasNext() {
		// write your code here
		return cur != null;
	}

	// @return: return next node
	public TreeNode next_wrong() {
		TreeNode node = null;
		// write your code here
		if (cur.left == null) {
			node = cur;
			cur = cur.right;
			System.out.print(node.value + " a ");
			// return node;
		} else {
			// cur.left != null
			TreeNode pre = cur.left;
			while (pre.right != null && pre.right != cur) {
				pre = pre.right;
			}
			if (pre.right == null) {
				pre.right = cur;
				cur = cur.left;
			} else {
				node = cur;
				pre.right = null;
				cur = cur.right;
				// System.out.print(node.value + " b ");
				// return node;
			}
		}
		return node;
	}
	
	
	//this one also doesn't work well
	public TreeNode next() {
		TreeNode node = null;
		if (cur.left == null) {
			node = cur;
			cur = cur.right;
			return node;
		}
		
		while (cur.left != null) {
			TreeNode pre = cur.left;
			while (pre.right != null && pre.right != cur) {
				pre = pre.right;
			}
			if (pre.right == null) {
				pre.right = cur;
				cur = cur.left;
			} else {
				node = cur;
				pre.right = null;
				cur = cur.right;
				return node;
			}
		}
		
		return node;
	}
}
