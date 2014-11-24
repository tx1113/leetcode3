package arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class FourSums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Given an array S of n integers, are there elements a, b, c, and d in S 
	 * such that a + b + c + d = target? Find all unique quadruplets 
	 * in the array which gives the sum of target.
	 * Note:
	 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ² b ² c ² d)
	 * The solution set must not contain duplicate quadruplets.
	 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
	 *    A solution set is:
	 *    (-1,  0, 0, 1)
	 *    (-2, -1, 1, 2)
	 *    (-2,  0, 0, 2)
	 */
	
	public static ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length < 4) {
			return result;
		}
		Arrays.sort(num);
		for (int i=0; i < num.length; i++) {
			for (int j = i + 1; j<num.length ;j++) {
				int goal = target - num[i] - num[j];
				twoSums(num, i, j, goal, result);
				while (j + 1 < num.length && num[j+1] == num[j]) {
					j++;
				}
			}
			while(i + 1 < num.length && num[i+1] == num[i]) {
				i++;
			}
		}
		return result;
		
    }
	public static void twoSums(int[] num, int firstEleIndex,int secondEleIndex, int goal, 
			ArrayList<ArrayList<Integer>> result) {
		int start = secondEleIndex + 1;
		int end = num.length - 1;
		while (start < end) {
			int sum = num[start] + num[end];
			ArrayList<Integer> line = new ArrayList<Integer>();
			if (sum == goal) {
				line.add(num[firstEleIndex]);
				line.add(num[secondEleIndex]);
				line.add(num[start]);
				line.add(num[end]);
				
				result.add(line);
				while (start + 1 < end && num[start + 1] == num[start]) {
					start ++;
				}
				start ++;
				while (end - 1 > start && num[end - 1] == num[end]) {
					end --;
				}
				end --;
			} else if (sum < goal) {
				start ++;
			} else {
				end --;
			}
		}
	}
	

}
