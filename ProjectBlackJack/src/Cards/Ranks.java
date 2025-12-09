package Cards;

public enum Ranks{
	
	/*Attribuisco valore minimo e massimo ad ogni carta
	 * in modo da poter attribuire all'asso sia valore 1 
	 * che 11.
	 */
	
	TWO   (2, 2),
	THREE (3, 3),
	FOUR  (4, 4),
	FIVE  (5, 5),
	SIX   (6, 6),
	SEVEN (7, 7),
	EIGHT (8, 8),
	NINE  (9, 9),
	TEN   (10, 10),
	JACK  (10, 10),
	QUEEN (10, 10),
	KING  (10, 10),
	ACE   (1, 11);
	
	private final int minValue; //valore minimo della carta
	private final int maxValue; //valore massimo della carta
	
	Ranks (int minValue, int maxValue){
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	//Restituisce il valore minimo
	public int getMinValue() {
		return minValue;
	}
	
	//Restituisce il valore massimo
	public int getMaxValue() {
		return maxValue;
	}
	
	//Indica se la carta è un asso
	public boolean isAce(){
		return this == ACE;
	}
	
	//Rappresentazione più leggibile 
	@Override
	public String toString() {
	
		String prettyName = name().charAt(0) + name().substring(1).toLowerCase();
		return prettyName;
		
		
	}
}