package model.cards;

// enum che rappresenta i Semi delle carte
public enum Suits {
    // Dichiaro le costanti enum ognuna con un simbolo e un colore.
    HEARTS("♥", true, 0),     // Seme di cuori, rosso
    DIAMONDS("♦", true, 1),   // Seme di quadri, rosso
    CLUBS("♣", false, 2),     // Seme di fiori, nero
    SPADES("♠", false, 3);    // Seme di picche, nero

    private final String symbol; 	// Simbolo del seme
    private final boolean red;		// Indica se il seme è rosso
    private final int position;

    // Costruttore dell'enum
    Suits(String symbol, boolean red, int position)
    {
        this.symbol = symbol; // Inizializzo simbolo
        this.red = red; 	  // Inizializzo colore
        this.position = position;
    }

    public int getPosition ()
    {
        return this.position;
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