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
		
		
		//ù��° �� �б�
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]);
		int M = Integer.parseInt(sp[1]);
		
		
		//�ι�° �� �б�
		str = br.readLine();
		sp = str.split(" ");
		
		list = new int[N]; //N���� ���� ����
		for(int i = 0; i < N; i++)
			list[i] = Integer.parseInt(sp[i]);
		
		//list ����
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
		int before = 0;	//������ Ž�� �� ����
		for(int i = 0; i<list.length;i++) {
			//�湮���� �ʾҴٸ�
			//Ž���� �� �ߺ��Ǵ� ���� Ž���ϸ� �Ȱ��� ������ �����Ƿ� before�� �����Ͽ��� Ž������ �ʵ��� �Ѵ�.
			if(!visit[i] && before != list[i]) {  
				before = list[i]; //Ž���� ����
				visit[i] = true;
				list2[cnt] = list[i];
				make_CM(cnt+1,M);
				visit[i] = false;
			}
		}
				
		
	}
}
