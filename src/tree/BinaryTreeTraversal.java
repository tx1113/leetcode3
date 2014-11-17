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
		System.out.println(layersBFS(root));
		System.out.println(layersBFS2(root));
		System.out.println(layersDFS(root));
		
		System.out.println("zigzag:");
		System.out.println(zigzag(root));
		System.out.println(zigzagDFS(root));
		

		System.out.println("level one queue");
		System.out.println(levelBFS(root));
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
		if (root == null) {
			return postOrder;
		}
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
	public static ArrayList<Integer> layersBFS(TreeNode root){
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
	
	public static ArrayList<ArrayList<Integer>> layersBFS2(TreeNode root){
		ArrayList<ArrayList<Integer>> layers = new ArrayList<ArrayList<Integer>>();
		if(root == null)
			return layers;
		ArrayList<TreeNode> current = new ArrayList<TreeNode>();
		current.add(root);
		while(!current.isEmpty()){
			ArrayList<TreeNode> parent = current;
			current = new ArrayList<TreeNode>();
			ArrayList<Integer> cur_layer = new ArrayList<Integer>();
			for(int i=0; i<parent.size(); i++){
				//visit the element in the parent
				TreeNode cur_node = parent.get(i);
				//add the value into the cur_layer
				cur_layer.add(cur_node.value);
				if(cur_node.left != null){
					current.add(cur_node.left);
				}
				if(cur_node.right != null){
					current.add(cur_node.right);
				}
			}
			//we finish traversal one layer, add the cur_layer into layers
			layers.add(cur_layer);
		}
		return layers;
	}
	
	//DFS
	public static ArrayList<ArrayList<Integer>> layersDFS(TreeNode root){
		ArrayList<ArrayList<Integer>> layers = new ArrayList<ArrayList<Integer>>();
		dfs(root, layers, 0);
		return layers;
	}
	
	//here, the level of root is 0. root.left's level is 1. etc
	private static void dfs(TreeNode node, ArrayList<ArrayList<Integer>> layers, 
			 int level){
		if(node == null)
			return ;
		ArrayList<Integer> cur_layer = null;
		if(level == layers.size()){
			//we reach a new layer. we need to new a new ArrayList<Integer> for this layer
			cur_layer = new ArrayList<Integer>();
			//add the cur_layer into layers
			layers.add(cur_layer);
		}else{
			// the current layer is layers.get(level)
			cur_layer = layers.get(level);
		}
		//add the node.value into the cur_layer
		cur_layer.add(node.value);
		dfs(node.left, layers, level+1);
		dfs(node.right, layers, level+1);
	}
	
	//zigzag traversal
	/*
	 * e.g
	 * layer traversal: [[5], [2, 7], [1, 3, 6, 8], [4, 9]]
	 * zigzag 		  : [[5], [7, 2], [1, 3, 6, 8], [9, 4]]
	 * 
	 * idea: similar with the layersBFS
	 * */
	
	public static ArrayList<ArrayList<Integer>> zigzag(TreeNode root){
		ArrayList<ArrayList<Integer>> zigzag = new ArrayList<ArrayList<Integer>>();
		ArrayList<TreeNode> current = new ArrayList<TreeNode>();
		current.add(root);
		boolean odd_layer = false;
		
		while(!current.isEmpty()){
			ArrayList<TreeNode> parents = current;
			current = new ArrayList<TreeNode>();
			ArrayList<Integer> one_layer = new ArrayList<Integer>();
			for(int i=0; i<parents.size(); i++){
				TreeNode node = parents.get(i);
				if(odd_layer){
					one_layer.add(0, node.value);
				}else{
					one_layer.add(node.value);
				}
				if(node.left != null)
					current.add(node.left);
				if(node.right != null)
					current.add(node.right);
			}
			//we have finish the traverse of this layer, add it to zigzag
			zigzag.add(one_layer);
			odd_layer = !odd_layer;
		}
		return zigzag;
	}
	
	public static ArrayList<ArrayList<Integer>> zigzagDFS(TreeNode root){
		ArrayList<ArrayList<Integer>> zigzag = new ArrayList<ArrayList<Integer>>();
		zzdfs(root, zigzag, 0);
		return zigzag;
	}
	
	//here, the level of root is 0. root.left's level is 1. etc
		private static void zzdfs(TreeNode node, ArrayList<ArrayList<Integer>> layers, 
				 int level){
			if(node == null)
				return ;
			ArrayList<Integer> cur_layer = null;
			if(level == layers.size()){
				//we reach a new layer. we need to new a new ArrayList<Integer> for this layer
				cur_layer = new ArrayList<Integer>();
				//add the cur_layer into layers
				layers.add(cur_layer);
			}else{
				// the current layer is layers.get(level)
				cur_layer = layers.get(level);
			}
			//add the node.value into the cur_layer
			if(level%2 == 0){
				cur_layer.add(node.value);
			}else{
				cur_layer.add(0, node.value);
			}
			
			zzdfs(node.left, layers, level+1);
			zzdfs(node.right, layers, level+1);
		}
	
		//this method only use one queue
		public static ArrayList<ArrayList<Integer>> levelBFS(TreeNode root) {
			ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.add(root);
			
			while (!q.isEmpty()) {
				ArrayList<Integer> oneLevel = new ArrayList<Integer>();
				int size = q.size();
				for (int i = 0; i < size; i++) {
					TreeNode node = q.remove();
					oneLevel.add(node.value);
					if (node.left != null) {
						q.add(node.left);
					}
					if (node.right != null) {
						q.add(node.right);
					}
				}
				levels.add(oneLevel);
			}
			return levels;
		}
}
