package dp;


public class UniquePath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int uniquePathsRec(int m, int n) {
        // write your code here 
		if (m < 1 || n < 1) {
			return 0;
		}
		if (m == 1 || n == 1) {
			return 1;
		}
		return uniquePathsRec(m-1, n) + uniquePathsRec(m, n - 1);
    }
	
	public static int uniquePathMemo(int m, int n) {
		int[][] memo = new int[m+1][n+1];
		return dfs(m, n, memo);
	}
	
	public static int dfs(int m, int n, int[][] memo) {
		if (m < 1 || n < 1) {
			return 0;
		}
		if (m == 1 || n == 1) {
			return 1;
		}
		if (memo[m][n] > 0) {
			return memo[m][n];
		}else {
			memo[m][n] = dfs(m-1, n, memo) + dfs(m, n-1, memo);
			return memo[m][n];
		}
	}
	
	public static int uniquePathsDP(int m, int n) {
		int[][] dp = new int[m+1][n+1];
		for (int i = 1; i <= m; i++) {
			dp[i][1] = 1;
		}
		for (int j = 1; j<=n; j++) {
			dp[1][j] = 1;
		}
		
		for (int i = 2; i<=m; i++) {
			for (int j =2; j<=n; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		return dp[m][n];
	}
	
	//rolling array
	public static int uniquePathsDP1D(int m, int n) {
		int[] sum = new int[n + 1];
		sum[1] = 1;
		
		for (int i= 1; i<=m; i++) {
			for ( int j = 1; j<=n; j++) {
				if (j == 1) {
					sum[j] = 1;
				} else {
					sum[j] = sum [j] + sum[j-1];
				}
			}
		}
		return sum[n];
	}

}
