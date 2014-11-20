package linkedlist;


import basicDS.*;

public class PartitionList {

	/*
	 * Given a linked list and a value x, partition it such that all nodes less
	 * than x come before nodes greater than or equal to x. You should preserve
	 * the original relative order of the nodes in each of the two partitions.
	 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	//this is a great method
	public static ListNode partition(ListNode head, int x) {
		if (head == null)
			return null;
		ListNode leftDummy = new ListNode(-1);
		ListNode rightDummy = new ListNode(-1);
		ListNode left = leftDummy, right = rightDummy;
		
		while (head != null) {
			if (head.val < x) {
				left.next = head;
				left = head;
			} else {
				right.next = head;
				right = head;
			}
			head = head.next;
		}
		right.next = null;
		left.next = rightDummy.next;
		return leftDummy.next;
	}
	

	/*
	 * Basic idea for this is to throw the list node whose value is larger than x to the tail
	 */
	public static ListNode partition_wrong(ListNode head, int x) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy, cur = head;
		ListNode tail = dummy;
		while (tail != null && tail.next != null) {
			tail = tail.next;
		}
		// System.out.println("tail.val =" + tail.val ) ;
		// the tail is the tail of the linkedlist.
		//!!! There is infinite loop inside, need to debug
		while (cur != null) {
			if (cur.val > x) {
				// delete the cur
				if (cur == tail) {
					break;
				} else {
					prev = cur.next;
					// add the cur to the tail
					tail.next = cur;
					// update tail
					tail = tail.next;
					// update cur
					cur = prev.next;
				}

			} else {
				prev = cur;
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	public static void test() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		node1.next = node2;
		printList(node1);
		ListNode part = partition(node1, 0);
		printList(part);

	}

	public static void printList(ListNode node) {
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
	}

}
