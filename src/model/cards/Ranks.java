package model.cards;

// enum che rappresenta valori delle carte
public enum Ranks{

    /* Attribuisco valore minimo e massimo ad ogni carta  *
     * in modo da poter attribuire all'asso sia valore 1  *
     * che 11.                                            */

    TWO   (2, 2, 0),
    THREE (3, 3, 1),
    FOUR  (4, 4, 2),
    FIVE  (5, 5, 3),
    SIX   (6, 6, 4),
    SEVEN (7, 7, 5),
    EIGHT (8, 8, 6),
    NINE  (9, 9, 7),
    TEN   (10, 10, 8),
    JACK  (10, 10, 9),
    QUEEN (10, 10, 10),
    KING  (10, 10, 11),
    ACE   (1, 11, 12);

    private final int minValue; // valore minimo della carta
    private final int maxValue; // valore massimo della carta
    private final int position; // posizione per la rappresentazione spirit

    Ranks (int minValue, int maxValue, int position){
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.position = position;
    }

    public int getPosition ()
    {
        return this.position;
    }

    // Restituisce il valore minimo
    public int getMinValue()
    {
        return minValue;
    }

    // Restituisce il valore massimo
    public int getMaxValue()
    {
        return maxValue;
    }

    // Indica se la carta è un asso
    public boolean isAce()
    {
        return this == ACE;
    }

    // toString di Ranks
    @Override
    public String toString()
    {
        String prettyName = name().charAt(0) + name().substring(1).toLowerCase();
        return prettyName;
    }
}