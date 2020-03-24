package model;

public class Machine {
	private char letter;
	
	private Machine nextMachine;
	private Machine beforeMachine;
	/////
	public Machine(char letter) {
		this.setLetter(letter); 
		setNextMachine(null);
		setBeforeMachine(null);
	}
	
	/**
	 * @return the beforeMachine
	 */
	public Machine getBeforeMachine() {
		return beforeMachine;
	}
	/**
	 * @param beforeMachine the beforeMachine to set
	 */
	public void setBeforeMachine(Machine beforeMachine) {
		this.beforeMachine = beforeMachine;
	}
	/**
	 * @return the nextMachine
	 */
	public Machine getNextMachine() {
		return nextMachine;
	}
	/**
	 * @param nextMachine the nextMachine to set
	 */
	public void setNextMachine(Machine nextMachine) {
		this.nextMachine = nextMachine;
	}
	/**
	 * @return the letter
	 */
	public char getLetter() {
		return letter;
	}
	/**
	 * 
	 * @param letter the letter to set
	 */
	public void setLetter(char letter) {
		this.letter = letter;
	}
}
