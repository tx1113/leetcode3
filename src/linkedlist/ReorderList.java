package linkedlist;

import basicDS.ListNode;

public class ReorderList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testFindMid();
		testReverse();
	}
	
	public static void reorderList(ListNode head){
		if (head == null) {
			return ;
		}
		ListNode mid = findMid(head);
		ListNode secondHead = mid.next;
		mid.next = null;
		
		//reverse the second part
		ListNode cur2 = reverseList(secondHead);
		
		//merge the first and second
		ListNode cur1 = head;
		ListNode cur2Next = null, cur1Next = null;
		while (cur1 != null && cur2 != null) {
			cur1Next = cur1.next;
			cur2Next = cur2.next;
			cur2.next = cur1.next;
			cur1.next = cur2;
			
			//update cur1, cur2
			cur1 = cur1Next;
			cur2 = cur2Next;
		}
		
	}
	
	
	//e.g  1,2,3,4,5  mid = 3;
	//	   1,2,3,4    mid = 2;
	public static ListNode findMid(ListNode head) {
		ListNode fast = head.next, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	

	
	public static ListNode reverseList(ListNode head) {
		// if head == null
		if (head == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode cur = head.next;
		head.next = null;  
		//here, we must break the head.next, or else, there would be loop
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = dummy.next;
			dummy.next = cur;
			cur = next;
		}
		return dummy.next;
	}
	
	public static void testReverse() {
		ListNode head = buildList();
//		ListNode head = null;
		ListNode n_head = reverseList(head);
		while (n_head!= null) {
			System.out.print(n_head.val + " ");
			n_head = n_head.next;
		}
		System.out.println();
	} 
	
	public static ListNode buildList() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		return node1;
	}
	
	
	public static void testFindMid() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		
		ListNode mid = findMid(node1);
		System.out.println("mid.val = " + mid.val);
	}

}
