package tree;

import java.util.Arrays;

import basicDS.*;
public class ConstructBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * Given preOrder and inOrder traversal of a tree, construct the binary tree.
	 * Note:
	 * You may assume that duplicates do not exist in the tree.
	 */
	public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        if(preorder.length == 0){
        	return null;
        }
		TreeNode root = new TreeNode(preorder[0]);
        int i = 0; 
        for(; i< inorder.length; i++){
        	if(inorder[i] == root.value)
        		break;
        }
        //the current value of i is the root position. 
        //preorder[1, i], inorder[0, i-1]
        //preorder[i+1, preorder.length - 1], inorder[i+1, inorder.length - 1]
        //!!! pay attention to Arrays.copyOfRange(int[], int start, int end + 1)
        if(i > 0){
        	//make sure that i > 0
        	root.left = buildTreePreIn(Arrays.copyOfRange(preorder, 1, i+1), 
        							   Arrays.copyOfRange(inorder, 0, i));
        }
        if(i < inorder.length){
        	root.right = buildTreePreIn(Arrays.copyOfRange(preorder, i+1, preorder.length), 
                	Arrays.copyOfRange(inorder, i+1, inorder.length));
        }
        
        return root;
    }
	
	public static void test(){
		int[] preOrder = {5,2,1,3,4,7,6,8,9};
		int[] inOrder = {1,2,3,4,5,6,7,8,9};
		int[] postOrder = {1, 4, 3, 2, 6, 9, 8, 7, 5};
		TreeNode root = buildTreePreIn(preOrder, inOrder);
//		TreeNode root2 = buildTreePreIn2(preOrder, inOrder);
		System.out.println(BinaryTreeTraversal.preOrderIter1(root));
		System.out.println(BinaryTreeTraversal.inOrder(root));
		System.out.println(BinaryTreeTraversal.postOrder(root));
		
		
		System.out.println("----------------------------------");
		TreeNode root2 = buildTreeInPost(inOrder, postOrder);
		System.out.println(BinaryTreeTraversal.preOrderIter1(root2));
		System.out.println(BinaryTreeTraversal.inOrder(root2));
		System.out.println(BinaryTreeTraversal.postOrder(root2));
		
//		System.out.println("-------------------");
//		System.out.println(BinaryTreeTraversal.preOrderIter1(root2));
//		System.out.println(BinaryTreeTraversal.inOrder(root2));
//		System.out.println(BinaryTreeTraversal.postOrder(root2));
		
	}
	
	//save space
	/*
	 * This is not OK. Since with the position change
	 * Although the length of preOrder and inOrder are same, 
	 * their index are different. 
	 */
//	public static TreeNode buildTreePreIn2(int[] preOrder, int[] inOrder){
//		int pStart = 0, pEnd = preOrder.length - 1;
//		int iStart = 0, iEnd = inOrder.length  - 1;
//		return buildTreePIHelper(preOrder, pStart, pEnd, inOrder, iStart, iEnd);
//	}
//	public static TreeNode buildTreePIHelper(int[] preOrder, int pStart, int pEnd,
//											 int[] inOrder,  int iStart, int iEnd){
//		if(pStart > pEnd || iStart > iEnd){
//			return null;
//		}
//		TreeNode node = new TreeNode(preOrder[pStart]);
//		int i = iStart;
//		for(; i<=iEnd; i++){
//			if(inOrder[i] == node.value){
//				break;
//			}
//		}
//		
//		node.left = buildTreePIHelper(preOrder, pStart+1, i, inOrder, iStart, i-1);
//		node.right = buildTreePIHelper(preOrder, i+1, pEnd, inOrder, i+1, iEnd);
//		return node;
//	}
	
	
	
	 public static TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
		 if(postorder.length == 0)
			 return null;
		 TreeNode root = new TreeNode(postorder[postorder.length - 1]);
		 int i = 0; 
		 for(; i < inorder.length ; i++){
			 if(inorder[i] == root.value){
				 break;
			 }
		 }
		 if(i > 0){
			 root.left = buildTreeInPost(Arrays.copyOfRange(inorder, 0, i),
					 Arrays.copyOfRange(postorder, 0, i));
		 }
		 if(i < inorder.length){
			 root.right = buildTreeInPost(Arrays.copyOfRange(inorder, i+1, inorder.length), 
					 Arrays.copyOfRange(postorder, i, postorder.length - 1));
		 }
		 return root;
	 }
	
	

}
