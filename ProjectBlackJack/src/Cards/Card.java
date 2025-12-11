package Cards;

// Classe che rappresenta l'effettiva carta
public class Card{
    private final Suits suit;
    private final Ranks rank;

    // Costruttore
    public Card(Suits suit, Ranks rank)
    {
        this.suit = suit;
        this.rank = rank;
    }

    // funzioni per vedere se due carte sono uguali
    public boolean isEqualValue (Card c2)
    {
        return (this.getRank().getMinValue() == c2.getRank().getMinValue());
    }

    public boolean isEqual (Card c2)
    {
        boolean res = this.isEqualValue(c2);
        return (res && (this.getSuit() == c2.getSuit()));
    }

    // getter
    public Suits getSuit() 
    {
        return this.suit;
    }

    public Ranks getRank() 
    {
        return this.rank;
    }

    // toString
    @Override
    public String toString()
    {
        return this.rank.toString() + " of " + this.suit.toString();
    }
}
