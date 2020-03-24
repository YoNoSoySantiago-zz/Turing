package model;

import java.io.*;

public class TuringMachine {
	private Machine c0;
	private Machine c1;
	private Machine c2;
	private int cant;
	////////
	public static final String OUTPUT = "data/output.txt";
	public TuringMachine() {
		cant=0;
		c0 = null;
		c1 = c0;
		c2 = c0;
	}
	/**
	 * 
	 * 
	 * @param position
	 * @param letter
	 */
	public void  add(int position, char letter) {
		cant++;
		Machine nueva = new Machine(letter);
		if(c0==null) {
			c0 = nueva;
			c1 = c0;
			c2 = c0;
		}else {
			switch(position) {
			case 0:;
				nueva.setNextMachine(c0);
				c0.setBeforeMachine(nueva);
				c0=c0.getBeforeMachine();
				if(cant>2) {
					if(cant%2==0) {
						c1=c1.getBeforeMachine();
					}else {
						if(cant==3) {
							c1=c0.getNextMachine();
							c1.setBeforeMachine(c0);
						}
					}
				}else if(cant ==2) {
					c1 =c0;
				}
				break;
			case 1:
				switch(cant) {
				case 1:
					c0 =nueva;
					c1 =c0;
					c2 =c0;
					break;
				case 2:
					nueva.setNextMachine(c0);
					c0.setBeforeMachine(nueva);
					c0 = c0.getBeforeMachine();
					c1 = c0;
					c2 = c1.getNextMachine();
					/*
					Machine next = c0.getNextMachine();
					nueva.setNextMachine(next);
					c0.setNextMachine(nueva);
					*/
					break;
				default:
					if(cant%2!=0)	{
						if(cant ==3) {
							nueva.setNextMachine(c2);
							nueva.setBeforeMachine(c0);
							c0.setNextMachine(nueva);
							c2.setBeforeMachine(nueva);
							c1 = c0.getNextMachine();
							/*
							nueva.setNextMachine(c0.getNextMachine());
							c0.setNextMachine(nueva);
							c1 = c0.getNextMachine();
							c2=c1.getNextMachine();
							*/
						}else {
							Machine next = c1.getNextMachine();
							nueva.setBeforeMachine(c1);
							c1.setNextMachine(nueva);
							nueva.setNextMachine(next);
							next.setBeforeMachine(nueva);
							c1 = c1.getNextMachine();
							/*
							c1.setBeforeMachine(nueva);
							nueva.setNextMachine(c1);
							c1=c1.getBeforeMachine();
							*/
						}
						
					}else {
						Machine before = c1.getBeforeMachine();
						nueva.setBeforeMachine(before);
						before.setNextMachine(nueva);
						nueva.setNextMachine(c1);						
						c1.setBeforeMachine(nueva);
						c1 = c1.getBeforeMachine();
						/*
						nueva.setNextMachine(c1.getNextMachine());
						c1.setNextMachine(nueva);
						*/
					}
				}
				break;
			case 2:
				if(cant == 1) {
					c0 =nueva;
					c1 =c0;
					c2 =c0;
				}else {
					nueva.setBeforeMachine(c2);
					c2.setNextMachine(nueva);
					c2 = c2.getNextMachine();
					if(cant>2) {
						if(cant%2!=0) {
							if(cant==3) {
								c1=c0.getNextMachine();
							}else c1=c1.getNextMachine();
						}
					}else {
						c1=c0;
					}
				}
				
			}
		}
		
	}
	public void delate(int position) {
		if(cant>1) {
			cant--;
			switch(position) {
			case 0:
				c0=c0.getNextMachine();
				c0.setBeforeMachine(null);
				if(cant>2) {
					if(cant%2!=0) {
						if(cant==3) {
							c1=c0.getNextMachine();
						}else c1=c1.getNextMachine();
					}
				}else if(cant==1){
					c1 = c0;
					c2=c1;
				}
				break;
			case 1:
				if(cant==1) {
					c1 = c2;
					c0 = c1;
					break;
				}else {
					if(cant%2==0) {
						if(cant ==2) {
							c1 =c0;
							c1.setNextMachine(c2);
							c2.setBeforeMachine(c1);
						}else {
							Machine next = c1.getNextMachine();		
							c1=c1.getBeforeMachine();
							next.setBeforeMachine(c1);
							c1.setNextMachine(next);
							
						}		
					}else {
						Machine next =c1.getNextMachine();
						c1 = c1.getBeforeMachine();
						c1.setNextMachine(next);
						next.setBeforeMachine(c1);
						c1 = c1.getNextMachine();
					}
				}
				break;		
			case 2:
				c2=c2.getBeforeMachine();
				c2.setNextMachine(null);
				if(cant>2) {
					if(cant%2==0) {
						c1=c1.getBeforeMachine();
					}else {
						if(cant==3) {
							c1=c0.getNextMachine();
						}
					}
				}else {
					if(cant==1) {
						c0=null;
						c1=c0;
						c2=c1;
					}else{
						c1=c0;
					}
				}
				break;
			}
		}else {
			cant=0;
			c0=null;
			c1=c0;
			c2=c0;		
		}
	}
	public char read(int position) {
		
		char result='#';
		switch(position) {
		case 0:
			if(c0!=null) {
				result = c0.getLetter();
			}
			break;
		case 1:
			if(c1!=null) {
				result = c1.getLetter();
			}
			break;
		case 2:
			if(c2!=null) {
				result = c2.getLetter();
			}
		}
		return result;
	}
	public void ejecutable(String array) throws IOException {
		File file = new File(OUTPUT);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
		for (int i = 0; i < array.length(); i++) {
			int position = Character.getNumericValue(array.charAt(i));
			i++;
			switch(array.charAt(i)) {
			case '0':
				bw.write(read(position)+"\n");;
				break;
			case '1':
				i++;
				add(position,array.charAt(i));
				break;
			case '2':
				delate(position);
			}
		}
		bw.close();
		cant =0;
		c0=null;
		c1 =c0;
		c2=c0;
	}
}
