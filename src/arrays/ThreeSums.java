package arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	
	/*
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	 * Find all unique triplets in the array which gives the sum of zero.
	 * Note:
	 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ² b ² c)
	 * The solution set must not contain duplicate triplets.
	 */
	
	/*
	 * Solution1: 
	 * Sort the array O(n log n)
	 * (1) index1  0..n-1
	 * (2) index2: index1+1.. n-1
	 * (3) index3: binary search in index2..n-1
	 * 
	 * Total time: O(n^2 log n)
	 */
	
	/*
	 * solution2:
	 * (1) Sort the array.   O(n log n)
	 * (2) one pointer from the start of the array. O(n)
	 * (3) two from traverse the rest of the array. from ++ end -- O(n)
	 *     (2)(3) together O(n^2)
	 *  Total O(n^2)
	 */
	
	public static ArrayList<ArrayList<Integer>> threeSumSolution2(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
		if (num == null || num.length < 3) {
			return result;
		}
		
		Arrays.sort(num);
		for (int i=0; i < num.length; i++) {
			int target = -num[i];
			twoSums(num,i, target, result);
			while (i + 1 < num.length && num[i+1] == num[i]) {
				i++;
			}//trim
		}
		return result;
	}
	

	public static void twoSums(int[] num,int preElemIndex,  int target, ArrayList<ArrayList<Integer>> result ) {
		int start = preElemIndex + 1;
		int end = num.length - 1;
		while (start < end) {
			int sum = num[start] + num[end];
			if (sum == target) {
				ArrayList<Integer> line = new ArrayList<Integer>();
				line.add(num[preElemIndex]);
				line.add(num[start]);
				line.add(num[end]);
				
				//add line into result
			/*
				if(!result.contains(line)){
					result.add(line);
				};
			*/
				// !!! Actually, we don't need this. since with the trim,we won't produce duplicate
				result.add(line);
				//trim the start.
				while (start + 1 <= end && num[start + 1] == num[start]) {
					start ++;
				}
				start++; 			//!!! don't ignore this. 
				//trim the end
				while (end - 1 >= start && num[end - 1] == num[end]) {
					end --;
				}
				end --;
			} else if (sum < target) {
				//we need a larger start
				start ++;
			} else {
				end --;
			}
		}
	} 

	public static void test() {
//		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		int[] test1 = {0,0,0};
		int[] test2 = {1,-1,-1,0};
		int[] test3 = {-1,0,1};
//		System.out.println(threeSumSolution2(test1));
		System.out.println(threeSumSolution2(test3));
	}
	
}
