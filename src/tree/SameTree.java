package tree;

import java.util.Stack;

import basicDS.TreeNode;

public class SameTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * */
	public static boolean sameTree(TreeNode p, TreeNode q){
		if(p == null && q == null) //terminal condition
			return true;
		if(p == null || q == null) //trim
			return false;
		return p.value==q.value && sameTree(p.left, q.left) && sameTree(p.right, q.right);
	}
	
	public static boolean sameTreeIter(TreeNode p, TreeNode q){
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(p);
		st.push(q);
		while (!st.isEmpty()) {
			TreeNode q1 = st.peek();
			st.pop();
			TreeNode p1 = st.peek();
			st.pop();
			
			if(p1 == null && q1 == null)
				continue;
			if(p1 == null || q1 == null)
				return false;
			if(p1.value != q1.value)
				return false;
			
			st.push(p1.left);
			st.push(q1.left);
			
			st.push(p1.right);
			st.push(q1.right);
		}
		return true;
	}

}
