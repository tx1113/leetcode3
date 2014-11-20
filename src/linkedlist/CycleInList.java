package linkedlist;

import basicDS.ListNode;

public class CycleInList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}

	public static ListNode detectCycle(ListNode head) {
		// write your code here
		if (head == null || head.next == null) {
			return null;
		}

		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				break;
			}
		}
		if (fast == null || fast.next == null) {
			// the above is caused by execute the while loop, which means there
			// is no cycle
			// here we need to check fast == null or fast.next == null
			// since fast jump 2 steps every time.
			return null;
		}
		slow = head;
		while (slow != fast) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}

}
