package com.test;

import java.io.Console;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		Author a = new Author();
		Scanner in = new Scanner(System.in);
		while(true){
			String ii = in.nextLine();
			if(ii.startsWith("w")){
				System.out.println("true");
			}
			try {
				a.commandlist.put(ii);
				CheckCommandTask cct = new CheckCommandTask(a);
				a.getSes().execute(cct);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
//		if(ii.equals("w")){
//			System.out.println("true");
//		}
//		Console con = System.console();
//		String in = con.readLine();
//		if(in.equals("w")){
//			System.out.println("true");
//		}
	}

}
