package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.RootPaneContainer;

import basicDS.TreeNode;

public class SerializeBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
		test2();
//		test3();
//		test4();
	}
	
	public static String serialize_level(TreeNode root) {
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
	public static String serialize_pre(TreeNode root) {
		StringBuilder str = new StringBuilder();
		serialize_helper(root, str);
		return str.toString();
	}
	
	private static void serialize_helper(TreeNode node, StringBuilder str) {
		if (node == null) {
			str.append("# ");
			return;
		}
		str.append(node.value + " ");
		serialize_helper(node.left, str);
		serialize_helper(node.right, str);
	}
	
	
	public static void test2() {
		TreeNode root = new TreeNode(1);
		root.left = null;
		root.right = new TreeNode(2);
		String str = serialize_pre(root);
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
    }
    

}
