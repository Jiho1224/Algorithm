package al_07_04;

import java.io.*;
import java.util.*;

public class Go_Stack {
	public static ArrayList<String> list = new ArrayList<>(); // ���α׷� ������ ���� LIST ���� (MOD,SUB,DUP ���� ���ڰ� ��⵵�� �Ѵ�.)
	public static boolean condition = true; // ERROR�� �ߺ� ��µ��� �ʰ� �ϱ� ���Ͽ� ����.
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		
		// QUIT�� �Էµ� �� ���� �����Ѵ�.
		while(true) {
			list.clear(); // ���α׷��� �� ���α׷��� �Է°� ����� ������ ���� ���α׷��� list�� ���� �������־�� �Ѵ�. ���� list�� ����.
			String str = br.readLine();
			
			
			if (str.equals("")) { //""�� �ԷµǾ��ٸ� �������� �о��־�� �Ѵ�.
				str = br.readLine();
			}
			
			if(str.equals("QUIT")) { //"QUIT" �Է� �� while���� �����Ѵ�.	
				break;
			}
			while (!str.equals("END")) { //�� ���α׷��� list�� �����Ѵ�.
                String[] splitLine = str.split(" ");

                // NUM X ���� �ϰ�� split�Ͽ��� �� ���̰� 1�̴�.
                // NUM X�� ��� NUM�� X�� ���ε��� �������ش�.
                
                // ���� ��� 
                // DUP
                // NUM 2
                // �̷��� �Էµȴٸ� list���� [DUP,NUM,2]�� ����� ���̴�.
                if (splitLine.length == 1) {
                    list.add(splitLine[0]);
                } else {
                    list.add(splitLine[0]);
                    list.add(splitLine[1]);
                }

                str = br.readLine();
            }
			
			str = br.readLine();
			int num = Integer.parseInt(str); //  �� ���α׷����� ������ �Է°��� ������ �޴´�.
			
			int []test = new int[num];
			for(int i = 0; i<num;i++) { // �Է°� ����
				str = br.readLine();
				test[i] = Integer.parseInt(str);
			}
			
			
			int point = 0; // stack�� pointer �ʱ�ȭ
			for(int i = 0; i< num; i++) {
				long[] stack = new long[1000]; // stack �ʱ�ȭ
				point = 0; //point �ʱ�ȭ
				condition = true;
				stack[point] = test[i]; // stack�� �Է°��� �켱 �ִ´�.
				point++; 
				
				for(int j = 0; j<list.size();j++) {
					temp = list.get(j); // ���α׷� ��ɾ �ϳ��� ���ʴ�� �޴´�.
					if(temp.equals("NUM")) { //NUM�� ���� ���
						stack[point] = Integer.parseInt(list.get(j+1)); // NUM �������� ���ڰ� ���´� -> stack�� �״´�.
						j++;
						point++;
					}
					else if(temp.equals("POP")) { // POP�� ���� ���
						if(point < 1) { // ���� POP�� ���ڰ� ���ٸ�
							System.out.println("ERROR"); //ERROR
							condition = false;
							break;
						}
						point--; // stack�� point�� �ϳ� �ٿ��ش� -> pop�� ���� ����
					}
					else if(temp.equals("INV")) { //INV�� ���ð��
						if(point < 1) { // ������ ����� ���ٸ� ERROR
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-1] = (-1) * stack[point-1]; // EX. 42 -> -42
					}
					else if(temp.equals("DUP")) { // DUP�� ���� ���
						if(point < 1) { // ���� ����� ���ٸ� ERROR
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point] = stack[point-1]; //ù��° ���ڸ� �����Ͽ� �� ���� �ø���.
						point++;
					}
					else if(temp.equals("SWP")) { // SWP�� ���� ��� -> ù��° ���� �ι�° �� ��ü
						if(point<2) {
							System.out.println("ERROR");
							condition = false;
							break;
						}
						long temp_num;
						temp_num = stack[point-1];
						stack[point-1] = stack[point-2];
						stack[point-2] = temp_num;					
					}
					else if(temp.equals("ADD")) { // ADD�� ���� ��� ���� ������ �ѱ�ų� ���� ����� ���ٸ� ERROR
						if(point<2 || stack[point-2] + stack[point-1] > 1000000000) {
							System.out.println("ERROR");
							condition = false;
							break;
						}
						
						stack[point-2] = stack[point-1] + stack[point-2]; // �ƴϸ� ù��° ���� �ι�° ���� �����ش�.
						point--;
					}
					else if(temp.equals("SUB")) { // SUB�� ���� ��� ���� ������ �ѱ�ų� ���� ����� ���ٸ� ERROR
						if(point<2 || Math.abs(stack[point-2] - stack[point-1]) > 1000000000) {
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-2] = stack[point-2] - stack[point-1];
						point--;
					}
					else if(temp.equals("MUL")) { // MUL�� ���� ��� ���� ������ �ѱ�ų� ���� ����� ���ٸ� ERROR
						if(point<2 || Math.abs(stack[point-2] * stack[point-1]) > 1000000000) {
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-2] = stack[point-2] * stack[point-1];
						point--;
					}
					else if(temp.equals("DIV")) {
						if(point<2 || stack[point-1] == 0) { // DIV�� ���� ��� 0���� ������ �ǰų� ���� ����� ���ٸ� ERROR
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-2] = stack[point-2] / stack[point-1];
						point--;
					}
					// MOD�� ���� ��� 0���� ������ �ǰų� ���� ����� ���ٸ� ERROR
					else {
						if(point<2 || stack[point-1] == 0) {
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-2] = stack[point-2] % stack[point-1];
						point--;
					}
				}
				
				if(point != 1 && condition == true) { // stack�� �ϳ� �̻��� ���� �ִٸ� ERROR
					System.out.println("ERROR");
				}
				else if (condition == false) {
					
				}
				else { // ERROR�� �ƴ� �������� ����� stack�� �ִ� ���Ҹ� ����Ѵ�.
					System.out.println(stack[0]);
				}
				
				
			}
			if(!str.equals("QUIT"))
				System.out.println();
		}
	}
}
