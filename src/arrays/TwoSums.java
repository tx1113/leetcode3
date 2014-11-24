package arrays;

import java.util.HashMap;
import java.util.Map;

public class TwoSums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Given an array of integers, 
	 * find two numbers such that they add up to a specific target number.
	 * The function twoSum should return indices of the two numbers 
	 * such that they add up to the target, where index1 must be less than index2. 
	 * Please note that your returned answers (both index1 and index2) are not zero-based.
	 * You may assume that each input would have exactly one solution.
	 * Input: numbers={2, 7, 11, 15}, target=9
	 * Output: index1=1, index2=2
	 */
	
	/*
	 * Brute force
	 * Time(n^2)
	 */
	
	/*
	 * Use hashMap
	 * Time(n) Space: O(n)
	 */
	
	public static int[] twoSum(int[] numbers, int target) {
		if (numbers == null || numbers.length < 2) {
			return null;
		}
		Map<Integer, Integer> hs = new HashMap<Integer, Integer>();
		for (int i=0; i<numbers.length; i++) {
			hs.put(numbers[i], i+1);
		}
		int[] a = new int[2];
		for (int i=0; i<numbers.length; i++) {
			if (hs.containsKey(target - numbers[i])) {
				int index1 = i + 1;
				int index2 = hs.get(target - numbers[i]);
				if (index1 == index2) {
					//in case for the same elements add two the sum
					continue;
				}
				a[0] = index1;
				a[1] = index2;
				return a;
			}
		}
		return a;
	}

}
