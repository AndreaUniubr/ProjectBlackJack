package model.cards;

// Class to represent the Card
public class Card{
    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;

    public Card(Suit suit, Rank rank)
    {
        this.suit = suit;
        this.rank = rank;
        reveal();   // default value is revealed card
    }

    public void reveal()
    {
        this.faceUp = true;
    }

    public void hide()
    {
        this.faceUp = false;
    }

    public boolean isFaceUp()
    {
        return faceUp;
    }

    // check if two cards have equal value
    public boolean isEqualValue (Card c2)
    {
        return (this.getRank().getMinValue() == c2.getRank().getMinValue());
    }

    // check if two cards have equal suit
    public boolean isEqualSuit (Card c2)
    {
        return this.getSuit() == c2.getSuit();
    }

    // check if two cards is equal
    public boolean isEqual (Card c2)
    {
        return isEqualValue(c2) && isEqualSuit(c2);
    }

    public boolean isRed()
    {
        return this.suit.isRed();
    }

    public Suit getSuit()
    {
        return this.suit;
    }

    public Rank getRank()
    {
        return this.rank;
    }

    // Override di toString
    @Override
    public String toString()
    {
        return this.rank.toString() + " of " + this.suit.toString();
    }
}