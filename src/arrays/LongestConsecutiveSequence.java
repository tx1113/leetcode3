package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/**
	 * 
	 * @param num
	 * @return
	 * 
	 * Given an unsorted array of integers, 
	 * find the length of the longest consecutive elements sequence.
	 * For example,
	 * Given [100, 4, 200, 1, 3, 2],
	 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	 * Your algorithm should run in O(n) complexity.
	 */
	
	/*
	 * Basic Idea:
	 * (1)Sort
	 * (2)Get the longest consecutive elements sequence
	 * 
	 * Time O(n log n)
	 */
	public static int longestConsecutive(int[] num) {
		if (num == null || num.length == 0) {
			return 0;
		}
		if (num.length == 1) {
			return num.length;
		}
		Arrays.sort(num);
		int maxLen = 1;
		int start = 0, end = 1;
		while ( end < num.length) {
			if (num[end] == num[end -1] + 1) {
				if (maxLen < (end - start) + 1) {
					maxLen = end - start + 1;
				}
			} else {
				start = end;
			}
			end ++;
		}
		
		return maxLen;
	}
	
	public static void test1() {
		int[] a = {1,2,3,5,6,7,8,9};
		System.out.println(longestConsecutive(a));
		System.out.println("method 2");
		int[] a2 = {1,2,3,5,6,7,8,9};
		System.out.println(longestConsecutive2(a2));
	}
	
	/*
	 * Idea 2:
	 * Use a hash map <Integer, boolean>, put all node into map.
	 * Use the boolean to determine whether the node has been visited. 
	 * from to each node, to left, right, every step is 1. 
	 * get the longest length 
	 */
	
	public static int longestConsecutive2(int[] num) {
		if (num == null || num.length == 0) {
			return 0;
		}
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		
		int maxLen = Integer.MIN_VALUE;
		for (int i = 0; i < num.length ; i++ ) {
			map.put(num[i], false);
		}
		
		for (int i = 0; i< num.length ; i++) {
			int cur = num[i];
			int curLen = 1;
			if (map.get(cur) == false) {
				curLen += getLength(map, cur-1, -1);
				curLen += getLength(map, cur+1, 1);
			}
			maxLen = Math.max(maxLen, curLen);
		}
		return maxLen;
	}
	public static int getLength(Map<Integer, Boolean> map , int eleVal, int step) {
		int len = 0;
		for (int i = eleVal; map.containsKey(i); i += step) {
			map.put(i, true);
			len ++;
		}
		System.out.println("len = " + len);
		return len;
	}

}
