package tree;

import java.util.Stack;

import basicDS.TreeNode;

public class FlattenBinaryTreeToLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * e.g
	 *      1
	 *     / \
	 *    2   5
	 *   / \   \
	 *  3   4   6
	 *  
	 *  the flatten tree should like
	 *   1
	 *    \
	 *     2
	 *      \
	 *       3
	 *        \
	 *         4
	 *          \
	 *           5
	 *            \
	 *             6
	 */
	
	/*
	 * iteration: idea
	 * user the preOrder traversal. 
	 * st.add(root)
	 * while !st.isEmpty()
	 *    get the st.top() as node
	 *    push node.right into st   
	 *    push node.left into st
	 *    
	 *    let node.right = it's left, which is the current top element of the stack. 
	 *    of course, we need to check the !st.isEmpty()
	 * 
	 */
	
	public static void flattenIter(TreeNode root){
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		while(!st.isEmpty()){
			TreeNode p = st.peek();
			st.pop();
			if(p.right != null)
				st.push(p.right);
			if(p.left != null)
				st.push(p.left);
			
			p.left = null;
			if(!st.isEmpty()){
				p.right = st.peek();
			}
		}
	}

	public static void test(){
		TreeNode root = BinaryTreeTraversal.buildTree();
		flattenIter(root);
		printLinkedList(root);
		System.out.println("\n--------------");
		TreeNode root2 = BinaryTreeTraversal.buildTree();
		flattenRec(root2);
		printLinkedList(root2);
	}
	
	public static void printLinkedList(TreeNode root){
		TreeNode node = root;
		while(node != null){
			//visit the node
			System.out.print(node.value + " ");
			node = node.right;
		}
		System.out.println();
	}
	
	public static void flattenRec(TreeNode node){
		if(node == null)
			return ;
		
		flattenRec(node.left);
		flattenRec(node.right);
		
		//!!! here it is very important. 
		if(node.left == null)
			return ;
		TreeNode p = node.left;
		while(p.right != null){
			p = p.right;
			//get the right most element of left subtree
		}
		p.right = node.right;
		node.right = node.left;
		node.left = null;
	}
	
	/*
	 * This doesn't work, since we need to store the node.right. 
	 */
	public static void flatten_wrong(TreeNode root){
		if(root == null)
			return ;
		TreeNode node = root;
		Stack<TreeNode> st = new Stack<TreeNode>();
		while (node != null || !st.isEmpty()) {
			if(node != null){
				st.push(node);
				node = node.left;
			}else{
				node = st.peek();
				st.pop();
				TreeNode parent = st.peek();
				TreeNode cur_rightmost = node;
				while(cur_rightmost.right != null){
					cur_rightmost = cur_rightmost.right;
				}
				cur_rightmost.right = parent.right;
				parent.right = node;
				node = node.right;
			}
		}
	}
	
	
	

}
