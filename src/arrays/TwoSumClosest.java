package arrays;

public class TwoSumClosest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Given a sorted array and a number x, find the pair in array whose sum is closest to x
	 * 
	 * Input: arr[] = {10, 22, 28, 29, 30, 40}, x = 54
	 * Output: 22 and 30
	 * Input: arr[] = {1, 3, 4, 7, 10}, x = 15
	 * Output: 4 and 10
	 * 
	 */
	public static int[] twoSumClosest(int[] num, int target) {
		if (num == null || num.length < 2) {
			return null;
		}
		int[] result = new int[2];
		int minDiff = Integer.MAX_VALUE;
		int start = 0, end = num.length - 1;
		while (start < end) {
			int sum = num[start] + num[end];
			if (Math.abs(target - sum) < minDiff) {
				minDiff = Math.abs(target - sum);
				result[0] = num[start];
				result[1] = num[end];
//				return result;
			}
			if (sum < target) {
				start ++;
			}else {
				end --;
			}
		}
		return result;
	}

}
