package al_07_06;

import java.io.*;
import java.util.*;

class Queue{
	private ArrayList<Integer> queue = new ArrayList<>();
	
	public void push(String str) {
			int num = Integer.parseInt(str);
		
			queue.add(num);
	}
	public int pop() {
		if(queue.size() == 0) return -1; //queue에 아무것도 들어있지 않으면 -1
		return queue.remove(0); //가장 첫번째에 있는 원소 반환하고 삭제
	}
	
	public int size_is() {
		int num = queue.size();
		return num;
	}
	
	public int is_empty() {
		if (queue.size() == 0) return 1; //큐가 비어있으면 1을 출력
		else return 0; //큐가 비어있지 않으면 0을 출력
	}
	public int front() {
		if(queue.size() == 0) return -1; //queue에 아무것도 들어있지 않으면 -1
		return queue.get(0);
	}
	
	public int back() {
		if(queue.size() == 0) return -1; //queue에 아무것도 들어있지 않으면 -1
		return queue.get(queue.size()-1);
	}
}
public class Queue_10845 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		 
		int num = Integer.parseInt(str);
		String[] split_line;
		 
		ArrayList<String> act = new ArrayList<>(); 
		 
		for(int i= 0; i<num;i++) {
			str = br.readLine();
			split_line = str.split(" ");
			 
			if(split_line.length == 1) {
				act.add(split_line[0]);
			}
			else {
				act.add(split_line[0]);
				act.add(split_line[1]);
			}
		}
		
		Queue qu = new Queue();
		for(int j = 0; j < act.size();j++) {
			 String now = act.get(j);
			 if(now.equals("push")) {
				 qu.push(act.get(++j));
			 }
			 else if(now.equals("pop")) {
				 System.out.println(qu.pop());
			 }
			 else if (now.equals("size")) {
				 System.out.println(qu.size_is());
			 }
			 else if (now.equals("empty")) {
				 System.out.println(qu.is_empty());
			 }
			 else if(now.equals("front")) {
				 System.out.println(qu.front());
			 }
			 else {
				 System.out.println(qu.back());
			 }
		}
		
		
	}
}
