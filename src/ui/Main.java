package ui;

import java.io.*;
import model.TuringMachine;

public class Main {
	public static final String INPUT = "data/in_turing.txt";	
	public static void main(String[] args) throws IOException {
		TuringMachine tm = new TuringMachine();
		File file = new File(INPUT);
		BufferedReader br = new BufferedReader(new FileReader(file));
		long time1 = 0;
		long time2 =0;
		String str = br.readLine();
		time1 = System.currentTimeMillis();		
		while(str !=null) {	
			tm.ejecutable(str.trim());
			time2 = System.currentTimeMillis();
			str = br.readLine();			
		}
		System.out.println((time2-time1));
		br.close();
	}

}
