package strings;

public class KMP2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
		test2();
	}
	
	public static int[] prePrcessPattern(String ptrn){
		int i = 0, j = -1;
		int ptrnLen = ptrn.length();
		int[] b = new int[ptrnLen + 1];
		b[0] = -1;
		while(i < ptrnLen){
			while (j >=0 && ptrn.charAt(i) != ptrn.charAt(j)) {
				j = b[j];  //here, it's very tricky, to see the idea of it, 
				//go to KMP.java  getLongestPrefixSuffix and getNext
			}
			i++;
			j++;
			b[i] = j;
		}
		return b;
	}
	
	public static void kmp2(String ptrn, String txt){
		//error check
		if(ptrn.length() > txt.length()){
			return ;
		}
		int i= 0, j = 0;
		int[] b = prePrcessPattern(ptrn);
		System.out.println("out put the array\n");
		for(int k = 0; k<b.length; k++){
			System.out.print(b[k] +" ");
		}
		System.out.println();
		while (i < txt.length()) {
			while (j >=0 && ptrn.charAt(j) != txt.charAt(i)) {
				j = b[j];
			}
			i++;
			j++;
			if(j == ptrn.length()){
				//there we find a match
				System.out.println(" found the match pattern at " + (i - ptrn.length()));
				j = b[j];
			}
		}
	}
	
	public static void test(){
		String ptrn= "aaa";
		int[] b = new int[ptrn.length() + 1];
		b = prePrcessPattern(ptrn);
		for(int i=0; i<b.length; i++){
			System.out.print(b[i] + " ");
		}
		System.out.println();
		
	}
	
	public static void test2(){
		String txt = "AABAACAADAABAAABAA";
		String pattern = "AABA";
		kmp2(pattern, txt);
		SearchForPatterns.naiveSearch(pattern, txt);
	}
	

}
