package linkedlist;
import basicDS.*;
public class RemoveDuplicatesFromSortedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static ListNode deleteDuplicates(ListNode head) {
		// write your code here
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode tail = dummy, cur = head;
		
		while(cur != null && cur.next != null) {
			//find the duplicates
			while (cur.next != null && cur.val == cur.next.val) {
				cur = cur.next;
			}
			//!!! here, we need to pay attention that cur.next != null
			// 	  since we need to make sure that cur.next.val won't throw null pointer error
			// if there exists duplicates
			// after execution of the loop, cur points the last element of a
			// series duplicates
			
			tail.next = cur;
			tail = cur;
			cur = cur.next;
		}
		return dummy.next;
	}
	

}
