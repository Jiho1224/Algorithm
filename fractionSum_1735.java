package al_07_07;

import java.io.*;


public class fractionSum_1735 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str; 
		String[] str_split;
		int [] son = new int[2];
		int [] parent = new int[2];
		
		//Input
		for(int i = 0; i<2;i++) {
			str = br.readLine();
			str_split = str.split(" ");
			
			son[i] = Integer.parseInt(str_split[0]);
			parent[i] = Integer.parseInt(str_split[1]);
		}
		int tmp = lcm(parent[0],parent[1]); //parent
		
		for(int i = 0; i<2;i++) {
			int mul = tmp / parent[i];
			son[i] = mul*son[i];
		}
		int result_son = son[0] + son[1];
		int result_parent = tmp;
		
		int div = gcd(result_parent,result_son);
		
		result_son /= div;
		result_parent /= div;
		
		bw.write(result_son+" "+result_parent);
		br.close();
		bw.flush();
	}
	
	//최소 공배수
	public static int lcm(int x,int y) {
		return x*y / gcd(x,y);
	}
	
	//최대 공약수
	public static int gcd(int x,int y) {
		while(y != 0) {
			int r = x%y;
			x = y;
			y = r;
		}
		return x;
	}

}
