package dtabares.ejercicio1;

public class CharCounter  {

	private int stringToAnalizeLenght;
	private int ocurrences;
	private String stringToAnalize;

	public CharCounter(String stringToAnalize) {
		this.stringToAnalize = stringToAnalize;
		this.ocurrences = 0;
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
}