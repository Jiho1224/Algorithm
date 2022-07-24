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
		
		//ù��° ��; input
		String str = br.readLine();
		sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]); //�ܾ� N��
		K = Integer.parseInt(sp[1]); //����ĥ �� �ִ� ���� K��
		
		//���� �ܾ �޴� String �迭 �ʱ�ȭ
		sp = new String[N];
		int total = 0;
		
		for(int i = 0; i<N;i++) {
			str = br.readLine(); //string�� �д´�.
			sp[i] = str.substring(4,str.length()-4); //���� anta�� ���� tica�� ���� �ܾ �ִ´�.
			total += sp[i].length();
		}
		
		//a,n,t,i,c �� �⺻���� �������.
		//���� ��� �� �ִ� ���ڰ� 5���� �۴ٸ� �ƹ� �ܾ ����� ���Ѵ�.
		if(K < 5) {
			bw.write(0+"");
			bw.flush();
			System.exit(0); //���α׷� ����!
		}
		
		//5���� �ܾ�� �⺻���� ������ϹǷ� a,n,t,i,c�� �ȴٰ� ���� �� 
		//������ ��� �� �ִ� ���� K��
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
		
		//��� �� �ִ� ��ŭ ���ڸ� ��� ����ٸ�.
		if(n == K) {
			int result = 0;
			for(int i = 0; i<sp.length;i++) {
				boolean flag = true;
				for(int j = 0; j<sp[i].length();j++)
					//�ϳ��� false���
					if(!visit[sp[i].charAt(j)-'a']) flag = false;
				
				if(flag) result++;
			}
			
			max = Math.max(max,result); //result�� ������ max �� ���� ū ���� ����
			return;
		}
		
		for(int i = alpha;i<26;i++) {
			//���� �湮���� �ʾҴٸ�
			if(!visit[i]) {
				visit[i] = true;
				learn(i,n+1);
				visit[i] =false;
			}
		}
		
	}
	
	

}
