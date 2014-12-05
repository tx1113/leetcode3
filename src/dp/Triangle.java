package dp;
import java.util.*;

public class Triangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
//		test2();
	}
	
	/*
	 * Top-Bottom
	 * 
	 * f[i][j] when we reach triangle[i][j], the minimum cost 
	 * Note: edge case
	 */
	public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
		int rowL = triangle.size();
		int colL = triangle.get(rowL - 1).size();
		int[][] min = new int[rowL][colL];
		min[0][0] = triangle.get(0).get(0);
		for (int i = 1; i < rowL; i++) {
			for (int j = 0; j < triangle.get(i).size(); j++) {
				if (j == 0) {
					// can only get from its above 
					min[i][j] = min[i-1][j] + triangle.get(i).get(j);
				}else if (j == triangle.get(i).size() - 1){
					min[i][j] = min[i-1][j-1] + triangle.get(i).get(j);
				}else {
					//general case
					min[i][j] = Math.min(min[i-1][j], min[i-1][j-1]) + triangle.get(i).get(j);
				}
			}
		}
		//traversal the last layer.
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < min[rowL - 1].length; i++) {
			if (min[rowL - 1][i] < minSum) {
				minSum = min[rowL - 1][i];
			}
		}
		return minSum;
	}
	
	public static void test() {
		ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> line1 = new ArrayList<Integer>();
		line1.add(2);
		ArrayList<Integer> line2 = new ArrayList<Integer>();
		line2.add(3);
		line2.add(4);
		ArrayList<Integer> line3 = new ArrayList<Integer>();
		line3.add(6);
		line3.add(5);
		line3.add(7);
		ArrayList<Integer> line4 = new ArrayList<Integer>();
		line4.add(4);
		line4.add(1);
		line4.add(8);
		line4.add(3);
		triangle.add(line1);
		triangle.add(line2);
		triangle.add(line3);
		triangle.add(line4);
		
		int minSum = minimumTotal(triangle);
		System.out.println("minSum = " + minSum);
		
		int minSum2 = minimumTotalMemorized(triangle);
		System.out.println("minSum2 = " + minSum2);
	}
	
	//memorized recursion
	public static int[][] minSum ;
	private static int n;
	
	public static int search(int x, int y, ArrayList<ArrayList<Integer>> triangle) {
		if (x >= n) {
			return 0;
		}
		if (minSum[x][y] != Integer.MAX_VALUE) {
			return minSum[x][y]; 
		}
		
		minSum[x][y] = Math.min(search(x + 1, y, triangle), search(x + 1, y + 1, triangle)) 
				+ triangle.get(x).get(y);
		return minSum[x][y];
	}
	
	public static int minimumTotalMemorized(ArrayList<ArrayList<Integer>> triangle) {
		if (triangle == null || triangle.size() == 0) {
			return 0;
		}
		n = triangle.size();
		minSum = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				minSum[i][j] = Integer.MAX_VALUE;
			}
		}
		
		search(0, 0, triangle);
		int min = Integer.MAX_VALUE;
		for ( int i= 0; i<n; i++) {
			min = Math.min(min, minSum[n-1][i]);
		}
//		for (int i =0; i<n; i++) {
//			for (int j = 0; j<n; j++) {
//				System.out.print(minSum[i][j] + " ");
//			}
//			System.out.println();
//		}
		return minSum[0][0];
	}
	
	
	
}
