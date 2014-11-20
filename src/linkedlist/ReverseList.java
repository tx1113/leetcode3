package linkedlist;

import basicDS.ListNode;

public class ReverseList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static ListNode reverseRec(ListNode head) {
		return null;
	}
	
	
	public static ListNode reverseIter(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode cur = head.next;
		head.next = null;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = dummy.next;
			dummy.next = cur;
			cur = next;
		}
		return dummy.next;
	}

}
