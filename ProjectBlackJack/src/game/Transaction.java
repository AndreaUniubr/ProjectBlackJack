package game;

import java.time.LocalDateTime;

// classe per effettuare le puntate del gioco
public class Transaction {

    private  LocalDateTime timeStamp;
    private  double amount;
    private  TransactionType type;
    private int idHand ;
    //id della mano
    //transaction type
    // time
    // amount



    // Costruttore
    public Transaction(LocalDateTime timeStamp, double amount, TransactionType type, int idHand) {
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.type = type;
        this.idHand = idHand;
    }

    // Getter dei campi
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount * this.type.getValue();
    }

    public int getIdHand() {
        return idHand;
    }

    // Setter dei campi
    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction: " +
                " Time = " + timeStamp +
                " Amount = " + amount +
                " Transaction Type = " + type +
                " ID Hand = " + idHand +
                '}';
    }
}
