package dp;

public class UniquePath2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}

	public static int uniquePathsWithObstaclesMemo(int[][] obstacleGrid) {
		// write your code here
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] memo = new int[m][n];
		return uniquePathsIIMemo(obstacleGrid, m - 1, n - 1, memo);
	}

	public static int uniquePathsIIMemo(int[][] matrix, int m, int n,
			int[][] memo) {
		if (m < 0 || n < 0) {
			return 0;
		}

		if (matrix[m][n] == 1) {
			return 0;
		} else {
			if (memo[m][n] != 0) {
				return memo[m][n];
			} else {
				memo[m][n] = uniquePathsIIMemo(matrix, m - 1, n, memo)
						+ uniquePathsIIMemo(matrix, m, n - 1, memo);
				return memo[m][n];
			}
		}
	}

	public static int uniquePathsWithObstaclesDP(int[][] obstacleGrid) {
		// write your code here
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] sum = new int[m][n];
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					sum[i][j] = 0;
				} else {
					if (i == 0 && j == 0) {
						sum[i][j] = 1;
					} else if (i == 0) {
						sum[i][j] = sum[i][j - 1];
					} else if (j == 0) {
						sum[i][j] = sum[i - 1][j];
					} else {
						sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
					}
				}
			}
		}
		return sum[m - 1][n - 1];
	}

	public static int UniquePathWithObstacleDP1D(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[] sum = new int[n];
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					sum[j] = 0;
				} else {
					if (i == 0 && j == 0) {
						sum[0] = 1;
					} else if (j == 0) {
						sum[j] = sum[j];
					} else {
						sum[j] = sum[j - 1] + sum[j];
					}
				}
			}
		}
		return sum[n - 1];
	}

	public static void test2() {
		int[][] matrix = { { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 } };
		int rev = UniquePathWithObstacleDP1D(matrix);
		System.out.println("rev = " + rev);
		System.out.println(uniquePathsWithObstaclesDP(matrix));
	}

}
