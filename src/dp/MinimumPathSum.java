package dp;

public class MinimumPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}

	/*
	 * Given a m x n grid filled with non-negative numbers, 
	 * find a path from top left to bottom right 
	 * which minimizes the sum of all numbers along its path.
	 * 
	 * You can only move either down or right at any point in time.
	 */
	/*
	 * f[i][j] from 0 to grid[i][j], the minimum sum 
	 *  
	 * 
	 */
	public static int minPathSum(int[][] grid) {
		// write your code here
		if( grid == null || grid.length == 0) {
			return 0;
		}
		int rLen = grid.length;
		int cLen = grid[0].length;
		int[][] minSum = new int[rLen][cLen];
		minSum[0][0] = grid[0][0];
		for (int i = 1; i < rLen; i++) {
			minSum[i][0] = minSum[i-1][0] + grid[i][0];
		}
		for (int j = 1; j < cLen; j++) {
			minSum[0][j] = minSum[0][j-1] + grid[0][j];
		}
		
		for (int i = 1; i < rLen; i ++) {
			for (int j = 1; j < cLen; j++) {
				minSum[i][j] = Math.min(minSum[i-1][j], minSum[i][j-1]) + grid[i][j];
			}
		}
		return minSum[rLen - 1][cLen - 1];
	}

	public static int minPathDP1D(int[][] grid) {
		if( grid == null || grid.length == 0) {
			return 0;
		}
		int rLen = grid.length;
		int cLen = grid[0].length;
		int[] minSum = new int[cLen];
		for (int i = 0; i < rLen; i ++) {
			for (int j = 0; j < cLen; j++) {
				if (i == 0 && j == 0) {
					minSum[0] = grid[0][0];
				} else if (j == 0) {
					minSum[j] = minSum[j] + grid[i][j];
				}else if ( i == 0) {
					minSum[j] = minSum[j-1] + grid[i][j];
				}else {
					minSum[j] = Math.min(minSum[j-1] , minSum[j]) + grid[i][j];
				}
			}
		}
		return minSum[cLen - 1];
	}
	
	public static void test2() {
		int[][] matrix = {
				{1,2},
				{1,1}
		};
		int rev = minPathDP1D(matrix);
		System.out.println("rev = " + rev);
	}
	
}
