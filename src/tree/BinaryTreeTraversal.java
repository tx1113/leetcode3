package tree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


import basicDS.*;

public class BinaryTreeTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = buildTree();
		System.out.println("preOrder:");
		System.out.println(preOrderRec(root));
		System.out.println(preOrderIter1(root));
		System.out.println(preOrderIter2(root));
		
		System.out.println("inOrder:");
		System.out.println(inOrderRec(root));
		System.out.println(inOrder(root));
		
		System.out.println("postOrder:");
		System.out.println(postOrderRec(root));
		System.out.println(postOrder(root));
		
		System.out.println("layer:");
		System.out.println(layerBFS(root));
	}
	
	
	public static TreeNode buildTree(){
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.buildTreeFromSortedArray(a);
		return root;
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
	public static ArrayList<Integer> preOrderIter1(TreeNode root){
		ArrayList<Integer> preOrder= new ArrayList<Integer>();
		if(root == null)
			return preOrder;
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		while(!st.isEmpty()){
			TreeNode cur = st.peek();
			st.pop();
			preOrder.add(cur.value);
			if(cur.right != null){
				st.add(cur.right);
			}
			if(cur.left != null){
				st.add(cur.left);
			}
		}
		return preOrder;
	}
	public static ArrayList<Integer> preOrderIter2(TreeNode root){
		ArrayList<Integer> preOrder= new ArrayList<Integer>();
		if(root == null)
			return preOrder;
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode node = root;
		while(node != null || !st.isEmpty()){
			if(node != null){
				//this node is not null, visit it
				preOrder.add(node.value);
				//push the node into stack
				st.push(node);
				node = node.left;
			}else{
				//current node is null, pop from the stack
				TreeNode cur = st.peek();
				st.pop();
				node = cur.right;
			}
		}
		return preOrder;
	}
	
	//inOrder
	public static ArrayList<Integer> inOrderRec(TreeNode root){
		ArrayList<Integer> inOrder = new ArrayList<Integer>();
		inHelper(root, inOrder);
		return inOrder;
	}
	private static void inHelper(TreeNode node, ArrayList<Integer> inOrder){
		if(node == null)
			return ;
		inHelper(node.left, inOrder);
		inOrder.add(node.value);
		inHelper(node.right, inOrder);	
	}
	
	public static ArrayList<Integer> inOrder(TreeNode root){
		ArrayList<Integer> inOrder = new ArrayList<Integer>();
		if(root == null)
			return inOrder;
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode node = root;
		while(node != null || !st.isEmpty()){
			if(node != null){
				st.push(node);
				node = node.left;
			}else{
				//get node from the stack
				node = st.peek();
				st.pop();
				inOrder.add(node.value);
				node = node.right;
			}
		}
		return inOrder;
	}
	
	public static ArrayList<Integer> morrisInOrder(TreeNode root){
		return null;
	}
	
	
	//postOrder
	public static ArrayList<Integer> postOrderRec(TreeNode root){
		ArrayList<Integer> postOrder = new ArrayList<Integer>();
		if(root == null)
			return postOrder;
		postOrderHelper(root, postOrder);
		return postOrder;
	} 
	public static void postOrderHelper(TreeNode node, ArrayList<Integer> postOrder){
		if (node == null) {
			return ;
		}
		postOrderHelper(node.left, postOrder);
		postOrderHelper(node.right, postOrder);
		postOrder.add(node.value);
	}
	
	public static ArrayList<Integer> postOrder(TreeNode root){
		ArrayList<Integer> postOrder = new ArrayList<Integer>();
		Stack<TreeNode> st = new Stack<TreeNode>();
		Stack<Integer> output = new Stack<Integer>();
		st.push(root);
		while(!st.isEmpty()){
			TreeNode cur = st.peek();
			st.pop();
			output.push(cur.value);
			if(cur.left != null)
				st.push(cur.left);
			if(cur.right != null)
				st.push(cur.right);
		}
		while (!output.isEmpty()) {
			postOrder.add(output.peek());
			output.pop();
		}
		return postOrder;
	}
	
	//layer traversal
	//BFS
	public static ArrayList<Integer> layerBFS(TreeNode root){
		ArrayList<Integer> layer = new ArrayList<Integer>();
		if(root == null)
			return layer;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode cur = q.remove();
			//visit the cur
			layer.add(cur.value);
			if(cur.left != null)
				q.add(cur.left);
			if(cur.right != null)
				q.add(cur.right);
		}
		return layer;
	}
	
	
	
	
	
	

}
