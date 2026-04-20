package model.entities;

import model.balance.Balance;

public class Player extends Participant{
    private Balance balance;
    private boolean isStand;

    public Player(String name, int initial_balance)
    {
        this.balance = new Balance(initial_balance);
        super(name);
        isStand = false;
    }

    /*
    * ogni player ha un balance e una sezione con le azioni, quando esegue azioni modifica la mano, ad ogni
    * puntata corrisponde un falore true o false in base a se vincente o meno,
    * */

    public Balance getBalance()
    {
        return balance;
    }

    // gestione controllo validità valori effettuata esternamente
    public void setBalance(Balance balance)
    {
        this.balance = balance;
    }

    public void setStand(boolean stand)
    {
        this.isStand = stand;
    }

    // se è impiedi o gioca
    public boolean isBust()
    {
        return isStand;
    }
}