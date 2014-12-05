package dp;

public class SeqDPPalindromePartitioningII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/**
	 * Given a string s, cut s into some substrings such that every substring is a palindrome.
	 * Return the minimum cuts needed for a palindrome partitioning of s.
	 * Example
	 * For example, given s = "aab",
	 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
	 */
	public static int minCut(String s) {
		if (s == null || s.length() == 0 || s.length() == 1) {
			return 0;
		}
		boolean[][] isPalindrome = getPalindrome(s);
		int[] cut = new int[s.length() + 1];
	
		cut[0] = 0;
		for (int j = 1; j <= s.length(); j++) {
			cut[j] = Integer.MAX_VALUE;
			for (int i = 1; i <= j; i++) {
				if (isPalindrome[j - i][j - 1]
						&& cut[j - i] != Integer.MAX_VALUE) {
					cut[j] = Math.min(cut[j], cut[j - i] + 1);
				}
			}
		}
		return cut[s.length()] - 1;		
    }
	
	public static boolean isPalindrome(String s, int start, int end) {
		for (int i = start, j = end; i <= j; i++, j --) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean[][] getPalindrome(String s) {
		boolean[][] isPalindrome = new boolean[s.length()][s.length()];
		for (int i = 0; i<s.length(); i++) {
			isPalindrome[i][i] = true;
		}
		
		for (int i = 0; i<s.length() - 1; i++) {
			isPalindrome[i][i+1] = s.charAt(i) == s.charAt(i+1);
		}
		
		//actually, here, the length is trueLen - 1    so, here, we use len < s.length()
		//since we have handled the length == 1, length == 2.
		for (int len = 2; len < s.length(); len ++) {
			for (int start = 0; start < s.length() - len ; start ++) {
				isPalindrome[start][start + len ] =
						isPalindrome[start + 1][start + len - 1] &&
						s.charAt(start) == s.charAt(start + len);
			}
		}
		return isPalindrome;
	}
	
	public static int minCut2(String s) {
		if (s == null || s.length() == 0 || s.length() == 1) {
			return 0;
		}
		boolean[][] isPalindrome = getPalindrome(s);
		int[] cut = new int[s.length() + 1];
		int n = s.length();
		for (int i = 0; i <= n; i++) {
			cut[i] = n - i - 1;
		}
		
		for (int i = n-1; i >=0; i--) {
			for (int j = i; j< n; j++) {
				if (isPalindrome[i][j]) {
					cut[i] = Math.min(cut[i], cut[j+1] + 1);
				}
			}
		}
		return cut[0];
	}
	
	public static void test1() {
		String s = "abb";
		boolean[][] isPal = getPalindrome(s);
		for ( int i = 0; i< isPal.length; i++) {
			for (int j = 0; j< isPal[0].length; j++) {
				System.out.print( isPal[i][j] + " ");
			}
			System.out.println();
		}
		int minCut = minCut(s);
		System.out.println("minCut = " + minCut);
		int minCut2 = minCut2(s);
		System.out.println("minCut2 = " + minCut2);
	}
	
	

}
