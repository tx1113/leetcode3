package linkedlist;

import basicDS.ListNode;

public class RemoveDuplicatesFromSortedList2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Given a sorted linked list, delete all nodes that have duplicate numbers,
	 * leaving only distinct numbers from the original list. For example, Given
	 * 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3, return 2->3.
	 */

	public static ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode tail = dummy, cur = head, prev = head;
		// at first, cur and prev both points to head;
		// the prev is used to help to check duplicates

		while (cur != null && cur.next != null) {
			// here, we need to make sure that cur != null and cur.next!= null;
			// if we only write the cur.next != null, there would be NullPointer
			// Error if the cur == null
			// since if cur == null, cur.next would result in error

			while (cur.next != null && cur.val == cur.next.val) {
				cur = cur.next;
			}// if there exists duplicates
				// after execution of the loop, cur points the last element of a
				// series duplicates

			if (prev == cur) {
				// there is no duplicates. the above loop doesn't exist.
				// the prev and cur are still the same
				tail.next = cur;
				// we use the tail.next to link cur, now the cur is an unique
				// element.
				tail = cur;
				// update tail.
			}
			cur = cur.next;
			// if no duplicates, we need to go next.
			// if there are duplicates, since we don't want the duplicating
			// elements,
			// we also need to go to next element.
			prev = cur;
			// update prev = cur.
		}
		tail.next = cur;
		return dummy.next;
	}

}
