package dp;

import javax.sound.midi.Receiver;

public class KSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test();
		test2();
	}

	/*
	 * Given n distinct positive integers, integer k (k <= n) and a number
	 * target. Find k numbers where sum is target. Calculate how many solutions
	 * there are? Example Given [1,2,3,4], k=2, target=5. There are 2 solutions:
	 * [1,4] and [2,3], return 2.
	 */

	public static int solutionKSum(int A[], int k, int target) {
		// write your code here
		boolean[][][] f = new boolean[A.length + 1][k + 1][target + 1];
		f[0][0][0] = true;
		// initilize
		for (int i = 0; i <= A.length; i++) {
			for (int j = 0; j <= k; j++) {
				for (int t = 0; t <= target; t++) {
					if (i == 0 && j == 0 && t == 0) {
						f[i][j][t] = true;
					}
					if (j > i) {
						f[i][j][t] = false;
					}
					if ((i == 0 && j != 0 && t != 0)
							|| (i != 0 && j == 0 && t != 0)) {
						f[i][j][t] = false;
					}
					if (i >= 1 && j >= 1 && (t - A[i - 1]) >= 0
							&& (t - A[i - 1]) <= target) {
						f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]]
								|| f[i - 1][j][t];
					}
				}
			}
		}

		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[0].length; j++) {
				for (int j2 = 0; j2 < f[0][0].length; j2++) {
					System.out.print(f[i][j][j2] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

		int numSln = 0;
		for (int i = 0; i <= A.length; i++) {
			if (f[i][k][target]) {
				numSln++;
			}
		}
		return numSln;
	}

	public static void test() {
		int[] A = { 1, 2, 3, 4 };
		int k = 2;
		int target = 5;
		int numSln = solutionKSum(A, k, target);
		System.out.println("numSln = " + numSln);
	}

	public static int kSum(int[] A, int k, int target) {
		if (A == null || A.length == 0 || target == 0) {
			return 0;
		}

		int[][][] f = new int[A.length][k + 1][target + 1];

		// initialize

		// when k1 = 0

		// we only choose one
		for (int i = 0; i < A.length; i++) {
			for (int t = 1; t <= target; t++) {
				for (int i1 = 0; i1 <= i; i1++) {
					if (A[i1] == t) {
						f[i][1][t] += 1;
					}
				}
			}
		}
		// we finish the situation of k == 1

		// when i == 0,we can only choose the first element,
		// but the k1 >=2, which means get >=2 elements from the f[i][k1][t] = 0
		for (int k1 = 2; k1 <= k; k1++) {
			for (int t = 1; t <= target; t++) {
				f[0][k1][t] = 0;
			}
		}

		// when t == 1, k>=2, the f[i][k1][t] = 0, since target == 1, we can
		// only choose 1 element
		// to compose it.
		for (int i = 1; i < A.length; i++) {
			for (int k1 = 2; k1 <= k; k1++) {
				f[i][k1][1] = 0;
			}
		}

		for (int i = 1; i < A.length; i++) {
			for (int k1 = 2; k1 <= k; k1++) {
				for (int t = 2; t <= target; t++) {
					f[i][k1][t] = f[i - 1][k1][t];
					if (t - A[i] >= 0) {
						f[i][k1][t] += f[i - 1][k1 - 1][t - A[i]];
					}
				}
			}
		}
		return f[A.length - 1][k][target];
	}

	public static int kSum2(int[] A, int subNum, int target) {
		if (A == null || A.length == 0 || subNum == 0 || target == 0) {
			return 0;
		}

		int[][][] f = new int[A.length + 1][subNum + 1][target + 1];
		// if a == 0 //a stands for the first a elements in A
		for (int a = 0; a <= A.length; a++) {
			for (int s = 0; s <= subNum; s++) {
				for (int t = 0; t <= target; t++) {
					if (a == 0 || s == 0 || t == 0) {
						f[a][s][t] = 0;
					}
					if (s > a) {
						f[a][s][t] = 0;
					}
					if (s >= 2 && a >= s && t == 1) {
						f[a][s][t] = 0;
					}
					if (s == 1 && t >= 1) {
						for (int ai = 1; ai <= a; ai++) {
							if (A[ai - 1] == t) {
								f[a][s][t] = 1;
							}
						}
					}

				}
			}
		}
		// when subNum == 1
		for (int a = 1; a <= A.length; a++) {
			for (int t = 1; t <= target; t++) {
				for (int a1 = 1; a1 <= a; a1++) {
					if (A[a1 - 1] == t) {
						f[a][1][t] = 1;
					}
				}
			}
		}

		// print the initialized array
		for (int i = 0; i < f.length; i++) {
			System.out.println("i = " + i);
			for (int j = 0; j < f[0].length; j++) {
				// System.out.println("j = " + j);
				for (int j2 = 0; j2 < f[0][0].length; j2++) {
					System.out.print(f[i][j][j2] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		for (int a = 2; a <= A.length; a++) {
			for (int s = 2; s <= subNum; s++) {
				for (int t = 2; t <= target; t++) {
					f[a][s][t] = f[a - 1][s][t];
					if (t - A[a - 1] >= 0) {
						f[a][s][t] += f[a - 1][s - 1][t - A[a - 1]];
					}
				}
			}
		}
		return f[A.length][subNum][target];
	}

	public static void test2() {
		int[] A = { 1, 2, 3, 4 };
		int k = 2;
		int target = 5;
		int rev = kSum2(A, k, target);
		System.out.println("-------------------------");
		System.out.println("rev = " + rev);
	}

	public int solutionKSumLintCode(int A[], int k, int target) {
		// write your code here
		int[][] f = new int[k + 1][target + 1];
		f[0][0] = 1;
		for (int i = 0; i < A.length; i++) {
			for (int j = target; j >= A[i]; j--) {
				for (int select = 1; select <= k; select++) {
					f[select][j] += f[select - 1][j - A[i]];
				}
			}
		}
		return f[k][target];
	}

	//this doesn't work well. need modify
	public static int kSum3(int[] A, int subNum, int target) {
		if (A == null || A.length == 0 || subNum == 0 || target == 0) {
			return 0;
		}
		int[][][] f = new int[A.length + 1][subNum + 1][target + 1];
		f[0][0][0] = 0;
		for (int a = 1; a <= A.length; a++) {
			for (int t = target; t >= A[a]; t--) {
				for (int s = 1; s <= subNum; s++) {
					f[a][s][t] = f[a - 1][s][t];
					if (t - A[a] >= 0) {
						f[a][s][t] += f[a - 1][s - 1][t - A[a]];
					}
				}
			}
		}
		return f[A.length][subNum][target];
	}

}
