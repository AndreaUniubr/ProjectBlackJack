package Cards;

// enum che rappresenta i Semi delle carte
public enum Suits {
	// Dichiaro le costanti enum ognuna con un simbolo e un colore.
	HEARTS("♥", true),     // Seme di cuori, rosso
	DIAMONDS("♦", true),   // Seme di quadri, rosso 
	SPADES("♠", false),    // Seme di picche, nero 
	CLUBS("♣", false);     // Seme di fiori, nero 

	private final String symbol; 	// Simbolo del seme
	private final boolean red;		// Indica se il seme è rosso

	// Costruttore dell'enum
	Suits(String symbol, boolean red)
	{
		this.symbol = symbol; // Inizializzo simbolo
		this.red = red; 	  // Inizializzo colore
	}

	// Get per ottenere il simbolo
	public String getSymbol() 
	{
		return symbol;
	}

	// Get per sapere se la carta è rossa
	public boolean isRed() 
	{
		return red;
	}

	// Override di toString per rappresentazione 
	@Override
	public String toString () 
	{
		String prettyName = name().charAt(0) + name().substring(1).toLowerCase();
		return prettyName + " " + symbol;
	}
}