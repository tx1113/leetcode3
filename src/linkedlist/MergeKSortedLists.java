package linkedlist;
import basicDS.*;
import java.util.*;

public class MergeKSortedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	//this method will time out
	public static ListNode mergeKLists(List<ListNode> lists) {
		if (lists.size() == 0) {
			return null;
		}
		ListNode p = lists.get(0);
		for (int i = 1; i<lists.size(); i++) {
			p = mergeTwoList(p, lists.get(i));
		}
		return p;
	}
	
	public static ListNode mergeTwoList(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		for (ListNode p = dummy; l1 != null || l2 != null; p = p.next) {
			int val1 = l1 !=null? l1.val: Integer.MAX_VALUE;
			int val2 = l2 != null? l2.val: Integer.MAX_VALUE;
			if (val1 <= val2) {
				p.next = l1;
				l1 = l1.next;
			}else {
				p.next = l2;
				l2 = l2.next;
			}
		}
		return dummy.next;
	}
	
	
	private static Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
		
		@Override
		public int compare(ListNode left, ListNode right) {
			// TODO Auto-generated method stub
			if (left == null) {
				return -1;
			} else if (right == null) {
				return -1;
			}
			return left.val - right.val;
		}
	};
	
	//we use a priority queue to implement a heap
	public static ListNode mergeKListsBetter(ArrayList<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}
		
		Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null) {
				heap.add(lists.get(i));
			}
		}
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		while (!heap.isEmpty()) {
			ListNode head = heap.poll();
			tail.next = head;
			tail = head;
			if (head.next != null) {
				heap.add(head.next);
			}
		}
		return dummy.next;
	}
}
