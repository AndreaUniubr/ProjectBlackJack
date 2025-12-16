package game;

public enum TransactionType {

    BLACKJACK(2.5),
    PUSH(1.0),
    WINNER(2.0),
    LOOSER(0.0);

    private final double value;

    TransactionType(double value) {
       this.value = value;
    }

    public double getValue() {
        return value;
    }
}
