package strings;

public class SearchForPatterns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
//		test2();
	}
	
	public static void naiveSearch(String pattern, String txt){
		int M = pattern.length();
		int N = txt.length();
		
		for(int i=0; i<=N-M; i++){
			int j =0;
			for(; j<M; j++){
				if(pattern.charAt(j) != txt.charAt(i+j)){
					break;
				}
			}
			if(j == M){
				System.out.println("Pattern found at index " + i );
			}
		}
	}
	
	public static void test1(){
		String txt = "AABAACAADAABAAABAA";
		String pattern = "AABA";
		naiveSearch(pattern, txt);
		kmp(pattern, txt);
		System.out.println("*************");
//		String txt1= "AAAAAAAAAAAAAAAAAB";
//		String pattern1 = "AAAAB";
//		naiveSearch(pattern1, txt1);
	}
	
	public static int naiveSearch2(String pattern, String txt){
	
		int i=0, j = 0;
		while(i < txt.length() && j < pattern.length()){
			//compare txt[i] pattern[j]
			if(txt.charAt(i) == pattern.charAt(j)){
				i++;
				j++;
			}else{
				//
				j = 0;
				//i go back to i-j+1
				i = i - j +1;
			}
		}
		if(j == pattern.length()){
			return i - j;  //return the location of pattern in txt
		}else{
			return -1;
		}
	}
	
	
	public static int kmp(String pattern, String text){
		int i=0, j = 0;
		int[] lps = new int[pattern.length()];
		
		computeLongestPrefixSuffix(pattern, lps);
		
		for(int k = 0; k<lps.length; k++){
			System.out.print(lps[k] + " ");
		}
		System.out.println();
		while(i < text.length() && j < pattern.length()){
			//compare text[i] and pattern[j]
			if(text.charAt(i) == pattern.charAt(j)){
				i++;
				j++;
			}
			
			
			else if(text.charAt(i) != pattern.charAt(j)){
				if(j != 0){
					j = lps[j-1];
				}else{
					i = i+1;
				}
				
			}
			
		}
		if(j == pattern.length()){
			System.out.println("Found pattern at index " + (i-j));
		}
		
		if(j == pattern.length()){
			return i - j;
		}else{
			return -1;
		}
	}
	
	
	public static void computeLongestPrefixSuffix(String pattern, int[] lps){
		int len = 0;  //length of previous longest prefix suffix
		lps[0] = 0;
		int i= 1;
		int M = pattern.length();
		
		while (i < M) {
			if(pattern.charAt(i) == pattern.charAt(len)){
				len ++;
				lps[i] = len;
				i++;
			}else{
				//pattern[i] != pattern[len]
				if(len != 0){
					
					len = lps[len - 1];
				}else{
					lps[i] = 0;
					i++;
				}		
			}
		}
	}
	public static void test2(){
		String pat = "AAACAAAA";
		int[] lps = new int[pat.length()];
		computeLongestPrefixSuffix(pat, lps);
		int[] lps2 = new int[pat.length()] ;
		computeLongestPrefixSuffix(pat, lps2);
		for(int i=0; i<lps.length; i++){
			System.out.print(lps[i] + " ");
		}
		
		System.out.println();
		
		for(int i=0;i<lps2.length; i++){
			System.out.print(lps2[i] +" ");
		}
		System.out.println();
	}
	
	public static void computLGS(String pat, int[] lps){
		lps[0] = 0;
		int i= 1;
		while(i < pat.length()){
			if(pat.charAt(i) == pat.charAt(lps[i-1])){
				lps[i] = lps[i-1] + 1;
			}else{
				lps[i] = 0;
			}
			i++;
		}
	}
	
	
}
