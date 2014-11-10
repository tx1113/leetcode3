package strings;

public class KMP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
		test1();
	}
	
	public static int[]  preProcessPattern(char[] ptrn) {
		int i= 0, j = -1;
		int ptrnLen = ptrn.length;
		int[] b = new int[ptrnLen + 1];
		b[0] = -1;
		while (i < ptrnLen) {
			while (j >=0 && ptrn[i] != ptrn[j]) {
				
			}
		}
		return null;
	}
	
	public static void getLongestPrefixSuffix(String ptrn, int[] lps){
		int len = 0; 
		int i = 1;
		lps[0] = 0;
		
		while(i < ptrn.length()){
			if(ptrn.charAt(i) == ptrn.charAt(len)){
				len++;
				lps[i] = len;
				i++;
			}else{
				if(len != 0){
					len = lps[len - 1];
				}else{
					lps[i] = 0;
					i++;
				}
			}
		}
	}
	
	public static int[] getNext(String ptrn){
		int[] lps = new int[ptrn.length() + 1];
		int j = 0; 
		int i = 1; 
		lps[0] = -1;
		lps[1] = 0;
		while (i < ptrn.length()) {
			if(ptrn.charAt(i) == ptrn.charAt(j)){
				j ++;
				lps[i+1] = j;
				i++;
			}else{
				if(j != 0){
					j = lps[j];
				}else{
					lps[i+1] = 0;
					i++;
				}
			}
		}
		for(int k = 0; k<lps.length; k++){
			System.out.print(lps[k] + " ");
		}
		System.out.println();
		return lps;
	}
	
	public static void test(){
		String s = "ABCDABC";
		int[] lps = new int[s.length()];
		getLongestPrefixSuffix(s, lps);
		for(int i=0; i<lps.length; i++){
			System.out.print(lps[i] + " ");
		}
		System.out.println();
		
		int[] lps2 = getNext(s);
		
	}
	
	public static void searchString(String text, String ptrn){
		int i = 0, j = 0;
		int ptrnLen = ptrn.length();
		int textLen = text.length();
		int[] next = getNext(ptrn);
		while(i < textLen){
			while(j >=0 && text.charAt(i) != ptrn.charAt(j)){
				j = next[j];
			}
			i++;
			j++;
			
			//a match is found
			if(j == ptrnLen){
				System.out.println("found substring at index: " + (i-ptrnLen));
				j = next[j];
			}
		}
	}
	
	public static void test1(){
		String txt = "AABAACAADAABAAABAA";
		String pattern = "AABA";
		searchString(txt, pattern);
		SearchForPatterns.naiveSearch(pattern, txt);
	}

}
