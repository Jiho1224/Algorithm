package al_07_07;

import java.io.*;
import java.util.*;
class Data{
	long per;
	long candy;
	
	public Data(long per,long candy) {
		this.per = per; //사람수
		this.candy = candy; //캔디 한 봉지의 캔디 개수
	}
}

//확장된 유클리드 호제법 데이터를 담기 위한 class
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
	public static ArrayList<Euclid_data> list; //유클리드 호제법 계산 데이터
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		data = new ArrayList<>();
		list = new ArrayList<>();
		//testCase 개수
		int tcNum = Integer.parseInt(str);
		
		//input
		for(int i = 0; i<tcNum;i++) {
			str = br.readLine();
			String[] split_str = str.split(" ");
			
			//입력받은 사람수 (K)와 캔디 한봉지 캔디 수(C)를 DATA로 저장
			data.add(new Data(Long.parseLong(split_str[0]), Long.parseLong(split_str[1])));
		}
		
		// idea:
		// 사야할 사탕 봉지수를 x, 한 사람에게 주는 사탕 수를 y라고 두자.
		// 그러면 K*y+1 = C*x 여야 한다.
		// 즉, C*x - K*y = 1의 방정식의
		// (x,y)를 구하면 된다. 여기서 확장된 유클리드 호제법을 사용한다. 
		// 출력해야할 수는 사야할 사탕 봉지 수 이므로 x를 출력해준다.
		
		
		for(int i = 0; i<tcNum;i++) {
			long K = data.get(i).per;
			long C = data.get(i).candy;
			
			
			if(gcd(K,C) != 1) {
				bw.write("IMPOSSIBLE\n"); //만약 두 수의 최대공약수가 1이 아니라면 방정식 해는 없으므로 IMPOSSIBLE 출력
				continue; //다음 test case로 넘어간다.
			}
			else if(C == 1) {
				if(K>=Math.pow(10, 9))
					bw.write("IMPOSSIBLE\n"); //만약 두 수의 최대공약수가 1이 아니라면 방정식 해는 없으므로 IMPOSSIBLE 출력
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
			
			//만약에 가능한 상태라면 유클리드 호제법을 돌린다.
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
	
	//최대 공약수를 구하는 함수
	public static long gcd(long a,long b) {
		if(b == 0) return a;
		else return gcd(b,a%b);
	}
	
	//확장 유클리드 호제법
	public static void Euclid(long K, long C) {
		//구해야할 방정식:
		//C*x + K*y = 1 (데이터 저장할때 K에 -1을 곱해두었으므로)
		list.clear();
		list.add(new Euclid_data(1,0,C,0)); //초기 데이터 두개를 list에 먼저 담는다.
		list.add(new Euclid_data(0,1,K,0));
		
		long num = -1; 
		while(num != 1) {
			int si = list.size();
			
			
			long newQ = list.get(si-2).r / list.get(si-1).r; //새로운 q는 앞 두 Q의 몫
//			if (newQ < 0) newQ = (-1)*newQ;
			
			long newS = list.get(si-2).s - (newQ*list.get(si-1).s); // S'' = S - S'q
			long newT = list.get(si-2).t - (newQ*list.get(si-1).t);
			
			long newR = list.get(si-2).r - (newQ*list.get(si-1).r); //새로운 r은 앞 두 R의 나머지
			
			
			
			list.add(new Euclid_data(newS,newT,newR,newQ));
			if(newR ==0) break;
			num = newR; //맨 마지막 list의 r값이 1이 되면 작동을 멈춘다.
			
		}
		long tmp = list.get(list.size()-1).s;
		while(tmp <0) tmp += K;
		list.add(new Euclid_data(tmp,0,0,0));
		
	}

}
