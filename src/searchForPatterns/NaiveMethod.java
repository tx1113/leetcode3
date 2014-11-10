package searchForPatterns;

import java.nio.channels.ScatteringByteChannel;

public class NaiveMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void search(String pattern, String txt){
		int M = pattern.length();
		int N = txt.length();
		
		for(int i=0; i<=N-M; i++){
			int j = 0;
			for(; j<M; j++){
				if(txt.charAt(i+j) != pattern.charAt(j)){
					break;
				}
			}
			if(j == M){
				System.out.println("Pattern found at index " + i);
			}
		}
	}
	
	

}
