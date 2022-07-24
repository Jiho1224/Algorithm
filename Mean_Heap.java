package al_07_06;

import java.io.*;
import java.util.*;


public class Mean_Heap {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int num = Integer.parseInt(str); // 연산의 개수 N
		int point = 0;
		ArrayList<Integer> ans = new ArrayList<>() ;
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for(int i = 0; i<num;i++) {
			str = br.readLine();
			if(str.equals("")) str = br.readLine();
			int temp = Integer.parseInt(str); //입력되는 숫자를 읽는다.
			
			if(temp != 0) {
				queue.add(temp);
			}
			else if(queue.isEmpty()) {
				ans.add(0);
			}
			else {
				ans.add(queue.poll());
			}
		}
		
		for(int i = 0; i<ans.size();i++) {
			System.out.println(ans.get(i));
		}
	}

}
