package Cards;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Deck {
    private Stack<Cards> deck;
    private static final int n_decks = 4;

    public Deck() {
        this.deck = new Stack<>();
        restore();
        shuffle();
    }

    private void restore() {
        for (int i = 0; i < n_decks; i++) {
            for (Suits s : Suits.values()) {
                for (Ranks r : Ranks.values()) {
                    this.deck.push(new Cards(s, r));
                }
            }
        }
    }

    private void shuffle() {
        ArrayList<Cards> a = new ArrayList<>();
        int i;
        while (!deck.isEmpty()) {
            a.add(deck.pop());
        }
        while (!a.isEmpty()) {
            i = new Random().nextInt(a.size());
            deck.push(a.remove(i));
        }
    }

    // utilizzo una classe publica che richiama i metodi
    // precedenti per evitare chiamate singole inutili da sole alle 2 fun precedenti
    public void resetDeck()
    {
        this.restore();
        this.shuffle();
    }

    public Cards draw(){
        if (deck.isEmpty()) return null;
        return deck.pop();
    }

}
