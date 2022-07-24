package al_07_04;

import java.io.*;
import java.util.*;

public class teaching_1062 {
	public static int max = 0;
	public static String []sp;
	public static boolean[] visit;
	public static int K;
	public static int cnt;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//첫번째 줄; input
		String str = br.readLine();
		sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]); //단어 N개
		K = Integer.parseInt(sp[1]); //가를칠 수 있는 글자 K개
		
		//남극 단어를 받는 String 배열 초기화
		sp = new String[N];
		int total = 0;
		
		for(int i = 0; i<N;i++) {
			str = br.readLine(); //string을 읽는다.
			sp[i] = str.substring(4,str.length()-4); //앞의 anta와 뒤의 tica를 빼고 단어만 넣는다.
			total += sp[i].length();
		}
		
		//a,n,t,i,c 는 기본으로 배워야함.
		//만약 배울 수 있는 글자가 5보다 작다면 아무 단어도 배우지 못한다.
		if(K < 5) {
			bw.write(0+"");
			bw.flush();
			System.exit(0); //프로그램 종료!
		}
		
		//5개의 단어는 기본으로 배워야하므로 a,n,t,i,c는 안다고 가정 후 
		//나머지 배울 수 있는 글자 K개
		K = K-5; 

		visit = new boolean[26];
		visit['a'-'a'] = true;
		visit['t'-'a'] = true;
		visit['i'-'a'] = true;
		visit['n'-'a'] = true;
		visit['c'-'a'] = true;
		
		learn(0,0);
		
		bw.write(max+"");
		bw.flush();
		
	}
	
	public static void learn(int alpha, int n) {
		
		//배울 수 있는 만큼 글자를 모두 배웠다면.
		if(n == K) {
			int result = 0;
			for(int i = 0; i<sp.length;i++) {
				boolean flag = true;
				for(int j = 0; j<sp[i].length();j++)
					//하나라도 false라면
					if(!visit[sp[i].charAt(j)-'a']) flag = false;
				
				if(flag) result++;
			}
			
			max = Math.max(max,result); //result와 원래의 max 중 가장 큰 값을 저장
			return;
		}
		
		for(int i = alpha;i<26;i++) {
			//만약 방문하지 않았다면
			if(!visit[i]) {
				visit[i] = true;
				learn(i,n+1);
				visit[i] =false;
			}
		}
		
	}
	
	

}
