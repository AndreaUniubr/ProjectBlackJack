package model.balance;

public class Bet {
    private int standard_bet;

    public Bet(int s_bet)
    {
        this.setStandard_bet(s_bet);
    }

    public int getStandard_bet()
    {
        return standard_bet;
    }

    public void setStandard_bet(int standard_bet)
    {
        this.standard_bet = standard_bet;
    }
}
