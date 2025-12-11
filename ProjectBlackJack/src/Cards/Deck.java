package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Classe del mazzo
public class Deck {
    private static final int n_decks = 4;   // 4 * 52 carte
    private List<Card> deck;

    public Deck() 
    {
        this.deck = new ArrayList<>();
        resetDeck();
    }

    // Ripristina il mazzo come nuovo
    private void restore() 
    {
        this.deck.clear();
        for (int i = 0; i < n_decks; i++) 
        {
            for (Suits s : Suits.values()) 
            {
                for (Ranks r : Ranks.values()) 
                {
                    this.deck.add(new Card(s, r));
                }
            }
        }
    }

    /* Utilizziamo una classe publica che         *
     * richiama i 2 metodi precedenti per evitare *
     * chiamate singole inutili e/o dannose       */
    public void resetDeck()
    {
        this.restore();
        Collections.shuffle(deck); 
    }

    // Funzione per il pescaggio della carta
    public Card draw()
    {
        if (deck.isEmpty()) return null;
        return deck.remove(deck.size() -1);
    }
}