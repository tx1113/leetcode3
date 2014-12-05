package dp;

public class EditDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/* Edit Distance
	 * Given two words word1 and word2, 
	 * find the minimum number of steps required to convert word1 to word2. 
	 * (each operation is counted as 1 step.)
	 * You have the following 3 operations permitted on a word:
	 * a) Insert a character
	 * b) Delete a character
	 * c) Replace a character
	 */
	/*
	 * Idea:
	 * f[i][j]  the distance of 
	 * first i chars in word1 and first j chars in word2 
	 * if word1[i-1] == word2[j-1] 
	 * 	  f[i][j] = min(f[i-1][j-1], f[i-1][j] + 1,  f[i][j-1] + 1)  
	 * 								 delete in word1[0,i-1]
	 * 								 insert in word2[0,j-1] 
	 * 	  f[i][j] = f[i-1][j-1]; 
	 * 	if word1[i-1] != word2[j-1]
	 * 	  f[i][j] = min(f[i-1][j], f[i][j-1], f[i-1][j-1]) + 1;   // 1 means we need one operation
	 * 			 delete in word1  delete in word2	replace
	 * 
	 *  initialize:
	 *  	f[i][0] = i;
	 *  	f[0][j] = j;
	 *  answer:
	 *  	f[aLen][bLen];
	 */

	public static int minDistance(String word1, String word2) {
		// write your code here
		if (word1 == null || word2 == null) {
			return 0;
		}
		int aLen = word1.length();
		int bLen = word2.length();
		int[][] f = new int[aLen + 1][bLen + 1];
		
		for (int i = 0; i <= aLen; i++) {
			f[i][0] = i;
		}
		for (int j = 0; j <= bLen; j++) {
			f[0][j] = j;
		}
		
		for (int i = 1; i <= aLen; i++) {
			for (int j = 1; j <= bLen; j++) {
				if (word1.charAt(i-1) == word2.charAt(j-1)) {
					f[i][j] = f[i-1][j-1];
				} else {
					f[i][j] = Math.min(Math.min(f[i-1][j], f[i][j-1]), f[i-1][j-1]) + 1;
				}
			}
		}
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[0].length; j++) {
				System.out.print(f[i][j]+ " ");
			}
			System.out.println();
		}
		return f[aLen][bLen];
	}
	
	public static void test() {
		String word1 = "ab";
		String word2 ="a";
		int minDistance = minDistance(word1, word2);
		System.out.println("minDistance = " + minDistance);
	}

}
