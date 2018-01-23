package test;
import java.util.Scanner;

public class test1 {
	public void t(){
		Scanner sc=new Scanner(System.in);
		while(sc.hasNextLine()){
			System.out.println("+\" "+sc.nextLine()+"\"");
		}
	}
	public static void main(String agrs[]){
		new test1().t();
	}

}
