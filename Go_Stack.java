package al_07_04;

import java.io.*;
import java.util.*;

public class Go_Stack {
	public static ArrayList<String> list = new ArrayList<>(); // 프로그램 과정을 담을 LIST 선언 (MOD,SUB,DUP 등의 문자가 담기도록 한다.)
	public static boolean condition = true; // ERROR가 중복 출력되지 않게 하기 위하여 선언.
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		
		// QUIT가 입력될 때 까지 수행한다.
		while(true) {
			list.clear(); // 프로그램과 그 프로그램의 입력값 계산이 끝나면 다음 프로그램을 list에 새로 저장해주어야 한다. 따라서 list를 비운다.
			String str = br.readLine();
			
			
			if (str.equals("")) { //""이 입력되었다면 다음줄을 읽어주어야 한다.
				str = br.readLine();
			}
			
			if(str.equals("QUIT")) { //"QUIT" 입력 시 while문을 종료한다.	
				break;
			}
			while (!str.equals("END")) { //한 프로그램을 list에 저장한다.
                String[] splitLine = str.split(" ");

                // NUM X 제외 하고는 split하였을 때 길이가 1이다.
                // NUM X의 경우 NUM과 X를 따로따로 저장해준다.
                
                // 예를 들어 
                // DUP
                // NUM 2
                // 이렇게 입력된다면 list에는 [DUP,NUM,2]가 저장될 것이다.
                if (splitLine.length == 1) {
                    list.add(splitLine[0]);
                } else {
                    list.add(splitLine[0]);
                    list.add(splitLine[1]);
                }

                str = br.readLine();
            }
			
			str = br.readLine();
			int num = Integer.parseInt(str); //  위 프로그램으로 수행할 입력값의 개수를 받는다.
			
			int []test = new int[num];
			for(int i = 0; i<num;i++) { // 입력값 저장
				str = br.readLine();
				test[i] = Integer.parseInt(str);
			}
			
			
			int point = 0; // stack의 pointer 초기화
			for(int i = 0; i< num; i++) {
				long[] stack = new long[1000]; // stack 초기화
				point = 0; //point 초기화
				condition = true;
				stack[point] = test[i]; // stack에 입력값을 우선 넣는다.
				point++; 
				
				for(int j = 0; j<list.size();j++) {
					temp = list.get(j); // 프로그램 명령어를 하나씩 차례대로 받는다.
					if(temp.equals("NUM")) { //NUM이 나올 경우
						stack[point] = Integer.parseInt(list.get(j+1)); // NUM 다음에는 숫자가 나온다 -> stack에 쌓는다.
						j++;
						point++;
					}
					else if(temp.equals("POP")) { // POP이 나올 경우
						if(point < 1) { // 만약 POP할 문자가 없다면
							System.out.println("ERROR"); //ERROR
							condition = false;
							break;
						}
						point--; // stack의 point를 하나 줄여준다 -> pop과 같은 기작
					}
					else if(temp.equals("INV")) { //INV가 나올경우
						if(point < 1) { // 연산할 대상이 없다면 ERROR
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-1] = (-1) * stack[point-1]; // EX. 42 -> -42
					}
					else if(temp.equals("DUP")) { // DUP이 나올 경우
						if(point < 1) { // 연산 대상이 없다면 ERROR
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point] = stack[point-1]; //첫번째 숫자를 복사하여 그 위에 올린다.
						point++;
					}
					else if(temp.equals("SWP")) { // SWP이 나올 경우 -> 첫번째 수와 두번째 수 교체
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
					else if(temp.equals("ADD")) { // ADD가 나올 경우 제한 조건을 넘기거나 연산 대상이 없다면 ERROR
						if(point<2 || stack[point-2] + stack[point-1] > 1000000000) {
							System.out.println("ERROR");
							condition = false;
							break;
						}
						
						stack[point-2] = stack[point-1] + stack[point-2]; // 아니면 첫번째 수와 두번째 수를 더해준다.
						point--;
					}
					else if(temp.equals("SUB")) { // SUB가 나올 경우 제한 조건을 넘기거나 연산 대상이 없다면 ERROR
						if(point<2 || Math.abs(stack[point-2] - stack[point-1]) > 1000000000) {
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-2] = stack[point-2] - stack[point-1];
						point--;
					}
					else if(temp.equals("MUL")) { // MUL이 나올 경우 제한 조건을 넘기거나 연산 대상이 없다면 ERROR
						if(point<2 || Math.abs(stack[point-2] * stack[point-1]) > 1000000000) {
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-2] = stack[point-2] * stack[point-1];
						point--;
					}
					else if(temp.equals("DIV")) {
						if(point<2 || stack[point-1] == 0) { // DIV가 나올 경우 0으로 나누게 되거나 연산 대상이 없다면 ERROR
							System.out.println("ERROR");
							condition = false;
							break;
						}
						stack[point-2] = stack[point-2] / stack[point-1];
						point--;
					}
					// MOD가 나올 경우 0으로 나누게 되거나 연산 대상이 없다면 ERROR
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
				
				if(point != 1 && condition == true) { // stack에 하나 이상의 수가 있다면 ERROR
					System.out.println("ERROR");
				}
				else if (condition == false) {
					
				}
				else { // ERROR가 아닌 정상적인 경우라면 stack에 있는 원소를 출력한다.
					System.out.println(stack[0]);
				}
				
				
			}
			if(!str.equals("QUIT"))
				System.out.println();
		}
	}
}
