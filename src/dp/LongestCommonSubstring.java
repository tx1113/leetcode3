package dp;

public class LongestCommonSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Given two strings, find the longest common substring. 
	 * Return the length of it.
	 */
	
	
	public static int longestCommonSubstringDP(String A, String B) {
		// write your code here
		int aLen = A.length();
		int bLen = B.length();
		int[][] f = new int[aLen + 1][bLen + 1];
		int maxLen = 0;
		for (int i = 0; i <= aLen; i++) {
			f[i][0] = 0;
		}
		for (int i = 0; i <= bLen; i++) {
			f[0][i] = 0;
		}

		for (int i = 1; i <= aLen; i++) {
			for (int j = 1; j <= bLen; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					f[i][j] = f[i - 1][j - 1] + 1;
					maxLen = Math.max(maxLen, f[i][j]);
				} else {
					f[i][j] = 0;
				}
				
			}
		}
		
		return maxLen;
	}
	
	//we can do it without using the extra space. 
	//actually, I think this is a brute force method
	public static int longestCommonSubstringOpt(String A, String B) {
		int aLen = A.length();
		int bLen = B.length();
		int maxLen = 0;
		
		for (int i = 0; i < aLen; i++) {
			for (int j = 0; j < bLen; j++) {
				int len = 0;
				while (i + len < aLen && j + len < bLen && A.charAt(i + len) == B.charAt(j + len)) {
					len ++;
				}
				maxLen = Math.max(maxLen, len);
			}
		}
		return maxLen;
	}

}
