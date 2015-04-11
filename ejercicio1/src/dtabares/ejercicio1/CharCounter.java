package dtabares.ejercicio1;

import java.lang.Character;
import java.util.HashMap;

public class CharCounter  {

	private int stringToAnalizeLenght;
	private int ocurrences;
	private String stringToAnalize;
	private HashMap<Character,Integer> charCounterMap;

	public CharCounter(String stringToAnalize) {
		this.stringToAnalize = stringToAnalize;
		this.ocurrences = 0;
		this.initializeHashMap();
	}
	
	public int howMany (char charValue){
		switch (charValue){

			case '$': this.ocurrences = -1; break;
			case '@': this.ocurrences = -2; break;
			default:
				stringToAnalizeLenght = stringToAnalize.length();

				for (int i = 0; i < stringToAnalizeLenght; i++) {

					if (this.stringToAnalize.charAt(i) == charValue) {
						this.ocurrences++;
					}
				}
				break;
		}
		return this.ocurrences;
	}

	private void initializeHashMap(){
		this.charCounterMap = new HashMap<Character,Integer>();
		//this.charCounterMap.put('h',1);
		//this.charCounterMap.put('z',0);
		//this.charCounterMap.put('l',3);
		for (char ch = 'a'; ch <= 'z'; ++ch) {
			charCounterMap.put(Character.valueOf(ch), 0);
		}
	}

	public HashMap<Character,Integer> countAll(){
		stringToAnalizeLenght = stringToAnalize.length();
		int charCurrentValueInMap;
		int charNewValueInMap;
		char currentChar;
		for (int i = 0; i < stringToAnalizeLenght; i++) {
			currentChar = this.stringToAnalize.charAt(i);
			//Need to avoid white spaces
			if (currentChar != ' '){
				charCurrentValueInMap = this.charCounterMap.get(currentChar);
				charNewValueInMap = charCurrentValueInMap + 1;
				this.charCounterMap.put(currentChar,charNewValueInMap);
			}
		}
		return charCounterMap;
	}
}