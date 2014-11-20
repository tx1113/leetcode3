package linkedlist;
import basicDS.*;

public class SortList {
	/*
	 * Sort a linked list in O(n log n) time using constant space complexity.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testFindMide();
		test();
	}

	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode mid = findMid(head);
		ListNode right = sortList(mid.next);
		mid.next = null;
		ListNode left = sortList(head);
		return mergeList(left, right);
	}
	
	public static ListNode findMid(ListNode head) {
		ListNode fast = head.next, slow = head;
		//here, pay much attention that fast = head.next, if here, 
		//we use fast = head, there will lead java.lang.StackOverflowError
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public static void testFindMide(){
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		ListNode mid = findMid(node1);
		System.out.println("mid.val = " + mid.val);
	}
	
	public static ListNode mergeList(ListNode node1, ListNode node2) {
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		while (node1 != null && node2 != null) {
			if (node1.val < node2.val) {
				tail.next = node1;
				node1 = node1.next;
			} else {
				tail.next = node2;
				node2 = node2.next;
			}
			tail = tail.next;
		}
		if (node1 != null) {
			tail.next = node1;
		}else {
			tail.next = node2;
		}
		return dummy.next;
	}
	
	public static void  test() {
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(1);
		node1.next = node2;
		ListNode head = sortList(node1);
		ListNode cur = head;
		while (cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.next;
		}
	}

}
