package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import basicDS.TreeNode;

public class SerializeBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
//		test2();
//		test3();
//		test4();
//		test5();
		test6();
//		test7();
	}
	
	public static String serializeLevel(TreeNode root) {
        // write your code here
		//level traversal
		StringBuilder str = new StringBuilder();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = q.remove();
				str.append(node.value);
				if (node.left == null) {
					
				}
			}
		}
		return null;
    }
	
	// preOrder Traversal
	// we passed in StringBuilder into the serialize_helper. 
	// Also, we use " " (space) as separator. 
	/* e.g   1
	 *      / \
	 *     2   3
	 *          \
	 *           4
	 * should be 1 2 # # # 3 4 # #
	 * if the node number is n, we need n + 1 "#".
	 * 
	 */
	public static String serializePre(TreeNode root) {
		StringBuilder str = new StringBuilder();
		serializeHelper(root, str);
		return str.toString();
	}
	
	private static void serializeHelper(TreeNode node, StringBuilder str) {
		if (node == null) {
			str.append("# ");
			return;
		}
		str.append(node.value + " ");
		serializeHelper(node.left, str);
		serializeHelper(node.right, str);
	}
	
	
	public static void test2() {
		TreeNode root = new TreeNode(1);
		root.left = null;
		root.right = new TreeNode(2);
		String str = serializePre(root);
		System.out.println(str);
	}

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public static TreeNode deserialize(String data) {
        // write your code here
    	if (data == null || data.length() == 0) {
			return null;
		}
    	StringTokenizer st = new StringTokenizer(data, " ");
    	return deserializeHelper(st);
    }
    
    /*
     * Here, we used StringTokenizer.
     * A StringTokenizer object internally maintains a current position 
     * within the string to be tokenized. 
     * Some operations advance this current position past the characters processed.
     */
    public static TreeNode deserializeHelper(StringTokenizer st) {
    	if (!st.hasMoreTokens()) {
			return null;
		}
    	String val = st.nextToken();
    	if (val.equals("#")) {
			return null;
		}
    	TreeNode node = new TreeNode(Integer.parseInt(val));
    	node.left = deserializeHelper(st);
    	node.right = deserializeHelper(st);
    	return node;
    }
    
    public static void test3(){
    	String str = "1#2##";
    	TreeNode root = deserialize(str);
    	BinaryTreeTraversal.preOrderIter1(root);
    	BinaryTreeTraversal.inOrder(root);
    	BinaryTreeTraversal.postOrder(root);
    }
    
    
    public static void test4() {
    	StringTokenizer st = new StringTokenizer("this is   a test");
    	while (st.hasMoreTokens()) {
			System.out.print(st.nextToken());			
		}
    	System.out.println();
    	String s = "this is   a test";
    	String[] result = s.trim().split("//s+");
    	for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
    }
    
   //without the space. 
  
    
    public static String serializePre2(TreeNode root) {
		StringBuilder str = new StringBuilder();
		serializeHelper2(root, str);
		return str.toString();
	}
	
	private static void serializeHelper2(TreeNode node, StringBuilder str) {
		if (node == null) {
			str.append("#");
			return;
		}
		str.append(node.value);
		serializeHelper2(node.left, str);
		serializeHelper2(node.right, str);
	}
	
	public static void test5() {
		TreeNode root = new TreeNode(1);
		root.left = null;
		root.right = new TreeNode(2);
		String str = serializePre2(root);	
		System.out.println(str);
	}
	
	
	//here, we use a index class to store the index of String. 
	//avoid using a global variable, since the Object could be passed by reference
	private static class Index {
		private int index;
		public Index(int i){
			this.index = i;
		}
		public int getIndex() {
			return index;
		}
		public void increase() {
			index ++;
		}
	}
	
	public static TreeNode deserialize2(String data) {
	        // write your code here
	    	if (data == null || data.length() == 0) {
				return null;
			}
	    	Index strIndex = new Index(0);
	    	return deserializeHelper2(data, strIndex);
	    }
	    
	    public static TreeNode deserializeHelper2(String st, Index strIndex) {
	    	if(strIndex.index >= st.length() ) {
	    		return null;
	    	}
	    	if( st.charAt(strIndex.getIndex()) == '#') {
	    		strIndex.increase();
	    		System.out.println(strIndex.getIndex());
	    		return null;
	    	}
	    	char val = st.charAt(strIndex.getIndex());
	    	strIndex.increase();
	    	int valInt = (int)(val - '0');
	    	TreeNode node = new TreeNode(valInt);
	    	node.left = deserializeHelper2(st, strIndex);
	    	node.right = deserializeHelper2(st, strIndex);
	    	return node;
	    }
	    
	    public static void test6() {
	    	String str = "1#2##";
	    	TreeNode root = deserialize2(str);
	    	System.out.println(BinaryTreeTraversal.preOrderIter1(root));
	    	System.out.println(BinaryTreeTraversal.inOrder(root));
	    	System.out.println(BinaryTreeTraversal.postOrder(root));
	    }
	    
	    public static void test7() {
	    	//used to test the convert from char to int
	    	char ch = '9';
	    	int chi = (int)(ch - '0');
	    	System.out.println("chi = " + chi);
	    }
	    
	    
	    
}
