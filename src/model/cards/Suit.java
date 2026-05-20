package model.cards;

// Enumerate that represent the Suit of the cards
public enum Suit {
    // Declaration of all Suit in enum, with symbol, color and position
    SPADES  ("♠", false, 0),    // Seme di picche, nero
    HEARTS  ("♥", true , 1),    // Seme di cuori, rosso
    CLUBS   ("♣", false, 2),    // Seme di fiori, nero
    DIAMONDS("♦", true,  3);    // Seme di quadri, rosso

    private final String symbol;
    private final boolean red;
    private final int position;

    // Creates a suit with symbol, color and position.
    Suit(String symbol, boolean red, int position)
    {
        this.symbol = symbol;
        this.red = red;
        this.position = position;
    }

    // Get to obtain symbol
    public String getSymbol()
    {
        return symbol;
    }

    // return true (red) or false (black)
    public boolean isRed()
    {
        return red;
    }

    public int getPosition ()
    {
        return this.position;
    }

    // Override di toString
    @Override
    public String toString ()
    {
        return name().charAt(0)
                + name().substring(1).toLowerCase()
                + " "
                + symbol;
    }
}