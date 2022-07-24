package al_07_07;

import java.io.*;
import java.util.*;
class Data{
	long per;
	long candy;
	
	public Data(long per,long candy) {
		this.per = per; //�����
		this.candy = candy; //ĵ�� �� ������ ĵ�� ����
	}
}

//Ȯ��� ��Ŭ���� ȣ���� �����͸� ��� ���� class
class Euclid_data{
	long s,t,r,q;
	
	public Euclid_data(long s,long t, long r, long q){
		this.s = s;
		this.t = t;
		this.r = r;
		this.q = q;
	}
}

public class Divide_Candy_3955{
	
	public static ArrayList<Data> data; //test case input data
	public static ArrayList<Euclid_data> list; //��Ŭ���� ȣ���� ��� ������
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		data = new ArrayList<>();
		list = new ArrayList<>();
		//testCase ����
		int tcNum = Integer.parseInt(str);
		
		//input
		for(int i = 0; i<tcNum;i++) {
			str = br.readLine();
			String[] split_str = str.split(" ");
			
			//�Է¹��� ����� (K)�� ĵ�� �Ѻ��� ĵ�� ��(C)�� DATA�� ����
			data.add(new Data(Long.parseLong(split_str[0]), Long.parseLong(split_str[1])));
		}
		
		// idea:
		// ����� ���� �������� x, �� ������� �ִ� ���� ���� y��� ����.
		// �׷��� K*y+1 = C*x ���� �Ѵ�.
		// ��, C*x - K*y = 1�� ��������
		// (x,y)�� ���ϸ� �ȴ�. ���⼭ Ȯ��� ��Ŭ���� ȣ������ ����Ѵ�. 
		// ����ؾ��� ���� ����� ���� ���� �� �̹Ƿ� x�� ������ش�.
		
		
		for(int i = 0; i<tcNum;i++) {
			long K = data.get(i).per;
			long C = data.get(i).candy;
			
			
			if(gcd(K,C) != 1) {
				bw.write("IMPOSSIBLE\n"); //���� �� ���� �ִ������� 1�� �ƴ϶�� ������ �ش� �����Ƿ� IMPOSSIBLE ���
				continue; //���� test case�� �Ѿ��.
			}
			else if(C == 1) {
				if(K>=Math.pow(10, 9))
					bw.write("IMPOSSIBLE\n"); //���� �� ���� �ִ������� 1�� �ƴ϶�� ������ �ش� �����Ƿ� IMPOSSIBLE ���
				else
					bw.write((K+1)+"\n");
				continue;
			}
			else if(K == 1 && C == 1) {
				bw.write("2\n");
				continue;
			}
			else if(K == 1) {
				bw.write("1\n");
				continue;
			}
			
			//���࿡ ������ ���¶�� ��Ŭ���� ȣ������ ������.
			Euclid(K,C);
			
			if(list.get(list.size()-1).s >= Math.pow(10, 9)) {
				bw.write("IMPOSSIBLE\n"); 
				continue;
			}
			
			else if(list.get(list.size()-1).s >= Math.pow(10, 9))
				bw.write("IMPOSSIBLE\n");
			else 
				bw.write(list.get(list.size()-1).s+"\n");
		}
		br.close();
		bw.flush();
	}
	
	//�ִ� ������� ���ϴ� �Լ�
	public static long gcd(long a,long b) {
		if(b == 0) return a;
		else return gcd(b,a%b);
	}
	
	//Ȯ�� ��Ŭ���� ȣ����
	public static void Euclid(long K, long C) {
		//���ؾ��� ������:
		//C*x + K*y = 1 (������ �����Ҷ� K�� -1�� ���صξ����Ƿ�)
		list.clear();
		list.add(new Euclid_data(1,0,C,0)); //�ʱ� ������ �ΰ��� list�� ���� ��´�.
		list.add(new Euclid_data(0,1,K,0));
		
		long num = -1; 
		while(num != 1) {
			int si = list.size();
			
			
			long newQ = list.get(si-2).r / list.get(si-1).r; //���ο� q�� �� �� Q�� ��
//			if (newQ < 0) newQ = (-1)*newQ;
			
			long newS = list.get(si-2).s - (newQ*list.get(si-1).s); // S'' = S - S'q
			long newT = list.get(si-2).t - (newQ*list.get(si-1).t);
			
			long newR = list.get(si-2).r - (newQ*list.get(si-1).r); //���ο� r�� �� �� R�� ������
			
			
			
			list.add(new Euclid_data(newS,newT,newR,newQ));
			if(newR ==0) break;
			num = newR; //�� ������ list�� r���� 1�� �Ǹ� �۵��� �����.
			
		}
		long tmp = list.get(list.size()-1).s;
		while(tmp <0) tmp += K;
		list.add(new Euclid_data(tmp,0,0,0));
		
	}

}
