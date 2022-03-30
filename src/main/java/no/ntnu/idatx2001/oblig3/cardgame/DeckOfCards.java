package no.ntnu.idatx2001.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {
    private final char[] suit = { 'S', 'H', 'D', 'C' };
    private ArrayList<PlayingCard> deckOfCards = new ArrayList<>();

    public DeckOfCards() {
        for (int i = 0; i < suit.length; i++) {
            for (int j = 1; j <= 13; j++) {
                deckOfCards.add(new PlayingCard(suit[i], j));
            }
        }
    }

    public ArrayList<PlayingCard> getDeckOfCards() {
        return deckOfCards;
    }

    public HandOfCards dealHand(int n){
        HandOfCards handOfCards = new HandOfCards();
        //Randomizes the order of the cards
        Collections.shuffle(deckOfCards);

        for (int i = 0; i < n; i++) {
            handOfCards.addCard(deckOfCards.get(i));
        }

        return handOfCards;
    }
}







