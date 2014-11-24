package arrays;

import java.util.Arrays;

public class ThreeSumClosest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * Given an array S of n integers, 
	 * find three integers in S such that the sum is closest to a given number, target. 
	 * Return the sum of the three integers. 
	 * You may assume that each input would have exactly one solution.
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 */
	
	public static int threeSumClosest(int[] num, int target) {
		if (num == null || num.length < 3) {
			return Integer.MIN_VALUE;
		}
		Arrays.sort(num);
		int result = num[0] + num[1] + num[2];
		for (int i = 0; i < num.length; i++) {
			while (i > 0 && num[i] == num[i - 1]) {
				i++;
			}
			
			int start = i+1;
			int end = num.length - 1;
			while (start < end) {
				int current_result = num[i] + num[start] + num[end];
				//update the result
				if (Math.abs(target - current_result) < Math.abs(target - result) ) {
					result = current_result;
				}
				if (result == target) {
					return result;
				}
				if (current_result < target) {
					start ++;
				} else {
					end --;
				}
			}
		}
		return result;
	}
	
	
	public static int threeSumClosest2(int[] num, int target) {
		// Note: The Solution object is instantiated only once and is reused by
		// each test case.
		if (num == null || num.length < 3) {
			return Integer.MAX_VALUE;
		}
		Arrays.sort(num);
		int closet = Integer.MAX_VALUE / 2; // otherwise it will overflow for
											// opeartion (closet-target)'
		for (int i = 0; i < num.length - 2; i++) {
			int left = i + 1;
			int right = num.length - 1;
			while (left < right) {
				int sum = num[i] + num[left] + num[right];
				if (sum == target) {
					return sum;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
				closet = Math.abs(sum - target) < Math.abs(closet - target) ? sum
						: closet;
			}
		}
		return closet;
	}

}
