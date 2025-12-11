package game;
import java.util.ArrayList;
import java.util.List;
import cards.Card;

public class Hand {

    private List <Card> card;  //Lista di carte in mano

    private boolean isStand; //Indica se il giocatore sta oppure no

    public Hand() {
        this.card = new ArrayList<>();
        this.isStand = false;
    }

    public void addCard(Card card) {  //Aggiunge una carta alla mano
        this.card.add(card);
    }

    public List<Card> getCards() {   //Elenca le carte che hai nella mano
        return card;
    }

    public  void isStand() {        //Imposta lo stato stand
        this.isStand = true;
    }

    public boolean isstand() {
        return this.isStand;
    }
//Calcola il miglior valore delle carte nella mano
    public int getValue(){
       int total = 0;
       int aces = 0;

       //Somma i valori minimi e conta quanti assi ci sono
       for (Card card : card) {

           total += card.getRank().getMinValue();
           if (card.getRank().isAce()){
               aces++;
           }
       }
       //Aggiungi dieci per ogni asso finchè la somma non supera i 21
       while (aces > 0 && total + 10 <= 21) {
           total += 10;
           aces--;
       }
       return total;
    }
    //Una mano è bust se supera i 21
    public boolean isBust() {
        return getValue() > 21;
    }
    //Controlla se la mano è un blackjack con due carte
    public boolean isBlackjack() {
        return card.size() == 2 && getValue() == 21;
    }

    //Controlla se si può fare lo split di due carte
    public boolean canSplit(){
        if (card.size() != 2) return false;
        return card.get(0).getRank() == card.get(1).getRank();
    }

    //Reset della mano
    public void reeset(){
        this.card.clear();
        this.isStand = false;
    }
//Rappresentazione grafica
    @Override
    public String toString() {
        return "Hand " + card.toString() + "(Value " + getValue() + ")";
    }
}

