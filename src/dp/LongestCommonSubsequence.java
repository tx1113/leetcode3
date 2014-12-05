package dp;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}
	
	
	/*
	 * Rec:
	 */
	public static int LongestCommonSubsequenceRec(String A, String B) {
		return LCSHelper(A, B, A.length(), B.length());
	}
	
	public static int LCSHelper(String A, String B, int aLen, int bLen) {
		//base case
		if (aLen == 0 || bLen == 0) {
			return 0;
		}
		if (A.charAt(aLen - 1) == B.charAt(bLen - 1)) {
			return LCSHelper(A, B, aLen-1, bLen - 1) + 1;
		} else {
			return Math.max(LCSHelper(A, B, aLen-1, bLen), LCSHelper(A, B, aLen, bLen-1));
		}
	}
	
	/*
	 * Memorized Rec
	 */
	public static int LCSMemo(String A, String B) {
		int aLen = A.length();
		int bLen = B.length();
		int[][] f = new int[aLen + 1][bLen + 1];
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[0].length; j++) {
				f[i][j] = -1;
			}
		}
		return LCSMemoHelper(A, B, aLen, bLen, f);
	}
	public static int LCSMemoHelper(String A, String B, int aLen,int bLen, int[][] f) {
		//base case
		if (aLen == 0 || bLen == 0) {
			f[aLen][bLen] = 0;
			return f[aLen][bLen];
		}
		if (f[aLen][bLen] != -1) {
			return f[aLen][bLen];
		} else {
			if (A.charAt(aLen - 1) == B.charAt(bLen - 1)){
				f[aLen][bLen] = LCSMemoHelper(A, B, aLen -1, bLen -1, f) + 1;
			} else {
				f[aLen][bLen] = Math.max(LCSMemoHelper(A, B, aLen-1, bLen, f), 
						LCSMemoHelper(A, B, aLen, bLen-1, f));
			}
		}
		return f[aLen][bLen];
	}
	
	
	
	/*
	 * State 
	 * f[i][j]   
	 * the longest common subsequence of 
	 * 	   the first i chars in String A && the first j chars in String B
	 * f[i][j] = f[i-1][j-1] + 1    A[i-1] == B[j-1]
	 *           max(f[i-1][j], f[i][j-1])  A[i-1] != B[j-1] 
	 * initialize f[i][0] = 0, f[0][i] = 0
	 * answer f[A.length()][B.length()];
	 * 
	 */
	public static int longestCommonSubsequence(String A, String B) {
        // write your code here
		int aLen = A.length();
		int bLen = B.length();
		int[][] f = new int[aLen + 1][bLen + 1];
		
		for (int i = 0; i <= aLen; i++) {
			f[i][0] = 0;
		}
		for (int i = 0; i <= bLen; i++) {
			f[0][i] = 0;
		}
		
		for (int i = 1; i <= aLen; i++) {
			for (int j = 1; j <= bLen; j++) {
				if (A.charAt(i-1) == B.charAt(j-1)) {
					f[i][j] = f[i-1][j-1] + 1;
				} else {
					f[i][j] = Math.max(f[i-1][j], f[i][j-1]);
				}
			}
		}
		return f[aLen][bLen];
    }
	
	public static void test2() {
		String A = "AGGTAB";
		String B = "GXTXAYB";
		
		int LCS = longestCommonSubsequence(A, B);
		int LCS2 = LongestCommonSubsequenceRec(A, B);
		int LCS3 = LCSMemo(A, B);
		System.out.println("LCS = " + LCS);
		System.out.println("LCS2 = " + LCS2);
		System.out.println("LCS3 = " + LCS3);
	}
}
