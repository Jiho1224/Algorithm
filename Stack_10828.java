package al_07_06;

import java.io.*;
import java.util.*;

class Stack{
	private ArrayList<Integer> arr = new ArrayList<>();
	
	public void push(String str) {
		int num = Integer.parseInt(str);
		
		arr.add(num);
	}
	
	public int pop() {
		if(arr.size() == 0) return -1; // ���ÿ� ����ִ� ������ ���� ��쿡�� -1 ��ȯ
		
		// ���ÿ� ����ִ� ������ �ִ� ��쿡�� �� �������� ���� (���� ����) �� ��ȯ�ϰ� ����
		int num = arr.remove(arr.size()-1);
		return num;
	}
	
	public int size_is() {
		int num = arr.size();
		return num;
	}
	
	public int is_empty() {
		if (arr.size() == 0) return 1; //������ ��������� 1�� ���
		else return 0; //������ ������� ������ 0�� ���
	}
	
	public int top() {
		if(arr.size() == 0) return -1; //���ÿ� ����ִ� ������ ���� ��쿡�� -1 ���
		else return arr.get(arr.size()-1); //������ ���� ���� �ִ� ���� ���
	}
}
public class Stack_10828 {

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
		 
		 Stack st = new Stack(); // Stack ��ü ���� �� ����
		 for(int j = 0; j < act.size();j++) {
			 String now = act.get(j);
			 if(now.equals("push")) {
				 st.push(act.get(++j));
			 }
			 else if(now.equals("pop")) {
				 int tmp = st.pop();
				 System.out.println(tmp);
			 }
			 else if(now.equals("size")) {
				 int tmp = st.size_is();
				 System.out.println(tmp);
			 }
			 else if(now.equals("empty")) {
				 int tmp = st.is_empty();
				 System.out.println(tmp);
			 }
			 else {
				 int tmp = st.top();
				 System.out.println(tmp);
			 }
		 }
		 
	}

}
