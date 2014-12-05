package arrays;

import java.util.*;

public class SpiralMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}
	
	/*
	 * Given a matrix of m x n elements (m rows, n columns), 
	 * return all elements of the matrix in spiral order.
	 * For example,
	 * Given the following matrix:
	 * [
	 * 	[ 1, 2, 3 ],
	 * 	[ 4, 5, 6 ],
	 * 	[ 7, 8, 9 ]
	 * ]
	 * 
	 * You should return [1,2,3,6,9,8,7,4,5].
	 */
	
	public static ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return result;
		}
		
		int beginX = 0, endX = matrix.length - 1;
		int beginY = 0, endY = matrix[0].length - 1;
		
		while (true) {
			for ( int i = beginY; i<=endY; i++) {
				result.add(matrix[beginX][i]);
			}
			beginX ++;
			if (beginX > endX) 
				break;
			
			for (int i = beginX; i <= endX; i++) {
				result.add(matrix[i][endY]);
			}
			endY --;
			if (beginY > endY) 
				break;
			
			for (int i = endY; i >= beginY; i--) {
				result.add(matrix[endX][i]);
			}
			endX --;  //!!! must be careful enough
			if (beginX > endX) 
				break;
			
			for (int i = endX; i >= beginX; i--) {
				result.add(matrix[i][beginY]);
			}
			beginY ++;
			if (beginY > endY) 
				break;
		}
		return result;	
	}
	
	public static void test1() {
		int[][] matrix = {
				{1, 2},
				{3, 4}
		};
		ArrayList<Integer> result = spiralOrder(matrix);
		System.out.println(result);
	}

	public static int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		if (n == 0) {
			return matrix;
		}
		int beginX = 0, endX = n - 1;
		int beginY = 0, endY = n-1;
		int num = 1;
		
		while (num <= n*n) {
			for ( int i = beginY; i<=endY; i++) {
				matrix[beginX][i] = num;
				num ++;
			}
			beginX ++;
	
			
			for (int i = beginX; i <= endX; i++) {
				matrix[i][endY] = num;
				num ++;
			}
			endY --;
		
			for (int i = endY; i >= beginY; i--) {
				matrix[endX][i] = num++;
			}
			endX --;  //!!! must be careful enough
			
			for (int i = endX; i >= beginX; i--) {
				matrix[i][beginY] = num ++;
			}
			beginY ++;
		}
		return matrix;
	}
	
	public static void test2() {
		int n = 3;
		int[][] matrix = generateMatrix(n);
		printMatrix(matrix);
		
	}
	
	public static void printMatrix(int[][] m) {
		for (int i=0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
