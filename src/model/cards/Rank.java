package model.cards;

// Enumerate that represent the Value of the cards
public enum Rank {
    /* Represents Blackjack card values.              *
     * ACE can assume both minimum and maximum value. */

    ACE   (1,  11, 0),
    TWO   (2,  2,  1),
    THREE (3,  3,  2),
    FOUR  (4,  4,  3),
    FIVE  (5,  5,  4),
    SIX   (6,  6,  5),
    SEVEN (7,  7,  6),
    EIGHT (8,  8,  7),
    NINE  (9,  9,  8),
    TEN   (10, 10, 9),
    JACK  (10, 10, 10),
    QUEEN (10, 10, 11),
    KING  (10, 10, 12);

    private final int minValue;
    private final int maxValue;
    private final int position; // position in the sprite sheet

    Rank(int minValue, int maxValue, int position){
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.position = position;
    }

    // return minimum value
    public int getMinValue()
    {
        return minValue;
    }

    // return maximum value
    public int getMaxValue()
    {
        return maxValue;
    }

    // return position, based on position in the sprite sheet
    public int getPosition ()
    {
        return this.position;
    }

    // return true if the card is an ACE
    public boolean isAce()
    {
        return this == ACE;
    }

    // Override di toString
    @Override
    public String toString()
    {
        return name().charAt(0)
                + name().substring(1).toLowerCase();
    }
}