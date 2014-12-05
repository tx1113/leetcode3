package dp;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test3();
		test4();
	}
	
	
	/*
	 * DFS:
	 * This doesn't work well
	 */
	public static boolean wordBreakRec(String s, Set<String> dict) {
		return dfs(s, dict, 0, 0);
	}
	
	public static boolean dfs(String s, Set<String> dict, int start, int cur) {
		if (cur == s.length()) {
			//go to the last
			return dict.contains(s.substring(start, cur));
		}
		if (dfs(s, dict, start, cur + 1)) {
			return true;
		}
		
		if (!dict.contains(s.substring(start, cur))) {
			if (dfs(s, dict, cur+1, cur + 1)) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * DP
	 * State: f[i]    s[0..i-1] could be cut 
	 * i.e. the word make of first i characters could be cut, i is also the length 
	 * f[i] = any of (f[j] && s[j..i-1] in dict) (0 <=j < i) 
	 * initialize f[0] = true;  
	 */
	public static boolean wordBreakDP(String s, Set<String> dict) {
		int len = s.length();
		boolean[] f = new boolean[len + 1];
		
		f[0] = true;
		for ( int i = 1; i <= s.length(); i++) {
			f[i] = false;
			for (int j = 0; j <= i; j++) {
				if (f[j] && dict.contains(s.substring(j, i))) {
					f[i] = true;
					break;
				}
			}
		}
		return f[len];
	} // O(n*n)
	
	/*
	 * Optimize:
	 * 
	 */
	
	private static int getMaxLength(Set<String> dict) {
		int maxLen = 0;
		for (String s: dict) {
			maxLen = Math.max(maxLen, s.length());
		}
		return maxLen;
	}
	
	public static boolean wordBreakDPOpt(String s, Set<String> dict) {
		
		if (s == null || s.length() == 0) {
			if (dict == null || dict.size() == 0) {
				return true;
			} 
		} 
		
		int len = s.length();
		boolean[] canSegment = new boolean[len + 1];
		int maxLen = getMaxLength(dict);
		canSegment[0] = true;
		for ( int i = 1; i <= s.length(); i++) {
			canSegment[i] = false;
			for (int j = 1; j <= maxLen && j <= i; j++) {
				if (canSegment[i-j] == false) {
					continue;
				}
				String word = s.substring(i-j, i);
				if (dict.contains(word)) {
					canSegment[i] = true;
					break;
				}
			}
		}
		return canSegment[len];
	}
	
	public static void test4() {
		Set<String> dict = new HashSet<String>();
		dict.add("a");
		dict.add("b");
		String s = "ab";
		boolean rev = wordBreakDPOpt(s, dict);
		System.out.println("rev = " + rev);
	}
	
	
	/*
	 * f[i][len] means start position in i, string.len = len is in the dict
	 * (1) the word s[i, i+len -1] is in the dict
	 * (2) there exist a k, 0 < k < sublen, the two words are in the dict
	 * finally, 
	 * f[0][len]
	 */
	public static boolean wordBreakDP2D(String s, Set<String> dict) {
		int len = s.length();
		boolean[][] f = new boolean[len][len + 1];
		
		for (int subLen = 1; subLen <= s.length(); subLen++) {
			for (int start = 0; start <= s.length() - subLen; start++) {
				if (dict.contains(s.substring(start, start + subLen))) {
					f[start][subLen] = true;
					continue;
				}
				for (int k = 1; k < subLen; k++) {
					if (f[start][k] && f[start + k][subLen - k]) {
						f[start][subLen] = true;
						break;
					}
				}
			}
		}
		return f[0][len];
	}
	
	public static void test3() {
		Set<String> dict = new HashSet<String>();
		dict.add("a");
		dict.add("b");
		String s = "ab";
		boolean rev = wordBreakRec(s, dict);
//		boolean rev2 = wordBreakDP(s, dict);		
		System.out.println("rev = " + rev);
//		System.out.println("rev2 dp = " + rev2);
	}
	
	public static void test2() {
		Set<String> dict = new HashSet<String>();
		dict.add("leet");
		dict.add("code");
		String s = "leetcode";
		boolean rev = wordBreakRec(s, dict);
		System.out.println("rev = " + rev);
	}
}
