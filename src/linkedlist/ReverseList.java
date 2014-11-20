package linkedlist;

import basicDS.ListNode;

public class ReverseList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static ListNode reverseRec(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode newTail = head.next;
		// new tail should be the head.next.

		ListNode newHead = reverseRec(head.next);
		// return the new head.

		newTail.next = head;
		head.next = null;
		return newHead;
	}

	// use a dummy node
	// from the second node to the last, insert each node into dummy and first
	// node.

	public static ListNode reverseIter1(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode cur = head.next;

		// !!! here we need to set the head.next = null, otherwise,
		// there would exist cycle.
		head.next = null;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = dummy.next;
			dummy.next = cur;
			cur = next;
		}
		return dummy.next;
	}

	// the idea
	public static ListNode reverseIter2(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode prev = head;
		ListNode cur = head.next;
		head.next = null; // here, we need to set head.next as null
		while (cur != null) {
			ListNode next = cur.next; // store the next position
			cur.next = prev; // points to its previous node
			prev = cur; // update prev to cur
			cur = next; // update cur as next
		}
		// at last, the cur will point null. return prev as the head node
		head = prev;
		return head;
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

	public static void test() {
		ListNode head = buildList();
		// ListNode head2 = reverseIter2(head);
		ListNode head2 = reverseRec(head);
		printList(head2);
	}

	public static void printList(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.next;
		}
		System.out.println();
	}

}
