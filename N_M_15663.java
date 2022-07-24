package al_07_08;


import java.io.*;
import java.util.*;

public class N_M_15663 {
	public static int []list2;
	public static ArrayList<String> pick;
	public static boolean[] visit;
	public static int []list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		//첫번째 줄 읽기
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]);
		int M = Integer.parseInt(sp[1]);
		
		
		//두번째 줄 읽기
		str = br.readLine();
		sp = str.split(" ");
		
		list = new int[N]; //N개의 수를 받음
		for(int i = 0; i < N; i++)
			list[i] = Integer.parseInt(sp[i]);
		
		//list 정렬
		Arrays.sort(list);
		
		
		list2 = new int[N];
		visit = new boolean[N];
		pick = new ArrayList<>();
		
		make_CM(0,M);
		
		for(int i = 0; i<pick.size();i++) {
			bw.write(pick.get(i)+"\n");
		}
		
		bw.flush();
	}
	
	public static void make_CM(int cnt,int M) { 
		
		if(cnt == M) {
			String str = "";
			for(int k = 0; k<M;k++) {
				str += list2[k]+" ";
			}

			pick.add(str);
			return;
		}
		int before = 0;	//이전에 탐색 한 숫자
		for(int i = 0; i<list.length;i++) {
			//방문하지 않았다면
			//탐색할 때 중복되는 수를 탐색하면 똑같은 수열이 나오므로 before을 설정하여서 탐색하지 않도록 한다.
			if(!visit[i] && before != list[i]) {  
				before = list[i]; //탐색한 숫자
				visit[i] = true;
				list2[cnt] = list[i];
				make_CM(cnt+1,M);
				visit[i] = false;
			}
		}
				
		
	}
}
