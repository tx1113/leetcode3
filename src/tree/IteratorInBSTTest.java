package tree;

import basicDS.Tree;
import basicDS.TreeNode;

public class IteratorInBSTTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = buildTree();
		System.out.println(BinaryTreeTraversal.preOrderRec(root));
		System.out.println(BinaryTreeTraversal.inOrder(root));
		System.out.println(BinaryTreeTraversal.postOrder(root));
		
//		IteratorInBinarySearchTreeMorris it = new IteratorInBinarySearchTreeMorris(root);
//		while (it.hasNext()) {
//			System.out.print(it.next().value + " ");
//		}
	}
	public static TreeNode buildTree() {
		int[] a = {2,1};
		TreeNode root = Tree.buildTreeFromSortedArray(a);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(1);
		TreeNode node3 = new TreeNode(3);
		node1.left = node2;
		node1.right = node3;
		return node1;
	}
}
