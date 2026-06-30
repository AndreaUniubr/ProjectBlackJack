package model.balance;

// Stores the betting information for a hand.
// Can be extended in the future with additional bet types.
public class Bet
{
    private int standardBet;

    public Bet(int standardBet)
    {
        this.standardBet = standardBet;
    }

    public int getStandardBet()
    {
        return standardBet;
    }

    public void setStandardBet(int standardBet)
    {
        this.standardBet = standardBet;
    }

    public void incrementBet(int value)
    {
        this.standardBet += value;
    }
}
