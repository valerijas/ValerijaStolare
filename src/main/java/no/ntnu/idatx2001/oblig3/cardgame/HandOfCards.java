package no.ntnu.idatx2001.oblig3.cardgame;

import java.util.ArrayList;

public class HandOfCards {
    ArrayList<PlayingCard> handOfCards;


    //Creates an instance of HandOfCards.
    public HandOfCards() {
        handOfCards = new ArrayList<>();
    }


    // Creates an instance of HandOfCards.
    public ArrayList<PlayingCard> getHandOfCards() {
        return handOfCards;
    }

    public boolean addCard(PlayingCard card){
        if(!(handOfCards.contains(card))){
            handOfCards.add(card);
            return true;
        }
        return false;
    }


    //Creates a list with specific card faces, and checks if there is a flush(5 of the same faces), returns true if that is the case.
    public boolean checkForFlush(int typeOfFlush){
        if(handOfCards.stream().filter(c -> c.getSuit()=='S').count() >= typeOfFlush||
                handOfCards.stream().filter(c -> c.getSuit()=='H').count() >= typeOfFlush||
                handOfCards.stream().filter(c -> c.getSuit()=='D').count() >= typeOfFlush||
                handOfCards.stream().filter(c -> c.getSuit()=='C').count() >= typeOfFlush){
            return true;
        }
        return false;
    }
}

