package linkedlist;

import basicDS.ListNode;

public class FindMid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static ListNode findMid(ListNode head) {
		ListNode fast = head.next, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

}
